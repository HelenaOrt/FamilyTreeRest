/******************************************************************************
 * Copyright (c) 2019. Cristian Gonzalez Morante                              *
 ******************************************************************************/

package com.FamilyTreeRest.FamilyTreeRest.services.impl;

import com.FamilyTreeRest.FamilyTreeRest.controllers.PersonController;
import com.FamilyTreeRest.FamilyTreeRest.entities.Person;
import com.FamilyTreeRest.FamilyTreeRest.exceptions.*;
import com.FamilyTreeRest.FamilyTreeRest.exceptions.generic.ConflictException;
import com.FamilyTreeRest.FamilyTreeRest.models.PersonModel;
import com.FamilyTreeRest.FamilyTreeRest.models.PersonModelSave;
import com.FamilyTreeRest.FamilyTreeRest.repositories.PersonRepository;
import com.FamilyTreeRest.FamilyTreeRest.services.PersonService;
import com.sun.xml.internal.ws.api.message.ExceptionHasMessage;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonServiceImpl implements PersonService {

	private static final Log LOGGER = LogFactory.getLog(PersonServiceImpl.class);

	private final PersonRepository personRepository;

	public PersonServiceImpl(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}

	@Override
	public List<PersonModel> findAll() {
		return personRepository.findAll().stream()
							   .map(PersonModel::from)
							   .collect(Collectors.toList());
	}

	@Override
	public PersonModel findOne(long id) throws EntityNotFoundException {
		return personRepository.findById(id)
							   .map(PersonModel::from)
							   .orElseThrow(() -> new EntityNotFoundException(Person.class, id));
	}

	@Override
	public PersonModelSave save(PersonModelSave personModel) throws Exception {

		Person person = new Person();
		if (!personModel.getFatherId().isPresent())
			throw new EntityNotFoundException(Person.class, personModel.getFatherId().get());

		if (personModel.getFatherId().get() == 0) {
			person.setFather(null);
			personModel.setFatherId(0);
		} else {
			long id = personModel.getFatherId().get();
			if (personRepository.findById(id).isPresent()) {
				Person father = personRepository.findById(id).get();
				if (!personModel.getLastName().equalsIgnoreCase(father.getLastName()))
					throw new IllegalOperationException("El padre y el hijo deben tener el mismo apellido");
				if (personModel.getAge() >= father.getAge()) {
					throw new IllegalOperationException("El hijo no puede tener los mismos años que el padre o más");
				}
			}
			person.setFather(personRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Person.class, id)));
		}

		person.setName(personModel.getName());
		person.setLastName(personModel.getLastName());
		person.setAge(personModel.getAge());
		person.setCountry(personModel.getCountry());

		LOGGER.info("METODO: 'savePerson'-- \nPARAMS: '" + personModel + "'" + person);
		return PersonModelSave.from(personRepository.save(person));
	}

	@Override
	public PersonModelSave update(long id, PersonModelSave personModelSave) throws EntityNotFoundException, DuplicatedEntityException, IdRequiredException, IllegalOperationException {
		long modelId = personModelSave.getId().orElseThrow(IdRequiredException::new);

		if (id != modelId)
			throw new IllegalOperationException("El Id en base de datos no coincide con el introducido, intentelo de nuevo");

		long fatherId = personModelSave.getFatherId().get();

		if (fatherId != personRepository.findById(id).get().getFather().getId())
			throw new IllegalOperationException("Modificar el parentesco podría alterar su árbol genealógico." +
												"Antes de realizar cualquier cambio póngase en contacto con el ADMIN");

		Person person = personRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Person.class, id));
		Optional<Person> father = personRepository.findById(personModelSave.getFatherId().get());
		if (!father.get().getLastName().equalsIgnoreCase(personModelSave.getLastName())) {
			throw new IllegalOperationException("El apellido debe ser igual que el de su padre. " +
												"Si aún así desea modificarlo pongase en contacto con el ADMIN");
		} else {
			person.setLastName(personModelSave.getLastName());
		}

		if (personModelSave.getAge() > father.get().getAge())
			throw new IllegalOperationException("No puede tener más años que su padre");

		person.setName(personModelSave.getName());
		person.setCountry(personModelSave.getCountry());
		person.setAge(personModelSave.getAge());

		return PersonModelSave.from(personRepository.save(person));
	}

	@Override
	public void delete(long id) throws EntityNotFoundException {
		Person person = personRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Person.class, id));
		personRepository.delete(person);
	}

}

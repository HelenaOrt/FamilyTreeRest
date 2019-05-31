/******************************************************************************
 * Copyright (c) 2019. Cristian Gonzalez Morante                              *
 ******************************************************************************/

package com.FamilyTreeRest.FamilyTreeRest.services.impl;

import com.FamilyTreeRest.FamilyTreeRest.controllers.PersonController;
import com.FamilyTreeRest.FamilyTreeRest.entities.Person;
import com.FamilyTreeRest.FamilyTreeRest.exceptions.DuplicatedEntityException;
import com.FamilyTreeRest.FamilyTreeRest.exceptions.EntityNotFoundException;
import com.FamilyTreeRest.FamilyTreeRest.exceptions.IdRequiredException;
import com.FamilyTreeRest.FamilyTreeRest.exceptions.IllegalOperationException;
import com.FamilyTreeRest.FamilyTreeRest.models.PersonModel;
import com.FamilyTreeRest.FamilyTreeRest.models.PersonModelSave;
import com.FamilyTreeRest.FamilyTreeRest.repositories.PersonRepository;
import com.FamilyTreeRest.FamilyTreeRest.services.PersonService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonServiceImpl implements PersonService {

	private static final Log LOGGER = LogFactory.getLog(PersonController.class);

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
	public List<PersonModelSave> fatherModel() {
		return personRepository.findAll().stream()
				.map(PersonModelSave::from)
				.collect(Collectors.toList());
	}

	@Override
	public PersonModel findOne(long id) throws EntityNotFoundException {
		return personRepository.findById(id)
							   .map(PersonModel::from)
							   .orElseThrow(() -> new EntityNotFoundException(Person.class, id));
	}

	@Override
	public PersonModel save(PersonModelSave personModel) throws DuplicatedEntityException, IllegalOperationException, IdRequiredException {
		if (personRepository.findByNameIgnoreCase(personModel.getName()).isPresent()){
			if(personRepository.findByLastNameIgnoreCase(personModel.getLastName()).isPresent()){
				throw new DuplicatedEntityException();
			}
		}

		Person person = new Person();
		long fatherId = personModel.getFather().getId();

		person.setName(personModel.getName());
		person.setLastName(personModel.getLastName());
		person.setAge(personModel.getAge());
		person.setCountry(personModel.getCountry());
		person.setFather(personRepository.findById(fatherId).orElseThrow(javax.persistence.EntityNotFoundException::new));

		/*if(father.isPresent()){
			person.setFather(father.get());
		}else {
			person.setFather(null);
		}*/

		LOGGER.info("METODO: 'savePerson'-- \nPARAMS: '" + personModel + "'" + person);



		return PersonModel.from(personRepository.save(person));
	}

	@Override
	public PersonModel update(long id, PersonModel personModel) throws EntityNotFoundException, DuplicatedEntityException, IdRequiredException, IllegalOperationException {
		long modelId = personModel.getId().orElseThrow(IdRequiredException::new);

		if (id != modelId)
			throw new IllegalOperationException("IDs doesn't match");

		Person person = personRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Person.class, id));
		Optional<Person> duplicatedClient = personRepository.findByNameIgnoreCase(personModel.getName());
		if (duplicatedClient.isPresent()) {
			if (duplicatedClient.get().getId() != person.getId()) {
				throw new DuplicatedEntityException();
			}
		}
		if (personRepository.findByNameIgnoreCase(personModel.getName()).isPresent())
			throw new DuplicatedEntityException();

		person.setName(personModel.getName());

		return PersonModel.from(personRepository.save(person));
	}

	@Override
	public void delete(long id) throws EntityNotFoundException {
		Person person = personRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Person.class, id));
		personRepository.delete(person);
	}

}

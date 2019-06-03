/******************************************************************************
 * Copyright (c) 2019. Cristian Gonzalez Morante                              *
 ******************************************************************************/

package com.FamilyTreeRest.FamilyTreeRest.services.impl;

import com.FamilyTreeRest.FamilyTreeRest.entities.Person;
import com.FamilyTreeRest.FamilyTreeRest.exceptions.EntityNotFoundException;
import com.FamilyTreeRest.FamilyTreeRest.exceptions.IdRequiredException;
import com.FamilyTreeRest.FamilyTreeRest.exceptions.IllegalOperationException;
import com.FamilyTreeRest.FamilyTreeRest.models.PersonModel;
import com.FamilyTreeRest.FamilyTreeRest.repositories.PersonRepository;
import com.FamilyTreeRest.FamilyTreeRest.services.PersonService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PersonServiceImpl implements PersonService {

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
	public PersonModel save(PersonModel personModel) throws Exception {
		Person person = new Person();

		if (personModel.getFatherId() == null) {
			person.setFather(null);
		} else {
			long fatherModelId = personModel.getFatherId();
			fatherExceptions(personModel, person, fatherModelId);
		}

		person.setName(personModel.getName());
		person.setLastName(personModel.getLastName());
		person.setCountry(personModel.getCountry());
		person.setAge(personModel.getAge());

		return PersonModel.from(personRepository.save(person));

	}

	@Override
	public PersonModel update(long id, PersonModel personModel) throws EntityNotFoundException, IdRequiredException, IllegalOperationException {
		long modelId = personModel.getId().orElseThrow(IdRequiredException::new);

		if (id != modelId)
			throw new IllegalOperationException("El id introducido no coincide con la búsqueda realizada");

		Person person = personRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Person.class, id));

		if (personModel.getFatherId() == null) {
			person.setFather(null);
		} else {
			long fatherModelId = personModel.getFatherId();
			if (fatherModelId == id) {
				throw new IllegalOperationException("No puede ser padre de sí mismo");
			}
			fatherExceptions(personModel, person, fatherModelId);
		}
		personSetters(personModel, person);

		return PersonModel.from(personRepository.save(person));

	}

	@Override
	public void delete(long id) throws EntityNotFoundException {
		Person person = personRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Person.class, id));
		Set<Person> personHashSet = person.getSonsSet().stream().filter(child -> child.getFather().getId() == id).collect(Collectors.toSet());
		personHashSet.forEach(child -> child.setFather(null));
		personRepository.delete(person);
	}

	private void fatherExceptions(PersonModel personModel, Person person, long fatherModelId) throws EntityNotFoundException, IllegalOperationException {
		Person fatherRepository = personRepository.findById(fatherModelId)
												  .orElseThrow(() -> new EntityNotFoundException(Person.class, fatherModelId));

		if (!fatherRepository.getLastName().equalsIgnoreCase(personModel.getLastName()))
			throw new IllegalOperationException("El padre y el hijo tienen que tener el mismo apellido");
		if (personModel.getAge() >= fatherRepository.getAge())
			throw new IllegalOperationException("El hijo no puede ser mayor que el padre");
		person.setFather(fatherRepository);
	}

	private void personSetters(PersonModel personModel, Person person) {
		person.setName(personModel.getName());
		person.setLastName(personModel.getLastName());
		person.setCountry(personModel.getCountry());
		person.setAge(personModel.getAge());
	}
}



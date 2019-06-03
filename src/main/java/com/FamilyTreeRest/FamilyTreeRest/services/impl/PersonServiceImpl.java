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
            person.setSonsSet(null);
            person.setLastName(personModel.getLastName());
        } else {
            long fatherModelId = personModel.getFatherId();

            Person fatherRepository = personRepository.findById(fatherModelId)
                    .orElseThrow(() -> new EntityNotFoundException(Person.class, fatherModelId));

            if (!fatherRepository.getLastName().equalsIgnoreCase(personModel.getLastName()))
                throw new IllegalOperationException("El padre y el hijo tienen que tener el mismo apellido");
            if (personModel.getAge() >= fatherRepository.getAge())
                throw new IllegalOperationException("El hijo no puede ser mayor que el padre");
            person.setFather(fatherRepository);
            person.setLastName(personModel.getLastName());
        }

        person.setName(personModel.getName());
        person.setAge(personModel.getAge());
        person.setCountry(personModel.getCountry());

        return PersonModel.from(personRepository.save(person));

    }


    @Override
    public PersonModel update(long id, PersonModel personModel) throws EntityNotFoundException, IdRequiredException, IllegalOperationException {
        long modelId = personModel.getId().orElseThrow(IdRequiredException::new);

        if (id != modelId)
            throw new IllegalOperationException("El introducido no coincide con la búsqueda realizada");

        Person person = personRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Person.class, id));

        /*Person fatherRepository = fatherExceptions(personModel, person);

        person.setFather(fatherRepository);
        person.setName(personModel.getName());
        person.setCountry(personModel.getCountry());
        person.setAge(personModel.getAge());*/

        return PersonModel.from(personRepository.save(person));

    }

    @Override
    public void delete(long id) throws EntityNotFoundException {
        Person person = personRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Person.class, id));
        personRepository.delete(person);
    }

}

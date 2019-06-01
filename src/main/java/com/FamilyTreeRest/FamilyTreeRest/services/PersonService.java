/******************************************************************************
 * Copyright (c) 2019. Cristian Gonzalez Morante                              *
 ******************************************************************************/

package com.FamilyTreeRest.FamilyTreeRest.services;

import com.FamilyTreeRest.FamilyTreeRest.entities.Person;
import com.FamilyTreeRest.FamilyTreeRest.exceptions.DuplicatedEntityException;
import com.FamilyTreeRest.FamilyTreeRest.exceptions.EntityNotFoundException;
import com.FamilyTreeRest.FamilyTreeRest.exceptions.IdRequiredException;
import com.FamilyTreeRest.FamilyTreeRest.exceptions.IllegalOperationException;
import com.FamilyTreeRest.FamilyTreeRest.models.PersonModel;
import com.FamilyTreeRest.FamilyTreeRest.models.PersonModelSave;

import java.util.List;

public interface PersonService {

	List<PersonModel> findAll();

	PersonModel findOne(long id) throws EntityNotFoundException;

	PersonModelSave save(PersonModelSave personModelSave) throws Exception;

	PersonModel update(long id, PersonModel personModel) throws DuplicatedEntityException, IdRequiredException, IllegalOperationException, EntityNotFoundException;

	void delete(long id) throws EntityNotFoundException;

}

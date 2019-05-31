/******************************************************************************
 * Copyright (c) 2019. Cristian Gonzalez Morante                              *
 ******************************************************************************/

package com.FamilyTreeRest.FamilyTreeRest.controllers;

import com.FamilyTreeRest.FamilyTreeRest.exceptions.DuplicatedEntityException;
import com.FamilyTreeRest.FamilyTreeRest.exceptions.EntityNotFoundException;
import com.FamilyTreeRest.FamilyTreeRest.exceptions.IdRequiredException;
import com.FamilyTreeRest.FamilyTreeRest.exceptions.IllegalOperationException;
import com.FamilyTreeRest.FamilyTreeRest.models.PersonModel;
import com.FamilyTreeRest.FamilyTreeRest.services.PersonService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class PersonController {

	private final PersonService personService;

	public PersonController(PersonService personService) {
		this.personService = personService;
	}

	@GetMapping("/people")
	public List<PersonModel> findAll() {
		return personService.findAll();
	}

	@GetMapping("/people/{id}")
	public PersonModel findOne(@PathVariable long id) throws EntityNotFoundException {
		return personService.findOne(id);
	}

	@PostMapping("/people")
	public PersonModel save(@Valid @RequestBody PersonModel personModel) throws DuplicatedEntityException, DuplicatedEntityException {
		return personService.save(personModel);
	}

	@PutMapping("/people/{id}")
	public PersonModel update(@PathVariable long id, @RequestBody PersonModel personModel) throws DuplicatedEntityException, IllegalOperationException, IdRequiredException, IdRequiredException, IllegalOperationException, EntityNotFoundException {
		return personService.update(id, personModel);
	}

	@DeleteMapping("/people/{id}")
	public void delete(@PathVariable long id) throws EntityNotFoundException {
		personService.delete(id);
	}

}

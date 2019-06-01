/******************************************************************************
 * Copyright (c) 2019. Cristian Gonzalez Morante                              *
 ******************************************************************************/

package com.FamilyTreeRest.FamilyTreeRest.repositories;

import com.FamilyTreeRest.FamilyTreeRest.entities.PersonModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<PersonModel, Long> {

	Optional<PersonModel> findByNameIgnoreCase(String name);

	Optional<PersonModel> findByLastNameIgnoreCase(String surname);

}

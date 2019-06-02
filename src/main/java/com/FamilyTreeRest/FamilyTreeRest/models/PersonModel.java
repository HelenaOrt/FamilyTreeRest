/******************************************************************************
 * Copyright (c) 2019. Cristian Gonzalez Morante                              *
 ******************************************************************************/

package com.FamilyTreeRest.FamilyTreeRest.models;

import com.FamilyTreeRest.FamilyTreeRest.entities.Person;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class PersonModel {

	private Long id;

	@NotNull
	@Size(min = 3)
	private String name;

	@NotNull
	@Size(min = 3)

	private String lastName;

	@Min(0)
	private int age;

	@NotNull
	@Size(min = 3)
	private String country;

	private Set<PersonModel> sonsSet;

	public static PersonModel from(Person person) {
		PersonModel personModel = new PersonModel();
		personModel.setId(person.getId());
		personModel.setName(person.getName());
		personModel.setLastName(person.getLastName());
		personModel.setAge(person.getAge());
		personModel.setCountry(person.getCountry());

		personModel.setSonsSet(person.getSonsSet().stream().map(PersonModel::from).collect(Collectors.toSet()));
		return personModel;
	}

	public Optional<Long> getId() {
		return Optional.ofNullable(id);
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Set<PersonModel> getSonsSet() {
		return sonsSet;
	}

	public void addSonModel(PersonModel personModel){
		sonsSet.add(personModel);
	}

	public void setSonsSet(Set<PersonModel> sonsSet) {
		this.sonsSet = sonsSet;
	}

	@Override
	public String toString() {
		return "PersonModel{" +
			   "id=" + id +
			   ", name='" + name + '\'' +
			   ", lastName='" + lastName + '\'' +
			   ", age=" + age +
			   ", country='" + country + '\'' +
			   ", sonsSet=" + sonsSet +
			   '}';
	}
}

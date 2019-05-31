package com.FamilyTreeRest.FamilyTreeRest.models;

import com.FamilyTreeRest.FamilyTreeRest.entities.Person;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class PersonModelSave {

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

	private Person father;

	public static PersonModelSave from(Person person) {
		PersonModelSave personModelSave = new PersonModelSave();
		personModelSave.setId(person.getId());
		personModelSave.setName(person.getName());
		personModelSave.setLastName(person.getLastName());
		personModelSave.setAge(person.getAge());
		personModelSave.setCountry(person.getCountry());
		personModelSave.setFather(person.getFather());
		return personModelSave;
	}

	public Person getFather() {
		return father;
	}

	public PersonModelSave setFather(Person father) {
		this.father = father;
		return this;
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

}

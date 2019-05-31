package com.FamilyTreeRest.FamilyTreeRest.entities;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotNull
	@Size(min = 3)
	private String name;

	@NotNull
	@Size(min = 3)
	private String lastName;

	@NotNull
	@Min(0)
	private int age;

	@NotNull
	private String country;

	@ManyToOne
	private Person father;

	@OneToMany(mappedBy = "father")
	private Set<Person> sonsSet;

	public Person(long id, Person father) {
		this.id = id;
		this.father = father;
	}

	public Person() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public Set<Person> getSonsSet() {
		return sonsSet;
	}

	public void setSonsSet(Set<Person> sonsSet) {
		this.sonsSet = sonsSet;
	}
}

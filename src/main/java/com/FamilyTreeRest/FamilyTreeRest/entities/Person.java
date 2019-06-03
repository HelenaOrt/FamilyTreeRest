package com.FamilyTreeRest.FamilyTreeRest.entities;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
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

	@OneToMany(mappedBy = "father", cascade = {CascadeType.REFRESH})
	private Set<Person> sonsSet = new HashSet<>();


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

	public void addSon(Person person){
		this.sonsSet.add(person);
	}

	public Person getFather() {
		return father;
	}

	public void setFather(Person father) {
		this.father = father;
	}

}

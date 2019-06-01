package com.FamilyTreeRest.FamilyTreeRest.entities;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
public class PersonModel {

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
	private PersonModel father;

	@OneToMany(mappedBy = "father", cascade ={CascadeType.REMOVE, CascadeType.REFRESH})
	private Set<PersonModel> sonsSet;


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

	public Set<PersonModel> getSonsSet() {
		return sonsSet;
	}

	public void setSonsSet(Set<PersonModel> sonsSet) {
		this.sonsSet = sonsSet;
	}

	public void addSon(PersonModel personModel){
		this.sonsSet.add(personModel);
	}

	public PersonModel getFather() {
		return father;
	}

	public void setFather(PersonModel father) {
		this.father = father;
	}

}

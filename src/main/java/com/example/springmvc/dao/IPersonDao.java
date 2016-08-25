package com.example.springmvc.dao;

import java.util.List;

import com.example.springmvc.model.Person;

public interface IPersonDao {
	List<Person> getAllPersons();

	Person getPersonById(int pid);

	boolean addPerson(Person person);

	void updatePerson(Person person);

	void deletePerson(int pid);

	boolean personExists(String name, String location);
}

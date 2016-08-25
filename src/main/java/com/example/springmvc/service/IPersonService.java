package com.example.springmvc.service;

import java.util.List;

import com.example.springmvc.model.Person;

public interface IPersonService {
	List<Person> getAllPersons();

	Person getPersonById(int pid);

	boolean addPerson(Person person);

	void updatePerson(Person person);

	void deletePerson(int pid);
}

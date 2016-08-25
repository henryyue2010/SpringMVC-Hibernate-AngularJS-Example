package com.example.springmvc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springmvc.dao.IPersonDao;
import com.example.springmvc.model.Person;
import com.example.springmvc.service.IPersonService;

@Service
public class PersonServiceImpl implements IPersonService {
	@Autowired
	private IPersonDao personDao;

	@Override
	public Person getPersonById(int pid) {
		return personDao.getPersonById(pid);
	}

	@Override
	public List<Person> getAllPersons() {
		return personDao.getAllPersons();
	}

	@Override
	public boolean addPerson(Person person) {
		if (personDao.personExists(person.getName(), person.getLocation())) {
			return false;
		} else {
			personDao.addPerson(person);
			return true;
		}
	}

	@Override
	public void updatePerson(Person person) {
		personDao.updatePerson(person);
	}

	@Override
	public void deletePerson(int pid) {
		personDao.deletePerson(pid);
	}
}

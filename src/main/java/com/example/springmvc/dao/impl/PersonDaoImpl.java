package com.example.springmvc.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.springmvc.dao.IPersonDao;
import com.example.springmvc.model.Person;

@Transactional
@Repository
public class PersonDaoImpl implements IPersonDao {
	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Override
	public Person getPersonById(int pid) {
		return hibernateTemplate.get(Person.class, pid);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Person> getAllPersons() {
		String hql = "FROM Person as p ORDER BY p.pid";
		return (List<Person>) hibernateTemplate.find(hql);
	}

	@Override
	public boolean addPerson(Person person) {
		hibernateTemplate.save(person);
		return false;
	}

	@Override
	public void updatePerson(Person person) {
		Person p = getPersonById(person.getPid());
		p.setName(person.getName());
		p.setLocation(person.getLocation());
		hibernateTemplate.update(p);
	}

	@Override
	public void deletePerson(int pid) {
		hibernateTemplate.delete(getPersonById(pid));
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean personExists(String name, String location) {
		String hql = "FROM Person as p WHERE p.name = ? and p.location = ?";
		List<Person> persons = (List<Person>) hibernateTemplate.find(hql, name, location);
		return persons.size() > 0 ? true : false;
	}
}

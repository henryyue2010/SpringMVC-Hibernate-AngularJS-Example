package com.example.springmvc.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.springmvc.dao.IPersonDao;
import com.example.springmvc.model.Person;

@Transactional
@Repository
public class PersonDaoImpl implements IPersonDao {
	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Person getPersonById(int pid) {
		return sessionFactory.getCurrentSession().get(Person.class, pid);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Person> getAllPersons() {
		String hql = "FROM Person as p ORDER BY p.pid";
		Query q = sessionFactory.getCurrentSession().createQuery(hql);
		return (List<Person>) q.list();
	}

	@Override
	public boolean addPerson(Person person) {
		try {
			sessionFactory.getCurrentSession().save(person);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public void saveOrUpdate(Person person) {
		sessionFactory.getCurrentSession().saveOrUpdate(person);
	}

	@Override
	public void updatePerson(Person person) {
		Person p = getPersonById(person.getPid());
		p.setName(person.getName());
		p.setLocation(person.getLocation());
		sessionFactory.getCurrentSession().update(p);
	}

	@Override
	public void deletePerson(int pid) {
		sessionFactory.getCurrentSession().delete(getPersonById(pid));
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean personExists(String name, String location) {
		String hql = "FROM Person as p WHERE p.name = ? and p.location = ?";
		Query q = sessionFactory.getCurrentSession().createQuery(hql);
		q.setParameter(0, name);
		q.setParameter(1, location);
		List<Person> persons = (List<Person>) q.list();
		return persons.size() > 0 ? true : false;
	}
}

package br.com.erudio.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.validation.ConstraintViolationException;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.erudio.model.Person;

@Repository
@Transactional(readOnly = true)
public class PersonRepository {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Transactional
	public Person save(Person person) {
		entityManager.merge(person);
		return person;
	}

	@Transactional
	public Person update(Person person) {
		entityManager.merge(person);
		return person;
	}

	@Transactional
	public void delete(Integer id) {
		try {
        	Query query = entityManager.createNamedQuery("Person.deletePersonById").setParameter("idPerson", id);
            query.executeUpdate(); 
        } catch (ConstraintViolationException e) {
            System.out.println("Erro ao alterar status: " + e.getMessage());
            entityManager.getTransaction().rollback();
        } finally {
        	entityManager.close();
        }
	}	
		
	public Person findByName(String name) {
		try {
			return entityManager.createNamedQuery("Person.findPersonByName", Person.class).setParameter("name", name).getSingleResult();
		} catch (PersistenceException e) {
			e.printStackTrace();
			return new Person();
		} 
	}
	
	public List<Person> findAll() {
		try {
			return (List<Person>) entityManager.createNamedQuery("Person.findAllPersons", Person.class).getResultList();
		} catch (PersistenceException e) {
			e.printStackTrace();
			return new ArrayList<Person>();
		}
	}

	public Person findById(Integer id) {
		try {
			return entityManager.createNamedQuery("Person.findPersonById", Person.class).setParameter("idPerson", id).getSingleResult();
		} catch (PersistenceException e) {
			e.printStackTrace();
			return null;
		}
	}
}
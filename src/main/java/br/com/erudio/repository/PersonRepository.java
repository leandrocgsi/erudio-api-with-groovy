package br.com.erudio.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.validation.ConstraintViolationException;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.erudio.dto.PagedSearchDTO;
import br.com.erudio.model.Address;
import br.com.erudio.model.Person;

@Repository
@Transactional(readOnly = true)
public class PersonRepository {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Transactional
	public Person save(Person person) {
		Person persistedPerson = entityManager.merge(person);
		for (Address address : person.getAddresses()) {
			ArrayList<Person> persons = new ArrayList<>();
			persons.add(persistedPerson);
			address.setPersons(persons);
			if (address.getIdAddress() == null) {
				entityManager.persist(address);
			} else {
				/*Query query = entityManager.createNativeQuery("Address.findAllPersonsByAddress").setParameter(1, address.getIdAddress());
				@SuppressWarnings("unchecked")
				ArrayList<Person> personsRelated = (ArrayList<Person>) query.getResultList();
				personsRelated.add(persistedPerson);
				address.setPersons(personsRelated);*/
				entityManager.merge(address);
			}
		}
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
			List<Person> persons = (List<Person>) entityManager.createNamedQuery("Person.findAllPersons", Person.class).getResultList();
			return persons;
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

	public PagedSearchDTO<Person> pagedSearch(PagedSearchDTO<Person> person) {
        Long total = getTotalFindPerson(person);
        @SuppressWarnings("unchecked")
		List<Person> perfilAcessos = getQueryFindPersons(person).getResultList();
        
        person.setList(perfilAcessos);
        person.setTotalResults(total.intValue());
        return person;
    }
    
    private Query getQueryFindPersons(PagedSearchDTO<Person> person) {
        String jpql = getHqlFindPerson(person, "p");
        
		Query query = entityManager.createQuery(jpql + person.getOrderBy("p"));
        
        setFiltersFindPerson(query, person);
        if (person.getCurrentPage() != null) query.setFirstResult((person.getCurrentPage() - 1) * person.getPageSize());
        else person.setCurrentPage(0);
        if (person.getPageSize() != null) query.setMaxResults(person.getPageSize());
        else person.setPageSize(0);
        return query;
    }

	private Long getTotalFindPerson(PagedSearchDTO<Person> person) {
    	Query query = entityManager.createQuery("select count(*) from Person");
    	setFiltersFindPerson(query, person);
    	Long count = (Long)query.getSingleResult();
        return count;
    }
    
    private String getHqlFindPerson(PagedSearchDTO<Person> person, String retorno) {
        String filterName = person.getFilters().get("name").toString();
        StringBuilder jpql = new StringBuilder();
        
        jpql.append("select ").append(retorno).append(" from Person p");
        
        String filter = " where ";
        if (!StringUtils.isEmpty(filterName)) {
            jpql.append(filter).append("p.name = :name");
            filter = " and ";
        }
        return jpql.toString();
    }
    
    private void setFiltersFindPerson(Query query, PagedSearchDTO<Person> person) {
        String filterName = person.getFilters().get("name").toString();
        if (!StringUtils.isEmpty(filterName)) query.setParameter("name", filterName);
    }
}
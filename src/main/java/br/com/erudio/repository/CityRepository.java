package br.com.erudio.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.validation.ConstraintViolationException;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.erudio.model.City;

@Repository
@Transactional(readOnly = true)
public class CityRepository {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Transactional
	public City save(City city) {
		entityManager.persist(city);
		return city;
	}

	@Transactional
	public City update(City city) {
		entityManager.merge(city);
		return city;
	}

	@Transactional
	public void delete(Integer id) {
        try {
        	Query query = entityManager.createNamedQuery("City.deleteCityById").setParameter("idCity", id);
            query.executeUpdate(); 
        } catch (ConstraintViolationException e) {
            System.out.println("Erro ao alterar status: " + e.getMessage());
            entityManager.getTransaction().rollback();
        } finally {
        	entityManager.close();
        }
	}	
		
	public City findByName(String name) {
		try {
			return entityManager.createNamedQuery("City.findCityByName", City.class).setParameter("name", name).getSingleResult();
		} catch (PersistenceException e) {
			e.printStackTrace();
			return null;
		} 
	}
	
	public City findById(Integer idCity) {
		try {
			return entityManager.createNamedQuery("City.findCityById", City.class).setParameter("idCity", idCity).getSingleResult();
		} catch (PersistenceException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<City> findAll() {
		try {
			return (List<City>) entityManager.createNamedQuery("City.findAllCities", City.class).getResultList();
		} catch (PersistenceException e) {
			e.printStackTrace();
			return null;
		}
	}
}
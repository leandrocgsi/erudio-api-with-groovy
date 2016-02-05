package br.com.erudio.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.erudio.model.Gender;

@Repository
@Transactional(readOnly = true)
public class GenderRepository {
	
	@PersistenceContext private EntityManager entityManager;

	public List<Gender> findAllGender() {
		try {
			return entityManager.createQuery("select g from Gender g", Gender.class).getResultList();
		} catch (PersistenceException e) {
			e.printStackTrace();
			return new ArrayList<Gender>();
		}
	}
}
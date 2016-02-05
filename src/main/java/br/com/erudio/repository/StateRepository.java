package br.com.erudio.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.erudio.model.State;

@Repository
@Transactional(readOnly = true)
public class StateRepository {
	
	@PersistenceContext private EntityManager entityManager;

	public State findStateById(Integer id) {
		try {
			return entityManager.createQuery("select p from State p where p.idState = :idState", State.class).setParameter("idState", id).getSingleResult();
		} catch (PersistenceException e) {
			e.printStackTrace();
			return new State();
		}
	}
	
	public State findStateByName(String name) {
		try {
			return entityManager.createQuery("select p from State p where p.stateName like :stateName", State.class).setParameter("stateName", name).getSingleResult();
		} catch (PersistenceException e) {
			e.printStackTrace();
			return new State();
		} 
	}
	
	public List<State> findAllState() {
		try {
			return entityManager.createQuery("select p from State p", State.class).getResultList();
		} catch (PersistenceException e) {
			e.printStackTrace();
			return new ArrayList<State>();
		}
	}
}
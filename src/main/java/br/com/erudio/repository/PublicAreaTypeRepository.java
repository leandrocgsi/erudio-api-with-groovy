package br.com.erudio.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.erudio.model.PublicAreaType;

@Repository
@Transactional(readOnly = true)
public class PublicAreaTypeRepository {
	
	@PersistenceContext private EntityManager entityManager;

	public PublicAreaType findPublicAreaTypeById(Integer id) {
		try {
			return entityManager.createQuery("select p from PublicAreaType p where p.idPublicAreaType = :idPublicAreaType", PublicAreaType.class).setParameter("idPublicAreaType", id).getSingleResult();
		} catch (PersistenceException e) {
			e.printStackTrace();
			return new PublicAreaType();
		}
	}
	
	public PublicAreaType findPublicAreaTypeByName(String name) {
		try {
			return entityManager.createQuery("select p from PublicAreaType p where p.publicAreaTypeDescription like :name", PublicAreaType.class).setParameter("name", name).getSingleResult();
		} catch (PersistenceException e) {
			e.printStackTrace();
			return new PublicAreaType();
		} 
	}
	
	public List<PublicAreaType> findAllPublicAreaType() {
		try {
			return entityManager.createQuery("select p from PublicAreaType p", PublicAreaType.class).getResultList();
		} catch (PersistenceException e) {
			e.printStackTrace();
			return new ArrayList<PublicAreaType>();
		}
	}
}
package br.com.erudio.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.erudio.model.AddressType;

@Repository
@Transactional(readOnly = true)
public class AddressTypeRepository {
	
	@PersistenceContext private EntityManager entityManager;

	public AddressType findAddressTypeById(Integer id) {
		try {
			return entityManager.createQuery("select p from AddressType p where p.idAddressType = :idAddressType", AddressType.class).setParameter("idAddressType", id).getSingleResult();
		} catch (PersistenceException e) {
			e.printStackTrace();
			return new AddressType();
		}
	}
	
	public AddressType findAddressTypeByName(String name) {
		try {
			return entityManager.createQuery("select p from AddressType p where p.description like :description", AddressType.class).setParameter("description", name).getSingleResult();
		} catch (PersistenceException e) {
			e.printStackTrace();
			return new AddressType();
		} 
	}
	
	public List<AddressType> findAllAddressType() {
		try {
			return entityManager.createQuery("select p from AddressType p", AddressType.class).getResultList();
		} catch (PersistenceException e) {
			e.printStackTrace();
			return new ArrayList<AddressType>();
		}
	}
}
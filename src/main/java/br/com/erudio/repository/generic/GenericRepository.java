package br.com.erudio.repository.generic;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public class GenericRepository<T> implements IGenericRepository<T>, Serializable {

    private static final long serialVersionUID = 1L;
    
    private Class<T> clazz;
    
	@PersistenceContext
	private EntityManager entityManager;

	
	
    public GenericRepository() {
		super();
	}

	public GenericRepository(Class<T> clazz) {
        super();
        this.clazz = clazz;
    }
    
    @Override
    @Transactional
    public void save(T entity) {
    	entityManager.persist(entity);
    }

    @Override
    @Transactional
    public void update(T entity) {
    	entityManager.merge(entity);
    }

    @Override
    @Transactional
    public void remove(T entity) {
    	entityManager.remove(entity);
    }

    @Override
    @Transactional
    public void merge(T entity) {
    	entityManager.merge(entity);
    }

    @Override
    @Transactional
    public T getEntity(Serializable id) {
        T entity = (T)entityManager.getReference(clazz, id);
        return entity;
    }

//    @Override
//    public T getEntityByDetachedCriteria(DetachedCriteria criteria) {
//        T entity = (T)criteria.getExecutableCriteria(session).uniqueResult();
//        return entity;
//    }
//
//        
//    @Override
//    public T getEntityByHQLQuery(String stringQuery) {
//        Query query = entityManager.createQuery(stringQuery);        
//        return (T) query.uniqueResult();
//    }
//
//    @Override
//    public List<T> getListByDetachedCriteria(DetachedCriteria criteria) {
//        return criteria.getExecutableCriteria(session).list();
//    }
//    
//    @Override
//    public List<T> getEntities() {
//        List<T> enties = (List<T>) entityManager.createQuery(clazz) createCriteria(clazz).list();
//        return enties;
//    }    
    
}

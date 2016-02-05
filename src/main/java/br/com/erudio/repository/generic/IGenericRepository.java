package br.com.erudio.repository.generic;

import java.io.Serializable;

public interface IGenericRepository<T> {
    
    void save (T entity);
    void update (T entity);
    void remove (T entity);
    void merge (T entity);
    T getEntity(Serializable id);
//    T getEntityByDetachedCriteria(DetachedCriteria criteria);
//    T getEntityByHQLQuery(String stringQuery);
//    List<T> getEntities();
//    List<T> getListByDetachedCriteria(DetachedCriteria criteria);    
    
}

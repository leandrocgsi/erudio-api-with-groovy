package br.com.erudio.repository

import java.io.Serializable;
import java.util.List
import java.util.Map;

import javax.persistence.EntityManager
import javax.persistence.PersistenceContext;
import javax.persistence.Query

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional

import br.com.erudio.dto.PagedSearchVO;
import br.com.erudio.model.Person;
import br.com.erudio.repository.querybuilder.QueryBuilder;;

@Repository
@Transactional(readOnly = true)
class PersonPagedSearchRepository<T extends Serializable> implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private QueryBuilder<Person> queryBuilder; 

    @PersistenceContext
    protected EntityManager entityManager;

    void setParameters(Query query, Map<String, Object> filters) {
		filters.each{ k, v -> (isEmpty(k, v)) ? query.setParameter("${k}", v) : ""}
	}
	
	Boolean isEmpty(String k, Object v) {
		k && v && !v.toString().empty;
	}
	
	private Long getTotal(String alias, String entityName, PagedSearchVO<Person> person) {
		String select = queryBuilder.withVO(person).getBaseSelectCount(alias, entityName) + queryBuilder.withVO(person).getWhereAndParameters(alias);
		System.out.println(select);
		Query query = entityManager.createQuery(select);
		setParameters(query, person.getFilters());
		return (Long)query.getSingleResult();
	}
	
	private Query getSearchQuery(String alias, String entityName, PagedSearchVO<Person> person) {
		String hqlQuery = queryBuilder.withVO(person).getHQLQuery(alias, entityName);
		System.out.println(hqlQuery);
		Query query = entityManager.createQuery(hqlQuery);
		setParameters(query, person.getFilters());
		query.setFirstResult((person.getCurrentPage() - 1) * person.getPageSize());
		query.setMaxResults(person.getPageSize());
		return query;
	}
	
	public PagedSearchVO<Person> getPagedSearch(String alias, String entityName, PagedSearchVO<Person> person) {
		Query searchQuery = getSearchQuery(alias, entityName, person);
		person.setList(searchQuery.getResultList());
		person.setTotalResults(getTotal(alias, entityName, person).intValue());
		return (PagedSearchVO<Person>) person;
	}
}
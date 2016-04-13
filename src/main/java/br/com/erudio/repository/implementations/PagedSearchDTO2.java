package br.com.erudio.repository.implementations;

import java.io.Serializable;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.erudio.dto.PagedSearchVO;
import br.com.erudio.dto.QueryBuilder;
import br.com.erudio.model.Person;

@Repository
@Transactional(readOnly = true)
public class PagedSearchDTO2<T extends Serializable> implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private QueryBuilder<Serializable> queryBuilder; 

    @PersistenceContext
    protected EntityManager entityManager;
	
    /*	The Groovy Way
	void setParameters(Query query) {
		filters.each{ k, v -> (isEmpty(k, v)) ? query.setParameter("${k}", v) : ""}
	}*/
    public void setParameters(Query query, Map<String, Object> filters) {
		for (Map.Entry<String, Object> entry : filters.entrySet()){
		    String k = entry.getKey();
			Object v = entry.getValue();
		    if (entryIsEmpty(k, v)) {
		    	query.setParameter(k, v);
			}
		}
	}
	
    /*	The Groovy Way
    	Boolean isEmpty(String k, Object v) {
		k && v && !v.toString().empty;
	}*/
	private boolean entryIsEmpty(String k, Object v) {
		return k != null && v != null && !StringUtils.isEmpty(k) && !StringUtils.isEmpty(v.toString());
	}
	
	private Long getTotal(String alias, String entityName, Map<String, Object> filters) {
		String select = queryBuilder.getBaseSelectCount(alias, entityName) + queryBuilder.getWhereAndParameters(alias);
		Query query = entityManager.createQuery(select);
		setParameters(query, filters);
		return (Long)query.getSingleResult();
	}
	
	private Query getSearchQuery(String alias, String entityName, PagedSearchVO<Person> person) {
		Query query = entityManager.createQuery(queryBuilder.getHQLQuery(alias, entityName));
		setParameters(query, person.getFilters());
		query.setFirstResult((person.getCurrentPage() - 1) * person.getPageSize());
		query.setMaxResults(person.getPageSize());
		return query;
	}
	
	@SuppressWarnings("unchecked")
	public PagedSearchVO<Person> getPagedSearch(String alias, String entityName, PagedSearchVO<Person> person) {
		Query searchQuery = getSearchQuery(alias, entityName, person);
		person.setList(searchQuery.getResultList());
		person.setTotalResults(getTotal(alias, entityName, person.getFilters()).intValue());
		return (PagedSearchVO<Person>) person;
	}
}
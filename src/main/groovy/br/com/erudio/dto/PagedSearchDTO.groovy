package br.com.erudio.dto

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query

import org.apache.commons.lang.StringUtils

class PagedSearchDTO<T extends Serializable> implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	Integer currentPage;
	Integer pageSize;
	Integer totalResults;
	String sortFields;
	String sortDirections;
	Map<String, Object> filters;
	List<T> list;

	PagedSearchDTO() {}

	PagedSearchDTO(Integer currentPage, Integer pageSize, String sortFields, String sortDirections) {
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.sortFields = sortFields;
		this.sortDirections = sortDirections;
	}

	PagedSearchDTO(Integer currentPage, Integer pageSize, String sortFields, String sortDirections, Map<String, Object> filters) {
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.sortFields = sortFields;
		this.sortDirections = sortDirections;
		this.filters = filters;
	}
	
	PagedSearchDTO(Integer currentPage, String sortFields, String sortDirections) {
		this(currentPage, Integer.valueOf(10), sortFields, sortDirections);
	}
	
	Integer getCurrentPage(){
		if (currentPage) return currentPage;
		return 0;
	}
	
	Integer getPageSize(){
		if (pageSize) return pageSize;
		return 0;
	}

	Integer getStart() {
		return Integer.valueOf((getCurrentPage().intValue() - 1) * getPageSize().intValue());
	}
	
	String getOrderBy(String alias) {
		" order by ${alias}.${sortFields} ${sortDirections}";
	}
	
	String getWhereAndParameters(String alias) {
		String query = " where ";
		ArrayList<String> parametros = new ArrayList<>();
		for (Map.Entry<String, Object> entry : filters) {
			if (isNotEmpty(entry)) parametros.add("${alias}.${entry.getKey()} = :${entry.getKey()} and ");
		}
		return query + " " + parametros.join(" ") + "1 = 1 ";
	}
	
	void setParameters(Query query) {
		for (Map.Entry<String, Object> entry : filters) {
			if (isNotEmpty(entry)) query.setParameter("${entry.getKey()}", entry.getValue());
		}
	}
	
	Boolean isNotEmpty(Map.Entry<String, Object> entry) {
		entry.getKey() && entry.getValue();
	}
	
	String getHQLQuery(String alias, String entityName) {
		getBaseSelect(alias, entityName) + getWhereAndParameters(alias) + getOrderBy(alias);
	}

	String getBaseSelect(String alias, String entityName) {
		"select ${alias} from ${entityName} ${alias} ";
	}

	String getBaseSelectCount(String alias, String entityName) {
		"select count(*) from ${entityName} ${alias} ";
	}
	
	Long getTotal(EntityManager entityManager, String alias, String entityName) {
		String select = getBaseSelectCount(alias, entityName) + getWhereAndParameters(alias);
		Query query = entityManager.createQuery(select);
		setParameters(query);
		(Long)query.getSingleResult();
	}
	
	Query getSearchQuery(EntityManager entityManager, String alias, String entityName) {
		Query query = entityManager.createQuery(getHQLQuery(alias, entityName));
		setParameters(query);
		query.setFirstResult((getCurrentPage() - 1) * getPageSize());
		query.setMaxResults(getPageSize());
	}
	
	PagedSearchDTO<T> getPagedSearch(EntityManager entityManager, String alias, String entityName) {
		Query searchQuery = getSearchQuery(entityManager, alias, entityName);
		setList(searchQuery.getResultList());
		setTotalResults(getTotal(entityManager, alias, entityName).intValue());
		return this;
	}
}
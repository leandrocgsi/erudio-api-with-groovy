package br.com.erudio.dto;

import java.io.Serializable;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public class QueryBuilder<T extends Serializable> implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private PagedSearchVO<T> pagedSearchVO = new PagedSearchVO<T>();
	
	public QueryBuilder<T> withVO(PagedSearchVO<T> pagedSearchVO) {
		this.pagedSearchVO = pagedSearchVO;
		return this;
	}
	
	String getOrderBy(String alias) {
		" order by ${alias}.${sortFields} ${sortDirections}";
	}
	
	String getWhereAndParameters(String alias) {
		def query = ' where ';
		filters.each{ k, v -> (isEmpty(k, v)) ? query = query + "${alias}.${k} = :${k} and " : "" }
		query + '1 = 1 ';
	}

	private boolean entryIsEmpty(String k, Object v) {
		return k != null && v != null && !StringUtils.isEmpty(k) && !StringUtils.isEmpty(v.toString());
	}
	
	public String getHQLQuery(String alias, String entityName) {
		return getBaseSelect(alias, entityName) + getWhereAndParameters(alias) + getOrderBy(alias);
	}

	public String getBaseSelect(String alias, String entityName) {
		return "select " + alias + " from " + entityName + " " + alias + " ";
	}

	public String getBaseSelectCount(String alias, String entityName) {
		return "select count(*) from " + entityName + " " + alias + " ";
	}
	
	public Integer getStart() {
		return Integer.valueOf((pagedSearchVO.getCurrentPage() - 1) * pagedSearchVO.getPageSize());
	}
}

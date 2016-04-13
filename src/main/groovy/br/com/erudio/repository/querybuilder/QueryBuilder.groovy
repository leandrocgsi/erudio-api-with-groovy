package br.com.erudio.repository.querybuilder;

import java.io.Serializable;
import java.util.Map;

import org.apache.commons.lang3.StringUtils
import org.springframework.stereotype.Repository

import br.com.erudio.dto.PagedSearchVO;;;

@Repository
public class QueryBuilder<T extends Serializable> implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private PagedSearchVO<T> pagedSearchVO = new PagedSearchVO<T>();
	
	public QueryBuilder<T> withVO(PagedSearchVO<T> pagedSearchVO) {
		this.pagedSearchVO = pagedSearchVO;
		return this;
	}
	
	String getOrderBy(String alias) {
		" order by ${alias}.${pagedSearchVO.sortFields} ${pagedSearchVO.sortDirections}";
	}
	
	String getWhereAndParameters(String alias) {
		def query = ' where ';
		pagedSearchVO.filters.each{ k, v -> (isEmpty(k, v)) ? query = query + "${alias}.${k} = :${k} and " : "" }
		query + '1 = 1 ';
	}

	Boolean isEmpty(String k, Object v) {
		k && v && !v.toString().empty;
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
	
	Integer getStart() {
		Integer.valueOf((getCurrentPage() - 1.intValue()) * getPageSize());
	}
}

package br.com.erudio.dto;

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

	Integer getStart() {
		return Integer.valueOf((getCurrentPage().intValue() - 1) * getPageSize().intValue());
	}
	
	String getOrderBy(String alias) {
		" order by ${alias}.${sortFields} ${sortDirections}";
	}
}
package br.com.erudio.dto;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import br.com.erudio.dto.PagedSearchDTO;
import br.com.erudio.model.Person;

public class PagedSearchDTOTest {
	
	PagedSearchDTO<Person> dto = new PagedSearchDTO<Person>();
	
	@Before
	public void setup() {
		dto = mockDTO();	
	}
	
	@Test
	public void getBaseSelect() {
		String baseSelect = "select p from Person p ";
		assertEquals(dto.getBaseSelect("p", "Person"), baseSelect);
	}
	
	@Test
	public void getBaseSelectCount() {
		String baseSelect = "select count(*) from Person p ";
		assertEquals(dto.getBaseSelectCount("p", "Person"), baseSelect);
	}
	
	@Test
	public void getWhereAndParametersTest() {
		String whereClause = " where  p.phone = :phone and  p.name = :name and  p.email = :email and 1 = 1 ";
		assertEquals(dto.getWhereAndParameters("p"), whereClause);
	}
	
	@Test
	public void getHQLQueryTest() {
		String selectWithParameters = "select p from Person p"
				+ "  where  p.phone = :phone and"
				+ "  p.name = :name and"
				+ "  p.email = :email and 1 = 1 "
				+ " order by p.name asc";
		assertEquals(dto.getHQLQuery("p", "Person"), selectWithParameters);
	}
	
	@Test
	public void getOrderByTest() {
		assertEquals(dto.getOrderBy("p"), " order by p.name asc");
	}
	
	@Test
	public void getPageSizeTest() {
		assertEquals(dto.getPageSize(), (Integer)10);
	}
	
	@Test
	public void getCurrentPageTest() {
		assertEquals(dto.getCurrentPage(), (Integer)1);
	}
	
	@Test
	public void getPageSizeNullTest() {
		dto.setPageSize(null);
		assertEquals(dto.getPageSize(), (Integer)0);
	}
	
	@Test
	public void getCurrentPageNullTest() {
		dto.setCurrentPage(null);
		assertEquals(dto.getCurrentPage(), (Integer)0);
	}
	
	public PagedSearchDTO<Person> mockDTO(){
		dto.setCurrentPage(1);
		dto.setPageSize(10);
		dto.setSortFields("name");
		dto.setSortDirections("asc");
		dto.setFilters(mockFilters());
		return dto;
	}

	private Map<String, Object> mockFilters() {
		Map<String, Object> filters = new HashMap<String, Object>();
		filters.put("name", "LEANDRO");
		filters.put("email", "a@b.c");
		filters.put("phone", "12345678998");
		filters.put("cpf", null);
		return filters;
	}

}

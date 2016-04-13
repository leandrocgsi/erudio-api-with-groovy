package br.com.erudio.dto;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import br.com.erudio.model.Person;
import br.com.erudio.repository.querybuilder.QueryBuilder;

public class QueryBuilderTest {
    
    PagedSearchVO<Person> dto = new PagedSearchVO<Person>();
    QueryBuilder<Person> queryBuilder = new QueryBuilder<Person>();
    
    @Before
    public void setup() {
        dto = mockDTO();    
    }
    
    @Test
    public void getPageSizeTest() {
        assertEquals((Integer)10, dto.getPageSize());
    }
    
    @Test
    public void getCurrentPageTest() {
        assertEquals((Integer)1, dto.getCurrentPage());
    }
    
    @Test
    public void getPageSizeNullTest() {
        dto.setPageSize(null);
        assertEquals((Integer)0, dto.getPageSize());
    }
    
    @Test
    public void getCurrentPageNullTest() {
        dto.setCurrentPage(null);
        assertEquals((Integer)0, dto.getCurrentPage());
    }
    
    public PagedSearchVO<Person> mockDTO(){
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
        filters.put("religion", null);
        filters.put("cpf", null);
        filters.put(null, "COSTA");
        return filters;
    }
}
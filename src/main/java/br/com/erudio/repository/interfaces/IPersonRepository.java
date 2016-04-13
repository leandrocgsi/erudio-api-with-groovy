package br.com.erudio.repository.interfaces;

import java.util.List;

import br.com.erudio.dto.PagedSearchVO;
import br.com.erudio.model.Person;
import br.com.erudio.repository.generic.IGenericRepository;;


public interface IPersonRepository extends IGenericRepository<Person>{

	List<Person> findAll();
	Person findByName(String name);
	Person findById(Integer id);
	void deleteById(Integer id);
	PagedSearchVO<Person> pagedSearch(PagedSearchVO<Person> person);
}
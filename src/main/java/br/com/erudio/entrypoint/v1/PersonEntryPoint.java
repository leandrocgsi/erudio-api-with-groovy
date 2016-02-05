package br.com.erudio.entrypoint.v1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.erudio.model.Person;
import br.com.erudio.repository.PersonRepository;

import com.wordnik.swagger.annotations.Api;

@Controller
@Secured("ROLE_USER")
@Api(value = "/person", description = "Exposes endpoints of service Person.")
@RequestMapping("/api/v1/person")
class PersonEntryPoint {

    private PersonRepository personRepository;

    @Autowired
    public PersonEntryPoint(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }
    
	@RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody Person save(@RequestBody Person person) {
		return personRepository.save(person);
	}

	@RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody Person update(@RequestBody Person person) {
		return personRepository.update(person);
	}
    
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody ResponseEntity<Void> delete(@PathVariable Integer id) {
		personRepository.delete(id);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody Person findById(@PathVariable Integer id) {
		return personRepository.findById(id);
	}
	
	@RequestMapping(value = "/findByName/{name}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody Person findPersonByName(@PathVariable String name) {
        return personRepository.findByName(name);
    }
    
	@RequestMapping(value = "/findAll", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody List<Person> findAll() {
        return personRepository.findAll();
    }
}
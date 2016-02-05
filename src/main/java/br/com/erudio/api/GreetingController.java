package br.com.erudio.api;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.erudio.model.Greeting;

@Controller
@Secured("ROLE_USER")
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping(value = "greeting", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
    	System.out.println("Nome " + name);
        Greeting greeting = new Greeting(counter.incrementAndGet(), String.format(template, name));
		return greeting;
    }
}

package app.controller.hello;

import java.util.concurrent.atomic.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import app.service.hello.*;

@Controller
@Profile("dev")
public class HWController {

	@Autowired
	private IService service;
	
	
	    private static final String template = "Hello, %s! - %s";
	    private final AtomicLong counter = new AtomicLong();

	    @GetMapping("/hw")
	    @ResponseBody
	    public Greeting sayHello(@RequestParam(name="name", required=false, defaultValue="Stranger") String name) {
	        return new Greeting(counter.incrementAndGet(), String.format(template, name, service.getValus(name)));
	    }

}

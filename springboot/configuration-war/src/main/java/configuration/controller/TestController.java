package configuration.controller;

import java.net.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

import configuration.config.*;

@RestController
@RequestMapping("/c1")
@CrossOrigin
//@PropertySource(value="classpath:c1/application.properties")
public class TestController {
	
	@Autowired
	private Config config;
	
	@Value("${testValue}")
	private String testValue;
	
	@Value("${jboss.server.config.dir}")
	private String jbossConfigDir;
	
	@Value("${spring.config.location}")
	private String springConfigLocation;
	
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String check(){
    	String dir = System.getProperty("jboss.server.config.dir");
    	
		//InputStream s = this.getClass().getClassLoader().getResourceAsStream("application.properties");
    	//System.out.println(new InputStreamReader(s).toString());
    	URL url = this.getClass().getClassLoader().getResource("application.properties");
    	
    	
    	
    	StringBuilder result = new StringBuilder();
    	result.append("OK - ").append(config.getValue1());    	
    	result.append(" testValue=").append(testValue);
    	result.append(" spring.config.location=").append(springConfigLocation);
    	return  result.toString();
    }

}

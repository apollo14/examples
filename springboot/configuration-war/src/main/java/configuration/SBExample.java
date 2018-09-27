package configuration;

import java.util.*;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.boot.builder.*;
import org.springframework.boot.web.support.*;

@SpringBootApplication
public class SBExample extends SpringBootServletInitializer {
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return configureApp(builder);
	}
	
	public static void main(String[] args){
		new SpringApplicationBuilder(SBExample.class).run(args);
	}
	
	private SpringApplicationBuilder configureApp(SpringApplicationBuilder builder){
		
				
		builder.sources(SBExample.class).bannerMode(Banner.Mode.OFF);
		builder.properties(getProperties());
		return builder;
	}
	
	private Properties getProperties(){
		String jbossConfigDir = System.getProperty("jboss.server.config.dir");
		
		Properties p = new Properties();
		p.put("spring.config.location", jbossConfigDir + "/c1/");
		return p;
	}
}

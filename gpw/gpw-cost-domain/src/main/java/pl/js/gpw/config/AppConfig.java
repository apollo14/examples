package pl.js.gpw.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
	@Value("${dataLocation}")
	private String dataLocation;
	

}

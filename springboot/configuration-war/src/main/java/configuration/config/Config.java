package configuration.config;

import org.springframework.boot.context.properties.*;
import org.springframework.context.annotation.*;

@Configuration
@ConfigurationProperties("cfg")
//@PropertySource("classpath:cfg.properties") //yaml - not supported (only application.yml)
public class Config {
	private String value1;
	private String value2;

	public String getValue1() {
		return value1;
	}

	public void setValue1(String value1) {
		this.value1 = value1;
	}

	public String getValue2() {
		return value2;
	}

	public void setValue2(String value2) {
		this.value2 = value2;
	}

}

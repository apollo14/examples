package configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import configuration.config.Config;

@SpringBootApplication
public class Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication a = new SpringApplication(Application.class);
		a.setBannerMode(Banner.Mode.OFF);
		a.run(args);

	}

	@Autowired
	private Config config;
	
	@Override
	public void run(String... arg0) throws Exception {
		System.out.println("Application - Configuration started.");
		System.out.println("Config.value1 = " + config.getValue1());
		
	}

}

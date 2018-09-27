package app;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;

@SpringBootApplication
public class Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication a = new SpringApplication(Application.class);
		a.setBannerMode(Banner.Mode.OFF);
		a.run(args);

	}

	
	@Override
	public void run(String... arg0) throws Exception {
//		System.out.println("Application - Configuration started.");
//		System.out.println("Config.value1 = " + config.getValue1());
		
	}

}

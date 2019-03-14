package pl.js.gpw;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Application implements CommandLineRunner {
	
	@Value("${dataLocation}")
	private String dataLocation;
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	public void run(String... args) throws Exception {
		System.out.println("Data location: " + dataLocation);
	}
	
}

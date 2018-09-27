package hierarchy;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import hierarchy.validator.Validator;

@SpringBootApplication
public class Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication a = new SpringApplication(Application.class);
		a.setBannerMode(Banner.Mode.OFF);
		a.run(args);

	}

	@Autowired
	private List<Validator> validatorsL;

	@Autowired
	private Map<String, Validator> validatorsM;
	
	@Autowired
	@Qualifier("X")
	private Map<String, Validator> validatorsQ;

	@Override
	public void run(String... arg0) throws Exception {
		System.out.println("Application - Validators LIST.");

		validatorsL.forEach(it -> it.validate());

		System.out.println("Application - Validators MAP.");
		for (String v : validatorsM.keySet()) {
			System.out.print(v + " - ");
			validatorsM.get(v).validate();
		}

		System.out.println("Application - Validators With Qualifier.");
		for (String v : validatorsQ.keySet()) {
			System.out.print(v + " - ");
			validatorsQ.get(v).validate();
		}

	}

}

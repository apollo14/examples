package others.config.di1.start;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application  implements CommandLineRunner {
	public static void main(String[] args) {
		SpringApplication a = new SpringApplication(Application.class);
		a.setBannerMode(Banner.Mode.OFF);
		a.run(args);
	}

	@Autowired
	private C1 c1;
	
	@Override
	public void run(String... arg0) throws Exception {
		System.out.println("Application - Configuration started.");
		//System.out.println("C1 = " + config.getValue1());
		
	}

}

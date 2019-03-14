package build.version;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BuildVersionApp {

	public static void main(String[] args) {
		SpringApplication.run(BuildVersionApp.class, args);

		GreetingWebClient gwc = new GreetingWebClient();
		System.out.println(gwc.getVersion());
	}
}
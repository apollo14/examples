package hello2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Hello2App {

	public static void main(String[] args) {
		SpringApplication.run(Hello2App.class, args);

		GreetingWebClient gwc = new GreetingWebClient();
		System.out.println(gwc.getVersion());
		System.out.println(gwc.getResult());
		System.out.println(gwc.getResultH2());
	}
}
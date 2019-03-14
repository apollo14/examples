package lookup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Lookup2App {

	public static void main(String[] args) {
		SpringApplication.run(Lookup2App.class, args);

		LookupWebClient gwc = new LookupWebClient();
		System.out.println(gwc.getResult());
		System.out.println(gwc.getResult());
	}
}
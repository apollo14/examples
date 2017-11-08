package encryption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;

import encryption.config.EncryptedConfig;
import encryption.config.PersonConfig;

@SpringBootApplication
//@EnableEncryptableProperties
public class Application implements ApplicationRunner {

	@Value("${name}")
	private String name;
	
	@Autowired
	private PersonConfig person;
	
	@Autowired
	private EncryptedConfig config;
	
	@Value("${encryption.p}")
	private String password;

	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Override
	public void run(ApplicationArguments arg0) throws Exception {
		System.out.println("Name from Value: " + name);
		System.out.println("Name from config: " + person.getName());
		System.out.println("Password from Value: " + password);
		System.out.println("Password from config: " + config.getPassword());
		
	}
}

package encryption.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;
import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;

//@Configuration
//@Component
//@EnableEncryptableProperties
//@ConfigurationProperties(prefix="encryption")
public class EncryptedConfig {
	
	//@Value("${encryption.password}")
	private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}

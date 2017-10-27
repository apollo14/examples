package encryption.config;


import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

@Component
public class EncryptedConfig {
	
	@Value("${encryption.p}")
	private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}

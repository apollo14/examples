package configuration.service.hello;

import static org.assertj.core.api.BDDAssertions.*;

import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.context.annotation.*;
import org.springframework.test.context.*;
import org.springframework.test.context.junit4.*;

import app.service.hello.*;

@RunWith(SpringRunner.class)
@EnableAutoConfiguration
@ComponentScan(basePackages="configuration")
@ActiveProfiles("dev")
public class ServiceImplTest {
	
	@Autowired
	private IService sut;

	@Test
	public void toUpperCaseTest(){
		String actual = sut.getValus("test");

		then(actual).isEqualTo("TEST");
		
//		Assert.assertNotNull(actual);
//		Assert.eq
	}
}

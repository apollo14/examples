package app.service.hello;

import org.springframework.context.annotation.*;
import org.springframework.stereotype.*;

@Component
@Profile("test")
public class ServiceTestImpl implements IService{
	@Override
	public String getValus(String input) {
		return input.toLowerCase();
	}

}

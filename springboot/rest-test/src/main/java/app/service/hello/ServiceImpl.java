package app.service.hello;

import org.springframework.context.annotation.*;
import org.springframework.stereotype.*;

@Component
@Profile("dev")
public class ServiceImpl implements IService {

	@Override
	public String getValus(String input) {
		return input.toUpperCase();
	}

}

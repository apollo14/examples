package hierarchy.validator;

import org.springframework.stereotype.Component;

@Component
public class TextValidator implements Validator {
	@Override
	public void validate() {
		System.out.println("Text validator");
	}
}

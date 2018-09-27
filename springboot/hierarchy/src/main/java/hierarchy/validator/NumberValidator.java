package hierarchy.validator;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("X")
public class NumberValidator implements Validator {
	@Override
	public void validate() {
		System.out.println("Number validator");
	}
}

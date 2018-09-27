package hierarchy.validator;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("X")
public class ObjectValidator implements Validator {
	@Override
	public void validate() {
		System.out.println("Object validator");
	}
}

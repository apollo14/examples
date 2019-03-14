package others.config.di1.start;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class C1 {
	@Autowired
	private C2 c2;
}

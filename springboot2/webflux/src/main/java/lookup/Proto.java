package lookup;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.scripting.ScriptCompilationException;
import org.springframework.stereotype.Component;
@Component
@Scope(value=ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode=ScopedProxyMode.TARGET_CLASS)
public class Proto {
	private static AtomicLong count = new AtomicLong(0);
			
	
	public Proto() {
		count.incrementAndGet();
	}


	public static long getCount() {
		return count.get();
	}
	
	
}

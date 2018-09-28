package interview.collection.set;

import java.util.HashSet;
import java.util.Set;

public class SetTest {

	public void addNullToHashSet() {
		Set<Integer> s = new HashSet<>();
		boolean r = s.add(null);
		r = s.add(null);
		r = s.add(1);
		r = s.add(1);
		System.out.println(s);
	}
	
	public static void main(String[] args) {
		SetTest st = new SetTest();
		st.addNullToHashSet();
		
	}
}

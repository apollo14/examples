package js.f;

import java.util.*;
import java.util.function.*;

public class Main {

	public static void main(String[] args) {

		List<VO> list = new ArrayList<>();
		list.add(new VO(3, "N3"));
		list.add(new VO(1, "N1"));
		list.add(new VO(2, "N2"));
		
		Predicate<VO> p1 = p-> p.getId() > 1;
		
		
		//Collections.sort(list, (s1, s2) -> s1.getName().compareTo(s2.getName()));
		//list.forEach(s -> System.out.println(s.getName()));
		Function<VO, String> toStrF = (e) -> e.getName();
		
		list.stream().filter(p1).sorted((s1, s2)-> s1.getId() - s2.getId()).map(toStrF).forEach(System.out::println);
		
		/*BiFunction<Integer, String, VO> vo = VO::new;
		VO voi = vo.apply(1,  "2");		
		System.out.println(voi.getId() + " - " + voi.getName());
		*/

		
		Check c = (s) -> {
			System.out.println(s);
			return "zz";
			}; 
		System.out.println(c.check("aa"));
		
		
		
	}

}

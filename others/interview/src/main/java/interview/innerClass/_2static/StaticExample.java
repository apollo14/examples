package interview.innerClass._2static;

import interview.model.Person;

public class StaticExample {

	private static String s = "aaa";
	private static Person p = new Person("aa");
	private InnerClass ic = new InnerClass();
	
	public void doSthOuter() {
		p.setName("sss");
		System.out.println("outer from outer s=" + s);
		System.out.println("outer from outer p=" +p);		
		System.out.println("inner from outer pi=" +ic.pi);
	}
	
	private static class InnerClass {
		private Person pi = new Person("pp");
		
		public void doSthInner() {
			p.setName("cc");
			System.out.println("outer from inner s=" + s);
			System.out.println("outer from inner p=" +p);
			//System.out.println("outer from inner ic.pi=" +ic.pi);
			System.out.println("inner from inner pi=" +pi);
		}	
	}
	
	public static void main(String[] args) {
		StaticExample o = new StaticExample();
		o.doSthOuter();
		StaticExample.InnerClass i = new StaticExample.InnerClass();
		i.doSthInner();
	}
}

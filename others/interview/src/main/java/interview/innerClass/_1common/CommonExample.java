package interview.innerClass._1common;

import interview.model.Person;

public class CommonExample {

	private String s = "aaa";
	private Person p = new Person("aa");
	
	public void doSth() {
		Person pl = new Person("local");
		pl = new Person();
		
		InnerClass ic = new InnerClass();
		
		p.setName("sss");
		System.out.println("outer from outer s=" + s);
		System.out.println("outer from outer p=" +p);		
		System.out.println("inner from outer pi=" +ic.pi);
		
		ic.doSth(pl);
	}
	
	private class InnerClass {
		private Person pi = new Person("pp");
		
		public void doSth(Person pa) {
			p.setName("cc");
			System.out.println("outer from inner s=" + s);
			System.out.println("outer from inner p=" +p);
			//System.out.println("outer from inner ic.pi=" +ic.pi);
			System.out.println("inner from inner pi=" +pi);
			System.out.println("local from inner pi=" +pa);
		}	
	}
	
	public static void main(String[] args) {
		CommonExample o = new CommonExample();
		o.doSth();
		//CommonExample.InnerClass i = o.new InnerClass();
		//i.doSth();
	}
}

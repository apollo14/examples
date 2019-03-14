package interview.innerClass._3local;

import interview.model.Person;

public class LocalExample {
	private String s = "aaa";
	private Person p = new Person("aa");

	public void doSthOuter() {
		Person pl = new Person("local");
		
		//pl = new Person(""); //not efectively final 
		
		class InnerClass {
			private Person pi = new Person("pp");
			
			public void doSthInner() {
				pl.setName("cc");
				System.out.println("outer from inner s=" + s);
				System.out.println("outer from inner p=" +p);
				System.out.println("inner from inner pi=" +pi);
				System.out.println("inner from inner pi=" +pl);
				test();
			}	
			
			public void t1(Person pa) {
				System.out.println("inner from inner pi=" +pa);
			}
		}

		InnerClass ic = new InnerClass();

		p.setName("sss");
		System.out.println("outer from outer s=" + s);
		System.out.println("outer from outer p=" +p);		
		System.out.println("inner from outer pi=" +ic.pi);
		pl.setName("local2");
		
		ic.doSthInner();	
		//ic.t1(pl);
	}

	private void test() {
		System.out.println("test");
	}
	public static void main(String[] args) {
		LocalExample o = new LocalExample();
		o.doSthOuter();
	}

}

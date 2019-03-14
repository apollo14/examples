package interview.innerClass._4anonymous;

import interview.model.Person;

public class AnonymousExample {
	private String s = "aaa";
	private Person p = new Person("aa");

	public void doSthOuter() {
		Person pl = new Person("local");
		
		//pl = new Person(""); // not efecitvely final
		
		IDoWork i = new IDoWork() {
						
			@Override
			public void doWork() {
				pl.setName("qq");
				System.out.println("outer from anonymous s=" + s);
				System.out.println("outer from anonymous p=" + p);
				System.out.println("local from anonymous pl=" + pl);
				test();
			}
		};

		p.setName("sss");
		System.out.println("outer from outer s=" + s);
		System.out.println("outer from outer p=" +p);		
		pl.setName("local2");
		System.out.println("local from outer pl=" +pl);
		i.doWork();
	}

	private void test() {
		System.out.println("test");
	}
	public static void main(String[] args) {
		AnonymousExample o = new AnonymousExample();
		o.doSthOuter();
	}

}

package js.f;

public class Main {

	public static void main(String[] args) {
		ClasspathFileReader f = new ClasspathFileReader("/test.txt");
		System.out.println(f.read());

	}

}

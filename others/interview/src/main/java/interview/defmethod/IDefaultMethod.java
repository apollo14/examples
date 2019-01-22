package interview.defmethod;

public interface IDefaultMethod {

	public default void d1(){
		System.out.println("Default method d1");
	}
	public static void m1(){
		System.out.println("Static method m1");
	}
	
}

package interview.list;

import java.util.*;
/*	
 * Testuje wydajnoœæ ArrayList kontra LinkedList
 * jeœli AL ma ustawion¹ pocz¹tkow¹ d³ugoœæ wystarczaj¹c¹ do pomieszczenia wszystkich elementów.
 */
public class ArrayListTest {
	private static int SIZE = 100000;
	private List<Integer> al = new ArrayList<Integer>(SIZE+1000);
	private List<Integer> ll = new LinkedList<Integer>();
	
	public void fill(List<Integer> list){
		for(int i =0 ; i< SIZE ; i++){
			list.add(i); 
		}
	}
	
	public long getTime(List<Integer> list){
		long start = System.nanoTime();
		
		fill(list);
		return System.nanoTime() - start;
	}
	
	public void test(){
		long alTime = getTime(al);
		long llTime = getTime(ll);
		
		System.out.println("Size: " + SIZE);
		System.out.println("AL: " + alTime);
		System.out.println("LL: " + llTime);
	}
	
	
	
	public static void main(String[] args){
		ArrayListTest t = new ArrayListTest();
		t.test();
		
	}
}

package interview.collection.list.performance;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
/*
 * Testuje wydajnoœæ ArrayList kontra LinkedList
 * testAdd 		- dodawanie na koniec listy
 * testAddOne 	- dodanie jednego elementu w œrodku listy 
 * 
 */
public class ArrayListKontraLinkedList {

	private List<Integer> al = new ArrayList<Integer>();
	private List<Integer> ll = new LinkedList<Integer>();

	private long fill(int size, List<Integer> list) {

		long start = System.nanoTime();
		for (int i = 0; i <= size; i++) {
			list.add(i);
		}
		return System.nanoTime() - start;
	}
	
	private long addOne(int size, List<Integer> list) {
		int index = Math.floorDiv(size, 2);
		long start = System.nanoTime();
		
		list.add(index, 1);
		
		return System.nanoTime() - start;
	}
	
	public static void main(String[] args) {
		ArrayListKontraLinkedList t = new ArrayListKontraLinkedList();
		// add to the eond of array
		//t.testAdd();
		
		// add one in the middle
		t.testAddOne();
	}
	
	private void testAdd() {
		testAdd(1000);
		testAdd(10000);
		testAdd(100000);
		testAdd(1000000);
		testAdd(5000000);
		testAdd(10000000);
		//testAdd(50000000);	
	}
	private void testAdd(int size) {
		long alTime = fill(size, al);
		long llTime = fill(size, ll);
		System.out.println("testAdd: size=" + size);
		System.out.println("ArrayList  - time=" + alTime);
		System.out.println("LinkedList - time=" + llTime);
		
		this.al = new ArrayList<Integer>(); 
		this.ll = new LinkedList<Integer>();
	}
	
	private void testAddOne() {
		testAddOne(1000);
		testAddOne(10000);
		testAddOne(100000);
		testAddOne(1000000);
		testAddOne(10000000);
	}
	private void testAddOne(int size) {
		fill(size, al);
		fill(size, ll);
		
		System.out.println("testAddOne: size=" + size);
		long alTime = addOne(size, al);
		System.out.println("ArrayList  - time=" + alTime);
		long llTime = addOne(size, ll);
		System.out.println("LinkedList - time=" + llTime);

		this.al = new ArrayList<Integer>(); 
		this.ll = new LinkedList<Integer>();

	}
}
package stream.nio;

import java.util.stream.*;

public class StreamTest {

	public static void main(String[] args){
		IntStream.range(1, 10).forEach(System.out::println);
		Stream.of(1, 2).forEach(System.out::println);;
	}
}

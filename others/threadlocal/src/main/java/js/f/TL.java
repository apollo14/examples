package js.f;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TL implements Runnable {
	
	private static final ThreadLocal<String> TLV = new ThreadLocal<String>();
	private String x;
	
	
	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + " - " + TLV.get());
		System.out.println(Thread.currentThread().getName() + " - " + x);
        TLV.set("a - " + Thread.currentThread().getName());
        x = "x - " + Thread.currentThread().getName();

        try {
            Thread.sleep(new Random().nextInt(5000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " - " + TLV.get());
        System.out.println(Thread.currentThread().getName() + " - " + x);
	}
	
	
	public static void main(String[] args) {

		TL tl = new TL();
		for(int i = 0 ; i<4; i++) {
			Thread t = new Thread(tl);
			t.start();
		}
	}

}

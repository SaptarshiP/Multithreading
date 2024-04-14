package com.psja.check;

import java.lang.Thread;
import java.lang.Runnable;

/*
 * 
 * */

public class MyThreadCounting extends Thread {

	private int i = 0;
	
	public void run() {
		while(i<100) {
			i++;
		}
	} 
	
	public static void main( String args[] ) {
		MyThreadCounting m1 = new MyThreadCounting();
		Runnable r1 = ()->{
			m1.run();
		};
		Thread th1 = new Thread(r1);
		Thread th2 = new Thread(r1);
		th1.start();
		th2.start();
		
		try {
			th1.join();
			th2.join();
		}catch(Exception exp) {
			System.out.println(exp.getMessage());
		}
		
		System.out.println(m1.i);
	}
}

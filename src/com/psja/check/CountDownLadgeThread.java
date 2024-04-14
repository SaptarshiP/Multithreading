package com.psja.check;

import java.util.concurrent.CountDownLatch;
import java.lang.InterruptedException;

public class CountDownLadgeThread {

	public void mainMethod() {
		CountDownLatch countDownLatch = new CountDownLatch(3);
		Thread th1 = new Thread(new Execution(countDownLatch));
		Thread th2 = new Thread(new Execution(countDownLatch));
		Thread th3 = new Thread(new Execution(countDownLatch));
		
		th1.start();
		th2.start();
		th3.start();
		try {
			countDownLatch.await();
			System.out.println("All thread has completed");
		}catch(Exception exp) {
			System.out.println(exp.getMessage());
		}
	}
	
}

class Execution extends Thread{
	
	private CountDownLatch countDownLatch;
	
	public Execution( CountDownLatch countDownLatch ) {
		this.countDownLatch = countDownLatch;
	}
	@Override
	public void run() {
		
		try {
			Thread.currentThread().sleep(10000);
			System.out.println("The thread named"+Thread.currentThread().getName());
			this.countDownLatch.countDown();
		}catch( InterruptedException exp ) {
			
		}
	}
}

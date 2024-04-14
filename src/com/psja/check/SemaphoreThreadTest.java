package com.psja.check;

import java.util.concurrent.Semaphore;

import org.junit.Test;

/*
 * Write a Java program to demonstrate Semaphore usage for thread synchronization.
 * */

public class SemaphoreThreadTest {

	@Test
	public void test() {
		Semaphore semaphore = new Semaphore(2);
		SemaphoreThread semaphoreThread = new SemaphoreThread(semaphore);
		Thread th1 = new Thread(semaphoreThread, "Thread1");
		Thread th2 = new Thread(semaphoreThread, "Thread2");
		Thread th3 = new Thread(semaphoreThread, "Thread3");
		Thread th4 = new Thread(semaphoreThread, "Thread4");
		Thread th5 = new Thread(semaphoreThread, "Thread5");
		th1.start();
		th2.start();
		th3.start();
		th4.start();
		th5.start();
		try {
			th1.join();
			th2.join();
			th3.join();
			th4.join();
			th5.join();
		}catch(Exception exp) {
			System.out.println("Exception in joining thread:"+exp.getMessage());
		}
	}
	
}

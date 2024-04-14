package com.psja.check;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.lang.Runnable;

import org.junit.Test;

public class ReentrantLockThreadTest {

	@Test
	public void processTest() {
		ReentrantLock reentrantLock = new ReentrantLock();
		ExecutorService executorService = Executors.newFixedThreadPool(3);
		Runnable r1 = new ReentrantLockThread(reentrantLock, "Job1");
		Runnable r2 = new ReentrantLockThread(reentrantLock, "Job2");
		Runnable r3 = new ReentrantLockThread(reentrantLock, "Job3");
		
		executorService.execute(r1);
		executorService.execute(r2);
		executorService.execute(r3);
		executorService.shutdown();
	}
	
}

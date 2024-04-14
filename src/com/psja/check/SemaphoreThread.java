package com.psja.check;

import java.lang.Runnable;
import java.util.concurrent.Semaphore;

public class SemaphoreThread implements Runnable{

	private Semaphore semaphore;
	
	SemaphoreThread( Semaphore semaphore ){
		this.semaphore = semaphore;
	} 
	/*
	 * Thread will acquire the lock through semaphore.lock and allowed to execute the critical section which is 
	 * Thread.sleep portion and then release the lock the number of thread will be allowed to execute the 
	 * critical section depends on the instance of semphore where we give the number of thread while instantiating(In test class)
	 * the whole working of up and down in semaphore internal working is written in document page
	 * */
	@Override
	public void run() {
		
		try {
			this.semaphore.acquire();
			System.out.println("Acquiring the lock by the thread:"+Thread.currentThread().getName()+" at:"+System.currentTimeMillis());
			Thread.sleep(10000);
			this.semaphore.release();
			System.out.println("Releasing the lock by the thread:"+Thread.currentThread().getName()+" at:"+System.currentTimeMillis());
		}catch(Exception exp) {
			System.out.println(exp.getMessage());
		}
	}
	
}

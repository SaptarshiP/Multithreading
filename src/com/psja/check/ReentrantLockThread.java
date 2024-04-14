package com.psja.check;

import java.util.concurrent.locks.ReentrantLock;
import java.lang.Runnable;

/*Write a Java program that uses the ReentrantLock class to 
 * synchronize access to a shared resource among multiple threads.*/

public class ReentrantLockThread implements Runnable{

	private ReentrantLock reentrantLock;
	private String jobName;
	
	ReentrantLockThread(ReentrantLock reentrantLock, String jobName){
		this.reentrantLock = reentrantLock;
		this.jobName = jobName;
	}
	
	/*
	 *The reentrant lock is updated format of synchronized block here we can see a particular thread after 
	 *acquiring a lock can have one extra lock that is the internal lock portion before releasing the lock
	 *The internal working reentrant lock is written in documentation
	 *
	 **/
	public void run() {
		
		System.out.println("Job waiting for outlock to acquire"+this.jobName);
		Boolean ans = reentrantLock.tryLock();
		int num = 0;
		if (ans) {
			try {
				System.out.println("Job acquired the outlock at: " + System.currentTimeMillis() + ","
						+ "The value of hold count" + reentrantLock.getHoldCount() + ", The Job name"
						+ this.jobName);
				for (int i = 0; i<10000; i++) {
					num++;
				}
				//Thread.sleep(10000);
				System.out.println("I am here");
				reentrantLock.lock();
				try {

					System.out.println("Job acquired the innerlock at:" + System.currentTimeMillis() + ","
							+ "The value of hold count" + reentrantLock.getHoldCount() + ", The Job name"
							+ this.jobName);
					for (int i = 0; i<10000; i++) {
						num++;
					}
					//Thread.sleep(10000);
				} catch (Exception exp) {
					System.out.println("InnerLock exception" + exp.getMessage());
				} finally {
					reentrantLock.unlock();
					System.out.println("Job unlock the inlock at" + System.currentTimeMillis() + ","
							+ "The value of hold count" + reentrantLock.getHoldCount() + ", The Job name"
							+ this.jobName);
				}
			}catch(Exception exp) {
				System.out.println("OuterLock exception"+exp.getMessage());
			}finally {
				reentrantLock.unlock();
				System.out.println("Job unlock the outlock at"+System.currentTimeMillis()+","+
										"The value of hold count"+reentrantLock.getHoldCount()+", The Job name"+this.jobName);
			}
		}else {
			try {
				System.out.println("The Job is waiting:"+this.jobName);
				Thread.sleep(1000);
				
			}catch(InterruptedException exp) {
				System.out.println(exp.getMessage());
			}
		}
	}
	
}	

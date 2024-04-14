package com.psja.check;

import java.lang.Thread;
import java.lang.Runnable;
import java.lang.FunctionalInterface;
import java.util.List;
import java.util.ArrayList;

/*
 * Write a Java program to create a producer-consumer scenario using the wait() 
 * and notify() methods for thread synchronization.  
 */

/*
 * The producer thread puts the data into the queue and consumer reads the data from the queue
 * when the data in the queue reaches into the maximum limit the producer waits and the consumer after completion
 * it's work gives a notify and the waiting producer thread starts working as on the other part if there is 
 * no data into queue the consumer thread goes into waiting state and waits until producer produce new job
 * and notify method calls, so that waiting consumer starts working by retrieving data from queue.
 * */


public class ProducerConsumerThread {

	@FunctionalInterface
	interface FirstRunnable{
		public void run();
	}
	
	public void mainMethod() {
		
		ProducerConsumerOperation pco = new ProducerConsumerOperation();
		
		FirstRunnable rf1 = ()->{
			pco.produce();
		};
		Thread th1 = new Thread(rf1::run);
		Thread th2 = new Thread(()->{
			pco.consume();
		});
		th2.start();
		th1.start();
		try {
			th1.join();
			//th2.join();
		}catch(Exception exp) {
			System.out.println(exp.getMessage());
		}
	}
}
class ProducerConsumerOperation{
	
	private List<Integer> numList = new ArrayList<>();
	private int limit = 3;
	private int num = 0;
	
	public void produce() {
		try {
			synchronized(this) {
				for (int i = 0; i < 10; i++) {
					if (numList.size() == limit) {
						System.out.println("****Producer is waiting****");
						wait();
					}
					System.out.println("****Producer is working****");
					System.out.println(numList);
					++num;
					numList.add(num);
					notify();
				}
			}
		}catch(Exception exp) {
			System.out.println(exp.getMessage());
		}
	}
	
	public void consume() {
		try {
			synchronized(this) {
				for (int i = 1; i < 10; i++) {
					if (numList.size() == 0) {
						System.out.println("****Consumer is waiting****");
						wait();
					}
					System.out.println("****Consumer is working****");
					Integer lastNum = numList.get(numList.size() - 1);
					numList.remove(numList.size() - 1);
					System.out.println(lastNum + ":" + numList);
					notify();
				}
			}
		}catch(Exception exp) {
			System.out.println(exp.getMessage());
		}
	}
	
}

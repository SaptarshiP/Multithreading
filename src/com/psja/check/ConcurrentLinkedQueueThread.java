package com.psja.check;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.Queue;
import java.util.PriorityQueue;
import java.util.concurrent.ThreadPoolExecutor;

public class ConcurrentLinkedQueueThread<T> {

	private Queue<T> concurrentLinkedQueue = new ConcurrentLinkedQueue<T>();
	//private Queue<T> concurrentLinkedQueue = new PriorityQueue<>();
	
	public void mainOperation( T a, T b, T c ) {
		ExecutorService executorService = Executors.newFixedThreadPool(2);
		executorService.execute( new ProducerConcurrentLinkedQueueThreadOperation( concurrentLinkedQueue, a ) );
		executorService.execute( new ProducerConcurrentLinkedQueueThreadOperation( concurrentLinkedQueue, b ) );
		executorService.execute( new ProducerConcurrentLinkedQueueThreadOperation( concurrentLinkedQueue, c ) );
		executorService.execute( new ConsumerConcurrentLinkedQueueThreadOpeartion( concurrentLinkedQueue ) );
		executorService.execute( new ConsumerConcurrentLinkedQueueThreadOpeartion( concurrentLinkedQueue ) );
		executorService.execute( new ConsumerConcurrentLinkedQueueThreadOpeartion( concurrentLinkedQueue ) );
		
		executorService.shutdown();
	}
	
}


class ProducerConcurrentLinkedQueueThreadOperation<T> implements Runnable{
	
	private Queue<T> concurrentLinkedQueue = null;
	private T val;
	ProducerConcurrentLinkedQueueThreadOperation( Queue<T> concurrentLinkedQueue, T val ){
		this.concurrentLinkedQueue = concurrentLinkedQueue;
		this.val = val;
	}
	
	@Override
	public void run() {
		
		this.concurrentLinkedQueue.add(val);
	}
}

class ConsumerConcurrentLinkedQueueThreadOpeartion<T> implements Runnable{
	
	private Queue<T> concurrentLinkedQueue = null;
	
	ConsumerConcurrentLinkedQueueThreadOpeartion( Queue<T> concurrentLinkedQueue ){
		this.concurrentLinkedQueue = concurrentLinkedQueue;
	}
	
	@Override
	public void run() {
		int s = 0;
		for ( int i = 0; i<100000; i++ ) {
			s = s+i;
		}
		System.out.println(this.concurrentLinkedQueue.poll());
	}
}

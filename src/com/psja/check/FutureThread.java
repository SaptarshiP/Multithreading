package com.psja.check;

import java.util.concurrent.Future;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

public class FutureThread {

	private ExecutorService threadPool = Executors.newFixedThreadPool(3);
	
	public void mainMethodFuture( Integer num )throws InterruptedException, ExecutionException {
		Factorial factorial =  new Factorial(num);
		System.out.println("Task is submitting ....................");
		
		Future future = threadPool.submit(factorial);
		System.out.println("Task is submitted .................");
		
		while( !future.isDone() ) {
			System.out.println("Thread is not completed working so it waiting");
			Thread.sleep(1);
		}
		
		System.out.println( "The thread has completed it's operation" );
		System.out.println("The response is:"+future.get());
		System.out.println( "The main thread ends" );
		threadPool.shutdown();
	}
}

class Factorial implements Callable<Integer>{
	
	private Integer num;
	Factorial( Integer num ){
		this.num = num;
	}
	
	@Override
	public Integer call() {
		Integer fact = 1;
		System.out.println("fact:"+fact+" num:"+num);
		for ( int i = 1; i<num; i++ ) {
			fact = fact*i;
		}
		return fact;
	}
	
}

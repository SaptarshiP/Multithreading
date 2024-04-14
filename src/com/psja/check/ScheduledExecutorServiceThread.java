package com.psja.check;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorServiceThread {

	public void mainMethod() {
		ScheduledExecutorService schedulesExecutors = Executors.newScheduledThreadPool(3);
		Task task = new Task();
		Runnable runnable = task.run();
		//schedulesExecutors.scheduleAtFixedRate(new Task(), 10, 10, TimeUnit.SECONDS);
	}
	
}
class Task{
	
//	Task( Integer i ){
//		this.i = i;
//	}

	public void run() {
		System.out.println("Time:"+System.currentTimeMillis()+" Thread:"+
										Thread.currentThread().getName());
	}
	
}

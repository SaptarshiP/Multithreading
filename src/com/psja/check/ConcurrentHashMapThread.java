package com.psja.check;

import java.lang.ThreadGroup;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapThread {

	private ConcurrentHashMap<String, String> concurrentHashMap = new ConcurrentHashMap<String, String>();
	
	public void mainMethod() {
		Operation operation = new Operation( concurrentHashMap );
		
		ThreadGroup readGroup = new ThreadGroup( "READ_GROUP" );
		ThreadGroup writeGroup = new ThreadGroup( "WRITE_GROUP" );
		
		Thread th1 = new Thread( writeGroup, operation, "WRITE_1" );
		Thread th2 = new Thread( writeGroup, operation, "WRITE_2" );
		Thread th3 = new Thread( writeGroup, operation, "WRITE_3" );
		Thread th4 = new Thread( readGroup, operation, "READ_1" );
		Thread th5 = new Thread( readGroup, operation, "READ_2" );
		
		th1.start();
		th2.start();
		th3.start();
		th4.start();
		th5.start();
	}
	
}


class Operation extends Thread {
	
	private ConcurrentHashMap<String, String> concurrentHashMap = null;
	
	Operation( ConcurrentHashMap<String, String> concurrentHashMap ){
		this.concurrentHashMap = concurrentHashMap;
	}
	
	@Override
	public void run() {
		ThreadGroup thGroup = Thread.currentThread().getThreadGroup();
		if ( thGroup.getName().equalsIgnoreCase( "WRITE_GROUP" ) ) {
			this.setOperation(Thread.currentThread().getName());
		} else if ( thGroup.getName().equalsIgnoreCase( "READ_GROUP" ) ) {
			this.getOperation();
		}
	}
	
	private void setOperation( String name ) {
		System.out.println( "Thread name in set opreration:"+Thread.currentThread().getName()+
										" at time:"+System.nanoTime() );
		int s = 0;
		for ( int i = 0; i<100000; i++ ) {
			s=s+i;
		}
		this.concurrentHashMap.put(name, name);
	}
	
	public void getOperation() {
		System.out.println( "Thread name in set opreration:"+Thread.currentThread().getName()+
				" at time:"+System.nanoTime() );
		int s = 0;
		for ( int i = 0; i<100000; i++ ) {
			s=s+i;
		}
		System.out.println(concurrentHashMap.get(Thread.currentThread().getName()));
	}
}
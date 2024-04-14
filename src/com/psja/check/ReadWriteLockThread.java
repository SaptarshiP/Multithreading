package com.psja.check;

import java.lang.ThreadGroup;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/*
 * The implementation of 
 * */

public class ReadWriteLockThread{

	private List<String> dataList = new ArrayList<>();
	public void mainOperation() {
		OperationClass readWriteLockThread1 = new OperationClass(dataList);
		
		ThreadGroup writeThreadGroup = new ThreadGroup( "WRITE" );
		ThreadGroup readThreadGroup = new ThreadGroup( "READ" );
		
		Thread th1 = new Thread( writeThreadGroup, readWriteLockThread1, "WRITE_1" );
		Thread th2 = new Thread( writeThreadGroup, readWriteLockThread1, "WRITE_2");
		Thread th3 = new Thread( writeThreadGroup, readWriteLockThread1, "WRITE_3");
		Thread th4 = new Thread( readThreadGroup, readWriteLockThread1, "READ_1" );
		Thread th5 = new Thread( readThreadGroup, readWriteLockThread1, "READ_2" );
		
		th1.start();
		th2.start();
		th4.start();
		th3.start();
		th5.start();
	}
}
class OperationClass implements Runnable {
	
	private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
	private List<String> dataList = null;
	private static int num = 1;
	
	public OperationClass( List<String> dataList ) {
		this.dataList = dataList;
	}
		
	@Override
	public void run() {
		
		if ( Thread.currentThread().getThreadGroup().getName().equalsIgnoreCase("WRITE") ) {
			this.setOperation(Thread.currentThread().getName());
		} else if ( Thread.currentThread().getThreadGroup().getName().equalsIgnoreCase("READ") ) {
			this.getOperation();
		}
		//System.out.println("Test");
	}
	
	public void setOperation( String data ) {
		Lock writeLock = readWriteLock.writeLock();
		
		try {
			writeLock.lock();
			System.out.println("Completing the write operation at:"+System.nanoTime()+"by:"+
										Thread.currentThread().getName());
			int s = 0;
			for ( int i = 0; i<100000; i++ ) {
				s = s+i;
			}
			dataList.add( data );
			System.out.println(dataList);		
		}finally {
			writeLock.unlock();
		}
	}
	
	public void getOperation() {
		Lock readLock = readWriteLock.readLock();
		String data = null;
		try {
			readLock.lock();
			System.out.println("Completing the read operation at:"+System.nanoTime()+"by:"+
											Thread.currentThread().getName());
			int s = 0;
			for ( int i = 0; i<100000; i++ ) {
				s = s+i;
			}
			System.out.println( dataList.get( num ) ); 
			num++;
		}finally {
			readLock.unlock();
		}
	}
}

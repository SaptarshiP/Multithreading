package com.psja.check;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.BrokenBarrierException;

class addition implements Runnable{
	private int a = 5;
	private int b = 10;
	public static int sum = 0;
	
	@Override
	public void run() {
		sum = a + b;
		try {
			CyclicBarrierThread.testBarrier.await();
		}catch(InterruptedException | BrokenBarrierException exp) {
			System.out.println("Exception in addition:"+exp.getMessage());
		}
	}
}

class Multiplication implements Runnable{
	
	private int a = 5;
	private int b = 10;
	public static int multiply = 1;
	
	@Override
	public void run() {
		multiply = a*b;
		try {
			CyclicBarrierThread.testBarrier.await();
		}catch(InterruptedException | BrokenBarrierException exp) {
			System.out.println("Exception in multiplication:"+exp.getMessage());
		}
	}
}

public class CyclicBarrierThread implements Runnable {

	public static CyclicBarrier testBarrier = new CyclicBarrier(3);
	
	public void mainMethod() {
		Thread th1 = new Thread( new CyclicBarrierThread() );
		th1.start();
	}
	
	@Override
	public void run() {
		
		Thread th1 = new Thread( new addition() );
		Thread th2 = new Thread( new Multiplication() );
		try {
			th1.start();
			th2.start();
			CyclicBarrierThread.testBarrier.await();
		}catch(InterruptedException|BrokenBarrierException exp) {
			System.out.println("Exception in main thread:"+exp.getMessage());
		}
		System.out.println(addition.sum+Multiplication.multiply+"");
		
	}
	
}

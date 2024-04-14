package com.psja.check;

public class MyThreadAddition {

	private int s = 0;
	private int i = 0;
	
	public void add() {
		while(i<=100) {
			//System.out.println(s+":"+i);
			s = s+i;
			i++;
			
		}
	}
	
	public static void main(String args[]) {
		MyThreadAddition m1 = new MyThreadAddition();
		Runnable r1 = ()->{
			m1.add();
		};
		Long prev = System.nanoTime();
		Thread th1 = new Thread(r1);
		Thread th2 = new Thread(r1);
		Thread th3 = new Thread(r1);
		th1.start();
		th2.start();
		th3.start();
		try {
			th1.join();
			th2.join();
			th3.join();
		}catch(Exception exp) {
			System.out.println(exp.getMessage());
		}
		System.out.println(m1.s);
		System.out.println(System.nanoTime()-prev);
		
	}
	
}

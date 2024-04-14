package com.psja.check;

import java.util.concurrent.Exchanger;

public class ExchangerThread {

	private Exchanger<String> exchanger = new Exchanger<>();

	public void mainMethod() {
		
		Runnable run1 = ()->{
			System.out.println("In run 1");
			try {
				char ch = 'A';
				for (int i = 0; i < 3; i++) {
					String str = "";
					for (int j = 0; j < 5; j++) {
						str += ch++;
					}
					this.exchanger.exchange(str);
				}
			}catch( InterruptedException exp ) {
				System.out.println(exp.getMessage());
			}
			
		};
		
		Runnable run2 = ()->{
			System.out.println("In run2");
			try {
				for ( int i = 0; i<3; i++ ) {
					String str = this.exchanger.exchange(new String());
					System.out.println(str);
				}
			} catch(InterruptedException exp) {
				System.out.println(exp.getMessage());
			}
		};
		Thread th1 = new Thread( run1 );
		Thread th2 = new Thread( run2 );
		
		th1.start();
		th2.start();
	}
	
}

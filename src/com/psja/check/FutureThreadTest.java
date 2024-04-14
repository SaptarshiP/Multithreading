package com.psja.check;

import org.junit.Test;

public class FutureThreadTest {

	@Test
	public void futureTestMethod() {
		FutureThread completableFutureThread = new FutureThread();
		try {
			completableFutureThread.mainMethodFuture(100000000);
		}catch(Exception exp) {
			System.out.println(exp.getMessage());
		}
	}
	
}

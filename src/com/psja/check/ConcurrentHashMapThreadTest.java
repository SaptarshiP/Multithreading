package com.psja.check;

import org.junit.Test;

public class ConcurrentHashMapThreadTest {

	@Test
	public void testOperation() {
		ConcurrentHashMapThread concurrentHashMapThread = new ConcurrentHashMapThread();
		concurrentHashMapThread.mainMethod();
	}
	
}

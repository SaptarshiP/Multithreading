package com.psja.check;

import org.junit.Test;

public class ConcurrentLinkedQueueThreadTest {

	@Test
	public void test() {
		ConcurrentLinkedQueueThread<Integer> concurrentLinkedQueueThread = new ConcurrentLinkedQueueThread<>();
		concurrentLinkedQueueThread.mainOperation(1, 2, 3);
	}
	
}

package com.psja.check;

import org.junit.Test;

public class ProducerConsumerThreadTest {

	@Test
	public void testProducerConsumer() {
		ProducerConsumerThread producerConsumerThread = new ProducerConsumerThread();
		producerConsumerThread.mainMethod();
	}
	
}

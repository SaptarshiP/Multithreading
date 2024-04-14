package com.psja.check;

import org.junit.Test;

public class CyclicBarrierThreadTest {

	@Test
	public void testing() {
		CyclicBarrierThread ch1 = new CyclicBarrierThread();
		ch1.mainMethod();
	}
}

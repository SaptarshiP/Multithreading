package com.psja.check;

import org.junit.Test;

public class CompletableFutureThreadTest {

	@Test
	public void testIt() {
		CompletableFutureThread completableFutureThread = new CompletableFutureThread();
		completableFutureThread.checkExceptionMethod();
	}
	@Test
	public void testcompletableFutureAllOf() {
		CompletableFutureThread completableFutureThread = new CompletableFutureThread();
		completableFutureThread.checkAllOf();
	}
}

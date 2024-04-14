package com.psja.check;

import org.junit.Test;

public class ReadWriteLockThreadTest {

	@Test
	public void testIt() {
		ReadWriteLockThread readWriteLockThread = new ReadWriteLockThread();
		readWriteLockThread.mainOperation();
	}
}

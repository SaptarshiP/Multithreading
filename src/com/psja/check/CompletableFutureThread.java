package com.psja.check;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureThread {

	public void checkExceptionMethod() {
		CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(()->{
			Integer data = 10/0;
			return data;
		});
		
		completableFuture.exceptionally(exp->{
			System.out.println("Exception is:"+ exp.getMessage());
			return 0;
		}).thenAccept((data)->{
			System.out.println("The default data is:"+data);
		});
		
		System.out.println("Here in the last");
	}
	
	
	public void checkAllOf() {
		
		CompletableFuture<String> completableFuture1 = CompletableFuture.supplyAsync(()->{
			try {
				Thread.sleep(1);
			}catch(InterruptedException exp) {
				throw new RuntimeException(exp.getMessage());
			}
			return "First completable future";
		}).exceptionally((exp)->{
			System.out.println("Exception in first completable future:"+exp.getMessage());
			return "DEFAULT FIRST COMPLETABLE FUTURE";
		});
		
		completableFuture1.thenAccept((data)->{
			System.out.println(data);
		});
		
		CompletableFuture<String> completableFuture2 = CompletableFuture.supplyAsync(()->{
			try {
				Thread.sleep(2);
			}catch( InterruptedException exp ) {
				throw new RuntimeException(exp.getMessage());
			}
			return "Second Completable Future";
		}).exceptionally((exp)->{
			System.out.println( "Exception in second completable future:"+exp.getMessage() );
			
			return "DEFAULT SECOND COMPLETABLE FUTURE";
		});
		
		completableFuture2.thenAccept((data)->{
			System.out.println(data);
		});
		
		CompletableFuture<String> completableFuture3 = CompletableFuture.supplyAsync(()->{
			try {
				Thread.sleep(3);
			}catch( Exception exp ) {
				throw new RuntimeException(exp.getMessage());
			}
			
			return "Third Completable Future";
		}).exceptionally((exp)->{
			System.out.println(exp.getMessage());
			return "DEFAULT THIRD COMPLETABLE FUTURE";
		});
		
		completableFuture3.thenAccept((data)->{
			System.out.println(data);
		});
		
		CompletableFuture<Void> allCompletableFuture = CompletableFuture.allOf( completableFuture1, completableFuture2, completableFuture3 );
		allCompletableFuture.exceptionally( (exp)->{
			System.out.println(exp.getMessage());
			return null;
		} ).thenRun(()->{
			String data1 = completableFuture1.join();
			String data2 = completableFuture2.join();
			String data3 = completableFuture3.join();
		
			System.out.println("data1:"+data1+"data2:"+data2+"data3:"+data3);
		});
		
	}
}	

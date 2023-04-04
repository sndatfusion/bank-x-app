package com.bankxapp.bankApp.util;

public class Util {

	private static final long LIMIT = 10000000000L;
	private static long last = 0;

	public static long getID() {
	  // 10 digits.
	  long id = System.currentTimeMillis() % LIMIT;
	  if ( id <= last ) {
	    id = (last + 1) % LIMIT;
	  }
	  return last = id;
	}
	
	public static double getReceiverAmountDiscount(Double amount) {
		
		double discount = (amount * 0.5) / 100;
		
		return discount;
		
	}
	
	public static double getSenderChangesAmount(Double amount) {
		double charges = (amount * 0.05) / 100;
		return charges;	
	}
}

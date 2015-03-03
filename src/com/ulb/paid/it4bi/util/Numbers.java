package com.ulb.paid.it4bi.util;

public class Numbers {
	  public static boolean isPowerOf2(long value) {
	    return (value & value - 1) == 0;
	  }
	}

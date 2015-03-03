package com.ulb.padi.it4bi.main;

import java.util.HashSet;
import java.util.Random;

public class TestHLL {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SlidingHLL hll=new SlidingHLL(1024);
//		HyperLogLog hll=new HyperLogLog(1024);
		HashSet<Long> hashSet=new HashSet<Long>();
		Random random=new Random();
		long value;
		for(int i=0;i<1000000;i++){
			value=random.nextInt(100000);
			hashSet.add(value);
			hll.add(value);
		}
		long hllestimate=hll.estimate();
		long realestimate=hashSet.size();
		double error=(hllestimate-realestimate)*(100)/realestimate;
		System.out.println(hllestimate);
		System.out.println(realestimate);
		System.out.println(error);
		 
//		HyperLogLog hll=new HyperLogLog(1024);
//		hll.add(100);
//		hll.add(100);
//		hll.add(200);
//		System.out.println(hll.estimate());
		

		
	}

}

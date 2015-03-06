package com.ulb.paid.it4bi.util;

import com.google.common.base.Charsets;
import com.google.common.base.Preconditions;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import com.ulb.padi.it4bi.main.SlidingHLL;

import java.util.ArrayList;
import java.util.Random;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

public class HyperLogLogUtil {
  private static final HashFunction HASH = Hashing.murmur3_128();

  public static long estimateCardinality(int[] bucketValues) {
    Preconditions.checkArgument(
      Numbers.isPowerOf2(bucketValues.length),
      "number of buckets must be a power of 2"
    );

    int zeroBuckets = 0;
    double sum = 0;
    for (Integer value : bucketValues) {
      sum += 1.0 / (1L << value);
      if (value == 0) {
        ++zeroBuckets;
      }
    }

    double alpha = computeAlpha(bucketValues.length);
    double result = alpha * bucketValues.length * bucketValues.length / sum;

    if (result <= 2.5 * bucketValues.length) {
      // adjust for small cardinalities
      if (zeroBuckets > 0) {
        // baselineCount is the number of buckets with value 0
        result = bucketValues.length * Math.log(bucketValues.length * 1.0 / zeroBuckets);
      }
    }

    return Math.round(result);
  }

  public static int[] generateBuckets(int numberOfBuckets, long cardinality) {
    Preconditions.checkArgument(
      Numbers.isPowerOf2(numberOfBuckets),
      "number of buckets must be a power of 2"
    );

    double[] probabilities = computeProbabilities(numberOfBuckets, cardinality, Byte.MAX_VALUE);

    Random random = new Random();
    int[] result = new int[numberOfBuckets];
    for (int i = 0; i < numberOfBuckets; ++i) {
      double trial = random.nextDouble();

      int k = 0;
      while (trial > probabilities[k]) {
        ++k;
      }

      result[i] = k;
    }

    return result;
  }

  /**
   * Probability that a bucket has a value <= k
   */
  private static double cumulativeProbability(int numberOfBuckets, long cardinality, int k) {
    return Math.pow(1.0 - 1.0 / ((1L << k) * 1.0 * numberOfBuckets), cardinality);
  }

  /**
   * Compute cumulative probabilities for value <= k for all k = 0..maxK
   */
  private static double[] computeProbabilities(int numberOfBuckets, long cardinality, int maxK) {
    double[] result = new double[maxK];

    for (int k = 0; k < maxK; ++k) {
      result[k] = cumulativeProbability(numberOfBuckets, cardinality, k);
    }

    return result;
  }

  public static int[] mergeBuckets(int[] first, int[] second) {
    checkNotNull(first, "first is null");
    checkNotNull(second, "second is null");
    checkArgument(
      first.length == second.length,
      "Array sizes must match, found %s vs %s",
      first.length,
      second.length
    );

    int[] result = new int[first.length];
    for (int i = 0; i < first.length; i++) {
      result[i] = Math.max(first[i], second[i]);
    }

    return result;
  }
  public static  ArrayList<ElementList> mergeBuckets( ArrayList<ElementList> first,  ArrayList<ElementList> second) {
	    checkNotNull(first, "first is null");
	    checkNotNull(second, "second is null");
	    checkArgument(
	      first.size() == second.size(),
	      "Array sizes must match, found %s vs %s",
	      first.size(),
	      second.size()
	    );

	    ArrayList<ElementList> result=new ArrayList<ElementList>();
	    for (int i = 0; i < first.size(); i++) {
	    
	      result.add(null);
	    }

	    return result;
	  }
  public static double computeAlpha(int numberOfBuckets) {
    double alpha;
    switch (numberOfBuckets) {
      case (1 << 4):
        alpha = 0.673;
        break;
      case (1 << 5):
        alpha = 0.697;
        break;
      case (1 << 6):
        alpha = 0.709;
        break;
      default:
        alpha = (0.7213 / (1 + 1.079 / numberOfBuckets));
    }
    return alpha;
  }

  
  public static SlidingHLL mergeHLL(SlidingHLL first,SlidingHLL second){
	  
	  SlidingHLL out=new SlidingHLL(first.getBuckets().size());
	  ArrayList<ElementList> outList=out.getBuckets();
	  
	  for(ElementList e : outList){
		  
	  }
	  
	  return out;
	  
  }
  /**
   * Computes a 64-bit hash suitable for adding to a hyperloglog instance.
   *
   * The hyperloglog implementation uses bits from least significant to most significant first, so
   * If you need to keep shorter hashes around (e.g., for storage), make sure to drop bits from the
   * most significant side, as the hyperloglog implementation uses bits from least significant
   * to most significant.
   */
  public static long computeHash(long value) {
	 
    return HASH.hashLong(value).asLong();
  }
  public static long computeHash(String value) {
	 
    return HASH.hashString(value, Charsets.UTF_8).asLong();
  }
  public static long computeHash(Object value) {
		 if(value instanceof Long){
			 return computeHash((long)value);
		 }
		 if(value instanceof String){
			 return computeHash(String.valueOf(value));
		 }
	    return -1;
	  }
}

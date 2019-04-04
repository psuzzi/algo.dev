package algo.prep.leetcode.p1015;

import java.util.HashSet;
import java.util.Set;

public class NumberWithRepeatedDigits {
	
	public static void main(String[] args) {
		NumberWithRepeatedDigits t = new NumberWithRepeatedDigits();
		
		long time = System.currentTimeMillis();
		int res = t.numDupDigitsAtMostN(100);
		time = System.currentTimeMillis()-time;
		System.out.printf("%s in %s millis %n", res, time);
	}

	/**
	 * Computes the number of numbers with repeated digits. 
	 * 
	 * The numbers with non-repeated digits can be calculated.
	 * 
	 * @param N
	 * @return
	 */
    public int numDupDigitsAtMostN(int N) {
    	
    	// one digit: 0
    	// two digits: 11, 22 ..99 = 9
    	// three digits: 11, 22, .. +
    	
        int count = 0;
        for(int i=1; i<=N; i++){
            if(hasRepeatedDigits(i)){
                count++;
            }
        }
        return count;
    }
    
    static final boolean hasRepeatedDigits(int N){
        Set<Integer> digits = new HashSet<>();
        int n=N;
        while(n>0){
            int digit = n%10;
            n = (int) n/10;
            if(digits.contains(digit))
                return true;
            digits.add(digit);
        }
        return false;
    }

}

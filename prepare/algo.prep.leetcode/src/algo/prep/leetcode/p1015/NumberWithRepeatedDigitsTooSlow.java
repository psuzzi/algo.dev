package algo.prep.leetcode.p1015;

import java.util.HashSet;
import java.util.Set;

public class NumberWithRepeatedDigitsTooSlow {
	
	public static void main(String[] args) {
		NumberWithRepeatedDigitsTooSlow t = new NumberWithRepeatedDigitsTooSlow();
		
		long time = System.currentTimeMillis();
		int res = t.numDupDigitsAtMostN(100);
		time = System.currentTimeMillis()-time;
		System.out.printf("%s in %s millis %n", res, time);
	}

    public int numDupDigitsAtMostN(int N) {
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

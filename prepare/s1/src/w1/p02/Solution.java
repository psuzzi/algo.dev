package w1.p02;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Solution {

    public boolean containsDuplicate(int[] nums) {
        Arrays.sort(nums);
        for( int i=0; i< nums.length-1; i++)
            if( nums[i] == nums[i+1] )
                return true;
        return false;   
    }
    
    public boolean containsDuplicate2(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for ( int i : nums ){
        	if(!set.add(i))
                return true;
        }
        return false;   
    }

}
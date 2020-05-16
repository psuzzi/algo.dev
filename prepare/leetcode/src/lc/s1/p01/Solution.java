package lc.s1.p01;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of integers, find two numbers that add up to the given target
 * sum
 * 
 * @author psuzzi
 * @see https://leetcode.com/problems/two-sum/
 */
class Solution {
    public int[] twoSum(final int[] nums, final int target) {
        final Map<Integer, Integer> map = new HashMap<>();
        for( int i=0; i<nums.length; i++){
            if(map.containsKey(nums[i]))
            return new int[]{map.get(nums[i]), i};
            map.put(target-nums[i], i);
        }
        return new int[0];
    } 
}
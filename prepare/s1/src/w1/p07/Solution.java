package w1.p07;
class Solution {
    
    public int maxSubArray(int[] nums) {
        
        int curMax = Integer.MIN_VALUE;
        int globMax = curMax;
        
        for(int i=0; i<nums.length; i++){
            curMax = Math.max(curMax,0) + nums[i];
            globMax = Math.max(globMax, curMax);
        }
        
        return globMax;
    }
}
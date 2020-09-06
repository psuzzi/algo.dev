package w1.p01;

class Solution {
    public int findLengthOfLCIS(int[] nums) {
        int ans=0;
        for(int l=0, r=0; r<nums.length; r++){
            if(r>0 && nums[r-1] >= nums[r])
                l=r;
            ans = Math.max(ans, r-l+1);
        }
        return ans;
    }
}
package w1.p06;
class Solution {
    public int[] productExceptSelf(int[] nums) {
        int[] ans = new int[nums.length];
        int[] l = new int[nums.length];
        int[] r = new int[nums.length];
        for( int i=0; i<nums.length; i++){
            l[i] = (i==0) ? 1 : l[i-1] * nums[i-1];
        }
        for(int i=nums.length-1; i>=0; i--){
            r[i] = (i==nums.length-1) ? 1 : r[i+1] * nums[i+1];
        }
        
        for(int i=0; i<nums.length; i++){
            ans[i] = l[i]*r[i];
        }
        return ans;
    }
}
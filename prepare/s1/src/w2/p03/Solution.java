package w2.p03;

public class Solution {

    public int maxArea(int[] height) {
        int maxArea = 0;
        for( int i=0; i<height.length-1; i++){
            for(int j=i+1; j<height.length; j++){
                int minHeight = Math.min(height[i], height[j]);
                maxArea = Math.max(minHeight * (j-i), maxArea);
            }
        }
        return maxArea;
    }
    
    public int maxArea2(int[] height) {
        int maxArea = 0;
        int l=0, r=height.length;
        while(l<r) {
        	int minHeight = Math.min(height[r], height[l]);
            maxArea = Math.max(minHeight * (r-l), maxArea);
            if( height[r] < height[l])
            	r--;
            else
            	l++;
        }
        return maxArea;
    }

}
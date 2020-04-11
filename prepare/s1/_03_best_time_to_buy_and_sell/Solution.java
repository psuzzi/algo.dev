package prepare.s1._03_best_time_to_buy_and_sell;

class Solution {
    public int maxProfit(int[] prices) {
        int maxCurr = 0, maxGlobal = 0;
        for(int i=1; i<prices.length; i++){
            // to maximize on contiguous subarray
            int delta = prices[i] - prices[i-1];
            maxCurr = Math.max(0, maxCurr + delta);
            maxGlobal = Math.max( maxCurr, maxGlobal);
        }
        return maxGlobal;
    }
}

class Solution1 {
    public int maxProfit(int[] prices) {
        int max = 0;
        for( int i=0; i<prices.length-1; i++)
            for (int j=i+1; j<prices.length; j++){
                int gain = prices[j] - prices[i];
                if(gain>max)
                    max = gain;
            }
        return max;      
    }
}
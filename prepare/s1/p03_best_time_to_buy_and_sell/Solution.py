class Solution:
    def maxProfit(self, prices: List[int]) -> int:
        max_curr=0
        max_global=0
        for i in range( 1, len(prices) ):
            # to maximize on contiguous subarray
            delta = prices[i] - prices[i-1]
            max_curr = max(0, max_curr + delta )
            max_global = max( max_curr, max_global)
        return max_global

class Solution1:
    def maxProfit(self, prices: List[int]) -> int:
        max = 0
        for i in range(0, len(prices)-1):
            for j in range(i, len(prices)):
                gain = prices[j] - prices[i];
                if gain > max:
                    max = gain
        return max
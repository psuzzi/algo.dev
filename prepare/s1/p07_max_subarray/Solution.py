class Solution:
    def maxSubArray(self, nums: List[int]) -> int:
        cur_max = nums[0]
        glob_max = cur_max
        for i in range(1, len(nums)):
            cur_max = max(cur_max, 0) + nums[i]
            glob_max = max(glob_max, cur_max)
        return glob_max
        
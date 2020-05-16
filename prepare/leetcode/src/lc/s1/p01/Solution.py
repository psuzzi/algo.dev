from typing import List

class Solution:
    def twoSum(self, nums: List[int], target: int) -> List[int]:
        map = {}
        for i in range(0, len(nums)):
            if nums[i] in map:
                return [i, map[nums[i]]]
            map[target-nums[i]] = i
        return []
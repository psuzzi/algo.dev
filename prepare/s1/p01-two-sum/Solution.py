class Solution:
    def twoSum(self, nums: List[int], target: int) -> List[int]:
        

class Solution1:
    def twoSum(self, nums: List[int], target: int) -> List[int]:
        for i in range(0, len(nums)-1):
            for j in range(i+1, len(nums)):
                if nums[i] + nums[j] == target:
                    return [i,j]
        return []
                
class Solution2:
    def twoSum(self, nums: List[int], target: int) -> List[int]:
        map = {}
        for i in range(0, len(nums)):
            map[target-nums[i]] = i
        for i in range(0, len(nums)):
            if nums[i] in map and map[nums[i]] != i:
                return [i,map[nums[i]]]
        return []

class Solution3:
    def twoSum(self, nums: List[int], target: int) -> List[int]:
        map = {}
        for i in range(0, len(nums)):
            if nums[i] in map:
                return [i, map[nums[i]]]
            map[target-nums[i]] = i
        return []
                
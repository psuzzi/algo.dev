from typing import List
import fileinput

def main():
    sol = Solution()
    with open('in.txt') as f:
        n = int(f.readline().strip())
        nums = list(map(int, f.readline().strip().split() ))
        target = int(f.readline().strip())
        res = sol.twoSum(nums, target)
        print("{}".format(res))

class Solution:
    def twoSum(self, nums: List[int], target: int) -> List[int]:
        map = {}
        for i in range(0, len(nums)):
            if nums[i] in map:
                return [i, map[nums[i]]]
            map[target-nums[i]] = i
        return []

if __name__ == "__main__":
    main()

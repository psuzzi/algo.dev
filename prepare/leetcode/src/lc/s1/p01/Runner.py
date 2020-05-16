from typing import List
import fileinput

def main():
    with open('in.txt') as f:
        lines=f.readlines()
        lines = fileinput.input('in.txt')
        n = int(lines[0])
        nums = [list(map(int, lines[1].split())) for i in range(n)]
        target = int(lines[2])
        print('two')
        print ("Solution: {} -> {}".format(nums, target))
        print('two')

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

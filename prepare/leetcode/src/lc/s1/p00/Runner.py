from typing import List
import fileinput

def main():
    sol = Solution()
    with open('in.txt') as f:
        t = int(f.readline().strip())
        for i in range(1,t+1):
            n = int(f.readline().strip())
            arr = [list(map(int, f.readline().strip().split() )) for i in range(n)]
            print ("Case #{}: {}".format(i, sol.solve(arr)))

class Solution:
    def solve(self, arr):
        n = len(arr)
        trace = sum([arr[i][i] for i in range(n)])
        rows = sum([1 if len(set([arr[i][j] for j in range(n)])) != n else 0 for i in range(n)])
        cols = sum([1 if len(set([arr[j][i] for j in range(n)])) != n else 0 for i in range(n)])
        return ' '.join([str(trace), str(rows), str(cols)])

if __name__ == "__main__":
    main()
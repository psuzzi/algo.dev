from typing import List
import fileinput

class Solution:
    def solve(self, arr):
        n = len(arr)
        trace = sum([arr[i][i] for i in range(n)])
        rows = sum([1 if len(set([arr[i][j] for j in range(n)])) != n else 0 for i in range(n)])
        cols = sum([1 if len(set([arr[j][i] for j in range(n)])) != n else 0 for i in range(n)])
        return ' '.join([str(trace), str(rows), str(cols)])

def main():
    n = int(input())
    arr = [list(map(int, input().split())) for i in range(n)]
    trace = sum([arr[i][i] for i in range(n)])
    rows = sum([1 if len(set([arr[i][j] for j in range(n)])) != n else 0 for i in range(n)])
    cols = sum([1 if len(set([arr[j][i] for j in range(n)])) != n else 0 for i in range(n)])
    return ' '.join([str(trace), str(rows), str(cols)])

if __name__ == "__main__":
    t = int(input())
    for i in range(t):
        print ("Case #{}: {}".format(i+1, main()))

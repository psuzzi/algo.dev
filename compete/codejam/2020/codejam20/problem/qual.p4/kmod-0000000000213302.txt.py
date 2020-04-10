
import sys

def p(s):
    print s
    # print >>sys.stderr, ">", s
    sys.stdout.flush()
    r = sys.stdin.readline().strip()
    # print >>sys.stderr, "<", r
    return r

if __name__ == "__main__":
    f = sys.stdin
    T, B = map(int, f.readline().split())
    # print >>sys.stderr, T, B
    for _T in xrange(T):
        # print >>sys.stderr, "test case", _T
        A = [None] * B

        for i in range(5):
            A[i] = int(p(i + 1))
            A[B - i - 1] = int(p(B - i))

        while True:
            if all([a is not None for a in A]):
                r = p(''.join(map(str, A)))
                assert r == "Y"
                break

            switch_same = None
            switch_diff = None

            # print >>sys.stderr, A
            for i in range(B / 2):
                x, y = A[i], A[B - i - 1]
                if x is None:
                    continue

                if x == y and switch_same is None:
                    nx = int(p(i + 1))
                    switch_same = (nx != x)
                elif x != y and switch_diff is None:
                    nx = int(p(i + 1))
                    switch_diff = (nx != x)

            if switch_same is None:
                p(1)
            if switch_diff is None:
                p(1)

            # print >>sys.stderr, switch_same, switch_diff

            for i in range(B / 2):
                x, y = A[i], A[B - i - 1]
                if x is None:
                    continue

                if x == y:
                    if switch_same:
                        A[i] = 1 ^ x
                        A[B - i - 1] = 1 ^ y
                else:
                    if switch_diff:
                        A[i] = 1 ^ x
                        A[B - i - 1] = 1 ^ y
            # print >>sys.stderr, A

            asked = 0
            for i in range(B / 2):
                if A[i] is not None:
                    continue

                A[i] = int(p(i + 1))
                A[B - i - 1] = int(p(B - i))
                asked += 1
                if asked >= 4:
                    break

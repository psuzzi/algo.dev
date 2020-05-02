def union_p(prefixes):
    longest = ''
    for prefix in prefixes:
        if len(prefix) > len(longest):
            longest = prefix
    for prefix in prefixes:
        if longest.find(prefix) != 0:
            return None
    return longest

def union_s(suffixes):
    result = union_p([suffix[::-1] for suffix in suffixes])
    if result is None:
        return None
    return result[::-1]

def main():
    n = int(input())
    patterns = [input().split('*') for i in range(n)]
    prefix = union_p([pattern[0] for pattern in patterns])
    suffix = union_s([pattern[-1] for pattern in patterns])
    if prefix is None or suffix is None:
        return '*'
    answer = prefix
    for pattern in patterns:
        answer = answer + ''.join(pattern[1:-1])
    answer = answer + suffix
    return answer

t = int(input())

for i in range(t):
    print ("Case #{}: {}".format(i+1, main()))

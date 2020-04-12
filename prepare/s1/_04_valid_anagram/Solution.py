class Solution:
    def isAnagram(self, s: str, t: str) -> bool:
        occ = {}
        i=j=k=0
        for i in s:
            if i not in occ:occ[i]=1
            else:
                occ[i]+=1
        for j in t:
            if j not in occ:return False
            else:
                occ[j]-=1
        for k in occ:
            if occ[k]>0:return False
        return True

    def isAnagram1(self, s: str, t: str) -> bool:
        dic1, dic2 = {}, {}
        for c in s:
            dic1[c] = dic1.get(c,0) +1
        for c in t:
            dic2[c] = dic2.get(c,0) +1
        return dic1 == dic2
    
    def isAnagram2(self, s, t):
        dic1, dic2 = [0]*26, [0]*26
        for item in s:
            dic1[ord(item)-ord('a')] += 1
        for item in t:
            dic2[ord(item)-ord('a')] += 1
        return dic1 == dic2
        
    def isAnagram3(self, s, t):
        return sorted(s) == sorted(t)
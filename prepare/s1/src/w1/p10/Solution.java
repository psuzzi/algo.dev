package w1.p10;
import java.util.*;
import java.util.stream.Collectors;

class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for(String s : strs){
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);
            map.computeIfAbsent(key, k -> new ArrayList<>());
            map.get(key).add(s);
        }
        return new ArrayList(map.values());
    }
}

class Solution1 {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        int [] count = new int[26];
        for(String s : strs){
            Arrays.fill(count, 0);
            for( char c : s.toCharArray())
                count[c-'a']++;
            
            StringBuilder sb = new StringBuilder();
            for(int i : count)
                sb.append("#").append(i);
            
            String key = sb.toString();
            map.computeIfAbsent(key, k -> new ArrayList<>());
            map.get(key).add(s);
        }
        return new ArrayList(map.values());
    }
}


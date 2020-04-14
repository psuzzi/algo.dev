package prepare.s1._04_valid_anagram;

class Solution {
    public boolean isAnagram(String s, String t) {
        int[] count = new int[26];
        for( char c : s.toCharArray() )
            count[c-'a']++;
        for( char c : t.toCharArray() )
            count[c-'a']--;
        for( int n : count )
            if( n!= 0)
                return false;
        return true;
    }
}
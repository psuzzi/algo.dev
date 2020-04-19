import java.util.ArrayList;
import java.util.List;

class Solution {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a,b) -> a[0]-b[0] );
        
        List<int[]> ans = new ArrayList<>();
        int[] curInt = null;
        
        for( int i=0; i<intervals.length; i++ ){
            if( i==0 || curInt[1] < intervals[i][0] ) {
                curInt = intervals[i];
                ans.add(curInt);   
            }
            else // overlapping
                curInt[1] = Math.max( curInt[1], intervals[i][1]);
        }
        return ans.toArray(new int[ans.size()][]);
    }
}
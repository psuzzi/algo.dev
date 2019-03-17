package hqtest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Test3 {
	
	static int [] A1 = new int[] {4, 6, 2, 2, 6, 6, 1};
//	static int [] A2 = new int[] {1, 2, 3};
//	static int [] A3 = new int[] {-1, -3};

	public static void main(String[] args) {
		System.out.println(solution(A1));
//		System.out.println(solution(A2));
//		System.out.println(solution(A3));

	}
	
	
    static int solution(int[] A) {
        int N = A.length;
        int result = 0;
        // for each pair A[i], A[j] of equal elements,
        // the result is the max distance between i and j.
        long time = System.currentTimeMillis();
        // map an integer to its min and max index
        Map<Integer, Integer> mapLo = new HashMap<>();
        Map<Integer, Integer> mapHi = new HashMap<>();
        // loop on each element
        for(int i=0; i<N; i++){
            int val = A[i];
            if(!mapLo.containsKey(A[i])){
                mapLo.put(i, val);
                mapHi.put(i, val);
            } else {
                // the value is already mapped, we need to update max
                mapHi.put(i, val);
            }
        }
        
        // now, for each value we mapped lo and hi
        // we iterate over the map entries to find the max
        
        for(Integer key: mapLo.keySet()){
            // hi is always greater than lo --> no need to abs()
            result = Math.max(result, mapHi.get(key) - mapLo.get(key));
        }
        System.out.println(System.currentTimeMillis()-time);
        return result;
    }
	

	public static int solutionTest(int[] A) {
		Arrays.sort(A);//O(n nlog n)
		int max = 0;
		for (int i : A) {// O(n)
			System.out.println(" > " + i);
			if (i > 0) {
				if(i<=max+1) {
					max = i;
				} else {
					break;
				}
			}
		}
		return max+1;
	}

}

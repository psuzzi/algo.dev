package hqtest;

/**
 * The array represents a number of connected items. Find the length of the biggest one. 
 * Example: {5, 4, 0, 3, 1, 6, 2} contains three chain-loops: {5,6,2,0}, {1,4}, {3}. 
 * The size of the longest is 4
 * 
 * @author psuzzi
 *
 */
public class Test1 {
	
	static int[] A1 = new int[] {5, 4, 0, 3, 1, 6, 2};
	
	public static void main(String[] args) {
		Test1 t = new Test1();
		System.out.println(t.solution(A1));
	}
	
    public int solution(int[] A) {
        // iterate over the values, 
        // put them into a set of connected components
        // find the size of the biggest connected component
        
        boolean used[] = new boolean [A.length];
        int conn[] = new int[A.length];
        int maxCount = 0;
        for(int i=0; i<A.length; i++){
            if(!used[i]){
                int count = followCount(i, A, conn, used);
                if(count>maxCount){
                    maxCount = count;
                }
            }
        }
        return maxCount;
    }
    
    int followCount(int i, int[] A, int[] conn, boolean[] used){
        int index = i;
        used[i] = true;
        int count = 1;
        do{
            index = A[index];
            used[index] = true;
            count++;
        }
        while(index != i);
        return count;
    }

}

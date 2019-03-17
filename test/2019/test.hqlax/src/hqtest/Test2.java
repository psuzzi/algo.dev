package hqtest;

/**
 * Compute the maximum number of squares between two numbers
 * @author psuzzi
 *
 */
public class Test2 {
	
	public static void main(String[] args) {
		Test2 t = new Test2();
		System.out.println(t.solution(6000, 7000));
	}
	
	
    public int solution(int A, int B) {
        // write your code in Java SE 8
        
        // order A, B to get min, max
        int hi = Math.max(A,B);
        
        int sq1 = (int) Math.sqrt(hi);
        
        return maxSquares(sq1, 1);
    }
    
    int maxSquares(int hi, int currNSquares){
        int sq1 = (int) Math.sqrt(hi);
        if(sq1<=2){
            return currNSquares;
        }
        if(sq1*sq1==hi){
            // perfect square
            return maxSquares(sq1, currNSquares+1);
        } else {
            // decrease sqrt
            return maxSquares(hi-1, currNSquares);
        }
    }    

}

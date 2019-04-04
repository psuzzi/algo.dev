package algo.prep.p1;

/**
 * Compute the number of BST that is possible to form with n distinct elements.
 * @author psuzzi
 *
 */
public class NumberOfBsts {
	
	public static void main(String[] args) {
		
		int n = 10;
		long time = System.currentTimeMillis();
		int res = countBsts(n);
		time = System.currentTimeMillis() - time;
		System.out.printf("with %s distinct keys we can have %s BSTs [t: %s ms]", n, res, time);
	}
	
	/**
	 * Count the number of BSTs that can be formed with given 'n' elements.
	 * @param n number of distinct Elements
	 * @return
	 */
	static int countBsts( int n) {
		if(n<2)
			return n;
		
		int ns [] = new int[n+1];
		ns[0] = ns[1] = 1;
		
		for(int i=2; i<=n; i++) {
			ns[i] = 0;
			for(int j=0; j<i; j++) {
				ns[i] += ns[j]*ns[i-j-1];
			}
		}
	
		
		return ns[n];
	}

}

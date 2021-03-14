package ks19.r0;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;

/**
 * 
 * Solution to the Mural problem, 
 * 
 * @see: https://codingcompetitions.withgoogle.com/kickstart/round/0000000000051060/0000000000058b89
 * @author psuzzi
 *
 */
public class SolutionP1{

	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(new File("input/input1.txt"));
		long time = System.currentTimeMillis();
		new SolutionP1().solve(in);
		time = System.currentTimeMillis() - time;
		in.close();
		System.out.printf("Solved in %s ms %n", time);
	}
	
	public static void main1(String[] args){
		Scanner in = new Scanner(System.in);
		new SolutionP1().solve(in);
		in.close();
	}

	
	private void solve(Scanner in) {
		int t = in.nextInt();
		for(int i=1; i<=t; i++){
			int n = in.nextInt();
			int p = in.nextInt();
			List<Integer> studs = new ArrayList<>();
			for(int j=0; j<n; j++) {
				studs.add(in.nextInt());
			}
			int h = solve(n,p,studs);
			System.out.printf("Case #%s: %s %n", i, h);
		}
	}




	/** Solve t-th test case: 
	 * n students, p to pick, S[] skills
	 * @return 
	 **/
	private static int solve(int n, int p, List<Integer> studs) {
		
		// sorted by skill, O(n log n)
		Collections.sort(studs);
		int min = Integer.MAX_VALUE;
		// find a list of students with min difference in skills
		System.out.printf("%s %s : %s", n, p, studs);
		// 1 3 9 100
		// 1 2 3 4 5 5
		// 1 7 7 7 7 
		
		// find interval of length p with lower difference between ends
		int lo, hi;
		int minSkillDiff = Integer.MAX_VALUE;
		int loMin = -1;
		for(lo=0; lo<studs.size()-p; lo++) {
			hi = lo+p-1;
			int skillDiff = studs.get(hi) - studs.get(lo);
			if(skillDiff < minSkillDiff ) {
				minSkillDiff = skillDiff;
				loMin = lo;
			}
		}
		
		// compute hours
		int hours = 0;
		for(int i=loMin+1; i<loMin+p; i++) {
			int dSkill = (studs.get(i) - studs.get(loMin));
			int dPos = i-lo;
			hours += dPos * dSkill;
		}
		
		// 
//		for(int i=0; i<=studs.size()-p; i++) {
//			int hours = 0;
//			for(int j=i+1; j-i<p; j++) {
//				int dSkill = (studs.get(j) - studs.get(j-1));
//				int dPos = j-i;
//				hours += dPos * dSkill;
//				
//			}
//			if(hours<min) {
//				min = hours;
//			}
//		}
		return min;
	}
	
	static void scan(String filename, Consumer<Scanner> consumer) {
		try (Scanner sc = new Scanner(new File(filename))) {
			consumer.accept(sc);
		} catch (FileNotFoundException e) {
			System.err.printf("Error scanning %s", filename);
			e.printStackTrace();
		}
	}
	
}

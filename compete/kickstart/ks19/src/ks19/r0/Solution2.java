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
public class Solution2 {

	public static void main(String[] args) {
		scan("input/input1.txt", in -> {
//		Scanner in = new Scanner(System.in);
		int t = in.nextInt();
		for(int i=1; i<=t; i++){
			int n = in.nextInt();
			int p = in.nextInt();
			int S [] = new int[n]; 
			for(int j=0; j<n; j++) {
				S[j] = in.nextInt();
			}
			int h = solve(n,p,S);
			System.out.printf("Case #%s: %s %n", i, h);
		}
		});
	}
	
	public static class Student{
		int id;
		int skill;
		@Override
		public String toString() {
			return String.format("(%s) %s", id, skill);
		}
	}

	/** Solve t-th test case: 
	 * n students, p to pick, S[] skills
	 * @return 
	 **/
	private static int solve(int n, int p, int[] S) {
		List<Student> students = new ArrayList<>();
		for(int i=0; i<S.length; i++) {
			Student s = new Student();
			s.id = i;
			s.skill = S[i];
			students.add(s);
		}
		// sorted by skill
		Collections.sort(students, (s1, s2) -> Integer.compareUnsigned(s1.skill, s2.skill));
		int min = Integer.MAX_VALUE;
		// find a list of students with min difference in skills
		for(int i=0; i<=students.size()-p; i++) {
			int hours = 0;
			for(int j=i+1; j-i<p; j++) {
				int dSkill = (students.get(j).skill - students.get(j-1).skill);
				int dPos = j-i;
				hours += dPos * dSkill;
				
			}
			if(hours<min) {
				min = hours;
			}
		}
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

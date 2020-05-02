package codejam20.comp.r1a1;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;

public class Solution {
	
	public static void main(String[] args) {
		scan(Solution.class, "in.txt", in -> {
			int t = in.nextInt();
			for (int i = 1; i <= t; ++i) {
				String [] ns = new String[in.nextInt()];
				for( int j=0; j<ns.length; j++)
					ns[j] = in.next();
				System.out.println("Case #" + i + ": " + solve(ns) );
			}
		});
	}

	private static String solve(String [] ns) {
		
		StringBuilder middle = new StringBuilder();
		List<String> pref = new ArrayList<>();
		List<String> suff = new ArrayList<>();
		
		for(int i=0; i<ns.length; i++) {
			String s = ns[i];
			int ix1 = s.indexOf('*');
			int ixL = s.lastIndexOf('*');
			pref.add(s.substring(0,ix1));
			suff.add(s.substring(ixL+1));
			if( ix1 < ixL)
				middle.append(s.substring(ix1+1, ixL).replace("*", "") );
		}
		
		pref.sort( (a,b) -> a.length()-b.length() );
		suff.sort( (a,b) -> a.length()-b.length() );
		
		boolean ok = true;
		for(int i=1; i<ns.length; i++) {
			if( !pref.get(i).startsWith(pref.get(i-1))) {
				ok = false;
			}
			if(!suff.get(i).endsWith(suff.get(i-1))) {
				ok = false;
			}
		}
		
		if(ok) {
			return String.format("%s%s%s", pref.get(pref.size()-1), middle, suff.get(suff.size()-1));
		}
		
		return "*";
	}

	public static void scan(Class<?> cl, String path, Consumer<Scanner> consumer) {
		try( Scanner sc = (path==null) ? new Scanner(System.in) : (cl==null) ? new Scanner(new File(path)) : 
			new Scanner(cl.getResourceAsStream(path))){
			consumer.accept(sc);
		} catch (Exception e) {
			System.err.println("ERROR in " + cl + " < " + path);
			e.printStackTrace();
		}
	}

}
 


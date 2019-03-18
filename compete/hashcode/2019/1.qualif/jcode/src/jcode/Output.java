package jcode;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Output class responsible to store data and print to file
 */
public class Output {
	
	Input in;
	
	List<Slide> slides = new ArrayList<>();
	
	public String computeScore() {
		return String.format("%s", score());
	}

	/**
	 * Write data to file
	 * @param filename
	 */
	public void write(String filename) {
		try {
			PrintWriter out = new PrintWriter(new PrintStream(filename));
			
			out.printf("%s%n", slides.size());
			for(Slide s : slides) {
				out.printf("%s%n", s);
			}
			
			out.close();
		} catch (Exception e) {
			System.err.printf("Error writing %s", filename);
		}
	}
	
	long NO_SOL = Long.MIN_VALUE;
	
	long score = NO_SOL;
	
	long score() {
		if(score!=NO_SOL)
			return score;
		if(!isValid() || slides.size()<1) {
			return 0;
		}
		
		long score = 0;
		Slide prev, curr;
		for(int i=1; i<slides.size(); i++) {
			prev = slides.get(i-1);
			curr = slides.get(i);
			
			score += prev.tags.compareNoCache(curr.tags).score;

		}
		return score;
	}
	
	static int score(Set<String> prevTags, Set<String> currTags) {
		int first=0, common=0, second=0;
		for(String tag1: prevTags) {
			if(currTags.contains(tag1)) {
				common ++;
			} else {
				first++;
			}
		}
		second = currTags.size() - common;
		return Math.min(common, Math.min(first, second));
	}
	
	boolean isValid() {
		Set<Integer> photos = new HashSet<Integer>();
		for(Slide s : slides) {
			if(s.isH) {
				if(photos.contains(s.photo1)) {
					System.err.printf("Photo %s already there%n", s.photo1);
					return false;
				}
				photos.add(s.photo1);
				if(s.photo2>0)
					return false;
				if(!in.photos.get(s.photo1).isH)
					return false;
			} else {
				if(photos.contains(s.photo1)) {
					System.err.printf("Photo %s already there%n", s.photo1);
					return false;
				}
				if(photos.contains(s.photo2)) {
					System.err.printf("Photo %s already there%n", s.photo2);
					return false;
				}
				photos.add(s.photo1);
				photos.add(s.photo2);
				if(in.photos.get(s.photo1).isH || in.photos.get(s.photo2).isH)
					return false;
			}
		}
		return true;
	}

}
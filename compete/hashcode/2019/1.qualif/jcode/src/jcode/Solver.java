package jcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jcode.Tags.Compare;

/**
 * Solver class responsible to get the input, and transform it into output
 */
public class Solver {

	Output out;
	Input in;

	List<Photo> vPhotos;
	List<Slide> hSlides;
	List<Slide> vSlides;
	List<Slide> slides;;

	void clear() {
		vPhotos = new ArrayList<>();
		hSlides = new ArrayList<>();
		vSlides = new ArrayList<>();
		slides = new ArrayList<>();
	}

	/** Solve the input and return the output */
	public Output solve(Input in) {
		out = new Output();
		out.in = in;
		this.in = in;
		clear();

		// store h slides
		for (Photo p : in.photos) {
			if (p.isH) {
				hSlides.add(new Slide(p));
			} else {
				vPhotos.add(p);
			}
		}

		if (!vPhotos.isEmpty()) {
			composeVSlides();
		}

		//
		System.out.printf("# %6s ", hSlides.size() + vSlides.size());
		slides.clear();
		slides.addAll(hSlides);
		slides.addAll(vSlides);
		slides.sort(bySize);// sort desc by n.tags

		TimerLog.start();

		switch (in.type) {
		case 'a':
//			sortMidLHS(-1);
			sortLeftLHS(-1);		//sortLeftLHS(   -1)  t:        3 ms ->       2
//			sortLeftDict(-1);
//			sortLeftBForce(-1);

			break;
		case 'b':
			/***/
//			sortMidLHS(400);
//			sortLeftLHS(400);
			sortLeftDict(-1);		//sortLeftDict(   -1) t:    5,594 ms -> 205,338
//			sortLeftBForce(500);
			break;

		case 'c':
//			sortMidLHS(-1);
			sortLeftLHS(-1);		//sortLeftLHS(   -1)  t:      103 ms ->   1,546
//			sortLeftDict(-1);
//			sortLeftBForce(-1);
//			sortLeftTwice(-1);
			break;

		case 'd':
//			sortMidLHS(-1);			//sortMidLHS(   -1)   t:    7,361 ms -> 432,619
//			sortLeftLHS(-1);		//sortLeftLHS(   -1)  t:    9,180 ms -> 432,353
//			sortLeftDict(10);		// too slow
//			sortLeftBForce(-1);
			break;

		case 'e':
			sortMidLHS(5000);		
//			sortMidLHS(400);		//sortMidLHS(  400)   t:    8,168 ms -> 336,780
//			sortLeftLHS(5000);		//222,027 ms -> 396,430
//			sortLeftLHS(40000);
//			sortLeftLHS(20000);		//sortLeftLHS(20000)  t:  772,791 ms -> 411,669
//			sortLeftDict(80);
//			sortLeftBForce(1000);	//25,514 ms -> 358,839
			break;

		default:
			break;
		}

//		improve(slides, 50);
		out.slides = slides;
		TimerLog.end();

		return out;
	}
	
	static final void improve(final List<Slide> slides, final int ncomp) {
		int len = slides.size();
		for(int i=1; i<len-1; i++) {
			int scurr = score(slides, i - 1, i, i + 1);
			// max ncomp per each i
			for(int j=i+2; j<i+2+ncomp && j<len-1; j++) {
				int srepl = score(slides, i - 1, j, i + 1);
				if (srepl > scurr) {
					// score with new item is higher, see if the swap is convenient
					int scurrj = score(slides, j - 1, j, j + 1);
					int sreplji = score(slides, j - 1, i, j + 1);
					//
					int curr = scurr + scurrj;
					int repl = srepl + sreplji;
					if (curr < repl) {
						swapSlides(slides, i, j);
					}
				}
			}
		}
	}
	
	final static int score(List<Slide> slides, int i, int j) {
		return slides.get(i).tags.compareNoCache(slides.get(j).tags).score;
	}

	final static int score(List<Slide> slides, int i, int j, int k) {
		return slides.get(i).tags.compareNoCache(slides.get(j).tags).score
				+ slides.get(j).tags.compareNoCache(slides.get(k).tags).score;
	}

	/**
	 * 
	 * Compose slide sequence using a dictionary to track all the slides having a
	 * given tag.
	 * 
	 * 
	 * @param kLimit: limit to the number of comparisons
	 */
	protected void sortLeftDict(int kLimit) {
		TimerLog.info(this, info(new Object() {
		}, kLimit));
		// map tag -> set of slides containing it
		Map<String, Set<Slide>> mapSet = new HashMap<>();
		// map tag -> list of slides ordered by #tags, desc
		Map<String, List<Slide>> mapList = new HashMap<>();

		for (int i = 0; i < slides.size(); i++) {
			Slide s = slides.get(i);
			for (String tag : s.tags.list) {
				if (!mapSet.containsKey(tag)) {
					mapSet.put(tag, new HashSet<>());
					mapList.put(tag, new ArrayList<>());
				}
				if (mapSet.get(tag).add(s)) {
					mapList.get(tag).add(s);
				}
			}
			s.id = i;
		}

		// foreach slide, compute a base score
		Set<Slide> used = new HashSet<>();
		Set<Slide> bad = new HashSet<>();
		for (int i = 0; i < slides.size() - 1; i++) {
			Slide curr = slides.get(i);
			used.add(curr);
			Slide next = slides.get(i + 1);
			int max = curr.tags.compareNoCache(next.tags).score;
			bad.clear();
			// count comparisons
			int k = 0;
			boolean exit = false;
			// then iterate to get a better one
			Iterator<String> iter = curr.tags.list.iterator();
//			while(iter.hasNext() && canImprove(curr, max) && !exit) {
			while (iter.hasNext() && curr.tags.size() >= 2 * (max + 1) && !exit) {
				String tag = iter.next();
				for (Slide s : mapList.get(tag)) {
//					if(used.contains(s) || bad.contains(s) || !canImprove(s, max)) {
					if (used.contains(s) || bad.contains(s) || s.tags.size() < 2 * (max + 1)) {
						continue;
					}
					int score = curr.tags.compareNoCache(s.tags).score;
					if (score > max) {
						max = score;
						next = s;
					} else {
						bad.add(s);
					}
					if (kLimit > 0 && k++ > kLimit) {
						exit = true;
						break;
					}
				}
			}
			if (next.id != i + 1) {
				swapSlides(slides, i + 1, next.id);
			}
		}
		out.slides = slides;
	}

	static final Comparator<Slide> bySize = (s1, s2) -> -Integer.compare(s1.tags.size(), s2.tags.size());

	static final String info(Object obj, int kLimit) {
		String name = obj.getClass().getEnclosingMethod().getName();
		return String.format(" %s(%5s)", name, kLimit);
	}

	/**
	 * Put slides in sequence, starting from left. Slides to compare are stored in
	 * an linked hash set, orddered by n tags
	 * 
	 * (uses a LinkedHashSet to keep the slides in order by n. tags decreasing )
	 * 
	 * @param kLimit
	 */
	protected void sortLeftLHS(int kLimit) {
		TimerLog.info(this, info(new Object() {
		}, kLimit));
		// order by size decreasing
		LinkedHashSet<Slide> ord = new LinkedHashSet<>(slides);
		// result
		int len = ord.size();
		Slide[] res = new Slide[len];
		// place first slide
		Slide curr = slides.get(0);
		res[0] = curr;
		ord.remove(curr);
		//
		Iterator<Slide> iter;
		// loop until the set is empty, keep i as index
		for (int i = 1; i < len && !ord.isEmpty(); i++) {
			curr = res[i - 1];
			iter = ord.iterator();
			Slide next = iter.next();
			int max = curr.tags.compareNoCache(next.tags).score;
			// limit compares
			int nCompares = 0;
			while (iter.hasNext() && canImprove(curr, max)) {
				Slide candidate = iter.next();
				if (cannotImprove(candidate, max))
					continue;
				int score = curr.tags.compareNoCache(candidate.tags).score;
				if (score >= max) {
					max = score;
					next = candidate;
				}
				if (kLimit > 0 && nCompares++ > kLimit) {
					break;
				}
			}
			res[i] = next;
			ord.remove(next);
		}
		slides = Arrays.asList(res);
	}

	static final int iNext(Slide[] in, int iPrev, int max) {
		return -1;
	}

	/**
	 * Compose slides, and limit the number of comparisons
	 * 
	 * @param kLimit limit of comparison per sorted element
	 * @paran wLimit limit of elements to check per cycle
	 */
	protected void sortMidLHS(int kLimit) {
		TimerLog.info(this, info(new Object() {
		}, kLimit));
		// slides ordered by size decreasing
		LinkedHashSet<Slide> ord = new LinkedHashSet<Slide>(slides);
		// result
		int len = ord.size();
		Slide res[] = new Slide[len];
		// remove first and put in the middle
		Iterator<Slide> iter = ord.iterator();
		Slide prev = iter.next();
		res[index(len, 0)] = prev;
		iter.remove();
		//
		for (int i = 1; !ord.isEmpty(); i++) {
			int iPrev = (i == 1) ? 0 : i - 2;
			prev = res[index(len, iPrev)];
			iter = ord.iterator();
			Slide selected = iter.next();
			int max = prev.tags.compareNoCache(selected.tags).score;
			int nCompares = 0;
			for (; iter.hasNext() && canImprove(prev, max);) {
				Slide next = iter.next();
				if (!canImprove(next, max)) {
					continue;
				}
				int score = prev.tags.compareNoCache(next.tags).score;
				if (score > max) {
					max = score;
					selected = next;
				}
				if (kLimit > 0 && nCompares++ > kLimit) {
					break;
				}
			}
			res[index(len, i)] = selected;
			ord.remove(selected);
		}
		slides = Arrays.asList(res);
	}

	/**
	 * @param curr
	 * @param curr
	 */
	static final int index(int len, int i) {
		return (i % 2 == 0) ? len / 2 + (i + 1) / 2 : len / 2 - (i + 1) / 2;
	}

	/**
	 * Brute force with limit iterations per slide
	 * <p>
	 * With K = 500 gives good results and running times for all cases
	 * 
	 * @param k limit the number of comparisons
	 */
	protected void sortLeftBForce(int kLimit) {
		TimerLog.info(this, info(new Object() {
		}, kLimit));
		Slide curr, next;
		int score, max;
		int iNext;
		Compare cmp;

		for (int i = 0; i < slides.size() - 1; i++) {
			curr = slides.get(i);
			// next slide
			iNext = i + 1;
			next = slides.get(iNext);
			cmp = curr.tags.compareNoCache(next.tags);
			max = cmp.score;
			// score = n ==> at least n common, and 2*n tags in both slide
			// score > max ==> both slide must have >= 2*(max+1) tags
			int ctsize = curr.tags.set.size();
			//
			int nCompares = 0;
			for (int j = i + 2; j < slides.size() && ctsize >= 2 * (max + 1); j++) {
				next = slides.get(j);
				if (next.tags.set.size() >= 2 * (max + 1)) {
					cmp = curr.tags.compareNoCache(next.tags);
					score = cmp.score;
					if (score > max) {
						max = score;
						iNext = j;
					}
					if (kLimit > 0 && ++nCompares > kLimit) {
						break;
					}
				}
			}
			if (iNext > i + 1) {
				swapSlides(slides, i + 1, iNext);
			}
		}
		slides = slides;
	}

	/** true if the given slide can improve the given max */
	private static final boolean canImprove(final Slide slide, final int max) {
		return slide.tags.size() >= 2 * (max + 1);
	}

	/** true if the given slide can improve the given max */
	private static final boolean cannotImprove(final Slide slide, final int max) {
		return slide.tags.size() < 2 * (max + 1);
	}

	/** true if the slide can match the given max */
	private static final boolean canMatch(final Slide slide, final int max) {
		return slide.tags.size() >= 2 * (max);
	}

	/** true if the slide can not match the given max */
	private static final boolean cannotMatch(final Slide slide, final int max) {
		return slide.tags.size() < 2 * (max);
	}

	protected void composeVSlides() {
		boolean used[] = new boolean[vPhotos.size()];
		Compare cmp;
		int minCommon = 0;

		for (int i = 0; i < vPhotos.size() - 1; i++) {
			if (used[i]) {
				continue;
			}
			minCommon = Integer.MAX_VALUE;
			int jSelected = -1;
			for (int j = 0; j < vPhotos.size(); j++) {
				if (used[j]) {
					continue;
				}
				cmp = vPhotos.get(i).tags.compareNoCache(vPhotos.get(j).tags);
				if (cmp.common < minCommon) {
					minCommon = cmp.common;
					jSelected = j;
				}
				if (minCommon == 0) {
					break;
				}
			}
			if (jSelected != -1) {
				Slide slide = new Slide(vPhotos.get(i), vPhotos.get(jSelected));
				vSlides.add(slide);
				used[i] = true;
				used[jSelected] = true;
			}
		}
	}

	static void swapSlides(List<Slide> list, int i, int j) {
		Collections.swap(list, i, j);
		list.get(i).id = i;
		list.get(j).id = j;
	}

}
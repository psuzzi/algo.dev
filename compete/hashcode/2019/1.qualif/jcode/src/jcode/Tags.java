package jcode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Tags {

	static Cache cache = new Cache();

	/** Result of comparison of two tags */
	public static class Compare {
		int common = 0;
		int first = 0;
		int second = 0;
		int score = 0;
	}

	Set<String> set = new HashSet<>();
	List<String> list = new ArrayList<>();

	/** number of tags */
	int size() {
		return list.size();
	}

	/** Merge the given tags into this one */
	void merge(Tags tags) {
		addAll(tags.list);
	}

	/** Add the given collection of tags */
	void addAll(Collection<String> tags) {
		tags.forEach(tag -> {
			if (!set.contains(tag)) {
				set.add(tag);
				list.add(tag);
			}
		});
		Collections.sort(list);
	}

	/**
	 * Compare with another Tags. Runtime is O(list.size)
	 * 
	 * @param t2
	 * @return
	 */
	public Compare compareNoCache(Tags t2) {
		Compare cmp = new Compare();
		int size = size(), size2 = t2.size();
		if(size<size2) {
			for (String tag : list) {
				if (t2.set.contains(tag)) {
					cmp.common++;
				}
			}			
		} else {
			for(String tag : t2.list) {
				if(set.contains(tag)) {
					cmp.common++;
				}
			}
		}
		cmp.first = size - cmp.common;
		cmp.second = size2 - cmp.common;
		cmp.score = Math.min(cmp.common, Math.min(cmp.first, cmp.second));
		return cmp;
	}

}

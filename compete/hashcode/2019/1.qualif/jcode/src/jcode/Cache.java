package jcode;

import java.util.HashMap;
import java.util.Map;

import jcode.Tags.Compare;

public class Cache {
	
	Map<String, Compare> tagData = new HashMap<>();

	public void clear() {
		tagData.clear();
	}

	public static String tagKey(Tags t1, Tags t2) {
		int hash1 = t1.hashCode();
		int hash2 = t2.hashCode();
		return (hash1 < hash2) ? hash1 + "_" + hash2 : hash2 + "_" + hash1;
	}

	public boolean contains(Tags t1, Tags t2) {
		return tagData.containsKey(tagKey(t1, t2));
	}

	public Compare put(Tags t1, Tags t2, Compare cmp) {
		return tagData.put(tagKey(t1, t2), cmp);
	}

	public Compare cached(Tags t1, Tags t2) {
		return tagData.get(tagKey(t1, t2));
	}
}

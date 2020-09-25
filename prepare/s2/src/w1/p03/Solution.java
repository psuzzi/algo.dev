package w1.p03;

class Solution {

	public int removeDuplicates(int[] nums) {
		int dup = 0;// 0
		for (int i = 0; i < nums.length; i++) {

			if (i > 0 && nums[i] == nums[i - dup - 1]) {
				// is a duplicate
				dup++;// 1
			}

			if (dup > 0)
				nums[i - dup] = nums[i];

		}
		return nums.length - dup;

	}
}
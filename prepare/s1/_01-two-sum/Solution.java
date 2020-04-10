import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

class Solution {
    public static void main(final String[] args) {

    }
}

class Solution1 {
    public int[] twoSum(final int[] nums, final int target) {
        for (int i = 0; i < nums.length - 1; i++)
            for (int j = i + 1; j < nums.length; j++)
                if (nums[i] + nums[j] == target)
                    return new int[] { i, j };
        return new int[0];
    }
}

class Solution2 {
    public int[] twoSum(final int[] nums, final int target) {
        final Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(target - nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i]) && map.get(nums[i]) != i)
                return new int[] { map.get(nums[i]), i };
        }
        return new int[0];
    }
}

class Solution3 {
    public int[] twoSum(final int[] nums, final int target) {
        final Map<Integer, Integer> map = new HashMap<>();
        for( int i=0; i<nums.length; i++){
            if(map.containsKey(nums[i]))
            return new int[]{map.get(nums[i]), i};
            map.put(target-nums[i], i);
        }
        return new int[0];
    } 
}
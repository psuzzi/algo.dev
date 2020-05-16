package lc.s1.p01;

import static util.IO.*;

public class Runner {
    public static void main(String[] args) {
        scan(Runner.class, "in1.txt", in -> {
            int n = in.nextInt();
            int[] nums = new int[n];
            for( int i=0; i<n; i++)
                nums[i] = in.nextInt();
            int target = in.nextInt();
            new Solution().twoSum(nums, target);
            System.out.println("Hello, World");
        });
    }

}
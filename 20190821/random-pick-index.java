// 398. Random Pick Index
// DescriptionHintsSubmissionsDiscussSolution
//
// Given an array of integers with possible duplicates, randomly output the index of a given target number. You can assume that the given target number must exist in the array.
//
// Note:
// The array size can be very large. Solution that uses too much extra space will not pass the judge.
//
// Example:
//
// int[] nums = new int[] {1,2,3,3,3};
// Solution solution = new Solution(nums);
//
// // pick(3) should return either index 2, 3, or 4 randomly. Each index should have equal probability of returning.
// solution.pick(3);
//
// // pick(1) should return 0. Since in the array only nums[0] is equal to 1.
// solution.pick(1);
//
// Seen this question in a real interview before?
//
//     Difficulty:Medium


class Solution {

    int[] arr;
    Random rand;

    // 要返回原数组当中的 index， 不能去 sort
    // reservior sampling

    public Solution(int[] nums) {
        arr = Arrays.copyOf(nums, nums.length);
        rand = new Random();
    }

    public int pick(int target) {
        int result = -1;
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                count++;
                int index = rand.nextInt(count); // [0, count)
                // 每个数取到的概率相等： 1 / SUM{# target}
                // 当前这个 i 是新的一个数， 取它的概率 1 / count
                // 之后还用这个 i 的概率是: 1 / count * (1 - 1 / (count + 1)) * (1 - 1 / (count + 2)) *... * (1 - 1 / (n)) => 1 / n
                // 所以每个数都有相同的概率 1 / n 被取到
                if (index == 0) {
                    result = i;
                }
            }
        }

        return result;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int param_1 = obj.pick(target);
 */

// 384. Shuffle an Array
// DescriptionHintsSubmissionsDiscussSolution
// Shuffle a set of numbers without duplicates.
//
// Example:
//
// // Init an array with set 1, 2, and 3.
// int[] nums = {1,2,3};
// Solution solution = new Solution(nums);
//
// // Shuffle the array [1,2,3] and return its result. Any permutation of [1,2,3] must equally likely to be returned.
// solution.shuffle();
//
// // Resets the array back to its original configuration [1,2,3].
// solution.reset();
//
// // Returns the random shuffling of array [1,2,3].
// solution.shuffle();
// Seen this question in a real interview before?
// Difficulty:Medium


class Solution {

    public int[] origin;
    public Random random;

    public Solution(int[] nums) {
        origin = nums;
        random = new Random();
    }

    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        return origin;
    }

    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        int n = origin.length;
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = origin[i];
        }
        // similar to reservior sampling, which means we need to shuffle n times for whole array with same prob
        for (int i = 1; i < n; i++) {
            int index = random.nextInt(i + 1);
            swap(nums, i, index);
        }
        return nums;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int[] param_1 = obj.reset();
 * int[] param_2 = obj.shuffle();
 */

// 453. Minimum Moves to Equal Array Elements
// DescriptionHintsSubmissionsDiscussSolution
// Given a non-empty integer array of size n, find the minimum number of moves required to make all array elements equal, where a move is incrementing n - 1 elements by 1.
//
// Example:
//
// Input:
// [1,2,3]
//
// Output:
// 3
//
// Explanation:
// Only three moves are needed (remember each move increments two elements):
//
// [1,2,3]  =>  [2,3,3]  =>  [3,4,3]  =>  [4,4,4]
// Seen this question in a real interview before?
// Difficulty:Easy


class Solution {
    public int minMoves(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

//         method 1: by math
        return math_sol(nums);

//         method 2: add n - 1 elements by 1 => decrease by 1 to min value
        // int min = Integer.MAX_VALUE;
        // for (int num : nums) {
        //     min = Math.min(num, min);
        // }
        // int result = 0;
        // for (int num : nums) {
        //     result += num - min;
        // }
        // return result;
    }
    private int math_sol(int[] nums) {
        int min = Integer.MAX_VALUE;
        int sum = 0;
        for (int num : nums) {
            min = Math.min(min, num);
            sum += num;
        }
        return sum - min * nums.length;
    }
}

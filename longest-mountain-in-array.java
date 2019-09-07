// 845. Longest Mountain in Array
// DescriptionHintsSubmissionsDiscussSolution
// Let's call any (contiguous) subarray B (of A) a mountain if the following properties hold:
//
// B.length >= 3
// There exists some 0 < i < B.length - 1 such that B[0] < B[1] < ... B[i-1] < B[i] > B[i+1] > ... > B[B.length - 1]
// (Note that B could be any subarray of A, including the entire array A.)
//
// Given an array A of integers, return the length of the longest mountain.
//
// Return 0 if there is no mountain.
//
// Example 1:
//
// Input: [2,1,4,7,3,2,5]
// Output: 5
// Explanation: The largest mountain is [1,4,7,3,2] which has length 5.
// Example 2:
//
// Input: [2,2,2]
// Output: 0
// Explanation: There is no mountain.
// Note:
//
// 0 <= A.length <= 10000
// 0 <= A[i] <= 10000
// Follow up:
//
// Can you solve it using only one pass?
// Can you solve it in O(1) space?
// Seen this question in a real interview before?
// Difficulty:Medium


class Solution {
    public int longestMountain(int[] A) {
        if (A == null || A.length < 3) {
            return 0;
        }

        return mytry(A);
    }

    private int mytry(int[] nums) {
        int n = nums.length;
        int result = 0;
        for (int i = 0; i < n - 2; i++) {
            int j = i + 1;
            while (j < n && nums[j] > nums[j - 1]) {
                j++;
            }
            if (j - i < 2) {
                continue;
            }
            int k = j;
            while (k < n && nums[k] < nums[k - 1]) {
                k++;
            }
            if (k - j < 1) {
                continue;
            }
            result = Math.max(result, k - i);
            i = k - 2;
        }
        return result;
    }
}

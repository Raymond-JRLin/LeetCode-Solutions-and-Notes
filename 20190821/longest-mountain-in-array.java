// 845. Longest Mountain in Array
// DescriptionHintsSubmissionsDiscussSolution
//
// Let's call any (contiguous) subarray B (of A) a mountain if the following properties hold:
//
//     B.length >= 3
//     There exists some 0 < i < B.length - 1 such that B[0] < B[1] < ... B[i-1] < B[i] > B[i+1] > ... > B[B.length - 1]
//
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
//
// Example 2:
//
// Input: [2,2,2]
// Output: 0
// Explanation: There is no mountain.
//
// Note:
//
//     0 <= A.length <= 10000
//     0 <= A[i] <= 10000
//
// Follow up:
//
//     Can you solve it using only one pass?
//     Can you solve it in O(1) space?
//
// Seen this question in a real interview before?
//
//     Difficulty:Medium


class Solution {
    public int longestMountain(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }

        // return mytry(A);

        // return method1(A);

        return method2(A);
    }

    private int method2(int[] nums) {
        int n = nums.length;
        // 从前往后和从后往前分别走一次
        // f[i] 表示的是从左到右/从右到左到达 i - 1/i + 1 的时候最长的递增序列的长度
        // 所以最后求的时候要 + 1， 即加上 i 这个位置
        int[] forward = new int[n]; // 从左往右， 记录 i 左边最长的 uphill
        int[] backward = new int[n]; // 从右往左， 记录 i 右边最长的 downhill
        for (int i = 1; i < n; i++) {
            if (nums[i] > nums[i - 1]) {
                forward[i] = forward[i - 1] + 1;
            }
        }
        for (int i = n - 2; i >= 0; i--) {
            if (nums[i] > nums[i + 1]) {
                backward[i] = backward[i + 1] + 1;
            }
        }
        int result = 0;
        for (int i = 0; i < n; i++) {
            if (forward[i] > 0 && backward[i] > 0) {
                result = Math.max(result, forward[i] + backward[i] + 1);
            }
        }
        return result;
    }

    private int method1(int[] nums) {
        // 2 pointers
        int n = nums.length;
        int i = 0;
        int result = 0;
        while (i < n) {
            int right = i;
            // check a uphill, to find a peak
            if (right + 1 < n && nums[right + 1] > nums[right]) {
                while (right + 1 < n && nums[right + 1] > nums[right]) {
                    right++;
                }
                // right is peak, check if right side is a downhill
                if (right + 1 < n && nums[right + 1] < nums[right]) {
                    while (right + 1 < n && nums[right + 1] < nums[right]) {
                        right++;
                    }
                    // update result
                    result = Math.max(result, right + 1 - i);
                }
            }
            i = Math.max(i + 1, right);
        }
        return result;
    }

    /*
    private int mytry(int[] nums) {
        int n = nums.length;
        int result = 0;
        for (int i = 1; i < n - 1; i++) {
            int left = i - 1;
            int right = i + 1;
            while (left >= 0 && right < n) {
                if (nums[left] >= nums[left + 1] || nums[right] >= nums[right - 1]) {
                    break;
                }
                if (right + 1 - left >= 3) {
                    result = Math.max(result, right + 1 - left);
                }
                right++;
                left--;
            }
        }
        return result;
    }
    */
}

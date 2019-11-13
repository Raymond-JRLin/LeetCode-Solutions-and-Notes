// 1027. Longest Arithmetic Sequence
// DescriptionHintsSubmissionsDiscussSolution
//
// Given an array A of integers, return the length of the longest arithmetic subsequence in A.
//
// Recall that a subsequence of A is a list A[i_1], A[i_2], ..., A[i_k] with 0 <= i_1 < i_2 < ... < i_k <= A.length - 1, and that a sequence B is arithmetic if B[i+1] - B[i] are all the same value (for 0 <= i < B.length - 1).
//
//
//
// Example 1:
//
// Input: [3,6,9,12]
// Output: 4
// Explanation:
// The whole array is an arithmetic sequence with steps of length = 3.
//
// Example 2:
//
// Input: [9,4,7,2,10]
// Output: 3
// Explanation:
// The longest arithmetic subsequence is [4,7,10].
//
// Example 3:
//
// Input: [20,1,15,3,10,5,8]
// Output: 4
// Explanation:
// The longest arithmetic subsequence is [20,15,10,5].
//
//
//
// Note:
//
//     2 <= A.length <= 2000
//     0 <= A[i] <= 10000
//
// Seen this question in a real interview before?
//
//     Difficulty:Medium


class Solution {
    public int longestArithSeqLength(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }

        // return mytry(A);

        return method2(A);
    }

    private int method2(int[] nums) {
        // DP
        // 因为在每个 index 上， 有很多种不同的和前面数字的组合， 就意味每个 index 都有很多不同的 diff， 就是 mytry 当中的第二层 for loop
        // 所以在每个 index 的 DP array 上都要记录， 每个不同的 diff 以及其对应的 longest length， 考虑用 map
        int n = nums.length;
        // definition: f[i] := map on different length of longest arithemtic sequence with different diff ending at i
        Map<Integer,Integer>[] f = new HashMap[n]; // map: <diff, len of longest>
        // init
        for (int i = 0; i < n; i++) {
            f[i] = new HashMap<>();
        }
        // DP
        int result = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                int diff = nums[i] - nums[j];
                // 如果前面 j 也出现了 diff， 那么就可以连起来
                f[i].put(diff, f[j].getOrDefault(diff, 1) + 1); // 注意 len 实际上是个数， 默认是 1， 即它自身
                result = Math.max(result, f[i].get(diff));
            }
        }

        return result;
    }

    private int mytry(int[] nums) {
        // brute force
        int n = nums.length;
        int result = 0;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                int diff = nums[j] - nums[i];
                int prev = nums[i];
                int len = 1;
                for (int k = j; k < n; k++) {
                    if (nums[k] - prev == diff) {
                        len++;
                        prev = nums[k];
                    }
                }
                result = Math.max(result, len);
            }

        }
        return result;
    }
}

// 801. Minimum Swaps To Make Sequences Increasing
// DescriptionHintsSubmissionsDiscussSolution
//
// We have two integer sequences A and B of the same non-zero length.
//
// We are allowed to swap elements A[i] and B[i].  Note that both elements are in the same index position in their respective sequences.
//
// At the end of some number of swaps, A and B are both strictly increasing.  (A sequence is strictly increasing if and only if A[0] < A[1] < A[2] < ... < A[A.length - 1].)
//
// Given A and B, return the minimum number of swaps to make both sequences strictly increasing.  It is guaranteed that the given input always makes it possible.
//
// Example:
// Input: A = [1,3,5,4], B = [1,2,3,7]
// Output: 1
// Explanation:
// Swap A[3] and B[3].  Then the sequences are:
// A = [1, 3, 5, 7] and B = [1, 2, 3, 4]
// which are both strictly increasing.
//
// Note:
//
//     A, B are arrays with the same length, and that length will be in the range [1, 1000].
//     A[i], B[i] are integer values in the range [0, 2000].
//
// Seen this question in a real interview before?
//
//     Difficulty:Medium


class Solution {
    public int minSwap(int[] A, int[] B) {

        // return method1(A, B);

        return method2(A, B);
    }

    private int method2(int[] nums1, int[] nums2) {
        // 这个 logic 上可能有点儿绕， 在不同条件下当前是否 swap 和前面一位是否有关系
        // O(N) time and space
        int n = nums1.length;
        // definition
        int[] keep = new int[n];
        int[] swap = new int[n];
        // init
        Arrays.fill(keep, Integer.MAX_VALUE);
        Arrays.fill(swap, Integer.MAX_VALUE);
        keep[0] = 0;
        swap[0] = 1;
        // DP
        // 只需要比较 4 个数， 有 4 种情况
        for (int i = 1; i < n; i++) {
            if (nums1[i - 1] < nums1[i] && nums2[i - 1] < nums2[i]) {
                // 已经符合条件了
                // 不需要交换， 即为前一位不交换的结果
                keep[i] = keep[i - 1];
                // 如果尝试着交换， 那么前一位也必须跟着交换来保证一定是单调递增的
                swap[i] = swap[i - 1] + 1;
            }
            if (nums2[i - 1] < nums1[i] && nums1[i - 1] < nums2[i]) {
                // 可以交换
                // 如果要保持不变， 那么前一位需要交换， 这样可以保证在当前位 i 不交换的条件下是单调递增的
                keep[i] = Math.min(keep[i], swap[i - 1]);
                // 当前位 i 交换， 那么前一位 i - 1 就保持不变即可满足条件
                swap[i] = Math.min(swap[i], keep[i - 1] + 1);
            }
        }
        // result
        return Math.min(keep[n - 1], swap[n - 1]);
    }

    private int method1(int[] nums1, int[] nums2) {
        int n = nums1.length;
        // 没有 memo 会 TLE: O(2 ^ N) time
        // 在做的时候我们考虑一个 index 可以换或是不换， 那么就可以设一个 2D array， 第二层表示换或是不换
        int[][] memo = new int[n][2];
        for (int[] m : memo) {
            Arrays.fill(m, -1);
        }
        return Math.min(dfs(nums1, nums2, 0, -1, -1, memo, 0), dfs(nums1, nums2, 0, -1, -1, memo, 1));
    }
    private int dfs(int[] nums1, int[] nums2, int index, int prev1, int prev2, int[][] memo, int swap) {
        if (index == nums1.length) {
            return 0;
        }
        if (memo[index][swap] != -1) {
            return memo[index][swap];
        }
        int result = Integer.MAX_VALUE;
        // swap 表示的是当前的状态: 1 是换了， 0 表示不换
        if (nums1[index] <= prev1 || nums2[index] <= prev2) {
            // 如果有一边不满足严格单调递增的要求， 那么就必须要 swap
            result = Math.min(result, dfs(nums1, nums2, index + 1, nums2[index], nums1[index], memo, 1) + 1);
        } else {
            // 都满足的情况下， 可以不换
            result = Math.min(result, dfs(nums1, nums2, index + 1, nums1[index], nums2[index], memo, 0));
            // 也可以换， 不过要在换过来后是满足要求的条件下
            if (nums2[index] > prev1 && nums1[index] > prev2) {
                result = Math.min(result, dfs(nums1, nums2, index + 1, nums2[index], nums1[index], memo, 1) + 1);
            }
        }
        return memo[index][swap] = result;
    }
}

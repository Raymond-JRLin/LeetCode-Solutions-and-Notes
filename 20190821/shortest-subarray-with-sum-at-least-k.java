// 862. Shortest Subarray with Sum at Least K
// DescriptionHintsSubmissionsDiscussSolution
//
// Return the length of the shortest, non-empty, contiguous subarray of A with sum at least K.
//
// If there is no non-empty subarray with sum at least K, return -1.
//
//
//
// Example 1:
//
// Input: A = [1], K = 1
// Output: 1
//
// Example 2:
//
// Input: A = [1,2], K = 4
// Output: -1
//
// Example 3:
//
// Input: A = [2,-1,2], K = 3
// Output: 3
//
//
//
// Note:
//
//     1 <= A.length <= 50000
//     -10 ^ 5 <= A[i] <= 10 ^ 5
//     1 <= K <= 10 ^ 9
//
// Seen this question in a real interview before?
//
//     Difficulty:Hard


class Solution {
    public int shortestSubarray(int[] A, int K) {
        if (A == null || A.length == 0) {
            return -1;
        }

        // return mytry(A, K);

        return method1(A, K);
    }

    // sliding window 没办法解的原因就在于有负数， left 走到负数的时候， sum 变小了， 就没办法跨过这个负数继续查看更短的 subarray
    // 所以用 deque 来改良， 因为 deque 可以同时操作头和尾
    // 这里 dq 中装的是前面 prefix sum 的 index， 这些 index 是潜在的可以构成满足条件的 subarray 的 start index
    // 这里第二个 while 是为了保证这个 dq 是单调递增的
    // 原因是这样的： 如果现在走到了 i， dq 最后一个 index 是 j， 假设后面有个 k 对 j 满足条件， 即 prefix[k] - prefix[j] >= k, 那么因为 prefix[i] <= prefix[j], 所以 prefix[k] - prefix[i] 也一定是 >= k 的， 那么 j 一定不可能是答案， 因为 i 比 j 大， 从 i 到 k 一定是比 j 到 k 更短的 subarray
    // 也可以这么想， 如果这个数在 nums 中是负的， 如果包括进这个负数去得到了一个满足条件的 subarray， 那么拿掉这个负数， 剩下后面的 subarray 也一定更满足条件， 因为前面这个负数使得 sum 更小了

    private int method1(int[] nums, int k) {
        int n = nums.length;
        // 先计算 prefix sum， sliding window 里可以用双指针和 sum 来直接得到， 这里没办法， 所以要先预处理一下
        int[] prefix = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }
        int result = n + 1;
        Deque<Integer> dq = new ArrayDeque<>(); // <满足条件的潜在 start index>
        for (int i = 0; i < n + 1; i++) {
            while (!dq.isEmpty() && prefix[i] - prefix[dq.peekFirst()] >= k) {
                // 找到一个满足条件的 subarray
                int start = dq.pollFirst();
                result = Math.min(result, i - start); // 这里不加 1， 因为 prefix sum 的 index 已经加过了
            }
            while (!dq.isEmpty() && prefix[i] <= prefix[dq.peekLast()]) {
                // 维持单调递增的 deque
                dq.pollLast();
            }
            dq.offer(i); // 放到 tail 进去
        }
        return result == n + 1 ? -1 : result;
    }

    private int mytry(int[] nums, int k) {
        // 因为 nums[i] 可能是负数， 所以普通的 sliding window 不对了
        // brute force: 以每一个数为 left， 然后找满足条件的 subarray: TLE
        int n = nums.length;
        int result = Integer.MAX_VALUE;
        for (int left = 0; left < n; left++) {
            int sum = 0;
            for (int right = left; right < n; right++) {
                sum += nums[right];
                if (sum >= k) {
                    result = Math.min(result, right + 1 - left);
                    break;
                }
            }
        }
        return result == Integer.MAX_VALUE ? -1 : result;
    }
}

// 907. Sum of Subarray Minimums
// Medium
//
// Given an array of integers A, find the sum of min(B), where B ranges over every (contiguous) subarray of A.
//
// Since the answer may be large, return the answer modulo 10^9 + 7.
//
//
//
// Example 1:
//
// Input: [3,1,2,4]
// Output: 17
// Explanation: Subarrays are [3], [1], [2], [4], [3,1], [1,2], [2,4], [3,1,2], [1,2,4], [3,1,2,4].
// Minimums are 3, 1, 2, 4, 1, 1, 2, 1, 1, 1.  Sum is 17.
//
//
//
// Note:
//
//     1 <= A.length <= 30000
//     1 <= A[i] <= 30000


class Solution {
    public int sumSubarrayMins(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }

        // return mytry(A);

        return method1(A);
    }

    private int method1(int[] nums) {
        // 这个方法对的原因我个人的理解是： left[i]/right[i] 存的实际上是一个距离， i 离它前面/后面一个比它 nums[i] 小的数的距离
        // [... j ...... i ...... k ...]
        //      |  left  X  right |
        // 那么相乘可以得到从 j 到 k 的 subarray 的个数， 即 [j, i] 可以从前往后加上 right 个 ([i + 1, k])， [i, k] 也可以从 i 后往前加上 left 个 ([j, i - 1])
        // 对于每个 subarray， 给最终结果贡献的是 nums[i], i.e. 即当前所有 subarray 的 min
        // 注意 duplicate 的情况， 我们采用一边 strict 一边不 strict 的方法来求， 也就是说当一个 subarray 有两个相同的 min 在的时候， 只算一次, e.g. [71,55,82,55]
        int n = nums.length;
        final int MOD = (int) 1e9 + 7;
        Stack<Integer> stack = new Stack<>();
        // left[i] 和 right[i] 放的是 从左边/右边过来到 i 为止， 以 nums[i] 为 min 的 subarray 的个数
        int[] left = new int[n];
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] >= nums[i]) {
                // 设置一边为 strict larger， 另一边是 non-strict larger (i.e. larger or equal) 来处理 duplicates 的情况
                stack.pop();
            }
            left[i] = stack.isEmpty() ? i + 1 : i - stack.peek(); // stack.peek() 是比 nums[i] 严格大的， 所以不包括， 不用 + 1
            stack.push(i);
        }
        stack = new Stack<>();
        int[] right = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && nums[stack.peek()] > nums[i]) {
                stack.pop();
            }
            right[i] = stack.isEmpty() ? n - 1 + 1 - i : stack.peek() - i;
            stack.push(i);
        }

        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum = ((nums[i] * left[i] * right[i]) % MOD + sum) % MOD;
        }
        return sum;
    }

    private int mytry(int[] nums) {
        // TLE
        // 我感觉我真的是突发奇想， basically 这个就是每次求 k size window 的 min 的时候， 去和 k - 1 size 的 min 做比较， 然后每次存下来
        // 可以认为是 O(N ^ 2) time
        // 如果要改进， 我的直觉的是要用单调栈， 因为要不断地去找当前数的前一个小的值
        int n = nums.length;
        final int MOD = 1000000007;
        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            list.add(num);
        }
        int sum = 0;
        for (int i = 0; i < n; i++) {
            List<Integer> next = getMinSumByK(list, nums, i, MOD);
            sum = (sum + next.get(next.size() - 1) % MOD) % MOD;
            list = next;
        }
        return sum;
    }
    private List<Integer> getMinSumByK(List<Integer> list, int[] nums, int k, int MOD) {
        int sum = 0;
        List<Integer> mins = new ArrayList<>();
        for (int i = k; i < nums.length; i++) {
            int min = Math.min(nums[i], list.get(i - k));
            sum = (sum + min) % MOD;
            mins.add(min);
        }
        mins.add(sum);
        return mins;
    }
}

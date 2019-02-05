// 239. Sliding Window Maximum
// DescriptionHintsSubmissionsDiscussSolution
// Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position. Return the max sliding window.
//
// Example:
//
// Input: nums = [1,3,-1,-3,5,3,6,7], and k = 3
// Output: [3,3,5,5,6,7]
// Explanation:
//
// Window position                Max
// ---------------               -----
// [1  3  -1] -3  5  3  6  7       3
//  1 [3  -1  -3] 5  3  6  7       3
//  1  3 [-1  -3  5] 3  6  7       5
//  1  3  -1 [-3  5  3] 6  7       5
//  1  3  -1  -3 [5  3  6] 7       6
//  1  3  -1  -3  5 [3  6  7]      7
// Note:
// You may assume k is always valid, 1 ≤ k ≤ input array's size for non-empty array.
//
// Follow up:
// Could you solve it in linear time?


class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }

        // return method1(nums, k);

        return method2(nums, k);
    }

    private int[] method1(int[] nums, int k) {
        // Deque: we always keep max within k range on the head, and offer index into tail (when we iterate it, no matter what it's larger or smaller), when it has k size, poll from the head (which makes sense, remove previous max out of k range), I think it's O(N) time (each index can be offer and poll twice at most) and O(K) space (dq has k at most)
        //ref: https://leetcode.com/problems/sliding-window-maximum/discuss/65884/Java-O(n)-solution-using-deque-with-explanation
        int n = nums.length;
        int[] result = new int[n - k + 1];
        Deque<Integer> dq = new ArrayDeque<>(); // <index>
        for (int i = 0; i < n; i++) {
            // remove values out of range of k, compare index
            while (!dq.isEmpty() && dq.peekFirst() <= i - k) {
                dq.pollFirst();
            }
            // 在把当前 index 放进去之前， 先比较头部和现在 value 的大小， 我们总是保证头部是 k range 内的最大; This is because if a[x] < a[i] and x< i, then a[x] has no chance to be the "max" in [i - (k - 1), i], or any other subsequent window: a[i] would always be a better candidate.
            while (!dq.isEmpty() && nums[dq.peekLast()] < nums[i]) {
                dq.pollLast();
            }
            // 有可能个数会小于 k， 正是对的了， 因为往后放入 i， k 范围内的最大值可能就是头部那个在中间的， 并且可以保证再往后几个 i 还是最大， 因为 dq 的 size 小于 k
            // 每次都从尾部放入
            dq.offerLast(i);
            // As a result elements in the deque are ordered in both sequence in array and their value. At each step the head of the deque is the max element in [i - (k - 1), i]
            if (i >= k - 1) {
                result[i - k + 1] = nums[dq.peekFirst()];
            }
        }
        return result;
    }

    private int[] method2(int[] nums, int k) {
        // https://leetcode.com/problems/sliding-window-maximum/discuss/65881/O(n)-solution-in-Java-with-two-simple-pass-in-the-array
        int n = nums.length;
        // partition the array in blocks of size k, get the leftMax from left and leftRight from right within each block
        int[] left = new int[n];
        int[] right = new int[n];
        left[0] = nums[0];
        right[n - 1] = nums[n - 1];
        for (int i = 1; i < n; i++) {
            if (i % k == 0) {
                left[i] = nums[i];
            } else {
                left[i] = Math.max(left[i - 1], nums[i]);
            }
        }
        for (int i = n - 2; i >= 0; i--) {
            right[i] = (i % k == 0 ? nums[i] : Math.max(right[i + 1], nums[i]));
        }
        int[] result = new int[n - k + 1];
        for (int i = 0, j = 0; i < n - k + 1; i++) {
            // 注意这里的顺序， 是 i 位置的【右边】最大， 以及 k 之后的位置的【左边】最大
            // 因为 i 都是当前 k 范围的起始点， 有可能当前 k range 会跨越 block 的分界线， 所以如果取 k 末尾的右边值可能会有超过 k 范围内的更右边的 max， 同样地， 如果左边选当前 i 的话， 可能会因为 block 的阻断而没选到后面的 max， 因此选 k 末尾的左边能够保证 block 后能拿到最大值， 而选当前 i 的右边值能达到从 block 向左到当前 i 的最大值， 这样就能覆盖整个 k range 内的所有 max 了
            result[j++] = Math.max(right[i], left[i + k - 1]);
        }
        return result;
    }
}

// 525. Contiguous Array
// DescriptionHintsSubmissionsDiscussSolution
//
// Given a binary array, find the maximum length of a contiguous subarray with equal number of 0 and 1.
//
// Example 1:
//
// Input: [0,1]
// Output: 2
// Explanation: [0, 1] is the longest contiguous subarray with equal number of 0 and 1.
//
// Example 2:
//
// Input: [0,1,0]
// Output: 2
// Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with equal number of 0 and 1.
//
// Note: The length of the given binary array will not exceed 50,000.
// Seen this question in a real interview before?
//
//     Difficulty:Medium


class Solution {
    public int findMaxLength(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        return method1(nums);
    }

    // 一开始呢， 就想着 2 pointers or sliding window, 然后用个 count 去记录 0 和 1 的个数
    // 但是！ 这样没办法找到最长呐， 因为不知道是不是连续的， 也不知道对应的 index， 没办法根据 0 和 1 个数决定是移动 left 还是 right
    // 或者这么想， 两个 counter 记录 0 和 1， 当它们不相等的时候， 没办法觉得是 remove 多的那个， 还是往下走看看能不能增加少的那个
    // 其实之前也有过类似的做法， 用个 count， 0 就 + 1， 1 就 - 1， 当 count == 0 的时候意味着找到了相等的个数
    // 但是呢， 因为从一开始计数， count 并不一定能够回到 0， 比如 [0, 0, 0, 1, 1], 最后是 1
    // 在 index == 1 的位置， count 也等于 1， 所以我们可以记录之前出现过的 count， 再遇到的时候就找到了
    // 所以可以用一个 map 记录对应的 count 和 index
    // 类似于 2 sum 我感觉

    private int method1(int[] nums) {
        int n = nums.length;
        int count = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1); // init
        int result = 0;
        for (int i = 0; i < n; i++) {
            count += (nums[i] == 0 ? -1 : 1);
            if (map.containsKey(count)) {
                // 之前出现的 index (exclusive) 到当下 i 是新的满足条件的 subarray, i + 1 - map.get(count) - 1
                result = Math.max(result, i - map.get(count));
            } else {
                map.put(count, i);
            }
        }
        return result;
    }
}

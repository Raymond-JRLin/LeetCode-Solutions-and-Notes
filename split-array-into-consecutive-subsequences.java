// 659. Split Array into Consecutive Subsequences
// DescriptionHintsSubmissionsDiscussSolution
// You are given an integer array sorted in ascending order (may contain duplicates), you need to split them into several subsequences, where each subsequences consist of at least 3 consecutive integers. Return whether you can make such a split.
//
// Example 1:
// Input: [1,2,3,3,4,5]
// Output: True
// Explanation:
// You can split them into two consecutive subsequences :
// 1, 2, 3
// 3, 4, 5
// Example 2:
// Input: [1,2,3,3,4,4,5,5]
// Output: True
// Explanation:
// You can split them into two consecutive subsequences :
// 1, 2, 3, 4, 5
// 3, 4, 5
// Example 3:
// Input: [1,2,3,4,4,5]
// Output: False
// Note:
// The length of the input is in range of [1, 10000]


class Solution {
    public boolean isPossible(int[] nums) {
        if (nums == null || nums.length < 3) {
            return false;
        }

        return method1(nums);
    }

    private boolean method1(int[] nums) {
        // ref: https://leetcode.com/problems/split-array-into-consecutive-subsequences/discuss/106496/Java-O(n)-Time-O(n)-Space
        // O(N) time and O(N) space
        // 要注意不同的查看条件， 有的是查是否还有 num 可以用， 有的是看它是否在某个顺子内
        // 还要注意不同 HashMap 的操作， 对某个 HashMap 更改 value 自然只能是拿自身的 value 来， 譬如 map 减少， 就是自身可以使用的个数减少， 而 group 就是 group 内需要多少个来构成顺子的改动
        Map<Integer, Integer> map = new HashMap<>();// <number, frequency>
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        Map<Integer, Integer> group = new HashMap<>(); // 记录 group 需要的数字及其需要的个数
        for (int num : nums) {
            if (map.get(num) == 0) {
                // num 用完了
                continue;
            } else if (group.getOrDefault(num, 0) > 0) {
                // num 在 group 中， 意味着它在某个顺子内
                group.put(num, group.get(num) - 1); // 用了一个， 减少一个需要
                group.put(num + 1, group.getOrDefault(num + 1, 0) + 1); // num 下一位 num + 1 可以接上去了, 多加 1 个需要的个数
            } else if (map.getOrDefault(num + 1, 0) > 0 && map.getOrDefault(num + 2, 0) > 0) {
                // 如果不在某个顺子内， 看看是否能成为新的顺子的开头， 去查看之后两位是不是还有个数可以用
                // 可以的话， 减少他们的 freq， 因为拿来当作一组新的顺子
                map.put(num + 1, map.get(num + 1) - 1);
                map.put(num + 2, map.get(num + 2) - 1);
                // 连上了就需要 num + 3 来继续往后接
                group.put(num + 3, group.getOrDefault(num + 3, 0) + 1);
            } else {
                // 都不可以就直接返回 false， 说明这个 num 是个单子
                return false;
            }
            map.put(num, map.get(num) - 1); // 用了 num， 将其 freq 减 1
        }
        return true;
    }
}

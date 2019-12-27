// 659. Split Array into Consecutive Subsequences
// DescriptionHintsSubmissionsDiscussSolution
//
// Given an array nums sorted in ascending order, return true if and only if you can split it into 1 or more subsequences such that each subsequence consists of consecutive integers and has length at least 3.
//
//
//
// Example 1:
//
// Input: [1,2,3,3,4,5]
// Output: True
// Explanation:
// You can split them into two consecutive subsequences :
// 1, 2, 3
// 3, 4, 5
//
// Example 2:
//
// Input: [1,2,3,3,4,4,5,5]
// Output: True
// Explanation:
// You can split them into two consecutive subsequences :
// 1, 2, 3, 4, 5
// 3, 4, 5
//
// Example 3:
//
// Input: [1,2,3,4,4,5]
// Output: False
//
//
//
// Constraints:
//
//     1 <= nums.length <= 10000
//
//
// Seen this question in a real interview before?
//
//     Difficulty:Medium


class Solution {
    public boolean isPossible(int[] nums) {
        if (nums == null || nums.length < 3) {
            return false;
        }

        // return method1(nums);

        return method2(nums);
    }

    // 题目有个条件要注意到， 就是给的 array 是 sorted

    private boolean method2(int[] nums) {
        // O(N) time and O(1) space
        // 如果要省空间， 那么就不用 map， 而用 variables 来记录， 稍微有点儿麻烦想到
        int n = nums.length;
        // prev 上一轮的结尾数字， p1/p2 表示的是以 prev 结尾的长度为 1/2 的 consecutive subsequence 的个数， p3 表示的是长度 >= 3 的个数
        int prev = -1, p1 = 0, p2 = 0, p3 = 0;
        // curr 当前查看的数字，c1/c2/c3 定义同上
        int curr = 0, c1 = 0, c2 = 0, c3 = 0;

        int i = 0;
        while (i < n) {
            curr = nums[i];
            int count = 0;
            while (i < n && nums[i] == curr) {
                count++;
                i++;
            }

            if (prev + 1 != curr) {
                // curr 不能接到前一个以 prev 结尾的 consecutive subsequence
                if (p1 != 0 || p2 != 0) {
                    // 如果之前有以 prev 结尾的长度为 1/2 的 consecutive subsequence， 那么就已经不够了
                    return false;
                }
                // 更新一下
                c1 = count;
                c2 = 0;
                c3 = 0;
            } else {
                // curr 可以 append 到 prev 成为新的更长的 consecutive subsequence
                if (count < p1 + p2) {
                    // 但是如果 curr 的个数不够 append 到以 prev 结尾的长度为 1/2 的， 那么仍然不够
                    return false;
                }
                // 更新以 curr 结尾的各长度的 consecutive subsequence
                // 我们优先 append 到前面短的 consecutive subsequence 上， 剩下的再作为只有它自己一个的 subsequence
                c1 = Math.max(0, count - (p1 + p2 + p3));
                // 这个很显然， append 一个上去
                c2 = p1;
                // 这个也是 append 上去， 但还有长度 >= 3 的， 要减掉那些 append 给长度为 1/2 的
                c3 = p2 + Math.min(p3, count - (p1 + p2));
            }
            // 更新以供下一轮使用
            prev = curr;
            p1 = c1;
            p2 = c2;
            p3 = c3;
        }
        // 最后不能有长度为 1/2 的 consecutive subsequence
        return p1 == 0 && p2 == 0;
    }

    private boolean method1(int[] nums) {
        // ref: https://leetcode.com/problems/split-array-into-consecutive-subsequences/discuss/106493/c-on-solution-two-pass
        // O(N) time and space
        Map<Integer, Integer> map = new HashMap<>(); // <num, freq>
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        Map<Integer, Integer> tails = new HashMap<>(); // <i, j> := 以 i 结尾的 consecutive subsequence 有 j 个
        for (int num : nums) {
            if (!map.containsKey(num)) {
                continue;
            }
            deFreq(map, num);
            // 如果有前一位数， 加上去成为更长的 consecutive subsequence
            if (tails.containsKey(num - 1)) {
                deFreq(tails, num - 1);
                tails.put(num, tails.getOrDefault(num, 0) + 1);
            } else if (map.containsKey(num + 1) && map.containsKey(num + 2)) {
                // 如果加不进已有的 consecutive subsequence， 那就成为新的 consecutive subsequence 的起点
                deFreq(map, num + 1);
                deFreq(map, num + 2);
                tails.put(num + 2, tails.getOrDefault(num + 2, 0) + 1);
            } else {
                return false;
            }
        }
        return true;
    }
    private void deFreq(Map<Integer, Integer> map, int num) {
        map.put(num, map.get(num) - 1);
        if (map.get(num) == 0) {
            map.remove(num);
        }
    }
}

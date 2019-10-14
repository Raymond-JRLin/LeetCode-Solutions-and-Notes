// 1224. Maximum Equal Frequency
//
//     User Accepted: 463
//     User Tried: 817
//     Total Accepted: 502
//     Total Submissions: 2543
//     Difficulty: Hard
//
// Given an array nums of positive integers, return the longest possible length of an array prefix of nums, such that it is possible to remove exactly one element from this prefix so that every number that has appeared in it will have the same number of occurrences.
//
// If after removing one element there are no remaining elements, it's still considered that every appeared number has the same number of ocurrences (0).
//
//
//
// Example 1:
//
// Input: nums = [2,2,1,1,5,3,3,5]
// Output: 7
// Explanation: For the subarray [2,2,1,1,5,3,3] of length 7, if we remove nums[4]=5, we will get [2,2,1,1,3,3], so that each number will appear exactly twice.
//
// Example 2:
//
// Input: nums = [1,1,1,2,2,2,3,3,3,4,4,4,5]
// Output: 13
//
// Example 3:
//
// Input: nums = [1,1,1,2,2,2]
// Output: 5
//
// Example 4:
//
// Input: nums = [10,2,8,9,3,8,1,5,2,3,7,6]
// Output: 8
//
//
//
// Constraints:
//
//     2 <= nums.length <= 10^5
//     1 <= nums[i] <= 10^5
//


class Solution {
    public int maxEqualFreq(int[] nums) {
        // 题目意思有点儿绕， 要看清楚
        // 题目问的是： 最长的一段prefix， 删掉某个数， 剩下的数拥有相同的频率
        // 当然可以去暴力枚举所有的 prefix， 然后对频率分组查看
        // 既然讲到 prefix， 那么就可以维护一个前缀， 去记录之前出现过的数的频率， 以及频率对应的数的个数

        return method1(nums);
    }

    private int method1(int[] nums) {
        int n = nums.length;
        Map<Integer, Integer> count = new HashMap<>(); // <number, number's frequency>
        Map<Integer, Integer> freq = new HashMap<>(); // <frequency, # number in this frequency>
        int result = 0;
        for (int i = 0; i < n; i++) {
            int curr = nums[i]; // 当前的数
            count.put(curr, count.getOrDefault(curr, 0) + 1); // 更新这个数对应的频率
            int f = count.get(curr); // 当前数的频率
            freq.put(f, freq.getOrDefault(f, 0) + 1); // 更新频率所拥有的数的个数
            // 这里并不可以把数之前的频率删掉， 因为一直往后走， 这个数可以算作不同的之前出现过的频率
            // 假设此时 prefix 每个数的频率就是当前数的频率 f， 那么总共的数一共有 (当前频率 * 频率所有的数的个数)
            int sum = f * freq.get(f);
            if (sum == i + 1 && i < n - 1) {
                // 总数等于当前 prefix 的长度 (i + 1)， 那么可以从下一个拿一个过来 （用来删掉） 得到一个满足条件的 prefix
                // 当然 i 不能是最后一个数
                result = i + 1 + 1;
            } else if (sum == i + 1 - 1) {
                // 总数等于当前 prefix 的长度 (i + 1) 减 1， 那么此 prefix 即是满足条件的， 因为可以在其中删掉一个
                result = i + 1;
            }
        }
        return result;
    }
}

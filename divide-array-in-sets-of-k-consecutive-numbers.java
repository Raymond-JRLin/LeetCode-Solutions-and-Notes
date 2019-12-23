// 1296. Divide Array in Sets of K Consecutive Numbers
//
//     User Accepted: 1848
//     User Tried: 2411
//     Total Accepted: 1890
//     Total Submissions: 4750
//     Difficulty: Medium
//
// Given an array of integers nums and a positive integer k, find whether it's possible to divide this array into sets of k consecutive numbers
// Return True if its possible otherwise return False.
//
//
//
// Example 1:
//
// Input: nums = [1,2,3,3,4,4,5,6], k = 4
// Output: true
// Explanation: Array can be divided into [1,2,3,4] and [3,4,5,6].
//
// Example 2:
//
// Input: nums = [3,2,1,2,3,4,3,4,5,9,10,11], k = 3
// Output: true
// Explanation: Array can be divided into [1,2,3] , [2,3,4] , [3,4,5] and [9,10,11].
//
// Example 3:
//
// Input: nums = [3,3,2,2,1,1], k = 3
// Output: true
//
// Example 4:
//
// Input: nums = [1,2,3,4], k = 3
// Output: false
// Explanation: Each array should be divided in subarrays of size 3.
//
//
//
// Constraints:
//
//     1 <= nums.length <= 10^5
//     1 <= nums[i] <= 10^9
//     1 <= k <= nums.length
//


class Solution {
    public boolean isPossibleDivide(int[] nums, int k) {

        // return mytry(nums, k);

        return method2(nums, k);
    }

    private boolean method2(int[] nums, int k) {
        // 和 mytry 类似的意思， 不过不每次 deFreq by 1， 而是直接往后减掉当前数的频率
        // 有点 greedy 的意思
        int n = nums.length;
        if (n % k != 0) {
            return false;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int key : map.keySet()) {
            pq.offer(key);
        }

        while (!pq.isEmpty()) {
            int curr = pq.poll();
            if (map.get(curr) == 0) {
                continue;
            }
            int freq = map.get(curr);
            //  把以 curr 为起始的 subarray 的每个元素都减掉 freq
            for (int i = 0; i < k; i++) {
                int next = curr + i;
                if (!map.containsKey(next) || map.get(next) < freq) {
                    return false;
                } else {
                    map.put(next, map.get(next) - freq);
                }
            }
        }
        return true;
    }

    private boolean mytry(int[] nums, int k) {
        // 直接按照题目意思去找每个 size 为 k 的 consecutive subarray
        // 有点 greedy 的意思
        int n = nums.length;
        if (n % k != 0) {
            return false;
        }
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int i = 0;
        while (!map.isEmpty()) {
            int min = map.firstKey();
            deFreq(map, min);
            i = 1;
            for (; i < k; i++) {
                min++;
                if (map.containsKey(min)) {
                    deFreq(map, min);
                } else {
                    return false;
                }
            }
        }
        return i == k;
    }
    private void deFreq(Map<Integer, Integer> map, int num) {
        map.put(num, map.get(num) - 1);
        if (map.get(num) == 0) {
            map.remove(num);
        }
    }
}

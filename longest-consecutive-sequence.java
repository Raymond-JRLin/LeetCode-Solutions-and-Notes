// 128. Longest Consecutive Sequence
// DescriptionHintsSubmissionsDiscussSolution
// Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
//
// Your algorithm should run in O(n) complexity.
//
// Example:
//
// Input: [100, 4, 200, 1, 3, 2]
// Output: 4
// Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
// Seen this question in a real interview before?
// Difficulty:Hard


class Solution {
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        return method1(nums);

        // return method2(nums);

        // return method3(nums);
    }

    private int method3(int[] nums) {
        // use HashSet, O(N) time and O(N) space
        // ref: https://leetcode.com/problems/longest-consecutive-sequence/discuss/41057/Simple-O(n)-with-Explanation-Just-walk-each-streak
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int result = 0;
        for (int num : nums) {
            if (!set.contains(num - 1)) {
                // this num is a start
                int next = num + 1; // start to check next num
                while (set.contains(next)) {
                    // keep tracking, if it exist
                    next++;
                }
                // finish a consecutive sequence, right now next is not included in set after last next++, so we can minus num directly to get the number (no need + 1)
                result = Math.max(result, next - num);
            }
        }
        // Since we check each streak only once, this is overall O(n).
        return result;
    }

    private int method2(int[] nums) {
        // use HashMap, O(N) time and O(1) space
        // ref: https://leetcode.com/problems/longest-consecutive-sequence/discuss/41055/My-really-simple-Java-O(n)-solution-Accepted
        int result = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            //
            if (!map.containsKey(num)) {
                int left = map.containsKey(num - 1) ? map.get(num - 1) : 0;
                int right = map.containsKey(num + 1) ? map.get(num + 1) : 0;
                int sum = left + right + 1;
                map.put(num, sum);
                result = Math.max(result, sum);
                map.put(num - left, sum);
                map.put(num + right, sum);
            }

        }
        return result;
    }

    private int method1(int[] nums) {
        // sort: O(NlogN)
        Arrays.sort(nums);
        int global = 1; // basic/edge case: only one value, e.g. [0]
        int local = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                continue;
            }
            if (nums[i] == nums[i - 1] + 1) {
                local++;
            } else {
                global = Math.max(global, local);
                local = 1;
            }
        }
        // we update global when they don't satisfy nums[i] == nums[i - 1] + 1, so we should do one more time
        global = Math.max(global, local);
        return global;
    }
}

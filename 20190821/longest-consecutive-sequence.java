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

        // return method1(nums);

        return method2(nums);
    }

    private int method2(int[] nums) {
        // 只朝一个方向走， 比如如果有 num - 1 那就不去查看， 直到最小的 value 被找到， 然后一路向上找 consecutive
        int n = nums.length;
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int result = 0;
        for (int num : nums) {
            if (set.contains(num - 1)) {
                continue;
            }
            int count = 1;
            while (set.contains(num + 1)) {
                count++;
                num += 1;
            }
            result = Math.max(result, count);
        }
        return result;
    }

    private int method1(int[] nums) {
        // sequence doesn't require order
        // use a map to record the number and its longest length when it's the edge number of the sequence
        Map<Integer, Integer> map = new HashMap<>();
        int result = 0;
        for (int num : nums) {
            // skip duplicates
            if (map.containsKey(num)) {
                continue;
            }
            // stretch to 2 sides
            int leftLen = map.getOrDefault(num - 1, 0);
            int rightLen = map.getOrDefault(num + 1, 0);
            int totalLen = leftLen + rightLen + 1;
            result = Math.max(result, totalLen);
            // update itself
            map.put(num, totalLen);
            // update edge numbers
            map.put(num - leftLen, totalLen);
            map.put(num + rightLen, totalLen);
        }
        return result;
    }
}

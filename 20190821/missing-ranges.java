// 163. Missing Ranges
// DescriptionHintsSubmissionsDiscussSolution
// Given a sorted integer array nums, where the range of elements are in the inclusive range [lower, upper], return its missing ranges.
//
// Example:
//
// Input: nums = [0, 1, 3, 50, 75], lower = 0 and upper = 99,
// Output: ["2", "4->49", "51->74", "76->99"]
// Seen this question in a real interview before?
// Difficulty:Medium


class Solution {
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {

        // return method1(nums, lower, upper);

        return method2(nums, lower, upper);
    }

    private List<String> method2(int[] nums, int lower, int upper) {
        // more intuitive
        // 但输入要是符合条件的， 即 nums 是在 [lower, upper] 区间内
        List<String> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            result.add(getRange(lower, upper));
            return result;
        }
        int n = nums.length;
        // 1. check lower and 1st number in nums[]
        if (lower < nums[0]) {
            result.add(getRange(lower, nums[0] - 1));
        }
        // 2. check each adjacent numbers in nums[]
        for (int i = 0; i < n - 1; i++) {
            // special case: duplicates on MAX_VALUE, MIN_VALUE
            if (nums[i] != nums[i + 1] && nums[i] + 1 < nums[i + 1]) {
                result.add(getRange(nums[i] + 1, nums[i + 1] - 1));
            }
        }
        // 3. check last number and upper
        if (nums[n - 1] < upper) {
            result.add(getRange(nums[n - 1] + 1, upper));
        }
        return result;
    }

    private List<String> method1(int[] nums, int lower, int upper) {
        List<String> result = new ArrayList<>();
        long left = (long) lower;
        for (int num : nums) {
            if (num < left) {
                continue;
            } else if (num == left) {
                left++;
            } else {
                // left ... num
                result.add(getRange(left, num - 1));
                left = (long) num + 1;
            }
        }
        if (left <= upper) {
            result.add(getRange(left, upper));
        }
        return result;
    }
    private String getRange(long start, int end) {
        if (start == end) {
            return String.valueOf(start);
        } else {
            return start + "->" + end;
        }
    }
}

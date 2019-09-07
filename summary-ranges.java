// 228. Summary Ranges
// DescriptionHintsSubmissionsDiscussSolution
// Given a sorted integer array without duplicates, return the summary of its ranges.
//
// Example 1:
//
// Input:  [0,1,2,4,5,7]
// Output: ["0->2","4->5","7"]
// Explanation: 0,1,2 form a continuous range; 4,5 form a continuous range.
// Example 2:
//
// Input:  [0,2,3,4,6,8,9]
// Output: ["0","2->4","6","8->9"]
// Explanation: 2,3,4 form a continuous range; 8,9 form a continuous range.
// Seen this question in a real interview before?
// Difficulty:Medium


class Solution {
    public List<String> summaryRanges(int[] nums) {
        if (nums == null || nums.length == 0) {
            return Collections.emptyList();
        }

        // return mytry(nums);

        return method2(nums);
    }

    private List<String> method2(int[] nums) {
        // 2 pointers as the start and end of range
        int n = nums.length;
        List<String> result = new ArrayList<>();
        for (int start = 0, end = 0; end < n; end++) {
            while (end + 1 < n && nums[end] + 1 == nums[end + 1]) {
                end++;
            }
            if (start == end) {
                result.add(String.valueOf(nums[start]));
            } else {
                result.add(nums[start] + "->" + nums[end]);
            }
            start = end + 1;
        }
        return result;
    }

    private List<String> mytry(int[] nums) {
        int n = nums.length;
        List<String> result = new ArrayList<>();
        int prev = nums[0];
        for (int i = 1; i < n; i++) {
            if (nums[i - 1] + 1 == nums[i]) {
                continue;
            }
            result.add(getRange(prev, nums[i - 1]));
            prev = nums[i];
        }
        result.add(getRange(prev, nums[n - 1]));
        return result;
    }
    private String getRange(int prev, int num) {
        if (prev == num) {
            return String.valueOf(prev);
        } else {
            return String.format("%d->%d", prev, num);
        }
    }
}

// 540. Single Element in a Sorted Array
// DescriptionHintsSubmissionsDiscussSolution
//
// You are given a sorted array consisting of only integers where every element appears exactly twice, except for one element which appears exactly once. Find this single element that appears only once.
//
//
//
// Example 1:
//
// Input: [1,1,2,3,3,4,4,8,8]
// Output: 2
//
// Example 2:
//
// Input: [3,3,7,7,10,11,11]
// Output: 10
//
//
//
// Note: Your solution should run in O(log n) time and O(1) space.
// Seen this question in a real interview before?
//
//     Difficulty:Medium


class Solution {
    public int singleNonDuplicate(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        return mytry(nums);
    }

    private int mytry(int[] nums) {
        int n = nums.length;
        int start = 0;
        int end = n - 1;
        while (start < end) {
            int mid = start + (end - start) / 2;
            // System.out.println("mid = " + mid + ", nums[mid]/nums[mid - 1] : " + nums[mid] + " , " + nums[mid - 1]);
            if (mid % 2 == 0) {
                if (nums[mid] == nums[mid - 1]) {
                    end = mid - 2;
                } else {
                    start = mid;
                }
            } else {
                if (nums[mid] == nums[mid - 1]) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
        }
        return nums[start];
    }
}

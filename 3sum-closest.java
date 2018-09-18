// 16. 3Sum Closest
// DescriptionHintsSubmissionsDiscussSolution
// Given an array nums of n integers and an integer target, find three integers in nums such that the sum is closest to target. Return the sum of the three integers. You may assume that each input would have exactly one solution.
//
// Example:
//
// Given array nums = [-1, 2, 1, -4], and target = 1.
//
// The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).



class Solution {
    public int threeSumClosest(int[] nums, int target) {

        // return mytry(nums, target);

        return method2(nums, target);
    }

    private int method2(int[] nums, int target) {
        // similar to 3Sum, we can use 3 pointers
        int n = nums.length;
        Arrays.sort(nums);
        int result = nums[0] + nums[1] + nums[n - 1];
        for (int i = 0; i < n - 2; i++) {
            int start = i + 1;
            int end = n - 1;
            while (start < end) {
                int sum = nums[i] + nums[start] + nums[end];
                if (sum == target) {
                    return target;
                } else if (sum > target) {
                    end--;
                } else {
                    start++;
                }
                // in while loop, after each move, we calculate the difference and compare to get the closer one
                if (Math.abs(sum - target) < Math.abs(result - target)) {
                    result = sum;
                }
            }

        }
        return result;
    }

    private int mytry(int[] nums, int target) {
        // brute force: O(N ^ 3) time
        int n = nums.length;
        Arrays.sort(nums);
        int result = nums[n - 1] + nums[n - 2] + nums[n - 3];
        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                for (int k = j + 1; k < n; k++) {
                    int sum = nums[i] + nums[j] + nums[k];
                    if (Math.abs(sum - target) < Math.abs(result - target)) {
                        // elements in array or target can be negative
                        result = sum;
                    }
                }
            }
        }
        return result;
    }
    private int equalOrClosest(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (Math.abs(nums[start] - target) <= Math.abs(nums[end] - target)) {
            return start;
        } else {
            return end;
        }
    }
}

// 219. Contains Duplicate II
// DescriptionHintsSubmissionsDiscussSolution
// Given an array of integers and an integer k, find out whether there are two distinct indices i and j in the array such that nums[i] = nums[j] and the absolute difference between i and j is at most k.
//
// Example 1:
//
// Input: nums = [1,2,3,1], k = 3
// Output: true
// Example 2:
//
// Input: nums = [1,0,1,1], k = 1
// Output: true
// Example 3:
//
// Input: nums = [1,2,3,1,2,3], k = 2
// Output: false


class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return false;
        }

        // return mytry(nums, k);

        return method2(nums, k);
    }

    private boolean method2(int[] nums, int k) {
        // sliding window: O(N) time and O(min{N, k}) space
        int n = nums.length;
        Set<Integer> set = new HashSet<>(); // <values within k range>
        for (int i = 0; i < n; i++) {
            if (i > k) {
                set.remove(nums[i - k - 1]);
            }
            if (set.contains(nums[i])) {
                return true;
            }
            set.add(nums[i]);
        }
        return false;
    }

    private boolean mytry(int[] nums, int k) {
        // brute force: O(N * min{N, k}) time and O(1) space
        int n = nums.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (j > i + k) {
                    break;
                }
                if (nums[i] == nums[j] && Math.abs(i - j) <= k) {
                    return true;
                }
            }
        }
        return false;
    }
}

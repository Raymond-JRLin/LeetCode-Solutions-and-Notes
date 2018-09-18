// 1. Two Sum
// DescriptionHintsSubmissionsDiscussSolution
// Given an array of integers, return indices of the two numbers such that they add up to a specific target.
//
// You may assume that each input would have exactly one solution, and you may not use the same element twice.
//
// Example:
//
// Given nums = [2, 7, 11, 15], target = 9,
//
// Because nums[0] + nums[1] = 2 + 7 = 9,
// return [0, 1].


class Solution {
    public int[] twoSum(int[] nums, int target) {

        // method 1: use 2 pointer after sorting - BUT we need to return the original indices, O(nlogn) time and O(1) space
        // return mytry1(nums, target);

        // return mytry2(nums, target);

        return method2(nums, target);
    }

    private int[] method2(int[] nums, int target) {
        // use HashMap to record the indices
        Map<Integer, Integer> map = new HashMap<>(); // <target - value, value's index>
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                // found
                int[] result = new int[2];
                result[0] = map.get(nums[i]);
                result[1] = i;
                return result;
            }
            map.put(target - nums[i], i);
        }
        return new int[2];
    }

    private int[] mytry2(int[] nums, int target) {
        // brute force: O(N ^ 2)
        int n = nums.length;
        for (int i = 0; i < n - 1; i++) {
            int num = target - nums[i];
            for (int j = i + 1; j < n; j++) {
                if (nums[j] == num) {
                    int[] result = new int[2];
                    result[0] = i;
                    result[1] = j;
                    return result;
                }
            }
        }
        return new int[2];
    }

    private int[] mytry1(int[] nums, int target) {
        Arrays.sort(nums); // cannot sort!!!
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum == target) {
                System.out.println("there is a answer: " + left + ", " + right);
                int[] result = new int[2];
                result[0] = left;
                result[1] = right;
                return result;
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        return new int[2];
    }
}

// 18. 4Sum
// DescriptionHintsSubmissionsDiscussSolution
// Given an array nums of n integers and an integer target, are there elements a, b, c, and d in nums such that a + b + c + d = target? Find all unique quadruplets in the array which gives the sum of target.
//
// Note:
//
// The solution set must not contain duplicate quadruplets.
//
// Example:
//
// Given array nums = [1, 0, -1, 0, -2, 2], and target = 0.
//
// A solution set is:
// [
//   [-1,  0, 0, 1],
//   [-2, -1, 1, 2],
//   [-2,  0, 0, 2]
// ]



class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        if (nums == null || nums.length < 4) {
            return Collections.emptyList();
        }

        return method1(nums, target);
    }

    public List<List<Integer>> method1(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length < 4) {
            return result;
        }
        int n = nums.length;
        Arrays.sort(nums);
        // 因为 i j 会组成所有组合， 所以要检查是否重复， 并可以用一些条件过滤一些情况
        for (int i = 0; i < n - 3; i++) {
            if (nums[i] + nums[n - 1] + nums[n - 2] + nums[n - 3] < target) {
                // too small
                continue;
            }
            if (nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target) {
                // too larget
                break;
            }
            if (i > 0 && nums[i] == nums[i - 1]) {
                // skip duplicates
                continue;
            }
            for (int j = i + 1; j < n - 2; j++) {
                if (nums[i] + nums[j] + nums[n - 1] + nums[n - 2] < target) {
                    // too small
                    continue;
                }
                if (nums[i] + nums[j] + nums[j + 1] + nums[j + 2] > target) {
                    // too larget
                    break;
                }
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    // skip duplicates
                    continue;
                }
                int left = j + 1;
                int right = n - 1;
                while (left < right) {
                    int sum = nums[i] + nums[j] + nums[left] + nums[right];
                    if (sum == target) {
                        List<Integer> list = new ArrayList<>();
                        list.add(nums[i]);
                        list.add(nums[j]);
                        list.add(nums[left]);
                        list.add(nums[right]);
                        result.add(list);
                        while (left < right && nums[left] == nums[left + 1]) {
                            // skip duplicate
                            left++;
                        }
                        while (left < right && nums[right] == nums[right - 1]) {
                            // skip duplicate
                            right--;
                        }
                        left++;
                        right--;
                    } else if (sum < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }
        return result;
    }
}

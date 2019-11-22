// 18. 4Sum
// DescriptionHintsSubmissionsDiscussSolution
//
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
//
// Seen this question in a real interview before?
//
//     Difficulty:Medium


class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        if (nums == null || nums.length < 4) {
            return new ArrayList<List<Integer>>();
        }

        // return mytry(nums, target);

        // return mytry2(nums, target);

        return method2(nums, target);
    }

    private List<List<Integer>> method2(int[] nums, int target) {
        // Reduce K sum problem to K – 1 sum Problem, until we can do 2 sum
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        return kSum(nums, target, 0, 4);
    }
    private List<List<Integer>> kSum(int[] nums, int target, int index, int k) {
        List<List<Integer>> result = new ArrayList<>();
        if (k == 2) {
            return twoSum(nums, target, index, nums.length - 1);
        }
        for (int i = index; i < nums.length - k + 1; i++) {
            if (i > index && nums[i] == nums[i - 1]) {
                continue;
            }
            List<List<Integer>> kMinusOneSum = kSum(nums, target - nums[i], i + 1, k - 1);
            if (kMinusOneSum.isEmpty()) {
                continue;
            }
            for (List<Integer> list : kMinusOneSum) {
                list.add(nums[i]);
                result.add(list);
            }
        }
        return result;
    }
    private List<List<Integer>> twoSum(int[] nums, int target, int start, int end) {
        List<List<Integer>> result = new ArrayList<>();
        while (start < end) {
            if (nums[start] + nums[end] == target) {
                // remember to wrap Arrays.asList() into a ArrayList<>(), otherwise Arrays.asList() is non-resizable
                List<Integer> list = new ArrayList<>(Arrays.asList(nums[start], nums[end]));
                result.add(list);
                while (start < end && nums[start + 1] == nums[start]) {
                    start++;
                }
                start++;
                while (start < end && nums[end - 1] == nums[end]) {
                    end--;
                }
                end--;
            } else if (nums[start] + nums[end] > target) {
                end--;
            } else {
                start++;
            }
        }
        return result;
    }

    private List<List<Integer>> mytry2(int[] nums, int target) {
        // TLE
        Arrays.sort(nums);
        int n = nums.length;
        List<List<Integer>> result = new ArrayList<>();
        dfsKSum(nums, target, 0, 0, 4, 0, result, new ArrayList<>());
        return result;
    }
    private void dfsKSum(int[] nums, int target, int index, int count, int k, int sum,
                     List<List<Integer>> result, List<Integer> list) {
        if (count == k) {
            if (sum == target) {
                result.add(new ArrayList<>(list));
            }
            return;
        }
        for (int i = index; i < nums.length; i++) {
            if (i > index && nums[i] == nums[i - 1]) {
                continue;
            }
            list.add(nums[i]);
            dfsKSum(nums, target, i + 1, count + 1, k, sum + nums[i], result, list);
            list.remove(list.size() - 1);
        }
    }

    private List<List<Integer>> mytry(int[] nums, int target) {
        int n = nums.length;
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        Arrays.sort(nums);
        for (int i = 0; i < n - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            for (int j = i + 1; j < n - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                int sum = target - nums[i] - nums[j];
                int left = j + 1;
                int right = n - 1;
                while (left < right) {
                    if (nums[left] + nums[right] == sum) {
                        result.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        while (left < right && nums[left] == nums[left + 1]) {
                            left++;
                        }
                        while (left < right && nums[right] == nums[right - 1]) {
                            right--;
                        }
                        left++;
                        right--;
                    } else if (nums[left] + nums[right] < sum) {
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

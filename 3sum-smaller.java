// 259. 3Sum Smaller
// DescriptionHintsSubmissionsDiscussSolution
// Given an array of n integers nums and a target, find the number of index triplets i, j, k with 0 <= i < j < k < n that satisfy the condition nums[i] + nums[j] + nums[k] < target.
//
// Example:
//
// Input: nums = [-2,0,1,3], and target = 2
// Output: 2
// Explanation: Because there are two triplets which sums are less than 2:
//              [-2,0,1]
//              [-2,0,3]
// Follow up: Could you solve it in O(n2) runtime?


class Solution {
    public int threeSumSmaller(int[] nums, int target) {
        if (nums == null || nums.length < 3) {
            return 0;
        }

        // return mytry(nums, target);

        // return method2(nums, target);

        return method3(nums, target);
    }

    private int method3(int[] nums, int target) {
        // 2 pointers: O(N ^ 2)
        int n = nums.length;
        int result = 0;
        // because we only consider 3 index pair, so it doesn't matter which 3 are
        Arrays.sort(nums);
        for (int i = 0; i < n - 2; i++) {
            int start = i + 1;
            int end = n - 1;
            while (start < end) {
                if (nums[i] + nums[start] + nums[end] < target) {
                    // since it's sorted, so if right now is qualified, then all index from end to start + 1 can be qualifies too, count these triples
                    result += end - start;
                    // move start to end to make a larger sum
                    start++;
                } else {
                    // move end to front to make a smaller sum
                    end--;
                }
            }
        }
        return result;
    }

    private int method2(int[] nums, int target) {
        // BS: O(N ^ 2 * logN)
        int n = nums.length;
        int result = 0;
        Arrays.sort(nums);
        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                int sum = target - nums[i] - nums[j];
                int index = bs(nums, j + 1, n - 1, sum); // find the index of number which is the largest less than target

                if (index != -1) {
                    result += index - j;
                }
            }
        }
        return result;
    }
    private int bs(int[] nums, int start, int end, int target) {
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] >= target) {
                end = mid;
            } else {
                start = mid;
            }
        }
        if (nums[end] < target) {
            return end;
        } else if (nums[start] < target) {
            return start;
        } else {
            return -1;
        }
    }

    private int mytry(int[] nums, int target) {
        // brute force: 3 for loops, O(N ^ 3)
        int n = nums.length;
        int result = 0;
        Arrays.sort(nums);
        // cannot exit in previous 2 for loops in advance, becaues nums[i], nums[j] or nums[k] may >= target, but final sum will be smaller than target
        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                for (int k = j + 1; k < n; k++) {
                    if (nums[i] + nums[j] + nums[k] >= target) {
                        break;
                    }
                    if (nums[i] + nums[j] + nums[k] < target) {
                        result++;
                    }
                }
            }
        }
        return result;
    }
}

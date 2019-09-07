// 209. Minimum Size Subarray Sum
// DescriptionHintsSubmissionsDiscussSolution
// Given an array of n positive integers and a positive integer s, find the minimal length of a contiguous subarray of which the sum ≥ s. If there isn't one, return 0 instead.
//
// Example:
//
// Input: s = 7, nums = [2,3,1,2,4,3]
// Output: 2
// Explanation: the subarray [4,3] has the minimal length under the problem constraint.
// Follow up:
// If you have figured out the O(n) solution, try coding another solution of which the time complexity is O(n log n).
// Seen this question in a real interview before?
// Difficulty:Medium


class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        // attention: sum >= s

        // return mytry(s, nums);

        // return method2(s, nums);

        // return method3(s, nums);

        return method4(s, nums);
    }

    private int method4(int target, int[] nums) {
        // 2 pointers: O(N)
        // ref: https://leetcode.com/problems/minimum-size-subarray-sum/discuss/59078/Accepted-clean-Java-O(n)-solution-(two-pointers)
        int n = nums.length;
        int sum = 0;
        int left = 0, right = 0;
        int result = Integer.MAX_VALUE;
        while (right < n) {
            sum += nums[right++]; // summary values
            while (sum >= target) {
                // update min length
                result = Math.min(result, right - left); // no need to + 1, since we already moved right to right by 1
                // minus left position value, and move left to right to decrease the length to find min one
                sum -= nums[left++];
            }

        }
        return result == Integer.MAX_VALUE ? 0 : result;
    }

    private int method3(int target, int[] nums) {
        // BS on prefix sum: O(NlogN)
        // ref: https://leetcode.com/articles/minimum-size-subarray-sum/
        int n = nums.length;
        int[] prefix = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }
        int result = Integer.MAX_VALUE;
        for (int i = 1; i < n + 1; i++) {
            int sum = prefix[i - 1] + target;
            int index = equalOrLarger(prefix, sum, i);
            if (index == -1) {
                // cannot find the equal or larger one, then if we move to next i, prefix[i] is bigger, then we cannot find next one definitely
                break;
            }
            result = Math.min(result, index - i + 1);
        }
        return result == Integer.MAX_VALUE ? 0 : result;
    }
    private int equalOrLarger(int[] nums, int target, int start) {
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
        if (nums[start] >= target) {
            return start;
        } else if (nums[end] >= target) {
            return end;
        } else {
            return -1;
        }
    }

    private int method2(int target, int[] nums) {
        // another brute force with prefix sum: O(N ^ 2)
        int n = nums.length;
        int[] prefix = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n + 1; j++) {
                if (prefix[j] - prefix[i] >= target) {
                    result = Math.min(result, j - i);
                    break;
                }
            }
        }
        return result == Integer.MAX_VALUE ? 0 : result;
    }

    private int mytry(int target, int[] nums) {
        // brute force: O(N ^ 2)
        int n = nums.length;
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = i; j < n; j++) {
                sum += nums[j];
                if (sum >= target) {
                    result = Math.min(result, j + 1 - i);
                    break;
                }
            }
        }
        return result == Integer.MAX_VALUE ? 0 : result;
    }
}

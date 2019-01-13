// 976. Largest Perimeter Triangle
// User Accepted: 1960
// User Tried: 2185
// Total Accepted: 2015
// Total Submissions: 3952
// Difficulty: Easy
// Given an array A of positive lengths, return the largest perimeter of a triangle with non-zero area, formed from 3 of these lengths.
//
// If it is impossible to form any triangle of non-zero area, return 0.
//
//
//
// Example 1:
//
// Input: [2,1,2]
// Output: 5
// Example 2:
//
// Input: [1,2,1]
// Output: 0
// Example 3:
//
// Input: [3,2,3,4]
// Output: 10
// Example 4:
//
// Input: [3,6,2,3]
// Output: 8
//
//
// Note:
//
// 3 <= A.length <= 10000
// 1 <= A[i] <= 10^6


class Solution {
    public int largestPerimeter(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }

        // return mytry(A);

        return method2(A);
    }

    private int method2(int[] nums) {
        // 很傻， sort 完了之后就直接取能组成三角形的最大的边就好了 -> 从后面的取
        Arrays.sort(nums);
        int n = nums.length;
        for (int i = n - 1; i >= 2; i--) {
            if (nums[i - 1] + nums[i - 2] > nums[i]) {
                return nums[i] + nums[i - 1] + nums[i - 2];
            }
        }
        return 0;
    }

    private int mytry(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int result = 0;
        for (int i = 0; i < n - 2; i++) {
            int j = i + 1;
            int k = n - 1;
            while (j < k) {
                if (nums[i] + nums[j] <= nums[k]) {
                    k--;
                    continue;
                }
                int perimeter = nums[i] + nums[j] + nums[k];
                result = Math.max(result, perimeter);
                j++;
            }
        }
        return result;
    }
}

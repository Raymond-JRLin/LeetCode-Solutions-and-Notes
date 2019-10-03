// 42. Trapping Rain Water
// DescriptionHintsSubmissionsDiscussSolution
// Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.
//
//
// The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped. Thanks Marcos for contributing this image!
//
// Example:
//
// Input: [0,1,0,2,1,0,1,3,2,1,2,1]
// Output: 6
// Seen this question in a real interview before?
// Difficulty:Hard


class Solution {
    public int trap(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }

        // return method1(height);

        return method2(height);
    }

    private int method2(int[] height) {
        // 2 pointers
        // 2 variables 代替 DP 数组， 不断去记录从两边开始走能够分别能到达的最高的高度
        int n = height.length;
        int leftMax = 0;
        int left = 0;
        int rightMax = 0;
        int right = n - 1;
        int result = 0;
        while (left <= right) {
            if (leftMax <= rightMax) {
                if (leftMax > height[left]) {
                    result += (leftMax - height[left]) * 1;
                } else {
                    leftMax = height[left];
                }
                left++;
            } else {
                if (rightMax > height[right]) {
                    result += (rightMax - height[right]) * 1;
                } else {
                    rightMax = height[right];
                }
                right--;
            }
        }
        return result;
    }

    private int method1(int[] height) {
        // DP
        int n = height.length;
        int[] leftMax = new int[n]; // leftMax[i] = 从左边起到达 i 这个位置的最高的高度
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, height[i]);
            leftMax[i] = max;
        }
        max = Integer.MIN_VALUE;
        int[] rightMax = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            max = Math.max(max, height[i]);
            rightMax[i] = max;
        }
        int result = 0;
        for (int i = 0; i < n; i++) {
            // 算更小的一端与当前高度的差值
            result += (Math.min(leftMax[i], rightMax[i]) - height[i]) * 1;
        }
        return result;
    }
}

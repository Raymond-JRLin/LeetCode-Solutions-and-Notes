// 11. Container With Most Water
// DescriptionHintsSubmissionsDiscussSolution
// Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai). n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines, which together with x-axis forms a container, such that the container contains the most water.
//
// Note: You may not slant the container and n is at least 2.
//
//
//
//
//
// The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. In this case, the max area of water (blue section) the container can contain is 49.
//
//
//
// Example:
//
// Input: [1,8,6,2,5,4,8,3,7]
// Output: 49



class Solution {
    public int maxArea(int[] height) {
        // return mytry(height);

        return method2(height);
    }

    private int method2(int[] height) {
        // 一开始有点没看懂题意， 应该是找两根线组成 container 的两边， 中间的就不能算了。 可以得到公式： V-water = (j - i) * min(ai, aj), 所以起始状态可以是首尾两个， 因为是最大的 j - i， 然后 two pointers 向中间移动去找更大的 V-water， 那么就是要确定移动的规则， 发现向内移动的时候 j - i 是变小的， 所以要去找更高的线， 即更大的 height[] 值， 同时短的线是不能 cover 长的线的， 所以我们移动两根指针中矮的那个
        // 或者参见： https://leetcode.com/problems/container-with-most-water/discuss/6099/Yet-another-way-to-see-what-happens-in-the-O(n)-algorithm
        // 就是说两边线短的那根决定了水的高度， 所以如果假设当 i = 1, j = 6， height[1] < height[6], 那么没必要去比较 i = 1, j = 2,3,4,5 的情况了, 因为此时水只能到 height[1] 的高度， 而 (6 - 1) 此时最大， 所以下一步就是移动 i 向右； 同理， 当 i = 2, j = 6， 假设 height[2] > height[6],  那么没必要去比较i = 3,4,5, j = 6 的情况了, 因为此时水只能到 height[6] 的高度， 而 (6 - 2) 此时最大。 因此最终我们比较 n - 1 次
        int start = 0;
        int end = height.length - 1;
        int result = 0;
        while (start < end) {
            result = Math.max(result, (end - start) * Math.min(height[start], height[end]));
            if (height[start] < height[end]) {
                start++;
            } else {
                end--;
            }
        }
        return result;
    }

    private int mytry(int[] height) {
        // brute 2 loops => TLE
        int n = height.length;
        int result = 0;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                result = Math.max(result, (j - i) * Math.min(height[i], height[j]));
            }
        }
        return result;
    }
}

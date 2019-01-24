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


class Solution {
    public int trap(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }

        // return method1(height);

        // return method2(height);

        return method3(height);
    }

    private int method3(int[] h) {
        // 2 pointers: (N) time and O(1) space
        int n = h.length;
        int left = 0;
        int right = n - 1;
        int leftMax = 0;
        int rightMax = 0;
        int result = 0;
        while (left <= right) {
            // like said in method1, water is dependent with current index and min{left, right}, so we just go to the lower part to check
            if (leftMax < rightMax) {
                if (h[left] >= leftMax) {
                    // from leftMax to current index, keep increasing, cannot trap water
                    leftMax = h[left];
                } else {
                    result += leftMax - h[left];
                }
                left++;
            } else {
                if (h[right] >= rightMax) {
                    rightMax = h[right];
                } else {
                    result += rightMax - h[right];
                }
                right--;
            }
        }
        return result;
    }

    private int method2(int[] h) {
        // stack: O(N) time (each index would be insert once and pop once at most) and space
        int n = h.length;
        int index = 0;
        int result = 0;
        Stack<Integer> stack = new Stack<>();
        while (index < n) {
            while (!stack.isEmpty() && h[index] > h[stack.peek()]) {
                // we found a previous index that can be bounded
                int prev = stack.pop();
                if (stack.isEmpty()) {
                    // no left wall, cannot trap water
                    break;
                }
                // what we trap is be bounded by left (stack.peek()) and right walls, so the water is within 2 walls
                int dis = index - 1 - stack.peek(); // length
                int height = Math.min(h[index], h[stack.peek()]) - h[prev]; // height diff
                result += dis * height;
            }
            // if height keeps decreasing, just push
            stack.push(index);
            index++;
        }
        return result;
    }

    private int method1(int[] h) {
        // brute force, kinda DP: 一个位置能装水， 把它自己视作 wall， 需要它左边或者右边有比它高的位置作为 wall， 所以我们可以去找 i 的左边最高和右边最高， 去他们的 min， 因为这两个位置是边界来 trap water。 同时我们只计算单位面积， 就是计算面积的时候高是两个位置的高度之差， 而长就是 1， 因为我们会去找每个位置， 所以只计算当前这一水平线的就好了， 下面淹没的水由它们之间更矮的 wall 去计算 => O(N) time and space
        int n = h.length;
        // get the maxLeft and maxRight for this index seperatedly
        // we should include height of current index, since then when we minus h[i] we get 0
        int[] left = new int[n];
        int[] right = new int[n];
        int max = 0;
        for (int i = 0; i < n; i++) {
            if (h[i] > max) {
                max = h[i];
            }
            left[i] = max;
        }
        max = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (h[i] > max) {
                max = h[i];
            }
            right[i] = max;
        }

        int result = 0;
        for (int i = 0; i < n; i++) {
            max = Math.min(left[i], right[i]); // use Min{left, right}
            result += max - h[i];
        }

        // or we can find maxLeft and maxRigt when we each index, O(N ^ 2) time and O(1) space
        // for (int i = 1; i < n - 1; i++) {
        //     int max_left = 0, max_right = 0;
        //     for (int j = i; j >= 0; j--) { //Search the left part for max bar size
        //         max_left = Math.max(max_left, h[j]);
        //     }
        //     for (int j = i; j < n; j++) { //Search the right part for max bar size
        //         max_right = Math.max(max_right, h[j]);
        //     }
        //     System.out.println("i is " + i + ", and maxLeft = " + max_left + ", maxRight = " + max_right);
        //     result += Math.min(max_left, max_right) - h[i];
        // }

        return result;
    }
}

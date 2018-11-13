// 84. Largest Rectangle in Histogram
// DescriptionHintsSubmissionsDiscussSolution
// Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, find the area of largest rectangle in the histogram.
//
//
//
//
// Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].
//
//
//
//
// The largest rectangle is shown in the shaded area, which has area = 10 unit.
//
//
//
// Example:
//
// Input: [2,1,5,6,2,3]
// Output: 10


class Solution {
    public int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }

        // return method1(heights);

        // return method2(heights);

        // return method3(heights);

        return method4(heights);
    }

    private int method4(int[] heights) {
        // divide and conquer: O(NlogN) time
        // ref: https://leetcode.com/problems/largest-rectangle-in-histogram/discuss/28910/Simple-Divide-and-Conquer-AC-solution-without-Segment-Tree
        int n = heights.length;
        return dq(heights, 0, n - 1);
    }
    private int dq(int[] heights, int start, int end) {
        if (start == end) {
            return heights[start];
        }
        int mid = start + (end - start) / 2;
        int leftArea = dq(heights, start, mid); // mid 左边能组成的最大面积
        int rightArea = dq(heights, mid + 1, end); // mid 右边能组成的最大面积
        int crossArea = getCrossArea(heights, start, mid, end); // 横跨 mid 能组成的最大面积
        return Math.max(Math.max(leftArea, rightArea), crossArea);
    }
    private int getCrossArea(int[] heights, int start, int mid, int end) {
        // System.out.println("check from " + start + " to " + end + ", and mid is " + mid);
        // 从 mid 向两边展开
        int left = mid;
        int right = mid + 1;
        int area = 0;
        int h = Math.min(heights[left], heights[right]);
        while (left >= start && right <= end) {
            h = Math.min(h, Math.min(heights[left], heights[right]));
            area = Math.max(area, h * (right - left + 1));
            // System.out.println("left and right are: " + left + ", " + right + ", and height and area is " + h + ", " + area);
            if (left == start) {
                right++;
            } else if (right == end) {
                left--;
            } else {
                // 左右都没到边界， 向更高的方向展开
                if (heights[left - 1] >= heights[right + 1]) {
                    left--;
                } else {
                    right++;
                }
            }
        }
        return area;
    }

    private int method3(int[] heights) {
        // use Stack, O(N) time and O(N) space
        int n = heights.length;
        Stack<Integer> stack = new Stack<>(); // store index
        int result = 0;
        for (int i = 0; i <= n; i++) {
            // 从 0 一直到 n， 因为查看的是到当前为止前面最高的 height 可以往前覆盖多少， （当下位置是更矮的）
            int currH = (i == n ? 0 : heights[i]); // n 就把 height 当作 0
            // System.out.println("i: " + i + ", and current height is " + currH);
            while (!stack.isEmpty() && heights[stack.peek()] >= currH) {
                // current i 更矮， 往前查看
                int h = heights[stack.pop()]; // 上一个最高的 height
                int left = (stack.isEmpty() ? -1 : stack.peek()); // 从最高 height h 位置往前， 第一个比 h 矮的位置， 即 left 右边到 h 位置都是 >= h 的， 下面 area 要算的也是这部分面积
                int area = h * (i - left - 1); // - 1 减掉的应该是 left 这个位置是不算的
                result = Math.max(result, area);
                // System.out.println("stack is not empty, pop h is " + h + ", left is " + left + ", so base is " + (i - left - 1) + ", and area is " + area);
            }
            stack.push(i);
        }
        return result;
    }

    private int method2(int[] heights) {
        // O(N) time and O(N) space
        // 从 metho1 优化而来， 先做一个预处理， 就不用每次都要去左右两边找第一个比它矮的位置 （最后一个不小于高度的）， 先把每个位置的左右两边存下来
        // ref: https://leetcode.com/problems/largest-rectangle-in-histogram/discuss/28902/5ms-O(n)-Java-solution-explained-(beats-96)
        int n = heights.length;
        int[] lessFromLeft = new int[n]; // the first coordinate of the bar to the left with height h[l] < h[i]
        int[] lessFromRight = new int[n]; // the first coordinate of the bar to the right with height h[r] < h[i]
        // initialization
        lessFromRight[n - 1] = n;
        lessFromLeft[0] = -1;
        // left
        for (int i = 1; i < n; i++) {
            int p = i - 1;
            while (p >= 0 && heights[p] >= heights[i]) {
                p = lessFromLeft[p];
            }
            lessFromLeft[i] = p;
        }
        // right
        for (int i = n - 2; i >= 0; i--) {
            int p = i + 1;
            while (p < n && heights[p] >= heights[i]) {
                p = lessFromRight[p];
            }
            lessFromRight[i] = p;
        }

        int maxArea = 0;
        for (int i = 0; i < n; i++) {
            maxArea = Math.max(maxArea, heights[i] * (lessFromRight[i] - lessFromLeft[i] - 1));
        }

        return maxArea;
    }

    private int method1(int[] heights) {
        // basic idea: O(N ^ 2) time
        // 以每个位置的高度为最低高度， 向左边和右边去找不低于这个高度的连续最远位置， 计算当前的面积
        int n = heights.length;
        int result = 0;
        for (int i = 0; i < n; i++) {
            // System.out.println("now check " + i);
            int left = i;
            while (left - 1 >= 0 && heights[left - 1] >= heights[i]) {
                left--;
            }
            int right = i;
            while (right + 1 < n && heights[right + 1] >= heights[i]) {
                right++;
            }
            result = Math.max(result, (right - left + 1) * heights[i]);
            // System.out.println("left, right, result are " + left + ", " + right + ", " + result);
        }
        return result;
    }
}

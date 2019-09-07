// 360. Sort Transformed Array
// DescriptionHintsSubmissionsDiscussSolution
// Given a sorted array of integers nums and integer values a, b and c. Apply a quadratic function of the form f(x) = ax2 + bx + c to each element x in the array.
//
// The returned array must be in sorted order.
//
// Expected time complexity: O(n)
//
// Example 1:
//
// Input: nums = [-4,-2,2,4], a = 1, b = 3, c = 5
// Output: [3,9,15,33]
// Example 2:
//
// Input: nums = [-4,-2,2,4], a = -1, b = 3, c = 5
// Output: [-23,-5,1,7]
// Seen this question in a real interview before?
// Difficulty:Medium


class Solution {
    public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
        if (nums == null || nums.length == 0) {
            return nums;
        }

        // return mytry(nums, a, b, c);

        return method2(nums, a, b, c);
    }

    private int[] method2(int[] nums, int a, int b, int c) {
        // 如果要做到 O(n)， 那么肯定是一边比较一边排序， 这里用到 2 pointers + merge sort, 观察 transform function 是一个二次函数， a 的正负决定了开口方向
        // a > 0, 开口向上， 两端的值比中间的大
        // a < 0, 开口向下， 中间的值比两端的大
        // 那么就可以用 2 pointers 从两端往中间扫， 谁大／小谁出列
        // a = 0 不影响上述过程， 因为 b 只决定一次函数的斜率， 依然是 x 越大， 值越大
        int n = nums.length;
        int left = 0;
        int right = n - 1;
        int i = a > 0 ? n - 1 : 0;
        int[] result = new int[n];
        while (left <= right) {
            int leftVal = function(nums[left], a, b, c);
            int rightVal = function(nums[right], a, b, c);
            if (a > 0) {
                if (leftVal >= rightVal) {
                    result[i--] = leftVal;
                    left++;
                } else {
                    result[i--] = rightVal;
                    right--;
                }
            } else {
                if (leftVal <= rightVal) {
                    result[i++] = leftVal;
                    left++;
                } else {
                    result[i++] = rightVal;
                    right--;
                }
            }
        }
        return result;
    }

    private int[] mytry(int[] nums, int a, int b, int c) {
        // straightforward: O(nlogn) time and O(n) space
        int n = nums.length;
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            result[i] = function(nums[i], a, b, c);
        }
        Arrays.sort(result);
        // nums = Arrays.copyOf(result);
        return result;
    }
    private int function(int num, int a, int b, int c) {
        return a * num * num + b * num + c;
    }
}

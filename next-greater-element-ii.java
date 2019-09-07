// 503. Next Greater Element II
// DescriptionHintsSubmissionsDiscussSolution
// Given a circular array (the next element of the last element is the first element of the array), print the Next Greater Number for every element. The Next Greater Number of a number x is the first greater number to its traversing-order next in the array, which means you could search circularly to find its next greater number. If it doesn't exist, output -1 for this number.
//
// Example 1:
// Input: [1,2,1]
// Output: [2,-1,2]
// Explanation: The first 1's next greater number is 2;
// The number 2 can't find next greater number;
// The second 1's next greater number needs to search circularly, which is also 2.
// Note: The length of given array won't exceed 10000.
//
// Seen this question in a real interview before?
// Difficulty:Medium


class Solution {
    public int[] nextGreaterElements(int[] nums) {
        if (nums == null || nums.length == 0) {
            return nums;
        }

        // like I, but first extend the original array to double length with repeated value, and search for index and next greater element - O(n ^ 2) time
        // 延长原数组有两种方式：
        // 1: 建立一个新的数组， 其长度是原数组的两倍
        // 2: 采用取余的方式
        // return method1_extension1_array(nums);
        // return method1_extension2_remain(nums);

        // method 2: use Stack - O(n) time
        return method2_stack(nums);
    }

    private int[] method2_stack(int[] nums) {
        int n = nums.length;
        Stack<Integer> stack = new Stack<>(); // record the index
        // initialization the stack
        for (int i = n - 1; i >= 0; i--) {
            // attentio: push from end to start, then we could traverse from start to end
            stack.push(i); // attention: push INDEX into stack rather than values in array
        }
        int[] result = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            result[i] = -1; // initialization
            while (!stack.isEmpty() && nums[stack.peek()] <= nums[i]) {
                stack.pop();
            }
            if (!stack.isEmpty()) {
                result[i] = nums[stack.peek()];
            }
            stack.push(i);
        }
        return result;
    }

    private int[] method1_extension1_array(int[] nums) {
        int n = nums.length;
        int[] temp = new int[n * 2];
        // construct a array with length of 2 times of original one
        for (int i = 0; i < n * 2; i++) {
            temp[i] = nums[i % n];
        }
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            result[i] = -1;
            for (int j = i + 1; j < i + n; j++) {
                if (temp[j] > nums[i]) {
                    result[i] = temp[j];
                    break;
                }
            }
        }
        return result;
    }
    private int[] method1_extension2_remain(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            result[i] = -1;
            for (int j = i + 1; j < i + n; j++) {
                if (nums[j % n] > nums[i]) {
                    result[i] = nums[j % n];
                    break;
                }
            }
        }
        return result;
    }
}

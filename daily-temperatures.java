// 739. Daily Temperatures
// DescriptionHintsSubmissionsDiscussSolution
// Given a list of daily temperatures T, return a list such that, for each day in the input, tells you how many days you would have to wait until a warmer temperature. If there is no future day for which this is possible, put 0 instead.
//
// For example, given the list of temperatures T = [73, 74, 75, 71, 69, 72, 76, 73], your output should be [1, 1, 4, 2, 1, 1, 0, 0].
//
// Note: The length of temperatures will be in the range [1, 30000]. Each temperature will be an integer in the range [30, 100].


class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        if (temperatures == null || temperatures.length == 0) {
            return new int[0];
        }

        // return my_try(temperatures);

        // 这种一个个往后面找的类型， 我们可以用 stack 来做
        return method2_stack(temperatures);
    }

    private int[] method2_stack(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && nums[i] > nums[stack.peek()]) {
                // 注意比较的数组中的 value 而不是 index
                int prev = stack.pop(); // pop 出来的位置对应的下一个大的就是当前 i 指向的地方
                result[prev] = i - prev; // 保存的是 index 的距离
            }
            stack.push(i);
        }
        return result;
    }

    private int[] my_try(int[] nums) {
        // brute method
        int n = nums.length;
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (nums[j] > nums[i]) {
                    result[i] = j - i;
                    break;
                }
            }
        }
        return result;
    }
}

// 66. Plus One
// DescriptionHintsSubmissionsDiscussSolution
// Given a non-empty array of digits representing a non-negative integer, plus one to the integer.
//
// The digits are stored such that the most significant digit is at the head of the list, and each element in the array contain a single digit.
//
// You may assume the integer does not contain any leading zero, except the number 0 itself.
//
// Example 1:
//
// Input: [1,2,3]
// Output: [1,2,4]
// Explanation: The array represents the integer 123.
// Example 2:
//
// Input: [4,3,2,1]
// Output: [4,3,2,2]
// Explanation: The array represents the integer 4321.


class Solution {
    public int[] plusOne(int[] digits) {
        if (digits == null) {
            return digits;
        }
        if (digits.length == 0) {
            int[] result = {1};
            return result;
        }

        // return my_try(digits);

        // we can do more simply
        return method2(digits);
    }

    private int[] method2(int[] nums) {
        // 只要在从后往前的过程中， 哪怕有进位， 但是只要碰到一个不是 9 的， 那么 + 1 就可以返回了， 否则就一直变为 0
        int n = nums.length;
        for (int i = n - 1; i >= 0; i--) {
            if (nums[i] < 9) {
                nums[i]++;
                return nums;
            } else {
                nums[i] = 0;
            }
        }
        // if we do not return in for loop, it means we can do carrying for all digits, so we just need to add leading 1
        int[] result = new int[n + 1];
        result[0] = 1;
        return result;
    }

    private int[] my_try(int[] digits) {
        int n = digits.length;
        // case 1: do not need to carry
        if (digits[n - 1] < 9) {
            digits[n - 1] += 1;
            return digits;
        }
        // case 2: need to carry
        // use stack to record digits since we may exceed original length, like [0] => [1]
        Stack<Integer> stack = new Stack<>();
        stack.push(0); // last one
        int index = n - 2; // start from last 2nd one
        int carry = 1;
        while (index >= 0) {
            int sum = digits[index] + carry;

            stack.push(sum % 10);
            carry = sum / 10;
            // System.out.println(sum + " " + (sum % 10) + " " + carry);
            index--;
        }
        if (carry != 0) {
            stack.push(carry);
        }
        int[] result = new int[stack.size()];
        int j = 0;
        // for (; j < stack.size(); j++) {
        //     result[j] = stack.pop();
        // }
        // stack SIZE is changing along with pop()
        while (!stack.isEmpty()) {
            System.out.print(stack.peek() + " ");
            result[j++] = stack.pop();
        }
        return result;
    }
}

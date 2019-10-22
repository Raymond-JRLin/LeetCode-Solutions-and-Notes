// 224. Basic Calculator
// DescriptionHintsSubmissionsDiscussSolution
//
// Implement a basic calculator to evaluate a simple expression string.
//
// The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces .
//
// Example 1:
//
// Input: "1 + 1"
// Output: 2
//
// Example 2:
//
// Input: " 2-1 + 2 "
// Output: 3
//
// Example 3:
//
// Input: "(1+(4+5+2)-3)+(6+8)"
// Output: 23
//
// Note:
//
//     You may assume that the given expression is always valid.
//     Do not use the eval built-in library function.
//
// Seen this question in a real interview before?
//
//     Difficulty:Hard


class Solution {
    public int calculate(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        return method1(s);
    }

    private int method1(String s) {
        int result = 0;
        int sign = 1;
        int curr = 0;
        Stack<Integer> stack = new Stack<>(); // 因为有括号， 所以要先存一下
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '+') {
                // 把之前的结果加进 result
                result += sign * curr;
                curr = 0;
                sign = 1;
            } else if (c == '-') {
                // 把之前的结果加进 result， mark sign 为负
                result += sign * curr;
                curr = 0;
                sign = -1;
            } else if (Character.isDigit(c)) {
                // 数字的话， 直接加就好
                curr = curr * 10 + (c - '0');
            } else if (c == '(') {
                // 左括号， 把之前的 result 连同符号一起放进 stack
                stack.push(result);
                stack.push(sign);
                sign = 1;
                result = 0;
            } else if (c == ')') {
                // 结束了括号里面， 把当前的数加进 result， 并一起加入 stack 之前存的
                result += sign * curr;
                curr = 0;
                result *= stack.pop(); // * sign
                result += stack.pop(); // add previous result
            }
        }
        if (curr != 0) {
            result += sign * curr;
        }
        return result;
    }
}

// 224. Basic Calculator
// DescriptionHintsSubmissionsDiscussSolution
// Implement a basic calculator to evaluate a simple expression string.
//
// The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces .
//
// Example 1:
//
// Input: "1 + 1"
// Output: 2
// Example 2:
//
// Input: " 2-1 + 2 "
// Output: 3
// Example 3:
//
// Input: "(1+(4+5+2)-3)+(6+8)"
// Output: 23
// Note:
// You may assume that the given expression is always valid.
// Do not use the eval built-in library function.


class Solution {
    public int calculate(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        return method1(s);
    }

    private int method1(String s) {
        // ref: https://leetcode.com/problems/basic-calculator/discuss/62362/JAVA-Easy-Version-To-Understand!!!!!
        Stack<Integer> stack = new Stack<>();
        int result = 0;
        int sign = 1;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                int num = c - '0';
                // 要去查下一个 char， 不然查看当前 i 的话， 不是 digit 的时候就会被跳过了
                while (i + 1 < s.length() && Character.isDigit(s.charAt(i + 1))) {
                    num = num * 10 + (s.charAt(i + 1) - '0');
                    i++;
                }
                // 找到当前的数字, 并加到结果中
                result += num * sign;
            } else if (c == '+') {
                sign = 1;
            } else if (c == '-') {
                sign = -1;
            } else if (c == '(') {
                // 左括号： 有更高优先级的运算， 把之前算好的结果和符号 push 到 stack 中
                stack.push(result);
                stack.push(sign);
                result = 0;
                sign = 1;
            } else if (c == ')') {
                // 右括号： 更高优先级的运算结束， 加上之前的运算结果
                result = result * stack.pop() + stack.pop();
            }
        }
        return result;
    }
}

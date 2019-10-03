// 32. Longest Valid Parentheses
// DescriptionHintsSubmissionsDiscussSolution
// Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.
//
// Example 1:
//
// Input: "(()"
// Output: 2
// Explanation: The longest valid parentheses substring is "()"
// Example 2:
//
// Input: ")()())"
// Output: 4
// Explanation: The longest valid parentheses substring is "()()"
// Seen this question in a real interview before?
// Difficulty:Hard


class Solution {
    public int longestValidParentheses(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        // return method1(s);

        // return method2(s);

        return method3(s);
    }

    private int method3(String s) {
        // another DP
        int n = s.length();
        // definition
        int[] f = new int[n];
        // initialzation
        // DP
        for (int i = 1; i < n; i++) {
            if (s.charAt(i) == '(') {
                continue;
            }
            // ')'
            if (s.charAt(i - 1) == '(') {
                f[i] = (i >= 2 ? f[i - 2] : 0) + 2;
            } else {
                // 前一个是 ')', 往前看 i - 1 相连的一片的前一位是否是 '(' 能和当前 i 匹配
                if (i - f[i - 1] - 1 >= 0 && s.charAt(i - f[i - 1] - 1) == '(') {
                    f[i] = f[i - 1] + 2;
                    // 如果可以， 看与当前 i 匹配的再前一位是否能够连起来
                    f[i] += (i - f[i - 1] >= 2 ? f[i - f[i - 1] - 2] : 0);
                }
            }
        }
        // result
        int result = 0;
        for (int num : f) {
            result = Math.max(result, num);
        }
        return result;
    }

    private int method2(String s) {
        // DP
        int n = s.length();
        // definition
        int[] f = new int[n]; // f[i] = longest valid parantheses ending at i
        // initialization
        int left = 0; // count '('
        // DP
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '(') {
                left++;
            } else if (left > 0) {
                // ')' && 前面有 '('
                f[i] = f[i - 1] + 2;
                left--;
                // 看是否能和前面的连起来
                if (i - f[i] >= 0) {
                    f[i] += f[i - f[i]];
                }
            }
        }
        // result
        int result = 0;
        for (int num : f) {
            result = Math.max(result, num);
        }
        return result;
    }

    private int method1(String s) {
        int n = s.length();
        // 用 stack 存不能够匹配的 index， 每个不能匹配之间都是能匹配的
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.push(i);
            } else {
                // ')'
                if (!stack.isEmpty() && s.charAt(stack.peek()) == '(') {
                    // pair
                    stack.pop();
                } else {
                    stack.push(i);
                }
            }
        }
        if (stack.isEmpty()) {
            return n;
        }
        int result = 0;
        int end = n;
        while (!stack.isEmpty()) {
            int curr = stack.pop();
            result = Math.max(result, end - 1 - curr);
            end = curr;
        }
        result = Math.max(result, end - 0);
        return result;
    }
}

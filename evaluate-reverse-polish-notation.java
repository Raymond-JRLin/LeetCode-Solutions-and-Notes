// 150
// Evaluate the value of an arithmetic expression in Reverse Polish Notation.
//
// Valid operators are +, -, *, /. Each operand may be an integer or another expression.
//
// Note:
//
// Division between two integers should truncate toward zero.
// The given RPN expression is always valid. That means the expression would always evaluate to a result and there won't be any divide by zero operation.
// Example 1:
//
// Input: ["2", "1", "+", "3", "*"]
// Output: 9
// Explanation: ((2 + 1) * 3) = 9
// Example 2:
//
// Input: ["4", "13", "5", "/", "+"]
// Output: 6
// Explanation: (4 + (13 / 5)) = 6
// Example 3:
//
// Input: ["10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"]
// Output: 22
// Explanation:
//   ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
// = ((10 * (6 / (12 * -11))) + 17) + 5
// = ((10 * (6 / -132)) + 17) + 5
// = ((10 * 0) + 17) + 5
// = (0 + 17) + 5
// = 17 + 5
// = 22


class Solution {
    public int evalRPN(String[] tokens) {
        if (tokens == null || tokens.length == 0) {
            return 0;
        }

        // return mytry(tokens);

        return mytry2(tokens);
    }

    private int mytry2(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        Set<String> signs = new HashSet<>(Arrays.asList("*", "/", "+", "-"));
        for (String token : tokens) {
            if (signs.contains(token)) {
                stack.push(calculate2(token, stack.pop(), stack.pop()));
            } else {
                stack.push(Integer.valueOf(token));
            }
        }
        return stack.pop();
    }
    private int calculate2(String sign, int a, int b) {
        if (sign.equals("+")) {
            return b + a;
        } else if (sign.equals("-")) {
            return b - a;
        } else if (sign.equals("*")) {
            return b * a;
        } else {
            return b / a;
        }
    }

    private int mytry(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (String token : tokens) {
            char c = token.charAt(0);
            if (token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/")) {
                int num1 = stack.pop();
                int num2 = stack.pop();
                stack.push(calculate(c, num1, num2));
            } else {
                stack.push(Integer.valueOf(token));
            }
        }
        return stack.pop();
    }
    private int calculate(char c, int num1, int num2) {
        if (c == '+') {
            return num2 + num1;
        } else if (c == '-') {
            return num2 - num1;
        } else if (c == '*') {
            return num2 * num1;
        } else {
            return num2 / num1;
        }
    }
}

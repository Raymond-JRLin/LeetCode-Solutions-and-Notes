// 227. Basic Calculator II
// DescriptionHintsSubmissionsDiscussSolution
// Implement a basic calculator to evaluate a simple expression string.
//
// The expression string contains only non-negative integers, +, -, *, / operators and empty spaces . The integer division should truncate toward zero.
//
// Example 1:
//
// Input: "3+2*2"
// Output: 7
// Example 2:
//
// Input: " 3/2 "
// Output: 1
// Example 3:
//
// Input: " 3+5 / 2 "
// Output: 5
// Note:
//
// You may assume that the given expression is always valid.
// Do not use the eval built-in library function.
// Seen this question in a real interview before?
// Difficulty:Medium


class Solution {
    public int calculate(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }

        // return method1(s);

        return method2(s);
    }

    private int method2(String s) {
        // 另外我们可以用一个 prev variable 去记录上一个数字， 如果是乘除， 先还回去， 然后做运算
        int n = s.length();
        int result = 0;
        int prev = 0;
        char sign = '+';
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == ' ') {
                continue;
            }
            // check current number
            int curr = 0;
            while (i < n && Character.isDigit(s.charAt(i))) {
                curr = curr * 10 + (s.charAt(i) - '0');
                i++;
            }
            // deal with prev part if we met a sign
            if (sign == '+') {
                result += prev;
                prev = curr;
            } else if (sign =='-') {
                result += prev;
                prev = -curr;
            } else if (sign == '*') {
                prev *= curr;
            } else if (sign == '/') {
                prev /= curr;
            }
            if (i < n) {
                sign = s.charAt(i);
            }

        }
        return result + prev;
    }

    private int method1(String s) {
        // 因为乘除优先级更高， 所以不知道先做哪个运算
        // 所以把所有的数字在符号的位置 split， 拿到一个数就 push 进 stack， 之后利用 stack 的特性做运算
        // 这样 stack 里面放的是每个运算部分的结果， 之后取和
        Stack<Integer> stack = new Stack<>();
        int curr = 0;
        char sign = '+';
        for (char c : s.toCharArray()) {
            if (c == ' ') {
                continue;
            }
            if (Character.isDigit(c)) {
                curr = curr * 10 + (c - '0');
            } else {
                compute(stack, curr, sign);
                sign = c;
                curr = 0;
            }
        }
        compute(stack, curr, sign);

        int result = 0;
        while (!stack.isEmpty()) {
            result += stack.pop();
        }
        return result;
    }
    private void compute(Stack<Integer> stack, int curr, char sign) {
        if (sign == '+') {
            stack.push(curr);
        } else if (sign == '-') {
            stack.push(-curr);
        } else if (sign == '*') {
            stack.push(stack.pop() * curr);
        } else {
            stack.push(stack.pop() / curr);
        }
    }
}

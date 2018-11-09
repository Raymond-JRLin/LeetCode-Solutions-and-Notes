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


class Solution {
    public int calculate(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }

        // return method1(s);

        return method2(s);
    }

    private int method2(String s) {
        // O(N) time and O(1) space with 1 variable
        int n = s.length();
        int result = 0;
        char sign = '+';
        int prev = 0; // 上一个结果
        int num = 0;
        // 要注意这里 prev 是上一个结果， e.g. 2+5/2, 当查到 5 的时候， prev 是 3， result 还是 0， 相当于落后一步
        // 所以在最后得到总的 result 的时候， 要再补上 prev， 也同样如此做更高优先级的乘除法的时候， 可以处理并且要处理的就是 prev， 保留 result 不变
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            // System.out.println("now check: " + c);
            if (c == ' ') {
                continue;
            }
            // 拿到当前一整个数
            if (Character.isDigit(c)) {
                num = c - '0';
                // 注意这里要查的是 i + 1, 即下一个字符， 因为我们用的 for 循环会 + 1， 就会跳过一个
                while (i + 1 < n && Character.isDigit(s.charAt(i + 1))) {
                    num = num * 10 + (s.charAt(i + 1) - '0');
                    i++;
                }
            }
            // System.out.println("current num : " + num);
            if (sign == '+') {
                result += prev;
                prev = num;
            } else if (sign == '-') {
                result += prev; // 用的依然是 +, 因为我们变了 prev 为负的 num
                prev = -num;
            } else if (sign == '*') {
                prev *= num;
            } else if (sign == '/') {
                prev /= num;
            }
            // System.out.println("result and prev are : " + result + ", " + prev);
            if (i < n) {
                sign = s.charAt(i);
            }
        }
        return result + prev;
    }

    private int method1(String s) {
        // Stack
        // O(N) time and space
        int n = s.length();
        Stack<Integer> stack = new Stack<>();
        char sign = '+';
        int curr = 0;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == ' ') {
                continue;
            }
            if (Character.isDigit(c)) {
                curr = curr * 10 + (c -'0');
            } else {
                calculate(stack, sign, curr);
                sign = c;
                curr = 0;
            }
        }
        calculate(stack, sign, curr); // last result
        int result = 0;
        while (!stack.isEmpty()) {
            result += stack.pop();
        }
        return result;
    }
    private void calculate(Stack<Integer> stack, char sign, int curr) {
        if (sign == '+') {
            stack.push(curr);
        } else if (sign == '-') {
            stack.push(-curr);
        } else if (sign == '*') {
            stack.push(stack.pop() * curr);
        } else if (sign == '/') {
            stack.push(stack.pop() / curr);
        }
    }
}

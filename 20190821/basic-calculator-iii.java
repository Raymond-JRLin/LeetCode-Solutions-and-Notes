// 772. Basic Calculator III
// DescriptionHintsSubmissionsDiscussSolution
//
// Implement a basic calculator to evaluate a simple expression string.
//
// The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces .
//
// The expression string contains only non-negative integers, +, -, *, / operators , open ( and closing parentheses ) and empty spaces . The integer division should truncate toward zero.
//
// You may assume that the given expression is always valid. All intermediate results will be in the range of [-2147483648, 2147483647].
//
// Some examples:
//
// "1 + 1" = 2
// " 6-4 / 2 " = 4
// "2*(5+5*2)/3+(6/2+8)" = 21
// "(2+6* 3+5- (3*14/7+2)*5)+3"=-12
//
//
//
// Note: Do not use the eval built-in library function.
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
        int n = s.length();
        Stack<Integer> nums = new Stack<>();
        Stack<Character> operators = new Stack<>();

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == ' ') {
                continue;
            }
            if (c == '(') {
                operators.push(c);
            } else if (Character.isDigit(c)) {
                int num = c - '0';
                while (i + 1 < n && Character.isDigit(s.charAt(i + 1))) {
                    num = num * 10 + (s.charAt(i + 1) - '0');
                    i++;
                }
                nums.push(num);
            } else if (c == ')') {
                while (!operators.isEmpty() && operators.peek() != '(') {
                    char ope = operators.pop();
                    int num2 = nums.pop();
                    int num1 = nums.pop();
                    int curr = compute(ope, num1, num2);
                    nums.push(curr);
                }
                operators.pop(); // pop '('
            } else {
                // operators
                while (!operators.isEmpty() && toCompute(c, operators.peek())) {
                    char ope = operators.pop();
                    int num2 = nums.pop();
                    int num1 = nums.pop();
                    int curr = compute(ope, num1, num2);
                    nums.push(curr);
                }
                // 题目说没有负数， 但其实出现了， 这里要处理一下负数的情况
                // 总的来说， 做法就是先补一个 0 进去
                if (c == '-') {
                    // 1. 如果开头是一个符号
                    if (nums.isEmpty()) {
                        nums.push(0);
                    } else {
                        // 2. 中间是负数， 那么中间是负数要求括号括起来， 所以往前找是否有括号， 来决定是负数还是减号
                        int j = i - 1;
                        while (j >= 0 && s.charAt(j) == ' ') {
                            j--;
                        }
                        if (s.charAt(j) == '(') {
                            nums.push(0);
                        }
                    }
                }
                operators.push(c);
            }
        }
        while (!operators.isEmpty()) {
            char ope = operators.pop();
            int num2 = nums.pop();
            int num1 = nums.pop();
            int curr = compute(ope, num1, num2);
            nums.push(curr);
        }
        return nums.peek();
    }
    private boolean toCompute(char curr, char prev) {
        // 括号的留给遇到 closing parentheses 去解决
        if (prev == '(' || prev == ')') {
            return false;
        }
        // 当前遇到的运算符 order 更高， 那么先不算， 得先算现在 order 更高的运算符， 它的下一位数字还没遇到
        if ((curr == '*' || curr == '/') && (prev == '+' || prev == '-')) {
            return false;
        }
        return true;
    }
    private int compute(char ope, int num1, int num2) {
        if (ope == '+') {
            return num1 + num2;
        } else if (ope == '-') {
            return num1 - num2;
        } else if (ope == '*') {
            return num1 * num2;
        } else {
            return num1 / num2;
        }
    }
}

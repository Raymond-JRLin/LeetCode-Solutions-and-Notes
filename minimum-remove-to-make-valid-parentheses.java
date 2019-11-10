// 1249. Minimum Remove to Make Valid Parentheses
//
//     User Accepted: 1506
//     User Tried: 1813
//     Total Accepted: 1534
//     Total Submissions: 2988
//     Difficulty: Medium
//
// Given a string s of '(' , ')' and lowercase English characters.
//
// Your task is to remove the minimum number of parentheses ( '(' or ')', in any positions ) so that the resulting parentheses string is valid and return any valid string.
//
// Formally, a parentheses string is valid if and only if:
//
//     It is the empty string, contains only lowercase characters, or
//     It can be written as AB (A concatenated with B), where A and B are valid strings, or
//     It can be written as (A), where A is a valid string.
//
//
//
// Example 1:
//
// Input: s = "lee(t(c)o)de)"
// Output: "lee(t(c)o)de"
// Explanation: "lee(t(co)de)" , "lee(t(c)ode)" would also be accepted.
//
// Example 2:
//
// Input: s = "a)b(c)d"
// Output: "ab(c)d"
//
// Example 3:
//
// Input: s = "))(("
// Output: ""
// Explanation: An empty string is also valid.
//
// Example 4:
//
// Input: s = "(a(b(c)d)"
// Output: "a(b(c)d)"
//
//
//
// Constraints:
//
//     1 <= s.length <= 10^5
//     s[i] is one of  '(' , ')' and lowercase English letters.
//


class Solution {
    public String minRemoveToMakeValid(String s) {
        if (s == null || s.isEmpty()) {
            return s;
        }

        return method1(s);
    }

    private String method1(String s) {
        // 实际上我觉得这道题只和括号有关， 抹掉不能够匹配的括号即可
        // 比较巧妙的是把要抹掉的位置用其他的 char 先替换一下
        char[] array = s.toCharArray();
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < array.length; i++) {
            char curr = array[i];
            if (curr == '(') {
                stack.push(i);
            } else if (curr == ')') {
                if (!stack.isEmpty() && array[stack.peek()] == '(') {
                    stack.pop();
                } else {
                    // 别忘了如果右括号不能够匹配， 也得 mark
                    array[i] = '#';
                }
            }
        }
        while (!stack.isEmpty()) {
            array[stack.pop()] = '#';
        }
        StringBuilder sb = new StringBuilder();
        for (char c : array) {
            if (c == '#') {
                continue;
            }
            sb.append(c);
        }
        return sb.toString();
    }
}

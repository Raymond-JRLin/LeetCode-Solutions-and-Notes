// 1190. Reverse Substrings Between Each Pair of Parentheses
// User Accepted: 2270
// User Tried: 2688
// Total Accepted: 2316
// Total Submissions: 4338
// Difficulty: Medium
// Given a string s that consists of lower case English letters and brackets.
//
// Reverse the strings in each pair of matching parentheses, starting from the innermost one.
//
// Your result should not contain any bracket.
//
//
//
//
//
// Example 1:
//
// Input: s = "(abcd)"
// Output: "dcba"
// Example 2:
//
// Input: s = "(u(love)i)"
// Output: "iloveu"
// Example 3:
//
// Input: s = "(ed(et(oc))el)"
// Output: "leetcode"
// Example 4:
//
// Input: s = "a(bcdefghijkl(mno)p)q"
// Output: "apmnolkjihgfedcbq"
//
//
// Constraints:
//
// 0 <= s.length <= 2000
// s only contains lower case English characters and parentheses.
// It's guaranteed that all parentheses are balanced.


class Solution {
    public String reverseParentheses(String s) {
        // 题意没理解清楚， Reverse the strings in each pair of matching parentheses, starting from the innermost one.
        // 是说从最里面的括号开始， 每层括号都要 reverse， 拿出来再放进去， 连同下一层括号一起再 reverse
        // 很显然要用到 stack

        if (s == null || s.length() == 0) {
            return s;
        }

        return method1(s);
    }

    private String method1(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c != ')') {
                stack.push(c);
            } else {
                List<Character> list = new ArrayList<>();
                while (!stack.isEmpty() && stack.peek() != '(') {
                    list.add(stack.pop());
                }
                if (!stack.isEmpty()) {
                    // '('
                    stack.pop();
                }
                for (char ch : list) {
                    stack.push(ch);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.reverse().toString();
    }
}

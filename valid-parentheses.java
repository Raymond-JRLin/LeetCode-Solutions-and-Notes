// 20. Valid Parentheses
// DescriptionHintsSubmissionsDiscussSolution
// Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
//
// An input string is valid if:
//
// Open brackets must be closed by the same type of brackets.
// Open brackets must be closed in the correct order.
// Note that an empty string is also considered valid.
//
// Example 1:
//
// Input: "()"
// Output: true
// Example 2:
//
// Input: "()[]{}"
// Output: true
// Example 3:
//
// Input: "(]"
// Output: false
// Example 4:
//
// Input: "([)]"
// Output: false
// Example 5:
//
// Input: "{[]}"
// Output: true


class Solution {
    public boolean isValid(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }

        // return my_try(s);

        // another smarter version by stack
        return method2(s);
    }

    private boolean method2(String s) {
        // very smart， 放右半边进 stack， 然后进行匹配消除
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(')');
            } else if (c == '{') {
                stack.push('}');
            } else if ( c == '[') {
                stack.push(']');
            } else if (stack.isEmpty() || stack.pop() != c) {
                // 这里是 pop() 比较， 如果一样的话就出栈了， 继续比较下一个
                return false;
            }
        }
        return stack.isEmpty();
    }

    private boolean my_try(String s) {
        // first thing into my mind: stack
        // 很正常的按照顺序去匹配
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    // attention: we use stack.peek() following, so we should check if it's valid first
                    // morover: if it's empty right now, then there is nothing in stack to pair with right parenthesis, so it's false directly
                    return false;
                } else if (c == ')' && stack.peek() == '(' || c == '}' && stack.peek() == '{' || c == ']' && stack.peek() == '[') {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}

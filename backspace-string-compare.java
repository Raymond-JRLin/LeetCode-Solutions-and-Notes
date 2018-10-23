// 844
// Given two strings S and T, return if they are equal when both are typed into empty text editors. # means a backspace character.
//
// Example 1:
//
// Input: S = "ab#c", T = "ad#c"
// Output: true
// Explanation: Both S and T become "ac".
// Example 2:
//
// Input: S = "ab##", T = "c#d#"
// Output: true
// Explanation: Both S and T become "".
// Example 3:
//
// Input: S = "a##c", T = "#a#c"
// Output: true
// Explanation: Both S and T become "c".
// Example 4:
//
// Input: S = "a#c", T = "b"
// Output: false
// Explanation: S becomes "c" while T becomes "b".
// Note:
//
// 1 <= S.length <= 200
// 1 <= T.length <= 200
// S and T only contain lowercase letters and '#' characters.
// Follow up:
//
// Can you solve it in O(N) time and O(1) space?


class Solution {
    public boolean backspaceCompare(String S, String T) {
        if ((S == null || S.isEmpty()) && (T == null || T.isEmpty())) {
            return true;
        }
        if (S == null || S.isEmpty()) {
            return false;
        }
        if (T == null || T.isEmpty()) {
            return false;
        }

        // return mytry(S, T);

        return method2(S, T);
    }

    private boolean method2(String s, String t) {
        // 如果要不用额外空间， 那就不能借助 stack 了， 那就没办法记住前面的 char， 那么可以考虑从后往前， 如果碰到 # 就跳过下一个 char
        return getString2(s).equals(getString2(t));
    }
    private String getString2(String s) {
        StringBuilder sb = new StringBuilder();
        int back = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '#') {
                back++; // count #
            } else {
                if (back > 0) {
                    back--; // do not add current char, use back
                } else {
                    sb.append(s.charAt(i)); // add current char
                }
            }
        }
        return sb.toString();
    }

    private boolean mytry(String s, String t) {
        // O(N) time and O(N) space
        String a = getString(s);
        String b = getString(t);
        return a.equals(b);
    }
    private String getString(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '#') {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else {
                stack.push(c);
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.toString();
    }
}

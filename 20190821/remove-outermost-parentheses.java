// 1021. Remove Outermost Parentheses
// DescriptionHintsSubmissionsDiscussSolution
//
// A valid parentheses string is either empty (""), "(" + A + ")", or A + B, where A and B are valid parentheses strings, and + represents string concatenation.  For example, "", "()", "(())()", and "(()(()))" are all valid parentheses strings.
//
// A valid parentheses string S is primitive if it is nonempty, and there does not exist a way to split it into S = A+B, with A and B nonempty valid parentheses strings.
//
// Given a valid parentheses string S, consider its primitive decomposition: S = P_1 + P_2 + ... + P_k, where P_i are primitive valid parentheses strings.
//
// Return S after removing the outermost parentheses of every primitive string in the primitive decomposition of S.
//
//
//
// Example 1:
//
// Input: "(()())(())"
// Output: "()()()"
// Explanation:
// The input string is "(()())(())", with primitive decomposition "(()())" + "(())".
// After removing outer parentheses of each part, this is "()()" + "()" = "()()()".
//
// Example 2:
//
// Input: "(()())(())(()(()))"
// Output: "()()()()(())"
// Explanation:
// The input string is "(()())(())(()(()))", with primitive decomposition "(()())" + "(())" + "(()(()))".
// After removing outer parentheses of each part, this is "()()" + "()" + "()(())" = "()()()()(())".
//
// Example 3:
//
// Input: "()()"
// Output: ""
// Explanation:
// The input string is "()()", with primitive decomposition "()" + "()".
// After removing outer parentheses of each part, this is "" + "" = "".
//
//
//
// Note:
//
//     S.length <= 10000
//     S[i] is "(" or ")"
//     S is a valid parentheses string
//
//
// Seen this question in a real interview before?
//
//     Difficulty:Easy


class Solution {
    public String removeOuterParentheses(String S) {

        // return mytry(S);

        return method2(S);
    }

    private String method2(String s) {
        // 不要想太复杂了
        int n = s.length();
        StringBuilder sb = new StringBuilder();
        int count = 0;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            // 成对出现的情况则不加
            if (c == '(' && count != 0) {
                sb.append(c);
            }
            if (c == ')' && count != 1) {
                sb.append(c);
            }
            count += c == '(' ? 1 : -1;
        }
        return sb.toString();
    }

    private String mytry(String s) {
        return dfs(s);
    }
    private String dfs(String s) {
        if (s == null || s.isEmpty()) {
            return s;
        }
        int i = 0;
        int count = 0;
        while (i <= s.length()) {
            count += s.charAt(i++) == '(' ? 1 : -1;
            if (count == 0) {
                break;
            }
        }
        if (i <= s.length()) {
            return s.substring(1, i - 1) + dfs(s.substring(i));
        } else if (count == 0) {
            return s.substring(1, i - 1);
        } else {
            return s;
        }
    }
}

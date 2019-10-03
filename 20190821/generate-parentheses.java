// 22. Generate Parentheses
// DescriptionHintsSubmissionsDiscussSolution
// Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
//
// For example, given n = 3, a solution set is:
//
// [
//   "((()))",
//   "(()())",
//   "(())()",
//   "()(())",
//   "()()()"
// ]


class Solution {
    public List<String> generateParenthesis(int n) {
        if (n <= 0) {
            return new ArrayList<>();
        }

        List<String> result = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        dfs(n, n, n, sb, result);
        return result;
    }
    private void dfs(int n, int left, int right, StringBuilder sb, List<String> result) {
        // 关键之处在于想清楚： 什么时候加左括号和右括号
        // 只要左括号还有余额就可以加
        // 但是右括号必须要少于左括号的时候才可以
        if (left == 0 && right == 0) {
            result.add(sb.toString());
            return;
        }
        if (left > 0) {
            sb.append('(');
            dfs(n, left - 1, right, sb, result);
            sb.deleteCharAt(sb.length() - 1);
        }

        if (left < right) {
            sb.append(')');
            dfs(n, left, right - 1, sb, result);
            sb.deleteCharAt(sb.length() - 1);
        }

    }
}

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

        // 生成所有组合， 那肯定要用 DFS
        // return my_try(n);

        // iteration
        return method2(n);
    }

    private List<String> method2(int n) {
        List<List<String>> result = new ArrayList<>();
        List<String> temp = new ArrayList<>();
        temp.add("");
        result.add(temp);
        for (int i = 1; i <= n; i++) {
            List<String> list = new ArrayList<>();
            for (int j = 0; j < i; j++) {
                for (String first : result.get(j)) {
                    for (String second : result.get(i - 1 - j)) {
                        list.add("(" + first + ")" + second);
                    }
                }
            }
            result.add(list);
        }
        return result.get(result.size() - 1);
    }

    private List<String> my_try(int n) {
        List<String> result = new ArrayList<>();
        dfs(result, "", n, 0, 0);
        return result;
    }
    private void dfs(List<String> result, String string, int n, int left, int right) {
        // 考虑什么情况下可以加左括号， 什么时候可以加右括号： 只要左边少于 n 就可以加左括号， 左边多于右边才可以加右括号
        if (left == n && right == n) {
            result.add(string);
            return;
        }
        // 如果在外面改变 string， 那么就要 backtracking 取 substring 并且减少 left 和 right 的值, 在里面就直接往下传并不改变 string 的值
        if (left < n) {
            dfs(result, string + "(", n, left + 1, right);
        }
        if (right < left) {
            dfs(result, string + ")", n, left, right + 1);
        }
    }
}

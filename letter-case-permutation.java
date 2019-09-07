// 784. Letter Case Permutation
// DescriptionHintsSubmissionsDiscussSolution
// Given a string S, we can transform every letter individually to be lowercase or uppercase to create another string.  Return a list of all possible strings we could create.
//
// Examples:
// Input: S = "a1b2"
// Output: ["a1b2", "a1B2", "A1b2", "A1B2"]
//
// Input: S = "3z4"
// Output: ["3z4", "3Z4"]
//
// Input: S = "12345"
// Output: ["12345"]
// Note:
//
// S will be a string with length between 1 and 12.
// S will consist only of letters or digits.
// Seen this question in a real interview before?
// Difficulty:Easy


class Solution {
    public List<String> letterCasePermutation(String S) {
        List<String> result = new ArrayList<>();
        if (S == null || S.length() == 0) {
            result.add("");
            return result;
        }
        // char[] array = S.toCharArray();
        return mytry(S);
    }

    private List<String> mytry(String s) {
        List<String> result = new ArrayList<>();
        dfs(s, result, "", 0);
        return result;
    }
    private void dfs(String s, List<String> result, String curr, int index) {
        if (index == s.length()) {
            result.add(curr);
            return;
        }
        char c = s.charAt(index);
        // 没有 for loop， 也不用 backtracking 除了加上小写之后消掉再加大写， 其他都是加完了之后就继续往下走了
        if (Character.isDigit(c)) {
            curr += String.valueOf(c);
            dfs(s, result, curr, index + 1);
            // curr = curr.substring(0, curr.length() - 1);
        } else {
            curr += String.valueOf(Character.toLowerCase(c));
            dfs(s, result, curr, index + 1);
            curr = curr.substring(0, curr.length() - 1); // backtracking
            curr += String.valueOf(Character.toUpperCase(c));
            dfs(s, result, curr, index + 1);
            // curr = curr.substring(0, curr.length() - 1);
        }
    }
}

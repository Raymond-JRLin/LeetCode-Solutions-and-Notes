// 320. Generalized Abbreviation
// DescriptionHintsSubmissionsDiscussSolution
// Write a function to generate the generalized abbreviations of a word.
//
// Note: The order of the output does not matter.
//
// Example:
//
// Input: "word"
// Output:
// ["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]


class Solution {
    public List<String> generateAbbreviations(String word) {
        if (word == null) {
            return Collections.emptyList();
        }

        return method1(word);
    }

    private List<String> method1(String word) {
        // 每个 char 都有两种选择： 选或者不选， 选的话就把之前 abbreviated 的数字和当前 char 加上， 不选的话， 就把当前 char 算入 abbreviation 的数字里去
        List<String> result = new ArrayList<>();
        dfs(word, result, new StringBuilder(), 0, 0);
        return result;
    }
    private void dfs(String word, List<String> result, StringBuilder sb, int i, int count) {
        int len = sb.length();
        if (i == word.length()) {
            if (count > 0) {
                sb.append(count);
            }
            result.add(sb.toString());
        } else {
            // do not abbreviate current char
            dfs(word, result, sb.append(count > 0 ? count : "").append(word.charAt(i)), i + 1, 0);
            sb.setLength(len); // 也可以把上下两个 dfs 的顺序调换一下， 这样 sb 就不会有改变， 可以直接进行下一个 dfs 了
            // abbreviate current char
            dfs(word, result, sb, i + 1, count + 1);
        }
        // 无论哪种方式， 最后都重新设置回原来的长度来做 backtracking
        sb.setLength(len);
    }
}

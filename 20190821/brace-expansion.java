// 1087. Brace Expansion
// DescriptionHintsSubmissionsDiscussSolution
//
// A string S represents a list of words.
//
// Each letter in the word has 1 or more options.  If there is one option, the letter is represented as is.  If there is more than one option, then curly braces delimit the options.  For example, "{a,b,c}" represents options ["a", "b", "c"].
//
// For example, "{a,b,c}d{e,f}" represents the list ["ade", "adf", "bde", "bdf", "cde", "cdf"].
//
// Return all words that can be formed in this manner, in lexicographical order.
//
//
//
// Example 1:
//
// Input: "{a,b}c{d,e}f"
// Output: ["acdf","acef","bcdf","bcef"]
//
// Example 2:
//
// Input: "abcd"
// Output: ["abcd"]
//
//
//
// Note:
//
//     1 <= S.length <= 50
//     There are no nested curly brackets.
//     All characters inside a pair of consecutive opening and ending curly brackets are different.
//
// Seen this question in a real interview before?
//
//     Difficulty:Medium


class Solution {
    public String[] expand(String S) {

        return mytry(S);
    }

    private String[] mytry(String s) {
        List<String> list = dfs(s, 0);
        String[] result = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
    }
    private List<String> dfs(String s, int index) {
        List<String> result = new ArrayList<>();
        if (index == s.length()) {
            result.add("");
            return result;
        }

        List<String> left = new ArrayList<>();
        if (s.charAt(index) == '{') {
            int i = index + 1;
            for (; i < s.length(); i++) {
                if (s.charAt(i) == '}') {
                    break;
                }
                if (s.charAt(i) == ',') {
                    continue;
                }
                left.add(String.valueOf(s.charAt(i)));
            }
            for (String l : left) {
                for (String r : dfs(s, i + 1)) {
                    result.add(l + r);
                }
            }
            Collections.sort(result);
            return result;
        } else {
            int i = index;
            for (; i < s.length(); i++) {
                if (s.charAt(i) == '{') {
                    break;
                }
            }
            left.add(s.substring(index, i));
            for (String l : left) {
                for (String r : dfs(s, i)) {
                    result.add(l + r);
                }
            }
            Collections.sort(result);
            return result;
        }
    }
}

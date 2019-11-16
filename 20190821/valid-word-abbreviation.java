// 408. Valid Word Abbreviation
// DescriptionHintsSubmissionsDiscussSolution
//
// Given a non-empty string s and an abbreviation abbr, return whether the string matches with the given abbreviation.
//
// A string such as "word" contains only the following valid abbreviations:
//
// ["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]
//
// Notice that only the above abbreviations are valid abbreviations of the string "word". Any other string is not a valid abbreviation of "word".
//
// Note:
// Assume s contains only lowercase letters and abbr contains only lowercase letters and digits.
//
// Example 1:
//
// Given s = "internationalization", abbr = "i12iz4n":
//
// Return true.
//
// Example 2:
//
// Given s = "apple", abbr = "a2e":
//
// Return false.
//
// Seen this question in a real interview before?
//
//     Difficulty:Easy


class Solution {
    public boolean validWordAbbreviation(String word, String abbr) {
        if (abbr == null || abbr.length() == 0) {
            return false;
        }

        return mytry(word, abbr);
    }

    private boolean mytry(String word, String abbr) {
        int m = word.length();
        int n = abbr.length();
        int i = 0, j = 0;
        while (i < n) {
            if (j >= m) {
                return false;
            }
            if (!Character.isDigit(abbr.charAt(i))) {
                if (abbr.charAt(i) != word.charAt(j)) {
                    return false;
                }
                i++;
                j++;
            } else {
                // 首位数字不能是 0
                if (abbr.charAt(i) == '0') {
                    return false;
                }
                int num = 0;
                while (i < n && Character.isDigit(abbr.charAt(i))) {
                    num = num * 10 + (int) (abbr.charAt(i) - '0');
                    i++;
                }
                j += num;
            }
        }
        return j == m;
    }
}

// 809. Expressive Words
// DescriptionHintsSubmissionsDiscussSolution
//
// Sometimes people repeat letters to represent extra feeling, such as "hello" -> "heeellooo", "hi" -> "hiiii".  In these strings like "heeellooo", we have groups of adjacent letters that are all the same:  "h", "eee", "ll", "ooo".
//
// For some given string S, a query word is stretchy if it can be made to be equal to S by any number of applications of the following extension operation: choose a group consisting of characters c, and add some number of characters c to the group so that the size of the group is 3 or more.
//
// For example, starting with "hello", we could do an extension on the group "o" to get "hellooo", but we cannot get "helloo" since the group "oo" has size less than 3.  Also, we could do another extension like "ll" -> "lllll" to get "helllllooo".  If S = "helllllooo", then the query word "hello" would be stretchy because of these two extension operations: query = "hello" -> "hellooo" -> "helllllooo" = S.
//
// Given a list of query words, return the number of words that are stretchy.
//
//
//
// Example:
// Input:
// S = "heeellooo"
// words = ["hello", "hi", "helo"]
// Output: 1
// Explanation:
// We can extend "e" and "o" in the word "hello" to get "heeellooo".
// We can't extend "helo" to get "heeellooo" because the group "ll" is not size 3 or more.
//
//
//
// Notes:
//
//     0 <= len(S) <= 100.
//     0 <= len(words) <= 100.
//     0 <= len(words[i]) <= 100.
//     S and all words in words consist only of lowercase letters
//
//
// Seen this question in a real interview before?
//
//     Difficulty:Medium


class Solution {
    public int expressiveWords(String S, String[] words) {

        return method1(S, words);

        // return method2(S, words);
    }

    private int method2(String s, String[] words) {
        int n = s.length();
        int result = 0;
        for (String word : words) {
            if (check(s, word)) {
                result++;
            }
        }
        return result;
    }
    private boolean check(String s, String w) {
        int n = s.length(), len = w.length();
        int i = 0, j = 0;
        while (i < n) {
            // 查第一位是否相同
            if (j < len && s.charAt(i) == w.charAt(j)) {
                i++;
                j++;
            } else if (i > 1 && s.charAt(i - 2) == s.charAt(i - 1) && s.charAt(i - 1) == s.charAt(i)) {
                // 第三位重复
                i++;
            } else if (i > 0 && i < n - 1 && s.charAt(i - 1) == s.charAt(i) && s.charAt(i) == s.charAt(i + 1)) {
                // 第二位重复
                i++;
            } else {
                return false;
            }
        }
        return j == len;
    }

    private int method1(String s, String[] words) {
        int result = 0;
        for (String word : words) {
            if (isStretchy(s, word)) {
                result++;
            }
        }
        return result;
    }
    private boolean isStretchy(String s, String word) {
        int n = s.length(), len = word.length();
        int i = 0, j = 0;
        char prev = '0';
        while (i < n && j < len) {
            if (s.charAt(i) == word.charAt(j)) {
                // 要在两个 char 相等的时候， 一直扫过相等的 char， 然后判断
                prev = s.charAt(i);
                int countS = 0;
                while (i < n && s.charAt(i) == prev) {
                    i++;
                    countS++;
                }
                int countW = 0;
                while (j < len && word.charAt(j) == prev) {
                    j++;
                    countW++;
                }
                // 如果 S 的 repeat < 3, 看是否能正好相等
                if (countS < 3) {
                    if (countS != countW) {
                        return false;
                    }
                } else {
                    // 如果 S 的 repeat > 3, word 不能超过它
                    if (countS < countW) {
                        return false;
                    }
                }
            } else {
                return false;
            }
        }
        // 还要检查是否都走完了
        return i == n && j == len;
    }
}

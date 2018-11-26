// 809. Expressive Words
// DescriptionHintsSubmissionsDiscussSolution
// Sometimes people repeat letters to represent extra feeling, such as "hello" -> "heeellooo", "hi" -> "hiiii".  Here, we have groups, of adjacent letters that are all the same character, and adjacent characters to the group are different.  A group is extended if that group is length 3 or more, so "e" and "o" would be extended in the first example, and "i" would be extended in the second example.  As another example, the groups of "abbcccaaaa" would be "a", "bb", "ccc", and "aaaa"; and "ccc" and "aaaa" are the extended groups of that string.
//
// For some given string S, a query word is stretchy if it can be made to be equal to S by extending some groups.  Formally, we are allowed to repeatedly choose a group (as defined above) of characters c, and add some number of the same character c to it so that the length of the group is 3 or more.  Note that we cannot extend a group of size one like "h" to a group of size two like "hh" - all extensions must leave the group extended - ie., at least 3 characters long.
//
// Given a list of query words, return the number of words that are stretchy.
//
// Example:
// Input:
// S = "heeellooo"
// words = ["hello", "hi", "helo"]
// Output: 1
// Explanation:
// We can extend "e" and "o" in the word "hello" to get "heeellooo".
// We can't extend "helo" to get "heeellooo" because the group "ll" is not extended.
// Notes:
//
// 0 <= len(S) <= 100.
// 0 <= len(words) <= 100.
// 0 <= len(words[i]) <= 100.
// S and all words in words consist only of lowercase letters


class Solution {
    public int expressiveWords(String S, String[] words) {
        if (S == null || S.length() == 0 || words == null || words.length == 0) {
            return -1;
        }

        // return method1(S, words);

        return method2(S, words);
    }

    private int method2(String s, String[] words) {
        // 把每个 string 缩减成没有扩展的字符， 查看是否一样， 并且查看所有字符出现的次数是否一样
        // ref: https://leetcode.com/articles/expressive-words/
        int result = 0;
        Stretchy ori = new Stretchy(s);
        for (String word : words) {
            Stretchy curr = new Stretchy(word);
            if (!ori.key.equals(curr.key) || ori.count.size() != curr.count.size()) {
                // 单一字符的 string 都不相等
                continue;
            }
            boolean isMatch = true;
            for (int i = 0; i < ori.count.size(); i++) {
                // 不能够扩展的 2 种情况
                if (ori.count.get(i) < curr.count.get(i)) {
                    // 1: ori 的个数本身已经小于 curr 的个数， 那无论如何 curr 也变不成 ori
                    isMatch = false;
                    break;
                } else if (ori.count.get(i) < 3 && ori.count.get(i) != curr.count.get(i)) {
                    // 2: ori 小于 3 （不能扩展）， 并且两者数量还不相等
                    isMatch = false;
                    break;
                }
            }
            if (isMatch) {
                result++;
            }
        }
        return result;
    }
    private class Stretchy {
        // 新的数据结构， 存下除掉扩展的 char 之后的 string 以及各个 char 扩展的个数
        private String key; // 除掉扩展的 char 之后， 即每个 char 都唯一的 string
        private List<Integer> count; // 记录每个字符有多少个

        public Stretchy(String s) {
            count = new ArrayList<>();
            StringBuilder sb = new StringBuilder();
            int n = s.length();
            int prev = -1;
            for (int i = 0; i < n; ++i) {
                if (i == n - 1 || s.charAt(i) != s.charAt(i + 1)) {
                    // 记录的点是重复的 char 的最后一个的位置
                    sb.append(s.charAt(i));
                    count.add(i - prev);
                    prev = i;
                }
            }
            key = sb.toString();
        }
    }

    private int method1(String s, String[] words) {
        // ref: https://leetcode.com/problems/expressive-words/discuss/122660/C++JavaPython-2-Pointers-and-4-pointers
        // 不可以把 s 拿去还原看是否存在于 words 中， 那这样答案只有两种， 要么 0 或 1， 实际上， 可以是任何字符的扩展， 所以并不一定是所有的字符扩展
        int result = 0;
        for (String word : words) {
            if (check(s, word)) {
                result++;
            }
        }
        return result;
    }
    private boolean check(String s, String word) {
        // 2 pointers
        int n = s.length();
        int i = 0, j = 0;
        // 双指针去查看是否 s 通过减少某些字符的扩展， 可以变成 word
        while (i < n) {
            if (j < word.length() && s.charAt(i) == word.charAt(j)) {
                i++;
                j++;
            // 接下去查看 char 不同时候的情况， 判断 s 中是否是 3 位重复
            } else if (i > 0 && s.charAt(i - 1) == s.charAt(i) && i < n - 1 && s.charAt(i) == s.charAt(i + 1)) {
                // 此时 i 在 3 个重复的第 2 位
                i++;
            } else if (i > 1 && s.charAt(i - 2) == s.charAt(i - 1) && s.charAt(i - 1) == s.charAt(i)) {
                // 此时 i 在 3 个重复的第 3 位
                i++;
            } else {
                // 无需判断第 1 位， 因为第 1 位不相同， 那肯定就不一样了
                return false;
            }
        }
        // 当 i 走完 s 的时候判断 j 是否也走完了 word
        return j == word.length();
    }
}

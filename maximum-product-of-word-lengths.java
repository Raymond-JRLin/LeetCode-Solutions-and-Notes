// 318
// Given a string array words, find the maximum value of length(word[i]) * length(word[j]) where the two words do not share common letters. You may assume that each word will contain only lower case letters. If no such two words exist, return 0.
//
// Example 1:
//
// Input: ["abcw","baz","foo","bar","xtfn","abcdef"]
// Output: 16
// Explanation: The two words can be "abcw", "xtfn".
// Example 2:
//
// Input: ["a","ab","abc","d","cd","bcd","abcd"]
// Output: 4
// Explanation: The two words can be "ab", "cd".
// Example 3:
//
// Input: ["a","aa","aaa","aaaa"]
// Output: 0
// Explanation: No such pair of words.


class Solution {
    public int maxProduct(String[] words) {
        if (words == null || words.length == 0) {
            return 0;
        }

        // return method1(words);

        return method1_2(words);
    }

    private int method1_2(String[] words) {
        int n = words.length;
        int[] nums = new int[n]; // record results
        // sort strings from longer to shorter
        Arrays.sort(words, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Integer.compare(o2.length(), o1.length());
            }
        });
        for (int i = 0; i < n; i++) {
            String word = words[i];
            for (char c : word.toCharArray()) {
                nums[i] |= (1 << (c - 'a'));
            }
        }
        int result = 0;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (words[i].length() * words[j].length() <= result) {
                    break;
                }
                if ((nums[i] & nums[j]) == 0) {
                    // they don't share common letters
                    result = Math.max(result, words[i].length() * words[j].length());
                }
            }
        }
        return result;
    }

    private int method1(String[] words) {
        // 如果对每个 string 使用 HashMap 去找其他的是否重复， 肯定会 TLE， topics 显示 bit manipulation， 那么就提示要这么做
        // int 是 32 位， 小写字母才 26 个， 所以可以用后 26 位来表示每个不同的字母， 将当前 word string 的每一位化为数字， 然后取 与 -- |，将最终的结果放入当前位置， 如果它与某个其他位置的结果取 & 为 0， 说明它们俩没有重复的数字／字符
        int n = words.length;
        int[] nums = new int[n]; // record results
        for (int i = 0; i < n; i++) {
            String word = words[i];
            for (int j = 0; j < word.length(); j++) {
                //
                int num = 1 << (word.charAt(j) - 'a'); //
                nums[i] |= num; //
            }
        }
        int result = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (words[i].length() * words[j].length() <= result) {
                    continue;
                }
                if ((nums[i] & nums[j]) == 0) {
                    // they don't share common letters
                    result = Math.max(result, words[i].length() * words[j].length());
                }
            }
        }
        return result;
    }
}

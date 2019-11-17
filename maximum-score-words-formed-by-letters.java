// 1255. Maximum Score Words Formed by Letters
//
//     User Accepted: 709
//     User Tried: 811
//     Total Accepted: 729
//     Total Submissions: 1019
//     Difficulty: Hard
//
// Given a list of words, list of  single letters (might be repeating) and score of every character.
//
// Return the maximum score of any valid set of words formed by using the given letters (words[i] cannot be used two or more times).
//
// It is not necessary to use all characters in letters and each letter can only be used once. Score of letters 'a', 'b', 'c', ... ,'z' is given by score[0], score[1], ... , score[25] respectively.
//
//
//
// Example 1:
//
// Input: words = ["dog","cat","dad","good"], letters = ["a","a","c","d","d","d","g","o","o"], score = [1,0,9,5,0,0,3,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0]
// Output: 23
// Explanation:
// Score  a=1, c=9, d=5, g=3, o=2
// Given letters, we can form the words "dad" (5+1+5) and "good" (3+2+2+5) with a score of 23.
// Words "dad" and "dog" only get a score of 21.
//
// Example 2:
//
// Input: words = ["xxxz","ax","bx","cx"], letters = ["z","a","b","c","x","x","x"], score = [4,4,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,5,0,10]
// Output: 27
// Explanation:
// Score  a=4, b=4, c=4, x=5, z=10
// Given letters, we can form the words "ax" (4+5), "bx" (4+5) and "cx" (4+5) with a score of 27.
// Word "xxxz" only get a score of 25.
//
// Example 3:
//
// Input: words = ["leetcode"], letters = ["l","e","t","c","o","d"], score = [0,0,1,1,1,0,0,0,0,0,0,1,0,0,1,0,0,0,0,1,0,0,0,0,0,0]
// Output: 0
// Explanation:
// Letter "e" can only be used once.
//
//
//
// Constraints:
//
//     1 <= words.length <= 14
//     1 <= words[i].length <= 15
//     1 <= letters.length <= 100
//     letters[i].length == 1
//     score.length == 26
//     0 <= score[i] <= 10
//     words[i], letters[i] contains only lower case English letters.
//


class Solution {
    public int maxScoreWords(String[] words, char[] letters, int[] score) {

        // return mytry(words, letters, score);

        return method2(words, letters, score);
    }

    private int method2(String[] words, char[] letters, int[] score) {
        // get letters frequency
        int[] freq = new int[26];
        for (char c : letters) {
            freq[c - 'a']++;
        }
        // 反正 words 最多 14 个， 查所有的 subsets 就好了
        List<Item> items = new ArrayList<>();
        for (String word : words) {
            // check if this word could be made by these letters
            int[] arr = Arrays.copyOf(freq, 26);
            boolean isValid = true;
            for (char c : word.toCharArray()) {
                arr[c - 'a']--;
                if (arr[c - 'a'] < 0) {
                    isValid = false;
                }
            }
            if (!isValid) {
                continue;
            }
            // get word score
            int sum = 0;
            for (char c : word.toCharArray()) {
                sum += score[c - 'a'];
            }
            items.add(new Item(word, sum));
        }

        return dfs2(words, 0, freq, score);
    }
    private int dfs2(String[] words, int index, int[] freq, int[] score) {
        if (index == words.length) {
            return 0;
        }
        int result = 0;
        for (int i = index; i < words.length; i++) {
            String s = words[i];
            boolean isValid = true;
            int curr = 0;
            int[] copy = Arrays.copyOf(freq, 26);
            for (char c : s.toCharArray()) {
                copy[c - 'a']--;
                curr += score[c - 'a'];
                if (copy[c - 'a'] < 0) {
                    isValid = false;
                    break;
                }
            }
            if (!isValid) {
                continue;
            }
            curr += dfs2(words, i + 1, copy, score);
            result = Math.max(result, curr);
        }
        return result;
    }

    private int mytry(String[] words, char[] letters, int[] score) {
        // get letters frequency
        int[] freq = new int[26];
        for (char c : letters) {
            freq[c - 'a']++;
        }

        List<Item> items = new ArrayList<>();
        for (String word : words) {
            // check if this word could be made by these letters
            int[] arr = Arrays.copyOf(freq, 26);
            boolean isValid = true;
            for (char c : word.toCharArray()) {
                arr[c - 'a']--;
                if (arr[c - 'a'] < 0) {
                    isValid = false;
                }
            }
            if (!isValid) {
                continue;
            }
            // get word score
            int sum = 0;
            for (char c : word.toCharArray()) {
                sum += score[c - 'a'];
            }
            items.add(new Item(word, sum));
        }

        result = 0;
        for (int i = 0; i < items.size(); i++) {
            dfs(i, items, freq, 0);
        }

        return result;
    }
    private void dfs(int index, List<Item> items, int[] arr, int sum) {
        if (index == items.size()) {
            result = Math.max(result, sum);
            return;
        }
        String s = items.get(index).word;
        int[] copy = Arrays.copyOf(arr, 26);
        // 把当前的 string 出现的 freq 减掉
        for (char c : s.toCharArray()) {
            copy[c - 'a']--;
            if (copy[c - 'a'] < 0) {
                return;
            }
        }

        for (int i = index; i < items.size(); i++) {
            dfs(i + 1, items, copy, sum + items.get(index).score);
        }
    }
    private int result;
    private class Item {
        String word;
        int score;

        Item(String w, int s) {
            this.word = w;
            this.score = s;
        }
    }
}

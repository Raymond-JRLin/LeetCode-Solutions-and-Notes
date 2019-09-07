// 1170. Compare Strings by Frequency of the Smallest Character
// User Accepted: 2139
// User Tried: 2265
// Total Accepted: 2163
// Total Submissions: 3361
// Difficulty: Easy
// Let's define a function f(s) over a non-empty string s, which calculates the frequency of the smallest character in s. For example, if s = "dcce" then f(s) = 2 because the smallest character is "c" and its frequency is 2.
//
// Now, given string arrays queries and words, return an integer array answer, where each answer[i] is the number of words such that f(queries[i]) < f(W), where W is a word in words.
//
//
//
// Example 1:
//
// Input: queries = ["cbd"], words = ["zaaaz"]
// Output: [1]
// Explanation: On the first query we have f("cbd") = 1, f("zaaaz") = 3 so f("cbd") < f("zaaaz").
// Example 2:
//
// Input: queries = ["bbb","cc"], words = ["a","aa","aaa","aaaa"]
// Output: [1,2]
// Explanation: On the first query only f("bbb") < f("aaaa"). On the second query both f("aaa") and f("aaaa") are both > f("cc").
//
//
// Constraints:
//
// 1 <= queries.length <= 2000
// 1 <= words.length <= 2000
// 1 <= queries[i].length, words[i].length <= 10
// queries[i][j], words[i][j] are English lowercase letters.
//


class Solution {
    public int[] numSmallerByFrequency(String[] queries, String[] words) {
        return mytry(queries, words);
    }

    private int[] mytry(String[] queries, String[] words) {
        int n = queries.length;
        int[] queryFreq = getFreq(queries);
        int[] wordFreq = getFreq(words);
        Arrays.sort(wordFreq);
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            result[i] = largerCount(queryFreq[i], wordFreq);
        }
        return result;
    }
    private int largerCount(int target, int[] arr) {
        int n = arr.length;
        int start = 0;
        int end = n - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (arr[mid] <= target) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (arr[start] > target) {
            return n - start;
        } else if (arr[end] > target) {
            return n - end;
        } else {
            return 0;
        }
    }
    private int[] getFreq(String[] arr) {
        int n = arr.length;
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            result[i] = freqOfSmallestChar(arr[i]);
        }
        return result;
    }
    private int freqOfSmallestChar(String s) {
        int[] bucket = new int[26];
        int smallest = 27;
        for (char c : s.toCharArray()) {
            bucket[c - 'a']++;
            smallest = Math.min(smallest, c - 'a');
        }
        return bucket[smallest];
    }
}

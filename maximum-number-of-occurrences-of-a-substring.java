// 1297. Maximum Number of Occurrences of a Substring
//
//     User Accepted: 1066
//     User Tried: 1565
//     Total Accepted: 1089
//     Total Submissions: 3417
//     Difficulty: Medium
//
// Given a string s, return the maximum number of ocurrences of any substring under the following rules:
//
//     The number of unique characters in the substring must be less than or equal to maxLetters.
//     The substring size must be between minSize and maxSize inclusive.
//
//
//
// Example 1:
//
// Input: s = "aababcaab", maxLetters = 2, minSize = 3, maxSize = 4
// Output: 2
// Explanation: Substring "aab" has 2 ocurrences in the original string.
// It satisfies the conditions, 2 unique letters and size 3 (between minSize and maxSize).
//
// Example 2:
//
// Input: s = "aaaa", maxLetters = 1, minSize = 3, maxSize = 3
// Output: 2
// Explanation: Substring "aaa" occur 2 times in the string. It can overlap.
//
// Example 3:
//
// Input: s = "aabcabcab", maxLetters = 2, minSize = 2, maxSize = 3
// Output: 3
//
// Example 4:
//
// Input: s = "abcde", maxLetters = 2, minSize = 3, maxSize = 3
// Output: 0
//
//
//
// Constraints:
//
//     1 <= s.length <= 10^5
//     1 <= maxLetters <= 26
//     1 <= minSize <= maxSize <= min(26, s.length)
//     s only contains lowercase English letters.
//


class Solution {
    public int maxFreq(String s, int maxLetters, int minSize, int maxSize) {

        // return mytry(s, maxLetters, minSize, maxSize);

        return mytry2(s, maxLetters, minSize, maxSize);
    }

    private int mytry2(String s, int k, int min, int max) {
        // neat version of mytry
        int n = s.length();
        if (n < min) {
            return 0;
        }
        Map<String, Integer> map = new HashMap<>(); // <substring, freq>
        int left = 0;
        for (int i = 0; i < n + 1 - min; i++) {
            Set<Character> set = new HashSet<>(); // <distince char>
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < max; j++) {
                if (i + j >= n) {
                    break;
                }
                if (!set.contains(s.charAt(i + j)) && set.size() == k) {
                    break;
                }
                sb.append(s.charAt(i + j));
                set.add(s.charAt(i + j));
                if (sb.length() >= min) {
                    map.put(sb.toString(), map.getOrDefault(sb.toString(), 0) + 1);
                }
            }
        }

        int result = 0;
        for (String key : map.keySet()) {
            result = Math.max(result, map.get(key));
        }
        return result;
    }

    private int mytry(String s, int k, int min, int max) {
        // 一开始的时候想用 sliding window 做， 类似于 longest substring with at most k distinct character
        // 但是那样有问题， 比如 "aaaa", 左指针没有移动， 没办法找到所有的 substring
        // 所以转而去找所有可能的 substring， 同时查看是否满足条件， 记录下对应的 freq
        // 不知道有没有更简洁的 sliding window 解法
        // O(N * max) time, where 1 <= max <= 26 => O(N) time
        int n = s.length();
        if (n < min) {
            return 0;
        }
        Map<String, Integer> map = new HashMap<>(); // <substring, freq>
        int left = 0;
        for (int i = 0; i < n + 1 - min; i++) {
            Set<Character> set = new HashSet<>(); // <distince char>
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < min; j++) {
                if (i + j >= n) {
                    break;
                }
                if (!set.contains(s.charAt(i + j)) && set.size() == k) {
                    break;
                }
                sb.append(s.charAt(i + j));
                set.add(s.charAt(i + j));
            }
            // 不足 min
            if (sb.length() < min) {
                continue;
            }
            map.put(sb.toString(), map.getOrDefault(sb.toString(), 0) + 1);
            for (int j = min; j < max; j++) {
                if (i + j >= n) {
                    break;
                }
                if (!set.contains(s.charAt(i + j)) && set.size() == k) {
                    break;
                }
                sb.append(s.charAt(i + j));
                map.put(sb.toString(), map.getOrDefault(sb.toString(), 0) + 1);
                set.add(s.charAt(i + j));
            }

        }

        int result = 0;
        for (String key : map.keySet()) {
            result = Math.max(result, map.get(key));
        }
        return result;
    }
}

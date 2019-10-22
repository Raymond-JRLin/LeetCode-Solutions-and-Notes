// 76. Minimum Window Substring
// DescriptionHintsSubmissionsDiscussSolution
//
// Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).
//
// Example:
//
// Input: S = "ADOBECODEBANC", T = "ABC"
// Output: "BANC"
//
// Note:
//
//     If there is no such window in S that covers all characters in T, return the empty string "".
//     If there is such window, you are guaranteed that there will always be only one unique minimum window in S.
//
// Seen this question in a real interview before?
//
//     Difficulty:Hard


class Solution {
    public String minWindow(String s, String t) {
        if (t == null || t.isEmpty()) {
            return s;
        }

        // return method1(s, t);

        return method2(s, t);
    }

    private String method2(String s, String t) {
        int[] count = new int[256];
        for (char c : t.toCharArray()) {
            count[c]++;
        }
        int left = 0;
        int start = 0;
        int len = Integer.MAX_VALUE;
        int counter = t.length();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            count[c]--;
            if (count[c] >= 0) {
                counter--;
            }
            while (counter == 0) {
                if (i + 1 - left < len) {
                    len = i + 1 - left;
                    start = left;
                }
                count[s.charAt(left)]++;
                if (count[s.charAt(left)] > 0) {
                    counter++;
                }
                left++;
            }
        }
        return len == Integer.MAX_VALUE ? "" : s.substring(start, start + len);
    }

    private String method1(String s, String t) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : t.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        int left = 0;
        int counter = 0;
        int len = Integer.MAX_VALUE;
        String result = "";
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                map.put(c, map.get(c) - 1);
                if (map.get(c) >= 0) {
                    counter++;
                }
                while (counter == t.length()) {
                    if (i + 1 - left < len) {
                        len = i + 1 - left;
                        result = s.substring(left, i + 1);
                    }
                    char lc = s.charAt(left);
                    if (map.containsKey(lc)) {
                        // 补回来
                        map.put(lc, map.get(lc) + 1);
                        if (map.get(lc) > 0) {
                            counter--;
                        }
                    }
                    left++;
                }
            }
        }
        return result;
    }
}

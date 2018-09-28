// 291. Word Pattern II
// DescriptionHintsSubmissionsDiscussSolution
// Given a pattern and a string str, find if str follows the same pattern.
//
// Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty substring in str.
//
// Example 1:
//
// Input: pattern = "abab", str = "redblueredblue"
// Output: true
// Example 2:
//
// Input: pattern = pattern = "aaaa", str = "asdasdasdasd"
// Output: true
// Example 3:
//
// Input: pattern = "aabb", str = "xyzabcxzyabc"
// Output: false
// Notes:
// You may assume both pattern and str contains only lowercase letters.


class Solution {
    public boolean wordPatternMatch(String pattern, String str) {
        if (pattern == null && str == null || pattern.isEmpty() && str.isEmpty()) {
            return true;
        }
        if (pattern == null || pattern.length() == 0) {
            return false;
        }

        return method1(pattern, str);
    }

    private boolean method1(String pattern, String s) {
        Map<Character, String> map = new HashMap<>(); // <char in pattern, corresponding string in s>
        Set<String> set = new HashSet<>(); // store corresponding string
        return recursion(pattern, s, map, set, 0, 0);
    }
    private boolean recursion(String pattern, String s, Map<Character, String> map, Set<String> set, int i, int j) {
        if (i == pattern.length() && j == s.length()) {
            return true;
        }
        if (i == pattern.length() || j == s.length()) {
            return false;
        }
        // every time we can only have only 1 char as current pattern
        char c = pattern.charAt(i); // current pattern char
        if (map.containsKey(c)) {
            // if we alrady have this pattern char before, check if there's corresponding string in s
            String curr = map.get(c);
            if (j + curr.length() > s.length() || !curr.equals(s.substring(j, j + curr.length()))) {
                return false;
            }
            return recursion(pattern, s, map, set, i + 1, j + curr.length());
        }
        // check every possibility for corresponding string
        for (int k = j; k < s.length(); k++) {
            String curr = s.substring(j, k + 1);
            if (set.contains(curr)) {
                // different pattern char cannot be mapping to same string in s
                continue;
            }
            map.put(c, curr);
            set.add(curr);
            if (recursion(pattern, s, map, set, i + 1, k + 1)) {
                return true;
            }
            map.remove(c);
            set.remove(curr);
        }
        return false;
    }
}

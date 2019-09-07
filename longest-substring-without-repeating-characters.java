// 3. Longest Substring Without Repeating Characters
// DescriptionHintsSubmissionsDiscussSolution
// Given a string, find the length of the longest substring without repeating characters.
//
// Example 1:
//
// Input: "abcabcbb"
// Output: 3
// Explanation: The answer is "abc", with the length of 3.
// Example 2:
//
// Input: "bbbbb"
// Output: 1
// Explanation: The answer is "b", with the length of 1.
// Example 3:
//
// Input: "pwwkew"
// Output: 3
// Explanation: The answer is "wke", with the length of 3.
//              Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
// Seen this question in a real interview before?
// Difficulty:Medium


class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        // return my_try(s);

        return method2(s);
    }

    private int method2(String s) {
        // use HashSet
        Set<Character> set = new HashSet<>();
        int curr = 0;
        int prev = 0;
        int result = 0;
        while (curr < s.length()) {
            char c = s.charAt(curr);
            if (set.contains(c)) {
                set.remove(s.charAt(prev));
                prev++;
            } else {
                set.add(c);
                result = Math.max(result, set.size());
                curr++;
            }
        }
        return result;
    }

    private int my_try(String s) {
        int n = s.length();
        int index = 0;
        int result = 0;
        int prev = 0;
        Map<Character, Integer> map = new HashMap<>(); // <char, index>
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                // 也可以不删去 map 中的， 直接更新 prev
                for (int j = prev; j < map.get(c); j++) {
                    map.remove(s.charAt(j));
                }
                // prev 更新到上一次出现过的下一个
                prev = map.get(c) + 1;
            }
            map.put(c, i);
            result = Math.max(result, i - prev + 1);
        }
        return result;
    }
}

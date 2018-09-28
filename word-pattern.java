// 290. Word Pattern
// DescriptionHintsSubmissionsDiscussSolution
// Given a pattern and a string str, find if str follows the same pattern.
//
// Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in str.
//
// Example 1:
//
// Input: pattern = "abba", str = "dog cat cat dog"
// Output: true
// Example 2:
//
// Input:pattern = "abba", str = "dog cat cat fish"
// Output: false
// Example 3:
//
// Input: pattern = "aaaa", str = "dog cat cat dog"
// Output: false
// Example 4:
//
// Input: pattern = "abba", str = "dog dog dog dog"
// Output: false
// Notes:
// You may assume pattern contains only lowercase letters, and str contains lowercase letters separated by a single space.


class Solution {
    public boolean wordPattern(String pattern, String str) {
        if (pattern == null && str == null || pattern.isEmpty() && str.isEmpty()) {
            return true;
        }
        if (pattern == null || pattern.length() == 0) {
            return false;
        }

        return mytry(pattern, str);
    }

    private boolean mytry(String pattern, String s) {
        String[] strs = s.split(" ");
        if (strs.length != pattern.length()) {
            return false;
        }
        Map<Character, String> map = new HashMap<>();
        Set<String> set = new HashSet<>();
        for (int i = 0; i < strs.length; i++) {
            char c = pattern.charAt(i);
            String curr = strs[i];
            if (map.containsKey(c)) {
                if (!curr.equals(map.get(c))) {
                    return false;
                }
            } else {
                if (set.contains(curr)) {
                    return false;
                }
                map.put(c, curr);
                set.add(curr);
            }
        }
        return true;
    }
}

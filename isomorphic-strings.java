// 205. Isomorphic Strings
// DescriptionHintsSubmissionsDiscussSolution
// Given two strings s and t, determine if they are isomorphic.
//
// Two strings are isomorphic if the characters in s can be replaced to get t.
//
// All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may map to the same character but a character may map to itself.
//
// Example 1:
//
// Input: s = "egg", t = "add"
// Output: true
// Example 2:
//
// Input: s = "foo", t = "bar"
// Output: false
// Example 3:
//
// Input: s = "paper", t = "title"
// Output: true
// Note:
// You may assume both s and t have the same length.
//
// Seen this question in a real interview before?
// Difficulty:Easy


class Solution {
    public boolean isIsomorphic(String s, String t) {
        if ((s == null || s.length() == 0) && (t == null || t.length() == 0) || s.equals(t)) {
            return true;
        }

        // return mytry(s, t);

        return method2(s, t);
    }

    private boolean method2(String s, String t) {
        int n = s.length();
        int[] nums = new int[512]; // put chars of s in the first 256 part, and chars of t in the 2nd 256 part; or we can use 2 arrays
        for (int i = 0; i < n; i++) {
            char a = s.charAt(i);
            char b = t.charAt(i);
            // remember to add 256 to chars of b
            if (nums[a] != nums[b + 256]) {
                return false;
            }
            nums[a] = i + 1;
            nums[b + 256] = i + 1;
        }
        return true;
    }

    private boolean mytry(String s, String t) {
        // use HashMap to construct the mapping relations, attention we should check 2 directions: no 2 characters can map to 1 same char, beside, 1 character cannot map to 2 different chars: O(N) time and space
        int n = s.length();
        Map<Character, Character> map = new HashMap<>();
        Map<Character, Character> rev = new HashMap<>();
        for (int i = 0; i < n; i++) {
            char a = s.charAt(i);
            char b = t.charAt(i);
            if (map.containsKey(a)) {
                if (map.get(a) != b) {
                    return false;
                }
            } else {
                map.put(a, b);
            }
            if (rev.containsKey(b)) {
                if (rev.get(b) != a) {
                    return false;
                }
            } else {
                rev.put(b, a);
            }
        }
        return true;
    }
}

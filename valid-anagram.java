// 242. Valid Anagram
// DescriptionHintsSubmissionsDiscussSolution
// Given two strings s and t , write a function to determine if t is an anagram of s.
//
// Example 1:
//
// Input: s = "anagram", t = "nagaram"
// Output: true
// Example 2:
//
// Input: s = "rat", t = "car"
// Output: false
// Note:
// You may assume the string contains only lowercase alphabets.
//
// Follow up:
// What if the inputs contain unicode characters? How would you adapt your solution to such case?
//
// Seen this question in a real interview before?
// Difficulty:Easy


class Solution {
    public boolean isAnagram(String s, String t) {
        if (s == null || t == null) {
            return false;
        }
        if (s.equals(t)) {
            return true;
        }
        int n = s.length();
        int[] counts = new int[256];
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            counts[c]++;
        }
        for (int i = 0; i < t.length(); i++) {
            counts[t.charAt(i)]--;
        }
        for (int i = 0; i < 256; i++) {
            if (counts[i] != 0) {
                return false;
            }
        }
        return true;
    }
}

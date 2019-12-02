// 567. Permutation in String
// DescriptionHintsSubmissionsDiscussSolution
//
// Given two strings s1 and s2, write a function to return true if s2 contains the permutation of s1. In other words, one of the first string's permutations is the substring of the second string.
//
//
//
// Example 1:
//
// Input: s1 = "ab" s2 = "eidbaooo"
// Output: True
// Explanation: s2 contains one permutation of s1 ("ba").
//
// Example 2:
//
// Input:s1= "ab" s2 = "eidboaoo"
// Output: False
//
//
//
// Note:
//
//     The input strings only contain lower case letters.
//     The length of both given strings is in range [1, 10,000].
//
// Seen this question in a real interview before?
//
//     Difficulty:Medium


class Solution {
    public boolean checkInclusion(String s1, String s2) {

        // return mytry(s1, s2);

        return method2(s1, s2);
    }

    private boolean method2(String s1, String s2) {
        // sliding window
        int len1 = s1.length();
        int len2 = s2.length();
        if (len1 > len2) {
            return false;
        }
        int[] freq = new int[26];
        for (int i = 0; i < len1; i++) {
            freq[s1.charAt(i) - 'a']++;
            freq[s2.charAt(i) - 'a']--;
        }
        if (isAllZero(freq)) {
            return true;
        }
        // same to find all substring, but with sliding window to modify the freq array
        for (int i = len1; i < len2; i++) {
            freq[s2.charAt(i) - 'a']--; // remove this new char at i index
            freq[s2.charAt(i - len1) - 'a']++; // add back the first char in last window at (i - len1) index
            if (isAllZero(freq)) {
                return true;
            }
        }
        return false;
    }
    private boolean isAllZero(int[] freq) {
        for (int f : freq) {
            if (f != 0) {
                return false;
            }
        }
        return true;
    }

    private boolean mytry(String s1, String s2) {
        int len1 = s1.length();
        int len2 = s2.length();
        if (len1 > len2) {
            return false;
        }
        int[] count = getFreq(s1);
        // find all substring, then compare their freq
        for (int i = 0; i < len2 + 1 - len1; i++) {
            String sub = s2.substring(i, i + len1);
            int[] freq = getFreq(sub);
            if (compare(count, freq)) {
                return true;
            }
        }
        return false;
    }
    private boolean compare(int[] count, int[] freq) {
        for (int i = 0; i < count.length; i++) {
            if (count[i] != freq[i]) {
                return false;
            }
        }
        return true;
    }
    private int[] getFreq(String s) {
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }
        return count;
    }
}

// 28. Implement strStr()
// DescriptionHintsSubmissionsDiscussSolution
// Implement strStr().
//
// Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
//
// Example 1:
//
// Input: haystack = "hello", needle = "ll"
// Output: 2
// Example 2:
//
// Input: haystack = "aaaaa", needle = "bba"
// Output: -1
// Clarification:
//
// What should we return when needle is an empty string? This is a great question to ask during an interview.
//
// For the purpose of this problem, we will return 0 when needle is an empty string. This is consistent to C's strstr() and Java's indexOf().
//


class Solution {
    public int strStr(String haystack, String needle) {
        if ((haystack == null || haystack.length() == 0) && (needle == null || needle.length() == 0)) {
            return 0;
        }
        if (haystack == null || haystack.length() == 0) {
            return -1;
        }
        if (needle == null || needle.length() == 0) {
            return 0;
        }

        return my_try(haystack, needle);
    }
    private int my_try(String source, String target) {
        // search directly
        int j = 0;
        for (int i = 0; i < source.length() - target.length() + 1; i++) {
            if (source.charAt(i) == target.charAt(j)) {
                if (match(source, i, target, j)) {
                    return i;
                }
            }
        }
        return -1;
    }
    private boolean match(String s, int i, String t, int j) {
        while (j < t.length()) {
            if (i >= s.length()) {
                return false;
            }
            if (s.charAt(i) != t.charAt(j)) {
                return false;
            }
            i++;
            j++;
        }
        return true;
    }
}

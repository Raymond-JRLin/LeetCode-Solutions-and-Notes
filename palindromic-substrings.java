// 647. Palindromic Substrings
// DescriptionHintsSubmissionsDiscussSolution
// Given a string, your task is to count how many palindromic substrings in this string.
//
// The substrings with different start indexes or end indexes are counted as different substrings even they consist of same characters.
//
// Example 1:
//
// Input: "abc"
// Output: 3
// Explanation: Three palindromic strings: "a", "b", "c".
//
//
// Example 2:
//
// Input: "aaa"
// Output: 6
// Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
//
//
// Note:
//
// The input string length won't exceed 1000.
//
//
// Seen this question in a real interview before?
// Difficulty:Medium


class Solution {
    public int countSubstrings(String s) {
//         method 1: get all substrings and check if it's a palindrome
        // return method1(s);
//         method 2: start from the middle and spread forward and backward togerther to check palindrome
        return method2(s);
    }
    private int method1(String s) {
        int n = s.length();
        int count = 0;
        int index = 0;
        while (index < n) {
            for (int i = index; i < n; i++) {
                String curr = s.substring(index, i + 1);
                if (isPalindrome(curr)) {
                    count++;
                }
            }
            index++;
        }
        return count;
    }
    private boolean isPalindrome(String string) {
        int start = 0;
        int end = string.length() - 1;
        while (start < end) {
            if (string.charAt(start) != string.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }

//     method 2: 以 i 为中点向两边展开， 展开后的 palindromic string 可以是长度为单或双的
    int result = 0; // global varible to count palindromes
    private int method2(String s) {
        int n = s.length();
        for (int i = 0; i < n; i++) {
            // i is the middle position
            extendPalindrome(s, i, i + 1); // even
            extendPalindrome(s, i, i); // odd
        }
        return result;
    }
    private void extendPalindrome(String s, int left, int right) {
        int n = s.length();
        while (left >= 0 && right < n) {
            if (s.charAt(left) == s.charAt(right)) {
                result++;
                left--;
                right++;
            } else {
                break;
            }
        }
    }
}

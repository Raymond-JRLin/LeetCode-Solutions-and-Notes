// 680. Valid Palindrome II
// DescriptionHintsSubmissionsDiscussSolution
//
// Given a non-empty string s, you may delete at most one character. Judge whether you can make it a palindrome.
//
// Example 1:
//
// Input: "aba"
// Output: True
//
// Example 2:
//
// Input: "abca"
// Output: True
// Explanation: You could delete the character 'c'.
//
// Note:
//
//     The string will only contain lowercase characters a-z. The maximum length of the string is 50000.
//
// Seen this question in a real interview before?
//
//     Difficulty:Easy


class Solution {
    public boolean validPalindrome(String s) {
        if (s == null || s.length() <= 2) {
            return true;
        }

        return mytry(s);
    }

    private boolean mytry(String s) {
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                // 有一个不同， 删掉 left 或是删掉 right， 看其中一种是否能 valid
                return isPalindrome(s.substring(left + 1, right + 1)) ||
                       isPalindrome(s.substring(left, right));
            }
            left++;
            right--;
        }
        return true;
    }
    private boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}

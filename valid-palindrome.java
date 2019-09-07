// 125. Valid Palindrome
// DescriptionHintsSubmissionsDiscussSolution
// Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
//
// Note: For the purpose of this problem, we define empty string as valid palindrome.
//
// Example 1:
//
// Input: "A man, a plan, a canal: Panama"
// Output: true
// Example 2:
//
// Input: "race a car"
// Output: false
// Seen this question in a real interview before?
// Difficulty:Easy


public class Solution {
    public boolean isPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }

        return method1(s);
    }

    private boolean method1(String s) {
        int i = 0;
        int j = s.length() - 1;
        while (i < j) {
            while (i < j && !isValid(s.charAt(i))) {
                i++;
            }
            while (i < j && !isValid(s.charAt(j))) {
                j--;
            }
            if (Character.toLowerCase(s.charAt(i)) != Character.toLowerCase(s.charAt(j))) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
    private boolean isValid(char c) {
        // alphanumeric means letters or digits
        return Character.isLetter(c) || Character.isDigit(c);
    }
}

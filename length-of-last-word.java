// 58. Length of Last Word
// DescriptionHintsSubmissionsDiscussSolution
// Given a string s consists of upper/lower-case alphabets and empty space characters ' ', return the length of last word in the string.
//
// If the last word does not exist, return 0.
//
// Note: A word is defined as a character sequence consists of non-space characters only.
//
// Example:
//
// Input: "Hello World"
// Output: 5
//
//
// Seen this question in a real interview before?
// Difficulty:Easy


class Solution {
    public int lengthOfLastWord(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        // return mytry(s);

        // return method2(s);

        return method3(s);
    }
    private int method3(String s) {
        // improve of method2
        int n = s.length();
        int end = n - 1;
        while (end >= 0 && s.charAt(end) == ' ') {
            end--;
        }
        int result = 0;
        while (end >= 0 && s.charAt(end) != ' ') {
            result++;
            end--;
        }
        return result;
    }

    private int method2(String s) {
        int n = s.length();
        int end = n - 1;
        // jump trailing 0
        while (end >= 0 && s.charAt(end) == ' ') {
            end--;
        }
        if (end < 0) {
            return 0;
        }
        int trail = n - 1 - end;
        // count the last string
        while (end >= 0 && s.charAt(end) != ' ') {
            end--;
        }
        if (end < 0) {
            return n - trail;
        }
        return n - trail - 1 - end;
    }

    private int mytry(String s) {
        String[] arr = s.split(" ");
        if (arr.length == 0) {
            return 0;
        }
        return arr[arr.length - 1].length();
    }
}

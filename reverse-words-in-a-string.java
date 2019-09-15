// 151. Reverse Words in a String
// DescriptionHintsSubmissionsDiscussSolution
// Given an input string, reverse the string word by word.
//
//
//
// Example 1:
//
// Input: "the sky is blue"
// Output: "blue is sky the"
// Example 2:
//
// Input: "  hello world!  "
// Output: "world! hello"
// Explanation: Your reversed string should not contain leading or trailing spaces.
// Example 3:
//
// Input: "a good   example"
// Output: "example good a"
// Explanation: You need to reduce multiple spaces between two words to a single space in the reversed string.
//
//
// Note:
//
// A word is defined as a sequence of non-space characters.
// Input string may contain leading or trailing spaces. However, your reversed string should not contain leading or trailing spaces.
// You need to reduce multiple spaces between two words to a single space in the reversed string.
//
//
// Follow up:
//
// For C programmers, try to solve it in-place in O(1) extra space.
//
// Seen this question in a real interview before?
// Difficulty:Medium


public class Solution {
    public String reverseWords(String s) {
        if (s == null || s.length() < 2) {
            // input would be " "
            return s.trim();
        }

        // return mytry(s);

        return method2(s);
    }

    private String method2(String s) {
        s = cleanSpaces(s.toCharArray());
        char[] arr = s.toCharArray();
        int n = arr.length;
        reverse(arr, 0, n - 1);
        reverseWords(arr, n);
        return new String(arr);
    }
    private void reverseWords(char[] arr, int n) {
        int left = 0;
        int right = 0;
        while (left < n) {
            while (left < right || left < n && arr[left] == ' ') {
                left++;
            }
            while (right < left || right < n && arr[right] != ' ') {
                right++;
            }
            reverse(arr, left, right - 1);
        }
    }
    private void reverse(char[] arr, int start, int end) {
        while (start < end) {
            char temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }
    private String cleanSpaces(char[] arr) {
        int n = arr.length;
        int left = 0;
        int right = 0;
        while (right < n) {
            while (right < n && arr[right] == ' ') {
                right++;
            }
            while (right < n && arr[right] != ' ') {
                arr[left++] = arr[right++];
            }
            while (right < n && arr[right] == ' ') {
                right++;
            }
            if (right < n) {
                arr[left++] = ' ';
            }
        }
        return new String(arr).substring(0, left);
    }

    private String mytry(String s) {
        String[] arr = s.split(" +"); // there would be 1 more space
        StringBuilder sb = new StringBuilder();
        for (int i = arr.length - 1; i >= 0; i--) {
            sb.append(arr[i]);
            sb.append(" ");
        }
        return sb.toString().trim();
    }
}

// 186. Reverse Words in a String II
// DescriptionHintsSubmissionsDiscussSolution
// Given an input string , reverse the string word by word.
//
// Example:
//
// Input:  ["t","h","e"," ","s","k","y"," ","i","s"," ","b","l","u","e"]
// Output: ["b","l","u","e"," ","i","s"," ","s","k","y"," ","t","h","e"]
// Note:
//
// A word is defined as a sequence of non-space characters.
// The input string does not contain leading or trailing spaces.
// The words are always separated by a single space.
// Follow up: Could you do it in-place without allocating extra space?
//
// Seen this question in a real interview before?
// Difficulty:Medium


class Solution {
    public void reverseWords(char[] str) {
        if (str == null || str.length <= 1) {
            return;
        }

        method1(str);
    }

    private void method1(char[] str) {
        // it's given a char array, so it's different to deal with a string array
        // actually we found the order of a single word should be reversed, so in "macroscopical range", the total order should be reversed, then we deal with each single word to reverse it back to a correct word
        int n = str.length;
        // 1. reverse whole char array
        reverse(str, 0, n - 1);
        // 2. reverse each single word
        int start = 0;
        for (int i = 1; i < n; i++) {
            if (str[i] != ' ') {
                continue;
            }
            reverse(str, start, i - 1);
            start = i + 1;
            i++;
        }
        // 3. no leading/trailing zeros, so don't forget the last single word
        reverse(str, start, n - 1);
    }
    private void reverse(char[] str, int start, int end) {
        while (start < end) {
            char temp = str[start];
            str[start] = str[end];
            str[end] = temp;
            start++;
            end--;
        }
    }
}

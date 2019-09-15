// 344. Reverse String
// DescriptionHintsSubmissionsDiscussSolution
// Write a function that reverses a string. The input string is given as an array of characters char[].
//
// Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
//
// You may assume all the characters consist of printable ascii characters.
//
//
//
// Example 1:
//
// Input: ["h","e","l","l","o"]
// Output: ["o","l","l","e","h"]
// Example 2:
//
// Input: ["H","a","n","n","a","h"]
// Output: ["h","a","n","n","a","H"]
// Seen this question in a real interview before?
// Difficulty:Easy


class Solution {
    public String reverseString(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }
        // StringBuilder sb = new StringBuilder();
        // for (int i = s.length() - 1; i>= 0; i--) {
        //     sb.append(s.charAt(i));
        // }
        // return sb.toString(); // cannot do trim() since there my be space in original given string

        // cheating by API: String does not have reverse(), but StringBuilder has!
        return new StringBuilder(s).reverse().toString();
    }
}

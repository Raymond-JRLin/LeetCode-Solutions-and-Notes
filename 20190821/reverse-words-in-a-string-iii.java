// 557. Reverse Words in a String III
// DescriptionHintsSubmissionsDiscussSolution
//
// Given a string, you need to reverse the order of characters in each word within a sentence while still preserving whitespace and initial word order.
//
// Example 1:
//
// Input: "Let's take LeetCode contest"
// Output: "s'teL ekat edoCteeL tsetnoc"
//
// Note: In the string, each word is separated by single space and there will not be any extra space in the string.
// Seen this question in a real interview before?
//
//     Difficulty:Easy


class Solution {
    public String reverseWords(String s) {
        if (s == null || s.isEmpty()) {
            return s;
        }

        return mytry(s);
    }

    private String mytry(String s) {
        String[] arr = s.split(" ");
        for (int i = 0; i < arr.length; i++) {
            arr[i] = new StringBuilder(arr[i]).reverse().toString();;
        }
        StringBuilder sb = new StringBuilder();
        for (String str : arr) {
            sb.append(str).append(" ");
        }
        return sb.toString().trim();
    }
}

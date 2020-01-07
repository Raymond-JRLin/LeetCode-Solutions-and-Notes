// 709. To Lower Case
// DescriptionHintsSubmissionsDiscussSolution
//
// Implement function ToLowerCase() that has a string parameter str, and returns the same string in lowercase.
//
//
//
// Example 1:
//
// Input: "Hello"
// Output: "hello"
//
// Example 2:
//
// Input: "here"
// Output: "here"
//
// Example 3:
//
// Input: "LOVELY"
// Output: "lovely"
//
// Seen this question in a real interview before?
//
//     Difficulty:Easy


class Solution {
    public String toLowerCase(String str) {

        // return mytry(str);

        return mytry2(str);
    }

    private String mytry2(String s) {
        char[] arr = s.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            char c = arr[i];
            if (c >= 'A' && c <= 'Z') {
                arr[i] = (char) (c - 'A' + 'a');
            }
        }
        return String.valueOf(arr);
    }

    private String mytry(String s) {
        char[] arr = s.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Character.toLowerCase(arr[i]);
        }
        return String.valueOf(arr);
    }
}

// 345. Reverse Vowels of a String
// DescriptionHintsSubmissionsDiscussSolution
// Write a function that takes a string as input and reverse only the vowels of a string.
//
// Example 1:
//
// Input: "hello"
// Output: "holle"
// Example 2:
//
// Input: "leetcode"
// Output: "leotcede"
// Note:
// The vowels does not include the letter "y".
//
//
//
// Seen this question in a real interview before?
// Difficulty:Easy


class Solution {
    public String reverseVowels(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }

        return mytry(s);
    }

    private String mytry(String s) {
        // 2 pointers, similar to reverse a string
        int left = 0;
        int right = s.length() - 1;
        Set<Character> set = new HashSet<>();
        set.add('a');
        set.add('e');
        set.add('i');
        set.add('o');
        set.add('u');
        set.add('A');
        set.add('E');
        set.add('I');
        set.add('O');
        set.add('U');
        char[] arr = s.toCharArray();
        while (left < right) {
            while (left < right && !set.contains(arr[left])) {
                left++;
            }
            while (left < right && !set.contains(arr[right])) {
                right--;
            }
            // System.out.println("now right are " + right);
            if (left < right) {
                char temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
                left++;
                right--;
            }
        }
        return String.valueOf(arr);
    }
}

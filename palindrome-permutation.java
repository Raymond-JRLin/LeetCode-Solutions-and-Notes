// 266. Palindrome Permutation
// DescriptionHintsSubmissionsDiscussSolution
// Given a string, determine if a permutation of the string could form a palindrome.
//
// Example 1:
//
// Input: "code"
// Output: false
// Example 2:
//
// Input: "aab"
// Output: true
// Example 3:
//
// Input: "carerac"
// Output: true
// Seen this question in a real interview before?
// Difficulty:Easy


class Solution {
    public boolean canPermutePalindrome(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }

        // return mytry(s);

        return method2(s);
    }

    private boolean method2(String s) {
        // O(N) time and O(N) space
        Set<Character> set = new HashSet<>();
        for (char c : s.toCharArray()) {
            if (!set.add(c)) {
                // add failed, which means there's already same char in set
                set.remove(c);
            }
        }
        return set.size() <= 1;
    }

    // check more methods on https://leetcode.com/articles/palindrome-permutation/

    private boolean mytry(String s) {
        // O(N) time and O(256) space
        int[] nums = new int[256]; // problem doesn't say there's only letters
        for (char c : s.toCharArray()) {
            nums[c]++;
        }
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] % 2 != 0) {
                count++;
            }
        }
        return count <= 1;
    }
}

// 409. Longest Palindrome
// DescriptionHintsSubmissionsDiscussSolution
// Given a string which consists of lowercase or uppercase letters, find the length of the longest palindromes that can be built with those letters.
//
// This is case sensitive, for example "Aa" is not considered a palindrome here.
//
// Note:
// Assume the length of given string will not exceed 1,010.
//
// Example:
//
// Input:
// "abccccdd"
//
// Output:
// 7
//
// Explanation:
// One longest palindrome that can be built is "dccaccd", whose length is 7.
// Seen this question in a real interview before?
// Difficulty:Easy


class Solution {
    public int longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        // return mytry(s);

        // return method2(s);

        // return method3(s);

        return method4(s);
    }

    private int method4(String s) {
        // O(N) time and space
        Set<Character> set = new HashSet<>();
        int count = 0;
        for (char c : s.toCharArray()) {
            if (set.contains(c)) {
                set.remove(c);
                count++;
            } else {
                set.add(c);
            }
        }
        if (set.isEmpty()) {
            count *= 2;
        } else {
            count = 2 * count + 1;
        }
        return count;
    }

    private int method3(String s) {
        // O(N) time and O(1) space
        int[] nums = new int[128];
        for (char c : s.toCharArray()) {
            nums[c - 'A']++;
        }

        int result = 0;
        for (int num : nums) {
            result += num / 2 * 2;
            // we check this after whole loop, but we can also do it in loop
            // if we have a char with odd number, then we can add one, after adding one to final result, then result would be always odd
            if (result % 2 == 0 && num % 2 != 0) {
                result++;
            }
        }
        return result;
    }

    private int method2(String s) {
        // O(N) time and O(1) space
        int[] nums = new int[128]; // in ASCII table, UPPER case is in front of lower case AND they're not consecutive, so we cannot new int[52]
        for (char c : s.toCharArray()) {
            nums[c - 'A']++;
        }

        boolean hasOdd = false;
        int result = 0;
        for (int num : nums) {
            if (num != 0) {
                result += num / 2 * 2;
                if (num % 2 != 0) {
                    hasOdd = true;
                }
            }
        }
        if (hasOdd) {
            result++;
        }
        return result;
    }

    private int mytry(String s) {
        // O(N) time and space
        Map<Character, Integer> map = new HashMap<>(); // <char, freq>
        for (char c : s.toCharArray()) {
            int freq = map.getOrDefault(c, 0);
            freq++;
            map.put(c, freq);
        }
        int count = 0;
        boolean hasOdd = false;
        int result = 0;
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            count++;
            int freq = entry.getValue();
            if (freq % 2 != 0) {
                hasOdd = true;
            }
            result += freq / 2 * 2;
        }

        if (hasOdd || count < map.size()) {
            result++;
        }
        return result;
    }
}

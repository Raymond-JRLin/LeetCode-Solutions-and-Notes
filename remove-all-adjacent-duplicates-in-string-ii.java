// 1209. Remove All Adjacent Duplicates in String II
// User Accepted: 2168
// User Tried: 2530
// Total Accepted: 2201
// Total Submissions: 3922
// Difficulty: Medium
// Given a string s, a k duplicate removal consists of choosing k adjacent and equal letters from s and removing them causing the left and the right side of the deleted substring to concatenate together.
//
// We repeatedly make k duplicate removals on s until we no longer can.
//
// Return the final string after all such duplicate removals have been made.
//
// It is guaranteed that the answer is unique.
//
//
//
// Example 1:
//
// Input: s = "abcd", k = 2
// Output: "abcd"
// Explanation: There's nothing to delete.
// Example 2:
//
// Input: s = "deeedbbcccbdaa", k = 3
// Output: "aa"
// Explanation:
// First delete "eee" and "ccc", get "ddbbbdaa"
// Then delete "bbb", get "dddaa"
// Finally delete "ddd", get "aa"
// Example 3:
//
// Input: s = "pbbcggttciiippooaais", k = 2
// Output: "ps"
//
//
// Constraints:
//
// 1 <= s.length <= 10^5
// 2 <= k <= 10^4
// s only contains lower case English letters.


class Solution {
    public String removeDuplicates(String s, int k) {
        if (s == null || s.length() == 0 || k == 0) {
            return s;
        }

        // return mytry(s, k);

        return method2(s, k);
    }

    private String method2(String s, int k) {
        // iteration 那很明显就是用 Stack， 但是 contest 的时候只想到了 recursion
        Stack<Character> chars = new Stack<>();
        Stack<Integer> count = new Stack<>();
        for (char c : s.toCharArray()) {
            if (!chars.isEmpty() && chars.peek() == c) {
                count.push(count.peek() + 1);
            } else {
                count.push(1);
            }
            chars.push(c);
            if (count.peek() == k) {
                for (int i = 0; i < k; i++) {
                    chars.pop();
                    count.pop();
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!chars.isEmpty()) {
            sb.append(chars.pop());
        }
        return sb.reverse().toString();
    }

    private String mytry(String s, int k) {
        if (s.length() < k) {
            return s;
        }
        String curr = s;
        for (int i = 0; i < s.length() + 1 - k; i++) {
            if (hasKSame(s, i, k)) {
                s = s.substring(0, i) + s.substring(i + k, s.length());
            }
        }
        if (s.equals(curr)) {
            return s;
        }
        return mytry(s, k);
    }
    private boolean hasKSame(String s, int start, int k) {
        char c = s.charAt(start);
        for (int i = 0; i < k; i++) {
            if (s.charAt(start + i) != c) {
                return false;
            }
        }
        return true;
    }
}

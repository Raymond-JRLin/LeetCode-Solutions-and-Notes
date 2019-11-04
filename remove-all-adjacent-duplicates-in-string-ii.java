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

        // return method2(s, k);

        return method3(s, k);
    }

    private String method3(String s, int k) {
        // 1047 题一样的 2 pointers
        int n = s.length();
        char[] array = s.toCharArray();
        int[] count = new int[n];
        int j = 0;
        for (int i = 0; i < n; i++) {
            array[j] = array[i];
            if (j > 0 && array[j - 1] == array[i]) {
                count[j] = count[j - 1] + 1;
            } else {
                count[j] = 1;
            }
            if (count[j] == k) {
                j -= k;
            }
            j++;
        }
        return new String(array, 0, j);
    }

    private String method2(String s, int k) {
        // iteration 那很明显就是用 Stack， 但是 contest 的时候只想到了 recursion
        // 其实是 1047. Remove All Adjacent Duplicates In String 的 follow-up
        // k 从 2 个变成了不确定的 k 个， 此时没办法用一个 stack 查 peek 是否一不一样
        // 所以可以再有一个 stack， 放入放 char 的 stack 中 char 相对应的个数
        // 然后就喝 1047 题是一样的了， 有 k 个的时候， 把 k 个全 pop 出来
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

// 1047. Remove All Adjacent Duplicates In String
// DescriptionHintsSubmissionsDiscussSolution
//
// Given a string S of lowercase letters, a duplicate removal consists of choosing two adjacent and equal letters, and removing them.
//
// We repeatedly make duplicate removals on S until we no longer can.
//
// Return the final string after all such duplicate removals have been made.  It is guaranteed the answer is unique.
//
//
//
// Example 1:
//
// Input: "abbaca"
// Output: "ca"
// Explanation:
// For example, in "abbaca" we could remove "bb" since the letters are adjacent and equal, and this is the only possible move.  The result of this move is that the string is "aaca", of which only "aa" is possible, so the final string is "ca".
//
//
//
// Note:
//
//     1 <= S.length <= 20000
//     S consists only of English lowercase letters.
//
// Seen this question in a real interview before?
//
//     Difficulty:Easy


class Solution {
    public String removeDuplicates(String S) {
        if (S == null || S.length() == 0) {
            return S;
        }

        // return method1(S);

        return method2(S);
    }

    private String method2(String s) {
        // 2 pointers
        // 非常巧妙， 利用 pointer 的回退
        int n = s.length();
        char[] array = s.toCharArray();
        int[] count = new int[n]; // 记录 array[j] 对应的 char 的个数
        int j = 0; // 指向最后真正要返回的 string 的 end index (exclusive)
        for (int i = 0; i < n; i++) {
            // i 指针顺着 s 往下走
            // j 先 copy i 的 char
            array[j] = array[i];
            // 计算 j 当前 char 的个数
            if (j > 0 && array[j - 1] == array[i]) {
                count[j] = count[j - 1] + 1;
            } else {
                count[j] = 1;
            }
            // j 当前 char 的个数已经有 2 个了， 即 j - 1 & j
            // 直接回退 j 指针， - 2 因为最后正常情况 j 也需要 ++， 所以最后 j 会往前补一个
            if (count[j] == 2) {
                j -= 2;
            }
            j++;
        }
        return new String(array, 0, j);
    }

    private String method1(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (!stack.isEmpty() && stack.peek() == c) {
                stack.pop();
            } else {
                stack.push(c);
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.reverse().toString();
    }
}

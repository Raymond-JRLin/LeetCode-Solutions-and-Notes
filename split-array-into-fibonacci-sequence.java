// 842. Split Array into Fibonacci Sequence
// DescriptionHintsSubmissionsDiscussSolution
// Given a string S of digits, such as S = "123456579", we can split it into a Fibonacci-like sequence [123, 456, 579].
//
// Formally, a Fibonacci-like sequence is a list F of non-negative integers such that:
//
// 0 <= F[i] <= 2^31 - 1, (that is, each integer fits a 32-bit signed integer type);
// F.length >= 3;
// and F[i] + F[i+1] = F[i+2] for all 0 <= i < F.length - 2.
// Also, note that when splitting the string into pieces, each piece must not have extra leading zeroes, except if the piece is the number 0 itself.
//
// Return any Fibonacci-like sequence split from S, or return [] if it cannot be done.
//
// Example 1:
//
// Input: "123456579"
// Output: [123,456,579]
// Example 2:
//
// Input: "11235813"
// Output: [1,1,2,3,5,8,13]
// Example 3:
//
// Input: "112358130"
// Output: []
// Explanation: The task is impossible.
// Example 4:
//
// Input: "0123"
// Output: []
// Explanation: Leading zeroes are not allowed, so "01", "2", "3" is not valid.
// Example 5:
//
// Input: "1101111"
// Output: [110, 1, 111]
// Explanation: The output [11, 0, 11, 11] would also be accepted.
// Note:
//
// 1 <= S.length <= 200
// S contains only digits.
// Seen this question in a real interview before?
// Difficulty:Medium


class Solution {
    public List<Integer> splitIntoFibonacci(String S) {
        if (S == null || S.length() == 0) {
            return Collections.emptyList();
        }

        return method1(S);
    }

    private List<Integer> method1(String s) {
        // actually when I see this problem, I think we should check every possibility to find out the result -> DFS, refer does pretty well, I think 2 points: 1- how to get the fibonacci process in Math, i.e. translate math process to code (attention problem requires 0 <= F[i] <= 2^31 - 1 and F.length >= 3), 2- how to define DFS process
        // ref: https://leetcode.com/problems/split-array-into-fibonacci-sequence/discuss/133936/short-and-fast-backtracking-solution
        List<Integer> result = new ArrayList<>();
        dfs(s, result, 0);
        return result;
    }
    private boolean dfs(String s, List<Integer> result, int index) {
        // since we need to know if this split can word, we have a boolean return value, such that we can stop or continue to next level
        // complete a successful split
        if (index == s.length() && result.size() >= 3) {
            return true;
        }
        // split starting this index and end of i (including) and go checking each possibility
        for (int i = index; i < s.length(); i++) {
            if (s.charAt(index) =='0' && i > index) {
                // edge case: leading 0, if it's a single digit, then 0 is valid
                return false;
            }
            long num = Long.parseLong(s.substring(index, i + 1));
            if (num > Integer.MAX_VALUE) {
                // over int range
                return false;
            }
            int size = result.size();
            if (size >= 2 && num > result.get(size - 1) + result.get(size - 2)) {
                // not match fibonacci rule, attention we only return false directly when it over previous 2 sum, if it's less thant previous 2 sum, we continue to do this for loop
                return false;
            }
            if (size < 2 || num == result.get(size - 1) + result.get(size - 2)) {
                // this step can be splitted correctly
                result.add((int) num);
                // if we can split rest of string, then this split way is the answer
                if (dfs(s, result, i + 1)) {
                    return true;
                }
                // otherwise, backtracking
                result.remove(result.size() - 1);
            }
        }
        return false;
    }
}

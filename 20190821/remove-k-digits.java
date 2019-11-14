// 402. Remove K Digits
// DescriptionHintsSubmissionsDiscussSolution
//
// Given a non-negative integer num represented as a string, remove k digits from the number so that the new number is the smallest possible.
//
// Note:
//
//     The length of num is less than 10002 and will be ≥ k.
//     The given num does not contain any leading zero.
//
// Example 1:
//
// Input: num = "1432219", k = 3
// Output: "1219"
// Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.
//
// Example 2:
//
// Input: num = "10200", k = 1
// Output: "200"
// Explanation: Remove the leading 1 and the number is 200. Note that the output must not contain leading zeroes.
//
// Example 3:
//
// Input: num = "10", k = 2
// Output: "0"
// Explanation: Remove all the digits from the number and it is left with nothing which is 0.
//
// Seen this question in a real interview before?
//
//     Difficulty:Medium


class Solution {
    public String removeKdigits(String num, int k) {
        if (num == null || num.length() == 0) {
            return num;
        }
        if (num.length() == k) {
            return "0";
        }

        // return mytry(num, k);

        return mytry2(num, k);
    }

    private String mytry2(String num, int k) {
        // greedy with Stack
        // 从前往后， 碰到更小的数， 就把前面比它大的抹掉
        // Let's think about the simplest case: how to remove 1 digit from the number so that the new number is the smallest possible？ Well, one can simply scan from left to right, and remove the first "peak" digit; the peak digit is larger than its right neighbor. We can repeat this procedure k times.
        int n = num.length();
        Stack<Character> stack = new Stack<>();
        int i = 0;
        while (i < n) {
            while (k > 0 && !stack.isEmpty() && stack.peek() > num.charAt(i)) {
                stack.pop();
                k--;
            }
            stack.push(num.charAt(i));
            i++;
        }

        while (k > 0) {
            stack.pop();
            k--;
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        String result = sb.reverse().toString();

        int index = 0;
        while (index < result.length() && result.charAt(index) == '0') {
            index++;
        }
        result = result.substring(index);
        return result.isEmpty() ? "0" : result;
    }

    private String mytry(String num, int k) {
        // DFS: TLE
        char[] arr = num.toCharArray();
        result = num;
        for (int i = 0; i < num.length(); i++) {
            // System.out.println("Starting from index = " + i + ", original string is " + num);
            dfs(num, k, i);
        }
        int index = 0;
        while (index < result.length() && result.charAt(index) == '0') {
            index++;
        }
        result = result.substring(index);
        return result.isEmpty() ? "0" : result;
    }
    private String result;
    private void dfs(String num, int k, int index) {
        // System.out.println("check " + num);
        if (k == 0) {
            // System.out.println("get one string = " + num);
            if (num.compareTo(result) < 0) {
                result = String.valueOf(num);
            }
            return;
        }
        for (int i = index; i < num.length(); i++) {

            String next = num.substring(0, i) + num.substring(i + 1);
            // System.out.println("remove " + num.charAt(i) + " at " + i + " to get: " + next);
            dfs(next, k - 1, i);
        }
    }
}

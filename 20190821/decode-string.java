// 394. Decode String
// DescriptionHintsSubmissionsDiscussSolution
//
// Given an encoded string, return its decoded string.
//
// The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.
//
// You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.
//
// Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there won't be input like 3a or 2[4].
//
// Examples:
//
// s = "3[a]2[bc]", return "aaabcbc".
// s = "3[a2[c]]", return "accaccacc".
// s = "2[abc]3[cd]ef", return "abcabccdcdcdef".
//
//
// Seen this question in a real interview before?
//
//     Difficulty:Medium


class Solution {
    public String decodeString(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }

        return mytry(s);
    }

    private String mytry(String s) {
        Stack<String> stack = new Stack<>();
        int num = 0;
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                num = 0;
                while (Character.isDigit(s.charAt(i))) {
                    num = num * 10 + (s.charAt(i) - '0');
                    i++;
                }
                stack.push(String.valueOf(num));
                i--;
            } else if (s.charAt(i) == ']') {
                String curr = "";
                while (!stack.isEmpty() && !stack.peek().equals("[")) {
                    curr = stack.pop() + curr;
                }
                stack.pop(); // pop "["
                int repeat = Integer.valueOf(stack.pop()); // pop number
                String next = duplicate(curr, repeat);
                stack.push(next);
            } else {
                stack.push(String.valueOf(s.charAt(i)));
            }
        }
        String result = "";
        while (!stack.isEmpty()) {
            result = stack.pop() + result;
        }
        return result;
    }
    private String duplicate(String s, int n) {
        String result = "";
        for (int i = 0; i < n; i++) {
            result += s;
        }
        return result;
    }
}

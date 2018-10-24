// 394. Decode String
// DescriptionHintsSubmissionsDiscussSolution
// Given an encoded string, return it's decoded string.
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


class Solution {
    public String decodeString(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }

        return method1(s);

        // return method2(s);
    }

    private String method2(String s) {
        // use 2 stacks
        // ref: https://leetcode.com/problems/decode-string/discuss/87534/Simple-Java-Solution-using-Stack
        Stack<String> stack = new Stack(); // put string result
        Stack<Integer> count = new Stack(); // put numbers
        String curr = "";
        int i = 0;
        while (i < s.length()) {
            char c = s.charAt(i);
            if (c == '[') {
                // 注意的是要在这里把当前结果放入 stack 中， 然后重新设置 curr 为空的 string， 因为我们要返回的是 curr 而不是 stack 中的， 因为有可能没有数字， 所以不能在 ] 结束的时候加入
                stack.push(curr);
                curr = "";
                i++;
            } else if (c == ']') {
                // 计算 repeat 的 string， 可以不需要判断 stack 是否为空来 pop 出之前一次的结果， 因为我们是在 [ 的时候放入， 所以第一个前面一定会有一个 ""
                curr = repeat(curr, count.pop());
                // if (!stack.isEmpty()) {
                    curr = stack.pop() + curr;
                // }
                i++;
            } else if (Character.isDigit(c)) {
                // 得到数字并放入数字的 stack 中
                int num = 0;
                while (Character.isDigit(s.charAt(i))) {
                    num = num * 10 + (s.charAt(i) - '0');
                    i++;
                }
                count.push(num);
            } else {
                // 其他情况直接加到当前的 string 上
                curr += c;
                i++;
            }
        }
        return curr;
    }

    private String method1(String s) {
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++){
            //System.out.println("current char is " + s.charAt(i));
            if (s.charAt(i) != ']') {
                stack.push(String.valueOf(s.charAt(i)));
                continue;
            }
            String curr = "";
            while (!stack.peek().equals("[")) {
                curr = stack.pop() + curr;
            }
            stack.pop(); // pop("[")
            // get repeat number
            // 注意这里是倒着求， 所以如果是用 num * 10 + char 的话譬如 100 那么就只会得到 1， 所以要用 string 先得到再转为 int
            String num = "";
            while (!stack.isEmpty() && Character.isDigit(stack.peek().charAt(0))) {
                num = stack.pop() + num;
            }
            if (num.equals(0)) {
                continue;
            }

            stack.push(repeat(curr, Integer.parseInt(num)));
        }
        String result = "";
        while (!stack.isEmpty()) {
            result = stack.pop() + result;
        }
        return result;
    }
    private String repeat(String s, int times) {
        String res = "";
        for (int i = 0; i < times; i++) {
            res += s;
        }
        return res;
    }
}

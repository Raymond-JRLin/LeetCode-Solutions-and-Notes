// 38. Count and Say
// DescriptionHintsSubmissionsDiscussSolution
// The count-and-say sequence is the sequence of integers with the first five terms as following:
//
// 1.     1
// 2.     11
// 3.     21
// 4.     1211
// 5.     111221
// 1 is read off as "one 1" or 11.
// 11 is read off as "two 1s" or 21.
// 21 is read off as "one 2, then one 1" or 1211.
//
// Given an integer n where 1 ≤ n ≤ 30, generate the nth term of the count-and-say sequence.
//
// Note: Each term of the sequence of integers will be represented as a string.
//
//
//
// Example 1:
//
// Input: 1
// Output: "1"
// Example 2:
//
// Input: 4
// Output: "1211"


class Solution {
    public String countAndSay(int n) {
        // 看清楚题目意思LOL： 后一个数是读上一个数而生成的
        if (n == 1) {
            return "1";
        }

        return my_try(n);
    }

    private String my_try(int n) {
        String curr = "1";
        int count = 1;
        while (count < n) {
            String prev = curr;
            curr = new String("");
            char say = prev.charAt(0);
            int repeat = 1;
            for (int i = 1; i < prev.length(); i++) {
                if (prev.charAt(i) == say) {
                    repeat++;
                } else {
                    curr += String.valueOf(repeat) + String.valueOf(say);
                    say = prev.charAt(i);
                    repeat = 1;
                }
            }
            curr += String.valueOf(repeat) + String.valueOf(say);
            count++;
        }
        return curr;
    }
}

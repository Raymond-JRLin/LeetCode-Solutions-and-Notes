// 273. Integer to English Words
// DescriptionHintsSubmissionsDiscussSolution
//
// Convert a non-negative integer to its english words representation. Given input is guaranteed to be less than 231 - 1.
//
// Example 1:
//
// Input: 123
// Output: "One Hundred Twenty Three"
//
// Example 2:
//
// Input: 12345
// Output: "Twelve Thousand Three Hundred Forty Five"
//
// Example 3:
//
// Input: 1234567
// Output: "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
//
// Example 4:
//
// Input: 1234567891
// Output: "One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven Thousand Eight Hundred Ninety One"
//
// Seen this question in a real interview before?
//
//     Difficulty:Hard


class Solution {
    public String numberToWords(int num) {
        if (num == 0) {
            return "Zero";
        }

        return method1(num);
    }

    String[] unit = new String[]{"", "Thousand", "Million", "Billion"};
    String[] lessTwenty = new String[]{"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine",
                                    "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen",
                                    "Seventeen","Eighteen", "Nineteen"};
    String[] tens = new String[]{"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};

    private String method1(int num) {

        int mod = 1000;
        int index = 0;
        String result = "";
        while (num != 0) {
            int curr = num % mod;
            // 后面一串 0 不要， 不然会有单独的 unit 出现
            if (curr != 0) {
                String s = getString(curr); // s 带空格
                result = s + unit[index] + " " + result;
            }
            num /= mod;
            index++;
        }
        return result.trim();
    }

    private String getString(int num) {
        if (num == 0) {
            return "";
        } else if (num < 20) {
            return lessTwenty[num] + " ";
        } else if (num < 100) {
            return tens[num / 10] + " " + getString(num % 10);
        } else {
            return lessTwenty[num / 100] + " Hundred " + getString(num % 100);
        }
    }
}

// 415. Add Strings
// DescriptionHintsSubmissionsDiscussSolution
//
// Given two non-negative integers num1 and num2 represented as string, return the sum of num1 and num2.
//
// Note:
//
//     The length of both num1 and num2 is < 5100.
//     Both num1 and num2 contains only digits 0-9.
//     Both num1 and num2 does not contain any leading zero.
//     You must not use any built-in BigInteger library or convert the inputs to integer directly.
//
// Seen this question in a real interview before?
//
//     Difficulty:Easy


class Solution {
    public String addStrings(String num1, String num2) {
        if (num1 == null || num1.isEmpty()) {
            return num2;
        }
        if (num2 == null || num2.isEmpty()) {
            return num1;
        }

        return mytry(num1, num2);
    }

    private String mytry(String num1, String num2) {
        int n = num1.length();
        int m = num2.length();
        int left = n - 1;
        int right = m - 1;
        String result = "";
        int residue = 0;
        while (left >= 0 || right >= 0) {
            int a = left >= 0 ? num1.charAt(left) - '0' : 0;
            int b = right >= 0 ? num2.charAt(right) - '0' : 0;
            int sum = a + b + residue;
            result = (sum % 10) + result;
            residue = sum / 10;
            left--;
            right--;
        }
        if (residue != 0) {
            result = residue + result;
        }
        return result;
    }
}

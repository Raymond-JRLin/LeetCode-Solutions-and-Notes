// 67. Add Binary
// DescriptionHintsSubmissionsDiscussSolution
// Given two binary strings, return their sum (also a binary string).
//
// The input strings are both non-empty and contains only characters 1 or 0.
//
// Example 1:
//
// Input: a = "11", b = "1"
// Output: "100"
// Example 2:
//
// Input: a = "1010", b = "1011"
// Output: "10101"


class Solution {
    public String addBinary(String a, String b) {

        return mytry(a, b);
    }

    private String mytry(String a, String b) {
        int n = a.length();
        int m = b.length();
        int i = n - 1;
        int j = m - 1;
        int carry = 0;
        StringBuilder sb = new StringBuilder();
        while (i >= 0 || j >= 0) {
            if (i >= 0) {
                carry += a.charAt(i) - '0';
                i--;
            }
            if (j >= 0) {
                carry += b.charAt(j) - '0';
                j--;
            }
            sb.insert(0, carry % 2);
            carry /= 2;
        }
        if (carry != 0) {
            sb.insert(0, carry);
        }
        return sb.toString();
    }
}

// 537. Complex Number Multiplication
// DescriptionHintsSubmissionsDiscussSolution
// Given two strings representing two complex numbers.
//
// You need to return a string representing their multiplication. Note i2 = -1 according to the definition.
//
// Example 1:
// Input: "1+1i", "1+1i"
// Output: "0+2i"
// Explanation: (1 + i) * (1 + i) = 1 + i2 + 2 * i = 2i, and you need convert it to the form of 0+2i.
// Example 2:
// Input: "1+-1i", "1+-1i"
// Output: "0+-2i"
// Explanation: (1 - i) * (1 - i) = 1 + i2 - 2 * i = -2i, and you need convert it to the form of 0+-2i.
// Note:
//
// The input strings will not have extra blank.
// The input strings will be given in the form of a+bi, where the integer a and b will both belong to the range of [-100, 100]. And the output should be also in this form.


class Solution {
    public String complexNumberMultiply(String a, String b) {
        if (a == null && b == null) {
            return null;
        }
        if (a == null) {
            return b;
        }
        if (b == null) {
            return a;
        }
        String[] first = a.split("\\+");
        String[] second = b.split("\\+");
//         {real, imaginary}
        int a_real = Integer.parseInt(first[0]);
        int b_real = Integer.parseInt(second[0]);
        String[] a_imaginary = first[1].trim().split("i");
        String[] b_imaginary = second[1].trim().split("i");
        int a_imag = Integer.parseInt(a_imaginary[0]);
        int b_imag = Integer.parseInt(b_imaginary[0]);
        int real_product = a_real * b_real;
        int imag = a_real * b_imag + b_real * a_imag;
        int plus = a_imag * b_imag * -1;
        int real = real_product + plus;
        String result = String.valueOf(real) + "+" + String.valueOf(imag) + String.valueOf("i");
        return result;
    }
}

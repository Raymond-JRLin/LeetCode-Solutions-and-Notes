// 592. Fraction Addition and Subtraction
// DescriptionHintsSubmissionsDiscussSolution
// Given a string representing an expression of fraction addition and subtraction, you need to return the calculation result in string format. The final result should be irreducible fraction. If your final result is an integer, say 2, you need to change it to the format of fraction that has denominator 1. So in this case, 2 should be converted to 2/1.
//
// Example 1:
// Input:"-1/2+1/2"
// Output: "0/1"
// Example 2:
// Input:"-1/2+1/2+1/3"
// Output: "1/3"
// Example 3:
// Input:"1/3-1/2"
// Output: "-1/6"
// Example 4:
// Input:"5/3+1/3"
// Output: "2/1"
// Note:
// The input string only contains '0' to '9', '/', '+' and '-'. So does the output.
// Each fraction (input and output) has format ±numerator/denominator. If the first input fraction or the output is positive, then '+' will be omitted.
// The input only contains valid irreducible fractions, where the numerator and denominator of each fraction will always be in the range [1,10]. If the denominator is 1, it means this fraction is actually an integer in a fraction format defined above.
// The number of given fractions will be in the range [1,10].
// The numerator and denominator of the final result are guaranteed to be valid and in the range of 32-bit int.
// Seen this question in a real interview before?
// Difficulty:Medium


class Solution {
    public String fractionAddition(String expression) {
        // 就一个个读， 然后按照数学公式计算， 不过有些点要注意下， 比如要约分， 还有一些 trick， 比如设置初始值为 0 / 1
        if (expression == null || expression.length() == 0) {
            return "0/1";
        }
        int n = expression.length();
        List<String> nums = split(expression);
        String result = "0/1";
        for (String num : nums) {
            result = add(result, num);
        }
        return result;
    }
    private List<String> split(String expression) {
        int n = expression.length();
        List<String> result = new ArrayList<>();
        int start = 0;
        for (int i = 0; i <= n; i++) {
            // 因为要 substring 所以 n 要取到
            if (i == n) {
                result.add(expression.substring(start, i));
                break;
            }
            char c = expression.charAt(i);
            if (i != start && (c == '+' || c == '-')) {
                // 当 i == start 的时候会输入空的字符串， 这样要
                result.add(expression.substring(start, i));
                if (c == '+') {
                    start = i + 1;
                } else {
                    start = i;
                }
            }
        }
        return result;
    }
    private String add(String num1, String num2) {
        // result = (-) a / b, exp = (-) c / d => multiply directly => result * exp = (a * d + c * b) / (b * d) => gcd
        String[] first = num1.split("/");
        String[] second = num2.split("/");
        int a = Integer.parseInt(first[0]);
        int b = Integer.parseInt(first[1]);
        int c = Integer.parseInt(second[0]);
        int d = Integer.parseInt(second[1]);
        int div = a * d + c * b;
        if (div == 0) {
            return "0/1";
        }
        int fac = b * d;
        boolean isNeg = div * fac < 0 ? true : false;
        div = Math.abs(div);
        fac = Math.abs(fac);
        int gcd = gcd(div, fac);
        div = div / gcd;
        fac = fac / gcd;
        String result = div + "/" + fac;
        if (isNeg) {
            return "-" + result;
        } else {
            return result;
        }
    }
    private int gcd (int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
}

// 43. Multiply Strings
// DescriptionHintsSubmissionsDiscussSolution
// Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2, also represented as a string.
//
// Example 1:
//
// Input: num1 = "2", num2 = "3"
// Output: "6"
// Example 2:
//
// Input: num1 = "123", num2 = "456"
// Output: "56088"
// Note:
//
// The length of both num1 and num2 is < 110.
// Both num1 and num2 contain only digits 0-9.
// Both num1 and num2 do not contain any leading zero, except the number 0 itself.
// You must not use any built-in BigInteger library or convert the inputs to integer directly.


class Solution {
    public String multiply(String num1, String num2) {

        // return mytry(num1, num2);

        return method2(num1, num2);
    }

    private String method2(String num1, String num2) {
         // found: `num1[i] * num2[j]` will be placed at indices `[i + j`, `i + j + 1]`
        int n = num1.length();
        int m = num2.length();
        int[] result = new int[n + m];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                // since result array is the final result, so when we multiply, we should add previous results in result[k]
                int res = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                int p1 = i + j;
                int p2 = i + j + 1;
                res += result[p2];
                result[p2] = res % 10;
                result[p1] += res / 10;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int num : result) {
            if (sb.length() == 0 && num == 0) {
                continue;
            }
            sb.append(num);
        }
        return sb.length() == 0 ? "0" : sb.toString();
    }

    private String mytry(String num1, String num2) {
        // imitate multiplication process, O(N + M) time
        int n = num1.length();
        int m = num2.length();
        int[] result = new int[n + m];

        int index = n + m - 1;
        for (int i = n - 1; i >= 0; i--) {
            int a = num1.charAt(i) - '0';
            if (a == 0) {
                index--;
                continue; // if we wanna continue here, don't forget to minus index first
            }
            int carry = 0;
            int k = index;
            for (int j = m - 1; j >= 0; j--) {
                // since result array is the final result, so when we multiply, we should add previous results in result[k]
                int res = a * (num2.charAt(j) - '0') + carry + result[k];
                result[k--] = res % 10;
                carry = res / 10;
            }
            if (carry != 0) {
                result[k] += carry;
            }
            index--;
        }

        index = 0;
        while (index < n + m && result[index] == 0) {
            index++;
        }
        // edge case : 0 * 0
        if (index == n + m) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = index; i < result.length; i++) {
            sb.append(result[i]);
        }
        return sb.toString();
    }
}

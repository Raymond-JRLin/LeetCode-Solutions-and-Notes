// 166. Fraction to Recurring Decimal
// DescriptionHintsSubmissionsDiscussSolution
// Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.
//
// If the fractional part is repeating, enclose the repeating part in parentheses.
//
// Example 1:
//
// Input: numerator = 1, denominator = 2
// Output: "0.5"
// Example 2:
//
// Input: numerator = 2, denominator = 1
// Output: "2"
// Example 3:
//
// Input: numerator = 2, denominator = 3
// Output: "0.(6)"
// Seen this question in a real interview before?
// Difficulty:Medium


class Solution {
    public String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) {
            return String.valueOf(0);
        }
        if (denominator == 1) {
            return String.valueOf(numerator);
        }

        return mytry(numerator, denominator);
    }

    public String method2(int numerator, int denominator) {
    StringBuilder result = new StringBuilder();
    String sign = (numerator < 0 == denominator < 0 || numerator == 0) ? "" : "-";
    long num = Math.abs((long) numerator);
    long den = Math.abs((long) denominator);
    result.append(sign);
    result.append(num / den);
    long remainder = num % den;
    if (remainder == 0)
        return result.toString();
    result.append(".");
    HashMap<Long, Integer> hashMap = new HashMap<Long, Integer>();
    while (!hashMap.containsKey(remainder)) {
        System.out.println("remainder and index are: " + remainder + ", " + result.length());
        hashMap.put(remainder, result.length());
        result.append(10 * remainder / den);
        remainder = 10 * remainder % den;
    }
    int index = hashMap.get(remainder);
    result.insert(index, "(");
    result.append(")");
    return result.toString().replace("(0)", "");
}

    private String mytry(int numerator, int denominator) {
        // be careful about edge case
        String sign = "";
        if (numerator < 0 && denominator > 0 || numerator > 0 && denominator < 0) {
            sign = "-";
        }
        // edge case : MAX or MIN, so we should change to long type FIRST
        long num = Math.abs((long) numerator);
        long de = Math.abs((long) denominator);

        // System.out.println("new num and de are: " + num + ", " + de);
        StringBuilder sb = new StringBuilder();

        // 整数部分
        long res = num / de;
        long remainder = num % de;
        // if res == 0, then we should add "-" in front of result rather than * -1 directly
        String result = sign + res;
        // 恰好除的尽
        if (remainder == 0) {
            return result;
        }
        // 处理小数部分
        num = remainder * 10;
        int index = 0;
        // 注意的是 map 的 key 要放的应该是 remainder 而不是每次除出来的得数， 因为得数有可能相同但并不造成循环, e.g. 1 / 333
        Map<Long, Integer> map = new HashMap<>(); // <value, index>
        while (remainder != 0 && !map.containsKey(remainder)) {
            // System.out.println("remainder and index are: " + remainder + ", " + result.length());
            map.put(remainder, index++);
            res = num / de;
            remainder = num % de;
            sb.append(res);
            System.out.println("we append: " + res);
            num = remainder * 10;

        }
        if (remainder == 0) {
            return result + "." + sb.toString();
        }
        // String fraction = sb.toString();
        int n = sb.length();
        index = map.get(remainder);
        return result + "." + sb.substring(0, index) + "(" +  sb.substring(index, n) + ")";


    }
}

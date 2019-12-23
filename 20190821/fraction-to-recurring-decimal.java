// 166. Fraction to Recurring Decimal
// DescriptionHintsSubmissionsDiscussSolution
//
// Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.
//
// If the fractional part is repeating, enclose the repeating part in parentheses.
//
// Example 1:
//
// Input: numerator = 1, denominator = 2
// Output: "0.5"
//
// Example 2:
//
// Input: numerator = 2, denominator = 1
// Output: "2"
//
// Example 3:
//
// Input: numerator = 2, denominator = 3
// Output: "0.(6)"
//
// Seen this question in a real interview before?
//
//     Difficulty:Medium


class Solution {
    public String fractionToDecimal(int numerator, int denominator) {

        // return mytry(numerator, denominator);

        return method2(numerator, denominator);
    }

    private String method2(int numerator, int denominator) {
        // 但实际上判断是否有循环， 只要看 % 之后是否得到相同的结果， 因为这样下一轮会有一模一样的除数和被除数， 也就开始循环了
        // iteration 的做法
        if (numerator == 0) {
            return "0";
        }
        StringBuilder res = new StringBuilder();
        // "+" or "-"
        res.append(((numerator > 0) ^ (denominator > 0)) ? "-" : "");
        long num = Math.abs((long)numerator);
        long den = Math.abs((long)denominator);

        // integral part
        res.append(num / den);
        num %= den;
        if (num == 0) {
            return res.toString();
        }

        // fractional part
        res.append(".");
        HashMap<Long, Integer> map = new HashMap<Long, Integer>(); // <余数， sb 的长度 (也就是循环前面第一次出现的位置)>
        map.put(num, res.length());
        while (num != 0) {
            num *= 10;
            res.append(num / den);
            num %= den;
            if (map.containsKey(num)) {
                int index = map.get(num);
                res.insert(index, "(");
                res.append(")");
                break;
            }
            map.put(num, res.length());
        }
        return res.toString();
    }

    private String mytry(int numerator, int denominator) {
        if (numerator % denominator == 0) {
            return String.valueOf((long) numerator / (long) denominator);
        }
        String negative = "";
        if (numerator > 0 && denominator < 0 || numerator < 0 && denominator > 0) {
            negative = "-";
        }
        long num = Math.abs((long) numerator);
        long de = Math.abs((long) denominator); // avoid overflow
        long n = num % de;
        int[] index = new int[2];

        StringBuilder sb = new StringBuilder();
        Map<String, Integer> map = new HashMap<>();
        if (dfs(n, de, map, sb, index, 0)) {
            // has repeating fraction of decimal
            return negative + num / de + "." + sb.toString().substring(0, index[0]) + "(" + sb.toString().substring(index[0], index[1]) + ")";
        } else {
            return negative + num / de + "." + sb.toString();
        }
    }
    private boolean dfs(long num, long de, Map<String, Integer> map, StringBuilder sb, int[] index, int k) {
        if (num == 0) {
            return false;
        }
        long val = num * 10 / de;
        long residue = num * 10 % de;
        String curr = val + ":" + residue;
        // 出现循环就是做完除法的得数和余数在前面出现过
        if (map.containsKey(curr)) {
            index[0] = map.get(curr);
            index[1] = k;
            return true;
        }
        map.put(curr, k);
        sb.append(val);
        return dfs(residue, de, map, sb, index, k + 1);
    }
    private String getDecimalString(String s, int start, int end) {
        System.out.println(s);
        if (start == end) {
            return s;
        } else {
            return s.substring(0, start) + "(" + s.substring(start, end) + ")";
        }

    }
}

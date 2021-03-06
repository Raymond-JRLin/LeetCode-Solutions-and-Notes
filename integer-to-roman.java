// 12. Integer to Roman
// DescriptionHintsSubmissionsDiscussSolution
// Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.
//
// Symbol       Value
// I             1
// V             5
// X             10
// L             50
// C             100
// D             500
// M             1000
// For example, two is written as II in Roman numeral, just two one's added together. Twelve is written as, XII, which is simply X + II. The number twenty seven is written as XXVII, which is XX + V + II.
//
// Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII. Instead, the number four is written as IV. Because the one is before the five we subtract it making four. The same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:
//
// I can be placed before V (5) and X (10) to make 4 and 9.
// X can be placed before L (50) and C (100) to make 40 and 90.
// C can be placed before D (500) and M (1000) to make 400 and 900.
// Given an integer, convert it to a roman numeral. Input is guaranteed to be within the range from 1 to 3999.
//
// Example 1:
//
// Input: 3
// Output: "III"
// Example 2:
//
// Input: 4
// Output: "IV"
// Example 3:
//
// Input: 9
// Output: "IX"
// Example 4:
//
// Input: 58
// Output: "LVIII"
// Explanation: L = 50, V = 5, III = 3.
// Example 5:
//
// Input: 1994
// Output: "MCMXCIV"
// Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.
// Seen this question in a real interview before?
// Difficulty:Medium


class Solution {
    public String intToRoman(int num) {
        // return method1(num);

        // return method2(num);

        return method3(num);
    }
    private String method1(int n) {
        // 使用基本罗马数字及其规则
        char roman[] = {'M', 'D', 'C', 'L', 'X', 'V', 'I'};
        int value[] = {1000, 500, 100, 50, 10, 5, 1};
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 7; i += 2) {
            int res = n / value[i];
            // 分成 1 - 3， 4， 5 - 8， 9
            if (res < 4) {
                for (int j = 0; j < res; j++) {
                    sb.append(roman[i]);
                }
            } else if (res == 4) {
                sb.append(roman[i]).append(roman[i - 1]);
            } else if (res > 4 && res < 9) {
                sb.append(roman[i - 1]);
                for (int j = 5; j < res; j++) {
                    sb.append(roman[i]);
                }
            } else if (res == 9) {
                sb.append(roman[i]).append(roman[i - 2]);
            }
            n %= value[i];
        }
        return sb.toString().trim();
    }
    private String method2(int num) {
        // 因为 n 只取 1 - 3999， 所以可以贪心
        // 建立一个数表，每次通过查表找出当前最大的数，减去再继续查表。
        int[] values = {1000,900,500,400,100,90,50,40,10,9,5,4,1};
        String[] strs = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};

        StringBuilder sb = new StringBuilder();

        for(int i=0;i<values.length;i++) {
            while(num >= values[i]) {
                num -= values[i];
                sb.append(strs[i]);
            }
        }
        return sb.toString();
    }
    private String method3(int num) {
        // 把所有的情况都列了出来，然后直接按位查表
        String M[] = {"", "M", "MM", "MMM"};
        String C[] = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String X[] = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String I[] = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
        return M[num/1000] + C[(num%1000)/100] + X[(num%100)/10] + I[num%10];
    }
}

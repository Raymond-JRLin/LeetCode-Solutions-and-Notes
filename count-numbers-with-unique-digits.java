// 357. Count Numbers with Unique Digits
// DescriptionHintsSubmissionsDiscussSolution
// Given a non-negative integer n, count all numbers with unique digits, x, where 0 ≤ x < 10n.
//
// Example:
//
// Input: 2
// Output: 91
// Explanation: The answer should be the total numbers in the range of 0 ≤ x < 100,
//              excluding 11,22,33,44,55,66,77,88,99


class Solution {
    public int countNumbersWithUniqueDigits(int n) {
        if (n == 0) {
            return 1;
        }
        // only 10 digits, 11 place then there must be duplicates
        // 其实就是全排列的问题， n 是有几位位置可以放置， 譬如 n = 3 =>  _ _ _ ， 那么就是 9 * 9 * 8, 第一个位置不能放 0， 那这个只是当前位数的全排列个数， 而问的是从 0 开始一共有多少， 要把前面的都加起来
        int result = 10;
        int curr = 9;
        int available = 9;
        for (int i = 1; i < n && i <= 10; i++) {
            curr = curr * available;
            result += curr;
            available--;
        }
        return result;
    }
}

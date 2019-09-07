// 668. Kth Smallest Number in Multiplication Table
// DescriptionHintsSubmissionsDiscussSolution
// Nearly every one have used the Multiplication Table. But could you find out the k-th smallest number quickly from the multiplication table?
//
// Given the height m and the length n of a m * n Multiplication Table, and a positive integer k, you need to return the k-th smallest number in this table.
//
// Example 1:
// Input: m = 3, n = 3, k = 5
// Output:
// Explanation:
// The Multiplication Table:
// 1	2	3
// 2	4	6
// 3	6	9
//
// The 5-th smallest number is 3 (1, 2, 2, 3, 3).
// Example 2:
// Input: m = 2, n = 3, k = 6
// Output:
// Explanation:
// The Multiplication Table:
// 1	2	3
// 2	4	6
//
// The 6-th smallest number is 6 (1, 2, 2, 3, 4, 6).
// Note:
// The m and n will be in the range [1, 30000].
// The k will be in the range [1, m * n]
// Seen this question in a real interview before?
// Difficulty:Hard


class Solution {
    public int findKthNumber(int m, int n, int k) {
        // BS: 二分所有的乘法表中的数
        int start = 1;
        int end = m * n;
        while (start < end) {
            int mid = start + (end - start) / 2;
            int num = count(mid, m, n); // count how many values are less or equal than mid
            if (num < k) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        return start;
    }
    private int count2(int val, int m, int n) {
        int count = 0;
        for (int i = 1; i <= m; i++) {
            // n 是 一行有多少列， val / i 算得是这一行有多少列是小于等于 val 的， 因为从 1 开始， 所以得数就是列数
            count += Math.min(val / i, n);
        }
        return count;
    }
    private int count(int value, int m, int n) {
        int i = m, j = 1;
        int count = 0;
        while (i >= 1 && j <= n) {
            if (i * j <= value) {
                count += i;
                j++;
            } else {
                i--;
            }
        }
        return count;
    }
}

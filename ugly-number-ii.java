// 264. Ugly Number II
// DescriptionHintsSubmissionsDiscussSolution
// Write a program to find the n-th ugly number.
//
// Ugly numbers are positive numbers whose prime factors only include 2, 3, 5.
//
// Example:
//
// Input: n = 10
// Output: 12
// Explanation: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10 ugly numbers.
// Note:
//
// 1 is typically treated as an ugly number.
// n does not exceed 1690.


class Solution {
    public int nthUglyNumber(int n) {

        return method1(n);
    }

    private int method1(int n) {
        // O(N) time and space: 分别拿前面得到的 ugly number 乘上2 3 5 去得到下个， 取其中的最小值， 注意 2 3 5 乘的数并不尽相同， 因为每次乘的是不同的， 例如乘了 2， 2 要乘的数就要往后移一位， 如果两个数要乘的数是一样的， 要同时移动
        int[] nums = new int[n];
        nums[0] = 1;
        int i2 = 0, i3 = 0, i5 = 0; // index of previous nearest ugly number (which number should be multiply with 2 3 or 5 to get next)
        for (int i = 1; i < n; i++) {
            int next2 = nums[i2] * 2; // next number of multiply 2
            int next3 = nums[i3] * 3;
            int next5 = nums[i5] * 5;
            // pick the smallest value as next one
            nums[i] = Math.min(next2, Math.min(next3, next5));
            // even though they're equal we should move 2 pointer at the same time
            if (nums[i] == next2) {
                i2++;
            }
            if (nums[i] == next3) {
                i3++;
            }
            if (nums[i] == next5) {
                i5++;
            }
        }
        return nums[n - 1];
    }
}

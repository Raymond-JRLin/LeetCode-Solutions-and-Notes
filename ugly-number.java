// 263. Ugly Number
// DescriptionHintsSubmissionsDiscussSolution
// Write a program to check whether a given number is an ugly number.
//
// Ugly numbers are positive numbers whose prime factors only include 2, 3, 5.
//
// Example 1:
//
// Input: 6
// Output: true
// Explanation: 6 = 2 × 3
// Example 2:
//
// Input: 8
// Output: true
// Explanation: 8 = 2 × 2 × 2
// Example 3:
//
// Input: 14
// Output: false
// Explanation: 14 is not ugly since it includes another prime factor 7.
// Note:
//
// 1 is typically treated as an ugly number.
// Input is within the 32-bit signed integer range: [−231,  231 − 1].


class Solution {
    public boolean isUgly(int num) {
        if (num < 1) {
            return false;
        }
        if (num == 1) {
            return true;
        }

        // 任何一个数都可以描述成若干质数幂乘积

        // return mytry(num);

        return mytry2(num);
    }

    private boolean mytry2(int num) {
        // iteration
        while (num % 2 == 0) {
            num = num / 2;
        }
        while (num % 3 == 0) {
            num = num / 3;
        }
        while (num % 5 == 0) {
            num = num / 5;
        }
        return num == 1;
    }

    private boolean mytry(int num) {
        // brute force recursion: over all 2 3 5 to see if there's other prime factors, or just factor
        // O(log(2)N + log(3)N + log(5)N) time
        num = overPrime(num, 2);
        num = overPrime(num, 3);
        num = overPrime(num, 5);
        return num == 1;
    }
    private int overPrime(int num, int p) {
        if (num % p != 0) {
            return num;
        }
        return overPrime(num / p, p);
    }
}

// 231. Power of Two
// DescriptionHintsSubmissionsDiscussSolution
// Given an integer, write a function to determine if it is a power of two.
//
// Example 1:
//
// Input: 1
// Output: true
// Explanation: 20 = 1
// Example 2:
//
// Input: 16
// Output: true
// Explanation: 24 = 16
// Example 3:
//
// Input: 218
// Output: false
// Seen this question in a real interview before?
// Difficulty:Easy


class Solution {
    public boolean isPowerOfTwo(int n) {

        // return mytry(n);

        return mytry2(n);

        // return method2(n);

        // return method3(n);
    }

    private boolean method3(int n) {
        return n > 0 && Integer.bitCount(n) == 1;
    }

    private boolean method2(int n) {
        // 这里有一个 trick 或者是观察：2 的幂从 1 (0001) 开始， 每次乘以 2 相当于 << 1, 即将末尾的 1 向左移一位， 得到 2 (0010) 4 (0100) 8(1000), 因此如果一个数是 2 的幂次， 那么它在二进制中只有一个 1
        // n = 2 ^ 0 = 1 = 0b0000...00000001, and (n - 1) = 0 = 0b0000...0000.
        // n = 2 ^ 1 = 2 = 0b0000...00000010, and (n - 1) = 1 = 0b0000...0001.
        // n = 2 ^ 2 = 4 = 0b0000...00000100, and (n - 1) = 3 = 0b0000...0011.
        // n = 2 ^ 3 = 8 = 0b0000...00001000, and (n - 1) = 7 = 0b0000...0111.
        // we have n & (n-1) == 0b0000...0000 == 0
        if (n <= 0) {
            return false;
        }
        return (n & (n - 1)) == 0;
    }

    private boolean mytry2(int n) {
        // since mytry TLE, then try do n itself, everytime we over 2, check. O(logN) time

        // while (n > 1) {
        //     if (n % 2 == 1) {
        //         return false;
        //     }
        //     n/= 2;
        // }
        // return n == 1 ? true : false; // 1 or 0

        // another way:
        if (n <= 0) {
            return false;
        }
        while (n % 2 == 0) {
            n /= 2;
        }
        return n == 1;
    }

    private boolean mytry(int n) {
        // TLE
        int num = 1;
        while (num <= n) {
            if (num == n) {
                return true;
            }
            num *= 2;
        }
        return false;
    }
}

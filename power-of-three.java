// 326. Power of Three
// DescriptionHintsSubmissionsDiscussSolution
// Given an integer, write a function to determine if it is a power of three.
//
// Example 1:
//
// Input: 27
// Output: true
// Example 2:
//
// Input: 0
// Output: false
// Example 3:
//
// Input: 9
// Output: true
// Example 4:
//
// Input: 45
// Output: false
// Follow up:
// Could you do it without using any loop / recursion?
//
// Seen this question in a real interview before?
// Difficulty:Easy


class Solution {
    public boolean isPowerOfThree(int n) {
        if (n <= 0) {
            return false;
        }
        if (n == 1) {
            return true;
        }

        // 注意审题， 是 3 的幂， 而不是能够被 3 整除就行
        // return method1_iteration(n);

        // recursion way: be attention
        // return method2_recursion(n);

        // without loop/recursion: then we should do some trick on math
        // return method3_log(n);

        // 因为给的是 int， 所以在 int 范围内最大的 3 的幂是 3 ^ 19 = 1162261467
        // 但是要注意这种方法只适用于 base 是质数的求解
        return method4_cheat(n);
    }

    private boolean method4_cheat(int n) {
        // 1162261467 is 3^19,  3^20 is bigger than int
        return 1162261467 % n == 0;
    }

    private boolean method3_log(int n) {
        // log10(n) / log10(3) if n = 3 ^ x => xlog10(3) / log10(3) = x, 即得到了幂次， 判断是否为整数 int
        return Math.log10(n) / Math.log10(3) % 1 == 0;
    }

    private boolean method2_recursion(int n) {
         return n == 1 || (n % 3 == 0 && method2_recursion(n / 3));
    }

    private boolean method1_iteration(int n) {
        while (n % 3 == 0) {
            // 如果能被 3 整除， 那么不断的去除 - 也就是不断的把 3 拿掉， 看看最后是否能够得到 1
            n /= 3;
        }
        // 最后判断是否余 1， 此时不能再 % 3 来判断， 因为有的给的就是 % 3 = 1， 都进不了循环， 结果最后判断恰好是， 就会出错， e.g. 19684
        return n == 1;
    }
}

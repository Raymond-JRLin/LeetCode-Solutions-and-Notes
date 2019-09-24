// 1201. Ugly Number III
// User Accepted: 523
// User Tried: 2001
// Total Accepted: 535
// Total Submissions: 5132
// Difficulty: Medium
// Write a program to find the n-th ugly number.
//
// Ugly numbers are positive integers which are divisible by a or b or c.
//
//
//
// Example 1:
//
// Input: n = 3, a = 2, b = 3, c = 5
// Output: 4
// Explanation: The ugly numbers are 2, 3, 4, 5, 6, 8, 9, 10... The 3rd is 4.
// Example 2:
//
// Input: n = 4, a = 2, b = 3, c = 4
// Output: 6
// Explanation: The ugly numbers are 2, 3, 4, 6, 8, 9, 10, 12... The 4th is 6.
// Example 3:
//
// Input: n = 5, a = 2, b = 11, c = 13
// Output: 10
// Explanation: The ugly numbers are 2, 4, 6, 8, 10, 11, 12, 13... The 5th is 10.
// Example 4:
//
// Input: n = 1000000000, a = 2, b = 217983653, c = 336916467
// Output: 1999999984
//
//
// Constraints:
//
// 1 <= n, a, b, c <= 10^9
// 1 <= a * b * c <= 10^18
// It's guaranteed that the result will be in range [1, 2 * 10^9]


class Solution {
    public int nthUglyNumber(int n, int a, int b, int c) {

        // return mytry(n, a, b, c);

        return method1(n, a, b, c);
    }

    private int method1(int n, int a, int b, int c) {
        // ref: https://leetcode.com/problems/ugly-number-iii/discuss/387622/chinesec-1201-rong-chi-yu-er-fen-qiu-di-nge-chou-shu
        // Calculate how many numbers from 1 to num are divisible by either a, b or c by using the formula:
        // (num / a) + (num / b) + (num / c) – (num / lcm(a, b)) – (num / lcm(b, c)) – (num / lcm(a, c)) + (num / lcm(a, b, c))
        // 从 example 4 和求的是"第n个数"， 要想到用 binary search
        int start = 1;
        int end = 2 * (int) Math.pow(10, 9);
        while (start < end) {
            int mid = start + (end - start) / 2;
            int count = getCount(mid, a, b, c);
            if (count < n) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        return start;
    }
    private int getCount(int mid, int a, int b, int c) {
        // 注意乘法越界
        return (int) (mid / a + mid / b + mid / c - (mid/ lcm(a, b)) - (mid / lcm(a, c)) - (mid / lcm(b, c)) + (mid / lcm(a, lcm(b, c))));
    }
    private long lcm(long a, long b) {
        return a * b / gcd(a, b);
    }
    private long gcd(long a, long b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    private int mytry(int n, int a, int b, int c) {
        // wrong!
        // 审题啊喂
        // 这个题问的是能被 a 或 b 或 c 整除的正整数
        // ugly number ii 是质因数只能是 2, 3, 5 的
        // 完全不一样的
        // n 以内有多少个数能被 x 整除 => n / x
        long[] nums = new long[n + 1];
        int num1 = Math.min(a, Math.min(b, c));
        int num2 = (num1 == a ? Math.min(b, c) : (num1 == b ? Math.min(a, c) : Math.min(a, b)));
        int num3 = a + b + c - num1 - num2;
        nums[0] = 1;
        int p1 = 0, p2 = 0, p3 = 0;
        for (int i = 1; i < n + 1; i++) {
            System.out.println("check i: " + i);
            long next1 = num1 * nums[p1];
            System.out.println("p1: " + p1 + ", next1: " + next1);
            if (next1 < num2) {
                nums[i] = next1;
                p1++;
                continue;
            }
            long next2 = num2 * nums[p2];
            System.out.println("next2: " + next2);
            if (next2 < num3) {
                if (next1 < next2) {
                    nums[i] = next1;
                    p1++;
                } else if (next1 > next2) {
                    nums[i] = next2;
                    p2++;
                } else {
                    nums[i] = next1;
                    p1++;
                    p2++;
                }
                continue;
            }
            long next3 = num3 * nums[p3];
            System.out.println("next3: " + next3);
            nums[i] = Math.min(next1, Math.min(next2, next3));

            if (nums[i] == next1) {
                p1++;
            }
            if (nums[i] == next2) {
                p2++;
            }
            if (nums[i] == next3) {
                p3++;
            }
        }
        return (int) nums[n];
    }
}

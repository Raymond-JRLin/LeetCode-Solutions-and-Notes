// 258. Add Digits
// DescriptionHintsSubmissionsDiscussSolution
// Given a non-negative integer num, repeatedly add all its digits until the result has only one digit.
//
// Example:
//
// Input: 38
// Output: 2
// Explanation: The process is like: 3 + 8 = 11, 1 + 1 = 2.
//              Since 2 has only one digit, return it.
// Follow up:
// Could you do it without any loop/recursion in O(1) runtime?


class Solution {
    public int addDigits(int num) {
        if (num < 10) {
            return num;
        }

        // return mytry(num);

        // return method2(num);

        // return method3(num);

        return method4(num);
    }

    private int method4(int num) {
        while (num >= 10) {
            num = num / 10 + num % 10;
        }
        return num;
    }

    private int method3(int num) {
        // recursion
        int sum = 0;
        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }
        if (sum < 10) {
            return sum;
        } else {
            return method3(sum);
        }
    }

    private int method2(int num) {
        // digit root: O(1) time and space
        // ref: https://leetcode.com/problems/add-digits/discuss/68580/Accepted-C++-O(1)-time-O(1)-space-1-Line-Solution-with-Detail-Explanations
        return 1 + (num - 1) % 9;
    }

    private int mytry(int num) {
        while (num >= 10) {
            int sum = 0;
            while (num > 0) {
                sum += num % 10;
                num /= 10;
            }
            num = sum;
        }
        return num;
    }
}

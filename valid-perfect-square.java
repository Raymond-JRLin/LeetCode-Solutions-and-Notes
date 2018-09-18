// 367. Valid Perfect Square
// DescriptionHintsSubmissionsDiscussSolution
// Given a positive integer num, write a function which returns True if num is a perfect square else False.
//
// Note: Do not use any built-in library function such as sqrt.
//
// Example 1:
//
// Input: 16
// Output: true
// Example 2:
//
// Input: 14
// Output: false


class Solution {
    public boolean isPerfectSquare(int num) {
        if (num < 0) {
            return false;
        }

        // return mytry(num);

        // return method2(num);

        // return method3(num);

        return method4(num);
    }

    private boolean method4(int num) {
        // Newton
        long x = num;
        while (x * x > num) {
            x = (x + num / x) / 2;
        }
        return x * x == num;
    }

    private boolean method3(int num) {
        // 1 = 1
        // 4 = 1 + 3
        // 9 = 1 + 3 + 5
        // 16 = 1 + 3 + 5 + 7
        // O(N) time and O(1) space
        int i = 1;
        while (num > 0) {
            num -= i;
            i += 2;
        }
        return num == 0;
    }

    private boolean method2(int num) {
        // answer must be between 0 ~ num, so we can use BS O(logN)
        int start = 0;
        int end = num;
        while (start + 1 < end) {
            // use long type because mid * mid may overflow, even though mid itself would not
            long mid = start + (end - start) / 2;
            if (mid * mid == num) {
                return true;
            } else if (mid * mid < num) {
                start = (int) mid;
            } else {
                end = (int) mid;
            }
        }
        if (start * start == num || end * end == num) {
            return true;
        } else {
            return false;
        }
    }

    private boolean mytry(int num) {
        // O(sqrt(N))
        for (int i = 0; i <= Math.sqrt(num) + 1; i++) {
            if (i * i == num) {
                return true;
            }
        }
        return false;
    }
}

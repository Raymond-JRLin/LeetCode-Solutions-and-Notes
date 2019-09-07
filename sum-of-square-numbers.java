// 633. Sum of Square Numbers
// DescriptionHintsSubmissionsDiscussSolution
// Given a non-negative integer c, your task is to decide whether there're two integers a and b such that a2 + b2 = c.
//
// Example 1:
//
// Input: 5
// Output: True
// Explanation: 1 * 1 + 2 * 2 = 5
//
//
// Example 2:
//
// Input: 3
// Output: False
//
//
// Seen this question in a real interview before?
// Difficulty:Easy


class Solution {
    public boolean judgeSquareSum(int c) {

        // return mytry(c);

        return method2(c);
    }

    private boolean method2(int num) {
        // since we are looking for 2 int, so we can use 2 pointers: O(sqrt(N))
        int left = 0;
        int right = (int) Math.sqrt(num);
        while (left <= right) {
            int sum = left * left + right * right;
            if (sum == num) {
                return true;
            } else if (sum > num) {
                right--;
            } else {
                left++;
            }
        }
        return false;
    }

    private boolean mytry(int num) {
        // O(sqrt(N) * logN)
        for (int i = 0; i <= Math.sqrt(num); i++) {
            if (perfectSquare(num - i * i)) {
                return true;
            }
        }
        return false;
    }
    private boolean perfectSquare(int num) {
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
}

// 7. Reverse Integer
// DescriptionHintsSubmissionsDiscussSolution
// Given a 32-bit signed integer, reverse digits of an integer.
//
// Example 1:
//
// Input: 123
// Output: 321
// Example 2:
//
// Input: -123
// Output: -321
// Example 3:
//
// Input: 120
// Output: 21
// Note:
// Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−231,  231 − 1]. For the purpose of this problem, assume that your function returns 0 when the reversed integer overflows.


class Solution {
    public int reverse(int x) {
        if (x > Integer.MAX_VALUE) {
            return 0;
        }

        // it's wrong
        // return my_try(x);


        // 注意两个地方： 正负与越界
        // 正负好处理， 对于越界我们有两种处理方式： 使用 long， 如果越界了返回 0； 还可以使用某一步的结果(已越界) / 10 来判断是否和操作之前相等
        // the latter is better
        // return method1(x);
        return method2(x);
    }

    private int method2(int x) {
        int sign = (x < 0 ? -1 : 1); // use int so we can multiply directly rathen than using if to assgin a negative
        x = Math.abs(x); // get absolute value
        // 不借助其他存储结构， 同时一边取一边加入， 一边判断
        int result = 0;
        while (x != 0) {
            int temp = result * 10 + x % 10;
            // 还原比较下一个数在加之前是否还是一样的
            if (temp / 10 != result) {
                return 0;
            }
            result = temp;
            x /= 10;
        }
        return result * sign;
    }

    private int method1(int x) {
        int sign = (x < 0 ? -1 : 1);
        x = Math.abs(x);
        // use long to deal with out of int range
        long result = 0L;
        while (x != 0) {
            result = result * 10 + x % 10;
            // 比较下一个数在加之前是否还是一样的
            if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE) {
                return 0;
            }
            x /= 10;
        }
        return (int) result * sign;
    }


    // incorrect: digit may exceed MAX, use extra space
    // private int my_try(int x) {
    //     boolean isNegative = false;
    //     if (x < 0) {
    //         isNegative = true;
    //         x = -x;
    //     }
    //     if (x < 10) {
    //         if (isNegative) {
    //             x = -x;
    //         }
    //         return x;
    //     }
    //     Stack<Integer> stack = new Stack<>();
    //     while (x != 0) {
    //         int remain = x % 10;
    //         stack.push(remain);
    //         x /= 10;
    //     }
    //     while (stack.peek() == 0) {
    //         stack.pop();
    //     }
    //     int size = stack.size();
    //     long result = 0;
    //     int digit = 1;
    //     while (!stack.isEmpty()) {
    //         int num = stack.pop();
    //         result += num * digit;
    //         digit *= 10;
    //     }
    //     if (result > Integer.MAX_VALUE) {
    //         return 0;
    //     }
    //     if (isNegative) {
    //         return (int) -result;
    //     } else {
    //         return (int) result;
    //     }
    // }
}

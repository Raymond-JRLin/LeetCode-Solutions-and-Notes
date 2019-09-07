// 338. Counting Bits
// DescriptionHintsSubmissionsDiscussSolution
// Given a non negative integer number num. For every numbers i in the range 0 ≤ i ≤ num calculate the number of 1's in their binary representation and return them as an array.
//
// Example 1:
//
// Input: 2
// Output: [0,1,1]
// Example 2:
//
// Input: 5
// Output: [0,1,1,2,1,2]
// Follow up:
//
// It is very easy to come up with a solution with run time O(n*sizeof(integer)). But can you do it in linear time O(n) /possibly in a single pass?
// Space complexity should be O(n).
// Can you do it like a boss? Do it without using any builtin function like __builtin_popcount in c++ or in any other language.


class Solution {
    public int[] countBits(int num) {
        if (num == 0) {
            return new int[]{0};
        }
        if (num == 1) {
            return new int[]{0, 1};
        }

//         method 0: API
        // return method0(num);

//         method 1: use x & (x - 1) to count how many 1 of a int, then count every num => TLE
        // return method1(num);

//         题目这么写着， 肯定不能一个个算， 要去找其中的规律
        // i    bin   '1'  i&(i-1)
        // 0   0000    0
        // -----------------------
        // 1   0001    1    0000
        // -----------------------
        // 2   0010    1    0000
        // 3   0011    2    0010
        // -----------------------
        // 4   0100    1    0000
        // 5   0101    2    0100
        // 6   0110    2    0100
        // 7   0111    3    0110
        // -----------------------
        // 8   1000    1    0000
        // 9   1001    2    1000
        // 10  1010    2    1000
        // 11  1011    3    1010
        // 12  1100    2    1000
        // 13  1101    3    1100
        // 14  1110    3    1100
        // 15  1111    4    1110
//         从上面可以发现一些规律：
//         1: 每个区间前半部分和上一个区间相同， 后半部分等于前半部分 + 1
//         2: 从1开始，遇到偶数时，其1的个数和该偶数除以2得到的数字的1的个数相同，遇到奇数时，其1的个数等于该奇数除以2得到的数字的1的个数再加1
//         3: 每个i的1的个数都是i&(i-1)对应的个数加1
//         以上都可以写出来， ref： http://www.cnblogs.com/grandyang/p/5294255.html
//         这里写出2 与 3
        // return method2(num);
        return method3(num);
    }
    private int[] method2(int num) {
        int[] result = new int[num + 1];
        for (int i = 1; i < num + 1; i++) {
            if (i % 2 == 0) {
                result[i] = result[i / 2];
            } else {
                result[i] = result[i / 2] + 1;
            }
        }
        return result;
    }
    private int[] method3(int num) {
        int[] result = new int[num + 1];
        for (int i = 1; i < num + 1; i++) {
            result[i] = result[i & (i - 1)] + 1;
        }
        return result;
    }
    private int[] method0(int num) {
        int[] result = new int[num + 1];
        for (int i = 0; i < num + 1; i++) {
            result[i] = Integer.bitCount(i);
        }
        return result;
    }
    private int[] method1(int num) {
        int[] result = new int[num + 1];
        for (int i = 0; i < num + 1; i++) {
            int count = 0;
            while (i != 0) {
                i = i & (i - 1);
                count++;
            }
            result[i] = count;
        }
        return result;
    }
}

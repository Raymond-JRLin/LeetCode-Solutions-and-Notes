// 481. Magical String
// DescriptionHintsSubmissionsDiscussSolution
// A magical string S consists of only '1' and '2' and obeys the following rules:
//
// The string S is magical because concatenating the number of contiguous occurrences of characters '1' and '2' generates the string S itself.
//
// The first few elements of string S is the following: S = "1221121221221121122……"
//
// If we group the consecutive '1's and '2's in S, it will be:
//
// 1 22 11 2 1 22 1 22 11 2 11 22 ......
//
// and the occurrences of '1's or '2's in each group are:
//
// 1 2	2 1 1 2 1 2 2 1 2 2 ......
//
// You can see that the occurrence sequence above is the S itself.
//
// Given an integer N as input, return the number of '1's in the first N number in the magical string S.
//
// Note: N will not exceed 100,000.
//
// Example 1:
// Input: 6
// Output: 3
// Explanation: The first 6 elements of magical string S is "12211" and it contains three 1's, so return 3.
// Seen this question in a real interview before?
// Difficulty:Medium


class Solution {
    public int magicalString(int n) {
        // 应该要是发现规律， 但是没发现LOL， 看答案的
        // 总的来说就是先写下前三个数 1 2 2， 从第 3 个数开始， 是 2 就写两个， 是 1 就写一个， 但是是 1 与 2 交替出现
        // 这里有一个 trick， 如何使 1 2 交替出现呢： 自身与 3 取异或
        if (n <= 0) {
            return 0;
        }
        if (n <= 3) {
            return 1;
        }
        int[] res = new int[n + 1]; // use a array to generate magical string
        // initialization
        res[0] = 1;
        res[1] = 2;
        res[2] = 2;
        int count = 1; // already have 1
        // initialize pointer and number
        int head = 2;
        int tail = 3;
        int num = 1;
        while (tail < n) {
            for (int i = 0; i < res[head]; i++) {
                // 当前 head 指向的是几， 就要生成几个数
                res[tail] = num;
                if (tail < n && num == 1) {
                    // 依然首先要保证在 n 以内
                    count++;
                }
                tail++; // move to next position
            }
            num ^= 3; // XOR 3 => change 1/2 to 2/1
            head++; // move head to next number
        }
        return count;
    }
}

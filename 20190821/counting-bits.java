// 338. Counting Bits
// DescriptionHintsSubmissionsDiscussSolution
//
// Given a non negative integer number num. For every numbers i in the range 0 ≤ i ≤ num calculate the number of 1's in their binary representation and return them as an array.
//
// Example 1:
//
// Input: 2
// Output: [0,1,1]
//
// Example 2:
//
// Input: 5
// Output: [0,1,1,2,1,2]
//
// Follow up:
//
//     It is very easy to come up with a solution with run time O(n*sizeof(integer)). But can you do it in linear time O(n) /possibly in a single pass?
//     Space complexity should be O(n).
//     Can you do it like a boss? Do it without using any builtin function like __builtin_popcount in c++ or in any other language.
//


class Solution {
    public int[] countBits(int num) {

        // return method1(num);

        return method2(num);
    }

    // 重新做一遍， 有了更好的理解， 完全弄明白了这些解法背后的逻辑

    private int[] method2(int num) {
        // DP 的思想
        // i & (i - 1) 是抹掉最后一位 1 （最右边）， 所以拿到它的 bit 1 的个数， 补上 1 即可
        int[] result = new int[num + 1];
        for (int i = 1; i <= num; i++) {
            result[i] = result[i & (i - 1)] + 1;
        }
        return result;
    }

    private int[] method1(int num) {
        // i / 2 的操作实际上是将 bit 向右移一位， 所以看被移走的最后一位是不是 1
        // 偶数是 0， 奇数是 1， 所以补上 i % 2 即可
        int[] result = new int[num + 1];
        for (int i = 1; i <= num; i++) {
            result[i] = result[i / 2] + i % 2;
        }
        return result;
    }
}

// 201. Bitwise AND of Numbers Range
// DescriptionHintsSubmissionsDiscussSolution
// Given a range [m, n] where 0 <= m <= n <= 2147483647, return the bitwise AND of all numbers in this range, inclusive.
//
// Example 1:
//
// Input: [5,7]
// Output: 4
// Example 2:
//
// Input: [0,1]
// Output: 0
// Seen this question in a real interview before?
// Difficulty:Medium


class Solution {
    public int rangeBitwiseAnd(int m, int n) {
        // 直接 for 就 TLE 了， 那肯定是要找 bitwise 上有什么技巧
        // 仔细想一想这个题目， 一个 range 内的数字后面在 + 1 递增， 所以肯定是不一样的， 那只要有 0 就是 0 了
        // 那只有走到一个位置， 前面的二进制就都会一样了， 所以这题在找二进制下前面开始的长度一致的 prefix
        // 那么可以不断 remove n 与 m 的后面， 直到找到前面一样
        int result = 0;
        while (m != n) {
            result++;
            m >>= 1;
            n >>= 1;
        }
        return m << result;
    }
}

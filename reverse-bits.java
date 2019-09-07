// 190. Reverse Bits
// DescriptionHintsSubmissionsDiscussSolution
// Reverse bits of a given 32 bits unsigned integer.
//
//
//
// Example 1:
//
// Input: 00000010100101000001111010011100
// Output: 00111001011110000010100101000000
// Explanation: The input binary string 00000010100101000001111010011100 represents the unsigned integer 43261596, so return 964176192 which its binary representation is 00111001011110000010100101000000.
// Example 2:
//
// Input: 11111111111111111111111111111101
// Output: 10111111111111111111111111111111
// Explanation: The input binary string 11111111111111111111111111111101 represents the unsigned integer 4294967293, so return 3221225471 which its binary representation is 10101111110010110010011101101001.
//
//
// Note:
//
// Note that in some languages such as Java, there is no unsigned integer type. In this case, both input and output will be given as signed integer type and should not affect your implementation, as the internal binary representation of the integer is the same whether it is signed or unsigned.
// In Java, the compiler represents the signed integers using 2's complement notation. Therefore, in Example 2 above the input represents the signed integer -3 and the output represents the signed integer -1073741825.
//
//
// Follow up:
//
// If this function is called many times, how would you optimize it?
//
// Seen this question in a real interview before?
// Difficulty:Easy


public class Solution {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        if (n == 0) {
            return 0;
        }

        // return mytry(n);

        // return method2(n);

        // return method3(n);

        return method4(n);
    }

    private Map<Byte, Integer> map = new HashMap<>(); // cache
    private int method4(int n) {
        // if this function is called many times, then we should think about store a cache
        //ref: https://leetcode.com/problems/reverse-bits/discuss/54746/Java-Solution-and-Optimization
        // chop n (32 bits) into 4 byte(8 bits) to store seperately
        byte[] bytes = new byte[4];
        for (int i = 0; i < 4; i++) {
            bytes[i] = (byte) ((n >> (i * 8)) & 0xFF); // 0xFF: 00...00 1111
        }
        int result = 0;
        for (int i = 0; i < 4; i++) {
            result += reverseByte(bytes[i]);
            if (i < 3) {
                result <<= 8;
            }
        }
        return result;
    }
    private int reverseByte(byte b) {
        // reverse 1 byte of 4
        Integer value = map.get(b);
        if (value != null) {
            // hit cache
            return value;
        }
        value = 0;
        for (int i = 0; i < 8; i++) {
            value += (b >> i) & 1;
            if (i < 7) {
                value <<= 1;
            }
        }
        map.put(b, value); // record cache
        return value;
    }

    private int method3(int n) {
        // different idea
        int result = 0;
        for (int i = 0; i < 32; i++) {
            result <<= 1;
            result += (n & 1);
            n >>= 1;
        }
        return result;
    }

    private int method2(int n) {
        // we can modify result when we got the bit
        int result = 0;
        for (int i = 0; i < 32; i++) {
            int bit = (n & (1 << i)) == 0 ? 0 : 1;
            result |= bit << (31 - i);
        }
        return result;
    }

    private int mytry(int n) {
        int[] nums = new int[32];
        for (int i = 0; i < 32; i++) {
            nums[i] = (n & (1 << i)) == 0 ? 0 : 1;
        }
        int result = 0;
        for (int i = 0; i < 32; i++) {
            result = result | (nums[i] << (31 - i));
        }
        return result;
    }
}

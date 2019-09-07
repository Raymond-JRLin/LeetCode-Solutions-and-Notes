// 461. Hamming Distance
// DescriptionHintsSubmissionsDiscussSolution
// The Hamming distance between two integers is the number of positions at which the corresponding bits are different.
//
// Given two integers x and y, calculate the Hamming distance.
//
// Note:
// 0 ≤ x, y < 231.
//
// Example:
//
// Input: x = 1, y = 4
//
// Output: 2
//
// Explanation:
// 1   (0 0 0 1)
// 4   (0 1 0 0)
//        ↑   ↑
//
// The above arrows point to positions where the corresponding bits are different.
// Seen this question in a real interview before?
// Difficulty:Easy


class Solution {
    public int hammingDistance(int x, int y) {
        if (x == y) {
            return 0;
        }

        // return mytry(x, y);

        return method2(x, y);
    }


    private int method3(int x, int y) {
        // same idea, but easier
        int diff = x ^ y; // get the different bits together first
        int result = 0;
        for (int i = 0; i < 32; i++) {
            result += ((diff >> i) & 1);
        }
        return result;
    }

    private int method2(int x, int y) {
        // Java api
        return Integer.bitCount(x ^ y);
    }

    private int mytry(int x, int y) {
        int result = 0;
        for (int i = 0; i < 32; i++) {
            int a = (x >> i) & 1;
            int b = (y >> i) & 1;
            if (a != b) {
                result++;
            }
        }
        return result;
    }
}

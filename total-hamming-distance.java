// 477. Total Hamming Distance
// DescriptionHintsSubmissionsDiscussSolution
// The Hamming distance between two integers is the number of positions at which the corresponding bits are different.
//
// Now your job is to find the total Hamming distance between all pairs of the given numbers.
//
// Example:
// Input: 4, 14, 2
//
// Output: 6
//
// Explanation: In binary representation, the 4 is 0100, 14 is 1110, and 2 is 0010 (just
// showing the four bits relevant in this case). So the answer will be:
// HammingDistance(4, 14) + HammingDistance(4, 2) + HammingDistance(14, 2) = 2 + 2 + 2 = 6.
// Note:
// Elements of the given array are in the range of 0 to 10^9
// Length of the array will not exceed 10^4.
// Seen this question in a real interview before?
// Difficulty:Medium


class Solution {
    public int totalHammingDistance(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

//         my try TLE, because finding every pair spends too much
        // return my_try(nums);

        return method1_bitwise(nums);
    }

    private int method1_bitwise(int[] nums) {
//         计算 32 位上， 每位有几个 1， e.g. k, 那么不同的 pair 就有 k * (n - k)， 统计每一位就好
        int n = nums.length;
        int sum = 0;
        for (int i = 0; i < 32; i++) {
            int bits = 0;
            for (int num : nums) {
                // bits += (num & (1 << i)) == 0 ? 0 : 1; // or we can do:
                bits += (num >> i) & 1;
            }
            sum += bits * (n - bits);
        }
        return sum;
    }

    private int my_try(int[] nums) {
//         XOR everay pair, and find out how many 1 in the result
        int n = nums.length;
        int result = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int num = nums[i] ^ nums[j];
                result += getOnes(num);
            }
        }
        return result;
    }
    private int getOnes(int num) {
        int count = 0;
        while (num != 0) {
            num = num & (num - 1);
            count++;
        }
        return count;
    }
}

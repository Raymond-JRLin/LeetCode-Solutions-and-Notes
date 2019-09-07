// 421. Maximum XOR of Two Numbers in an Array
// DescriptionHintsSubmissionsDiscussSolution
// Given a non-empty array of numbers, a0, a1, a2, … , an-1, where 0 ≤ ai < 231.
//
// Find the maximum result of ai XOR aj, where 0 ≤ i, j < n.
//
// Could you do this in O(n) runtime?
//
// Example:
//
// Input: [3, 10, 5, 25, 2, 8]
//
// Output: 28
//
// Explanation: The maximum result is 5 ^ 25 = 28.
//
//
// Seen this question in a real interview before?
// Difficulty:Medium


class Solution {
    public int findMaximumXOR(int[] nums) {
        // 我只能想到说， 因为要找最大的异或结果， 所以我们可以查找每一位， 找出最大的不同， 具体怎么做不知道 =。=
        // 下面是参照答案的做法 - bit manipulation
        // 异或有个性质： if a ^ b = c, then a = b ^ c
        int mask = 0;
        int result = 0;
        for (int i = 31; i >= 0; i--) {
            mask = mask | (1 << i); // 1000,...; 1100,...; 1110,...;...;
            Set<Integer> set = new HashSet<>();
            for (int num : nums) {
                set.add(num & mask);  // 取前缀
            }
            int temp = result | (1 << i); // 假设当前位取到了 1，
            for (int prefix : set) {
                if (set.contains(prefix ^ temp)) {
                    //
                    result = temp;
                    break;
                }
            }
        }
        return result;
    }
}

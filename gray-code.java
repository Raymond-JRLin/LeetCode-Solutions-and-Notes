// 89. Gray Code
// DescriptionHintsSubmissionsDiscussSolution
// The gray code is a binary numeral system where two successive values differ in only one bit.
//
// Given a non-negative integer n representing the total number of bits in the code, print the sequence of gray code. A gray code sequence must begin with 0.
//
// Example 1:
//
// Input: 2
// Output: [0,1,3,2]
// Explanation:
// 00 - 0
// 01 - 1
// 11 - 3
// 10 - 2
//
// For a given n, a gray code sequence may not be uniquely defined.
// For example, [0,2,3,1] is also a valid gray code sequence.
//
// 00 - 0
// 10 - 2
// 11 - 3
// 01 - 1
// Example 2:
//
// Input: 0
// Output: [0]
// Explanation: We define the gray code sequence to begin with 0.
//              A gray code sequence of n has size = 2n, which for n = 0 the size is 20 = 1.
//              Therefore, for n = 0 the gray code sequence is [0].
// Seen this question in a real interview before?
// Difficulty:Medium


class Solution {
    public List<Integer> grayCode(int n) {
        if (n < 0) {
            return Collections.emptyList();
        }

        // return method1(n);

        return method2(n);
    }

    private List<Integer> method2(int n) {
        // recurtion: keep tracking back to last time and add 1 to the highest bit
        // ref: https://leetcode.com/problems/gray-code/discuss/30106/Recursive-solution-c++-6-ms-with-explaination
        List<Integer> result = new ArrayList<>();
        recursion(result, n);
        return result;
    }
    private void recursion(List<Integer> result, int n) {
        if (n == 0) {
            result.add(0);
            return;
        }
        recursion(result, n - 1); // to get the n - 1 result
        for (int i = result.size() - 1; i >= 0; i--) {
            result.add(result.get(i) | 1 << (n - 1)); // attention: move 1 left (n - 1) bit
        }
    }

    private List<Integer> method1(int n) {
        // observe the example, they are like mirror symmetry, 00 -> 10; 00-- [10->110] --100; and add 1 to the highest bit . Every time, we add 1 at the highest bit of last-time result from the last number to the first one
        // https://leetcode.com/problems/gray-code/discuss/29891/Share-my-solution
        List<Integer> result = new ArrayList<>();
        result.add(0);
        for (int i = 0; i < n; i++) {
            int size = result.size();
            for (int j = size - 1; j >= 0; j--) {
                result.add(result.get(j) | 1 << i);
            }
        }
        return result;
    }
}

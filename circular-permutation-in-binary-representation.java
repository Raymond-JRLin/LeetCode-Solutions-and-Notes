// 1238. Circular Permutation in Binary Representation
//
//     User Accepted: 1085
//     User Tried: 1547
//     Total Accepted: 1099
//     Total Submissions: 2517
//     Difficulty: Medium
//
// Given 2 integers n and start. Your task is return any permutation p of (0,1,2.....,2^n -1) such that :
//
//     p[0] = start
//     p[i] and p[i+1] differ by only one bit in their binary representation.
//     p[0] and p[2^n -1] must also differ by only one bit in their binary representation.
//
//
//
// Example 1:
//
// Input: n = 2, start = 3
// Output: [3,2,0,1]
// Explanation: The binary representation of the permutation is (11,10,00,01).
// All the adjacent element differ by one bit. Another valid permutation is [3,1,0,2]
//
// Example 2:
//
// Input: n = 3, start = 2
// Output: [2,6,7,5,4,0,1,3]
// Explanation: The binary representation of the permutation is (010,110,111,101,100,000,001,011).
//
//
//
// Constraints:
//
//     1 <= n <= 16
//     0 <= start < 2 ^ n
//


class Solution {
    public List<Integer> circularPermutation(int n, int start) {
        if (n < 1) {
            return Collections.emptyList();
        }

        // return mytry(n, start);

        return method2(n, start);
    }

    private List<Integer> method2(int n, int start) {
        // 每个差 1 个 1 => 其实就是 grey code
        // G(n) = n ^ (n >> 1)
        // 那么现在要以 start 开头， 那么每个都 ^ start 就好
        // ref: https://leetcode.com/problems/circular-permutation-in-binary-representation/discuss/414203/JavaC++Python-4-line-Gray-Code
        // ref: https://cp-algorithms.com/algebra/gray-code.html
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < (1 << n); i++) {
            result.add(start ^ i ^ (i >> 1));
        }
        return result;
    }

    private List<Integer> mytry(int n, int start) {
        List<Integer> result = new ArrayList<>();
        int count = (1 << n);
        dfs(n, start, start, result, new HashSet<Integer>(), count);
        return result;
    }
    private boolean dfs(int n, int start, int curr, List<Integer> result, Set<Integer> set, int count) {
        if (count == 0) {
            // check if the last number only has single one 1 different with start
            int num = curr ^ start;
            if (((num & (num - 1)) == 0)) {
                return true;
            }
            return false;
        }
        if (set.contains(curr)) {
            return false;
        }
        set.add(curr);
        result.add(curr);
        // 改动 n 个位置
        for (int i = 0; i < n; i++) {
            int mask = (1 << i);
            int next = curr ^ mask;
            if (dfs(n, start, next, result, set, count - 1)) {
                return true;
            }
        }
        result.remove(result.size() - 1);
        set.remove(curr);
        return false;
    }
}

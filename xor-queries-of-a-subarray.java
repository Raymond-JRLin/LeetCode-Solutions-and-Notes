// 1310. XOR Queries of a Subarray
//
//     User Accepted: 2485
//     User Tried: 3021
//     Total Accepted: 2529
//     Total Submissions: 4473
//     Difficulty: Medium
//
// Given the array arr of positive integers and the array queries where queries[i] = [Li, Ri], for each query i compute the XOR of elements from Li to Ri (that is, arr[Li] xor arr[Li+1] xor ... xor arr[Ri] ). Return an array containing the result for the given queries.
//
//
//
// Example 1:
//
// Input: arr = [1,3,4,8], queries = [[0,1],[1,2],[0,3],[3,3]]
// Output: [2,7,14,8]
// Explanation:
// The binary representation of the elements in the array are:
// 1 = 0001
// 3 = 0011
// 4 = 0100
// 8 = 1000
// The XOR values for queries are:
// [0,1] = 1 xor 3 = 2
// [1,2] = 3 xor 4 = 7
// [0,3] = 1 xor 3 xor 4 xor 8 = 14
// [3,3] = 8
//
// Example 2:
//
// Input: arr = [4,8,2,10], queries = [[2,3],[1,3],[0,0],[0,3]]
// Output: [8,0,4,4]
//
//
//
// Constraints:
//
//     1 <= arr.length <= 3 * 10^4
//     1 <= arr[i] <= 10^9
//     1 <= queries.length <= 3 * 10^4
//     queries[i].length == 2
//     0 <= queries[i][0] <= queries[i][1] < arr.length
//

class Solution {
    public int[] xorQueries(int[] arr, int[][] queries) {
        int n = arr.length;
        int[] prefix = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] ^ arr[i];
        }
        int m = queries.length;
        int[] result = new int[m];
        for (int i = 0; i < m; i++) {
            int[] query = queries[i];
            // 要注意这里的 index， query[L, R] 是要包括 [L, R] 的边界的
            // prefix[i] 对应的是前 i 个数
            // 所以 R 对应的是前 R + 1 个数， 左边界是 L + 1 个数， 那么要包括边界， 所以 XOR prefix[L]， 即消掉前 L 个数
            result[i] = prefix[query[1] + 1] ^ prefix[query[0]];
        }
        return result;
    }
}

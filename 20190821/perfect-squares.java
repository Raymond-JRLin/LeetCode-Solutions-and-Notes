// 279. Perfect Squares
// DescriptionHintsSubmissionsDiscussSolution
//
// Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.
//
// Example 1:
//
// Input: n = 12
// Output: 3
// Explanation: 12 = 4 + 4 + 4.
//
// Example 2:
//
// Input: n = 13
// Output: 2
// Explanation: 13 = 4 + 9.
//
// Seen this question in a real interview before?
//
//     Difficulty:Medium


class Solution {
    public int numSquares(int n) {

        // return mytry(n);

        // return method1(n);

        return method2(n);
    }

    private int method2(int n) {
        // BFS
        // 把已经是 perfect square 的找到， 然后看他们是否能再组成新的 perfect square
        // 最少个数组成 n， 也就是最短路径 => BFS
        int[] count = new int[n + 1];
        List<Integer> perfect = new ArrayList<>();
        for (int i = 1; i * i <= n; i++) {
            int square = i * i;
            count[square] = 1;
            perfect.add(square);
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int num : perfect) {
            queue.offer(num);
        }
        int result = 0;
        while (!queue.isEmpty()) {
            result++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int curr = queue.poll();
                if (curr == n) {
                    return result;
                }
                for (int num : perfect) {
                    int next = curr + num; // 新的 perfect square
                    if (next <= n && count[next] == 0) {
                        queue.offer(next);
                        count[next] = count[curr] + 1;
                    } else if (next > n) {
                        break;
                    }
                }
            }
        }
        return -1;
    }

    private int method1(int n) {
        // definition: f[i] = lease perfect quare number for i
        int[] f = new int[n + 1];
        // initializatin
        Arrays.fill(f, n);
        f[0] = 0;
        f[1] = 1;
        // DP
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j * j <= i; j++) {
                f[i] = Math.min(f[i], f[i - j * j] + 1);
            }
        }
        // result
        return f[n];
    }

    private int mytry(int n) {
        // TLE
        // DFS 其实不怎么 make sense， 因为要找最小个数
        int[] result = new int[]{n};
        recursion(n, 1, 0, 0, result);
        return result[0];
    }
    private void recursion(int n, int start, int sum, int count, int[] result) {
        if (sum == n) {
            result[0] = Math.min(result[0], count);
        }
        if (sum > n) {
            return;
        }
        for (int i = start; i * i <= n; i++) {
            recursion(n, i, sum + i * i, count + 1, result);
        }
    }
}

// 279. Perfect Squares
// DescriptionHintsSubmissionsDiscussSolution
// Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.
//
// Example 1:
//
// Input: n = 12
// Output: 3
// Explanation: 12 = 4 + 4 + 4.
// Example 2:
//
// Input: n = 13
// Output: 2
// Explanation: 13 = 4 + 9.


class Solution {
    public int numSquares(int n) {
        if (n <= 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }

        // return method1(n);

        return method2(n);
    }

    private int method2(int n) {
        // BSF
        int[] counts = new int[n + 1];
        List<Integer> perfects = new ArrayList<>();
        for (int i = 1; i * i <= n; i++) {
            counts[i * i] = 1; // it's a perfect square so # is 1
            perfects.add(i * i); // record perfect square
        }
        if (perfects.get(perfects.size() - 1) == n) {
            return 1;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int num : perfects) {
            queue.offer(num);
        }
        int count = 1;
        while (!queue.isEmpty()) {
            count++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int curr = queue.poll();
                for (int num : perfects) {
                    if (curr + num == n) {
                        return count;
                    } else if (curr + num < n && counts[curr + num] == 0) {
                        counts[curr + num] = count;
                        queue.offer(curr + num);
                    } else if (curr + num > n) {
                        break;
                    }
                }
            }
        }
        return -1;
    }

    private int method1(int n) {
        // ref: https://leetcode.com/problems/perfect-squares/discuss/71495/An-easy-understanding-DP-solution-in-Java
        // DP: f[j] = min{f[j - i * i] + 1}, j - i * i >= 0, i >= 1
        // definition
        int[] f = new int[n + 1];
        // initialization
        Arrays.fill(f, Integer.MAX_VALUE);
        f[0] = 0;
        // DP
        for (int i = 1; i <= n; i++) {
            for (int j = 1; i - j * j >= 0; j++) {
                f[i] = Math.min(f[i], f[i - j * j] + 1);
            }
        }
        // result
        return f[n];
    }
}

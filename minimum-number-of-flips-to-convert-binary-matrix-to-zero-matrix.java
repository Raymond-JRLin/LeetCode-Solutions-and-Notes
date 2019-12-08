// 1284. Minimum Number of Flips to Convert Binary Matrix to Zero Matrix
//
//     User Accepted: 879
//     User Tried: 958
//     Total Accepted: 924
//     Total Submissions: 1335
//     Difficulty: Hard
//
// Given a m x n binary matrix mat. In one step, you can choose one cell and flip it and all the four neighbours of it if they exist (Flip is changing 1 to 0 and 0 to 1). A pair of cells are called neighboors if they share one edge.
//
// Return the minimum number of steps required to convert mat to a zero matrix or -1 if you cannot.
//
// Binary matrix is a matrix with all cells equal to 0 or 1 only.
//
// Zero matrix is a matrix with all cells equal to 0.
//
//
//
// Example 1:
//
// Input: mat = [[0,0],[0,1]]
// Output: 3
// Explanation: One possible solution is to flip (1, 0) then (0, 1) and finally (1, 1) as shown.
//
// Example 2:
//
// Input: mat = [[0]]
// Output: 0
// Explanation: Given matrix is a zero matrix. We don't need to change it.
//
// Example 3:
//
// Input: mat = [[1,1,1],[1,0,1],[0,0,0]]
// Output: 6
//
// Example 4:
//
// Input: mat = [[1,0,0],[1,0,0]]
// Output: -1
// Explanation: Given matrix can't be a zero matrix
//
//
//
// Constraints:
//
//     m == mat.length
//     n == mat[0].length
//     1 <= m <= 3
//     1 <= n <= 3
//     mat[i][j] is 0 or 1.
//


class Solution {
    public int minFlips(int[][] mat) {

        // return mytry(mat);

        return method2(mat);
    }

    private int method2(int[][] matrix) {
        // BFS with different hash
        // 这样的方式会比较好 handle 一点， 包括边界条件， 就可以直接查看 i/j 是否 valid
        int n = matrix.length;
        int m = matrix[0].length;
        int hash = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                // 把 matrix[i][j] 按照 1D 的 index hash 过去
                // 这样的顺序会是反过来的， e.g. [[0, 0], [0, 1]] => 1000
                hash |= matrix[i][j] << i * m + j;
            }
        }

        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        queue.offer(hash);
        visited.add(hash);
        int result = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int k = 0; k < size; k++) {
                int curr = queue.poll();
                if (curr == 0) {
                    return result;
                }
                // 尝试不同的位置及其 neighbors
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < m; j++) {
                        int num = curr ^ (1 << (i * m + j)); // flip current position at [i][j]
                        // flip its 4 neighbors to get new hash
                        for (int l = 0; l < 4; l++) {
                            int x = i + dx[l];
                            int y = j + dy[l];
                            if (x < 0 || x >= n || y < 0 || y >= m) {
                                continue;
                            }
                            num ^= (1 << (x * m + y));
                        }
                        if (visited.contains(num)) {
                            continue;
                        }
                        queue.offer(num);
                        visited.add(num);
                    }
                }

            }
            result++;
        }
        return -1;
    }

    private int mytry(int[][] matrix) {
        // 我看到的时候就想 BFS 应该就是暴力解了吧， 也担心 time complexity
        // 但其实最大只有 3， 所以干脆就直接 BFS 了
        // 我也考虑了是存 int[][] 还是 hash 成 string or bit
        // 我选择了 bit， 在改变周围 4 个位置的时候， 多花了一点时间， 有一点点难弄清楚， discussion 有不同的 hash 到 bit 的方式
        int n = matrix.length;
        int m = matrix[0].length;
        int len = n * m;
        int oriHash = hash(matrix);
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        queue.offer(oriHash);
        visited.add(oriHash);
        int result = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int k = 0; k < size; k++) {
                int curr = queue.poll();
                if (curr == 0) {
                    return result;
                }
                // System.out.println("curr = " + Integer.toString(curr, 2));
                for (int next : getNext(curr, n, m)) {
                    if (visited.contains(next)) {
                        continue;
                    }
                    queue.offer(next);
                    visited.add(next);
                }
            }
            result++;
        }
        return -1;
    }
    private List<Integer> getNext(int num, int n, int m) {
        int mask = 1;
        List<Integer> list = new ArrayList<>();
        // System.out.println("move " + Integer.toString(num, 2));
        int len = n * m;
        for (int i = 0; i < len; i++) {
            int curr = num;
            curr ^= (mask << i);
            // System.out.println(Integer.toString(curr, 2));
            // right
            if ((len - 1 - i) % m != m - 1) {
                curr ^= (mask << (i - 1));
                // System.out.println("right " + Integer.toString(curr, 2));
            }
            // left
            if ((len - 1 - i) % m != 0) {
                curr ^= (mask << (i + 1));
                // System.out.println("left " + Integer.toString(curr, 2));
            }
            // up
            if ((len - 1 - i) / m != 0) {
                curr ^= (mask << (i + m));
                // System.out.println("up " + Integer.toString(curr, 2));
            }
            // down
            if ((len - 1 - i) / m != n - 1) {
                curr ^= (mask << (i - m));
                // System.out.println("down " + Integer.toString(curr, 2));
            }
            // System.out.println("get next = " + Integer.toString(curr, 2));
            list.add(curr);
        }
        return list;
    }
    private int hash(int[][] matrix) {
        int result = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                result <<= 1;
                result |= matrix[i][j];
                // 其实可以把 matrix[i][j] 或到 result 上
                // 这样在求 next 的时候， 可以方便一点， 就是把走到的新的 [x][y] 移动到 result 上
                // result |= matrix[i][j] << i * n + j;
            }
        }
        return result;
    }
}

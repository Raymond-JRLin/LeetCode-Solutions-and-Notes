// 909. Snakes and Ladders
// Medium
//
// On an N x N board, the numbers from 1 to N*N are written boustrophedonically starting from the bottom left of the board, and alternating direction each row.  For example, for a 6 x 6 board, the numbers are written as follows:
//
//
// You start on square 1 of the board (which is always in the last row and first column).  Each move, starting from square x, consists of the following:
//
//     You choose a destination square S with number x+1, x+2, x+3, x+4, x+5, or x+6, provided this number is <= N*N.
//         (This choice simulates the result of a standard 6-sided die roll: ie., there are always at most 6 destinations, regardless of the size of the board.)
//     If S has a snake or ladder, you move to the destination of that snake or ladder.  Otherwise, you move to S.
//
// A board square on row r and column c has a "snake or ladder" if board[r][c] != -1.  The destination of that snake or ladder is board[r][c].
//
// Note that you only take a snake or ladder at most once per move: if the destination to a snake or ladder is the start of another snake or ladder, you do not continue moving.  (For example, if the board is `[[4,-1],[-1,3]]`, and on the first move your destination square is `2`, then you finish your first move at `3`, because you do not continue moving to `4`.)
//
// Return the least number of moves required to reach square N*N.  If it is not possible, return -1.
//
// Example 1:
//
// Input: [
// [-1,-1,-1,-1,-1,-1],
// [-1,-1,-1,-1,-1,-1],
// [-1,-1,-1,-1,-1,-1],
// [-1,35,-1,-1,13,-1],
// [-1,-1,-1,-1,-1,-1],
// [-1,15,-1,-1,-1,-1]]
// Output: 4
// Explanation:
// At the beginning, you start at square 1 [at row 5, column 0].
// You decide to move to square 2, and must take the ladder to square 15.
// You then decide to move to square 17 (row 3, column 5), and must take the snake to square 13.
// You then decide to move to square 14, and must take the ladder to square 35.
// You then decide to move to square 36, ending the game.
// It can be shown that you need at least 4 moves to reach the N*N-th square, so the answer is 4.
//
// Note:
//
//     2 <= board.length = board[0].length <= 20
//     board[i][j] is between 1 and N*N or is equal to -1.
//     The board square with number 1 has no snake or ladder.
//     The board square with number N*N has no snake or ladder.
//


class Solution {
    public int snakesAndLadders(int[][] board) {

        return method1(board);
    }

    private int method1(int[][] board) {
        // 肯定是 BFS
        // 注意两点：
        // 1. 如果计算下一步， 因为数值是 Z 形过来的
        // 2. 有 snake 或者 ladder 要跳到对应的位置, 这个其实不用太纠结说用一个 flag 表示顺的还是逆的， 去发现一下其中的规律
        // 正常的 1D value/index 和 coor 对应的关系是： [val / m, val % m]
        // 对于 row 来说， 只是从下往上反了一下， 所以 row = n - 1 - val / m
        // 对于 col 来说， 偶数 row 是正常序列， 奇数 row 是反过来的， 即 m - 1 - val % m, 这么看的时候只不过是旋转 180， 和原来计算 row 的结果一样， 不需要 n - 1 - val / m 来判断奇偶， 而是直接 val / m
        // 3 <------
        // 2 ------>
        // 1 <------
        // 0 ------>

        int n = board.length; // m == n
        // 转换成 1D index
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n * n + 1];
        queue.offer(1);
        visited[1] = true;
        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int curr = queue.poll();
                if (curr == n * n) {
                    return step;
                }
                for (int j = 1; j <= 6; j++) {
                    int next = curr + j;
                    if (next > n * n) {
                        break;
                    }
                    // if next position is snake or ladder
                    // 要先做这个， 不然查完 visited 后又跳走， 那么就不会记录 visited 信息了
                    int[] nextCoor = getNextCoor(n, next - 1);
                    if (board[nextCoor[0]][nextCoor[1]] != -1) {
                        next = board[nextCoor[0]][nextCoor[1]];
                    }
                    if (visited[next]) {
                        continue;
                    }
                    queue.offer(next);
                    visited[next] = true;
                }
            }
            step++;
        }
        return -1;
    }
    private int[] getNextCoor(int n, int num) {
        int i = num / n;
        int j = num % n;
        int row = n - 1 - i;
        int col = (i % 2 == 0 ? j : n - 1 - j);
        return new int[]{row, col};
    }
}

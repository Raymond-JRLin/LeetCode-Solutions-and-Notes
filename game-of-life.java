// 289. Game of Life
// DescriptionHintsSubmissionsDiscussSolution
// According to the Wikipedia's article: "The Game of Life, also known simply as Life, is a cellular automaton devised by the British mathematician John Horton Conway in 1970."
//
// Given a board with m by n cells, each cell has an initial state live (1) or dead (0). Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) using the following four rules (taken from the above Wikipedia article):
//
// Any live cell with fewer than two live neighbors dies, as if caused by under-population.
// Any live cell with two or three live neighbors lives on to the next generation.
// Any live cell with more than three live neighbors dies, as if by over-population..
// Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
// Write a function to compute the next state (after one update) of the board given its current state. The next state is created by applying the above rules simultaneously to every cell in the current state, where births and deaths occur simultaneously.
//
// Example:
//
// Input:
// [
//   [0,1,0],
//   [0,0,1],
//   [1,1,1],
//   [0,0,0]
// ]
// Output:
// [
//   [0,0,0],
//   [1,0,1],
//   [0,1,1],
//   [0,1,0]
// ]
// Follow up:
//
// Could you solve it in-place? Remember that the board needs to be updated at the same time: You cannot update some cells first and then use their updated values to update other cells.
// In this question, we represent the board using a 2D array. In principle, the board is infinite, which would cause problems when the active area encroaches the border of the array. How would you address these problems?


class Solution {
    public void gameOfLife(int[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return;
        }

        // mytry(board);

        method2(board);
    }

    private void method2(int[][] board) {
        // O(n * m) time and O(1) space
        // in-place 需要另外的数字来标记下一轮的生死， 这里巧妙的用了 [2nd bit, 1st bit] = [next state, current state]
        // - 00  dead (next) <- dead (current)
        // - 01  dead (next) <- live (current)
        // - 10  live (next) <- dead (current)
        // - 11  live (next) <- live (current)

        int m = board.length;
        int n = board[0].length;
        int[] dx = {0, 0, 1, -1, 1, 1, -1, -1};
        int[] dy = {1, -1, 0, 0, 1, -1, 1, -1};
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int live = 0;
                for (int k = 0; k < 8; k++) {
                    int x = i + dx[k];
                    int y = j + dy[k];
                    if (x < 0 || x >= m || y < 0 || y >= n) {
                        continue;
                    }
                    if ((board[x][y] & 1) == 1) {
                        live++;
                    }
                }
                if (board[i][j] == 1) {
                    // 如果本身是 live 然后 dead 那就不用改变， 仍然是 1
                    if (live == 2 || live == 3) {
                        board[i][j] = 3;
                    }
                }
                if (board[i][j] == 0) {
                    if (live == 3) {
                        board[i][j] = 2;
                    }
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = board[i][j] >> 1;
            }
        }
    }

    private void mytry(int[][] board) {
        // O(n * m) time and space
        int m = board.length;
        int n = board[0].length;
        int[][] next = new int[m][n]; // 使用额外的空间来保存下一轮的结果， 而总是使用给定的数组来作为判断的依据
        int[] dx = {0, 0, 1, -1, 1, 1, -1, -1};
        int[] dy = {1, -1, 0, 0, 1, -1, 1, -1};
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int live = 0;
                for (int k = 0; k < 8; k++) {
                    int x = i + dx[k];
                    int y = j + dy[k];
                    if (x < 0 || x >= m || y < 0 || y >= n) {
                        continue;
                    }
                    if (board[x][y] == 1) {
                        live++;
                    }
                }
                // 这里的改动要注意： 把结果存在新的 next 数组中， 而不改变原有数组， 不然的话会影响后面的判断
                if (board[i][j] == 1) {
                    if (live < 2) {
                        next[i][j] = 0;
                    } else if (live == 2 || live == 3) {
                        next[i][j] = 1;
                    } else {
                        next[i][j] = 0;
                    }
                } else {
                    next[i][j] = live == 3 ? 1 : 0;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = next[i][j];
            }
        }
    }
}

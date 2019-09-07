// 348. Design Tic-Tac-Toe
// DescriptionHintsSubmissionsDiscussSolution
// Design a Tic-tac-toe game that is played between two players on a n x n grid.
//
// You may assume the following rules:
//
// A move is guaranteed to be valid and is placed on an empty block.
// Once a winning condition is reached, no more moves is allowed.
// A player who succeeds in placing n of their marks in a horizontal, vertical, or diagonal row wins the game.
// Example:
// Given n = 3, assume that player 1 is "X" and player 2 is "O" in the board.
//
// TicTacToe toe = new TicTacToe(3);
//
// toe.move(0, 0, 1); -> Returns 0 (no one wins)
// |X| | |
// | | | |    // Player 1 makes a move at (0, 0).
// | | | |
//
// toe.move(0, 2, 2); -> Returns 0 (no one wins)
// |X| |O|
// | | | |    // Player 2 makes a move at (0, 2).
// | | | |
//
// toe.move(2, 2, 1); -> Returns 0 (no one wins)
// |X| |O|
// | | | |    // Player 1 makes a move at (2, 2).
// | | |X|
//
// toe.move(1, 1, 2); -> Returns 0 (no one wins)
// |X| |O|
// | |O| |    // Player 2 makes a move at (1, 1).
// | | |X|
//
// toe.move(2, 0, 1); -> Returns 0 (no one wins)
// |X| |O|
// | |O| |    // Player 1 makes a move at (2, 0).
// |X| |X|
//
// toe.move(1, 0, 2); -> Returns 0 (no one wins)
// |X| |O|
// |O|O| |    // Player 2 makes a move at (1, 0).
// |X| |X|
//
// toe.move(2, 1, 1); -> Returns 1 (player 1 wins)
// |X| |O|
// |O|O| |    // Player 1 makes a move at (2, 1).
// |X|X|X|
// Follow up:
// Could you do better than O(n2) per move() operation?


class TicTacToe {

    // mytry: just record the status each time
    // int[][] matrix;

    // method2: don't need to record N ^ 2 space but keep 2 1D array to track rows and cols respectively
    int[] rows;
    int[] cols;
    int diagonal;
    int antiDiagonal;
    int len;

    /** Initialize your data structure here. */
    public TicTacToe(int n) {
        //mytry
        // matrix = new int[n][n];

        // method2:
        rows = new int[n];
        cols = new int[n];
        diagonal = 0;
        antiDiagonal = 0;
        len = n;
    }

    /** Player {player} makes a move at ({row}, {col}).
        @param row The row of the board.
        @param col The column of the board.
        @param player The player, can be either 1 or 2.
        @return The current winning condition, can be either:
                0: No one wins.
                1: Player 1 wins.
                2: Player 2 wins. */
    public int move(int row, int col, int player) {
        // mytry:
        // matrix[row][col] = player;
        // if (isWin(matrix, row, col, player)) {
        //     return player;
        // } else {
        //     return 0;
        // }

        // method2:
        int val = player == 1 ? 1 : -1;
        rows[row] += val;
        cols[col] += val;
        if (row == col) {
            diagonal += val;
        }
        if (row + col == len - 1) {
            antiDiagonal += val;
        }
        if (Math.abs(rows[row]) == len || Math.abs(cols[col]) == len ||
            Math.abs(diagonal) == len || Math.abs(antiDiagonal) == len) {
            return player;
        } else {
            return 0;
        }
    }

    private boolean isWin(int[][] matrix, int row, int col, int player) {
        int n = matrix.length;
        int i, j;
        // check anti-diagonal if necessary
        if (row + col == n - 1) {
            i = 0;
            j = n - 1;
            while (i < n && j >= 0) {
                if (matrix[i][j] != player) {
                    break;
                }
                i++;
                j--;
            }
            if (i == n) {
                return true;
            }
        }
        // check diagonal if necessary
        if (row == col) {
            i = 0;
            j = 0;
            while (i < n && j < n) {
                if (matrix[i][j] != player) {
                    break;
                }
                i++;
                j++;
            }
            if (i == n) {
                return true;
            }
        }
        // chekc the same column
        i = 0;
        while (i < n) {
            if (matrix[i][col] != player) {
                break;
            }
            i++;
        }
        if (i == n) {
            return true;
        }
        // check the same raw
        j = 0;
        while (j < n) {
            if (matrix[row][j] != player) {
                break;
            }
            j++;
        }
        if (j == n) {
            return true;
        }
        // none of above satisfy
        return false;
    }
}

/**
 * Your TicTacToe object will be instantiated and called as such:
 * TicTacToe obj = new TicTacToe(n);
 * int param_1 = obj.move(row,col,player);
 */

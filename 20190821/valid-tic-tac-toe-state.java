// 794. Valid Tic-Tac-Toe State
// DescriptionHintsSubmissionsDiscussSolution
//
// A Tic-Tac-Toe board is given as a string array board. Return True if and only if it is possible to reach this board position during the course of a valid tic-tac-toe game.
//
// The board is a 3 x 3 array, and consists of characters " ", "X", and "O".  The " " character represents an empty square.
//
// Here are the rules of Tic-Tac-Toe:
//
//     Players take turns placing characters into empty squares (" ").
//     The first player always places "X" characters, while the second player always places "O" characters.
//     "X" and "O" characters are always placed into empty squares, never filled ones.
//     The game ends when there are 3 of the same (non-empty) character filling any row, column, or diagonal.
//     The game also ends if all squares are non-empty.
//     No more moves can be played if the game is over.
//
// Example 1:
// Input: board = ["O  ", "   ", "   "]
// Output: false
// Explanation: The first player always plays "X".
//
// Example 2:
// Input: board = ["XOX", " X ", "   "]
// Output: false
// Explanation: Players take turns making moves.
//
// Example 3:
// Input: board = ["XXX", "   ", "OOO"]
// Output: false
//
// Example 4:
// Input: board = ["XOX", "O O", "XOX"]
// Output: true
//
// Note:
//
//     board is a length-3 array of strings, where each string board[i] has length 3.
//     Each board[i][j] is a character in the set {" ", "X", "O"}.
//
// Seen this question in a real interview before?
//
//     Difficulty:Medium


class Solution {
    public boolean validTicTacToe(String[] board) {
        if (board == null || board.length < 3 || board[0].length() < 3) {
            return false;
        }

        return method1(board);
    }

    // 这个题理解了半天 =。=
    // 意思是说， 从什么都没有的起点开始下棋， 问是否能够下成当下这个局面

    private boolean method1(String[] board) {
        int[] rows = new int[3];
        int[] cols = new int[3];
        int diag = 0, antiDiag = 0;
        int turns = 0;
        boolean xWin = false, oWin = false;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                char c = board[i].charAt(j);
                if (c == ' ') {
                    continue;
                }
                if (c == 'X') {
                    turns++;
                    rows[i]++;
                    cols[j]++;
                } else {
                    turns--;
                    rows[i]--;
                    cols[j]--;
                }
                if (i == j) {
                    diag += (c == 'X' ? 1 : -1);
                }
                if (i + j == 2) {
                    antiDiag += (c == 'X' ? 1 : -1);
                }
            }
        }
        // check if anyone wins
        for (int i = 0; i < 3; i++) {
            if (rows[i] == 3 || cols[i] == 3) {
                xWin = true;
            }
            if (rows[i] == -3 || cols[i] == -3) {
                oWin = true;
            }
        }
        if (diag == 3 || antiDiag == 3) {
            xWin = true;
        }
        if (diag == -3 || antiDiag == -3) {
            oWin = true;
        }
        if (oWin && turns == 1) {
            // 此时 x 落完子但 o 赢了
            return false;
        }
        if (xWin && turns == 0) {
            // x 赢了, 却是 o 刚落子
            return false;
        }
        // 双方都没有赢
        // return (turns == 0 || turns == 1) && (!xWin || !oWin);
        return turns == 0 || turns == 1;
    }
}

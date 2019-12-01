// 1275. Find Winner on a Tic Tac Toe Game
//
//     User Accepted: 2375
//     User Tried: 2530
//     Total Accepted: 2419
//     Total Submissions: 4328
//     Difficulty: Easy
//
// Tic-tac-toe is played by two players A and B on a 3 x 3 grid.
//
// Here are the rules of Tic-Tac-Toe:
//
//     Players take turns placing characters into empty squares (" ").
//     The first player A always places "X" characters, while the second player B always places "O" characters.
//     "X" and "O" characters are always placed into empty squares, never on filled ones.
//     The game ends when there are 3 of the same (non-empty) character filling any row, column, or diagonal.
//     The game also ends if all squares are non-empty.
//     No more moves can be played if the game is over.
//
// Given an array moves where each element is another array of size 2 corresponding to the row and column of the grid where they mark their respective character in the order in which A and B play.
//
// Return the winner of the game if it exists (A or B), in case the game ends in a draw return "Draw", if there are still movements to play return "Pending".
//
// You can assume that moves is valid (It follows the rules of Tic-Tac-Toe), the grid is initially empty and A will play first.
//
//
//
// Example 1:
//
// Input: moves = [[0,0],[2,0],[1,1],[2,1],[2,2]]
// Output: "A"
// Explanation: "A" wins, he always plays first.
// "X  "    "X  "    "X  "    "X  "    "X  "
// "   " -> "   " -> " X " -> " X " -> " X "
// "   "    "O  "    "O  "    "OO "    "OOX"
//
// Example 2:
//
// Input: moves = [[0,0],[1,1],[0,1],[0,2],[1,0],[2,0]]
// Output: "B"
// Explanation: "B" wins.
// "X  "    "X  "    "XX "    "XXO"    "XXO"    "XXO"
// "   " -> " O " -> " O " -> " O " -> "XO " -> "XO "
// "   "    "   "    "   "    "   "    "   "    "O  "
//
// Example 3:
//
// Input: moves = [[0,0],[1,1],[2,0],[1,0],[1,2],[2,1],[0,1],[0,2],[2,2]]
// Output: "Draw"
// Explanation: The game ends in a draw since there are no moves to make.
// "XXO"
// "OOX"
// "XOX"
//
// Example 4:
//
// Input: moves = [[0,0],[1,1]]
// Output: "Pending"
// Explanation: The game has not finished yet.
// "X  "
// " O "
// "   "
//
//
//
// Constraints:
//
//     1 <= moves.length <= 9
//     moves[i].length == 2
//     0 <= moves[i][j] <= 2
//     There are no repeated elements on moves.
//     moves follow the rules of tic tac toe.
//


class Solution {
    public String tictactoe(int[][] moves) {

        return mytry(moves);
    }

    private String mytry(int[][] moves) {
        int n = moves.length;
        int[] rows = new int[3];
        int[] cols = new int[3];
        int diag = 0;
        int antiDiag = 0;

        int move = 1; // indicate first player 'A'
        for (int i = 0; i < n; i++) {
            int[] m = moves[i];
            rows[m[0]] += move;
            cols[m[1]] += move;
            if (m[0] == m[1]) {
                diag += move;
            }
            if (m[0] + m[1] == 2) {
                antiDiag += move;
            }
            // switch player
            move *= -1;
        }

        if (Math.abs(diag) == 3 || Math.abs(antiDiag) == 3) {
            // -1 means A just played, so if right now ends, then A won
            return move == -1 ? "A" : "B";
        }
        for (int i = 0; i < 3; i++) {
            if (Math.abs(rows[i]) == 3 || Math.abs(cols[i]) == 3) {
                return move == -1 ? "A" : "B";
            }
        }
        // no winner yet
        if (n == 9) {
            return "Draw";
        } else {
            return "Pending";
        }
    }
}

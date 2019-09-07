// 419. Battleships in a Board
// DescriptionHintsSubmissionsDiscussSolution
// Given an 2D board, count how many battleships are in it. The battleships are represented with 'X's, empty slots are represented with '.'s. You may assume the following rules:
// You receive a valid board, made of only battleships or empty slots.
// Battleships can only be placed horizontally or vertically. In other words, they can only be made of the shape 1xN (1 row, N columns) or Nx1 (N rows, 1 column), where N can be of any size.
// At least one horizontal or vertical cell separates between two battleships - there are no adjacent battleships.
// Example:
// X..X
// ...X
// ...X
// In the above board there are 2 battleships.
// Invalid Example:
// ...X
// XXXX
// ...X
// This is an invalid board that you will not receive - as battleships will always have a cell separating between them.
// Follow up:
// Could you do it in one-pass, using only O(1) extra memory and without modifying the value of the board?


class Solution {
    public int countBattleships(char[][] board) {
//         用BFS当然可以做， 但是要求one-pass,  only O(1) extra memory and without modifying， 就要考虑一下不同的想法了
//         这里有个条件： 两艘战舰之间一定会隔开来， 不然就是不会有的非法输入， 可以利用这一点来解： 双重 for 循环的时候， 总的走向是左上到右下， 所以某点的左上是战舰， 那么这点不论是什么都不能算了
        int n = board.length;
        int m = board[0].length;
        int result = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == '.' || (i > 0 && board[i - 1][j] == 'X') || (j > 0 && board[i][j - 1] == 'X')) {
                    continue;
                } else {
                    result++;
                }
            }
        }
        return result;
    }
}

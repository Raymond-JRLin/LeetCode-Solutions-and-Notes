// 529. Minesweeper
// DescriptionHintsSubmissionsDiscussSolution
// Let's play the minesweeper game (Wikipedia, online game)!
//
// You are given a 2D char matrix representing the game board. 'M' represents an unrevealed mine, 'E' represents an unrevealed empty square, 'B' represents a revealed blank square that has no adjacent (above, below, left, right, and all 4 diagonals) mines, digit ('1' to '8') represents how many mines are adjacent to this revealed square, and finally 'X' represents a revealed mine.
//
// Now given the next click position (row and column indices) among all the unrevealed squares ('M' or 'E'), return the board after revealing this position according to the following rules:
//
// If a mine ('M') is revealed, then the game is over - change it to 'X'.
// If an empty square ('E') with no adjacent mines is revealed, then change it to revealed blank ('B') and all of its adjacent unrevealed squares should be revealed recursively.
// If an empty square ('E') with at least one adjacent mine is revealed, then change it to a digit ('1' to '8') representing the number of adjacent mines.
// Return the board when no more squares will be revealed.
//
//
// Example 1:
//
// Input:
//
// [['E', 'E', 'E', 'E', 'E'],
//  ['E', 'E', 'M', 'E', 'E'],
//  ['E', 'E', 'E', 'E', 'E'],
//  ['E', 'E', 'E', 'E', 'E']]
//
// Click : [3,0]
//
// Output:
//
// [['B', '1', 'E', '1', 'B'],
//  ['B', '1', 'M', '1', 'B'],
//  ['B', '1', '1', '1', 'B'],
//  ['B', 'B', 'B', 'B', 'B']]
//
// Explanation:
//
// Example 2:
//
// Input:
//
// [['B', '1', 'E', '1', 'B'],
//  ['B', '1', 'M', '1', 'B'],
//  ['B', '1', '1', '1', 'B'],
//  ['B', 'B', 'B', 'B', 'B']]
//
// Click : [1,2]
//
// Output:
//
// [['B', '1', 'E', '1', 'B'],
//  ['B', '1', 'X', '1', 'B'],
//  ['B', '1', '1', '1', 'B'],
//  ['B', 'B', 'B', 'B', 'B']]
//
// Explanation:
//
//
//
// Note:
//
// The range of the input matrix's height and width is [1,50].
// The click position will only be an unrevealed square ('M' or 'E'), which also means the input board contains at least one clickable square.
// The input board won't be a stage when game is over (some mines have been revealed).
// For simplicity, not mentioned rules should be ignored in this problem. For example, you don't need to reveal all the unrevealed mines when the game is over, consider any cases that you will win the game or flag any squares.
// Seen this question in a real interview before?
// Difficulty:Medium


class Solution {
    public char[][] updateBoard(char[][] board, int[] click) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return board;
        }
        int n = board.length;
        int m = board[0].length;
        // unreavel the mine
        if (board[click[0]][click[1]] == 'M') {
            board[click[0]][click[1]] = 'X';
            return board;
        }
        // 就想到用 BFS 来做， 但是和其他的有点儿不同在于， 要找完四周全部没有 mine 了之后才可以更改并继续查找， 如果只要有一个 mine， 就需要标记 mine 的数量并停止
        int[] dx = {0, 0, 1, -1, 1, 1, -1, -1};
        int[] dy = {1, -1, 0, 0, 1, -1, 1, -1};
        Queue<Coordinate> queue = new LinkedList<>();
        Coordinate root = new Coordinate(click[0], click[1]);
        queue.offer(root);
        while (!queue.isEmpty()) {
            int count = 0;
            Coordinate curr = queue.poll();
            List<Coordinate> list = new ArrayList<>();
            for (int i = 0; i < 8; i++) {
                Coordinate nei = new Coordinate(curr.x + dx[i], curr.y + dy[i]);
                if (!isValid(board, nei)) {
                    // out of board
                    continue;
                }
                if (board[nei.x][nei.y] == 'M' || board[nei.x][nei.y] == 'X') {
                    // found a mine
                    count++;
                }
                // blank
                if (board[nei.x][nei.y] == 'E') {
                    list.add(nei);
                }
            }
            if (count > 0) {
                // found mine
                board[curr.x][curr.y] = (char) (count + '0');
            } else {
                // all others are not mine, so unreveal this position and go on
                board[curr.x][curr.y] = 'B';
                for (Coordinate coor : list) {
                    board[coor.x][coor.y] = 'B';
                    queue.offer(coor);
                }
            }
        }
        return board;
    }
    private boolean isValid(char[][] board, Coordinate nei) {
        int n = board.length;
        int m = board[0].length;
        if (nei.x < 0 || nei.x >= n || nei.y < 0 || nei.y >= m) {
            return false;
        } else {
            return true;
        }
    }
}
class Coordinate {
    public int x;
    public int y;
    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

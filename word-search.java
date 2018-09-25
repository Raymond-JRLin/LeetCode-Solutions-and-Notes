// 79. Word Search
// DescriptionHintsSubmissionsDiscussSolution
// Given a 2D board and a word, find if the word exists in the grid.
//
// The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.
//
// Example:
//
// board =
// [
//   ['A','B','C','E'],
//   ['S','F','C','S'],
//   ['A','D','E','E']
// ]
//
// Given word = "ABCCED", return true.
// Given word = "SEE", return true.
// Given word = "ABCB", return false.


class Solution {
    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return false;
        }
        if (word == null || word.length() == 0) {
            return true;
        }

        return mytry(board, word);

        // return method2(board, word);
    }

    private boolean method2(char[][] board, String word) {
        // 同样的 DFS 思路， 但是我们可以节省空间， 就是不用 visited 数组， 把原数组进行改变， 然后 再改回来
        int n = board.length;
        int m = board[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == word.charAt(0)) {
                    if (search(board, word, i, j, 0)) {
                        // 从当前点开始
                        return true;
                    }
                }
            }
        }
        return false;
    }
    private boolean search(char[][] board, String word, int x, int y, int index) {
        if (index == word.length()) {
            // 到达最后一位了
            return true;
        }
        if (!isValid(board, x, y)) {
            // 越界
            return false;
        }
        if (board[x][y] != word.charAt(index)) {
            // 不是 word 的字母
            return false;
        }
        if (board[x][y] == '#') {
            // 已访问过
            return false;
        }
        char origin = board[x][y];
        board[x][y] = '#'; // mark
        // go 4 directions
        if (search(board, word, x + 1, y, index + 1) || search(board, word, x - 1, y, index + 1) ||
            search(board, word, x, y + 1, index + 1) || search(board, word, x, y - 1, index + 1)) {
            return true;
        }
        board[x][y] = origin; // backtracking
        return false;
    }

    private boolean mytry(char[][] board, String word) {
        // 找是否存在这样一条路径， 并非需要找最短， 所以 DFS 就好； 同时如果一个字母周围有多个符合下一个的字母， 那么 BFS 会把这几个相同的都放进去， 同时标记为 visited， 那么如果这些相同的点在后面是构成 word 的答案， 那么就会出错, 所以应该用 DFS 一条路走到黑然后 backtracking， 同时用 visited 数组标记
        int n = board.length;
        int m = board[0].length;
        boolean[][] visited = new boolean[n][m];
        char c = word.charAt(0);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == c) {
                    if (dfs(board, word, i, j, 0, visited)) {
                        // 从当前点开始
                        return true;
                    }
                }
            }
        }
        return false;
    }
    private boolean dfs(char[][] board, String word, int x, int y, int index, boolean[][] visited) {
        if (index == word.length()) {
            // 到达最后一位了
            return true;
        }
        if (!isValid(board, x, y)) {
            // 越界
            return false;
        }
        if (board[x][y] != word.charAt(index)) {
            // 不是 word 的字母
            return false;
        }
        if (visited[x][y]) {
            // 已访问过
            return false;
        }
        visited[x][y] = true; // mark
        // go 4 directions
        if (dfs(board, word, x + 1, y, index + 1, visited) || dfs(board, word, x - 1, y, index + 1, visited) ||
            dfs(board, word, x, y + 1, index + 1, visited) || dfs(board, word, x, y - 1, index + 1, visited)) {
            return true;
        }
        visited[x][y] = false; // backtracking
        return false;
    }


    // private class Coordinate {
    //     int x;
    //     int y;
    //     public Coordinate(int x, int y) {
    //         this.x = x;
    //         this.y = y;
    //     }
    // }
    // private boolean bfs(char[][] board, String word, Coordinate root) {
    //     int n = board.length, m = board[0].length;
    //     int[] dx = {0, 0, 1, -1};
    //     int[] dy = {1, -1, 0, 0};
    //     int index = 1;
    //     boolean[][] visited = new boolean[n][m];
    //     visited[root.x][root.y] = true;
    //     Queue<Coordinate> queue = new LinkedList<>();
    //     queue.offer(root);
    //     while (!queue.isEmpty()) {
    //         if (index == word.length()) {
    //             return true;
    //         }
    //         int size = queue.size();
    //         for (int i = 0; i < size; i++) {
    //             Coordinate curr = queue.poll();
    //             for (int j = 0; j < 4; j++) {
    //                 int x = curr.x + dx[j];
    //                 int y = curr.y + dy[j];
    //                 if (!isValid(board, x, y) || visited[x][y]) {
    //                     // attention: we always need to check if it's valid first and then others
    //                     continue;
    //                 }
    //                 if (board[x][y] == word.charAt(index)) {
    //                     queue.offer(new Coordinate(x, y));
    //                     visited[x][y] = true;
    //                 }
    //             }
    //         }
    //         index++;
    //     }
    //     return false;
    // }
    private boolean isValid(char[][] board, int x, int y) {
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length) {
            return false;
        }
        return true;
    }
}

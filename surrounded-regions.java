// 130. Surrounded Regions
// DescriptionHintsSubmissionsDiscussSolution
// Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.
//
// A region is captured by flipping all 'O's into 'X's in that surrounded region.
//
// Example:
//
// X X X X
// X O O X
// X X O X
// X O X X
// After running your function, the board should be:
//
// X X X X
// X X X X
// X X X X
// X O X X
// Explanation:
//
// Surrounded regions shouldn’t be on the border, which means that any 'O' on the border of the board are not flipped to 'X'. Any 'O' that is not on the border and it is not connected to an 'O' on the border will be flipped to 'X'. Two cells are connected if they are adjacent cells connected horizontally or vertically.
//


class Solution {
    public void solve(char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return;
        }

        // mytry(board);

        // method2(board);

        method3(board);
    }

    private void method3(char[][] board) {
        // use BFS to realize method2 idea
        int n = board.length;
        int m = board[0].length;
        Queue<Coordinator> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (board[i][0] == 'O') {
                board[i][0] = '#';
                queue.offer(new Coordinator(i, 0));
            }
            if (board[i][m - 1] == 'O') {
                board[i][m - 1] = '#';
                queue.offer(new Coordinator(i, m - 1));
            }
        }
        for (int j = 0; j < m; j++) {
            if (board[0][j] == 'O') {
                board[0][j] = '#';
                queue.offer(new Coordinator(0, j));
            }
            if (board[n - 1][j] == 'O') {
                board[n - 1][j] = '#';
                queue.offer(new Coordinator(n - 1, j));
            }
        }
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};
        while (!queue.isEmpty()) {
            Coordinator curr = queue.poll();
            for (int i = 0; i < 4; i++) {
                int x = curr.x + dx[i];
                int y = curr.y + dy[i];
                if (x < 0 || x >= n || y < 0 || y >= m) {
                    continue;
                }
                if (board[x][y] == 'O') {
                    board[x][y] = '#';
                    queue.offer(new Coordinator(x, y));
                }
            }
        }
        // double check
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == '#') {
                    // change back to original
                    board[i][j] = 'O';
                } else if (board[i][j] == 'O') {
                    // change
                    board[i][j] = 'X';
                }
            }
        }
    }

    private void method2(char[][] board) {
        // 倒过来想： 从边界开始延伸， 边界开始的（连着的） O 都临时变成另一个字符， 然后 double check， 还保留着的 O 就可以改变， 然后把临时改变的符号变回来
        int n = board.length;
        int m = board[0].length;
        for (int i = 0; i < n; i++) {
            if (board[i][0] == 'O') {
                dfs(board, i, 0);
            }
            if (board[i][m - 1] == 'O') {
                dfs(board, i, m - 1);
            }
        }
        for (int j = 0; j < m; j++) {
            if (board[0][j] == 'O') {
                dfs(board, 0, j);
            }
            if (board[n - 1][j] == 'O') {
                dfs(board, n - 1, j);
            }
        }
        // double check
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == '#') {
                    // change back to original
                    board[i][j] = 'O';
                } else if (board[i][j] == 'O') {
                    // change
                    board[i][j] = 'X';
                }
            }
        }
    }
    private void dfs(char[][] board, int x, int y) {
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length) {
            return;
        }
        if (board[x][y] != 'O') {
            return;
        }
        board[x][y] = '#'; // change to another char to mark
        // go 4 directions
        dfs(board, x + 1, y);
        dfs(board, x - 1, y);
        dfs(board, x, y + 1);
        dfs(board, x, y - 1);
    }

    private void mytry(char[][] board) {
        int n = board.length;
        int m = board[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 'O') {
                    List<Coordinator> list = bfs(board, i, j, new boolean[n][m]);
                    // 每次放 new 的 visited 进去， 是因为如果边界在之前被访问过了， 那么在 bfs 中就没办法因为接触边界而退出
                    if (!list.isEmpty()) {
                        for (Coordinator coor : list) {
                            board[coor.x][coor.y] = 'X';
                        }
                    }
                }
            }
        }
    }
    private List<Coordinator> bfs(char[][] board, int i, int j, boolean[][] visited) {
        // 我的想法就是用 island 那题的 BFS， 只要碰到边界那么这一块都不能变化， 不然的话可以转变
        int n = board.length;
        int m = board[0].length;
        List<Coordinator> result = new ArrayList<>();
        Queue<Coordinator> queue = new LinkedList<>();
        Coordinator root = new Coordinator(i, j);
        queue.offer(root);
        result.add(root);
        visited[i][j] = true;
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};
        while (!queue.isEmpty()) {
            Coordinator curr = queue.poll();
            for (int k = 0; k < 4; k++) {
                int x = curr.x + dx[k];
                int y = curr.y + dy[k];
                if (x < 0 || x >= n || y < 0 || y >= m) {
                    return new ArrayList<>();
                }
                if (visited[x][y]) {
                    continue;
                }
                if (board[x][y] == 'O') {
                    Coordinator nei = new Coordinator(x, y);
                    queue.offer(nei);
                    result.add(nei);
                    visited[x][y] = true;
                }
            }
        }
        return result;
    }
    private class Coordinator {
        public int x;
        public int y;
        public Coordinator(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}

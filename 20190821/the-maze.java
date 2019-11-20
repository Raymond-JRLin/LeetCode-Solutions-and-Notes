// 490. The Maze
// DescriptionHintsSubmissionsDiscussSolution
//
// There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up, down, left or right, but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction.
//
// Given the ball's start position, the destination and the maze, determine whether the ball could stop at the destination.
//
// The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space. You may assume that the borders of the maze are all walls. The start and destination coordinates are represented by row and column indexes.
//
//
//
// Example 1:
//
// Input 1: a maze represented by a 2D array
//
// 0 0 1 0 0
// 0 0 0 0 0
// 0 0 0 1 0
// 1 1 0 1 1
// 0 0 0 0 0
//
// Input 2: start coordinate (rowStart, colStart) = (0, 4)
// Input 3: destination coordinate (rowDest, colDest) = (4, 4)
//
// Output: true
//
// Explanation: One possible way is : left -> down -> left -> down -> right -> down -> right.
//
// Example 2:
//
// Input 1: a maze represented by a 2D array
//
// 0 0 1 0 0
// 0 0 0 0 0
// 0 0 0 1 0
// 1 1 0 1 1
// 0 0 0 0 0
//
// Input 2: start coordinate (rowStart, colStart) = (0, 4)
// Input 3: destination coordinate (rowDest, colDest) = (3, 2)
//
// Output: false
//
// Explanation: There is no way for the ball to stop at the destination.
//
//
//
// Note:
//
//     There is only one ball and one destination in the maze.
//     Both the ball and the destination exist on an empty space, and they will not be at the same position initially.
//     The given maze does not contain border (like the red rectangle in the example pictures), but you could assume the border of the maze are all walls.
//     The maze contains at least 2 empty spaces, and both the width and height of the maze won't exceed 100.
//
// Seen this question in a real interview before?
//
//     Difficulty:Medium


class Solution {
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        if (maze == null || maze.length == 0 || maze[0].length == 0) {
            return false;
        }
        if (start[0] == destination[0] && start[1] == destination[1]) {
            return false;
        }

        return method1(maze, start, destination);
    }

    // 仍然可以看出是 BFS/DFS， 不同的点在于， 每次走到下一步是走到最远能滚到停下来的地方
    // 怎么处理呢？ 对于每次的 dx/dy， 不停地加直到碰到边界或是 wall

    private boolean method1(int[][] maze, int[] start, int[] dest) {
        int n = maze.length;
        int m = maze[0].length;
        boolean[][] visited = new boolean[n][m];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(start);
        visited[start[0]][start[1]] = true;
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            if (curr[0] == dest[0] && curr[1] == dest[1]) {
                return true;
            }
            // go 4 direction
            for (int i = 0; i < 4; i++) {
                int x = curr[0];
                int y = curr[1];
                // keep going to this direction until meet a boundary or a wall
                while (isValid(maze, x + dx[i], y + dy[i], n, m)) {
                    x += dx[i];
                    y += dy[i];
                }
                if (visited[x][y]) {
                    continue;
                }
                queue.offer(new int[]{x, y});
                visited[x][y] = true;
            }
        }
        return false;
    }
    private boolean isValid(int[][] maze, int x, int y, int n, int m) {
        if (x < 0 || x >= n || y < 0 || y >= m || maze[x][y] == 1) {
            return false;
        }
        return true;
    }
}

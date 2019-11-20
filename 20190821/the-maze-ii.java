// 505. The Maze II
// DescriptionHintsSubmissionsDiscussSolution
//
// There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up, down, left or right, but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction.
//
// Given the ball's start position, the destination and the maze, find the shortest distance for the ball to stop at the destination. The distance is defined by the number of empty spaces traveled by the ball from the start position (excluded) to the destination (included). If the ball cannot stop at the destination, return -1.
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
// Output: 12
//
// Explanation: One shortest way is : left -> down -> left -> down -> right -> down -> right.
//              The total distance is 1 + 1 + 3 + 1 + 2 + 2 + 2 = 12.
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
// Output: -1
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
    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        if (maze == null || maze.length == 0 || maze[0].length == 0) {
            return -1;
        }
        if (start[0] == destination[0] && start[1] == destination[1]) {
            return -1;
        }

        return method1(maze, start, destination);
    }

    // 可以用和 I 相同的做法， 每一个点带着走的距离， 可以用 int[3] 或者自己建一个 class Point
    // 这里注意的地方就是 visited[][], 到不同的点有不同的方式， 所以如果第一次到了就标记成 true， 之后不再访问
    // 那么可能得不到最短的距离
    // 所以这里不应该用 boolean 标记是否走过， 而是类似于 Dijkstra， 用一个 int[][] 不断更新能够到达点的最小距离

    private int method1(int[][] maze, int[] start, int[] dest) {
        int n = maze.length;
        int m = maze[0].length;
        int[][] distance = new int[n][m];
        for (int[] d : distance) {
            Arrays.fill(d, Integer.MAX_VALUE);
        }
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(start[0], start[1], 0));
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};
        int result = n * m;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int k = 0; k < size; k++) {
                Point curr = queue.poll();
                if (curr.x == dest[0] && curr.y == dest[1]) {
                    result = Math.min(result, curr.dist);
                }
                // go 4 direction
                for (int i = 0; i < 4; i++) {
                    int x = curr.x;
                    int y = curr.y;
                    // keep going to this direction until meet a boundary or a wall
                    int count = curr.dist;
                    while (isValid(maze, x + dx[i], y + dy[i], n, m)) {
                        x += dx[i];
                        y += dy[i];
                        count++;
                    }
                    // 已经到过这个点， 现在花费的步数还更大， 没必要再走了
                    if (distance[x][y] <= count) {
                        continue;
                    }
                    queue.offer(new Point(x, y, count));
                    distance[x][y] = count;
                }
            }

        }
        return result == n * m ? -1 : result;
    }
    private boolean isValid(int[][] maze, int x, int y, int n, int m) {
        if (x < 0 || x >= n || y < 0 || y >= m || maze[x][y] == 1) {
            return false;
        }
        return true;
    }

    private class Point {
        int x;
        int y;
        int dist;

        Point(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }
}

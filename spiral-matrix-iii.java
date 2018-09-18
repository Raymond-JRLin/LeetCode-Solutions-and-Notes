// 885. Spiral Matrix III
// User Accepted: 889
// User Tried: 971
// Total Accepted: 897
// Total Submissions: 1399
// Difficulty: Medium
// On a 2 dimensional grid with R rows and C columns, we start at (r0, c0) facing east.
//
// Here, the north-west corner of the grid is at the first row and column, and the south-east corner of the grid is at the last row and column.
//
// Now, we walk in a clockwise spiral shape to visit every position in this grid.
//
// Whenever we would move outside the boundary of the grid, we continue our walk outside the grid (but may return to the grid boundary later.)
//
// Eventually, we reach all R * C spaces of the grid.
//
// Return a list of coordinates representing the positions of the grid in the order they were visited.
//
//
//
// Example 1:
//
// Input: R = 1, C = 4, r0 = 0, c0 = 0
// Output: [[0,0],[0,1],[0,2],[0,3]]
//
//
//
//
// Example 2:
//
// Input: R = 5, C = 6, r0 = 1, c0 = 4
// Output: [[1,4],[1,5],[2,5],[2,4],[2,3],[1,3],[0,3],[0,4],[0,5],[3,5],[3,4],[3,3],[3,2],[2,2],[1,2],[0,2],[4,5],[4,4],[4,3],[4,2],[4,1],[3,1],[2,1],[1,1],[0,1],[4,0],[3,0],[2,0],[1,0],[0,0]]
//
//
//
//
// Note:
//
// 1 <= R <= 100
// 1 <= C <= 100
// 0 <= r0 < R
// 0 <= c0 < C


class Solution {
    public int[][] spiralMatrixIII(int R, int C, int r0, int c0) {
        if (R == 0 && C == 0) {
            return new int[0][0];
        }

        return method1(R, C, r0, c0);
    }

    private int[][] method1(int row, int col, int r0, int c0) {
        // ref: https://leetcode.com/problems/spiral-matrix-iii/discuss/158970/C++JavaPython-112233-Steps
        // ref: https://leetcode.com/problems/spiral-matrix-iii/discuss/158977/Java-15-lines-concise-solution-with-comments
        // we can find the sequence of steps: 1,1,2,2,3,3,4,4,5,5....The directions order is (0,1),(1,0),(0,-1),(-1,0), then repeat.
        int total = row * col;
        int[][] result = new int[total][2];
        int index = 0;
        result[index++] = new int[]{r0, c0};
        // attention the x & y to row & col
        int[] dx = {0, 1, 0, -1}; // right, down, left, up
        int[] dy = {1, 0, -1, 0};
        int dir = 0;
        int n = 0;
        while (index < total) {
            if (dir == 0 || dir == 2) {
                n++;
            }
            for (int i = 0; i < n; i++) {
                r0 += dx[dir];
                c0 += dy[dir];
                if (r0 >= 0 && r0 < row && c0 >= 0 && c0 < col) {
                    result[index++] = new int[]{r0, c0};
                }
            }
            dir = (dir + 1) % 4;
        }
        return result;
    }
}

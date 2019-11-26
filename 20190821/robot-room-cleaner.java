// 489. Robot Room Cleaner
// DescriptionHintsSubmissionsDiscussSolution
//
// Given a robot cleaner in a room modeled as a grid.
//
// Each cell in the grid can be empty or blocked.
//
// The robot cleaner with 4 given APIs can move forward, turn left or turn right. Each turn it made is 90 degrees.
//
// When it tries to move into a blocked cell, its bumper sensor detects the obstacle and it stays on the current cell.
//
// Design an algorithm to clean the entire room using only the 4 given APIs shown below.
//
// interface Robot {
//   // returns true if next cell is open and robot moves into the cell.
//   // returns false if next cell is obstacle and robot stays on the current cell.
//   boolean move();
//
//   // Robot will stay on the same cell after calling turnLeft/turnRight.
//   // Each turn will be 90 degrees.
//   void turnLeft();
//   void turnRight();
//
//   // Clean the current cell.
//   void clean();
// }
//
// Example:
//
// Input:
// room = [
//   [1,1,1,1,1,0,1,1],
//   [1,1,1,1,1,0,1,1],
//   [1,0,1,1,1,1,1,1],
//   [0,0,0,1,0,0,0,0],
//   [1,1,1,1,1,1,1,1]
// ],
// row = 1,
// col = 3
//
// Explanation:
// All grids in the room are marked by either 0 or 1.
// 0 means the cell is blocked, while 1 means the cell is accessible.
// The robot initially starts at the position of row=1, col=3.
// From the top left corner, its position is one row below and three columns right.
//
// Notes:
//
//     The input is only given to initialize the room and the robot's position internally. You must solve this problem "blindfolded". In other words, you must control the robot using only the mentioned 4 APIs, without knowing the room layout and the initial robot's position.
//     The robot's initial position will always be in an accessible cell.
//     The initial direction of the robot will be facing up.
//     All accessible cells are connected, which means the all cells marked as 1 will be accessible by the robot.
//     Assume all four edges of the grid are all surrounded by wall.
//
// Seen this question in a real interview before?
//
//     Difficulty:Hard


/**
 * // This is the robot's control interface.
 * // You should not implement it, or speculate about its implementation
 * interface Robot {
 *     // Returns true if the cell in front is open and robot moves into the cell.
 *     // Returns false if the cell in front is blocked and robot stays in the current cell.
 *     public boolean move();
 *
 *     // Robot will stay in the same cell after calling turnLeft/turnRight.
 *     // Each turn will be 90 degrees.
 *     public void turnLeft();
 *     public void turnRight();
 *
 *     // Clean the current cell.
 *     public void clean();
 * }
 */
class Solution {
    public void cleanRoom(Robot robot) {
        // 这里 dx/dy 按照顺时针方向放置
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};
        Set<String> set = new HashSet<>();
        dfs(robot, 0, 0, dx, dy, set, 0);
    }

    // 注意几点
    // 1. 和通常的 DFS 不同的是这里没有 matrix[][] 信息和起点位置等， 这其实也符合现实意义， robot cleaner 并不需要知道具体家庭的房间信息
    // 2. robot 有朝向问题， 并不是普通的能够直接去下一个点
    // 3. 回溯不同， 正因为有朝向问题， 所以并不能 “瞬移” 回去， 而是要旋转 180 度， 移动一格， 再旋转 180
    // 4. 注意审题， move() 是：如果可以移动， 则向前【移动一步】并返回 true， 如果不行， 则【停留原地】并返回 false
    // 5. 题目假设一开始 robot 朝上， 所以我们要按照这个方向开始， 然后可以选择譬如顺时针方向移动， 此时 x/y direction 的坐标变换也要符合我们方向的变化

    private void dfs(Robot robot, int i, int j, int[] dx, int[] dy, Set<String> set, int dir) {
        String pos = i + ":" + j;
        if (set.contains(pos)) {
            return;
        }

        robot.clean();
        set.add(pos);

        for (int k = 0; k < 4; k++) {
            // 这个地方不能跟着 k 走， 如上面第 5 点所述， 移动的方向要相符
            // 因为这里采用了顺时针走法， 那么下一步的 x/y 就要按照从上一步顺时针过来的
            // k 只起到了走 4 个方向的作用， 具体该走哪个方向， 由实际的 direction 决定
            int x = i + dx[dir];
            int y = j + dy[dir];
            // move() 的判断得放在这里， 而不能放在 recursion 一进来的地方
            // 如果放在开始的位置作为出口， 因为 move() 如果可以移动， 则直接移动到下一步
            // 而若不能移动， 则 return， 那么当前的位置实际上走到了并且如果需要 clean 的话， 会没被 clean 就返回
            if (robot.move()) {
                dfs(robot, x, y, dx, dy, set, dir);
                // 从 DFS 回来， 要 backtracking, 回到下一步之前的位置和方向
                robot.turnRight();
                robot.turnRight();
                robot.move();
                robot.turnRight();
                robot.turnRight();
            }
            // 按顺时针走下一个方向
            robot.turnRight();
            dir = (dir + 1) % 4;
        }
    }
}

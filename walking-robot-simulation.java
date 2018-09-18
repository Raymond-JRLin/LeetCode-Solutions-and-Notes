// 874. Walking Robot Simulation
// User Accepted: 536
// User Tried: 1302
// Total Accepted: 543
// Total Submissions: 3485
// Difficulty: Easy
// A robot on an infinite grid starts at point (0, 0) and faces north.  The robot can receive one of three possible types of commands:
//
// -2: turn left 90 degrees
// -1: turn right 90 degrees
// 1 <= x <= 9: move forward x units
// Some of the grid squares are obstacles.
//
// The i-th obstacle is at grid point (obstacles[i][0], obstacles[i][1])
//
// If the robot would try to move onto them, the robot stays on the previous grid square instead (but still continues following the rest of the route.)
//
// Return the square of the maximum Euclidean distance that the robot will be from the origin.
//
//
//
// Example 1:
//
// Input: commands = [4,-1,3], obstacles = []
// Output: 25
// Explanation: robot will go to (3, 4)
// Example 2:
//
// Input: commands = [4,-1,4,-2,4], obstacles = [[2,4]]
// Output: 65
// Explanation: robot will be stuck at (1, 4) before turning left and going to (1, 8)
//
//
// Note:
//
// 0 <= commands.length <= 10000
// 0 <= obstacles.length <= 10000
// -30000 <= obstacle[i][0] <= 30000
// -30000 <= obstacle[i][1] <= 30000
// The answer is guaranteed to be less than 2 ^ 31.


class Solution {
    public int robotSim(int[] commands, int[][] obstacles) {
        if (commands == null || commands.length == 0) {
            return 0;
        }

        return mytry(commands, obstacles);
    }

    private int mytry(int[] commands, int[][] obs) {
        // ref: https://leetcode.com/problems/walking-robot-simulation/discuss/152322/Maximum!-This-is-crazy!
        Set<String> set = new HashSet<>(); // record the obstacles as string type
        for (int[] ob : obs) {
            set.add(ob[0] + "," + ob[1]);
        }
        int[][] dir = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int d = 0; // face north
        int x = 0;
        int y = 0; // original point
        int result = 0;
        for (int c : commands) {
            if (c == -1) {
                d++;
                d = (d + 4) % 4;
            } else if (c == -2) {
                d--;
                d = (d + 4) % 4; // if it's negative, should add 4 first
            } else {
                while (c > 0 && !set.contains((x + dir[d][0]) + "," + (y + dir[d][1]))) {
                    // next point cannot be obstacle
                    x += dir[d][0];
                    y += dir[d][1];
                    c--;
                }
            }
            result = Math.max(result, x * x + y * y);
        }

        return result;
    }
}

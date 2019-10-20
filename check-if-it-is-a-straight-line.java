// 1232. Check If It Is a Straight Line
//
//     User Accepted: 3488
//     User Tried: 3799
//     Total Accepted: 3570
//     Total Submissions: 7994
//     Difficulty: Easy
//
// You are given an array coordinates, coordinates[i] = [x, y], where [x, y] represents the coordinate of a point. Check if these points make a straight line in the XY plane.
//
//
//
//
//
// Example 1:
//
// Input: coordinates = [[1,2],[2,3],[3,4],[4,5],[5,6],[6,7]]
// Output: true
//
// Example 2:
//
// Input: coordinates = [[1,1],[2,2],[3,4],[4,5],[5,6],[7,7]]
// Output: false
//
//
//
// Constraints:
//
//     2 <= coordinates.length <= 1000
//     coordinates[i].length == 2
//     -10^4 <= coordinates[i][0], coordinates[i][1] <= 10^4
//     coordinates contains no duplicate point.
//


class Solution {
    public boolean checkStraightLine(int[][] coordinates) {

        return mytry(coordinates);
    }

    private boolean mytry(int[][] coordinates) {
        int n = coordinates.length;
        double dy = coordinates[1][1] - coordinates[0][1];
        double dx = coordinates[1][0] - coordinates[0][0];
        if (dx == 0) {
            for (int i = 2; i < n; i++) {
                if (coordinates[i][0] != coordinates[0][0]) {
                    return false;
                }
            }
        } else {
            for (int i = 2; i < coordinates.length; i++) {
                if (coordinates[i][0] == coordinates[i - 1][0]) {
                    return false;
                }
                if ((double) ((coordinates[i][1] - coordinates[i - 1][1]) / (coordinates[i][0] - coordinates[i - 1][0])) != dy / dx) {
                    return false;
                }
            }
        }
        return true;
    }
}

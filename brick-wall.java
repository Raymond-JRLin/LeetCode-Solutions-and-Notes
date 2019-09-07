// 554. Brick Wall
// DescriptionHintsSubmissionsDiscussSolution
// There is a brick wall in front of you. The wall is rectangular and has several rows of bricks. The bricks have the same height but different width. You want to draw a vertical line from the top to the bottom and cross the least bricks.
//
// The brick wall is represented by a list of rows. Each row is a list of integers representing the width of each brick in this row from left to right.
//
// If your line go through the edge of a brick, then the brick is not considered as crossed. You need to find out how to draw the line to cross the least bricks and return the number of crossed bricks.
//
// You cannot draw a line just along one of the two vertical edges of the wall, in which case the line will obviously cross no bricks.
//
//
//
// Example:
//
// Input: [[1,2,2,1],
//         [3,1,2],
//         [1,3,2],
//         [2,4],
//         [3,1,2],
//         [1,3,1,1]]
//
// Output: 2
//
// Explanation:
//
//
//
// Note:
//
// The width sum of bricks in different rows are the same and won't exceed INT_MAX.
// The number of bricks in each row is in range [1,10,000]. The height of wall is in range [1,10,000]. Total number of bricks of the wall won't exceed 20,000.



class Solution {
    public int leastBricks(List<List<Integer>> wall) {
         // 看了答案的 =。= 我们可以记录砖缝的位置及其频率， 最高的地方就是我们要切的地方， 可以通过最少的砖 => HashMap
        if (wall == null || wall.size() == 0) {
            return 0;
        }
        Map<Integer, Integer> map = new HashMap<>(); // <position, frequency>
        int count = 0;
        for (List<Integer> list : wall) {
            int pos = 0;
            for (int i = 0; i < list.size() - 1; i++) {
                // attention we cannot get the edge, so we cannot sum up the last element of list to get whole length of row
                pos += list.get(i);
                if (map.containsKey(pos)) {
                    map.put(pos, map.get(pos) + 1);
                } else {
                    map.put(pos, 1);
                }
                count = Math.max(count, map.get(pos)); // update the most frequency
            }
        }
        return wall.size() - count; // row - #row of edges
    }
}

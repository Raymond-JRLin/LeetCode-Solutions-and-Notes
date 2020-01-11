// 1197. Minimum Knight Moves
// DescriptionHintsSubmissionsDiscussSolution
//
// In an infinite chess board with coordinates from -infinity to +infinity, you have a knight at square [0, 0].
//
// A knight has 8 possible moves it can make, as illustrated below. Each move is two squares in a cardinal direction, then one square in an orthogonal direction.
//
// Return the minimum number of steps needed to move the knight to the square [x, y].  It is guaranteed the answer exists.
//
//
//
// Example 1:
//
// Input: x = 2, y = 1
// Output: 1
// Explanation: [0, 0] → [2, 1]
//
// Example 2:
//
// Input: x = 5, y = 5
// Output: 4
// Explanation: [0, 0] → [2, 1] → [4, 2] → [3, 4] → [5, 5]
//
//
//
// Constraints:
//
//     |x| + |y| <= 300
//
// Seen this question in a real interview before?
//
//     Difficulty:Medium


class Solution {
    public int minKnightMoves(int x, int y) {

        return mytry(x, y);
    }

    private int mytry(int x, int y) {
        // 只需要看一个象限即可
        x = Math.abs(x);
        y = Math.abs(y);
        int[] dx = {1, 2, 2, 1, -1, -2, -2, -1};
        int[] dy = {2, 1, -1, -2, -2, -1, 1, 2};
        Queue<int[]> queue = new LinkedList<>();
        Set<String> set = new HashSet<>();
        queue.offer(new int[]{0, 0});
        set.add(0 + ":" + 0);
        int result = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll();
                if (curr[0] == x && curr[1] == y) {
                    return result;
                }
                for (int k = 0; k < dx.length; k++) {
                    int nextX = curr[0] + dx[k];
                    int nextY = curr[1] + dy[k];
                    // 不往别的象限走， 取 -2 是因为， 比如说要走到 [1, 1]， 没办法直接到， 需要先退到带负的象限再走过去， 最多到 -2
                    if (nextX < -2 || nextY < -2) {
                        continue;
                    }

                    int[] next = new int[]{nextX, nextY};
                    String nextStr = nextX + ":" + nextY;
                    if (set.contains(nextStr)) {
                        continue;
                    }
                    if (Math.abs(nextX) + Math.abs(nextY) > 300) {
                        continue;
                    }
                    queue.offer(next);
                    set.add(nextStr);
                }
            }
            result++;
        }
        return -1;
    }
}

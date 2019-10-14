// 1222. Queens That Can Attack the King
//
//     User Accepted: 2710
//     User Tried: 2894
//     Total Accepted: 2872
//     Total Submissions: 4111
//     Difficulty: Medium
//
// On an 8x8 chessboard, there can be multiple Black Queens and one White King.
//
// Given an array of integer coordinates queens that represents the positions of the Black Queens, and a pair of coordinates king that represent the position of the White King, return the coordinates of all the queens (in any order) that can attack the King.
//
//
//
// Example 1:
//
// Input: queens = [[0,1],[1,0],[4,0],[0,4],[3,3],[2,4]], king = [0,0]
// Output: [[0,1],[1,0],[3,3]]
// Explanation:
// The queen at [0,1] can attack the king cause they're in the same row.
// The queen at [1,0] can attack the king cause they're in the same column.
// The queen at [3,3] can attack the king cause they're in the same diagnal.
// The queen at [0,4] can't attack the king cause it's blocked by the queen at [0,1].
// The queen at [4,0] can't attack the king cause it's blocked by the queen at [1,0].
// The queen at [2,4] can't attack the king cause it's not in the same row/column/diagnal as the king.
//
// Example 2:
//
// Input: queens = [[0,0],[1,1],[2,2],[3,4],[3,5],[4,4],[4,5]], king = [3,3]
// Output: [[2,2],[3,4],[4,4]]
//
// Example 3:
//
// Input: queens = [[5,6],[7,7],[2,1],[0,7],[1,6],[5,1],[3,7],[0,3],[4,0],[1,2],[6,3],[5,0],[0,4],[2,2],[1,1],[6,4],[5,4],[0,0],[2,6],[4,5],[5,2],[1,4],[7,5],[2,3],[0,5],[4,2],[1,0],[2,7],[0,1],[4,6],[6,1],[0,6],[4,3],[1,7]], king = [3,4]
// Output: [[2,3],[1,4],[1,6],[3,7],[4,3],[5,4],[4,5]]
//
//
//
// Constraints:
//
//     1 <= queens.length <= 63
//     queens[0].length == 2
//     0 <= queens[i][j] < 8
//     king.length == 2
//     0 <= king[0], king[1] < 8
//     At most one piece is allowed in a cell.
//


class Solution {
    public List<List<Integer>> queensAttacktheKing(int[][] queens, int[] king) {

        return mytry(queens, king);
    }

    private List<List<Integer>> mytry(int[][] queens, int[] king) {
        // Map<Integer, int[]> map = new HashMap<>();
        Set<Integer> set = new HashSet<>();
        for (int[] queen : queens) {
            set.add(hash(queen));
        }
        List<List<Integer>> result = new ArrayList<>();
        int[][] directions = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, -1}, {-1, 1}, {1, 1}, {-1, -1}};
        for (int i = 0; i < directions.length; i++) {
            findQueen(result, king, set, directions[i]);
        }
        return result;

    }
    private void findQueen(List<List<Integer>> result, int[] king, Set<Integer> set, int[] dir) {
        for (int i = 1; i < 8; i++) {
            int[] next = new int[]{king[0] + dir[0] * i, king[1] + dir[1] * i};
            if (set.contains(hash(next))) {
                result.add(Arrays.asList(next[0], next[1]));
                return;
            }
        }
    }
    private int hash(int[] nums) {
        return nums[0] * 113 + nums[1];
    }
}

// 773. Sliding Puzzle
// DescriptionHintsSubmissionsDiscussSolution
//
// On a 2x3 board, there are 5 tiles represented by the integers 1 through 5, and an empty square represented by 0.
//
// A move consists of choosing 0 and a 4-directionally adjacent number and swapping it.
//
// The state of the board is solved if and only if the board is [[1,2,3],[4,5,0]].
//
// Given a puzzle board, return the least number of moves required so that the state of the board is solved. If it is impossible for the state of the board to be solved, return -1.
//
// Examples:
//
// Input: board = [[1,2,3],[4,0,5]]
// Output: 1
// Explanation: Swap the 0 and the 5 in one move.
//
// Input: board = [[1,2,3],[5,4,0]]
// Output: -1
// Explanation: No number of moves will make the board solved.
//
// Input: board = [[4,1,2],[5,0,3]]
// Output: 5
// Explanation: 5 is the smallest number of moves that solves the board.
// An example path:
// After move 0: [[4,1,2],[5,0,3]]
// After move 1: [[4,1,2],[0,5,3]]
// After move 2: [[0,1,2],[4,5,3]]
// After move 3: [[1,0,2],[4,5,3]]
// After move 4: [[1,2,0],[4,5,3]]
// After move 5: [[1,2,3],[4,5,0]]
//
// Input: board = [[3,2,4],[1,5,0]]
// Output: 14
//
// Note:
//
//     board will be a 2 x 3 array as described above.
//     board[i][j] will be a permutation of [0, 1, 2, 3, 4, 5].
//
// Seen this question in a real interview before?
//
//     Difficulty:Hard


class Solution {
    public int slidingPuzzle(int[][] board) {

        return method1(board);
    }

    // 注意审题， 0 表示空置的格子， 所以实际上每次移动都是 0 的上下左右可以移动到 0 的位置
    // 那么反过来想， 每次 0 可以移动的也同样是上下左右 4 个方向， 然后将 0 移动到下一个位置， 之后 0 的四周可以继续移动
    // 终点状态就是 123450 这样， 找最短路径数， 可以用 BFS
    // 这里去比较这个 board 是否一样， 会有点麻烦， 我们可以转成 string 来比较最终是否是 "123450"

    private int method1(int[][] board) {
        String target = "123450";
        String start = "";
        for (int[] b : board) {
            for (int num : b) {
                start += num;
            }
        }
        // 因为 board 是固定的 2 x 3， 所以可以直接写出每次位置可以移动的下一个位置
        // 注意我们把整个 board 拉长成一行 string， 这里位置也要相对应的变化, 下面的数字表示 string 中的 index
        // [
        //  [0, 1, 2],
        //  [3, 4, 5]
        // ]
        // dir[i] 表示当 0 位于 i 的时候可以移动的下一步
        int[][] dir = {{1, 3}, {0, 2, 4}, {1, 5}, {0, 4}, {1, 3, 5}, {2, 4}};
        Queue<String> queue = new LinkedList<>();
        Set<String> set = new HashSet<>();
        queue.offer(start);
        set.add(start);
        int result = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String curr = queue.poll();
                if (curr.equals(target)) {
                    return result;
                }
                int index = curr.indexOf('0');
                for (int j : dir[index]) {
                    String next = getNext(curr, index, j);
                    if (set.contains(next)) {
                        continue;
                    }
                    queue.offer(next);
                    set.add(next);
                }
            }
            result++;
        }
        return -1;
    }
    private String getNext(String curr, int i, int j) {
        char[] arr = curr.toCharArray();
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
        return new String(arr);
    }
}

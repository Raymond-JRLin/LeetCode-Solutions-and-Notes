// 947. Most Stones Removed with Same Row or Column
// DescriptionHintsSubmissionsDiscussSolution
//
// On a 2D plane, we place stones at some integer coordinate points.  Each coordinate point may have at most one stone.
//
// Now, a move consists of removing a stone that shares a column or row with another stone on the grid.
//
// What is the largest possible number of moves we can make?
//
//
//
// Example 1:
//
// Input: stones = [[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]
// Output: 5
//
// Example 2:
//
// Input: stones = [[0,0],[0,2],[1,1],[2,0],[2,2]]
// Output: 3
//
// Example 3:
//
// Input: stones = [[0,0]]
// Output: 0
//
//
//
// Note:
//
//     1 <= stones.length <= 1000
//     0 <= stones[i][j] < 10000
//
// Seen this question in a real interview before?
//
//     Difficulty:Medium


class Solution {
    public int removeStones(int[][] stones) {

        return method1(stones);

        // return method2(stones);
    }

    /*
    // 怎么写都不对， 还是没很理解 =。=
    private int method2(int[][] stones) {
        // union find
        UnionFind uf = new UnionFind(10000, stones);
        for (int[] stone : stones) {
            uf.union(stone[0], stone[1] + 10000);
            System.out.println(uf.count);
        }
        return uf.count;
    }
    private class UnionFind {
        int[] nums;
        int count;

        UnionFind(int n, int[][] arr) {
            this.nums = new int[n * 2];
            Arrays.fill(nums, -1);
            this.count = 0;
        }

        int find(int i) {
            if (nums[i] == -1) {
                nums[i] = i;
                this.count++;
            }
            if (i != nums[i]) {
                nums[i] = find(nums[i]);
            }
            return i;
        }

        void union(int a, int b) {
            int rootA = this.find(a);
            int rootB = this.find(b);
            if (nums[rootA] != rootB) {
                nums[find(rootA)] = find(rootB);
                count--;
            }
        }
    }
    */

    private int method1(int[][] stones) {
        // 按照要求， 同一个 row/col 的可以拿走， 那么最多就是整个连在一起的可以剩下一个， 其余的全拿走
        // 那么很类似 number of island 算联通块， 然后用 # stones - # connected 就可以得到
        // 但是这里很不同的是， 联通块并不是上下左右实际上连在一起， 而是 row/col 坐标相同就算连在一起
        Map<Integer, List<Integer>> adj = new HashMap<>();
        for (int[] stone : stones) {
            int x = stone[0];
            int y = stone[1] + 10000; // 区分 x/y
            adj.computeIfAbsent(x, dummy -> (new ArrayList<>())).add(y);
            adj.computeIfAbsent(y, dummy -> (new ArrayList<>())).add(x);
        }
        int island = 0;
        Set<Integer> visited = new HashSet<>();
        for (int[] stone : stones) {
            int x = stone[0];
            int y = stone[1] + 10000;
            if (!visited.contains(x)) {
                dfs(x, adj, visited);
                island++;
            }
            if (!visited.contains(y)) {
                dfs(y, adj, visited);
                island++;
            }
        }
        return stones.length - island;
    }
    private void dfs(int i, Map<Integer, List<Integer>> adj, Set<Integer> visited) {
        if (visited.contains(i)) {
            return;
        }
        // flooding
        visited.add(i);
        for (int next : adj.get(i)) {
            dfs(next, adj, visited);
        }
    }
}

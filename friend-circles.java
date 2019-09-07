// 547. Friend Circles
// DescriptionHintsSubmissionsDiscussSolution
// There are N students in a class. Some of them are friends, while some are not. Their friendship is transitive in nature. For example, if A is a direct friend of B, and B is a direct friend of C, then A is an indirect friend of C. And we defined a friend circle is a group of students who are direct or indirect friends.
//
// Given a N*N matrix M representing the friend relationship between students in the class. If M[i][j] = 1, then the ith and jth students are direct friends with each other, otherwise not. And you have to output the total number of friend circles among all the students.
//
// Example 1:
// Input:
// [[1,1,0],
//  [1,1,0],
//  [0,0,1]]
// Output: 2
// Explanation:The 0th and 1st students are direct friends, so they are in a friend circle.
// The 2nd student himself is in a friend circle. So return 2.
// Example 2:
// Input:
// [[1,1,0],
//  [1,1,1],
//  [0,1,1]]
// Output: 1
// Explanation:The 0th and 1st students are direct friends, the 1st and 2nd students are direct friends,
// so the 0th and 2nd students are indirect friends. All of them are in the same friend circle, so return 1.
// Note:
// N is in range [1,200].
// M[i][i] = 1 for all students.
// If M[i][j] = 1, then M[j][i] = 1.
// Seen this question in a real interview before?
// Difficulty:Medium


class Solution {
    public int findCircleNum(int[][] M) {
        if (M == null || M.length == 0) {
            return 0;
        }

//         try: use BFS to traverse half matrix
        // return my_try(M);

//         method 2: use DFS with same idea of BFS
        // return method2_DFS(M);

//         当然还可以使用 union find, 可能会更加复杂一些
        return method3_unionFind(M);
    }

    private int my_try(int[][] nums) {
        int n = nums.length;
        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();
        int count = 0;
        // 这和之前的 BFS 略有不同， 一共就是 n 个人， 分别以每个人开头去一直往下查找， 知道找不到有任何有关联的朋友为止
        for (int i = 0; i < n; i++) {
            // 每次以 i 开始取找它所有的朋友
            if (visited[i]) {
                continue;
            }
            queue.offer(i);
            while (!queue.isEmpty()) {
                int curr = queue.poll();
                visited[curr] = true;
                for (int j = 0; j < n; j++) {
                    if (visited[j] || nums[curr][j] == 0) {
                        // attention: what we are looking for now is curr rather than i
                        continue;
                    }
                    // 只要找到一个朋友就继续放进 queue 里面查找 curr 的朋友 - j - 的朋友
                    queue.offer(j);
                }
            }
            count++;
        }
        return count;
    }

    private int method2_DFS(int[][] nums) {
        int n = nums.length;
        boolean[] visited = new boolean[n];
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (visited[i]) {
                continue;
            }
            visited[i] = true;
            dfs(nums, visited, i); // 搜寻完 i 的所有的朋友及朋友的朋友的时候， 说明找到一个 circle
            count++;
        }
        return count;
    }
    private void dfs(int[][] nums, boolean[] visited, int curr) {
        for (int i = 0; i < nums.length; i++) {
            if (visited[i] || nums[curr][i] == 0) {
                continue;
            }
            visited[i] = true;
            dfs(nums, visited, i);
        }
    }

    private int method3_unionFind(int[][] nums) {
        int n = nums.length;
        int[] id = new int[n];
        for (int i = 0; i < n; i++) {
            id[i] = i;
        }
        int count = n;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (nums[i][j] == 1) {
                    int r1 = findRoot(id, i);
                    int r2 = findRoot(id, j);
                    if (r1 != r2) {
                        count--;
                        id[r1] = id[r2];
                    }
                }
            }
        }
        return count;
    }
    private int findRoot(int[] id, int i) {
        while (i != id[i]) {
            id[i] = id[id[i]];
            i = id[i];
        }
        return i;
    }
}

// 207. Course Schedule
// DescriptionHintsSubmissionsDiscussSolution
// There are a total of n courses you have to take, labeled from 0 to n-1.
//
// Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
//
// Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?
//
// Example 1:
//
// Input: 2, [[1,0]]
// Output: true
// Explanation: There are a total of 2 courses to take.
//              To take course 1 you should have finished course 0. So it is possible.
// Example 2:
//
// Input: 2, [[1,0],[0,1]]
// Output: false
// Explanation: There are a total of 2 courses to take.
//              To take course 1 you should have finished course 0, and to take course 0 you should
//              also have finished course 1. So it is impossible.
// Note:
//
// The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
// You may assume that there are no duplicate edges in the input prerequisites.


class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (prerequisites == null || prerequisites.length == 0 || prerequisites[0].length == 0) {
            return true;
        }

        return method1(numCourses, prerequisites);

        // return method2(numCourses, prerequisites);
    }

    private boolean method2(int n, int[][] pre) {
        // DFS
        // ref: https://leetcode.com/problems/course-schedule/discuss/58524/Java-DFS-and-BFS-solution
        int len = pre.length;
        // 1-  get adjacent list
        List<Integer>[] adj = new List[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 0; i < len; i++) {
            adj[pre[i][1]].add(pre[i][0]);
        }
        // 2- DFS using visited to track if there's a cycle
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!dfs(adj, visited, i)) {
                return false;
            }
        }
        return true;
    }
    private boolean dfs(List<Integer>[] adj, boolean[] visited, int course) {
        if (visited[course]) {
            return false;
        }
        visited[course] = true;
        for (int num : adj[course]) {
            if (!dfs(adj, visited, num)) {
                return false;
            }
        }
        visited[course] = false;
        return true;
    }

    private boolean method1(int n, int[][] pre) {
        // BFS
        int len = pre.length;
        // 1- get in-degrees
        int[] degree = new int[n];
        for (int i = 0; i < len; i++) {
            degree[pre[i][0]]++;
        }
        // 2- get adjacent list
        List<Integer>[] adj = new List[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 0; i < len; i++) {
            adj[pre[i][1]].add(pre[i][0]);
        }
        // 3- use BFS
        int count = 0;
        Queue<Integer> queue = new LinkedList<>();
        // put courses with in-degree of 0 into queue
        for (int i = 0; i < n; i++) {
            if (degree[i] == 0) {
                // 首先把不需要 pre 的课放进去
                queue.offer(i);
            }
        }
        // 如果没有入读为 0 的课， 肯定不能完成
        if (queue.isEmpty()) {
            return false;
        }
        // 如果有， 那么也不能直接返回 true， 因为有可能其中有环， 也有一个单独的先修课， e.g. [[1,0],[1,2],[0,1]]
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            count++;
            for (int i : adj[curr]) {
                // 这门 pre 修完可以减少哪些课程的 pre 条件
                degree[i]--;
                if (degree[i] == 0) {
                    // 如果这门课 pre 条件都修完了， 那么就可以放进 queue 里修了
                    queue.offer(i);
                }
            }
        }
        // 其实这题就是找是否能够一条线连起所有
        return count == n;
    }
}

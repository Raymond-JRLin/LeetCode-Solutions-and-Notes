// 210. Course Schedule II
// DescriptionHintsSubmissionsDiscussSolution
// There are a total of n courses you have to take, labeled from 0 to n-1.
//
// Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
//
// Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should take to finish all courses.
//
// There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all courses, return an empty array.
//
// Example 1:
//
// Input: 2, [[1,0]]
// Output: [0,1]
// Explanation: There are a total of 2 courses to take. To take course 1 you should have finished
//              course 0. So the correct course order is [0,1] .
// Example 2:
//
// Input: 4, [[1,0],[2,0],[3,1],[3,2]]
// Output: [0,1,2,3] or [0,2,1,3]
// Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both
//              courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0.
//              So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3] .
// Note:
//
// The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
// You may assume that there are no duplicate edges in the input prerequisites.


class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        if (prerequisites == null || prerequisites.length == 0 || prerequisites[0].length == 0) {
            int[] result = new int[numCourses];
            for (int i = 0; i < numCourses; i++) {
                result[i] = i;
            }
            return result;
        }

        // return mytry(numCourses, prerequisites);

        return method2(numCourses, prerequisites);
    }

    private int[] method2(int n, int[][] pre) {
        // DFS: 但是和 I 有所不同， 因为 I 中只需要去找从每个 course number 进入的时候没有 cycle 在里面， 那么我就可以走到所有的课程， 但是这里需要输出能够上课的顺序， 在 dfs 中如果每次先标记为 true 然后还原成 false， 那么就会在 for 循环每次进入课程 number 的时候都会加进 stack， 所以要标记访问过、当前正在 dfs 中访问、没访问过 3 种不同的状态
        // 1: get adjacent list
        List<Integer>[] adj = new List[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
        }
        for (int[] p : pre) {
            adj[p[1]].add(p[0]);
        }
        // 2: DFS - 之所以 DFS 也行， 是因为 DFS 总是一条道走到最后， 那么只要当前课程是某些课程的先修课， 那么修完当前课程就可以继续往下走， 直到走到最后
        Stack<Integer> stack = new Stack<>();
        int[] visited = new int[n]; // 0: never visit, 1: visiting in this DFS, 2: visited
        for (int i = 0; i < n; i++) {
            if (!dfs(i, adj, visited, stack)) {
                return new int[0];
            }
        }
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            result[i] = stack.pop(); // recursion 走到最底层才放入 stack， 倒过来 pop 就正好了
        }
        return result;
    }
    private boolean dfs(int course, List<Integer>[] adj, int[] visited, Stack<Integer> stack) {
        //
        if (visited[course] == 2) {
            // System.out.println("already visited: " + course);
            return true; //
        }
        if (visited[course] == 1) {
            return false;
        }
        visited[course] = 1;
        // System.out.println("now check course: " + course + "'s next");
        for (int num : adj[course]) {
            if (!dfs(num, adj, visited, stack)) {
                return false;
            }
        }
        stack.push(course);
        // System.out.println("push: " + course);
        visited[course] = 2;
        return true;
    }

    private int[] mytry(int n, int[][] pre) {
        // do BFS like I
        // 1: get adjacent list
        List<Integer>[] adj = new List[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
        }
        for (int[] p : pre) {
            adj[p[1]].add(p[0]);
        }
        // 2: get in-degree
        int[] degree = new int[n];
        for (int[] p : pre) {
            degree[p[0]]++;
        }
        // 3: do BFS to check if we can finish all courses
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (degree[i] == 0) {
                queue.offer(i);
            }
        }
        // can't finish
        if (queue.isEmpty()) {
            return new int[0];
        }
        int[] result = new int[n];
        int index = 0;
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            result[index++] = curr;
            for (int num : adj[curr]) {
                degree[num]--;
                if (degree[num] == 0) {
                    queue.offer(num);
                }
            }
        }
        if (index == n) {
            // can finish all courses
            return result;
        } else {
            return new int[0];
        }
    }
}

// 785. Is Graph Bipartite?
// DescriptionHintsSubmissionsDiscussSolution
// Given an undirected graph, return true if and only if it is bipartite.
//
// Recall that a graph is bipartite if we can split it's set of nodes into two independent subsets A and B such that every edge in the graph has one node in A and another node in B.
//
// The graph is given in the following form: graph[i] is a list of indexes j for which the edge between nodes i and j exists.  Each node is an integer between 0 and graph.length - 1.  There are no self edges or parallel edges: graph[i] does not contain i, and it doesn't contain any element twice.
//
// Example 1:
// Input: [[1,3], [0,2], [1,3], [0,2]]
// Output: true
// Explanation:
// The graph looks like this:
// 0----1
// |    |
// |    |
// 3----2
// We can divide the vertices into two groups: {0, 2} and {1, 3}.
// Example 2:
// Input: [[1,2,3], [0,2], [0,1,3], [0,2]]
// Output: false
// Explanation:
// The graph looks like this:
// 0----1
// | \  |
// |  \ |
// 3----2
// We cannot find a way to divide the set of nodes into two independent subsets.
//
//
// Note:
//
// graph will have length in range [1, 100].
// graph[i] will contain integers in range [0, graph.length - 1].
// graph[i] will not contain i or duplicate values.
// The graph is undirected: if any element j is in graph[i], then i will be in graph[j].
// Seen this question in a real interview before?
// Difficulty:Medium


class Solution {
    public boolean isBipartite(int[][] graph) {

        // iteration
        // return method1(graph);

        // recursion
        return method2(graph);
    }

    private boolean method2(int[][] graph) {
        int n = graph.length;
        int[] colors = new int[n];
        Arrays.fill(colors, -1); // because we use recursion method, so we cannot use hard code saying 1 or -1 to be the color, so we should use 1 - currentColor
        for (int i = 0; i < n; i++) {
            if (colors[i] == -1 && !isValidColor(graph, colors, 1, i)) {
                return false;
            }
        }
        return true;
    }
    private boolean isValidColor(int[][] graph, int[] colors, int color, int index) {
        // in recursion method, we not only return true or false, but also color the opposite side's nodes
        if (colors[index] != -1) {
            return colors[index] == color;
        }
        colors[index] = color; // mark current index
        for (int num : graph[index]) {
            if (!isValidColor(graph, colors, 1 - color, num)) {
                return false;
            }
        }
        return true;
    }

    private boolean method1(int[][] graph) {
        // use an array to record the mark/color of i position, if total array can be marked by 2 colors without conflict then it is bipartite
        // iteration
        int n = graph.length;
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            if (result[i] == 0 || result[i] == 1) {
                result[i] = 1;
                for (int num : graph[i]) {
                    if (result[num] == 1) {
                        return false;
                    } else {
                        result[num] = -1;
                    }
                }
            } else {
                for (int num : graph[i]) {
                    if (result[num] == -1) {
                        return false;
                    } else {
                        result[num] = 1;
                    }
                }
            }
        }
        return true;
    }
}

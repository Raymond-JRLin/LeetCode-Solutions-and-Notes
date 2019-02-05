// 797. All Paths From Source to Target
// DescriptionHintsSubmissionsDiscussSolution
// Given a directed, acyclic graph of N nodes.  Find all possible paths from node 0 to node N-1, and return them in any order.
//
// The graph is given as follows:  the nodes are 0, 1, ..., graph.length - 1.  graph[i] is a list of all nodes j for which the edge (i, j) exists.
//
// Example:
// Input: [[1,2], [3], [3], []]
// Output: [[0,1,3],[0,2,3]]
// Explanation: The graph looks like this:
// 0--->1
// |    |
// v    v
// 2--->3
// There are two paths: 0 -> 1 -> 3 and 0 -> 2 -> 3.
// Note:
//
// The number of nodes in the graph will be in the range [2, 15].
// You can print different paths in any order, but you should keep the order of nodes inside one path.


class Solution {
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        if (graph == null || graph.length == 0 || graph[0].length == 0) {
            return Collections.emptyList();
        }

        // return mytry(graph);

        return method2(graph);
    }

    private List<List<Integer>> method2(int[][] graph) {
        // 给的是个数组， 其实并不需要 adjacent list， 数组是可以很快的得到的
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        list.add(0);
        dfs(graph, 0, result, list);
        return result;
    }
    private void dfs(int[][] graph, int curr, List<List<Integer>> result, List<Integer> list) {
        if (curr == graph.length - 1) {
            result.add(new ArrayList<>(list));
            return;
        }
        if (graph[curr].length == 0) {
            return;
        }
        for (int next : graph[curr]) {
            list.add(next);
            dfs(graph, next, result, list);
            list.remove(list.size() - 1);
        }
    }

    private List<List<Integer>> mytry(int[][] graph) {
        int n = graph.length;
        // adjacent list
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int i = 0; i < n; i++) {
            for (int num : graph[i]) {
                adj.get(i).add(num);
            }
        }
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        list.add(0);
        dfs(adj, 0, n - 1, result, list);
        return result;
    }
    private void dfs(List<List<Integer>> adj, int curr, int end, List<List<Integer>> result, List<Integer> list) {
        if (curr == end) {
            result.add(new ArrayList<>(list));
            return;
        }
        if (adj.get(curr).size() == 0) {
            return;
        }
        for (int next : adj.get(curr)) {
            list.add(next);
            dfs(adj, next, end, result, list);
            list.remove(list.size() - 1);
        }
    }
}

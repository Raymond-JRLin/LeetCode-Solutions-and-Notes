// 851. Loud and Rich
// DescriptionHintsSubmissionsDiscussSolution
// In a group of N people (labelled 0, 1, 2, ..., N-1), each person has different amounts of money, and different levels of quietness.
//
// For convenience, we'll call the person with label x, simply "person x".
//
// We'll say that richer[i] = [x, y] if person x definitely has more money than person y.  Note that richer may only be a subset of valid observations.
//
// Also, we'll say quiet[x] = q if person x has quietness q.
//
// Now, return answer, where answer[x] = y if y is the least quiet person (that is, the person y with the smallest value of quiet[y]), among all people who definitely have equal to or more money than person x.
//
//
//
// Example 1:
//
// Input: richer = [[1,0],[2,1],[3,1],[3,7],[4,3],[5,3],[6,3]], quiet = [3,2,5,4,6,1,7,0]
// Output: [5,5,2,5,4,5,6,7]
// Explanation:
// answer[0] = 5.
// Person 5 has more money than 3, which has more money than 1, which has more money than 0.
// The only person who is quieter (has lower quiet[x]) is person 7, but
// it isn't clear if they have more money than person 0.
//
// answer[7] = 7.
// Among all people that definitely have equal to or more money than person 7
// (which could be persons 3, 4, 5, 6, or 7), the person who is the quietest (has lower quiet[x])
// is person 7.
//
// The other answers can be filled out with similar reasoning.
// Note:
//
// 1 <= quiet.length = N <= 500
// 0 <= quiet[i] < N, all quiet[i] are different.
// 0 <= richer.length <= N * (N-1) / 2
// 0 <= richer[i][j] < N
// richer[i][0] != richer[i][1]
// richer[i]'s are all different.
// The observations in richer are all logically consistent.
// Seen this question in a real interview before?
// Difficulty:Medium


class Solution {
    public int[] loudAndRich(int[][] richer, int[] quiet) {

        // return mytry(richer, quiet);

        return method2(richer, quiet);
    }

    private int[] method2(int[][] richer, int[] quiet) {
        // DFS
        // https://leetcode.com/problems/loud-and-rich/discuss/137918/C++JavaPython-Concise-DFS
        int n = quiet.length;
        // construct "adjacent list"
        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(new ArrayList<>());
        }
        for (int i = 0; i < richer.length; i++) {
            list.get(richer[i][1]).add(richer[i][0]);
        }

        int[] result = new int[n];
        Arrays.fill(result, -1);
        for (int i = 0; i < n; i++) {
            dfs(list, quiet, result, i);
        }

        return result;
    }
    private int dfs(List<List<Integer>> list, int[] quiet, int[] result, int curr) {
        if (result[curr] != -1) {
            return result[curr];
        }
        result[curr] = curr;
        for (int next : list.get(curr)) {
            // every time we update result[curr], so we should use result[curr] to get quiet
            if (quiet[dfs(list, quiet, result, next)] < quiet[result[curr]]) {
                result[curr] = result[next];
            }

        }
        return result[curr];
    }

    private int[] mytry(int[][] richer, int[] quiet) {
        // BFS
        int n = quiet.length;
        // construct "adjacent list"
        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(new ArrayList<>());
        }
        for (int i = 0; i < richer.length; i++) {
            list.get(richer[i][1]).add(richer[i][0]);
        }

        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            // no one is richer than i
            if (list.get(i).size() == 0) {
                result[i] = i;
                continue;
            }
            // otherwise,do BFS
            result[i] = bfs(list, quiet, i);
        }

        return result;
    }
    private int bfs(List<List<Integer>> list, int[] quiet, int index) {
        int result = index;
        int q = Integer.MAX_VALUE; // least quiet
        boolean[] visited = new boolean[quiet.length];
        Queue<Integer> queue = new LinkedList<>();
        visited[index] = true;
        queue.offer(index);

        while (!queue.isEmpty()) {
            int curr = queue.poll();
            if (quiet[curr] < q) {
                q = quiet[curr];
                result = curr;
            }
            if (list.get(curr).size() != 0) {
                for (int next : list.get(curr)) {
                    if (visited[next]) {
                        continue;
                    }
                    queue.offer(next);
                    visited[next] = true;
                }
            }
        }
        return result;
    }
}

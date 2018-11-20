// 269. Alien Dictionary
// DescriptionHintsSubmissionsDiscussSolution
// There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you. You receive a list of non-empty words from the dictionary, where words are sorted lexicographically by the rules of this new language. Derive the order of letters in this language.
//
// Example 1:
//
// Input:
// [
//   "wrt",
//   "wrf",
//   "er",
//   "ett",
//   "rftt"
// ]
//
// Output: "wertf"
// Example 2:
//
// Input:
// [
//   "z",
//   "x"
// ]
//
// Output: "zx"
// Example 3:
//
// Input:
// [
//   "z",
//   "x",
//   "z"
// ]
//
// Output: ""
//
// Explanation: The order is invalid, so return "".
// Note:
//
// You may assume all letters are in lowercase.
// You may assume that if a is a prefix of b, then a must appear before b in the given dictionary.
// If the order is invalid, return an empty string.
// There may be multiple valid order of letters, return any one of them is fine.


class Solution {
    public String alienOrder(String[] words) {
        if (words == null || words.length == 0) {
            return "";
        }

        // return method1(words);

        return method2(words);
    }

    private String method2(String[] words) {
        // DFS: 略有一些不清楚， 不懂的点在于为什么最终答案和 for i 0 - n 的顺序无关， 而且最后要 reverse， reverse 我是觉得因为一直往下 recursion， 到最低无法继续的时候就是当前这个顺序能走的最后一位
        // 有一些 case 无法通过， 比如 ["er","ett","wwt","wf","rftt"]， 但也可能是因为本身这个 case 中 f 无处安放， 不知道确切的顺序
        // ref: https://leetcode.com/problems/alien-dictionary/discuss/70115/3ms-Clean-Java-Solution-(DFS)
        int n = 26;
        boolean[][] adj = new boolean[n][n];
        int[] visited = new int[n];
        buildGraph(words, adj, visited);

        StringBuilder sb = new StringBuilder();
        // print(visited);
        for (int i = 0; i < n; i++) {
            if (visited[i] == 0) {
                if (!dfs(adj, visited, sb, n, i)) {
                    return "";
                }
                // print(visited);
            }

        }

        return sb.reverse().toString();
    }
    private void print(int[] visited) {
        for (int num : visited) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
    private boolean dfs(boolean[][] adj, int[] visited, StringBuilder sb, int n, int curr) {
        visited[curr] = 1; // is visiting in this recursion
        // System.out.println("mark " + (char) (curr + 'a') + " as 1");
        for (int next = 0; next < n; next++) {
            if (adj[curr][next]) {
                // System.out.println("next: " + (char) (next + 'a'));
                // can go from curr to next
                if (visited[next] == 1) {
                    // System.out.println("already visited ");
                    // next is used in this recursion => cycle
                    return false;
                }
                if (visited[next] == 0) {
                    // System.out.println("it's 0, go next");
                    if (!dfs(adj, visited, sb, n, next)) {
                        // System.out.println("false");
                        return false;
                    }
                }
            }
        }
        visited[curr] = 2; // visited done and exit recursion
        sb.append((char) (curr + 'a'));
        // System.out.println("mark "+ (char) (curr + 'a') + " as 2 and append to be " + sb.toString());
        return true;
    }
    private void buildGraph(String[] words, boolean[][] adj, int[] visited) {
        for (int i = 1; i < words.length; i++) {
            String s1 = words[i - 1];
            String s2 = words[i];
            for (int j = 0; j < Math.min(s1.length(), s2.length()); j++) {
                if (s1.charAt(j) != s2.charAt(j)) {
                    adj[s1.charAt(j) - 'a'][s2.charAt(j) - 'a'] = true;
                    break;
                }
            }
        }

        Arrays.fill(visited, -1); // as non-existed originally
        for (String s : words) {
            for (char c : s.toCharArray()) {
                visited[c - 'a'] = 0; // as existed, but not visited yet
            }
        }
        // visited[i] = -1. Not even exist.
        // visited[i] = 0. Exist. Non-visited.
        // visited[i] = 1. Visiting.
        // visited[i] = 2. Visited.
    }

    private String method1(String[] words) {
        // topological sorting
        // 1. get adjacent list
        Map<Character, Set<Character>> adj = getAdj(words);
        // for (char key : adj.keySet()) {
        //     System.out.print(key + " : ");
        //     for (char next : adj.get(key)) {
        //         System.out.print(next + " ");
        //     }
        //     System.out.println();
        // }
        // 2. get in-degree
        Map<Character, Integer> indegree = getIndegree(adj);
        // System.out.println("indegree: ");
        // for (char key : indegree.keySet()) {
        //     System.out.println(key + " : " + indegree.get(key));
        // }
        // 3. topological sorting
        return topologicalSorting(adj, indegree);
    }
    private String topologicalSorting(Map<Character, Set<Character>> adj, Map<Character, Integer> indegree) {
        StringBuilder sb = new StringBuilder();
        PriorityQueue<Character> pq = new PriorityQueue<>();
        for (char key : indegree.keySet()) {
            if (indegree.get(key) == 0) {
                pq.offer(key);
            }
        }
        if (pq.isEmpty()) {
            // System.out.println("pq is empty");
            return "";
        }
        while (!pq.isEmpty()) {
            char curr = pq.poll();
            sb.append(curr);
            for (char next : adj.get(curr)) {
                indegree.put(next, indegree.get(next) - 1);
                if (indegree.get(next) == 0) {
                    pq.offer(next);
                }
            }
        }
        if (sb.length() != indegree.size()) {
            // System.out.println("string size is diff: " + sb.toString());
            return "";
        }
        return sb.toString();
    }
    private Map<Character, Integer> getIndegree(Map<Character, Set<Character>> adj) {
        Map<Character, Integer> result = new HashMap<>();
        for (char key : adj.keySet()) {
            result.put(key, 0);
        }
        for (char key : adj.keySet()) {
            for (char val : adj.get(key)) {
                result.put(val, result.get(val) + 1);
            }
        }
        return result;
    }
    private Map<Character, Set<Character>> getAdj(String[] words) {
        Map<Character, Set<Character>> result = new HashMap<>();
        for (String word : words) {
            for (char c : word.toCharArray()) {
                if (!result.containsKey(c)) {
                    result.put(c, new HashSet<Character>());
                }
            }
        }
        for (int i = 0; i < words.length - 1; i++) {
            String curr = words[i];
            String next = words[i + 1];
            for (int j = 0; j < Math.min(curr.length(), next.length()); j++) {
                if (curr.charAt(j) != next.charAt(j)) {
                    result.get(curr.charAt(j)).add(next.charAt(j));
                    // we need to break here, since we already find the 1st different char, which can used to sort dictionary, later different char cannot
                    break;
                }
            }
        }
        return result;
    }
}

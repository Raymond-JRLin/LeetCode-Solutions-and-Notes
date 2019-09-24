// 1202. Smallest String With Swaps
// User Accepted: 891
// User Tried: 1680
// Total Accepted: 901
// Total Submissions: 3475
// Difficulty: Medium
// You are given a string s, and an array of pairs of indices in the string pairs where pairs[i] = [a, b] indicates 2 indices(0-indexed) of the string.
//
// You can swap the characters at any pair of indices in the given pairs any number of times.
//
// Return the lexicographically smallest string that s can be changed to after using the swaps.
//
//
//
// Example 1:
//
// Input: s = "dcab", pairs = [[0,3],[1,2]]
// Output: "bacd"
// Explaination:
// Swap s[0] and s[3], s = "bcad"
// Swap s[1] and s[2], s = "bacd"
// Example 2:
//
// Input: s = "dcab", pairs = [[0,3],[1,2],[0,2]]
// Output: "abcd"
// Explaination:
// Swap s[0] and s[3], s = "bcad"
// Swap s[0] and s[2], s = "acbd"
// Swap s[1] and s[2], s = "abcd"
// Example 3:
//
// Input: s = "cba", pairs = [[0,1],[1,2]]
// Output: "abc"
// Explaination:
// Swap s[0] and s[1], s = "bca"
// Swap s[1] and s[2], s = "bac"
// Swap s[0] and s[1], s = "abc"
//
//
//
// Constraints:
//
// 1 <= s.length <= 10^5
// 0 <= pairs.length <= 10^5
// 0 <= pairs[i][0], pairs[i][1] < s.length
// s only contains lower case English letters.


class Solution {
    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        if (pairs == null || pairs.size() == 0) {
            return s;
        }

        // return mytry(s, pairs);

        // return method1(s, pairs);

        return method2(s, pairs);
    }

    private String method2(String s, List<List<Integer>> pairs) {
        // Union Find
        // ref: https://leetcode.com/problems/smallest-string-with-swaps/discuss/387534/Union-Find-Java-Sol
        int n = s.length();
        UnionFind uf = new UnionFind(n);
        for (List<Integer> pair : pairs) {
            uf.union(pair.get(0), pair.get(1));
        }
        Map<Integer, PriorityQueue<Character>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int root = uf.find(i);
            PriorityQueue<Character> pq = map.computeIfAbsent(root, (dummy) -> new PriorityQueue<>());
            pq.offer(s.charAt(i));
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            int root = uf.find(i);
            PriorityQueue<Character> pq = map.get(root); // 找到 i 所属的连通块
            sb.append(pq.poll());
        }
        return sb.toString();
    }
    private class UnionFind {
        int[] parent;
        private int count;
        UnionFind(int n) {
            this.parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
            this.count = n;
        }
        void union(int a, int b) {
            int rootA = find(a);
            int rootB = find(b);
            if (rootA != rootB) {
                parent[rootA] = rootB;
                count--;
            }
        }
        int find(int num) {
            while (parent[num] != num) {
                parent[num] = parent[parent[num]];
                num = parent[num];
            }
            return num;
        }
    }

    private String method1(String s, List<List<Integer>> pairs) {
        // ref: https://leetcode.com/problems/smallest-string-with-swaps/discuss/387618/ChineseC++-1202.
        // create dajacent list
        Map<Integer, List<Integer>> adj = new HashMap<>();
        for (List<Integer> pair : pairs) {
            int num1 = pair.get(0);
            int num2 = pair.get(1);
            List<Integer> list1 = adj.getOrDefault(num1, new ArrayList<>());
            List<Integer> list2 = adj.getOrDefault(num2, new ArrayList<>());
            list1.add(num2);
            list2.add(num1);
            adj.put(num1, list1);
            adj.put(num2, list2);
        }
        int n = s.length();
        char[] result = s.toCharArray();
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            // it's already in previous connected components
            if (visited[i]) {
                continue;
            }
            // it's just itself in its connected component, which means i cannot be changed with other indices
            if (!adj.containsKey(i)) {
                continue;
            }
            List<Integer> list = new ArrayList<>(); // record index
            dfs(adj, list, visited, i); // DFS to get all indices in single connected component
            char[] arr = new char[list.size()];
            for (int j = 0; j < list.size(); j++) {
                arr[j] = s.charAt(list.get(j));
            }
            Arrays.sort(arr);
            Collections.sort(list); // lexicographically smallest, 把排序好的 arr 按照 index 从小到大的顺序
            // list is sorted as lexicographically on those indices
            for (int j = 0; j < list.size(); j++) {
                result[list.get(j)] = arr[j];
            }
        }
        return String.valueOf(result);
    }
    private void dfs(Map<Integer, List<Integer>> adj, List<Integer> list, boolean[] visited, int curr) {
        if (visited[curr]) {
            return;
        }
        visited[curr] = true;
        list.add(curr);
        for (int next : adj.get(curr)) {
            dfs(adj, list, visited, next);
        }
    }

    private String mytry(String s, List<List<Integer>> pairs) {
        // TLE
        // 通过 set 判断是否这个 string 已经走过来剪枝还是不够， 因为也很显然当可以交换的 pair 多的话， 那么排列组合的数目是相当大的
        // contest 的时候就在想如何优化/剪枝， 一直想的思路是： 比如说有 [1, 3], [1, 5], [3, 5]， 然后找他们之间变换的关系， 这样可以找到他们变换的路径， 结果没有找到譬如 [1, 3] + [3, 5] = [1, 5] 之类的， 因为换过了之后字符就变了
        // 方向错了， 但是 idea 是对的， 就是去找变换路径
        // 正确的方向是： 有 [1, 3], [1, 5], [3, 5]， 那么 1, 3, 5 之间可以任意变换； 并且和 2, 4 之间的变换是互相独立的
        // 那么这样我们就可以得到解法的思路： 把 pair 变成 adjacent list, 那么我们就有了不同的连通图， 在每个连通图内部找最 lexicographically smallest string， 每个结果加在一起就是最终的结果
        String[] result = new String[1];
        result[0] = s;
        Set<String> set = new HashSet<>();
        dfs(s, pairs, result, set);
        return result[0];
    }
    private void dfs(String curr, List<List<Integer>> pairs, String[] result, Set<String> set) {
        if (curr.compareTo(result[0]) < 0) {
            result[0] = curr;
        }
        if (set.contains(curr)) {
            return;
        }
        set.add(curr);
        for (List<Integer> list : pairs) {
            if (list.get(0) == list.get(1)) {
                continue;
            }
            dfs(swap(curr, list), pairs, result, set);
        }
    }
    private String swap(String s, List<Integer> list) {
        char[] arr = s.toCharArray();
        char temp = arr[list.get(0)];
        arr[list.get(0)] = arr[list.get(1)];
        arr[list.get(1)] = temp;
        return String.valueOf(arr);
    }
}

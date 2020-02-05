class Solution {
    public int makeConnected(int n, int[][] connections) {

        // return mytry(n, connections);

        return method2(n, connections);
    }

    private int method2(int n, int[][] connections) {
        if (connections.length < n - 1) {
            return -1;
        }

        UnionFind uf = new UnionFind(n);
        for (int[] con : connections) {
            uf.union(con[0], con[1]);
        }
        return uf.count - 1;
    }
    private class UnionFind {
        int[] nums;
        int count;

        UnionFind(int n) {
            this.count = n;
            this.nums = new int[n];
            for (int i = 0; i < n; i++) {
                nums[i] = i;
            }
        }

        int find(int i) {
            while (i != nums[i]) {
                nums[i] = nums[nums[i]];
                i = nums[i];
            }
            return nums[i];
        }

        void union(int a, int b) {
            int rootA = find(a);
            int rootB = find(b);
            if (rootA != rootB) {
                nums[rootA] = rootB;
                this.count --;
            }
        }
    }

    private int mytry(int n, int[][] connections) {
        if (connections.length < n - 1) {
            return -1;
        }

        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] connection : connections) {
            adj.get(connection[0]).add(connection[1]);
            adj.get(connection[1]).add(connection[0]);
        }

        Set<Integer> set = new HashSet<>();
        int result = 0;
        for (int i = 0; i < n; i++) {
            if (set.contains(i)) {
                continue;
            }
            result++;
            bfs(i, adj, set);
        }
        return result - 1;
    }
    private void bfs(int root, List<List<Integer>> adj, Set<Integer> set) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int curr = queue.poll();
                for (int next : adj.get(curr)) {
                    if (set.contains(next)) {
                        continue;
                    }
                    queue.offer(next);
                    set.add(next);
                }
            }
        }
    }
}

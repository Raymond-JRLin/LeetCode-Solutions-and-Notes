// 399. Evaluate Division
// DescriptionHintsSubmissionsDiscussSolution
// Equations are given in the format A / B = k, where A and B are variables represented as strings, and k is a real number (floating point number). Given some queries, return the answers. If the answer does not exist, return -1.0.
//
// Example:
// Given a / b = 2.0, b / c = 3.0.
// queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? .
// return [6.0, 0.5, -1.0, 1.0, -1.0 ].
//
// The input is: vector<pair<string, string>> equations, vector<double>& values, vector<pair<string, string>> queries , where equations.size() == values.size(), and the values are positive. This represents the equations. Return vector<double>.
//
// According to the example above:
//
// equations = [ ["a", "b"], ["b", "c"] ],
// values = [2.0, 3.0],
// queries = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ].
// The input is always valid. You may assume that evaluating the queries will result in no division by zero and there is no contradiction.


class Solution {
    public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
        if (equations == null || equations.length == 0 || queries == null || queries.length == 0) {
            return new double[0];
        }

        // return method1(equations, values, queries);

        return method2(equations, values, queries);
    }

    private double[] method2(String[][] equations, double[] values, String[][] queries) {
        // Union Find, 但没有完全懂这个做法， union find 中的 method 比较特殊， 和通常的写法不同， 而且和顺序有关， 应该是题意要求如此
        // ref: https://leetcode.com/problems/evaluate-division/discuss/88287/Esay-understand-Java-solution-3ms
        int n = 0;
        // mapping string to index
        Map<String, Integer> indexes = new HashMap<>();
        for (String[] e : equations) {
            for (String i : e) {
                if (!indexes.containsKey(i)) {
                    indexes.put(i, n++);
                }
            }
        }
        UnionFind uf = new UnionFind(n);
        for (int i = 0; i < equations.length; i++) {
            int from = indexes.get(equations[i][0]);
            int to = indexes.get(equations[i][1]);

            // int headA = uf.find(from);
            // int headB = uf.find(to);
            // uf.nums[headB] = headA;
            // uf.values[headB] = uf.values[from] * values[i] / uf.values[to];
            // uf.count--;

            uf.connect(from, to, values[i]); // connect known equations
        }

        double[] result = new double[queries.length];
        for (int i = 0; i < queries.length; i++) {
            if (!indexes.containsKey(queries[i][0]) || !indexes.containsKey(queries[i][1])) {
                result[i] = -1.0;
                continue;
            }
            int from = indexes.get(queries[i][0]);
            int to = indexes.get(queries[i][1]);
            if (uf.find(from) != uf.find(to)) {
                result[i] = -1.0D;
            } else {
                result[i] = uf.values[to] / uf.values[from];
            }
        }
        return result;
    }
    private class UnionFind {
        private int[] nums;
        private double[] values; // 多一个 value 来记录， 但是我也不是很明白 values 记的是啥
        private int count;

        public UnionFind(int n) {
            nums = new int[n];
            values = new double[n];
            count = n;
            for (int i = 0; i < n; i++) {
                nums[i] = i;
                values[i] = 1.0D;
            }
        }

        public int find(int i) {
            // different from usual union find's find()
            int temp = i;
            while (temp != nums[temp]) {
                // nums[i] = nums[nums[i]]; // cannot use this
                temp = nums[temp];
                values[i] *= values[temp];
            }
            nums[i] = temp; // set parent index
            return temp;
        }

        public void connect(int a, int b, double val) {
            int headA = find(a);
            int headB = find(b);
            if (headA != headB) {
                nums[headB] = headA;
                values[headB] = values[a] * val / values[b];
                count--;
            }
        }
    }

    private double[] method1(String[][] equations, double[] values, String[][] queries) {
        // 当作 weighted graph
        // ref: https://leetcode.com/problems/evaluate-division/discuss/88287/Esay-understand-Java-solution-3ms
        int n = 0;
        // mapping string to index
        Map<String, Integer> indexes = new HashMap<>();
        for (String[] e : equations) {
            for (String i : e) {
                if (!indexes.containsKey(i)) {
                    indexes.put(i, n++);
                }
            }
        }
        // weights[i][j] = the wights from i to j
        double[][] weights = new double[n][n];
        // initialization
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    weights[i][j] = 1.0;
                } else {
                    weights[i][j] = -1.0;
                }
            }
        }
        // get weights of known nodes from equations
        for (int i = 0; i < equations.length; i++) {
            int from = indexes.get(equations[i][0]);
            int to = indexes.get(equations[i][1]);
            weights[from][to] = values[i];
            weights[to][from] = 1.0 / values[i];
        }
        // Floyd-Warshall like algorithm
        // to calculate all possible path's weights
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    if (weights[i][j] != -1.0 && weights[j][k] != -1.0) {
                        // cannot pass through j
                        weights[i][k] = weights[i][j] * weights[j][k];
                    }
                }
            }
        }

        double[] result = new double[queries.length];
        for (int i = 0; i < queries.length; i++) {
            if (!indexes.containsKey(queries[i][0]) || !indexes.containsKey(queries[i][1])) {
                result[i] = -1.0;
            } else {
                int from = indexes.get(queries[i][0]);
                int to = indexes.get(queries[i][1]);
                result[i] = weights[from][to];
            }
        }
        return result;
    }
}

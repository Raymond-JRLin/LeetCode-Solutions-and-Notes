// 399. Evaluate Division
// DescriptionHintsSubmissionsDiscussSolution
//
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
//
//
//
// The input is always valid. You may assume that evaluating the queries will result in no division by zero and there is no contradiction.
// Seen this question in a real interview before?
//
//     Difficulty:Medium


class Solution {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        if (equations == null || equations.size() == 0) {
            return new double[0];
        }

        // return mytry(equations, values, queries);

        // return method2(equations, values, queries);

        return method3(equations, values, queries);
    }

    private double[] method3(List<List<String>> equations, double[] values, List<List<String>> queries) {
        // union find
        Map<String, String> root = new HashMap<>(); // <key, root> with path compression
        Map<String, Double> dist = new HashMap<>(); // <key, dist from key to root>
        for (int i = 0; i < equations.size(); i++) {
            String r1 = find(root, dist, equations.get(i).get(0));
            String r2 = find(root, dist, equations.get(i).get(1));
            root.put(r1, r2);
            // e[i][0] = dist.get(e[i][0]) * r1
            // e[i][1] = dist.get(e[i][1]) * r2
            // e[i][0] / e[i][1] = values[i] = dist.get(e[i][0]) * r1 / (dist.get(e[i][1]) * r2)
            // => r1 / r2= dist.get(e[i][1]) * values[i] / dist.get(e[i][0])
            dist.put(r1, dist.get(equations.get(i).get(1)) * values[i] / dist.get(equations.get(i).get(0)));
            // System.out.println("r1, r2: " + r1 + " -> " + r2 + ". dist: r1 -> " + dist.get(r1));
        }

        double[] result = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            if (!root.containsKey(queries.get(i).get(0)) || !root.containsKey(queries.get(i).get(1))) {
                result[i] = -1.0;
                continue;
            }
            String r1 = find(root, dist, queries.get(i).get(0));
            String r2 = find(root, dist, queries.get(i).get(1));
            if (!r1.equals(r2)) {
                result[i] = -1.0;
                continue;
            }
            result[i] = (double) dist.get(queries.get(i).get(0)) / dist.get(queries.get(i).get(1));
            // System.out.println("r1, r2: " + r1 + ", " + r2 + ". dist: " + dist.get(queries.get(i).get(0)) + " / " + dist.get(queries.get(i).get(1)));
        }

        // for (String key : root.keySet()) {
        //     System.out.println(key + " -> " + root.get(key));
        // }
        // for (String key : dist.keySet()) {
        //     System.out.println(key + " : " + dist.get(key));
        // }
        return result;
    }
    private String find(Map<String, String> root, Map<String, Double> dist, String s) {
        if (!root.containsKey(s)) {
            root.put(s, s);
            dist.put(s, 1.0);
            return s;
        }
        if (root.get(s).equals(s)) {
            return s;
        }
        String lastP = root.get(s);
        String p = find(root, dist, lastP);
        root.put(s, p);
        dist.put(s, dist.get(s) * dist.get(lastP));
        return p;
    }

    private double[] method2(List<List<String>> equations, double[] values, List<List<String>> queries) {
        int n = equations.size();
        // indexing
        int len = 0;
        Map<String, Integer> mapping = new HashMap<>();
        for (List<String> list : equations) {
            for (String s : list) {
                if (!mapping.containsKey(s)) {
                    mapping.put(s, len);
                    len++;
                }
            }
        }
        // adjacent list
        double[][] adj = new double[len][len]; // [a, b] == value, where a / b = value
        // init
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (i == j) {
                    adj[i][j] = 1.0;
                } else {
                    adj[i][j] = -1.0;
                }
            }
        }
        // get the numbers from equations
        for (int i = 0; i < n; i++) {
            List<String> list = equations.get(i);
            int a = mapping.get(list.get(0));
            int b = mapping.get(list.get(1));

            adj[a][b] = values[i];
            adj[b][a] = 1.0 / values[i];
        }
        // floyd
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                for (int k = 0; k < len; k++) {
                    if (adj[i][k] != -1.0 && adj[k][j] != -1.0) {
                        adj[i][j] = adj[i][k] * adj[k][j];
                    }
                }
            }
        }
        // check result
        int m = queries.size();
        double[] result = new double[m];
        for (int i = 0; i < m; i++) {
            List<String> list = queries.get(i);
            String from = list.get(0);
            String to = list.get(1);
            if (!mapping.containsKey(from) || !mapping.containsKey(to)) {
                result[i] = -1.0;
                continue;
            }
            int a = mapping.get(from);
            int b = mapping.get(to);
            result[i] = adj[a][b];
        }
        return result;
    }

    private double[] mytry(List<List<String>> equations, double[] values, List<List<String>> queries) {
        int n = equations.size();
        // adjacent list
        Map<String, Map<String, Double>> adj = new HashMap<>(); // <a, <b, value>>, where a / b = value
        for (int i = 0; i < n; i++) {
            List<String> list = equations.get(i);
            Map<String, Double> map1 = adj.computeIfAbsent(list.get(0), dummy -> (new HashMap<>()));
            map1.put(list.get(1), values[i]);
            Map<String, Double> map2 = adj.computeIfAbsent(list.get(1), dummy -> (new HashMap<>()));
            map2.put(list.get(0), 1.0 / values[i]);
        }

        int m = queries.size();
        double[] result = new double[m];
        for (int i = 0; i < m; i++) {
            List<String> list = queries.get(i);
            result[i] = dfs(adj, list.get(0), list.get(1), 1.0, new HashSet<String>());
        }

        return result;
    }
    private double dfs(Map<String, Map<String, Double>> adj, String from, String to, double result,
                       Set<String> visited) {
        if (visited.contains(from)) {
            return -1.0;
        }
        if (!adj.containsKey(from)) {
            return -1.0;
        }
        if (from.equals(to)) {
            return result;
        }
        visited.add(from);
        Map<String, Double> map = adj.get(from);
        double num = -1.0;
        for (String next : map.keySet()) {
            num = dfs(adj, next, to, result * map.get(next), visited);
            if (num != -1.0) {
                break;
            }
        }
        visited.remove(from);
        return num;
    }
}

// 963. Minimum Area Rectangle II
// User Accepted: 299
// User Tried: 509
// Total Accepted: 304
// Total Submissions: 1244
// Difficulty: Medium
// Given a set of points in the xy-plane, determine the minimum area of any rectangle formed from these points, with sides not necessarily parallel to the x and y axes.
//
// If there isn't any rectangle, return 0.
//
//
//
// Example 1:
//
//
//
// Input: [[1,2],[2,1],[1,0],[0,1]]
// Output: 2.00000
// Explanation: The minimum area rectangle occurs at [1,2],[2,1],[1,0],[0,1], with an area of 2.
// Example 2:
//
//
//
// Input: [[0,1],[2,1],[1,1],[1,0],[2,0]]
// Output: 1.00000
// Explanation: The minimum area rectangle occurs at [1,0],[1,1],[2,1],[2,0], with an area of 1.
// Example 3:
//
//
//
// Input: [[0,3],[1,2],[3,1],[1,3],[2,1]]
// Output: 0
// Explanation: There is no possible rectangle to form from these points.
// Example 4:
//
//
//
// Input: [[3,1],[1,1],[0,1],[2,1],[3,3],[3,2],[0,2],[2,3]]
// Output: 2.00000
// Explanation: The minimum area rectangle occurs at [2,1],[2,3],[3,3],[3,1], with an area of 2.
//
//
// Note:
//
// 1 <= points.length <= 50
// 0 <= points[i][0] <= 40000
// 0 <= points[i][1] <= 40000
// All points are distinct.
// Answers within 10^-5 of the actual value will be accepted as correct.


class Solution {
    public double minAreaFreeRect(int[][] points) {
        if (points == null || points.length < 4 || points[0].length < 2) {
            return 0D;
        }

        // return method1(points);

        return method2(points);
    }

    private double method2(int[][] points) {
        // ref: https://leetcode.com/problems/minimum-area-rectangle-ii/discuss/208361/JAVA-O(n2)-using-Map
        int n = points.length;
        double result = Double.MAX_VALUE;
        // 矩阵的对角线平分且相等
        Map<String, List<Line>> map = new HashMap<>(); // <对角线及中点， 对应的对角线>
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                Line line = new Line(points[i], points[j]);
                String name = line.toString();
                List<Line> lines = map.getOrDefault(name, new ArrayList<>());
                lines.add(line);
                map.put(name, lines);
            }
        }

        for (String key : map.keySet()) {
            if (map.get(key).size() < 2) {
                continue;
            }
            List<Line> lines = map.get(key);
            for (int i = 0; i < lines.size() - 1; i++) {
                for (int j = i + 1; j < lines.size(); j++) {
                    // line 的距离是对角线的， 要取一条对角线的一个点， 连另一条对角线的两个点， 得到矩阵的边
                    int[] p1 = lines.get(i).p1;
                    int[] p2 = lines.get(j).p1;
                    int[] p3 = lines.get(j).p2;
                    double area = Math.sqrt(getDist(p1, p2)) * Math.sqrt(getDist(p1, p3));
                    if (area == 0.0) {
                        continue;
                    }
                    result = Math.min(result, area);
                }
            }
        }
        return result == Double.MAX_VALUE ? 0 : result;
    }
    private class Line {
        private int[] p1;
        private int[] p2;
        private double[] center;
        private double dist;

        public Line(int[] p1, int[] p2) {
            this.p1 = p1;
            this.p2 = p2;
            center = this.getCenter();
            dist = this.getDist();
        }

        public double getDist() {
            return Math.sqrt((p1[0] - p2[0]) * (p1[0] - p2[0]) + (p1[1] - p2[1])  * (p1[1] - p2[1]));
        }

        public double[] getCenter() {
            double centerX = (double) (this.p1[0] + this.p2[0]) / 2.0;
            double centerY = (double) (this.p1[1] + this.p2[1]) / 2.0;
            return new double[]{centerX, centerY};
        }

        @Override
        public String toString() {
            return dist + "," + center[0] + "," + center[1];
        }
    }

    private double method1(int[][] points) {
        // O(N ^ 3) time and O(N) space
        // ref: https://leetcode.com/problems/minimum-area-rectangle-ii/discuss/208470/Java-O(N3)-bruteforce
        Set<Integer> set = new HashSet<>();
        for (int[] p : points) {
            set.add(p[0] * 40001 + p[1]);
        }

        int n = points.length;
        double result = Double.MAX_VALUE;
        for (int i = 0; i < n - 2; i++) {
            int[] p1 = points[i];
            for (int j = i + 1; j < n - 1; j++) {
                int[] p2 = points[j];
                int dist2 = getDist(p1, p2);
                for (int k = j + 1; k < n; k++) {
                    int[] p3 = points[k];
                    int dist3 = getDist(p1, p3);
                    int dist1 = getDist(p2, p3);
                    // 勾股定理
                    if (dist2 + dist3 != dist1) {
                        continue;
                    }
                    // 求第四个点的坐标, 此时 p2 p3 是对角线， p1 是共用点
                    int x4 = p2[0] + p3[0] - p1[0];
                    int y4 = p2[1] + p3[1] - p1[1];
                    if (!set.contains(x4 * 40001 + y4)) {
                        continue;
                    }
                    double area = Math.sqrt(dist2) * Math.sqrt(dist3);
                    if (area == 0.0) {
                        continue;
                    }
                    result = Math.min(result, area);
                }
            }
        }
        return result == Double.MAX_VALUE ? 0 : result;
    }
    private int getDist(int[] p1, int[] p2) {
        return (p1[0] - p2[0]) * (p1[0] - p2[0]) + (p1[1] - p2[1])  * (p1[1] - p2[1]);
    }
}

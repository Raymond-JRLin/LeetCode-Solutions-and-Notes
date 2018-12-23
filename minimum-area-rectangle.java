// 939. Minimum Area Rectangle
// DescriptionHintsSubmissionsDiscussSolution
// Given a set of points in the xy-plane, determine the minimum area of a rectangle formed from these points, with sides parallel to the x and y axes.
//
// If there isn't any rectangle, return 0.
//
//
//
// Example 1:
//
// Input: [[1,1],[1,3],[3,1],[3,3],[2,2]]
// Output: 4
// Example 2:
//
// Input: [[1,1],[1,3],[3,1],[3,3],[4,1],[4,3]]
// Output: 2
//
//
// Note:
//
// 1 <= points.length <= 500
// 0 <= points[i][0] <= 40000
// 0 <= points[i][1] <= 40000
// All points are distinct.


class Solution {
    public int minAreaRect(int[][] points) {
        if (points == null || points.length < 4 || points[0].length == 0) {
            return 0;
        }

        return method1(points);
    }

    private int method1(int[][] points) {
        // 查对角线： 任意不同的两个点， 让他们作为一条对角线， 查是否存在另一条对角线， 即两个点 x y 分别组成的点
        // O(N ^ 2) time and O(N) space
        int n = points.length;
        // map 也改用成一个 set， 对所有的点进行 hash
        Map<Integer, Set<Integer>> map = new HashMap<>(); // <x, set of y>
        for (int[] point : points) {
            Set<Integer> set = map.getOrDefault(point[0], new HashSet<>());
            set.add(point[1]);
            map.put(point[0], set);
        }
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                int[] p1 = points[i];
                int[] p2 = points[j];
                // 跳过平行或垂直的两个点
                if (p1[0] == p2[0] || p1[1] == p2[1]) {
                    continue;
                }
                if (map.get(p1[0]).contains(p2[1]) && map.get(p2[0]).contains(p1[1])) {
                    int area = Math.abs(p1[0] - p2[0]) * Math.abs(p1[1] - p2[1]);
                    result = Math.min(result, area);
                }
            }
        }
        return result == Integer.MAX_VALUE ? 0 : result;
    }
}

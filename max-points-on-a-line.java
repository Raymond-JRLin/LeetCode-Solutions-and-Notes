// 149. Max Points on a Line
// DescriptionHintsSubmissionsDiscussSolution
// Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.
//
// Example 1:
//
// Input: [[1,1],[2,2],[3,3]]
// Output: 3
// Explanation:
// ^
// |
// |        o
// |     o
// |  o
// +------------->
// 0  1  2  3  4
// Example 2:
//
// Input: [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
// Output: 4
// Explanation:
// ^
// |
// |  o
// |     o        o
// |        o
// |  o        o
// +------------------->
// 0  1  2  3  4  5  6
// NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.
//
// Seen this question in a real interview before?
// Difficulty:Hard


class Solution {
    public int maxPoints(int[][] points) {
        if (points == null || points.length == 0) {
            return 0;
        }

        return method1(points);
    }

    private int method1(int[][] points) {
        int n = points.length;
        int result = 0;
        for (int i = 0; i < n; i++) {
            Map<Integer, Map<Integer, Integer>> lines = new HashMap<>(); // <x, <y, # line>>
            int duplicate = 0;
            int max = 0;
            for (int j = i + 1; j < n; j++) {
                int dx = points[j][0] - points[i][0];
                int dy = points[j][1] - points[i][1];
                if (dx == 0 && dy == 0) {
                    duplicate++;
                    continue;
                }
                int gcd = getGcd(dx, dy);
                if (gcd != 0) {
                    dx /= gcd;
                    dy /= gcd;
                }
                if (lines.containsKey(dx)) {
                    lines.get(dx).put(dy, lines.get(dx).getOrDefault(dy, 0) + 1);
                } else {
                    Map<Integer, Integer> map = new HashMap<>();
                    map.put(dy, 1);
                    lines.put(dx, map);
                }
                max = Math.max(max, lines.get(dx).get(dy));
            }
            result = Math.max(result, max + duplicate + 1);
        }
        return result;
    }
    private int getGcd(int a, int b) {
        if (b == 0) {
            return a;
        } else {
            return getGcd(b, a % b);
        }
    }
}

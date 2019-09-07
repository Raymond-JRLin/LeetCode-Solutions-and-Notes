// 812. Largest Triangle Area
// DescriptionHintsSubmissionsDiscussSolution
// You have a list of points in the plane. Return the area of the largest triangle that can be formed by any 3 of the points.
//
// Example:
// Input: points = [[0,0],[0,1],[1,0],[0,2],[2,0]]
// Output: 2
// Explanation:
// The five points are show in the figure below. The red triangle is the largest.
//
//
// Notes:
//
// 3 <= points.length <= 50.
// No points will be duplicated.
//  -50 <= points[i][j] <= 50.
// Answers within 10^-6 of the true value will be accepted as correct.
//
//
// Seen this question in a real interview before?
// Difficulty:Easy


class Solution {
    public double largestTriangleArea(int[][] points) {
        if (points == null || points.length == 0 || points[0].length < 2) {
            return 0.0;
        }

        // return mytry(points);

        return method2(points);
    }

    private double method2(int[][] points) {
        int n = points.length;
        double result = 0.0;
        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                for (int k = j + 1; k < n; k++) {
                    result = Math.max(result, calArea(points[i], points[j], points[k]));
                }
            }
        }
        return result;
    }
    private double calArea(int[] p1, int[] p2, int[] p3) {
        return Math.abs((p2[0] - p1[0]) * (p3[1] - p1[1]) - (p3[0] - p1[0]) * (p2[1] - p1[1])) / 2.0;
    }

    private double mytry(int[][] points) {
        int n = points.length;
        double result = 0.0;
        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                int x1 = points[i][0];
                int y1 = points[i][1];
                int x2 = points[j][0];
                int y2 = points[j][1];
                double len = getDis(x1, y1, x2, y2);
                for (int k = j + 1; k < n; k++) {
                    int x = points[k][0];
                    int y = points[k][1];
                    double side1 = getDis(x1, y1, x, y);
                    double side2 = getDis(x2, y2, x, y);
                    double p = getMid(len, side1, side2);
                    double s = getArea(p, len, side1, side2);
                    if (s > result) {
                        result = s;
                    }
                }
            }
        }
        return result;
    }
    private double getDis(int x1, int y1, int x2, int y2) {
        return Math.sqrt((x2 -x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
    }
    private double getMid(double l1, double l2, double l3) {
        return (l1 + l2 + l3) / 2;
    }
    private double getArea(double p, double l1, double l2, double l3) {
        return Math.sqrt(p * (p - l1) * (p - l2) * (p - l3));
    }
}

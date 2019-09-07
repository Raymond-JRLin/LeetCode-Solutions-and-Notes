// 587. Erect the Fence
// DescriptionHintsSubmissionsDiscussSolution
// There are some trees, where each tree is represented by (x,y) coordinate in a two-dimensional garden. Your job is to fence the entire garden using the minimum length of rope as it is expensive. The garden is well fenced only if all the trees are enclosed. Your task is to help find the coordinates of trees which are exactly located on the fence perimeter.
//
//
//
// Example 1:
//
// Input: [[1,1],[2,2],[2,0],[2,4],[3,3],[4,2]]
// Output: [[1,1],[2,0],[4,2],[3,3],[2,4]]
// Explanation:
//
// Example 2:
//
// Input: [[1,2],[2,2],[4,2]]
// Output: [[1,2],[2,2],[4,2]]
// Explanation:
//
// Even you only have trees in a line, you need to use rope to enclose them.
//
//
// Note:
//
// All trees should be enclosed together. You cannot cut the rope to enclose trees that will separate them in more than one group.
// All input integers will range from 0 to 100.
// The garden has at least one tree.
// All coordinates are distinct.
// Input points have NO order. No order required for output.
// input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.


/**
 * Definition for a point.
 * class Point {
 *     int x;
 *     int y;
 *     Point() { x = 0; y = 0; }
 *     Point(int a, int b) { x = a; y = b; }
 * }
 */
class Solution {
    public List<Point> outerTrees(Point[] points) {
        if (points == null || points.length == 0) {
            return new ArrayList<Point>();
        }

        return mytry(points);
    }

    private List<Point> mytry(Point[] points) {
        // Jarvis March Algorithm (gift wripping)
        // O(N * E): N is # point and E is the final result - convex hull outbounding, worst case: all points are on the boards, then E == N => O(n ^ 2)
        int n = points.length;
        // find the leftmost point as starting point
        Point start = points[0];
        for (int i = 1; i < n; i++) {
            if (points[i].x < start.x) {
                start = points[i];
            }
        }
        // keep tracking the leftmost point to create edge
        Point curr = start;
        Set<Point> result = new HashSet<>(); // to avoid edge case: given multiple same points
        result.add(curr);
        List<Point> collinear = new ArrayList<>();
        while (true) {
            Point next = points[0];
            for (int i = 1; i < n; i++) {
                if (points[i] == curr) {
                    continue;
                }
                int val = crossProduct(curr, next, points[i]);
                if (val > 0) {
                    // find one more left point
                    next = points[i];
                    // clear collinear points
                    collinear = new ArrayList<>();
                } else if (val == 0) {
                    // collinear: pick the farthest point and add another into colinear list
                    int dis = distance(curr, next, points[i]);
                    if (dis < 0) {
                        collinear.add(next);
                        next = points[i];
                    } else {
                        collinear.add(points[i]);
                    }
                }
                // to the right: no need to do further
            }
            for (Point p : collinear) {
                result.add(p);
            }
            if (next == start) {
                // go back to the start, which means we formed an envelope
                break;
            }
            result.add(next);
            curr = next;
        }
        return new ArrayList<Point>(result);

    }
    private int crossProduct(Point a, Point b, Point c) {
        int y1 = a.y - b.y;
        int y2 = a.y - c.y;
        int x1 = a.x - b.x;
        int x2 = a.x - c.x;
        return (y2 * x1) - (y1 * x2);
        // == 0: collinear
        // > 0: c is in the left of line ab;
        // < 0: c is in the right of line ab;
    }
    /** Returns < 0 if 'b' is closer to 'a' compared to 'c',
     *  == 0 if 'b' and 'c' are same distance from 'a'
     *  or > 0 if 'c' is closer to 'a' compared to 'b'.
     **/
    private int distance(Point a, Point b, Point c) {
        int y1 = a.y - b.y;
        int y2 = a.y - c.y;
        int x1 = a.x - b.x;
        int x2 = a.x - c.x;
        return Integer.compare(y1 * y1 + x1 * x1, y2 * y2 + x2 * x2);
    }
}

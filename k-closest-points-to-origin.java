// 973. K Closest Points to Origin
// DescriptionHintsSubmissionsDiscussSolution
// We have a list of points on the plane.  Find the K closest points to the origin (0, 0).
//
// (Here, the distance between two points on a plane is the Euclidean distance.)
//
// You may return the answer in any order.  The answer is guaranteed to be unique (except for the order that it is in.)
//
//
//
// Example 1:
//
// Input: points = [[1,3],[-2,2]], K = 1
// Output: [[-2,2]]
// Explanation:
// The distance between (1, 3) and the origin is sqrt(10).
// The distance between (-2, 2) and the origin is sqrt(8).
// Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
// We only want the closest K = 1 points from the origin, so the answer is just [[-2,2]].
// Example 2:
//
// Input: points = [[3,3],[5,-1],[-2,4]], K = 2
// Output: [[3,3],[-2,4]]
// (The answer [[-2,4],[3,3]] would also be accepted.)
//
//
// Note:
//
// 1 <= K <= points.length <= 10000
// -10000 < points[i][0] < 10000
// -10000 < points[i][1] < 10000
// Seen this question in a real interview before?
// Difficulty:Medium


class Solution {
    public int[][] kClosest(int[][] points, int K) {
        if (points == null || points.length == 0 || points[0].length == 0) {
            return new int[0][0];
        }

        // return mytry(points, K);

        return method2(points, K);
    }

    private int[][] method2(int[][] points, int k) {
        // we can sort in original array
        Arrays.sort(points, (o1, o2) -> Integer.compare(o1[0] * o1[0] + o1[1] * o1[1], o2[0] * o2[0] + o2[1] * o2[1]));
        return Arrays.copyOfRange(points, 0, k);
    }

    private int[][] mytry(int[][] points, int k) {
        PriorityQueue<Point> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o2.dist, o1.dist));
        for (int[] point : points) {
            pq.offer(new Point(point));
            if (pq.size() > k) {
                pq.poll();
            }
        }
        int[][] result = new int[k][2];
        while (k > 0) {
            result[k - 1] = pq.poll().coor;
            k--;
        }
        return result;
    }
    private static final int[] ori = new int[]{0, 0};
    private class Point {
        private int[] coor;
        private int dist;
        public Point(int[] coor) {
            this.coor = coor;
            dist = getDist();
        }

        public int getDist() {
            return (coor[0] - ori[0]) * (coor[0] - ori[0]) + (coor[1] - ori[1]) * (coor[1] - ori[1]);
        }
    }
}

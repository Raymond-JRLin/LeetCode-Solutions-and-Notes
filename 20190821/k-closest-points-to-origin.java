// 973. K Closest Points to Origin
// DescriptionHintsSubmissionsDiscussSolution
//
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
//
// Example 2:
//
// Input: points = [[3,3],[5,-1],[-2,4]], K = 2
// Output: [[3,3],[-2,4]]
// (The answer [[-2,4],[3,3]] would also be accepted.)
//
//
//
// Note:
//
//     1 <= K <= points.length <= 10000
//     -10000 < points[i][0] < 10000
//     -10000 < points[i][1] < 10000
//
// Seen this question in a real interview before?
//
//     Difficulty:Medium


class Solution {
    public int[][] kClosest(int[][] points, int K) {

        // return mytry(points, K);

        return method2(points, K);
    }

    private int[][] method2(int[][] points, int k) {
        // quickselect: O(n) time (average) / O(n ^ 2) time (worst), O(1) space
        int n = points.length;
        int start = 0;
        int end = n - 1;
        while (start < end) {
            int pivot = partition(points, start, end); // pivot is index
            if (pivot < k) {
                start = pivot + 1;
            } else if (pivot > k) {
                end = pivot - 1;
            } else {
                return Arrays.copyOfRange(points, 0, pivot);
            }
        }
        return Arrays.copyOfRange(points, 0, start);
    }
    private int partition(int[][] points, int start, int end) {
        // 3 way partition
        int pivot = start; // pivot is index
        while (start <= end) {
            while (start <= end && compare(points[start], points[pivot]) <= 0) {
                start++;
            }
            while (start <= end && compare(points[end], points[pivot]) >= 0) {
                end--;
            }
            if (start > end) {
                break;
            }
            swap(points, start, end);
        }
        swap(points, pivot, end);
        return end; // already swapped pivot and end, so return end index
    }
    private int compare(int[] o1, int[] o2) {
        return Integer.compare(o1[0] * o1[0] + o1[1] * o1[1], o2[0] * o2[0] + o2[1] * o2[1]);
    }
    private void swap(int[][] points, int i, int j) {
        int[] temp = points[i];
        points[i] = points[j];
        points[j] = temp;
    }

    private int[][] mytry(int[][] points, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> (Integer.compare(o2[0] * o2[0] + o2[1] * o2[1], o1[0] * o1[0] + o1[1] * o1[1])));
        for (int[] p : points) {
            pq.offer(p);
            if (pq.size() > k) {
                pq.poll();
            }
        }
        int[][] result = new int[k][2];
        int i = k - 1;
        while (!pq.isEmpty()) {
            result[i--] = pq.poll();
        }
        return result;
    }
}

// 378. Kth Smallest Element in a Sorted Matrix
// DescriptionHintsSubmissionsDiscussSolution
// Given a n x n matrix where each of the rows and columns are sorted in ascending order, find the kth smallest element in the matrix.
//
// Note that it is the kth smallest element in the sorted order, not the kth distinct element.
//
// Example:
//
// matrix = [
//    [ 1,  5,  9],
//    [10, 11, 13],
//    [12, 13, 15]
// ],
// k = 8,
//
// return 13.
// Note:
// You may assume k is always valid, 1 ≤ k ≤ n2.


class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        // we can use a queue to record all numbers and get the k-th smallest, O(n * m) time and space
        // get better: we can offer like BFS diagonal layer into queue, mark them whether they're already offered by a visited array
        // get better: (from discussion) we can add the 1st row and poll current peek and offer k - 1 times the number just below the peek value in queue
        // return method1_queue(matrix, k);

        // binary search: search range - from discussion, but still have some confusions
        return method2_bs(matrix, k);
    }
    private int method1_queue(int[][] matrix, int k) {
        int n = matrix.length; // matrix is n * n
        PriorityQueue<Pair> pq = new PriorityQueue<>(k, new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                return o1.val - o2.val; // min heap
            }
        });
        for (int j = 0; j < n; j++) {
            pq.offer(new Pair(0, j, matrix[0][j]));
        }
        for (int i = 0; i < k - 1; i++) {
            // if we want the 1st smallest, then we don't need to do following since we already have it in the peek, so the range should be 0 ~ k - 1
            Pair curr = pq.poll();
            if (curr.row < n - 1) {
                pq.offer(new Pair(curr.row + 1, curr.col, matrix[curr.row + 1][curr.col]));
            }
        }
        return pq.peek().val;
    }

    private int method2_bs(int[][] matrix, int k) {
        int n = matrix.length;
        int start = matrix[0][0];
        int end = matrix[n - 1][n - 1] + 1;
        while (start < end) {
            int count = 0; // 定义在 while 里面， 每一次循环 - 新的查找， 都要重新计算有多少个数小于等于 mid
            int mid = start + (end - start) / 2;
            int j = n - 1;
            for (int i = 0; i < n; i++) {
                // loop 每一行， 计算一共有多少个数 <= mid
                while (j >= 0 && matrix[i][j] > mid) {
                    j--;
                }
                count += (j + 1);
            }
            // 不断逼近答案所在的区间
            if (count < k) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        return start;
    }
}
class Pair {
    public int val;
    public int row;
    public int col;
    public Pair(int r, int c, int v) {
        row = r;
        col = c;
        val = v;
    }
}

// 986. Interval List Intersections
// DescriptionHintsSubmissionsDiscussSolution
//
// Given two lists of closed intervals, each list of intervals is pairwise disjoint and in sorted order.
//
// Return the intersection of these two interval lists.
//
// (Formally, a closed interval [a, b] (with a <= b) denotes the set of real numbers x with a <= x <= b.  The intersection of two closed intervals is a set of real numbers that is either empty, or can be represented as a closed interval.  For example, the intersection of [1, 3] and [2, 4] is [2, 3].)
//
//
//
// Example 1:
//
// Input: A = [[0,2],[5,10],[13,23],[24,25]], B = [[1,5],[8,12],[15,24],[25,26]]
// Output: [[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
// Reminder: The inputs and the desired output are lists of Interval objects, and not arrays or lists.
//
//
//
// Note:
//
//     0 <= A.length < 1000
//     0 <= B.length < 1000
//     0 <= A[i].start, A[i].end, B[i].start, B[i].end < 10^9
//
// NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.
// Seen this question in a real interview before?
//
//     Difficulty:Medium


class Solution {
    public int[][] intervalIntersection(int[][] A, int[][] B) {

        return mytry(A, B);
    }

    private int[][] mytry(int[][] A, int[][] B) {
        // 我自己写的时候也不知道为什么要这样， 也不确定是否是对的， 但 AC 了
        // 仔细想一想， 其实类似于 merge sort using 2 pointers
        List<int[]> list = new ArrayList<>();
        int n = A.length;
        int m = B.length;
        int i = 0, j = 0;
        while (i < n && j < m) {
            int[] num1 = A[i];
            int[] num2 = B[j];
            if (num2[0] <= num1[1] && num1[0] <= num2[1]) {
                int[] overlap = new int[]{Math.max(num1[0], num2[0]), Math.min(num1[1], num2[1])};
                list.add(overlap);
            }
            if (num1[1] >= num2[1]) {
                j++;
            } else {
                i++;
            }
        }
        int[][] result = new int[list.size()][2];
        for (int k = 0; k < list.size(); k++) {
            result[k] = list.get(k);
        }
        return result;
    }
}

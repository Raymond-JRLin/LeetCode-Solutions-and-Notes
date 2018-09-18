// 870. Advantage Shuffle
// User Accepted: 925
// User Tried: 1365
// Total Accepted: 939
// Total Submissions: 2872
// Difficulty: Medium
// Given two arrays A and B of equal size, the advantage of A with respect to B is the number of indices i for which A[i] > B[i].
//
// Return any permutation of A that maximizes its advantage with respect to B.
//
//
//
// Example 1:
//
// Input: A = [2,7,11,15], B = [1,10,4,11]
// Output: [2,11,7,15]
// Example 2:
//
// Input: A = [12,24,8,32], B = [13,25,32,11]
// Output: [24,32,8,12]
//
//
// Note:
//
// 1 <= A.length = B.length <= 10000
// 0 <= A[i] <= 10^9
// 0 <= B[i] <= 10^9


class Solution {
    public int[] advantageCount(int[] A, int[] B) {
        if (A == null || A.length == 0) {
            return new int[0];
        }

        return mytry(A, B);
    }

    private int[] mytry(int[] A, int[] B) {
        // we always put the least bigger on the target number, and if there's no/or use up least bigger, put smallest number
        // O(NlogN) time and O(N) space
        int n = A.length;

        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o2[0], o1[0]); // descending on values
            }
        });
        for (int i = 0; i < n; i++) {
            pq.offer(new int[]{B[i], i});
        }

        Arrays.sort(A);

        int[] result = new int[n];
        int start = 0;
        int end = n - 1;
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int val = curr[0];
            int i = curr[1];
            if (A[end] > val) {
                // current largest number of A is larger than current largest number of B, use it
                result[i] = A[end];
                end--;
            } else {
                // otherwise, use current smallest number of A
                result[i] = A[start];
                start++;
            }
        }
        return result;
    }
}

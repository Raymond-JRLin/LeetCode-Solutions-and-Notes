// 760. Find Anagram Mappings
// DescriptionHintsSubmissionsDiscussSolution
// Given two lists Aand B, and B is an anagram of A. B is an anagram of A means B is made by randomizing the order of the elements in A.
//
// We want to find an index mapping P, from A to B. A mapping P[i] = j means the ith element in A appears in B at index j.
//
// These lists A and B may contain duplicates. If there are multiple answers, output any of them.
//
// For example, given
//
// A = [12, 28, 46, 32, 50]
// B = [50, 12, 32, 46, 28]
// We should return
// [1, 4, 3, 2, 0]
// as P[0] = 1 because the 0th element of A appears at B[1], and P[1] = 4 because the 1st element of A appears at B[4], and so on.
// Note:
//
// A, B have equal lengths in range [1, 100].
// A[i], B[i] are integers in range [0, 10^5].


class Solution {
    public int[] anagramMappings(int[] A, int[] B) {
        if (A == null || A.length == 0) {
            return new int[0];
        }

        // return mytry(A, B);

        return method2(A, B);
    }

    private int[] method2(int[] ori, int[] tar) {
        int n = ori.length;
        Map<Integer, Integer> map = new HashMap<>(); // <value in tar, corresponding index>
        for (int i = 0; i < n; i++) {
            map.put(tar[i], i);
        }
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            result[i] = map.get(ori[i]);
        }
        return result;
    }

    private int[] mytry(int[] ori, int[] tar) {
        // O(N ^ 2) time
        int n = ori.length;
        int[] result = new int[n];
        int index = 0;
        for (int num : ori) {
            for (int i = 0; i < n; i++) {
                if (tar[i] == num) {
                    result[index++] = i;
                    break;
                }
            }
        }
        return result;
    }
}

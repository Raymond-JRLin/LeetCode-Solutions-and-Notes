// 1007. Minimum Domino Rotations For Equal Row
// DescriptionHintsSubmissionsDiscussSolution
//
// In a row of dominoes, A[i] and B[i] represent the top and bottom halves of the i-th domino.  (A domino is a tile with two numbers from 1 to 6 - one on each half of the tile.)
//
// We may rotate the i-th domino, so that A[i] and B[i] swap values.
//
// Return the minimum number of rotations so that all the values in A are the same, or all the values in B are the same.
//
// If it cannot be done, return -1.
//
//
//
// Example 1:
//
// Input: A = [2,1,2,4,2,2], B = [5,2,6,2,3,2]
// Output: 2
// Explanation:
// The first figure represents the dominoes as given by A and B: before we do any rotations.
// If we rotate the second and fourth dominoes, we can make every value in the top row equal to 2, as indicated by the second figure.
//
// Example 2:
//
// Input: A = [3,5,1,2,3], B = [3,6,3,3,4]
// Output: -1
// Explanation:
// In this case, it is not possible to rotate the dominoes to make one row of values equal.
//
//
//
// Note:
//
//     1 <= A[i], B[i] <= 6
//     2 <= A.length == B.length <= 20000
//
// Seen this question in a real interview before?
//
//     Difficulty:Medium


class Solution {
    public int minDominoRotations(int[] A, int[] B) {

        // return mytry(A, B);

        return method2(A, B);
    }

    private int method2(int[] A, int[] B) {
        // 但其实呢， 只要某一个数的 freq “并” 到一个 row 之后为 row 的长度， 那么就可以 swap 成功
        // 所谓 “并” 就是两个数组这个数的 freq 加起来再减去相同位置的重复， 因为有可能同一个位置两个数组都是一样的数
        // O(N) time and O(7) space
        int n = A.length;
        int[] count1 = new int[7];
        int[] count2 = new int[7];
        int[] same = new int[7];
        for (int i = 0; i < A.length; i++) {
            count1[A[i]]++;
            count2[B[i]]++;
            if (A[i] == B[i]) {
                same[A[i]]++;
            }
        }
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < 7; i++) {
            if (count1[i] + count2[i] - same[i] == n) {
                result = Math.min(result, Math.min(count1[i], count2[i]) - same[i]);
            }
        }
        return result == Integer.MAX_VALUE ? -1 : result;
    }

    private int mytry(int[] A, int[] B) {
        int result = Math.min(minSwap(A, B), minSwap(B, A));
        return result == Integer.MAX_VALUE ? -1 : result;
    }
    private int minSwap(int[] A, int[] B) {
        // 我的想法还比较直接， 从 freq 最大的数开始， 看其他位置的是否能够通过 swap 换成一样的数
        // O(N ^ 2) time, O(N) space, where N is the length of A
        if (A.length > B.length) {
            return Integer.MAX_VALUE;
        }
        Map<Integer, Integer> map = new HashMap<>(); // <val, freq>
        for (int num : A) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        TreeMap<Integer, Set<Integer>> freq = new TreeMap<>((o1, o2) -> (Integer.compare(o2, o1))); // <freq, Set<val>>
        for (int key : map.keySet()) {
            Set<Integer> set = freq.computeIfAbsent(map.get(key), dummy -> (new HashSet<>()));
            set.add(key);
        }
        for (int key : freq.keySet()) {
            int result = Integer.MAX_VALUE;
            for (int val : freq.get(key)) {
                int count = 0;
                for (int i = 0; i < A.length; i++) {
                    if (A[i] == val) {
                        continue;
                    }
                    if (B[i] != val) {
                        count = Integer.MAX_VALUE;
                        break;
                    }
                    count++;
                }
                result = Math.min(result, count);
            }
            if (result != Integer.MAX_VALUE) {
                return result;
            }
        }
        return Integer.MAX_VALUE;
    }
}

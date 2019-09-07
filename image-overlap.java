// 835. Image Overlap
// DescriptionHintsSubmissionsDiscussSolution
// Two images A and B are given, represented as binary, square matrices of the same size.  (A binary matrix has only 0s and 1s as values.)
//
// We translate one image however we choose (sliding it left, right, up, or down any number of units), and place it on top of the other image.  After, the overlap of this translation is the number of positions that have a 1 in both images.
//
// (Note also that a translation does not include any kind of rotation.)
//
// What is the largest possible overlap?
//
// Example 1:
//
// Input: A = [[1,1,0],
//             [0,1,0],
//             [0,1,0]]
//        B = [[0,0,0],
//             [0,1,1],
//             [0,0,1]]
// Output: 3
// Explanation: We slide A to right by 1 unit and down by 1 unit.
// Notes:
//
// 1 <= A.length = A[0].length = B.length = B[0].length <= 30
// 0 <= A[i][j], B[i][j] <= 1
// Seen this question in a real interview before?
// Difficulty:Medium


class Solution {
    public int largestOverlap(int[][] A, int[][] B) {

        // return mytry(A, B);

        return method2(A, B);
    }

    private int method2(int[][] A, int[][] B) {
        // since it's a parse matrix, so we have a smarter way - use offset
        // ref:
        int n = A.length;
        // get the position of 1s in each matrix
        List<Integer> posA = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (A[i][j] == 1) {
                    posA.add(i * 100 + j);
                }
            }
        }
        List<Integer> posB = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (B[i][j] == 1) {
                    posB.add(i * 100 + j);
                }
            }
        }
        // count result by offsets
        Map<Integer, Integer> map = new HashMap<>(); // <offset, # overlap of 1>
        int result = 0;
        for (int i : posA) {
            for (int j : posB) {
                int count = map.getOrDefault(i - j, 0) + 1;
                map.put(i - j, count);
                result = Math.max(result, count);
            }
        }

        return result;
    }

    private int mytry(int[][] A, int[][] B) {
        // brute force: O(N ^ 4) tim, 2N left/right and 2N up/down and N ^ 2 to check overlap
        int n = A.length;
        int result = 0;
        // A starts the right-bottom to left-top, i.e. [n - 1][n - 1] to [0, 0]
        for (int row = -(n - 1); row <= n - 1; row++) {
            for (int col = -(n - 1); col <= n - 1; col++) {
                int count = 0;
                // B always starts [0, 0] to [n - 1][n - 1]
                for (int i = Math.max(row, 0); i < Math.min(row + n, n); i++) {
                    for (int j = Math.max(col, 0); j < Math.min(col + n, n); j++) {
                        if (B[i][j] == 1 && A[i - row][j - col] == 1) {
                            count++;
                        }
                    }
                }
                result = Math.max(result, count);
            }
        }
        return result;
    }
}

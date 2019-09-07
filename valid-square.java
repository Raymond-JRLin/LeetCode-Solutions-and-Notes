// 593. Valid Square
// DescriptionHintsSubmissionsDiscussSolution
// Given the coordinates of four points in 2D space, return whether the four points could construct a square.
//
// The coordinate (x,y) of a point is represented by an integer array with two integers.
//
// Example:
//
// Input: p1 = [0,0], p2 = [1,1], p3 = [1,0], p4 = [0,1]
// Output: True
//
//
// Note:
//
// All the input integers are in the range [-10000, 10000].
// A valid square has four equal sides with positive length and four equal angles (90-degree angles).
// Input points have no order.
//
//
// Seen this question in a real interview before?
// Difficulty:Medium


class Solution {
    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        if (p1[0] == p2[0] && p1[1] == p2[1] || p1[0] == p3[0] && p1[1] == p3[1] || p1[0] == p4[0] && p1[1] == p4[1]) {
            // has same points
            return false;
        }


        // return method1(p1, p2, p3, p4);

        // return method2(p1, p2, p3, p4);

        return method3(p1, p2, p3, p4);
    }

    private boolean method3(int[] p1, int[] p2, int[] p3, int[] p4) {

        int[][] p = new int[][]{p1, p2, p3, p4};
        Arrays.sort(p, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]) {
                    return Integer.compare(o1[1], o2[1]);
                } else {
                    return Integer.compare(o1[0], o2[0]);
                }
            }
        });
        int x1 = p[0][0];
        int y1 = p[0][1];
        int x2 = p[3][0];
        int y2 = p[3][1];
        if (getDis(p[0], p[1]) == getDis(p[0], p[2]) && getHash(x1, y2) == getHash(p[1][0], p[1][1]) &&
           getHash(x2, y1) == getHash(p[2][0], p[2][1])) {
            return true;
        }
        return false;
    }
    private long getHash(int x, int y) {
        return (long) x * 12345 + y;
    }

    private boolean method2(int[] p1, int[] p2, int[] p3, int[] p4) {
        // another way to check all possible scenarios, assume we have 4 points in clockwise order: 0, 1, 2, 3; then we should check like what we do in method1, and transfer 4 points in different order to check
        int len2 = getDis(p1, p2);
        int len3 = getDis(p1, p3);
        int len4 = getDis(p1, p4);
        if (len2 == len3 && 2 * len2 == len4) {
            return (getDis(p4, p2) == len2 && getDis(p4, p3) == len2);
        }
        if (len2 == len4 && 2 * len2 == len3) {
            return (getDis(p3, p2) == len2 && getDis(p3, p4) == len2);
        }
        if (len3 == len4 && 2 * len3 == len2) {
            return (getDis(p2, p3) == len3 && getDis(p2, p4) == len3);
        }
        return false;
    }

    private boolean method1(int[] p1, int[] p2, int[] p3, int[] p4) {
        // only 3 scenarios, after sortingm we can use index to check relation between sides
        int[][] p = new int[][]{p1, p2, p3, p4};
        Arrays.sort(p, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]) {
                    return Integer.compare(o1[1], o2[1]);
                } else {
                    return Integer.compare(o1[0], o2[0]);
                }
            }
        });
        // 4 sides
        int len1 = getDis(p[0], p[1]);
        int len2 = getDis(p[2], p[3]);
        int len3 = getDis(p[0], p[2]);
        int len4 = getDis(p[1], p[3]);
        // 2 diagonals
        int dia1 = getDis(p[0], p[3]);
        int dia2 = getDis(p[1], p[2]);
        if (len1 == len2 && len2 == len3 && len3 == len4 && len4 == len1 && dia1 == dia2) {
            return true;
        }
        return false;
    }

    private int getDis(int[] p1, int[] p2) {
        // get square distance of 2 points actually
        return (p1[0] - p2[0]) * (p1[0] - p2[0]) + (p1[1] - p2[1]) * (p1[1] - p2[1]);
    }
}

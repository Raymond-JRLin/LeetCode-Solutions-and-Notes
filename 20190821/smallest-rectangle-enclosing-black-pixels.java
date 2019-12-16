// 302. Smallest Rectangle Enclosing Black Pixels
// DescriptionHintsSubmissionsDiscussSolution
//
// An image is represented by a binary matrix with 0 as a white pixel and 1 as a black pixel. The black pixels are connected, i.e., there is only one black region. Pixels are connected horizontally and vertically. Given the location (x, y) of one of the black pixels, return the area of the smallest (axis-aligned) rectangle that encloses all black pixels.
//
// Example:
//
// Input:
// [
//   "0010",
//   "0110",
//   "0100"
// ]
// and x = 0, y = 2
//
// Output: 6
//
// Seen this question in a real interview before?
//
//     Difficulty:Hard


class Solution {
    public int minArea(char[][] image, int x, int y) {
        if (image == null || image.length == 0 || image[0].length == 0) {
            return 0;
        }

        // return method1(image, x, y);

        return method2(image, x, y);
    }

    private int method2(char[][] image, int x, int y) {
        // 题目确定了一定有且只有一个连通块， 那么投影 col 或者 row 就一定会有连通的 1
        // 可以采用 BS 去找 4 边界， 每次 check mid 的时候是查对于这一行/列， 是否至少有一列/行有 1
        int n = image.length;
        int m = image[0].length;
        // 这个地方的 BS 特别要注意
        // 1. 边界情况， 右/下边界使用了 + 1 和 n/m， 以为着至少已经加了 1， 最后求距离的时候不能再 + 1 了
        // 2. 还有一个是 top-bottom/left-right 限制， 如果在 BS 中 == 为否定条件， 那么传的参就要是 n/m， 即已经出界的条件
        // 很巧妙的用一个 BS 来写左/右和上/下， 一般来说 first index 比较 tricky， 因为会涉及到死循环， 这里因为注意点 1， 再加上 flag 标示， 正好使得可以使用同一种条件判断
        int left = bsCol(image, 0, y, 0, n, true);
        int right = bsCol(image, y + 1, m, 0, n, false);
        int up = bsRow(image, 0, x, 0, m, true);
        int down = bsRow(image, x + 1, n, 0, m, false);
        // System.out.println(left + ", " + right + ", " + up + ", " + down);
        return (right - left) * (down - up);
    }
    private int bsCol(char[][] image, int start, int end, int top, int bottom, boolean flag) {
        while (start < end) {
            int mid = start + (end - start) / 2;
            int k = top;
            while (k < bottom && image[k][mid] != '1') {
                k++;
            }
            if ((k < bottom) == flag) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return start;
    }
    private int bsRow(char[][] image, int start, int end, int left, int right, boolean flag) {
        while (start < end) {
            int mid = start + (end - start) / 2;
            int k = left;
            while (k < right && image[mid][k] != '1') {
                k++;
            }
            if ((k < right) == flag) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return start;
    }
    /*
    private int firstCol(char[][] image, int start, int end, int top, int bottom) {
        while (start < end) {
            int mid = start + (end - start) / 2;
            int k = top;
            while (k < bottom && image[k][mid] != '1') {
                k++;
            }
            if (k == bottom) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        return start;
    }
    private int lastCol(char[][] image, int start, int end, int top, int bottom) {
        int prev = start;
        while (start < end) {
            int mid = start + (end - start) / 2;
            int k = top;
            while (k < bottom && image[k][mid] != '1') {
                k++;
            }
            if (k == bottom) {
                end = mid - 1;
            } else {
                prev = start;
                start = mid + 1;
            }
        }
        return prev;
    }
    private int firstRow(char[][] image, int start, int end, int top, int bottom) {
        while (start < end) {
            int mid = start + (end - start) / 2;
            int k = top;
            while (k < bottom && image[mid][k] != '1') {
                k++;
            }
            if (k == bottom) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        return start;
    }
    private int lastRow(char[][] image, int start, int end, int top, int bottom) {
        System.out.println("lastRow: start = " + start + ", end = " + end);
        int prev = start;
        while (start < end) {
            int mid = start + (end - start) / 2;
            System.out.println("mid = " + mid);
            int k = top;
            while (k < bottom && image[mid][k] != '1') {
                k++;
            }
            if (k == bottom) {
                end = mid - 1;
            } else {
                prev = start;
                start = mid + 1;
            }
        }
        int k = top;
            while (k < bottom && start <= end && image[start][k] != '1') {
                k++;
            }
        if (k == bottom) {
            return prev;
        } else {
            return start;
        }

    }
    **/

    private int method1(char[][] image, int x, int y) {
        // 也就是说要找一个矩形， 能够框住所有的 1
        // 那可以从 (x, y) 出发， 向 4 个方向走， 去找 4 个边界， 然后计算面积
        // O(N * M) time complexity
        int n = image.length;
        int m = image[0].length;
        boolean[][] visited = new boolean[n][m];
        int[] len = new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE}; // [up, down, left, right]
        dfs(image, x, y, visited, len);
        return (len[1] + 1 - len[0]) * (len[3] + 1 - len[2]);
    }
    private void dfs(char[][] image, int x, int y, boolean[][] visited, int[] len) {
        if (x < 0 || x >= image.length || y < 0 || y >= image[x].length) {
            return;
        }
        if (visited[x][y]) {
            return;
        }
        if (image[x][y] != '1') {
            return;
        }

        len[0] = Math.min(len[0], y);
        len[1] = Math.max(len[1], y);
        len[2] = Math.min(len[2], x);
        len[3] = Math.max(len[3], x);

        visited[x][y] = true;
        dfs(image, x - 1, y, visited, len);
        dfs(image, x + 1, y, visited, len);
        dfs(image, x, y - 1, visited, len);
        dfs(image, x, y + 1, visited, len);

        // 如果这里 backtracking 的话， 就会 TLE， 我个人认为是如果走过了那么就更新了我们的边界答案对于那个点的位置
        // 所以没有必要去还原， 然后再重复走
        // 因为我们只需要 4 边最边界的情况， 所以走过就走过了， 反正会在那个点更新 4 边， 不在意怎么走到这个点以及之后的走法
        // visited[x][y] = false;
    }
}

// 733. Flood Fill
// User Accepted: 1184
// User Tried: 1262
// Total Accepted: 1206
// Total Submissions: 2539
// Difficulty: Easy
// An image is represented by a 2-D array of integers, each integer representing the pixel value of the image (from 0 to 65535).
//
// Given a coordinate (sr, sc) representing the starting pixel (row and column) of the flood fill, and a pixel value newColor, "flood fill" the image.
//
// To perform a "flood fill", consider the starting pixel, plus any pixels connected 4-directionally to the starting pixel of the same color as the starting pixel, plus any pixels connected 4-directionally to those pixels (also with the same color as the starting pixel), and so on. Replace the color of all of the aforementioned pixels with the newColor.
//
// At the end, return the modified image.
//
// Example 1:
// Input:
// image = [[1,1,1],[1,1,0],[1,0,1]]
// sr = 1, sc = 1, newColor = 2
// Output: [[2,2,2],[2,2,0],[2,0,1]]
// Explanation:
// From the center of the image (with position (sr, sc) = (1, 1)), all pixels connected
// by a path of the same color as the starting pixel are colored with the new color.
// Note the bottom corner is not colored 2, because it is not 4-directionally connected
// to the starting pixel.
// Note:
//
// The length of image and image[0] will be in the range [1, 50].
// The given starting pixel will satisfy 0 <= sr < image.length and 0 <= sc < image[0].length.
// The value of each color in image[i][j] and newColor will be an integer in [0, 65535].


class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        if (image == null || image.length == 0 || image[0].length == 0) {
            return image;
        }
        int n = image.length;
        int m = image[0].length;
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};

        Queue<Coordinate> queue = new LinkedList<>();
        boolean[][] visited = new boolean[n][m];
        int changeColor = image[sr][sc];
        Coordinate root = new Coordinate(sr, sc);
        queue.offer(root);
        image[sr][sc] = newColor;
        visited[sr][sc] = true;
        while (!queue.isEmpty()) {
            Coordinate curr = queue.poll();
            for (int i = 0; i < 4; i++) {
                int row = curr.x + dx[i];
                int col = curr.y + dy[i];
                if (!isValid(row, col, image)) {
                    continue;
                }
                if (image[row][col] != changeColor) {
                    continue;
                }
                if (visited[row][col]) {
                    continue;
                }
                image[row][col] = newColor;
                visited[row][col] = true;
                Coordinate nei = new Coordinate(row, col);
                queue.offer(nei);
            }
        }
        return image;
    }
    private boolean isValid(int x, int y, int[][] image) {
        if (x < 0 || x >= image.length || y < 0 || y >= image[0].length) {
            return false;
        }
        return true;
    }
}
class Coordinate {
    public int x;
    public int y;
    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

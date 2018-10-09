// 276. Paint Fence
// DescriptionHintsSubmissionsDiscussSolution
// There is a fence with n posts, each post can be painted with one of the k colors.
//
// You have to paint all the posts such that no more than two adjacent fence posts have the same color.
//
// Return the total number of ways you can paint the fence.
//
// Note:
// n and k are non-negative integers.
//
// Example:
//
// Input: n = 3, k = 2
// Output: 6
// Explanation: Take c1 as color 1, c2 as color 2. All possible ways are:
//
//             post1  post2  post3
//  -----      -----  -----  -----
//    1         c1     c1     c2
//    2         c1     c2     c1
//    3         c1     c2     c2
//    4         c2     c1     c1
//    5         c2     c1     c2
//    6         c2     c2     c1


class Solution {
    public int numWays(int n, int k) {
        if (n == 0 || k < 1) {
            return 0;
        }
        if (n == 1) {
            return k;
        }

        // return method1(n, k);

        // return method2(n, k);

        return method3(n, k);
    }

    private int method3(int n, int k) {
        // I think at i == 0, just k, then at i == 1:
        int same = k;
        int diff = k * (k - 1);
        for (int i = 2; i < n; i++) {
            int temp = diff;
            diff = (diff + same) * (k - 1); // whatever it's same or different with last one, we got this different, i.e. (k - 1)
            same = temp; // current same is just last diff
        }
        return same + diff;
    }

    private int method2(int n, int k) {
        if (n == 2) {
            return k * k;
        }
        int[] f = new int[3];
        f[0] = k;
        f[1] = k * k;
        for (int i = 2; i < n; i++) {
            f[2] = f[0] * (k - 1) + f[1] * (k - 1);
            f[0] = f[1];
            f[1] = f[2];
        }
        return f[2];
    }

    private int method1(int n, int k) {
        // definition: f[i] = ways to paint at i
        int[] f = new int[n];
        // initialization
        f[0] = k;
        f[1] = k * k; // no more than 2, so i can be same color with i - 1
        // DP
        for (int i = 2; i < n; i++) {
            f[i] = f[i - 1] * (k - 1) + f[i - 2] * (k - 1);
        }
        // result
        return f[n - 1];
    }
}

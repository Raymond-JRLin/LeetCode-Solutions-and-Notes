// 1326. Minimum Number of Taps to Open to Water a Garden
//
//     User Accepted: 820
//     User Tried: 1471
//     Total Accepted: 890
//     Total Submissions: 3385
//     Difficulty: Hard
//
// There is a one-dimensional garden on the x-axis. The garden starts at the point 0 and ends at the point n. (i.e The length of the garden is n).
//
// There are n + 1 taps located at points [0, 1, ..., n] in the garden.
//
// Given an integer n and an integer array ranges of length n + 1 where ranges[i] (0-indexed) means the i-th tap can water the area [i - ranges[i], i + ranges[i]] if it was open.
//
// Return the minimum number of taps that should be open to water the whole garden, If the garden cannot be watered return -1.
//
//
//
// Example 1:
//
// Input: n = 5, ranges = [3,4,1,1,0,0]
// Output: 1
// Explanation: The tap at point 0 can cover the interval [-3,3]
// The tap at point 1 can cover the interval [-3,5]
// The tap at point 2 can cover the interval [1,3]
// The tap at point 3 can cover the interval [2,4]
// The tap at point 4 can cover the interval [4,4]
// The tap at point 5 can cover the interval [5,5]
// Opening Only the second tap will water the whole garden [0,5]
//
// Example 2:
//
// Input: n = 3, ranges = [0,0,0,0]
// Output: -1
// Explanation: Even if you activate all the four taps you cannot water the whole garden.
//
// Example 3:
//
// Input: n = 7, ranges = [1,2,1,0,2,1,0,1]
// Output: 3
//
// Example 4:
//
// Input: n = 8, ranges = [4,0,0,0,0,0,0,0,4]
// Output: 2
//
// Example 5:
//
// Input: n = 8, ranges = [4,0,0,0,4,0,0,0,4]
// Output: 1
//
//
//
// Constraints:
//
//     1 <= n <= 10^4
//     ranges.length == n + 1
//     0 <= ranges[i] <= 100
//


class Solution {
    public int minTaps(int n, int[] ranges) {

        // return method1(n, ranges);

        return method2(n, ranges);
    }

    private int method2(int n, int[] ranges) {
        // DP
        // definition: f[i] :=
        int[] f = new int[n + 1];
        // init
        Arrays.fill(f, n + 2);
        f[0] = 0;
        // DP: f[i] = MIN{f[i], f[Math.max(0, i - ranges[i])] + 1}
        for (int i = 0; i <= n; i++) {
            int left = Math.max(i - ranges[i] + 1, 0);
            int right = Math.min(i + ranges[i], n);
            for (int j = left; j <= right; j++) {
                // 可以从 i - ranges[i] 的位置接上去前面的覆盖长度
                f[j] = Math.min(f[j], f[Math.max(0, i - ranges[i])] + 1);
            }
        }
        // result
        return f[n] == n + 2 ? -1 : f[n];
    }

    private int method1(int n, int[] ranges) {
        // greedy
        // mostReach[i] 记录了 i 这个位置所能够到达的最右边， 或者说能够覆盖的最远的 range
        int[] mostReach = new int[n];
        Arrays.fill(mostReach, -1);
        for (int i = 0; i < ranges.length; i++) {
            if (ranges[i] == 0) {
                continue;
            }
            int left = i - ranges[i] < 0 ? 0 : i - ranges[i];
            int right = i + ranges[i];
            mostReach[left] = Math.max(mostReach[left], right);
        }
        // greedy： 每次都向最远的右边去延伸
        int i = 0;
        int longest = mostReach[i];
        int count = 1;
        while (longest < n) {
            int currRight = 0;
            for (; i <= longest; i++) {
                // 在目前能够到达的范围内， 找最右边能够到达的距离
                if (mostReach[i] == 0) {
                    continue;
                }
                currRight = Math.max(currRight, mostReach[i]);
            }
            // 如果没办法再继续延伸， 则无法到达 n
            if (currRight <= longest) {
                return -1;
            }
            longest = currRight;
            count++;
        }
        return count;
    }
}

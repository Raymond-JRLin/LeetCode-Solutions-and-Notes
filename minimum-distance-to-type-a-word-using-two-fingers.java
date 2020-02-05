// 1320. Minimum Distance to Type a Word Using Two Fingers
//
//     User Accepted: 571
//     User Tried: 1002
//     Total Accepted: 623
//     Total Submissions: 1990
//     Difficulty: Hard
//
// You have a keyboard layout as shown above in the XY plane, where each English uppercase letter is located at some coordinate, for example, the letter A is located at coordinate (0,0), the letter B is located at coordinate (0,1), the letter P is located at coordinate (2,3) and the letter Z is located at coordinate (4,1).
//
// Given the string word, return the minimum total distance to type such string using only two fingers. The distance between coordinates (x1,y1) and (x2,y2) is |x1 - x2| + |y1 - y2|.
//
// Note that the initial positions of your two fingers are considered free so don't count towards your total distance, also your two fingers do not have to start at the first letter or the first two letters.
//
//
//
// Example 1:
//
// Input: word = "CAKE"
// Output: 3
// Explanation:
// Using two fingers, one optimal way to type "CAKE" is:
// Finger 1 on letter 'C' -> cost = 0
// Finger 1 on letter 'A' -> cost = Distance from letter 'C' to letter 'A' = 2
// Finger 2 on letter 'K' -> cost = 0
// Finger 2 on letter 'E' -> cost = Distance from letter 'K' to letter 'E' = 1
// Total distance = 3
//
// Example 2:
//
// Input: word = "HAPPY"
// Output: 6
// Explanation:
// Using two fingers, one optimal way to type "HAPPY" is:
// Finger 1 on letter 'H' -> cost = 0
// Finger 1 on letter 'A' -> cost = Distance from letter 'H' to letter 'A' = 2
// Finger 2 on letter 'P' -> cost = 0
// Finger 2 on letter 'P' -> cost = Distance from letter 'P' to letter 'P' = 0
// Finger 1 on letter 'Y' -> cost = Distance from letter 'A' to letter 'Y' = 4
// Total distance = 6
//
// Example 3:
//
// Input: word = "NEW"
// Output: 3
//
// Example 4:
//
// Input: word = "YEAR"
// Output: 7
//
//
//
// Constraints:
//
//     2 <= word.length <= 300
//     Each word[i] is an English uppercase letter.
//


class Solution {
    public int minimumDistance(String word) {

        // return mytry(word);

        // return method1(word);

        // return method2(word);

        // return method3(word);

        return method4(word);
    }

    private int method4(String word) {
        // 只记录一只手指
        // 假设左手指不动， 只移动右手的话， 那么就是最坏的情况: 用一只手指从头移动到尾
        // 那么考虑在只移动右手的过程中， 让左手移动某些 char， 看是否能够 save 一些 cost， 那么问题就变成了左手该移动哪些则可以有最大的 save
        int n = word.length();
        // definition: f[i] := 左手指 end at char i 时候能够得到的 max save
        int[] f = new int[26];
        int save = 0;
        int total = 0;
        // init
        // DP
        for (int i = 0; i < n - 1; i++) {
            int curr = word.charAt(i) - 'A';
            int next = word.charAt(i + 1) - 'A';
            for (int j = 0; j < 26; j++) {
                f[curr] = Math.max(f[curr], f[j] + (getCost(curr, next) - getCost(j, next)));
            }
            save = Math.max(save, f[curr]);
            total += getCost(curr, next);
        }
        // result
        return total - save;
    }

    private int method3(String word) {
        // 如果想要再简化的话， 可以省一只手指的空间
        // 就是说， 当前手指， 假设左手指， 在 i 位置， 那么另一只， 即右手指就肯定在 i - 1 的位置（或者起始处）
        // 这样就可以只用记录一只手指的位置就好了
        int n = word.length();
        // definition: memo[left][index] := 另一只手指 （前前次移动的） 在 left， 上次移动的手指在 index - 1/或起始位置时， 从 index 位置出发打完 word 的 min cost
        int[][] memo = new int[27][n];
        // 从 1 开始， 因为肯定要先有一只手指放在 index == 0 的位置上， 而这个 move 是不 cost 的
        return recursion(word, 1, 26, memo);
    }
    private int recursion(String word, int index, int other, int[][] memo) {
        // other 指的是另一个 finger， 在前一次移动之前的那个
        // 因为前一个移动的一定在 index - 1 的位置上， 所以不需要去记录
        if (index == word.length()) {
            return 0;
        }
        if (memo[other][index] != 0) {
            return memo[other][index];
        }
        int curr = word.charAt(index) - 'A'; // 现在要移动到的位置
        int prev = word.charAt(index - 1) - 'A'; // 上一只手指移动的位置
        //  我们始终记录的是另一个只手指， 即上一次移动的前一次手指的位置
        // 如果我们移动另一个只手指， 那么对于下一次来说， 上一次处在 prev 位置的就是前前次手指的位置， 即新的 other finger
        int moveOtherFinger = getCost(other, curr) + recursion(word, index + 1, prev, memo);
        // 继续移动上一次移动的手指， 前前次仍然是 other
        int moveLastFinger = getCost(prev, curr) + recursion(word, index + 1, other, memo);
        return memo[other][index] = Math.min(moveOtherFinger, moveLastFinger);
    }

    private int method2(String word) {
        // bottom-up iterative DP version of method1
        int n = word.length();
        // definition: memo[left][right][index] := 左手指在 left， 右手指在right 位置时， 从 index 位置出发打完 word 的 min cost
        int[][][] f = new int[27][27][n + 1];
        // init
        // DP
        // for 不同的起始位置
        for (int k = n - 1; k >= 0; k--) {
            // index of word
            for (int i = 0; i < 27; i++) {
                // 1st finger
                for (int j = 0; j < 27; j++) {
                    // 2nd finger
                        int curr = word.charAt(k) - 'A';
                        int moveLeft = getCost(i, curr) + f[curr][j][k + 1];
                        int moveRight = getCost(j, curr) + f[i][curr][k + 1];
                        f[i][j][k] = Math.min(moveLeft, moveRight);
                }
            }
        }
        // result
        return f[26][26][0];
    }

    private int method1(String word) {
        // top-down recursive DP with memo
        // 其实在比赛的时候就知道要 memo， 但是关键在于 memo 什么， 其实当时有在想应该记录两只手指的位置， 那么还应该记录的是我现在走到了 word 的哪一个位置
        // 之后 DP 的走向就是要么移动第一只手指， 要么移动第二只手指， 不要想太复杂了
        int n = word.length();
        // definition: memo[left][right][index] := 左手指在 left， 右手指在right 位置时， 从 index 位置出发打完 word 的 min cost
        int[][][] memo = new int[27][27][n];
        return recursion(word, 0, 26, 26, memo);
    }
    private int recursion(String word, int index, int left, int right, int[][][] memo) {
        if (index == word.length()) {
            return 0;
        }
        if (memo[left][right][index] != 0) {
            return memo[left][right][index];
        }
        int curr = word.charAt(index) - 'A';
        int moveLeft = getCost(left, curr) + recursion(word, index + 1, curr, right, memo);
        int moveRight = getCost(right, curr) + recursion(word, index + 1, left, curr, memo);
        return memo[left][right][index] = Math.min(moveLeft, moveRight);
    }
    private int getCost(int from, int to) {
        // cost moving from 'from' char to 'to' char
        if (from == 26) {
            // 手指在起始位置
            return 0;
        }
        return Math.abs(from / 6 - to / 6) + Math.abs(from % 6 - to % 6);
    }

    private int mytry(String word) {
        // TLE
        // 没有 memo 应该是不行的
        result = Integer.MAX_VALUE;
        dfs(word, 0, false, false, new int[]{-1, -1}, new int[]{-1, -1}, 0);
        return result;
    }
    private int result;
    private void dfs(String word, int index, boolean leftUsed, boolean rightUsed, int[] left, int[] right, int sum) {
        if (index == word.length()) {
            result = Math.min(result, sum);
            return;
        }
        if (sum > result) {
            return;
        }
        int[] curr = new int[]{getX(word.charAt(index)), getY(word.charAt(index))};
        if (!leftUsed) {
            dfs(word, index + 1, true, rightUsed, curr, right, sum);
        } else {
            if (!rightUsed) {
                // move right at its 1st index
                dfs(word, index + 1, leftUsed, true, left, curr, sum);
                // move left
                dfs(word, index + 1, leftUsed, rightUsed, curr, right, sum + getDist(left, curr));
            } else {
                // move left
                dfs(word, index + 1, leftUsed, rightUsed, curr, right, sum + getDist(left, curr));
                // move left
                dfs(word, index + 1, leftUsed, rightUsed, left, curr, sum + getDist(right, curr));
            }
        }
    }
    private int getX(char c) {
        return (c - 'A') / 6;
    }
    private int getY(char c) {
        return (c - 'A') % 6;
    }
    private int getDist(int[] a, int[] b) {
        return Math.abs(a[0] - b[0]) + Math.abs(a[1] - b[1]);
    }
}

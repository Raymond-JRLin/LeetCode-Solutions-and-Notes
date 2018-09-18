// 869. Reordered Power of 2
// User Accepted: 1320
// User Tried: 1605
// Total Accepted: 1337
// Total Submissions: 3088
// Difficulty: Medium
// Starting with a positive integer N, we reorder the digits in any order (including the original order) such that the leading digit is not zero.
//
// Return true if and only if we can do this in a way such that the resulting number is a power of 2.
//
//
//
// Example 1:
//
// Input: 1
// Output: true
// Example 2:
//
// Input: 10
// Output: false
// Example 3:
//
// Input: 16
// Output: true
// Example 4:
//
// Input: 24
// Output: false
// Example 5:
//
// Input: 46
// Output: true
//
//
// Note:
//
// 1 <= N <= 10^9


class Solution {
    public boolean reorderedPowerOf2(int N) {
        if ((N & (N - 1)) == 0) {
            return true;
        }

        // return mytry(N);

        return method2(N);
    }

    private boolean method2(int n) {
        int target = counter(n);
        for (int i = 0; i < 32; i++) {
            // we go through all numbers of power of 2 in integer range, to check if there's one number, its binary digits representation is the same
            if (counter(1 << i) == target)  {
                return true;
            }
        }
        return false;
    }
    public int counter(int N) {
        // simulate binary representation, to represent # of digits, e.g. 10110 means no 0, one 1, one 2, no 3, one 4
        int res = 0;
        for (; N > 0; N /= 10) {
            res += (int) Math.pow(10, N % 10);
        }
        return res;
    }

    private boolean mytry(int n) {
        // DFS
        char[] arr = String.valueOf(n).toCharArray();
        int len = arr.length;
        for (int i = 0; i < len; i++) {
            if (arr[i] != '0') {
                // avoid leading 0
                if (dfs(arr, len, new boolean[len], 1, (int) (arr[i] - '0'))) {
                    return true;
                }
            }
        }
        return false;
    }
    private boolean dfs(char[] arr, int len, boolean[] visited, int count, int num) {
        if (count == len) {
            if ((num & (num - 1)) == 0) {
                return true;
            } else {
                return false;
            }
        }
        for (int i = 0; i < len; i++) {
            if (visited[i]) {
                continue;
            }
            visited[i] = true;
            if (dfs(arr, len, visited, count + 1, num * 10 + (arr[i] - '0'))) {
                return true;
            }
            visited[i] = false;
        }
        return false;
    }
}

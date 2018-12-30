// 967. Numbers With Same Consecutive Differences
// User Accepted: 1313
// User Tried: 1535
// Total Accepted: 1329
// Total Submissions: 4743
// Difficulty: Medium
// Return all non-negative integers of length N such that the absolute difference between every two consecutive digits is K.
//
// Note that every number in the answer must not have leading zeros except for the number 0 itself. For example, 01 has one leading zero and is invalid, but 0 is valid.
//
// You may return the answer in any order.
//
//
//
// Example 1:
//
// Input: N = 3, K = 7
// Output: [181,292,707,818,929]
// Explanation: Note that 070 is not a valid number, because it has leading zeroes.
// Example 2:
//
// Input: N = 2, K = 1
// Output: [10,12,21,23,32,34,43,45,54,56,65,67,76,78,87,89,98]
//
//
// Note:
//
// 1 <= N <= 9
// 0 <= K <= 9


class Solution {
    public int[] numsSameConsecDiff(int N, int K) {
        if (N < 1) {
            return new int[0];
        }

        // return mytry(N, K);

        return method2(N, K);
    }

    private int[] method2(int n, int k) {
        // 其是可以不需要用 char 来， 直接用 int， % 可以得到最后一位数
        List<Integer> curr = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
        for (int i = 1; i < n; i++) {
            List<Integer> next = new ArrayList<>();
            for (int num : curr) {
                if (num == 0) {
                    continue;
                }
                int digit = num % 10;
                if (digit + k <= 9) {
                    next.add(num * 10 + digit + k);
                }
                // 也是要跳过当 k = 0
                if (k > 0 && digit - k >= 0) {
                    next.add(num * 10 + digit - k);
                }
            }
            curr = next;
        }
        int[] result = new int[curr.size()];
        for (int i = 0; i < curr.size(); i++) {
            result[i] = curr.get(i);
        }
        return result;
    }

    private int[] mytry(int n, int k) {
        List<Integer> list = new ArrayList<>();
        char[] nums = new char[n];
        for (int i = 0; i <= 9; i++) {
            // 跳过只有 1 位数的时候， 开头为 0
            if (n != 1 && i == 0) {
                continue;
            }
            nums[0] = (char) (i + '0');
            dfs(n, k, list, nums, 1);
        }
        int[] result = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
    }
    private void dfs(int n, int k, List<Integer> list, char[] nums, int pos) {
        if (pos == n) {
            list.add(Integer.parseInt(String.valueOf(nums)));
            return;
        }
        int prev = nums[pos - 1] - '0';
        if (prev + k <= 9) {
            nums[pos] = (char) (prev + k + '0');
            dfs(n, k, list, nums, pos + 1);
            nums[pos] = '0';
        }
        // 如果 k 为 0， e.g. 11, 22, 就不能继续下面的， 不然会有重复
        if (k == 0) {
            return;
        }
        if (prev - k >= 0) {
            nums[pos] = (char) (prev - k + '0');
            dfs(n, k, list, nums, pos + 1);
            nums[pos] = '0';
        }
    }
}

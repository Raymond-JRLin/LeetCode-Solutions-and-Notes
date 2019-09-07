// 646. Maximum Length of Pair Chain
// DescriptionHintsSubmissionsDiscussSolution
// You are given n pairs of numbers. In every pair, the first number is always smaller than the second number.
//
// Now, we define a pair (c, d) can follow another pair (a, b) if and only if b < c. Chain of pairs can be formed in this fashion.
//
// Given a set of pairs, find the length longest chain which can be formed. You needn't use up all the given pairs. You can select pairs in any order.
//
// Example 1:
// Input: [[1,2], [2,3], [3,4]]
// Output: 2
// Explanation: The longest chain is [1,2] -> [3,4]
// Note:
// The number of given pairs will be in the range [1, 1000].
// Seen this question in a real interview before?
// Difficulty:Medium


class Solution {
    public int findLongestChain(int[][] pairs) {
        if (pairs == null || pairs.length == 0 || pairs[0].length == 0) {
            return 0;
        }

        // return my_try(pairs);

        // return method2_greedy_stack(pairs);

        return method2_greedy_variable(pairs);
    }

    private int my_try(int[][] pairs) {
        // sort given array and then do DP
        Arrays.sort(pairs, new Comparator<int[]> () {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        int n = pairs.length;
        // definition
        // 这里不用二维数组找 i - j 之间的能连成多长， 而是可以跳着连接的， 所以应该使用一维数组
        int[] f = new int[n];
        // initialization
        f[0] = 1;
        // DP
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (pairs[j][0] > pairs[i][1]) {
                    f[j] = Math.max(f[j], f[i] + 1);
                } else {
                    f[j] = Math.max(f[j], f[i]);
                }
            }

        }
//         一定要记住要用 max 来更新， 因为当前面已经连起来了后， 从下一个 i 开始算之后，  j 会覆盖前面计算的结果， 所以应该要看是否比当前已经得到的结果还大才去更新
        // or we can do
        // for (int i = 0; i < n; i++) {
        //     for (int j = 0; j < i; j++) {
        //         if (pairs[i][0] > pairs[j][1]) {
        //             f[i] = f[j] + 1;
        //         } else {
        //             f[i] = f[j];
        //         }
        //     }
        // }
        return f[n - 1];
    }

    private int method2_greedy_stack(int[][] pairs) {
        Arrays.sort(pairs, new Comparator<int[]> () {
            @Override
            public int compare(int[] o1, int[] o2) {
//                 greedy 的解法就要注意这里是按照第二位数字的从小到大排列， 因为这样才能算是 “greedy”， 把尽可能小的第二位放在前面， 即尽可能的让好一对的第一位数能够大于前面一对的第二位数
                return o1[1] - o2[1];
            }
        });
        int n = pairs.length;
        Stack<int[]> stack = new Stack<>();
        for (int[] pair : pairs) {
            if (stack.isEmpty()) {
                stack.push(pair);
            } else {
                int[] top = stack.peek();
                if (pair[0] > top[1]) {
                    stack.push(pair);
                }
            }
        }
        return stack.size();
    }
    private int method2_greedy_variable(int[][] pairs) {
        Arrays.sort(pairs, new Comparator<int[]> () {
            @Override
            public int compare(int[] o1, int[] o2) {
//                 greedy 的解法就要注意这里是按照第二位数字的从小到大排列， 因为这样才能算是 “greedy”， 把尽可能小的第二位放在前面， 即尽可能的让好一对的第一位数能够大于前面一对的第二位数
                return o1[1] - o2[1];
            }
        });
        int n = pairs.length;
        int count = 0;
        // 用 variable 来替代 stack 记住第二位数字
        int end = Integer.MIN_VALUE;
        for (int[] pair : pairs) {
            if (pair[0] > end) {
                count++;
                end = pair[1];
            }
        }
        return count;
    }
}

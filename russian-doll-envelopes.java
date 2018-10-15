// 354. Russian Doll Envelopes
// DescriptionHintsSubmissionsDiscussSolution
// You have a number of envelopes with widths and heights given as a pair of integers (w, h). One envelope can fit into another if and only if both the width and height of one envelope is greater than the width and height of the other envelope.
//
// What is the maximum number of envelopes can you Russian doll? (put one inside other)
//
// Note:
// Rotation is not allowed.
//
// Example:
//
// Input: [[5,4],[6,4],[6,7],[2,3]]
// Output: 3
// Explanation: The maximum number of envelopes you can Russian doll is 3 ([2,3] => [5,4] => [6,7]).


class Solution {
    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes == null || envelopes.length == 0 || envelopes[0].length < 2) {
            return 0;
        }

        // return method1(envelopes);

        return method2(envelopes);
    }

    private int method2(int[][] nums) {
        // method1 不够快， 可以这么想： 在 width 排序完成后， 就是在找 height 维度上的 LIS， 所以需要降序排列， 不然会出现相同 width 而 height 从小到大的答案
        int n = nums.length;
        Arrays.sort(nums, new myComparator());
        int[] f = new int[n];
        int result = 0; // 本来至少是 1， 但是它同时要做 f 的 index， 所以要从 0 开始， 不然会数组越界
        // LIS
        for (int[] env : nums) {
            int index = Arrays.binarySearch(f, 0, result, env[1]); // 找到当前 height 应该要在的位置, 注意是目前 [0, result] 的范围， 因为后面还没有数字
            if (index < 0) {
                index = - index - 1;
            }
            f[index] = env[1]; // insert
            if (result == index) {
                result++;
            }
        }
        return result;
    }

    private class myComparator implements Comparator<int[]> {
        @Override
        public int compare(int[] o1, int[] o2) {
            if (o1[0] == o2[0]) {
                // if widths are the same, order height descendingly
                return Integer.compare(o2[1], o1[1]);
            } else {
                // ascending order of width
                return Integer.compare(o1[0], o2[0]);
            }
        }
    }

    private int method1(int[][] env) {
        // DP
        int n = env.length;
        Arrays.sort(env, new myComparator());
        // definition: f[i] = how many envelopes we can have at index i
        int[] f = new int[n];
        // initialization
        Arrays.fill(f, 1); // at lease has itself
        // DP
        int result = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (env[j][0] < env[i][0] && env[j][1] < env[i][1]) {
                    f[i] = Math.max(f[i], f[j] + 1);
                }
            }
            result = Math.max(result, f[i]);
        }
        // result
        return result;
    }
}

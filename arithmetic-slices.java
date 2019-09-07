// 413. Arithmetic Slices
// DescriptionHintsSubmissionsDiscussSolution
// A sequence of number is called arithmetic if it consists of at least three elements and if the difference between any two consecutive elements is the same.
//
// For example, these are arithmetic sequence:
//
// 1, 3, 5, 7, 9
// 7, 7, 7, 7
// 3, -1, -5, -9
// The following sequence is not arithmetic.
//
// 1, 1, 2, 5, 7
//
// A zero-indexed array A consisting of N numbers is given. A slice of that array is any pair of integers (P, Q) such that 0 <= P < Q < N.
//
// A slice (P, Q) of array A is called arithmetic if the sequence:
// A[P], A[p + 1], ..., A[Q - 1], A[Q] is arithmetic. In particular, this means that P + 1 < Q.
//
// The function should return the number of arithmetic slices in the array A.
//
//
// Example:
//
// A = [1, 2, 3, 4]
//
// return: 3, for 3 arithmetic slices in A: [1, 2, 3], [2, 3, 4] and [1, 2, 3, 4] itself.


class Solution {
    public int numberOfArithmeticSlices(int[] A) {
        if (A == null || A.length < 3) {
            return 0;
        }
//         method 1: it's very straightforward and brute, with time of O(n ^ 2)
        // return method_try(A);

//         method 2: DP
        // return method2_DP(A);

//         method 3: DP with one variable
        // return method3_DP_variable(A);

//         method 4: use formula to calculate the sum of arithmetic sequence, another style of method 3
        return method4_formula(A);
    }
    private int method_try(int[] nums) {
        int n = nums.length;
        int count = 0;
        for (int i = 0; i <= n - 3; i++) {
            if (nums[i + 1] - nums[i] == nums[i + 2] - nums[i + 1]) {
                count++;
                int diff = nums[i + 1] - nums[i];
                for (int j = i + 3; j < n; j++) {
                    if (nums[j] - nums[j - 1] == diff) {
                        count++;
                    } else {
                        break;
                    }
                }
            }
        }
        return count;
    }
    private int method2_DP(int[] nums) {
        int n = nums.length;
        int[] f = new int[n]; // f[i] means how many pairs from [0, i];
        // initialization: first 2 are just 0 since we need length of 3 at least
        // DP
        for (int i = 2; i < n; i++) {
            if (nums[i - 1] - nums[i - 2] == nums[i] - nums[i - 1]) {
                f[i] = f[i - 1] + 1;
            }
        }
        // result
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += f[i];
        }
        return sum;
    }
    private int method3_DP_variable(int[] nums) {
        int n = nums.length;
        int local = 0;
        int global = 0;
        for (int i = 2; i < n; i++) {
            if (nums[i - 1] - nums[i - 2] == nums[i] - nums[i - 1]) {
                local++;
                // 这里直接加， 其实数组DP时候我们也可以这么做， 即每得到一个 f[i] 就可以加入 sum 中而不需要最后 for 一遍求 sum， 是因为每加一个数在后面成为等差数列的一员， 前面的那些个数都可以扩展这么多到新的数， 所以总的可以加这么多，具体数学见 ref
                global += local;
            } else {
                local = 0;
            }
        }
        return global;
    }
    private int method4_formula(int[] nums) {
        // 从 method 3 中可以看出， 最后在算 global 的时候， 其实就是算等差数列的和， 所以可以换成用公式求和， 本质上和 3 没太大差别
        int n = nums.length;
        int local = 0;
        int global = 0;
        for (int i = 2; i < n; i++) {
            if (nums[i - 1] - nums[i - 2] == nums[i] - nums[i - 1]) {
                local++;
            } else {
                global += local * (local + 1) / 2;
                local = 0;
            }
        }
        global += local * (local + 1) / 2; // since we sum up global when it does not meet the if condition, don't forget to calculate the last result
        return global;
    }
}

// ref: https://leetcode.com/articles/arithmetic-slices/

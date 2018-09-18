// 896. Monotonic Array
// User Accepted: 2111
// User Tried: 2152
// Total Accepted: 2167
// Total Submissions: 3356
// Difficulty: Easy
// An array is monotonic if it is either monotone increasing or monotone decreasing.
//
// An array A is monotone increasing if for all i <= j, A[i] <= A[j].  An array A is monotone decreasing if for all i <= j, A[i] >= A[j].
//
// Return true if and only if the given array A is monotonic.
//
//
//
// Example 1:
//
// Input: [1,2,2,3]
// Output: true
// Example 2:
//
// Input: [6,5,4,4]
// Output: true
// Example 3:
//
// Input: [1,3,2]
// Output: false
// Example 4:
//
// Input: [1,2,4,5]
// Output: true
// Example 5:
//
// Input: [1,1,1]
// Output: true
//
//
// Note:
//
// 1 <= A.length <= 50000
// -100000 <= A[i] <= 100000


class Solution {
    public boolean isMonotonic(int[] A) {
        if (A == null || A.length <= 2) {
            return true;
        }

        // return mytry(A);

        // return method1(A);

        return method2(A);
    }

    private boolean method2(int[] A) {
        // same idea but little bit different operation
        // ref: https://leetcode.com/problems/monotonic-array/discuss/165889/C++JavaPython-One-Pass-O(N)
        boolean inc = true, dec = true;
        for (int i = 1; i < A.length; ++i) {
            inc &= A[i - 1] <= A[i];
            dec &= A[i - 1] >= A[i];
        }
        return inc || dec;
    }

    private boolean method1(int[] nums) {
        // 为了避免同时存在增减的情况并且只能看到局部的错误， 应该要同时考虑递增顺序和递减顺序， 最后判断是否只存在其中一种
        boolean asc = true;
        boolean desc = true;
        // 如果按照 “符合要求” 来判断， 那么同时存在递增和递减， 都会为 true， 所以要反向记录， 当递增的时候， 递减的状态就为 false， 即 nums 是非递减的
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                desc = false;
            }
            if (nums[i] < nums[i - 1]) {
                asc = false;
            }
        }
        return asc || desc;
    }

    private boolean mytry(int[] nums) {
        // wrong!!! 这样做只能看到局部的变化， 如果是先增后减就没办法判断了
        int n = nums.length;
        // 并不能考虑局部的 nums[i] is a peak or valley， 可能会同时存在
        boolean isAsc = (nums[1] >= nums[0]);
        for (int i = 2; i < n; i++) {
            if (isAsc) {
                if (nums[i] < nums[i - 1]) {
                    return false;
                }
            } else {
                if (nums[i] > nums[i - 1]) {
                    return false;
                }
            }
        }
        return true;
    }
}

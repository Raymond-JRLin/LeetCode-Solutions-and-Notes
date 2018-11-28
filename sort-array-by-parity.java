// 905. Sort Array By Parity
// DescriptionHintsSubmissionsDiscussSolution
// Given an array A of non-negative integers, return an array consisting of all the even elements of A, followed by all the odd elements of A.
//
// You may return any answer array that satisfies this condition.
//
//
//
// Example 1:
//
// Input: [3,1,2,4]
// Output: [2,4,3,1]
// The outputs [4,2,3,1], [2,4,1,3], and [4,2,1,3] would also be accepted.
//
//
// Note:
//
// 1 <= A.length <= 5000
// 0 <= A[i] <= 5000


class Solution {
    public int[] sortArrayByParity(int[] A) {
        if (A == null || A.length == 0) {
            return A;
        }

        return mytry(A);
    }

    private int[] mytry(int[] nums) {
        // 2 pointers
        int i = 0;
        int j = nums.length - 1;
        while (i < j) {
            while (i < j && nums[i] % 2 == 0) {
                i++;
            }
            while (i < j && nums[j] % 2 == 1) {
                j--;
            }
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
        return nums;
    }
}

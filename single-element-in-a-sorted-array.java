// 540. Single Element in a Sorted Array
// DescriptionHintsSubmissionsDiscussSolution
// Given a sorted array consisting of only integers where every element appears exactly twice except for one element which appears exactly once. Find this single element that appears only once.
//
//
//
// Example 1:
//
// Input: [1,1,2,3,3,4,4,8,8]
// Output: 2
// Example 2:
//
// Input: [3,3,7,7,10,11,11]
// Output: 10
//
//
// Note: Your solution should run in O(log n) time and O(1) space.
//
// Seen this question in a real interview before?
// Difficulty:Medium


class Solution {
    public int singleNonDuplicate(int[] nums) {
//         method 1: bit manipulation
        // return method1_Bit(nums);

//         method 2: if we want O(log n) then there is only way to do that - Binary Search
//         这里很巧妙， 因为如果是重复数， 那么它们一定是成双成对出现， 而且还是 sorted array， 我们可以比较它们的 index 看看前后是否一样， 以此来判断 single number 存在的区间
        return method2_BS(nums);
    }
    private int method1_Bit(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int sum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sum ^= nums[i];
        }
        return sum;
    }
    private int method2_BS(int[] nums) {
        int start = 0;
        int end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (mid % 2 == 1) {
                mid--; // 让 mid 始终在偶数位置上， 去比较它和下一位是否是一样的一对
            }
            if (nums[mid] != nums[mid + 1]) {
                end = mid; // single number 一定在前半部分
            } else {
                start = mid + 2; // single number 一定在后半部分， 此时 + 2 是因为 mid 在偶数位置， 下一个和它一样， 跳过
            }
        }
        // 举例子可以发现不管怎样， 最后 single number 一定会在 start 的位置上
        return nums[start];
    }
}

// 462. Minimum Moves to Equal Array Elements II
// DescriptionHintsSubmissionsDiscussSolution
// Given a non-empty integer array, find the minimum number of moves required to make all array elements equal, where a move is incrementing a selected element by 1 or decrementing a selected element by 1.
//
// You may assume the array's length is at most 10,000.
//
// Example:
//
// Input:
// [1,2,3]
//
// Output:
// 2
//
// Explanation:
// Only two moves are needed (remember each move increments or decrements one element):
//
// [1,2,3]  =>  [2,2,3]  =>  [2,2,2]
// Seen this question in a real interview before?
// Difficulty:Medium


class Solution {
    public int minMoves2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

//         method 1: sort and use the middle as the final value, count diff - O(nlogn) for sorting
        // return sort_middle(nums);

//         method 2: use quick select to do the sorting and count diff against middle - O(n)
        return method2_quickSelect(nums);
    }
    private int sort_middle(int[] nums) {
        Arrays.sort(nums);
        int mid = nums[nums.length / 2];
        int result = 0;
        for (int num : nums) {
            result += Math.abs(num - mid);
        }
        // or we can do diff from 2 end points
        // int i = 0;
        // int j = nums.length - 1;
        // while (i < j) {
        //     result += nums[j--] - nums[i++];
        // }
        return result;
    }
    private int method2_quickSelect(int[] nums) {
        int n = nums.length;
        int mid = quick_select(nums, 0, n - 1, n / 2 + 1);
        int result = 0;
        for (int num : nums) {
            result += Math.abs(num - mid);
        }
        return result;
    }
    private int quick_select(int[] nums, int start, int end, int k) {
        if (start >= end) {
            return nums[start];
        }
        int left = start;
        int right = end;
        int pivot = nums[start + (end - start) / 2];
        while (left <= right) {
            while (left <= right && nums[left] < pivot) {
                left++;
            }
            while (left <= right && nums[right] > pivot) {
                right--;
            }
            if (left <= right) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
                left++;
                right--;
            }
        }
        if (start + k - 1 <= right) {
            return quick_select(nums, start, right, k);
        } else if (start + k - 1 >= left) {
            return quick_select(nums, left, end, k - (left - start));
        } else {
            return nums[right + 1];
        }
    }
    // private int partition(int[] nums, int start, int end) {
    //     int left = start;
    //     int right = end;
    //     int pivot = nums[left];
    //     while (left < right) {
    //         while (left < right && nums[right] >= pivot) {
    //             right--;
    //         }
    //         nums[left] = nums[right];
    //         while (left < right && nums[left] <= pivot) {
    //             left++;
    //         }
    //         nums[right] = nums[left];
    //     }
    //     nums[left] = pivot;
    //     return left;
    // }
}

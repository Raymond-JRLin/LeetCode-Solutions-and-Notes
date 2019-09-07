// 268. Missing Number
// DescriptionHintsSubmissionsDiscussSolution
// Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.
//
// Example 1:
//
// Input: [3,0,1]
// Output: 2
// Example 2:
//
// Input: [9,6,4,2,3,5,7,0,1]
// Output: 8
// Note:
// Your algorithm should run in linear runtime complexity. Could you implement it using only constant extra space complexity?
//
// Seen this question in a real interview before?
// Difficulty:Easy


class Solution {
    public int missingNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        // O(nlogn): just sort and check one by one
        // return my_try(nums);

        // bucket sort 把数都交换到它们应该去的地方 - O(n) time and O(1) space
        // return method2_bucket(nums);

        // we can also do math: calculate the supposed sum and minus each value
        // return method3_math(nums);

        // sort and BS: similar to the 1st method, but do BS instead of for loop - O(nlogn) time
        // return method4_bs(nums);

        // bit manipulation: 既然 i 和 nums[i] 要一样， 那么我们把 i 和 nums[i] 拿去 xor 那么剩下的数就是那个缺失的
        return method5_xor(nums);
    }

    private int method5_xor(int[] nums) {
        int sum = nums.length; // set n = length as default of missing number
        for (int i = 0; i < nums.length; i++) {
            sum ^= i;
            sum ^= nums[i];
        }
        return sum;
    }

    private int method4_bs(int[] nums) {
        Arrays.sort(nums);
        int start = 0;
        int end = nums.length;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] > mid) {
                // 缺少的那个数在前半部分
                // 注意一定是 > mid 才能把 end 往前移， 因为有可能缺失的数是 n - nums.length， 此时需要把 start 不断后移
                end = mid;
            } else {
                start = mid;
            }
        }
        if (nums[start] != start) {
            return start;
        } else {
            return end;
        }
    }

    private int method3_math(int[] nums) {
        int n = nums.length;
        int sum = n * (n + 1) / 2;
        for (int num : nums) {
            sum -= num;
        }
        return sum;
    }

    private int method2_bucket(int[] nums) {
        int i = 0;
        int n = nums.length;
        while (i < n) {
            while (i < n && nums[i] < n && nums[i] != i) {
                // 要用 while 而不是一次的 if， 因为换过来的值有可能也不应该是在这个位置上， 要进一步 swap
                int temp = nums[nums[i]];
                nums[nums[i]] = nums[i];
                nums[i] = temp;
            }
            i++;
        }
        for (int j = 0; j < n; j++) {
            if (nums[j] != j) {
                return j;
            }
        }
        return n;
    }

    private int my_try(int[] nums) {
        // very straitforward: sort and chcek
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i) {
                return i;
            }
        }
        return nums.length;
    }
}

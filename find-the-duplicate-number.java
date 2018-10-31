// 287. Find the Duplicate Number
// DescriptionHintsSubmissionsDiscussSolution
// Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), prove that at least one duplicate number must exist. Assume that there is only one duplicate number, find the duplicate one.
//
// Example 1:
//
// Input: [1,3,4,2,2]
// Output: 2
// Example 2:
//
// Input: [3,1,3,4,2]
// Output: 3
// Note:
//
// You must not modify the array (assume the array is read only).
// You must use only constant, O(1) extra space.
// Your runtime complexity should be less than O(n2).
// There is only one duplicate number in the array, but it could be repeated more than once.


class Solution {
    public int findDuplicate(int[] nums) {

        // return brute(nums);

        // return method2(nums);

        // return method3(nums);

        return method4(nums);
    }

    private int method4(int[] nums) {
        // 2 pointer to find cycle
        int slow = nums[0];
        int fast = nums[nums[0]];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }
        fast = 0;
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow; // return slow/fast rather than nums[slow], because we already catch nums[slow] in while loop, and then they equal and jump out of loop, so they already mean the value rather than index
    }

    private int method3(int[] nums) {
        // BS: O(NlogN) time
        int n = nums.length;
        int start = 1;
        int end = n - 1;
        while (start < end) {
            int mid = start + (end - start) / 2;
            int count = 0;
            for (int num : nums) {
                if (num <= mid) {
                    count++;
                }
            }
            if (count > mid) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return start;
    }

    private int method2(int[] nums) {
        // sorting: O(NlogN) time, O(1)/O(N) space, if modify then O(1), if not, use extra O(N) to copy array
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                return nums[i];
            }
        }
        return -1;
    }

    private int brute(int[] nums) {
        // O(N) time and space
        int n = nums.length;
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (!set.add(num)) {
                return num;
            }
        }
        return -1;
    }
}

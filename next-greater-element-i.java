// 496. Next Greater Element I
// DescriptionHintsSubmissionsDiscussSolution
// You are given two arrays (without duplicates) nums1 and nums2 where nums1’s elements are subset of nums2. Find all the next greater numbers for nums1's elements in the corresponding places of nums2.
//
// The Next Greater Number of a number x in nums1 is the first greater number to its right in nums2. If it does not exist, output -1 for this number.
//
// Example 1:
// Input: nums1 = [4,1,2], nums2 = [1,3,4,2].
// Output: [-1,3,-1]
// Explanation:
//     For number 4 in the first array, you cannot find the next greater number for it in the second array, so output -1.
//     For number 1 in the first array, the next greater number for it in the second array is 3.
//     For number 2 in the first array, there is no next greater number for it in the second array, so output -1.
// Example 2:
// Input: nums1 = [2,4], nums2 = [1,2,3,4].
// Output: [3,-1]
// Explanation:
//     For number 2 in the first array, the next greater number for it in the second array is 3.
//     For number 4 in the first array, there is no next greater number for it in the second array, so output -1.
// Note:
// All elements in nums1 and nums2 are unique.
// The length of both nums1 and nums2 would not exceed 1000.
// Seen this question in a real interview before?
// Difficulty:Easy


class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0) {
            return nums1;
        }

//         对 nums1 中每一个数去找 nums2 中的 index， 然后遍历查找第一个大的数 - O(n ^ 2)
        // return my_try(nums1, nums2);

//         个人一点想法： 要比 O(n ^ 2) 更快， 有 O(nlogn)/O(n)/O(logn), logn 在搜索中一般是 BS， 但是这题和 index 相关， 显然不能这么做， 那么就可以试试看如何做到 O(n), 也就意味着两个数组都只能分开 for 一次， 那么要么使用一些 trick， 要么使用一些数据结构， 能够记下之前 for 的结果， 之后可以在 O(1) 的时间内直接查到最终的结果
//         看了答案之后发现这种比大小的通常使用 stack 来做
        return method2_stack(nums1, nums2);
    }

    private int[] method2_stack(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>(); // <x, next greater of x>
        Stack<Integer> stack = new Stack<>();
        for (int num : nums2) {
            while (!stack.isEmpty() && num > stack.peek()) {
                map.put(stack.pop(), num);
            }
            stack.push(num);
        }
        int[] result = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            if (map.containsKey(nums1[i])) {
                result[i] = map.get(nums1[i]);
            } else {
                result[i] = -1;
            }
        }
        return result;
    }

    private int[] my_try(int[] nums1, int[] nums2) {
        int[] result = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            // because there is no duplicates, so we can search for index and traverse its right part
            int index = getIndex(nums2, nums1[i]);
            for (int j = index + 1; j < nums2.length; j++) {
                if (nums2[j] > nums1[i]) {
                    result[i] = nums2[j];
                    break;
                }
            }
            if (result[i] == 0) {
                result[i] = -1;
            }
        }
        return result;
    }
    private int getIndex(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                return i;
            }
        }
        return -1;
    }
}

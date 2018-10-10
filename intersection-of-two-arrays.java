// 349. Intersection of Two Arrays
// DescriptionHintsSubmissionsDiscussSolution
// Given two arrays, write a function to compute their intersection.
//
// Example 1:
//
// Input: nums1 = [1,2,2,1], nums2 = [2,2]
// Output: [2]
// Example 2:
//
// Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
// Output: [9,4]


class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) {
            return new int[0];
        }

        // return mytry(nums1, nums2);

        // return method2(nums1, nums2);

        // return method3(nums1, nums2);

        return method4(nums1, nums2);
    }


    private int[] method4(int[] nums1, int[] nums2) {
        // another BS: sort both arrays, so when we do BS we can drop previous half we just searched
        int n = nums1.length;
        int m = nums2.length;
        if (n > m) {
            return method4(nums2, nums1);
        }
        Set<Integer> set = new HashSet<>();
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int start = 0;
        int end = nums2.length;
        for (int num : nums1) {
            int index = Arrays.binarySearch(nums2, start, end, num);
            if (index >= 0) {
                // found the target
                set.add(num);
                start = index + 1;
            } else {
                // no such target in nums2, return -index - 1
                index = - (index + 1); // index of just greater than the target
                if (index >= end) {
                    break;
                }
                start = index;
            }
        }
        return set.stream().mapToInt(Number :: intValue).toArray();
    }


    private int[] method3(int[] nums1, int[] nums2) {
        // BS: suppose n <= m, O(mlogm + nlogm) time
        int n = nums1.length;
        int m = nums2.length;
        if (n > m) {
            return method3(nums2, nums1);
        }
        Set<Integer> set = new HashSet<>();
        Arrays.sort(nums2);
        for (int num : nums1) {
            if (bs(nums2, num)) {
                set.add(num);
            }
        }
        int[] result = new int[set.size()];
        int i = 0;
        for (int num : set) {
            result[i++] = num;
        }
        return result;
    }
    private boolean bs(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                return true;
            } else if (nums[mid] < target) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (nums[start] == target || nums[end] == target) {
            return true;
        } else {
            return false;
        }
    }

    private int[] method2(int[] nums1, int[] nums2) {
        // 2 pointers: suppose n > m, O(nlogn) time (for bs: O(mlogn)) and O(m) space
        int n = nums1.length;
        int m = nums2.length;
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        Set<Integer> set = new HashSet<>();
        int i = 0;
        int j = 0;
        while (i < n && j < m) {
            if (nums1[i] == nums2[j]) {
                set.add(nums1[i]);
                i++;
                j++;
            } else if (nums1[i] < nums2[j]) {
                i++;
            } else {
                j++;
            }
        }
        int[] result = new int[set.size()];
        int index = 0;
        for (int num : set) {
            result[index++] = num;
        }
        return result;
    }

    private int[] mytry(int[] nums1, int[] nums2) {
        // use HashSet: O(n + m) time and O(Min{n, m}) space
        int n = nums1.length;
        int m = nums2.length;
        if (n > m) {
            return mytry(nums2, nums1);
        }
        Set<Integer> set = new HashSet<>();
        for (int num : nums1) {
            set.add(num);
        }
        Set<Integer> count = new HashSet<>();
        for (int num : nums2) {
            if (set.contains(num)) {
                count.add(num);
            }
        }
        int[] result = new int[count.size()];
        int i = 0;
        for (int num : count) {
            result[i++] = num;
        }
        return result;
    }
}

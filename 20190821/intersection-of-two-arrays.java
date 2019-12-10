// 349. Intersection of Two Arrays
// DescriptionHintsSubmissionsDiscussSolution
//
// Given two arrays, write a function to compute their intersection.
//
// Example 1:
//
// Input: nums1 = [1,2,2,1], nums2 = [2,2]
// Output: [2]
//
// Example 2:
//
// Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
// Output: [9,4]
//
// Note:
//
//     Each element in the result must be unique.
//     The result can be in any order.
//
//
// Seen this question in a real interview before?
//
//     Difficulty:Easy


class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) {
            return new int[0];
        }

        // return mytry(nums1, nums2);

        // return method2(nums1, nums2);

        return method3(nums1, nums2);
    }

    private int[] method3(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int i = 0, j = 0;
        int n = nums1.length;
        int m = nums2.length;
        int index = 0;
        int[] result = new int[Math.max(n, m)];

        while (i < n && j < m) {
            while (i < n - 1 && nums1[i] == nums1[i + 1]) {
                i++;
            }
            while (j < m - 1 && nums2[j] == nums2[j + 1]) {
                j++;
            }
            if (nums1[i] == nums2[j]) {
                result[index++] = nums1[i];
                i++;
                j++;
            } else if (nums1[i] < nums2[j]) {
                i++;
            } else {
                j++;
            }
        }
        return Arrays.copyOf(result, index);
    }

    private int[] method2(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums1) {
            set.add(num);
        }
        Arrays.sort(nums2);
        int index = 0;
        int[] result = new int[Math.max(nums1.length, nums2.length)];
        for (int i = 0; i < nums2.length; i++) {
            if (i > 0 && nums2[i - 1] == nums2[i]) {
                continue;
            }
            if (set.contains(nums2[i])) {
                result[index++] = nums2[i];
            }
        }
        return Arrays.copyOf(result, index);
    }

    private int[] mytry(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums1) {
            set.add(num);
        }
        List<Integer> list = new ArrayList<>();
        for (int num : nums2) {
            if (set.contains(num)) {
                list.add(num);
                set.remove(num);
            }
        }
        int n = list.size();
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            result[i] = list.get(i);
        }
        return result;
    }
}

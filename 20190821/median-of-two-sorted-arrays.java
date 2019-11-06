// 4. Median of Two Sorted Arrays
// DescriptionHintsSubmissionsDiscussSolution
//
// There are two sorted arrays nums1 and nums2 of size m and n respectively.
//
// Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
//
// You may assume nums1 and nums2 cannot be both empty.
//
// Example 1:
//
// nums1 = [1, 3]
// nums2 = [2]
//
// The median is 2.0
//
// Example 2:
//
// nums1 = [1, 2]
// nums2 = [3, 4]
//
// The median is (2 + 3)/2 = 2.5
//
// Seen this question in a real interview before?
//
//     Difficulty:Hard


class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        return method1(nums1, nums2);
    }

    private double method1(int[] nums1, int[] nums2) {
        // ref: https://www.youtube.com/watch?v=LPFhl65R7ww
        int n1 = nums1.length;
        int n2 = nums2.length;
        if (n1 > n2) {
            return method1(nums2, nums1);
        }

        int start = 0;
        int end = n1;
        while (start <= end) {
            int mid1 = start + (end - start) / 2; // partition nums1
            // mid1 指的是 nums1 左边要有多少个， 因为我们在 mid1 这里 cut 分两边
            // 所以相当于 end index (exclusive), 所以开始时 end 是 n1
            // 通过 partition nums1， nums2 对应的划分， 每次总是使得左右两边总个数相等
            int mid2 = (n1 + n2 + 1) / 2 - mid1; // + 1 为了解决长度和为 odd 以及 even， odd + 1 会使得左边多一个
            // 统一分成左右两边
            int maxLeft1 = mid1 == 0 ? Integer.MIN_VALUE : nums1[mid1 - 1];
            int minRight1 = mid1 == n1 ? Integer.MAX_VALUE : nums1[mid1];

            int maxLeft2 = mid2 == 0 ? Integer.MIN_VALUE : nums2[mid2 - 1];
            int minRight2 = mid2 == n2 ? Integer.MAX_VALUE : nums2[mid2];

            // start/end 是 nums1 的， 所以没找到需要移动的情况， 只看 nums1 来决定怎么移
            if (maxLeft1 <= minRight2 && minRight1 >= maxLeft2) {
                // 找到了， 此时两个数组， 左边所有的数都小于等于右边的数
                // 总长度是偶数， 答案为 Average{MAX{左边}, MIN{右边}}
                if ((n1 + n2) % 2 == 0) {
                    return (double) (Math.max(maxLeft1, maxLeft2) + Math.min(minRight1, minRight2)) / 2.0;
                } else {
                    // 总长度是奇数， 答案为 MAX{左边}, 因为左边多一个
                    return (double) Math.max(maxLeft1, maxLeft2);
                }
            } else if (maxLeft1 > minRight2) {
                // nums1 左边多了一点， 向左移
                end = mid1 - 1;
            } else {
                // nums1 右边多了一点， 向右移
                start = mid1 + 1;
            }
        }
        return -1;
    }
}

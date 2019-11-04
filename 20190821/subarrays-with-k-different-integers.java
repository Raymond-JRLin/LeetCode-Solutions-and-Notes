// 992. Subarrays with K Different Integers
// DescriptionHintsSubmissionsDiscussSolution
//
// Given an array A of positive integers, call a (contiguous, not necessarily distinct) subarray of A good if the number of different integers in that subarray is exactly K.
//
// (For example, [1,2,3,1,2] has 3 different integers: 1, 2, and 3.)
//
// Return the number of good subarrays of A.
//
//
//
// Example 1:
//
// Input: A = [1,2,1,2,3], K = 2
// Output: 7
// Explanation: Subarrays formed with exactly 2 different integers: [1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2].
//
// Example 2:
//
// Input: A = [1,2,1,3,4], K = 3
// Output: 3
// Explanation: Subarrays formed with exactly 3 different integers: [1,2,1,3], [2,1,3], [1,3,4].
//
//
//
// Note:
//
//     1 <= A.length <= 20000
//     1 <= A[i] <= A.length
//     1 <= K <= A.length
//
// Seen this question in a real interview before?
//
//     Difficulty:Hard


class Solution {
    public int subarraysWithKDistinct(int[] A, int K) {
        if (A == null || A.length < K) {
            return 0;
        }

        return mytry(A, K);
    }

    private int mytry(int[] nums, int k) {
        return atMostDiffK(nums, k) - atMostDiffK(nums, k - 1);
    }
    private int atMostDiffK(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>(); // <num, freq>
        int left = 0;
        int result = 0;
        for (int right = 0; right < nums.length; right++) {
            map.put(nums[right], map.getOrDefault(nums[right], 0) + 1);
            while (map.size() > k) {
                map.put(nums[left], map.get(nums[left]) - 1);
                if (map.get(nums[left]) == 0) {
                    map.remove(nums[left]);
                }
                left++;
            }
            result += right + 1 - left;
        }
        return result;
    }
}

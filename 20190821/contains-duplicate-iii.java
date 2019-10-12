// 220. Contains Duplicate III
// DescriptionHintsSubmissionsDiscussSolution
//
// Given an array of integers, find out whether there are two distinct indices i and j in the array such that the absolute difference between nums[i] and nums[j] is at most t and the absolute difference between i and j is at most k.
//
// Example 1:
//
// Input: nums = [1,2,3,1], k = 3, t = 0
// Output: true
//
// Example 2:
//
// Input: nums = [1,0,1,1], k = 1, t = 2
// Output: true
//
// Example 3:
//
// Input: nums = [1,5,9,1,5,9], k = 2, t = 3
// Output: false
//
// Seen this question in a real interview before?
//
//     Difficulty:Medium


class Solution {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (nums == null) {
            return false;
        }
        if (t < 0) {
            return false;
        }
        if (k < 1) {
            return false;
        }

        // return mytry(nums, k, t);

        return method2(nums, k, t);
    }

    private boolean method2(int[] nums, int k, int t) {
        // 用 bucket， 把 nums[i] 按照 t 的 range map 到 bucket 中
        // 比如 t == 3， [0, 1, 2] 可以 map 到同一个 bucket 中, 因为 num / 3 == 0
        Map<Long, Long> map = new HashMap<>(); // <bucket, num / #bucket>
        for (int i = 0; i < nums.length; i++) {
            long curr = (long) nums[i] - Integer.MIN_VALUE;
            long bucket = curr / (t + 1);
            // into the same bucket, within t range
            if (map.containsKey(bucket)) {
                return true;
            }
            // check prev bucket, e.g. [1, 2, 3], [4, 5, 6] => 3 和 4 依然算是 t 范围内的 duplicate
            if (map.containsKey(bucket - 1) && Math.abs(map.get(bucket - 1) - curr) <= t) {
                return true;
            }
            // similar to check next bucket
            if (map.containsKey(bucket + 1) && Math.abs(map.get(bucket + 1) - curr) <= t) {
                return true;
            }
            // remove previous bucket if their index difference >= k (0-based index)
            if (i >= k) {
                long prev = (long) nums[i - k] - Integer.MIN_VALUE;
                long prevBucket = prev / (t + 1);
                if (map.containsKey(prevBucket)) {
                    map.remove(prevBucket);
                }
            }
            map.put(bucket, curr);
        }
        return false;
    }

    private boolean mytry(int[] nums, int k, int t) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (j - i > k) {
                    break;
                }
                if (Math.abs((long) nums[i] - (long) nums[j]) <= t) {
                    return true;
                }
            }
        }
        return false;
    }
}

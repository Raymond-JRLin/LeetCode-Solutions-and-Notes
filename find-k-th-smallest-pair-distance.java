// 719. Find K-th Smallest Pair Distance
// DescriptionHintsSubmissionsDiscussSolution
// Given an integer array, return the k-th smallest distance among all the pairs. The distance of a pair (A, B) is defined as the absolute difference between A and B.
//
// Example 1:
// Input:
// nums = [1,3,1]
// k = 1
// Output: 0
// Explanation:
// Here are all the pairs:
// (1,3) -> 2
// (1,1) -> 0
// (3,1) -> 2
// Then the 1st smallest distance pair is (1,1), and its distance is 0.
// Note:
// 2 <= len(nums) <= 10000.
// 0 <= nums[i] < 1000000.
// 1 <= k <= len(nums) * (len(nums) - 1) / 2.


class Solution {
    public int smallestDistancePair(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        // return mytry(nums, k);

        // return method1(nums, k);

        return method2(nums, k);
    }

    private int method2(int[] nums, int k) {
        // we can do further optimization when we count how many pairs <= mid, instead we can find the index of the value that is just larger than mid
        int n = nums.length;
        Arrays.sort(nums);
        int lo = nums[1] - nums[0];
        for (int i = 1; i < n - 1; i++) {
            lo = Math.min(lo, nums[i + 1] - nums[i]);
        }
        int hi = nums[n - 1] - nums[0];
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            int num = countPairs2(nums, mid); // # pair diff <= mid
            if (num < k) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return lo;
    }
    private int countPairs2(int[] nums, int target) {
        int n = nums.length;
        int result = 0;
        for (int i = 0; i < n; i++) {
            int num = nums[i] + target; // the actually "target"
            int j = upperBound(nums, i + 1, n - 1, num); // return the index of the 1st elements whose value is larger than num
            result += j - i - 1; // - 1 because when go out of while loop j is ++ 1 more
        }
        return result;
    }
    private int upperBound(int[] nums, int start, int end, int target) {
        if (nums[end] <= target) {
            // target exceeds the largest value
            return end + 1;
        }
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] <= target) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        return start;
    }

    private int method1(int[] nums, int k) {
        // 既然 O(n ^ 2) 超时， 那么可以尝试 O(nlogn), 也就是把第二个 n 变成 logn， 那么就要考虑BS， 如何 BS 是关键。
        // 这题是找 k-th 小的【差值】， 所以对差值进行 BS， 也就是 BS 结果： 找到最小和最大的差值， 取一半， 看看一半的数量是否有 k 个
        int n = nums.length;
        Arrays.sort(nums);
        int min = nums[1] - nums[0];
        for (int i = 1; i < n - 1; i++) {
            min = Math.min(min, nums[i + 1] - nums[i]);
        }
        int max = nums[n - 1] - nums[0];
        while (min < max) {
            int mid = min + (max - min) / 2;
            int num = countPairs(nums, mid); // # pair diff <= mid
            if (num < k) {
                min = mid + 1;
            } else {
                max = mid;
            }
        }
        return min;
    }
    private int countPairs(int[] nums, int target) {
        int n = nums.length;
        int result = 0;
        for (int i = 0; i < n; i++) {
            int j = i + 1;
            while (j < n && nums[j] - nums[i] <= target) {
                j++;
            }
            result += j - i - 1; // - 1 because when go out of while loop j is ++ 1 more
        }
        return result;
    }

    private int mytry(int[] nums, int k) {
        // TLE: O(n ^ 2)
        int n = nums.length;
        Arrays.sort(nums);
        PriorityQueue<Integer> pq = new PriorityQueue<>(k, new Comparator<Integer>() {
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                // 不能去重， 因为都要算进去
                if (j - i > k) {
                    break;
                }
                int diff = nums[j] - nums[i];
                pq.offer(diff);
                if (pq.size() > k) {
                    pq.poll();
                }
            }
        }
        return pq.peek();
    }
}

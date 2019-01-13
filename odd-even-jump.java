// 975. Odd Even Jump
// User Accepted: 284
// User Tried: 513
// Total Accepted: 305
// Total Submissions: 827
// Difficulty: Hard
// You are given an integer array A.  From some starting index, you can make a series of jumps.  The (1st, 3rd, 5th, ...) jumps in the series are called odd numbered jumps, and the (2nd, 4th, 6th, ...) jumps in the series are called even numbered jumps.
//
// You may from index i jump forward to index j (with i < j) in the following way:
//
// During odd numbered jumps (ie. jumps 1, 3, 5, ...), you jump to the index j such that A[i] <= A[j] and A[j] is the smallest possible value.  If there are multiple such indexes j, you can only jump to the smallest such index j.
// During even numbered jumps (ie. jumps 2, 4, 6, ...), you jump to the index j such that A[i] >= A[j] and A[j] is the largest possible value.  If there are multiple such indexes j, you can only jump to the smallest such index j.
// (It may be the case that for some index i, there are no legal jumps.)
// A starting index is good if, starting from that index, you can reach the end of the array (index A.length - 1) by jumping some number of times (possibly 0 or more than once.)
//
// Return the number of good starting indexes.
//
//
//
// Example 1:
//
// Input: [10,13,12,14,15]
// Output: 2
// Explanation:
// From starting index i = 0, we can jump to i = 2 (since A[2] is the smallest among A[1], A[2], A[3], A[4] that is greater or equal to A[0]), then we can't jump any more.
// From starting index i = 1 and i = 2, we can jump to i = 3, then we can't jump any more.
// From starting index i = 3, we can jump to i = 4, so we've reached the end.
// From starting index i = 4, we've reached the end already.
// In total, there are 2 different starting indexes (i = 3, i = 4) where we can reach the end with some number of jumps.
// Example 2:
//
// Input: [2,3,1,1,4]
// Output: 3
// Explanation:
// From starting index i = 0, we make jumps to i = 1, i = 2, i = 3:
//
// During our 1st jump (odd numbered), we first jump to i = 1 because A[1] is the smallest value in (A[1], A[2], A[3], A[4]) that is greater than or equal to A[0].
//
// During our 2nd jump (even numbered), we jump from i = 1 to i = 2 because A[2] is the largest value in (A[2], A[3], A[4]) that is less than or equal to A[1].  A[3] is also the largest value, but 2 is a smaller index, so we can only jump to i = 2 and not i = 3.
//
// During our 3rd jump (odd numbered), we jump from i = 2 to i = 3 because A[3] is the smallest value in (A[3], A[4]) that is greater than or equal to A[2].
//
// We can't jump from i = 3 to i = 4, so the starting index i = 0 is not good.
//
// In a similar manner, we can deduce that:
// From starting index i = 1, we jump to i = 4, so we reach the end.
// From starting index i = 2, we jump to i = 3, and then we can't jump anymore.
// From starting index i = 3, we jump to i = 4, so we reach the end.
// From starting index i = 4, we are already at the end.
// In total, there are 3 different starting indexes (i = 1, i = 3, i = 4) where we can reach the end with some number of jumps.
// Example 3:
//
// Input: [5,1,3,4,2]
// Output: 3
// Explanation:
// We can reach the end from starting indexes 1, 2, and 4.
//
//
// Note:
//
// 1 <= A.length <= 20000
// 0 <= A[i] < 100000


class Solution {
    public int oddEvenJumps(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }

        // return mytry(A);

        return method2(A);
    }

    private int method2(int[] nums){
        // 其实一看就是 DP 的题, O(NlogN) time and O(N) space
        int n = nums.length;
        // use larger and lower array to record if we can start from this index to get the end with corresponding jump type
        // the smallest larger number and largest smaller number -> TreeMap
        TreeMap<Integer, Integer> map = new TreeMap<>(); // <index, value>
        // definition: larger/smaller[i] = if index i can reach n - 1 by jump type of array's name
        boolean[] larger = new boolean[n];
        boolean[] smaller = new boolean[n];
        // initialization
        larger[n - 1] = smaller[n - 1] = true;
        map.put(nums[n - 1], n - 1);
        int result = 1;
        // DP: 和青蛙跳/reach end 什么的很像
        for (int i = n - 2; i >= 0; i--) {
            Integer high = map.ceilingKey(nums[i]);
            Integer low = map.floorKey(nums[i]);
            if (high != null) {
                larger[i] = smaller[map.get(high)];
            }
            if (low != null) {
                smaller[i] = larger[map.get(low)];
            }
            if (larger[i]) {
                result++;
            }
            map.put(nums[i], i);
        }
        // result
        return result;
    }

    private int mytry(int[] nums) {
        // DFS: O(N ^ 2) time and O(N) space
        int n = nums.length;
        // use map to record in advance
        Map<Integer, Integer> larger = new HashMap<>(); // <index, smallest larger number after index>
        Map<Integer, Integer> smaller = new HashMap<>(); // <index, smallest larger number after index>
        for (int i = 0; i < n - 1; i++) {
            int min = Integer.MIN_VALUE;
            int max = Integer.MAX_VALUE;
            int maxIndex = -1;
            int minIndex = -1;
            for (int j = i + 1; j < n; j++) {
                // larger one
                if (nums[j] >= nums[i] && nums[j] < max) {
                    max = nums[j];
                    maxIndex = j;
                }
                if (nums[j] <= nums[i] && nums[j] > min) {
                    min = nums[j];
                    minIndex = j;
                }
            }
            larger.put(i, maxIndex);
            smaller.put(i, minIndex);
            // System.out.println(nums[i] + " largest and smallest after is " + max + " at " + maxIndex + " and " + min + " at " + minIndex);
        }

        int result = 0;
        // record the index result, do pruning
        Map<Integer, Boolean> map = new HashMap<>(); // <index, result>
        // iterate from back to starting, then we can record those true position during the process we did. And then no need to add 1 when return, since we check the (n - 1) position
        for (int i = n - 1; i >= 0; i--) {
            if (dfs(larger, smaller, n, i, 1, map)) {
                // System.out.println(i + " is true");
                result++;
                map.put(i, true);
            }
        }
        return result;
        // for (int i = 0; i < n - 1; i++) {
        //     if (dfs(larger, smaller, n, i, 1, map)) {
        //         // System.out.println(i + " is true");
        //         result++;
        //         map.put(i, true);
        //     }
        // }
        // return result + 1;
    }
    private boolean dfs(Map<Integer, Integer> larger, Map<Integer, Integer> smaller, int n, int curr, int jump, Map<Integer, Boolean> map) {
        if (curr == n - 1) {
            return true;
        }
        // pruning
        if (map.containsKey(curr * jump)) {
            return map.get(curr * jump);
        }
        int next;
        if (jump == 1) {
            // odd jump
            next = larger.get(curr);
        } else {
            // even jump
            next = smaller.get(curr);
        }
        if (next == -1) {
            map.put(curr * jump, false);
            return false;
        }
        return dfs(larger, smaller, n, next, jump * -1, map);
    }
}

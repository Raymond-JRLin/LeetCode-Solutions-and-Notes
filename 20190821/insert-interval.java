// 57. Insert Interval
// DescriptionHintsSubmissionsDiscussSolution
// Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).
//
// You may assume that the intervals were initially sorted according to their start times.
//
// Example 1:
//
// Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
// Output: [[1,5],[6,9]]
// Example 2:
//
// Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
// Output: [[1,2],[3,10],[12,16]]
// Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
// NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.
//
// Seen this question in a real interview before?
// Difficulty:Hard


class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        if (intervals == null || intervals.length == 0) {
            return new int[][]{newInterval};
        }

        // return method1(intervals, newInterval);

        return method2(intervals, newInterval);
    }

    private int[][] method2(int[][] intervals, int[] newInterval) {
        // ref: https://leetcode.com/problems/insert-interval/discuss/21602/Short-and-straight-forward-Java-solution
        int n = intervals.length;
        List<int[]> list = new ArrayList<>();
        int i = 0;
        // straightforward
        // before newInterval
        while (i < n && intervals[i][1] < newInterval[0]) {
            list.add(intervals[i++]);
        }
        // overlapping with newInterval, 从上面 while 中出来就意味着 intervals[i][1] >= newInterval[0]
        while (i < n && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(intervals[i][0], newInterval[0]);
            newInterval[1] = Math.max(intervals[i][1], newInterval[1]);
            i++;
        }
        list.add(newInterval);
        // after newInterval
        while (i < n) {
            list.add(intervals[i++]);
        }

        int[][] result = new int[list.size()][2];
        for (int j = 0; j < list.size(); j++) {
            result[j] = list.get(j);
        }
        return result;
    }

    private int[][] method1(int[][] intervals, int[] newInterval) {
        // ref: comment in https://leetcode.com/problems/insert-interval/discuss/21602/Short-and-straight-forward-Java-solution
        List<int[]> list = new ArrayList<>();
        int[] prev = newInterval;
        for (int i = 0; i < intervals.length; i++) {
            int[] curr = intervals[i];
            if (curr[1] >= newInterval[0] && curr[0] <= newInterval[1]) {
                // overlapped
                prev[0] = Math.min(prev[0], curr[0]);
                prev[1] = Math.max(prev[1], curr[1]);
            } else if (curr[1] < prev[0]) {
                // curr, ..., prev
                list.add(curr);
            } else {
                // prev, ..., curr
                list.add(prev);
                prev = curr;
            }
        }
        list.add(prev);
        int[][] result = new int[list.size()][2];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
    }
}

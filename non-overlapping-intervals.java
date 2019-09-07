// 435. Non-overlapping Intervals
// DescriptionHintsSubmissionsDiscussSolution
// Given a collection of intervals, find the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.
//
//
//
// Example 1:
//
// Input: [[1,2],[2,3],[3,4],[1,3]]
// Output: 1
// Explanation: [1,3] can be removed and the rest of intervals are non-overlapping.
// Example 2:
//
// Input: [[1,2],[1,2],[1,2]]
// Output: 2
// Explanation: You need to remove two [1,2] to make the rest of intervals non-overlapping.
// Example 3:
//
// Input: [[1,2],[2,3]]
// Output: 0
// Explanation: You don't need to remove any of the intervals since they're already non-overlapping.
//
//
// Note:
//
// You may assume the interval's end point is always bigger than its start point.
// Intervals like [1,2] and [2,3] have borders "touching" but they don't overlap each other.
// Seen this question in a real interview before?
// Difficulty:Medium


/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
class Solution {
    public int eraseOverlapIntervals(Interval[] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }
        if (intervals.length == 1) {
            return 0;
        }

        // return my_try(intervals);

        return method2_greedy_variable(intervals);
    }
    private int my_try(Interval[] intervals) {
        Arrays.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return o1.end - o2.end;
            }
        });
        Stack<Interval> stack = new Stack<>();
        for (Interval interval : intervals) {
            if (stack.isEmpty()) {
                stack.push(interval);
            } else {
                Interval top = stack.peek();
                if (interval.start >= top.end) {
                    stack.push(interval);
                }
            }
        }
        return intervals.length - stack.size();
    }
    private int method2_greedy_variable(Interval[] intervals) {
        Arrays.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return o1.end - o2.end;
            }
        });
        int count = 0;
        Interval end = new Interval(0, Integer.MIN_VALUE);
        for (Interval interval : intervals) {
            if (interval.start >= end.end) {
                count++;
                end = interval;
            }
        }
        return intervals.length - count;
    }
}

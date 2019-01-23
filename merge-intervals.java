// 56. Merge Intervals
// DescriptionHintsSubmissionsDiscussSolution
// Given a collection of intervals, merge all overlapping intervals.
//
// Example 1:
//
// Input: [[1,3],[2,6],[8,10],[15,18]]
// Output: [[1,6],[8,10],[15,18]]
// Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
// Example 2:
//
// Input: [[1,4],[4,5]]
// Output: [[1,5]]
// Explanation: Intervals [1,4] and [4,5] are considered overlapping.


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
    public List<Interval> merge(List<Interval> intervals) {
        if (intervals == null || intervals.size() <= 1) {
            return intervals;
        }

        // return mytry(intervals);

        return method2(intervals);
    }

    private List<Interval> method2(List<Interval> intervals) {
        // ref: https://discuss.leetcode.com/topic/12788/a-clean-java-solution
        Collections.sort(intervals, new myComparator());
        List<Interval> result = new ArrayList<>();
        int n = intervals.size();
        Interval prev = null;
        for (Interval curr : intervals) {
            if (prev == null || prev.end < curr.start) {
                result.add(curr);
                prev = curr;
            } else {
                // overlap
                prev.end = Math.max(prev.end, curr.end);
            }
        }
        return result;
    }

    private List<Interval> mytry(List<Interval> intervals) {
        // O(nlogn) time and O(n) space
        // 给的可能不是 sort 好的
        Collections.sort(intervals, new myComparator());
        List<Interval> result = new ArrayList<>();
        int i = 0;
        int n = intervals.size();
        while (i < n - 1) {
            int start = intervals.get(i).start;
            int end = intervals.get(i).end;
            while (i < n - 1 && intervals.get(i + 1).start <= end) {
                // 前一个 end 可能比后一个的 end 还要大
                end = Math.max(intervals.get(i + 1).end, end);
                i++;
            }
            Interval merge = new Interval(start, end);
            result.add(merge);
            i++;
        }
        // 最后一个 interval 没有在 while 中合并， 所以要单拎出来处理
        if (i == n - 1) {
            result.add(intervals.get(i));
        }
        return result;
    }

    private class myComparator implements Comparator<Interval> {
        public int compare(Interval o1, Interval o2) {
            return Integer.compare(o1.start, o2.start);
        }
    }
}

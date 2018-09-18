// 252. Meeting Rooms
// DescriptionHintsSubmissionsDiscussSolution
// Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), determine if a person could attend all meetings.
//
// Example 1:
//
// Input: [[0,30],[5,10],[15,20]]
// Output: false
// Example 2:
//
// Input: [[7,10],[2,4]]
// Output: true


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
    public boolean canAttendMeetings(Interval[] intervals) {
        if (intervals == null || intervals.length == 0) {
            return true;
        }

        return brute(intervals);

        // return mytry(intervals);

        // return method2(intervals);
    }

    private boolean method2(Interval[] intervals) {
        int n = intervals.length;
        int[] starts = new int[n];
        int[] ends = new int[n];
        for (int i = 0; i < n; i++) {
            starts[i] = intervals[i].start;
            ends[i] = intervals[i].end;
        }
        Arrays.sort(starts);
        Arrays.sort(ends);
        for (int i = 1; i < n; i++) {
            if (starts[i] < ends[i - 1]) {
                return false;
            }
        }
        return true;
    }

    private boolean mytry(Interval[] intervals) {
        Arrays.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                if (o1.start == o2.start) {
                    return o1.end - o2.end;
                } else {
                    return o1.start - o2.start;
                }
            }
        });
        for (int i = 0; i < intervals.length - 1; i++) {
            if (intervals[i + 1].start < intervals[i].end) {
                return false;
            }
        }
        return true;
    }

    private boolean brute(Interval[] intervals) {
        for (int i = 0; i < intervals.length; i++) {
            for (int j = i + 1; j < intervals.length; j++) {
                if ((intervals[i].start <= intervals[j].start && intervals[j].start < intervals[i].end) ||
                   (intervals[i].start > intervals[j].start && intervals[j].end > intervals[i].start)) {
                    return false;
                }
            }
        }
        return true;
    }
}

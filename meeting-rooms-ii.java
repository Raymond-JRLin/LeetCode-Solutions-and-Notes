// 253. Meeting Rooms II
// DescriptionHintsSubmissionsDiscussSolution
// Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.
//
// Example 1:
//
// Input: [[0, 30],[5, 10],[15, 20]]
// Output: 2
// Example 2:
//
// Input: [[7,10],[2,4]]
// Output: 1


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
    public int minMeetingRooms(Interval[] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }

        // return method1(intervals);

        // return method2(intervals);

        return method3(intervals);

        // return method4(intervals);
    }

    private static int START = 1;
    private static int END = 0;
    private int method4(Interval[] intervals) {
        // use our own prive class Event to record its timestamp and it's start point or end point, then use PriorityQueue to sort all Event, if we meet a start event, then we need 1 more room, if we meet a end event, we just cancel 1 room
        // ref: https://leetcode.com/problems/meeting-rooms-ii/discuss/67920/Java-Another-thinking:-process-event-queue
        PriorityQueue<Event> pq = new PriorityQueue<>(new Comparator<Event>() {
            @Override
            public int compare(Event o1, Event o2) {
                if (o1.time == o2.time) {
                    return o1.type - o2.type;
                } else {
                    return o1.time - o2.time;
                }
            }
        });
        for (Interval inter : intervals) {
            pq.offer(new Event(inter.start, START));
            pq.offer(new Event(inter.end, END));
        }
        int rooms = 0;
        int count = 0;
        while (!pq.isEmpty()) {
            Event curr = pq.poll();
            if (curr.type == START) {
                count++;
                rooms = Math.max(rooms, count);
            } else {
                count--;
            }
        }
        return rooms;
    }
    private class Event {
        int time;
        int type;
        public Event(int time, int type) {
            this.time = time;
            this.type = type;
        }
    }

    private int method3(Interval[] intervals) {
        // use PQ(min-heap) to track end time
        int n = intervals.length;
        Arrays.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return o1.start - o2.start;
            }
        });
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (Interval inter : intervals) {
            if (!pq.isEmpty() && pq.peek() <= inter.start) {
                // 下一个开始的时间是在最早的结束时间之后， 那么下一个时间段就可以接着用最早结束时间的那个房间， 把最早的 poll 出来， 更新一下最早的结束时间
                pq.poll();
            }
            // min-heap: so offer every end time, since we sort array as start time, so we can keep tracking if there's sharing room for several intervals
            pq.offer(inter.end);
        }
        return pq.size();
    }

    private int method2(Interval[] intervals) {
        // like meeting room 1, get starts and ends, then sort them
        int n = intervals.length;
        int[] start = new int[n];
        int[] end = new int[n];
        for (int i = 0; i < n; i++) {
            start[i] = intervals[i].start;
            end[i] = intervals[i].end;
        }
        Arrays.sort(start);
        Arrays.sort(end);
        int rooms = 0;
        int index = 0;
        for (int i = 0; i < n; i++) {
            if (start[i] < end[index]) {
                rooms++;
            } else {
                // update end time
                index++;
            }
        }
        return rooms;
    }

    private int method1(Interval[] intervals) {
        // use HashMap
        Map<Integer, Integer> map = new HashMap<>(); // <time, # of meetings>
        for (Interval inter : intervals) {
            int start = inter.start;
            int end = inter.end;
            // start 开始的地方 + 1
            if (map.containsKey(start)) {
                map.put(start, map.get(start) + 1);
            } else {
                map.put(start, 1);
            }
            // end 结束的地方 - 1
            if (map.containsKey(end)) {
                map.put(end, map.get(end) - 1);
            } else {
                map.put(end, -1);
            }
        }
        // sort the map
        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return o1.getKey().compareTo(o2.getKey());
            }
        });

        int rooms = 0;
        int count = 0;
        for (Map.Entry<Integer, Integer> entry : list) {
            // 遍历 map， 时间从小到大， 房间数每次加上映射值， 然后更新结果 res， 遇到起始时间， 映射是正数， 则房间数会增加， 如果一个时间是一个会议的结束时间， 也是另一个会议的开始时间， 则映射值先减后加仍为 0， 并不用分配新的房间， 而结束时间的映射值为负数更不会增加房间数
            count += entry.getValue();
            rooms = Math.max(rooms, count);
        }
        return rooms;
    }
}

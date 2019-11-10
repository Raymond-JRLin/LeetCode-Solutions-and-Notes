// 253. Meeting Rooms II
// DescriptionHintsSubmissionsDiscussSolution
//
// Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.
//
// Example 1:
//
// Input: [[0, 30],[5, 10],[15, 20]]
// Output: 2
//
// Example 2:
//
// Input: [[7,10],[2,4]]
// Output: 1
//
// NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.
// Seen this question in a real interview before?
//
//     Difficulty:Medium


class Solution {
    public int minMeetingRooms(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }

        // return mytry(intervals);

        // return method2(intervals);

        return method3(intervals);
    }

    private int method3(int[][] intervals) {
        PriorityQueue<Event> pq = new PriorityQueue<>(new Comparator<Event>() {
            @Override
            public int compare(Event o1, Event o2) {
                if (o1.time == o2.time) {
                    return Integer.compare(o1.type, o2.type);
                } else {
                    return Integer.compare(o1.time, o2. time);
                }
            }
        });
        final int START = 1;
        final int END = 0;
        for (int[] inter : intervals) {
            pq.offer(new Event(inter[0], START));
            pq.offer(new Event(inter[1], END));
        }
        int room = 0;
        int result = 0;
        while (!pq.isEmpty()) {
            Event curr = pq.poll();
            if (curr.type == START) {
                room++;
            } else {
                room--;
            }
            result = Math.max(result, room);
        }
        return result;
    }
    private class Event {
        int time;
        int type;
        Event(int time, int type) {
            this.time = time;
            this.type = type;
        }
    }

    private int method2(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[0], o2[0]);
            }
        });
        PriorityQueue<Integer> pq = new PriorityQueue<>(); // <end>
        for (int[] inter : intervals) {
            if (!pq.isEmpty() && pq.peek() <= inter[0]) {
                // 一个房间结束了， 可以腾出来了
                pq.poll();
            }
            pq.offer(inter[1]);
        }
        return pq.size();
    }

    private int mytry(int[][] intervals) {
        int n = intervals.length;
        List<Integer> in = new ArrayList<>();
        List<Integer> out = new ArrayList<>();
        for (int[] inter : intervals) {
            in.add(inter[0]);
            out.add(inter[1]);
        }
        Collections.sort(in);
        Collections.sort(out);
        int i = 0, j = 0;
        int room = 0;
        int result = 0;
        while (i < in.size()) {
            if (in.get(i) < out.get(j)) {
                room++;
                i++;
            } else {
                room--;
                j++;
            }
            result = Math.max(result, room);
        }
        return result;
    }
}

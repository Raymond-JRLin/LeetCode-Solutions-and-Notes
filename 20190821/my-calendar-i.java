// 729. My Calendar I
// DescriptionHintsSubmissionsDiscussSolution
//
// Implement a MyCalendar class to store your events. A new event can be added if adding the event will not cause a double booking.
//
// Your class will have the method, book(int start, int end). Formally, this represents a booking on the half open interval [start, end), the range of real numbers x such that start <= x < end.
//
// A double booking happens when two events have some non-empty intersection (ie., there is some time that is common to both events.)
//
// For each call to the method MyCalendar.book, return true if the event can be added to the calendar successfully without causing a double booking. Otherwise, return false and do not add the event to the calendar.
// Your class will be called like this: MyCalendar cal = new MyCalendar(); MyCalendar.book(start, end)
//
// Example 1:
//
// MyCalendar();
// MyCalendar.book(10, 20); // returns true
// MyCalendar.book(15, 25); // returns false
// MyCalendar.book(20, 30); // returns true
// Explanation:
// The first event can be booked.  The second can't because time 15 is already booked by another event.
// The third event can be booked, as the first event takes every time less than 20, but not including 20.
//
//
//
// Note:
//
//     The number of calls to MyCalendar.book per test case will be at most 1000.
//     In calls to MyCalendar.book(start, end), start and end are integers in the range [0, 10^9].
//
//
// Seen this question in a real interview before?
//
//     Difficulty:Medium


class MyCalendar {

    // 1.
    // List<Event> calendar;

    // 2. use TreeSet or TreeMap
    TreeSet<Event> calendar;

    public MyCalendar() {
        // 1.
        // calendar = new LinkedList<>();

        // 2. use TreeSet and sort as start
        calendar = new TreeSet<>((o1, o2) -> (Integer.compare(o1.start, o2.start)));
    }

    public boolean book(int start, int end) {
        Event newEvent = new Event(start, end);

        // 1.
        // if (calendar.size() == 0 || end <= calendar.get(0).start) {
        //     calendar.add(0, newEvent);
        //     return true;
        // }
        // for (int i = 0; i < calendar.size() - 1; i++) {
        //     Event prev = calendar.get(i);
        //     Event next = calendar.get(i + 1);
        //     if (prev.end <= start && end <= next.start) {
        //         calendar.add(i + 1, newEvent); // 注意 index， 实际上替换掉了 next 的位置
        //         return true;
        //     }
        // }
        // if (start >= calendar.get(calendar.size() - 1).end) {
        //     calendar.add(newEvent);
        //     return true;
        // }
        // return false;

        // 2.
        Event floor = calendar.floor(newEvent);
        if (floor != null && floor.end > start) {
            return false;
        }
        Event ceiling = calendar.ceiling(newEvent);
        if (ceiling != null && ceiling.start < end) {
            return false;
        }
        calendar.add(newEvent);
        return true;
    }

    private class Event {
        int start;
        int end;

        Event(int s, int e) {
            this.start = s;
            this.end = e;
        }
    }
}

/**
 * Your MyCalendar object will be instantiated and called as such:
 * MyCalendar obj = new MyCalendar();
 * boolean param_1 = obj.book(start,end);
 */

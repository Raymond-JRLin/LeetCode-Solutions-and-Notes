// 352. Data Stream as Disjoint Intervals
// DescriptionHintsSubmissionsDiscussSolution
//
// Given a data stream input of non-negative integers a1, a2, ..., an, ..., summarize the numbers seen so far as a list of disjoint intervals.
//
// For example, suppose the integers from the data stream are 1, 3, 7, 2, 6, ..., then the summary will be:
//
// [1, 1]
// [1, 1], [3, 3]
// [1, 1], [3, 3], [7, 7]
// [1, 3], [7, 7]
// [1, 3], [6, 7]
//
//
//
// Follow up:
//
// What if there are lots of merges and the number of disjoint intervals are small compared to the data stream's size?
// Seen this question in a real interview before?
//
//     Difficulty:Hard


class SummaryRanges {

    TreeMap<Integer, int[]> map;

    /** Initialize your data structure here. */
    public SummaryRanges() {
        map = new TreeMap<>();
    }

    public void addNum(int val) {
        if (map.containsKey(val)) {
            return;
        }
        Integer low = map.lowerKey(val);
        Integer high = map.higherKey(val); // actually high == map.get(high)[0], 即是 high range 的左端
        if (low != null && high != null && map.get(low)[1] + 1 == val && map.get(high)[0] == val + 1) {
            // can combine lower range and higher range
            map.get(low)[1] = map.get(high)[1];
            map.remove(high);

        } else if (low != null && map.get(low)[1] + 1 >= val) {
            // val is inside lower range, or just can be adding to the end of lower range
            map.get(low)[1] = Math.max(map.get(low)[1], val);
        } else if (high != null && val + 1 == map.get(high)[0]) {
            // val can be just adding to the beginning of higher range
            // don't need to think about inside the higher range, because that means there's a lower in higher range
            map.put(val, new int[]{val, map.get(high)[1]});
            map.remove(high);
        } else {
            map.put(val, new int[]{val, val});
        }
    }

    public int[][] getIntervals() {
        List<int[]> list = new ArrayList<>(map.values());
        int[][] result = new int[list.size()][2];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
    }
}

/**
 * Your SummaryRanges object will be instantiated and called as such:
 * SummaryRanges obj = new SummaryRanges();
 * obj.addNum(val);
 * int[][] param_2 = obj.getIntervals();
 */

// 632. Smallest Range Covering Elements from K Lists
// DescriptionHintsSubmissionsDiscussSolution
// You have k lists of sorted integers in ascending order. Find the smallest range that includes at least one number from each of the k lists.
//
// We define the range [a,b] is smaller than range [c,d] if b-a < d-c or a < c if b-a == d-c.
//
//
//
// Example 1:
//
// Input: [[4,10,15,24,26], [0,9,12,20], [5,18,22,30]]
// Output: [20,24]
// Explanation:
// List 1: [4, 10, 15, 24,26], 24 is in range [20,24].
// List 2: [0, 9, 12, 20], 20 is in range [20,24].
// List 3: [5, 18, 22, 30], 22 is in range [20,24].
//
//
// Note:
//
// The given list may contain duplicates, so ascending order means >= here.
// 1 <= k <= 3500
// -105 <= value of elements <= 105.
// Seen this question in a real interview before?
// Difficulty:Hard


class Solution {
    public int[] smallestRange(List<List<Integer>> nums) {
        if (nums == null || nums.size() == 0) {
            return new int[0];
        }

        return method1(nums);
    }

    private int[] method1(List<List<Integer>> nums) {
        // ref: https://leetcode.com/problems/smallest-range/discuss/104893/Java-Code-using-PriorityQueue.-similar-to-merge-k-array
        // similar to merge k array
        int n = nums.size();
        PriorityQueue<Element> pq = new PriorityQueue<>(new Comparator<Element>() {
            @Override
            public int compare(Element o1, Element o2) {
                return Integer.compare(o1.val, o2.val);
            }
        });

        int range = Integer.MAX_VALUE;
        int start = -1;
        int end = -1;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            int num = nums.get(i).get(0);
            pq.offer(new Element(num, i, 0)); // offer the 1st col
            max = Math.max(max, num); // max value in the 1st col
        }

        while (pq.size() == nums.size()) {
            // 注意结束条件， 当其中一个 row 的 value 都拿完了之后就要结束了， 不然 range 会没有包括这一行
            // 同时 poll 出一个 offer 一个也保证了取数的时候一定是每一个 row 的一个数在其中， 每次比较的时候都是能够横跨每个 row 的
            Element curr = pq.poll();
            if (max - curr.val < range) {
                range = max - curr.val;
                start = curr.val;
                end = max;
            }
            if (curr.col + 1 < nums.get(curr.row).size()) {
                int nextVal = nums.get(curr.row).get(curr.col + 1);
                pq.offer(new Element(nextVal, curr.row, curr.col + 1));
                if (nextVal > max) {
                    // udpate max value
                    max = nextVal;
                }
            }
        }

        return new int[]{start, end};
    }
    private class Element {
        private int val;
        private int row;
        private int col;
        public Element(int v, int r, int c) {
            val = v;
            row = r;
            col = c;
        }
    }
}

// 632. Smallest Range Covering Elements from K Lists
// DescriptionHintsSubmissionsDiscussSolution
//
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
//
// Note:
//
//     The given list may contain duplicates, so ascending order means >= here.
//     1 <= k <= 3500
//     -105 <= value of elements <= 105.
//
// Seen this question in a real interview before?
//
//     Difficulty:Hard


class Solution {
    public int[] smallestRange(List<List<Integer>> nums) {

        // return method1(nums);

        return method2(nums);
    }

    private int[] method2(List<List<Integer>> nums) {
        // 如果想要做正常类型的 sliding window, 那要把所有的数放在一起， sorting
        // 然后同样的策略， 保证每个 window 都有来自 k 个 list 的元素
        int n = nums.size();
        List<Pair> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int num : nums.get(i)) {
                list.add(new Pair(num, i));
            }
        }
        Collections.sort(list); // ascending order by value

        int left = 0, right = 0;
        // 用一个 map 记录第 k list 有多少个元素
        Map<Integer, Integer> map = new HashMap<>(); // <k, count if kth list>
        int range = Integer.MAX_VALUE;
        int start = 0, end = 0;
        while (right < list.size()) {
            // 放进当下的元素
            int currRightIndex = list.get(right).index;
            map.put(currRightIndex, map.getOrDefault(currRightIndex, 0) + 1);
            // 如果 k 个 list 都有了的话
            while (map.size() == n) {
                // 更新 range 和 区间首尾
                if (list.get(right).val - list.get(left).val < range) {
                    range = list.get(right).val - list.get(left).val;
                    start = list.get(left).val;
                    end = list.get(right).val;
                }
                // 移动 left
                int leftIndex = list.get(left).index;
                map.put(leftIndex, map.get(leftIndex) - 1);
                if (map.get(leftIndex) == 0) {
                    map.remove(leftIndex);
                }
                left++;
            }
            right++;
        }
        return new int[]{start, end};
    }
    private class Pair implements Comparable<Pair> {
        int val;
        int index;

        Pair(int v, int i) {
            this.val = v;
            this.index = i;
        }

        @Override
        public int compareTo(Pair o2) {
            return Integer.compare(this.val, o2.val);
        }
    }

    private int[] method1(List<List<Integer>> nums) {
        // 类似于 merge k sorted list
        // 每次只比较 k 个 element， 分别来自于 k 个 list， 这样算是比较 greedy， 就是说我们只 cover 每个 list 中的 1 个 element
        // 有点像 vertical sliding window
        int n = nums.size();
        PriorityQueue<Element> pq = new PriorityQueue<>();
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            pq.offer(new Element(i, 0, nums.get(i).get(0)));
            max = Math.max(max, nums.get(i).get(0));
        }
        // 此时 PQ 中有 k 个元素， 最大的为 max
        int[] result = new int[2];
        int range = Integer.MAX_VALUE;
        while (pq.size() == n) {
            // 每次 poll 出最小的， 那么 max - curr.val 就是当前 cover 住 k 个元素的 range
            Element curr = pq.poll();
            if (max - curr.val < range) {
                range = max - curr.val;
                result[0] = curr.val;
                result[1] = max;
            }
            // 那么这个最小的数的下一个会稍微大一些， 我们认为会使得 range 更小一些
            if (curr.col < nums.get(curr.row).size() - 1) {
                Element next = new Element(curr.row, curr.col + 1, nums.get(curr.row).get(curr.col + 1));
                // 当然， 如果是比 max 还大的数， 就要更新 max
                if (next.val > max) {
                    max = next.val;
                }
                pq.offer(next);
            }
        }
        return result;
    }
    private class Element implements Comparable<Element> {
        int row;
        int col;
        int val;

        Element(int r, int c, int v) {
            this.row = r;
            this.col = c;
            this.val = v;
        }

        @Override
        public int compareTo(Element o2) {
            return Integer.compare(this.val, o2.val);
        }
    }
}

// 846. Hand of Straights
// DescriptionHintsSubmissionsDiscussSolution
// Alice has a hand of cards, given as an array of integers.
//
// Now she wants to rearrange the cards into groups so that each group is size W, and consists of W consecutive cards.
//
// Return true if and only if she can.
//
//
//
// Example 1:
//
// Input: hand = [1,2,3,6,2,3,4,7,8], W = 3
// Output: true
// Explanation: Alice's hand can be rearranged as [1,2,3],[2,3,4],[6,7,8].
// Example 2:
//
// Input: hand = [1,2,3,4,5], W = 4
// Output: false
// Explanation: Alice's hand can't be rearranged into groups of 4.
//
//
// Note:
//
// 1 <= hand.length <= 10000
// 0 <= hand[i] <= 10^9
// 1 <= W <= hand.length


class Solution {
    public boolean isNStraightHand(int[] hand, int W) {
        if (hand == null || hand.length % W != 0) {
            return false;
        }

        // return mytry(hand, W);

        return method2(hand, W);
    }

    private boolean method2(int[] nums, int w) {
        // 利用 TreeMap 就可以实现内部排列好的顺序， 每次从最小的开始走 w 个
        // 还有个小 trick 就是可以不用每次 - 1， 而是减去当前最小的数的个数， 因为如果符合题目要求的话， 就会刚好用完， 否则后面会出现负的， 那就返回 false
        Map<Integer, Integer> map = new TreeMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for (int key : map.keySet()) {
            if (map.get(key) > 0) {
                for (int i = w - 1; i >= 0; i--) {
                    // 注意这里要从后往前更新， 因为下面要更新 i 之后 w 个数， 减去的是 key 的个数， 所以要最后来更新它
                    if (map.getOrDefault(key + i, 0) < map.get(key)) {
                        return false;
                    }
                    map.put(key + i, map.get(key + i) - map.get(key));
                }
            }
        }
        return true;
    }

    private boolean mytry(int[] nums, int w) {
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        PriorityQueue<Pair> pq = new PriorityQueue<>(new Comparator<Pair>(){
            @Override
            public int compare(Pair o1, Pair o2) {
                return Integer.compare(o1.val, o2.val);
            }
        });
        for (int key : map.keySet()) {
            pq.offer(new Pair(key, map.get(key)));
        }
        while (!pq.isEmpty()) {
            int prev = 0;
            Stack<Pair> stack = new Stack<>();
            for (int i = 0; i < w; i++) {
                if (pq.isEmpty()) {
                    return false;
                }
                Pair curr = pq.poll();
                if (i != 0) {
                    if (curr.val != prev + 1) {
                        return false;
                    }
                }
                prev = curr.val;
                curr.count -= 1;
                if (curr.count != 0) {
                    stack.push(curr);
                }
            }
            while (!stack.isEmpty()) {
                pq.offer(stack.pop());
            }
        }
        return true;
    }
    private class Pair {
        private int val;
        private int count;
        public Pair(int v, int c) {
            val = v;
            count =c;
        }
    }
}

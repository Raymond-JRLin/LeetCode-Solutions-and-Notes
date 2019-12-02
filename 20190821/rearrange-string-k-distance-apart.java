// 358. Rearrange String k Distance Apart
// DescriptionHintsSubmissionsDiscussSolution
//
// Given a non-empty string s and an integer k, rearrange the string such that the same characters are at least distance k from each other.
//
// All input strings are given in lowercase letters. If it is not possible to rearrange the string, return an empty string "".
//
// Example 1:
//
// Input: s = "aabbcc", k = 3
// Output: "abcabc"
// Explanation: The same letters are at least distance 3 from each other.
//
// Example 2:
//
// Input: s = "aaabc", k = 3
// Output: ""
// Explanation: It is not possible to rearrange the string.
//
// Example 3:
//
// Input: s = "aaadbbcc", k = 2
// Output: "abacabcd"
// Explanation: The same letters are at least distance 2 from each other.
//
// Seen this question in a real interview before?
//
//     Difficulty:Hard


class Solution {
    public String rearrangeString(String s, int k) {

        return method1(s, k);
    }

    private String method1(String s, int k) {
        // 其实还是比较直接的想法， 就是先填 freq 最大的， 然后第二大， 隔了 k 个， 再填之前填过的
        // O(Nlog26), where N is the length of s
        // get freq
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        // use PQ 来 sort freq 最大的先用
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        for (char c : map.keySet()) {
            pq.offer(new Pair(c, map.get(c)));
        }
        // use queue 来模拟 k 个间隔， 相当于冷却一下
        Queue<Pair> queue = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            // 拿出 freq 最大的
            Pair curr = pq.poll();
            sb.append(curr.getVal());
            curr.use();
            queue.offer(curr);
            // 如果冷却 queue 里面还没有 k 个， 说明还不到间隔
            if (queue.size() < k) {
                continue;
            }
            // 间隔到了， 把 freq 最大的重新拿出来， 如果 freq 还有的话， 再放入 pq
            Pair next = queue.poll();
            if (next.isLive()) {
                pq.offer(next);
            }
        }
        return sb.length() == s.length() ? sb.toString() : "";
    }
    private class Pair implements Comparable<Pair> {
        char val;
        int freq;

        Pair(char c, int f) {
            this.val = c;
            this.freq = f;
        }

        @Override
        public int compareTo(Pair o2) {
            return Integer.compare(o2.freq, this.freq);
        }

        boolean use() {
            if (this.freq == 0) {
                return false;
            }
            this.freq--;
            return true;
        }

        boolean isLive() {
            return this.freq > 0;
        }

        char getVal() {
            return this.val;
        }
    }
}

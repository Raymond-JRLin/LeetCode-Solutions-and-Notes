// 373. Find K Pairs with Smallest Sums
// DescriptionHintsSubmissionsDiscussSolution
// You are given two integer arrays nums1 and nums2 sorted in ascending order and an integer k.
//
// Define a pair (u,v) which consists of one element from the first array and one element from the second array.
//
// Find the k pairs (u1,v1),(u2,v2) ...(uk,vk) with the smallest sums.
//
// Example 1:
//
// Input: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
// Output: [[1,2],[1,4],[1,6]]
// Explanation: The first 3 pairs are returned from the sequence:
//              [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
// Example 2:
//
// Input: nums1 = [1,1,2], nums2 = [1,2,3], k = 2
// Output: [1,1],[1,1]
// Explanation: The first 2 pairs are returned from the sequence:
//              [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
// Example 3:
//
// Input: nums1 = [1,2], nums2 = [3], k = 3
// Output: [1,3],[2,3]
// Explanation: All possible pairs are returned from the sequence: [1,3],[2,3]



class Solution {
    public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0 || k < 1) {
            return Collections.emptyList();
        }

        return method1(nums1, nums2, k);
    }

    private List<int[]> method1(int[] nums1, int[] nums2, int k) {
        // ref: https://leetcode.com/problems/find-k-pairs-with-smallest-sums/discuss/84566/Share-My-Solution-which-beat-96.42
        // 不要有迷思， 其实就找到所有的 pair 而言， 变动哪个 nums 都是可以的， 只不过是两层 for 的先后顺序而已
        // 和 ref 放入 pair 中的元素略有不同
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        // 预先放 nums1[0] 和 nums2 所有的配对
        for (int j = 0; j < nums2.length; j++) {
            pq.offer(new Pair(nums1[0], nums2[j], 0));
        }
        List<int[]> result = new ArrayList<>();
        while (k-- > 0 && !pq.isEmpty()) {
            Pair curr = pq.poll();
            int pos = curr.pos; // pos 是 nums1 中的 index
            result.add(new int[]{curr.x, curr.y});
            if (pos >= nums1.length - 1) {
                continue;
            }
            // 在接下去的过程中， for nums1 的不同 index， 保持 nums2 不变
            pq.offer(new Pair(nums1[curr.pos + 1], curr.y, curr.pos + 1));
        }
        return result;
    }
    private class Pair implements Comparable<Pair> {
        private int x;
        private int y;
        private int pos;
        public Pair(int x, int y, int pos) {
            this.x = x;
            this.y = y;
            this.pos = pos;
        }
        @Override
        public int compareTo(Pair o2) {
            return this.x + this.y - (o2.x + o2.y);
        }
    }
}

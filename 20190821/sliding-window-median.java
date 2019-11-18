// 480. Sliding Window Median
// DescriptionHintsSubmissionsDiscussSolution
//
// Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.
// Examples:
//
// [2,3,4] , the median is 3
//
// [2,3], the median is (2 + 3) / 2 = 2.5
//
// Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position. Your job is to output the median array for each window in the original array.
//
// For example,
// Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.
//
// Window position                Median
// ---------------               -----
// [1  3  -1] -3  5  3  6  7       1
//  1 [3  -1  -3] 5  3  6  7       -1
//  1  3 [-1  -3  5] 3  6  7       -1
//  1  3  -1 [-3  5  3] 6  7       3
//  1  3  -1  -3 [5  3  6] 7       5
//  1  3  -1  -3  5 [3  6  7]      6
//
// Therefore, return the median sliding window as [1,-1,-1,3,5,6].
//
// Note:
// You may assume k is always valid, ie: k is always smaller than input array's size for non-empty array.
// Seen this question in a real interview before?
//
//     Difficulty:Hard


class Solution {
    public double[] medianSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length < k) {
            return new double[0];
        }

        // return method1(nums, k);

        return method2(nums, k);
    }

    private double[] method2(int[] nums, int k) {
        // PQ's remove(Object) is linear time,  换一个数据结构使得 remove 也是 log 的
        // 这里使用 TreeSet, 但是 set 要处理 duplicates， 也就是说出现相同的数的时候要知道 remove 哪一个
        // 所以这里 TreeSet 里面放的是 index， 比较的时候去 nums 里面拿， 如果相同， remove index 小的那个
        // ref: https://leetcode.com/problems/sliding-window-median/discuss/96346/Java-using-two-Tree-Sets-O(n-logk)
        int n = nums.length;
        Comparator<Integer> myComparator =
            (o1, o2) -> (nums[o1] == nums[o2] ? Integer.compare(o1, o2) : Integer.compare(nums[o1], nums[o2]));
        TreeSet<Integer> minTree = new TreeSet<>(myComparator); // <index in nums>
        TreeSet<Integer> maxTree = new TreeSet<>(myComparator.reversed());

        int len = n - k + 1;
        double[] result = new double[len];
        double median = 0.0;
        for (int i = 0; i < n; i++) {
            minTree.add(i);
            maxTree.add(minTree.pollFirst());
            balance(minTree, maxTree);
            if (i + 1 >= k) {
                median = getMedian(minTree, maxTree, nums);
                result[i + 1 - k] = median;
                if (!maxTree.remove(i + 1 - k)) {
                    minTree.remove(i + 1 - k);
                }
            }
        }
        return result;
    }
    private double getMedian(TreeSet<Integer> minTree, TreeSet<Integer> maxTree, int[] nums) {
        if (minTree.isEmpty() && maxTree.isEmpty()) {
            return (double) 0.0;
        }
        if (minTree.size() == maxTree.size()) {
            return ((double) nums[minTree.first()] + (double) nums[maxTree.first()]) / 2.0;
        } else {
            return (double) nums[maxTree.first()];
        }
    }
    private void balance(TreeSet<Integer> minTree, TreeSet<Integer> maxTree) {
        // make sure maxTree.size() >= minTree.size()
        if (minTree.size() > maxTree.size()) {
            maxTree.add(minTree.pollFirst());
        }
        if (maxTree.size() - 1 > minTree.size()) {
            minTree.add(maxTree.pollFirst());
        }
    }


    private double[] method1(int[] nums, int k) {
        // similar to Median in Data Stream, use 2 PQ
        // O(N * K) time complexity, because PQ's remove(Object) is linear time
        int n = nums.length;
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((o1, o2) -> (Integer.compare(o2, o1)));

        int len = n - k + 1;
        double[] result = new double[len];
        for (int i = 0; i < n; i++) {
            add(minHeap, maxHeap, nums[i]);
            if (i + 1 >= k) {
                result[i + 1 - k] = getMedian(minHeap, maxHeap);
                remove(minHeap, maxHeap, nums[i + 1 - k]);
            }
        }
        return result;
    }
    private double getMedian(PriorityQueue<Integer> minHeap, PriorityQueue<Integer> maxHeap) {
        if (minHeap.isEmpty() && maxHeap.isEmpty()) {
            return (double) 0.0;
        }
        if (minHeap.size() == maxHeap.size()) {
            return ((double) minHeap.peek() + (double) maxHeap.peek()) / 2.0;
        } else {
            return (double) maxHeap.peek();
        }
    }
    private void add(PriorityQueue<Integer> minHeap, PriorityQueue<Integer> maxHeap, int num) {
        minHeap.offer(num);
        maxHeap.offer(minHeap.poll());
        balance(minHeap, maxHeap);
    }
    private void remove(PriorityQueue<Integer> minHeap, PriorityQueue<Integer> maxHeap, int num) {
        // == median 的时候在 maxHeap 这边
        if (num <= getMedian(minHeap, maxHeap)) {
            maxHeap.remove(num);
        } else {
            minHeap.remove(num);
        }
        balance(minHeap, maxHeap);
    }
    private void balance(PriorityQueue<Integer> minHeap, PriorityQueue<Integer> maxHeap) {
        // make sure maxHeap.size() >= minHeap.size()
        if (minHeap.size() > maxHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
        if (maxHeap.size() - 1 > minHeap.size()) {
            minHeap.offer(maxHeap.poll());
        }
    }
}

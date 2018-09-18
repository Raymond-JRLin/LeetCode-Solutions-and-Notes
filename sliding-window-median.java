// 480. Sliding Window Median
// DescriptionHintsSubmissionsDiscussSolution
// Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.
//
// Examples:
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
// Therefore, return the median sliding window as [1,-1,-1,3,5,6].
//
// Note:
// You may assume k is always valid, ie: k is always smaller than input array's size for non-empty array.
//


class Solution {
    public double[] medianSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length < k) {
            return new double[0];
        }

        return method1(nums, k);
    }

    private double[] method1(int[] nums, int k) {
        // 2 PQ to maintain the median candidate so we can get the median quickly
        int n = nums.length;
        double[] result = new double[n - k + 1];
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o2, o1);
            }
        });
        for (int i = 0; i <= n; i++) {
            // 注意 index 范围， 一共有 n - k + 1 个答案， 所以 i 要走到 n， 那么就要注意取 nums[i] 的时候不要越界
            if (i >= k) {
                result[i - k] = getMedian(minHeap, maxHeap, k);
                remove(minHeap, maxHeap, nums[i - k], k);
            }
            if (i < n) {
                add(minHeap, maxHeap, nums[i], k);
            }
        }
        return result;
    }
    private void add(PriorityQueue<Integer> minHeap, PriorityQueue<Integer> maxHeap, int num, int k) {
        if (num <= getMedian(minHeap, maxHeap, k)) {
            maxHeap.offer(num);
        } else {
            minHeap.offer(num);
        }
        swap(minHeap, maxHeap);
    }
    private void remove(PriorityQueue<Integer> minHeap, PriorityQueue<Integer> maxHeap, int num, int k) {
        if (num <= getMedian(minHeap, maxHeap, k)) {
            maxHeap.remove(num);
        } else {
            minHeap.remove(num);
        }
        swap(minHeap, maxHeap);
    }
    private void swap(PriorityQueue<Integer> minHeap, PriorityQueue<Integer> maxHeap) {
        // 当 k 是奇数的时候， 我把多出来的总是放到 maxHeap 中
        if (minHeap.size() > maxHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
        if (maxHeap.size() - minHeap.size() > 1) {
            minHeap.offer(maxHeap.poll());
        }
    }
    private double getMedian(PriorityQueue<Integer> minHeap, PriorityQueue<Integer> maxHeap, int k) {
        // initial
        if (minHeap.size() == 0 && maxHeap.size() == 0) {
            return 0;
        }
        // 这里不可以看 d 是否能被 2 整除， 因为有可能 peek() 会有空指针错误
        if (minHeap.size() != maxHeap.size()) {
            // 最中间的那么总是放在 maxHeap 中
            return (double) maxHeap.peek();
        } else {
            // 先转换成 double 避免相加越界 Integer
            return (double) ((double) maxHeap.peek() + (double) minHeap.peek()) / 2.0;
        }
    }
}

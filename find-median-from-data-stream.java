// 295. Find Median from Data Stream
// DescriptionHintsSubmissionsDiscussSolution
// Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.
//
// For example,
// [2,3,4], the median is 3
//
// [2,3], the median is (2 + 3) / 2 = 2.5
//
// Design a data structure that supports the following two operations:
//
// void addNum(int num) - Add a integer number from the data stream to the data structure.
// double findMedian() - Return the median of all elements so far.
//
//
// Example:
//
// addNum(1)
// addNum(2)
// findMedian() -> 1.5
// addNum(3)
// findMedian() -> 2
//
//
// Follow up:
//
// If all integer numbers from the stream are between 0 and 100, how would you optimize it?
// If 99% of all integer numbers from the stream are between 0 and 100, how would you optimize it?


class MedianFinder {

    // 和 sliding window mediadn 不同的是， 这里不需要 remove， 所以不要考虑两个 queue 会差很多， 然后看多出来的放哪边。
    // 每次都固定放一边， 最多差一个， 调整这一个就好了

    PriorityQueue<Integer> maxHeap; // smaller part
    PriorityQueue<Integer> minHeap; // larger part

    /** initialize your data structure here. */
    public MedianFinder() {
        maxHeap = new PriorityQueue<>((o1, o2) -> Integer.compare(o2, o1));
        minHeap = new PriorityQueue<>();
    }

//     public void addNum(int num) {
//         maxHeap.offer(num);
//         minHeap.offer(maxHeap.poll());

//         if (minHeap.size() > maxHeap.size()) {
//             maxHeap.offer(minHeap.poll());
//         }

//     }

//     public double findMedian() {
//         if (maxHeap.size() > minHeap.size()) {
//             return (double) (maxHeap.peek());
//         } else {
//             return (double) (maxHeap.peek() + minHeap.peek()) / 2.0D;
//         }
//     }

    // 或者按照 sliding window k median 的做法也可以
    public void addNum(int num) {
        if (num <= findMedian()) {
            maxHeap.offer(num);
        } else {
            minHeap.offer(num);
        }
        swap();
    }
    private void swap() {
        // 当 k 是奇数的时候， 我把多出来的总是放到 maxHeap 中
        if (minHeap.size() > maxHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
        if (maxHeap.size() - minHeap.size() > 1) {
            minHeap.offer(maxHeap.poll());
        }
    }
    public double findMedian() {
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

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */

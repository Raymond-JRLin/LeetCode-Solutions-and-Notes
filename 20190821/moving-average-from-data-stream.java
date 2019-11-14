// 346. Moving Average from Data Stream
// DescriptionHintsSubmissionsDiscussSolution
//
// Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.
//
// Example:
//
// MovingAverage m = new MovingAverage(3);
// m.next(1) = 1
// m.next(10) = (1 + 10) / 2
// m.next(3) = (1 + 10 + 3) / 3
// m.next(5) = (10 + 3 + 5) / 3
//
//
// Seen this question in a real interview before?
//
//     Difficulty:Easy


class MovingAverage {

    // method1
    // Queue<Integer> queue;
    // int size;
    // double sum;

    // method2: using circular array
    int[] nums;
    int index;
    int n;
    double sum;

    /** Initialize your data structure here. */
    public MovingAverage(int size) {
        // queue = new LinkedList<>();
        // this.size = size;
        // sum = 0.0;

        nums = new int[size];
        index = 0;
        n = 0;
        sum = 0.0;
    }

    public double next(int val) {
        // if (queue.size() == size) {
        //     sum -= queue.poll();
        // }
        // sum += val;
        // queue.offer(val);
        // return sum / queue.size();

        if (n < nums.length) {
            n++;
        }
        sum -=nums[index];
        sum += val;
        nums[index++] = val;
        index %= nums.length;
        return (double) (sum / n);
    }
}

/**
 * Your MovingAverage object will be instantiated and called as such:
 * MovingAverage obj = new MovingAverage(size);
 * double param_1 = obj.next(val);
 */

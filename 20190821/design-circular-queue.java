// 622. Design Circular Queue
// DescriptionHintsSubmissionsDiscussSolution
//
// Design your implementation of the circular queue. The circular queue is a linear data structure in which the operations are performed based on FIFO (First In First Out) principle and the last position is connected back to the first position to make a circle. It is also called "Ring Buffer".
//
// One of the benefits of the circular queue is that we can make use of the spaces in front of the queue. In a normal queue, once the queue becomes full, we cannot insert the next element even if there is a space in front of the queue. But using the circular queue, we can use the space to store new values.
//
// Your implementation should support following operations:
//
//     MyCircularQueue(k): Constructor, set the size of the queue to be k.
//     Front: Get the front item from the queue. If the queue is empty, return -1.
//     Rear: Get the last item from the queue. If the queue is empty, return -1.
//     enQueue(value): Insert an element into the circular queue. Return true if the operation is successful.
//     deQueue(): Delete an element from the circular queue. Return true if the operation is successful.
//     isEmpty(): Checks whether the circular queue is empty or not.
//     isFull(): Checks whether the circular queue is full or not.
//
//
//
// Example:
//
// MyCircularQueue circularQueue = new MyCircularQueue(3); // set the size to be 3
// circularQueue.enQueue(1);  // return true
// circularQueue.enQueue(2);  // return true
// circularQueue.enQueue(3);  // return true
// circularQueue.enQueue(4);  // return false, the queue is full
// circularQueue.Rear();  // return 3
// circularQueue.isFull();  // return true
// circularQueue.deQueue();  // return true
// circularQueue.enQueue(4);  // return true
// circularQueue.Rear();  // return 4
//
//
//
// Note:
//
//     All values will be in the range of [0, 1000].
//     The number of operations will be in the range of [1, 1000].
//     Please do not use the built-in Queue library.
//
// Seen this question in a real interview before?
//
//     Difficulty:Medium


class MyCircularQueue {

    int[] nums;
    int head;
    int tail;
    int taken;

    // 用一个数组来模拟， 注意这是个 queue， 所以先进先出
    // 那么我们从数组 0 开始往后填， 移动的应该是 tail 指针

    /** Initialize your data structure here. Set the size of the queue to be k. */
    public MyCircularQueue(int k) {
        nums = new int[k];
        head = 0; // 有可能还没有 dequeue， 就取 Front
        tail = -1;
        taken = 0;
    }

    /** Insert an element into the circular queue. Return true if the operation is successful. */
    public boolean enQueue(int value) {
        if (isFull()) {
            return false;
        }
        tail = (tail + 1) % nums.length;
        nums[tail] = value;
        taken++;
        return true;
    }

    /** Delete an element from the circular queue. Return true if the operation is successful. */
    public boolean deQueue() {
        if (isEmpty()) {
            return false;
        }
        head = (head + 1) % nums.length;
        taken--;
        return true;
    }

    /** Get the front item from the queue. */
    public int Front() {
        if (isEmpty()) {
            return -1;
        }
        return nums[head];
    }

    /** Get the last item from the queue. */
    public int Rear() {
        if (isEmpty()) {
            return -1;
        }
        return nums[tail];
    }

    /** Checks whether the circular queue is empty or not. */
    public boolean isEmpty() {
        return taken == 0;
    }

    /** Checks whether the circular queue is full or not. */
    public boolean isFull() {
        return taken == nums.length;
    }
}

/**
 * Your MyCircularQueue object will be instantiated and called as such:
 * MyCircularQueue obj = new MyCircularQueue(k);
 * boolean param_1 = obj.enQueue(value);
 * boolean param_2 = obj.deQueue();
 * int param_3 = obj.Front();
 * int param_4 = obj.Rear();
 * boolean param_5 = obj.isEmpty();
 * boolean param_6 = obj.isFull();
 */

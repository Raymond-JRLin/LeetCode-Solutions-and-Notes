// 232. Implement Queue using Stacks
// DescriptionHintsSubmissionsDiscussSolution
// Implement the following operations of a queue using stacks.
//
// push(x) -- Push element x to the back of queue.
// pop() -- Removes the element from in front of queue.
// peek() -- Get the front element.
// empty() -- Return whether the queue is empty.
// Example:
//
// MyQueue queue = new MyQueue();
//
// queue.push(1);
// queue.push(2);
// queue.peek();  // returns 1
// queue.pop();   // returns 1
// queue.empty(); // returns false
// Notes:
//
// You must use only standard operations of a stack -- which means only push to top, peek/pop from top, size, and is empty operations are valid.
// Depending on your language, stack may not be supported natively. You may simulate a stack by using a list or deque (double-ended queue), as long as you use only standard operations of a stack.
// You may assume that all operations are valid (for example, no pop or peek operations will be called on an empty queue).


class MyQueue {
    // ref: https://leetcode.com/problems/implement-queue-using-stacks/discuss/64206/Short-O(1)-amortized-C++-Java-Ruby
    // 这个比前一种方法更优的是， 每个元素在放进 in stack 之后只会被转移一次到 out 中， 然后就 pop 了， 所以 amortized cost for each operation is O(1).
    Stack<Integer> out;
    Stack<Integer> in;

    /** Initialize your data structure here. */
    public MyQueue() {
        out = new Stack<>(); // always pull out data from this stack
        in = new Stack<>(); // always push into this stack
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        in.push(x);
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        peek(); // check if there's still element on out stack
        return out.pop();
    }

    /** Get the front element. */
    public int peek() {
        if (out.isEmpty()) {
            // if out stack is empty, then move all elements in in stack pushed into previously
            while (!in.isEmpty()) {
                out.push(in.pop());
            }
        }
        return out.peek();
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return out.isEmpty() && in.isEmpty();
    }
}


// method1: O(N) time for push and pop and O(N) space
// class MyQueue {

//     Stack<Integer> major;
//     Stack<Integer> vice;

//     /** Initialize your data structure here. */
//     public MyQueue() {
//         major = new Stack<>(); // store like queue
//         vice = new Stack<>(); // cooperation
//     }

//     /** Push element x to the back of queue. */
//     public void push(int x) {
//         if (major.isEmpty()) {
//             major.push(x);
//             return;
//         }
//         transfer(major, vice);
//         major.push(x);
//         transfer(vice, major);
//     }
//     private void transfer(Stack<Integer> from, Stack<Integer> to) {
//         while (!from.isEmpty()) {
//             to.push(from.pop());
//         }
//     }

//     /** Removes the element from in front of queue and returns that element. */
//     public int pop() {
//         return major.pop();
//     }

//     /** Get the front element. */
//     public int peek() {
//         return major.peek();
//     }

//     /** Returns whether the queue is empty. */
//     public boolean empty() {
//         return major.isEmpty();
//     }
// }

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */

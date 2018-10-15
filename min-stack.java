// 155. Min Stack
// DescriptionHintsSubmissionsDiscussSolution
// Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
//
// push(x) -- Push element x onto stack.
// pop() -- Removes the element on top of the stack.
// top() -- Get the top element.
// getMin() -- Retrieve the minimum element in the stack.
// Example:
// MinStack minStack = new MinStack();
// minStack.push(-2);
// minStack.push(0);
// minStack.push(-3);
// minStack.getMin();   --> Returns -3.
// minStack.pop();
// minStack.top();      --> Returns 0.
// minStack.getMin();   --> Returns -2.


// method 1: use 2 stacks
// class MinStack {

//     Stack<Integer> stack; // record each value as push order
//     Stack<Integer> minStack; // recode only min value for each pushing

//     /** initialize your data structure here. */
//     public MinStack() {
//         stack = new Stack<Integer>();
//         minStack = new Stack<Integer>();
//     }

//     public void push(int x) {
//         stack.push(x);
//         if (minStack.isEmpty()) {
//             minStack.push(x);
//         } else {
//             minStack.push(Math.min(x, minStack.peek()));
//         }
//     }

//     public void pop() {
//         stack.pop();
//         minStack.pop();
//     }

//     public int top() {
//         return stack.peek();
//     }

//     public int getMin() {
//         return minStack.peek();
//     }
// }

// method 2: use 1 stack and 1 variable to record current min
// ref: https://leetcode.com/problems/min-stack/discuss/49014/
class MinStack {

    Stack<Integer> stack; // record each value as push order
    int min; // recode only min value for each pushing

    /** initialize your data structure here. */
    public MinStack() {
        stack = new Stack<Integer>();
        min = Integer.MAX_VALUE;
    }

    public void push(int x) {
        if (x <= min) {
            stack.push(min);
            min = x;
        }
        stack.push(x);
    }

    public void pop() {
        if (stack.pop() == min) {
            min = stack.pop();
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return min;
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */

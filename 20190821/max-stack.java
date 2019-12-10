// 716. Max Stack
// DescriptionHintsSubmissionsDiscussSolution
//
// Design a max stack that supports push, pop, top, peekMax and popMax.
//
//     push(x) -- Push element x onto stack.
//     pop() -- Remove the element on top of the stack and return it.
//     top() -- Get the element on the top.
//     peekMax() -- Retrieve the maximum element in the stack.
//     popMax() -- Retrieve the maximum element in the stack, and remove it. If you find more than one maximum elements, only remove the top-most one.
//
// Example 1:
//
// MaxStack stack = new MaxStack();
// stack.push(5);
// stack.push(1);
// stack.push(5);
// stack.top(); -> 5
// stack.popMax(); -> 5
// stack.top(); -> 1
// stack.peekMax(); -> 5
// stack.pop(); -> 1
// stack.top(); -> 5
//
// Note:
//
//     -1e7 <= x <= 1e7
//     Number of operations won't exceed 10000.
//     The last four operations won't be called when stack is empty.
//
// Seen this question in a real interview before?
//
//     Difficulty:Easy


class MaxStack {

    private class ListNode {
        ListNode prev;
        ListNode next;
        int val;

        ListNode(int val) {
            this.val = val;
            prev = null;
            next = null;
        }
    }

    ListNode head;
    ListNode tail;
    TreeMap<Integer, LinkedList<ListNode>> map;

    /** initialize your data structure here. */
    public MaxStack() {
        head = new ListNode(0);
        tail = new ListNode(0);
        head.next = tail;
        tail.prev = head;
        map = new TreeMap<>();
    }

    public void push(int x) {
        ListNode curr = new ListNode(x);
        tail.prev.next = curr;
        curr.prev = tail.prev;
        curr.next = tail;
        tail.prev = curr;

        LinkedList<ListNode> list = map.computeIfAbsent(x, dummy -> (new LinkedList<>()));
        list.add(curr);
    }

    public int pop() {
        if (tail.prev == head) {
            return -1;
        }
        ListNode last = tail.prev;
        this.remove(last);
        return last.val;
    }

    public int top() {
        return tail.prev.val;
    }

    public int peekMax() {
        return map.lastKey();
    }

    public int popMax() {
        int max = this.peekMax();
        LinkedList<ListNode> list = map.get(max);
        ListNode node = list.get(list.size() - 1);
        this.remove(node);
        return max;
    }

    private boolean remove(ListNode node) {
        node.next.prev = node.prev;
        node.prev.next = node.next;

        LinkedList<ListNode> list = map.get(node.val);
        list.remove(list.size() - 1);
        if (list.size() == 0) {
            map.remove(node.val);
        }
        return true;
    }
}

/*
class MaxStack {

    Stack<Integer> stack;
    Stack<Integer> maxs;

    // initialize your data structure here.
    public MaxStack() {
        stack = new Stack<>();
        maxs = new Stack<>();
    }

    public void push(int x) {
        stack.push(x);
        maxs.push(Math.max(x, maxs.isEmpty() ? Integer.MIN_VALUE : maxs.peek()));
    }

    public int pop() {
        maxs.pop();
        return stack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int peekMax() {
        return maxs.peek();
    }

    public int popMax() {
        Stack<Integer> temp = new Stack<>();
        int max = maxs.peek();
        while (!stack.isEmpty() && stack.peek() != max) {
            temp.push(stack.pop());
            // 在 temporally remove 过程中， remove 掉的这个 max 可能一直会是在后面的 max， 所以 maxs 也要相对应的 remove
            // 在下面加回来的过程中， 用 push() 把 number 和 max 都加回来
            maxs.pop();
        }
        // remove max
        stack.pop();
        maxs.pop();
        while (!temp.isEmpty()) {
            this.push(temp.pop());
        }
        return max;
    }
}
*/

/**
 * Your MaxStack object will be instantiated and called as such:
 * MaxStack obj = new MaxStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.peekMax();
 * int param_5 = obj.popMax();
 */

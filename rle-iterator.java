// 900. RLE Iterator
// User Accepted: 1572
// User Tried: 1914
// Total Accepted: 1598
// Total Submissions: 4701
// Difficulty: Medium
// Write an iterator that iterates through a run-length encoded sequence.
//
// The iterator is initialized by RLEIterator(int[] A), where A is a run-length encoding of some sequence.  More specifically, for all even i, A[i] tells us the number of times that the non-negative integer value A[i+1] is repeated in the sequence.
//
// The iterator supports one function: next(int n), which exhausts the next n elements (n >= 1) and returns the last element exhausted in this way.  If there is no element left to exhaust, next returns -1 instead.
//
// For example, we start with A = [3,8,0,9,2,5], which is a run-length encoding of the sequence [8,8,8,5,5].  This is because the sequence can be read as "three eights, zero nines, two fives".
//
//
//
// Example 1:
//
// Input: ["RLEIterator","next","next","next","next"], [[[3,8,0,9,2,5]],[2],[1],[1],[2]]
// Output: [null,8,8,5,-1]
// Explanation:
// RLEIterator is initialized with RLEIterator([3,8,0,9,2,5]).
// This maps to the sequence [8,8,8,5,5].
// RLEIterator.next is then called 4 times:
//
// .next(2) exhausts 2 terms of the sequence, returning 8.  The remaining sequence is now [8, 5, 5].
//
// .next(1) exhausts 1 term of the sequence, returning 8.  The remaining sequence is now [5, 5].
//
// .next(1) exhausts 1 term of the sequence, returning 5.  The remaining sequence is now [5].
//
// .next(2) exhausts 2 terms, returning -1.  This is because the first term exhausted was 5,
// but the second term did not exist.  Since the last term exhausted does not exist, we return -1.
//
// Note:
//
// 0 <= A.length <= 1000
// A.length is an even integer.
// 0 <= A[i] <= 10^9
// There are at most 1000 calls to RLEIterator.next(int n) per test case.
// Each call to RLEIterator.next(int n) will have 1 <= n <= 10^9.


/*

// mytry:

class RLEIterator {

    private Queue<Node> queue;
    private long len;

    public RLEIterator(int[] A) {
        queue = new LinkedList<Node>();
        for (int i = 0; i < A.length; i += 2) {
            queue.offer(new Node(A[i], A[i + 1]));
            len += A[i];
        }
    }

    public int next(int n) {
        if (n > len) {
            len = 0; // remember to set len value of 0, since all of numbers have been polled
            return -1;
        }
        int result = 0;
        while (n > 0) {
            int num = queue.peek().freq;
            result = queue.peek().val;
            if (num > n) {
                queue.peek().freq -= n;
                len -= n;
                break;
            } else if (num == n) {
                queue.poll();
                len -= n;
                break;
            } else {
                queue.poll();
                n -= num;
                len -= num;
            }
        }
        // System.out.println("rest:  " + len);
        return result;
    }

    private class Node {
        private int val;
        private int freq;
        public Node(int f, int v) {
            val = v;
            freq = f;
        }
    }
}
*/

class RLEIterator {

    private int[] nums;
    private int index;

    public RLEIterator(int[] A) {
        // copy the original array directly
        nums = A;
        index = 0;
    }

    public int next(int n) {
        while (index < nums.length && n > nums[index]) {
            // there's still elements in array, and n is larger which means this element would be poll out totally
            n -= nums[index];
            index += 2;
        }
        if (index >= nums.length) {
            return -1;
        }
        nums[index] -= n;
        return nums[index + 1];
    }
}

/**
 * Your RLEIterator object will be instantiated and called as such:
 * RLEIterator obj = new RLEIterator(A);
 * int param_1 = obj.next(n);
 */

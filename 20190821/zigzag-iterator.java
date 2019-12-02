// 281. Zigzag Iterator
// DescriptionHintsSubmissionsDiscussSolution
//
// Given two 1d vectors, implement an iterator to return their elements alternately.
//
//
//
// Example:
//
// Input:
// v1 = [1,2]
// v2 = [3,4,5,6]
// Output: [1,3,2,4,5,6]
// Explanation: By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,3,2,4,5,6].
//
//
//
// Follow up:
//
// What if you are given k 1d vectors? How well can your code be extended to such cases?
//
// Clarification for the follow up question:
// The "Zigzag" order is not clearly defined and is ambiguous for k > 2 cases. If "Zigzag" does not look right to you, replace "Zigzag" with "Cyclic". For example:
//
// Input:
// [1,2,3]
// [4,5,6,7]
// [8,9]
//
// Output: [1,4,8,2,5,9,3,6,7].
//
// Seen this question in a real interview before?
//
//     Difficulty:Medium


public class ZigzagIterator {

    // 如果有 k 个 list， 就不能交替进行， 可以用一个 queue 来模拟顺序

    Queue<Iterator<Integer>> queue;

    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        // 注意这里的顺序
        queue = new LinkedList<>();
        if (!v1.isEmpty()) {
            queue.offer(v1.iterator());
        }
        if (!v2.isEmpty()) {
            queue.offer(v2.iterator());
        }
    }

    public int next() {
        // queue 里面放的都是 valid iterator
        Iterator<Integer> curr = queue.poll();
        int result = curr.next();
        // 还有， 放进 queue 里等待下次
        if (curr.hasNext()) {
            queue.offer(curr);
        }
        return result;
    }

    public boolean hasNext() {
        // 看 queue 里是否还有 valid iterator
        return !queue.isEmpty();
    }
}

/**
public class ZigzagIterator {

    // 用两个 iterator 交替进行

    Iterator<Integer> i;
    Iterator<Integer> j;

    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        // 注意这里的顺序
        i = v2.iterator();
        j = v1.iterator();
    }

    public int next() {
        // 先判断 j 是否有， j 有的话， 就 j i 互换
        if (j.hasNext()) {
            Iterator<Integer> temp = j;
            j = i;
            i = temp;
        }
        return i.next();
    }

    public boolean hasNext() {
        return i.hasNext() || j.hasNext();
    }
}
*/

/**
 * Your ZigzagIterator object will be instantiated and called as such:
 * ZigzagIterator i = new ZigzagIterator(v1, v2);
 * while (i.hasNext()) v[f()] = i.next();
 */

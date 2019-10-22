// 341. Flatten Nested List Iterator
// DescriptionHintsSubmissionsDiscussSolution
//
// Given a nested list of integers, implement an iterator to flatten it.
//
// Each element is either an integer, or a list -- whose elements may also be integers or other lists.
//
// Example 1:
//
// Input: [[1,1],2,[1,1]]
// Output: [1,1,2,1,1]
// Explanation: By calling next repeatedly until hasNext returns false,
//              the order of elements returned by next should be: [1,1,2,1,1].
//
// Example 2:
//
// Input: [1,[4,[6]]]
// Output: [1,4,6]
// Explanation: By calling next repeatedly until hasNext returns false,
//              the order of elements returned by next should be: [1,4,6].
//
// Seen this question in a real interview before?
//
//     Difficulty:Medium


/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
public class NestedIterator implements Iterator<Integer> {

    // 1. use a stack to store NestedInteger
    // Stack<NestedInteger> stack;

    // 2. use a stack to store iterator of NestedInteger
    Stack<ListIterator<NestedInteger>> stack;

    public NestedIterator(List<NestedInteger> nestedList) {
        // 1.
        // stack = new Stack<>();
        // for (int i = nestedList.size() - 1; i >= 0; i--) {
        //     stack.push(nestedList.get(i));
        // }

        // 2.
        stack = new Stack<>();
        stack.push(nestedList.listIterator());
    }

    @Override
    public Integer next() {
        // 1.
        // if (!hasNext()) {
        //     return null;
        // }
        // return stack.pop().getInteger();

        // 2.
        if (!hasNext()) {
            return null;
        }
        return stack.peek().next().getInteger();
    }

    @Override
    public boolean hasNext() {
        // 1.
        // while (!stack.isEmpty() && !stack.peek().isInteger()) {
        //     List<NestedInteger> list = stack.pop().getList();
        //     for (int i = list.size() - 1; i >= 0; i--) {
        //         stack.push(list.get(i));
        //     }
        // }
        // return !stack.isEmpty();

        // 2.
        while (!stack.isEmpty()) {
            // 当前 NestedInteger 没有下一个了
            if (!stack.peek().hasNext()) {
                stack.pop();
            } else{
                // 查看是否下一个是 integer
                NestedInteger next = stack.peek().next();
                if (next.isInteger()) {
                    // 因为做了 next(), 所以往回移一位
                    stack.peek().previous();
                    return true;
                } else {
                    // 不是 integer 的话， 那这个 NestedInteger 的 ListIterator 放进去
                    stack.push(next.getList().listIterator());
                }
            }
        }
        return !stack.isEmpty();
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */

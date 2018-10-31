// 385. Mini Parser
// DescriptionHintsSubmissionsDiscussSolution
// Given a nested list of integers represented as a string, implement a parser to deserialize it.
//
// Each element is either an integer, or a list -- whose elements may also be integers or other lists.
//
// Note: You may assume that the string is well-formed:
//
// String is non-empty.
// String does not contain white spaces.
// String contains only digits 0-9, [, - ,, ].
// Example 1:
//
// Given s = "324",
//
// You should return a NestedInteger object which contains a single integer 324.
// Example 2:
//
// Given s = "[123,[456,[789]]]",
//
// Return a NestedInteger object containing a nested list with 2 elements:
//
// 1. An integer containing value 123.
// 2. A nested list containing two elements:
//     i.  An integer containing value 456.
//     ii. A nested list with one element:
//          a. An integer containing value 789.



/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *     // Constructor initializes an empty nested list.
 *     public NestedInteger();
 *
 *     // Constructor initializes a single integer.
 *     public NestedInteger(int value);
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // Set this NestedInteger to hold a single integer.
 *     public void setInteger(int value);
 *
 *     // Set this NestedInteger to hold a nested list and adds a nested integer to it.
 *     public void add(NestedInteger ni);
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
class Solution {
    public NestedInteger deserialize(String s) {
        if (s == null || s.isEmpty()) {
            return new NestedInteger();
        }

        if (s.charAt(0) != '[') {
            return new NestedInteger(Integer.valueOf(s));
        }

        return method1(s);
    }

    private NestedInteger method1(String s) {
        // ref: https://leetcode.com/problems/mini-parser/discuss/86066/An-Java-Iterative-Solution
        Stack<NestedInteger> stack = new Stack<>();
        int left = 0;
        NestedInteger curr = null;
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            if (c == '[') {
                // 只有碰到了下一个开始的 '[' 才 push 进 stack， 因为我们返回的是 curr 而不是 stack 的 top
                if (curr != null) {
                    stack.push(curr);
                }
                // 重新设置当前的 NestedInteger
                curr = new NestedInteger();
                left = right + 1;
            } else if (c == ']') {
                // 一个数字结束了
                String num = s.substring(left, right);
                if (!num.isEmpty()) {
                    // 注意有可能为空， 即 []
                    curr.add(new NestedInteger(Integer.parseInt(num)));
                }
                // 加到前一个 NestedInteger 里面
                if (!stack.isEmpty()) {
                    NestedInteger prev = stack.pop();
                    prev.add(curr);
                    curr = prev;
                }
                left = right + 1;
            } else if (c == ',') {
                // 逗号还是同一层， 加到当前 NestedInteger 即可
                if (s.charAt(right - 1) != ']') {
                    int num = Integer.parseInt(s.substring(left, right));
                    curr.add(new NestedInteger(num));
                }
                left = right + 1;
            }
        }
        return curr;
    }
}

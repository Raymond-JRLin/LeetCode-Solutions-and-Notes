// 255. Verify Preorder Sequence in Binary Search Tree

// Given an array of numbers, verify whether it is the correct preorder traversal sequence of a binary search tree.
//
// You may assume each number in the sequence is unique.
//
// Consider the following binary search tree:
//
//      5
//     / \
//    2   6
//   / \
//  1   3
// Example 1:
//
// Input: [5,2,6,1,3]
// Output: false
// Example 2:
//
// Input: [5,2,1,3,6]
// Output: true
// Follow up:
// Could you do it using only constant space complexity?


class Solution {
    public boolean verifyPreorder(int[] preorder) {
        if (preorder == null || preorder.length == 0) {
            return true;
        }

        // return method1(preorder);

        // return method2(preorder);

        return method3(preorder);
    }

    private boolean method3(int[] preorder) {
        // recursion: D&C, similar to construct tree from inorder, the 1st position in array must be the root, then values larger than it should be right, and smallers should be left
        // ref: https://leetcode.com/problems/verify-preorder-sequence-in-binary-search-tree/discuss/68144/Java-O(n-log-n)-time-and-O(1)-space-recursive-solution
        return verify(preorder, 0, preorder.length - 1, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    private boolean verify(int[] preorder, int start, int end, int min, int max) {
        // when start == end, still need to judge, since it should be compare with last curr
        if (start > end) {
            return true;
        }
        int curr = preorder[start];
        // check
        if (curr < min || curr > max) {
            return false;
        }
        // split left and right subtree
        int right = start + 1;
        while (right <= end && preorder[right] < curr) {
            right++;
        }
        // D&C
        return verify(preorder, start + 1, right - 1, min, curr) && verify(preorder, right, end, curr, max);
    }

    private boolean method2(int[] preorder) {
        // almost same to method1, but modify given array directly without using stack, O(1) space
        int low = Integer.MIN_VALUE;
        // use array to imitate stack
        int i = -1; // use i as a pointer, pointing the low in the array
        for (int num : preorder) {
            if (num < low) {
                return false;
            }
            while (i >= 0 && num > preorder[i]) {
                // if current num is larger, then make it as new low value, and move i front by 1
                low = preorder[i--];
            }
            // make next position of i as current num
            preorder[++i] = num;
        }
        return true;
    }

    private boolean method1(int[] preorder) {
        // do as preorder traverse, O(N) time and space
        // ref: https://leetcode.com/problems/verify-preorder-sequence-in-binary-search-tree/discuss/68142/Java-O(n)-and-O(1)-extra-space
        int low = Integer.MIN_VALUE;
        Stack<Integer> stack = new Stack<>();
        for (int num : preorder) {
            if (num < low) {
                // 在更后面的位置反而有一个更小的数
                return false;
            }
            while (!stack.isEmpty() && num > stack.peek()) {
                // 如果有一个更大的数， 那么此时应该在 right subtree 的位置上， 把前面比它小的都 pop 出来， 即左子树和它的 root， 并且成为新的下限， 后面的数都应该比它大
                low = stack.pop();
            }
            stack.push(num);
        }
        return true;
    }
}

// 538. Convert BST to Greater Tree
// DescriptionHintsSubmissionsDiscussSolution
// Given a Binary Search Tree (BST), convert it to a Greater Tree such that every key of the original BST is changed to the original key plus sum of all keys greater than the original key in BST.
//
// Example:
//
// Input: The root of a Binary Search Tree like this:
//               5
//             /   \
//            2     13
//
// Output: The root of a Greater Tree like this:
//              18
//             /   \
//           20     13


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode convertBST(TreeNode root) {
        if (root == null) {
            return root;
        }

        // return method1(root);

        return method2(root);

        // return method3(root);
    }

    private TreeNode method3(TreeNode root) {
        // iteration
        // ref: https://leetcode.com/problems/convert-bst-to-greater-tree/discuss/100516/Java-Three-O(n)-Methods:-Recursive-Iterative-and-Morris-Traversal
        int sum = 0;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        while (!stack.isEmpty() || curr != null) {
            // get the right-most node
            while (curr != null) {
                stack.push(curr);
                curr = curr.right;
            }
            curr = stack.pop();
            curr.val += sum; // update current root
            sum = curr.val; // keep adding total sum
            curr = curr.left; // go left subtree
        }
        return root;
    }

    private TreeNode method2(TreeNode root) {
        // recursion with global variable
        // ref: https://leetcode.com/problems/convert-bst-to-greater-tree/discuss/100506/Java-Recursive-O(n)-time
        // 这个题有点儿不一样在于， 走完了右边可以得到右子树的 sum， 加到当前的 root 上， 但是此时左子树相对于 root 的 root 也是右边， 也要把左子树的 sum 返回到上一层， 就是说 sum 的值要是不断累加的， 哪怕要往左走， 相对于上一层也是右子树
        dfs(root);
        return root;
    }
    private int total; // use a global variable
    private void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        dfs(root.right); // go right subtree first, with updating total
        root.val += total;
        total = root.val; // update sum of right subtree with this root value
        dfs(root.left); // go left
    }

    private TreeNode mytry(TreeNode root) {
        // ref: https://leetcode.com/problems/convert-bst-to-greater-tree/discuss/100619/Java-6-lines
        recursion(root, 0);
        return root;
    }
    private int recursion(TreeNode root, int sum) {
        // it's a bit different from usuall D & C, since after we go right subtree and update current root, then we may go left subtree, and we should return both left and right to upper level
        // 这里不能处理走到 null 节点的返回值， 因为不知道是右边还是左边是 null
        if (root.right != null) {
            sum = recursion(root.right, sum);
        }

        root.val += sum;
        sum = root.val;

        if (root.left != null) {
            return recursion(root.left, sum);
        } else {
            return sum;
        }
    }
}

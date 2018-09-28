// 285. Inorder Successor in BST
// DescriptionHintsSubmissionsDiscussSolution
// Given a binary search tree and a node in it, find the in-order successor of that node in the BST.
//
// Note: If the given node has no in-order successor in the tree, return null.
//
// Example 1:
//
// Input: root = [2,1,3], p = 1
//
//   2
//  / \
// 1   3
//
// Output: 2
// Example 2:
//
// Input: root = [5,3,6,2,4,null,null,1], p = 6
//
//       5
//      / \
//     3   6
//    / \
//   2   4
//  /
// 1
//
// Output: null


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
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (root == null) {
            return null;
        }

        // return mytry(root, p);

        // return method2(root, p);

        return method3(root, p);
    }

    private TreeNode method3(TreeNode root, TreeNode p) {
        return recursion(root, p);
    }
    private TreeNode recursion(TreeNode root, TreeNode p) {
        if (root == null) {
            return null;
        }
        if (root.val <= p.val) {
            return recursion(root.right, p);
        } else {
            // 我的理解就是和 method2 一样， 要记录当前为潜在答案， 但是还要 recursion 左边， 那么先 recursion 左边， 如果左边有答案的话， 那么左边的更小， 是 successor； 不然的话当前 root 是
            TreeNode candidate = recursion(root.left, p);
            if (candidate != null) {
                return candidate;
            } else {
                return root;
            }
        }
    }

    private TreeNode method2(TreeNode root, TreeNode p) {
        // iteration: 如果当前 node 比 p 更小， 那么就向右走， 它自己和左边的都不可能是结果； 如果 root 更大， 那么它自己有可能是结果， 也有可能结果在 root 左子树中的某一个。 和做过的 AVL tree 的 proj 一样， 因为是 BST 所以只要向左走就要记录下当前 node 作为潜在可能， 向右走就无所谓了
        TreeNode result = null;
        while (root != null) {
            if (root.val <= p.val) {
                root = root.right;
            } else {
                result = root;
                root = root.left;
            }
        }
        return result;
    }

    private TreeNode mytry(TreeNode root, TreeNode p) {
        // straightforward: get inorder list and then check
        List<TreeNode> list = new ArrayList<>();
        Stack<Guide> stack = new Stack<>();
        Guide curr = new Guide(root, 0);
        stack.push(curr);
        while (!stack.isEmpty()) {
            curr = stack.pop();
            if (curr.node == null) {
                continue;
            }
            if (curr.ope == 1) {
                list.add(curr.node);
            } else {
                stack.push(new Guide(curr.node.right, 0));
                stack.push(new Guide(curr.node, 1));
                stack.push(new Guide(curr.node.left, 0));
            }
        }
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (list.get(i).val == p.val) {
                // check if it's the last one
                return i == size - 1 ? null : list.get(i + 1);
            }
        }
        return null;

    }
    class Guide {
        public TreeNode node;
        public int ope;
        public Guide (TreeNode node, int ope) {
            this.node = node;
            this.ope = ope;
        }
    }
}

// 199. Binary Tree Right Side View
// DescriptionHintsSubmissionsDiscussSolution
// Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.
//
// Example:
//
// Input: [1,2,3,null,5,null,4]
// Output: [1, 3, 4]
// Explanation:
//
//    1            <---
//  /   \
// 2     3         <---
//  \     \
//   5     4       <---


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
    public List<Integer> rightSideView(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }

        // wrong
        // return mytry(root);

        return method1(root);
    }

    private List<Integer> method1(TreeNode root) {
        // so we still should offer all children into the queue, then when we iterate the last one (right-most), add it into result list
        List<Integer> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size > 0) {
                TreeNode curr = queue.poll();
                if (size == 1) {
                    result.add(curr.val);
                }
                if (curr.left != null) {
                    queue.offer(curr.left);
                }
                if (curr.right != null) {
                    queue.offer(curr.right);
                }
                size--;
            }
        }
        return result;
    }

    private List<Integer> mytry(TreeNode root) {
        // WRONG
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        List<Integer> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            result.add(curr.val);
            // 错误在于， 总是从最右边放， 但是问题在于如果当前的 node 有左右子树， 但只放了 right child， 而这个 right child 并没有 children 而是它左边的 node  有下一层， 那么这个时候就会出错
            if (curr.right != null) {
                queue.offer(curr.right);
            } else if (curr.left != null) {
                queue.offer(curr.left);
            }
        }
        return result;
    }
}

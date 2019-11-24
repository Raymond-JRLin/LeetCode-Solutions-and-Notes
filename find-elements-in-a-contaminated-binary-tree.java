// 1261. Find Elements in a Contaminated Binary Tree
//
//     User Accepted: 2201
//     User Tried: 2337
//     Total Accepted: 2226
//     Total Submissions: 3136
//     Difficulty: Medium
//
// Given a binary tree with the following rules:
//
//     root.val == 0
//     If treeNode.val == x and treeNode.left != null, then treeNode.left.val == 2 * x + 1
//     If treeNode.val == x and treeNode.right != null, then treeNode.right.val == 2 * x + 2
//
// Now the binary tree is contaminated, which means all treeNode.val have been changed to -1.
//
// You need to first recover the binary tree and then implement the FindElements class:
//
//     FindElements(TreeNode* root) Initializes the object with a contamined binary tree, you need to recover it first.
//     bool find(int target) Return if the target value exists in the recovered binary tree.
//
//
//
// Example 1:
//
// Input
// ["FindElements","find","find"]
// [[[-1,null,-1]],[1],[2]]
// Output
// [null,false,true]
// Explanation
// FindElements findElements = new FindElements([-1,null,-1]);
// findElements.find(1); // return False
// findElements.find(2); // return True
//
// Example 2:
//
// Input
// ["FindElements","find","find","find"]
// [[[-1,-1,-1,-1,-1]],[1],[3],[5]]
// Output
// [null,true,true,false]
// Explanation
// FindElements findElements = new FindElements([-1,-1,-1,-1,-1]);
// findElements.find(1); // return True
// findElements.find(3); // return True
// findElements.find(5); // return False
//
// Example 3:
//
// Input
// ["FindElements","find","find","find","find"]
// [[[-1,null,-1,-1,null,-1]],[2],[3],[4],[5]]
// Output
// [null,true,false,false,true]
// Explanation
// FindElements findElements = new FindElements([-1,null,-1,-1,null,-1]);
// findElements.find(2); // return True
// findElements.find(3); // return False
// findElements.find(4); // return False
// findElements.find(5); // return True
//
//
//
// Constraints:
//
//     TreeNode.val == -1
//     The height of the binary tree is less than or equal to 20
//     The total number of nodes is between [1, 10^4]
//     Total calls of find() is between [1, 10^4]
//     0 <= target <= 10^6
//


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class FindElements {

    // TreeNode root;

    // method2
    // 可以认为 root 这颗 tree 已经给了， 可以在那原本之上改 val
    // 用个 set 把重新赋值过后的的 val 放入， 就可以直接查看了
    Set<Integer> set;

    public FindElements(TreeNode root) {
        // root.val = 0;
        // recover(root);
        // this.root = root;

        // method2
        set = new HashSet<>();
        recursion(root, 0);
    }

    private void recursion(TreeNode root, int num) {
        if (root == null) {
            return;
        }
        set.add(num);
        root.val = num;
        recursion(root.left, num * 2 + 1);
        recursion(root.right, num * 2 + 2);
    }

    private void recover(TreeNode root) {
        if (root.left != null) {
            root.left.val = root.val * 2 + 1;
            recover(root.left);
        }
        if (root.right != null) {
            root.right.val = root.val * 2 + 2;
            recover(root.right);
        }
    }

    public boolean find(int target) {
        // TreeNode head = root;
        // return dfs(head, target);

        // method2
        return set.contains(target);
    }

    private boolean dfs(TreeNode curr, int target) {
        if (curr == null) {
            return false;
        }
        if (curr.val == target) {
            return true;
        }
        return dfs(curr.left, target) || dfs(curr.right, target);
    }
}

/**
 * Your FindElements object will be instantiated and called as such:
 * FindElements obj = new FindElements(root);
 * boolean param_1 = obj.find(target);
 */

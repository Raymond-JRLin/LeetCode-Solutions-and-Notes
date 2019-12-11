// 1145. Binary Tree Coloring Game
// DescriptionHintsSubmissionsDiscussSolution
//
// Two players play a turn based game on a binary tree.  We are given the root of this binary tree, and the number of nodes n in the tree.  n is odd, and each node has a distinct value from 1 to n.
//
// Initially, the first player names a value x with 1 <= x <= n, and the second player names a value y with 1 <= y <= n and y != x.  The first player colors the node with value x red, and the second player colors the node with value y blue.
//
// Then, the players take turns starting with the first player.  In each turn, that player chooses a node of their color (red if player 1, blue if player 2) and colors an uncolored neighbor of the chosen node (either the left child, right child, or parent of the chosen node.)
//
// If (and only if) a player cannot choose such a node in this way, they must pass their turn.  If both players pass their turn, the game ends, and the winner is the player that colored more nodes.
//
// You are the second player.  If it is possible to choose such a y to ensure you win the game, return true.  If it is not possible, return false.
//
//
//
// Example 1:
//
// Input: root = [1,2,3,4,5,6,7,8,9,10,11], n = 11, x = 3
// Output: true
// Explanation: The second player can choose the node with value 2.
//
//
//
// Constraints:
//
//     root is the root of a binary tree with n nodes and distinct node values from 1 to n.
//     n is odd.
//     1 <= x <= n <= 100
//
// Seen this question in a real interview before?
//
//     Difficulty:Medium


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
    public boolean btreeGameWinningMove(TreeNode root, int n, int x) {

        return method1(root, n, x);
    }

    // 题目有点费解
    // 意思是一开始第一个 player 涂色在了 x 位置， 然后我们需要找一个位置去染色， 然后我们能赢
    // 经过一开始的两个涂色后， 之后只能在【涂过色】的 node 的 neighbor 上涂色
    // 所以一开始的涂色 node 决定了之后我们能够涂多少个
    // 所以为了阻击第一位 player 能涂的范围， 我们要去抢 x node 的 left/right/parent 位置
    // 任意一个位置为 root 的子树的个数 > 总数 / 2， 那么我们就可以控制更多的 node
    // n 是奇数， 所以没有 tie

    private boolean method1(TreeNode root, int n, int x) {
        leftCount = 0;
        rightCount = 0;
        count(root, x);
        return leftCount > n / 2 || rightCount > n / 2 || (n - leftCount - rightCount - 1) > n / 2;
    }
    int leftCount, rightCount;
    private int count(TreeNode root, int x) {
        if (root == null) {
            return 0;
        }
        int left = count(root.left, x);
        int right = count(root.right, x);
        if (root.val == x) {
            leftCount = left;
            rightCount = right;
        }
        return left + right + 1;
    }
}

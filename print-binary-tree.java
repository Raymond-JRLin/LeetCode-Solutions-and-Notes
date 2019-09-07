// 655. Print Binary Tree
// DescriptionHintsSubmissionsDiscussSolution
// Print a binary tree in an m*n 2D string array following these rules:
//
// The row number m should be equal to the height of the given binary tree.
// The column number n should always be an odd number.
// The root node's value (in string format) should be put in the exactly middle of the first row it can be put. The column and the row where the root node belongs will separate the rest space into two parts (left-bottom part and right-bottom part). You should print the left subtree in the left-bottom part and print the right subtree in the right-bottom part. The left-bottom part and the right-bottom part should have the same size. Even if one subtree is none while the other is not, you don't need to print anything for the none subtree but still need to leave the space as large as that for the other subtree. However, if two subtrees are none, then you don't need to leave space for both of them.
// Each unused space should contain an empty string "".
// Print the subtrees following the same rules.
// Example 1:
// Input:
//      1
//     /
//    2
// Output:
// [["", "1", ""],
//  ["2", "", ""]]
// Example 2:
// Input:
//      1
//     / \
//    2   3
//     \
//      4
// Output:
// [["", "", "", "1", "", "", ""],
//  ["", "2", "", "", "", "3", ""],
//  ["", "", "4", "", "", "", ""]]
// Example 3:
// Input:
//       1
//      / \
//     2   5
//    /
//   3
//  /
// 4
// Output:
//
// [["",  "",  "", "",  "", "", "", "1", "",  "",  "",  "",  "", "", ""]
//  ["",  "",  "", "2", "", "", "", "",  "",  "",  "",  "5", "", "", ""]
//  ["",  "3", "", "",  "", "", "", "",  "",  "",  "",  "",  "", "", ""]
//  ["4", "",  "", "",  "", "", "", "",  "",  "",  "",  "",  "", "", ""]]
// Note: The height of binary tree is in the range of [1, 10].
//
// Seen this question in a real interview before?
// Difficulty:Medium


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
    public List<List<String>> printTree(TreeNode root) {
        if (root == null) {
            return new ArrayList<List<String>>();
        }
        // row m = height of BT, column n = 2 ^ m - 1
        int height = getHeight(root);
        // int cols = (int) Math.pow(2, height) - 1; // Math.pow() 返回的是 double， 要强制转换， 或者使用 bitwise
        int cols = (1 << height) - 1; // bitwise 要记得用 ()
        // recursion 直接打印不好打， 可以换成先全部是 "" （打印的效果， 应该说什么都没有， 用空占一个位置）， 然后在对应位置上替换成相应的 val
        List<String> list = new ArrayList<>();
        for (int i = 0; i < cols; i++) {
            list.add(""); // add an null
        }
        // 组装好最后的
        List<List<String>> result = new ArrayList<>();
        for (int i = 0; i < height; i++) {
            result.add(new ArrayList<String>(list)); // DEEP COPY!!! otherwise we would get the same list for each row
        }
        printing(root, 0, cols - 1, height, 0, result);
        return result;
    }
    private int getHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left_height = getHeight(root.left);
        int right_height = getHeight(root.right);
        return Math.max(left_height, right_height) + 1;
    }
    private void printing(TreeNode root, int left, int right, int height, int row, List<List<String>> result) {
        if (row == height || root == null) {
            return;
        }
        int index = left + (right - left) / 2;
        // change value
        result.get(row).set(index, String.valueOf(root.val)); // 不能直接 =， 要用 set()
        // recursion
        printing(root.left, left, index - 1, height, row + 1, result);
        printing(root.right, index + 1, right, height, row + 1, result);
    }
}

// 666. Path Sum IV
// DescriptionHintsSubmissionsDiscussSolution
//
// If the depth of a tree is smaller than 5, then this tree can be represented by a list of three-digits integers.
//
// For each integer in this list:
//
//     The hundreds digit represents the depth D of this node, 1 <= D <= 4.
//     The tens digit represents the position P of this node in the level it belongs to, 1 <= P <= 8. The position is the same as that in a full binary tree.
//     The units digit represents the value V of this node, 0 <= V <= 9.
//
//
//
// Given a list of ascending three-digits integers representing a binary tree with the depth smaller than 5, you need to return the sum of all paths from the root towards the leaves.
//
// Example 1:
//
// Input: [113, 215, 221]
// Output: 12
// Explanation:
// The tree that the list represents is:
//     3
//    / \
//   5   1
//
// The path sum is (3 + 5) + (3 + 1) = 12.
//
//
//
// Example 2:
//
// Input: [113, 221]
// Output: 4
// Explanation:
// The tree that the list represents is:
//     3
//      \
//       1
//
// The path sum is (3 + 1) = 4.
//
//
// Seen this question in a real interview before?
//
//     Difficulty:Medium


class Solution {
    public int pathSum(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        // return method1(nums);

        return method2(nums);
    }

    // 如果我们有一棵树， 要做 path sum， DFS 即可
    // 现在是一种特别的方式给 node， 第一个数是 level， 第二个数是这个 node 在这一 level 第几个， 第三个数才是 value
    // 同样地， 类似于在数组中的关系， 我们同样可以有第二个数来得到 root 的 children， 所以我们不需要真正的去建树， 只要拿到相对应的 index 即可

    private int method2(int[] nums) {
        // 类似于 DP 的想法， 把树构建在数组中
        // definition: f[i][j] := 第 i 层， 第 j 个 node 的 value 是 f[i][j]
        int[][] f = new int[5][9];
        // init: all 0 in f[0][j]
        // DP
        for (int num : nums) {
            int level = num / 10 / 10;
            int k = num / 10 % 10;
            int val = num % 10;
            f[level][k] = f[level - 1][(k + 1) / 2] + val;
        }
        // result
        int result = 0;
        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= 8; j++) {
                // 加 leaf node
                // 最后一层： 4， 或者在前面几层， 但是没有 left && right children
                if (i == 4 || f[i][j] != 0 && f[i + 1][j * 2 - 1] == 0 && f[i + 1][j * 2] == 0) {
                    result += f[i][j];
                }
            }
        }
        return result;
    }

    private int method1(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>(); // <index number, val>, where index is first 2 digit in num of nums[]
        for (int num : nums) {
            map.put(num / 10, num % 10);
        }
        result = 0;
        recursion(map, 11, 0);
        return result;
    }
    private int result;
    private void recursion(Map<Integer, Integer> map, int root, int prev) {
        int level = root / 10;
        int k = root % 10;
        int leftIndex = (level + 1) * 10 + (2 * k - 1); // 1-based
        int rightIndex = (level + 1) * 10 + (2 * k);

        int currSum = map.get(root) + prev;
        // leaf node
        if (!map.containsKey(leftIndex) && !map.containsKey(rightIndex)) {
            result += currSum;
            return;
        }
        if (map.containsKey(leftIndex)) {
            recursion(map, leftIndex, currSum);
        }
        if (map.containsKey(rightIndex)) {
            recursion(map, rightIndex, currSum);
        }
    }
}

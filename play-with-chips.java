// 1217. Play with Chips
// User Accepted:2829
// User Tried:3336
// Total Accepted:2882
// Total Submissions:5156
// Difficulty:Easy
// There are some chips, and the i-th chip is at position chips[i].
//
// You can perform any of the two following types of moves any number of times (possibly zero) on any chip:
//
// Move the i-th chip by 2 units to the left or to the right with a cost of 0.
// Move the i-th chip by 1 unit to the left or to the right with a cost of 1.
// There can be two or more chips at the same position initially.
//
// Return the minimum cost needed to move all the chips to the same position (any position).
//
//
//
// Example 1:
//
// Input: chips = [1,2,3]
// Output: 1
// Explanation: Second chip will be moved to positon 3 with cost 1. First chip will be moved to position 3 with cost 0. Total cost is 1.
// Example 2:
//
// Input: chips = [2,2,2,3,3]
// Output: 2
// Explanation: Both fourth and fifth chip will be moved to position two with cost 1. Total minimum cost will be 2.
//
//
// Constraints:
//
// 1 <= chips.length <= 100
// 1 <= chips[i] <= 10^9


class Solution {
    public int minCostToMoveChips(int[] chips) {
        if (chips == null || chips.length == 0) {
            return 0;
        }

        return mytry(chips);
    }

    private int mytry(int[] chips) {
        // 看了好几遍题目才看懂， chip[i] 表示第 i 片 chip 放在 chip[i] 的位置上， 这个 chip[i] 和 chip 数组的 index 没有关系
        // 画个图走个流程会比较好理解， 因为移动 2 不 cost， 所以最终目标位置不重要， 只和奇偶有关系
        // 偶数 index： 所有偶数移动都不 cost， 所有奇数移动只有 1 * SUM{奇数位置上的 chip 数}
        // 奇数 index 也是类似的
        // 所以只需要看是全移动到奇数位置， 还是偶数位置。 这取决于哪个位置上的总 chip 数更少， 这样 cost 小
        int n = chips.length;
        int odd = 0, even = 0;
        for (int pos : chips) {
            if (pos % 2 == 0) {
                even++;
            } else {
                odd++;
            }
        }
        return Math.min(odd, even);
    }
}

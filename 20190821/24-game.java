// 679. 24 Game
// DescriptionHintsSubmissionsDiscussSolution
//
// You have 4 cards each containing a number from 1 to 9. You need to judge whether they could operated through *, /, +, -, (, ) to get the value of 24.
//
// Example 1:
//
// Input: [4, 1, 8, 7]
// Output: True
// Explanation: (8-4) * (7-1) = 24
//
// Example 2:
//
// Input: [1, 2, 1, 2]
// Output: False
//
// Note:
//
//     The division operator / represents real division, not integer division. For example, 4 / (1 - 2/3) = 12.
//     Every operation done is between two numbers. In particular, we cannot use - as a unary operator. For example, with [1, 1, 1, 1] as input, the expression -1 - 1 - 1 - 1 is not allowed.
//     You cannot concatenate numbers together. For example, if the input is [1, 2, 1, 2], we cannot write this as 12 + 12.
//
// Seen this question in a real interview before?
//
//     Difficulty:Hard


class Solution {
    public boolean judgePoint24(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }

        return method1(nums);
    }

    private boolean method1(int[] nums) {
        List<Double> list = new ArrayList<>();
        for (int num : nums) {
            list.add((double) num);
        }
        return dfs(list, 0.001);
    }
    private boolean dfs(List<Double> list, double eps) {
        if (list.size() == 1) {
            // eps 是精度， 或者说 tolerance， 当差值小于这么多的时候， 我们认为达到了
            if (Math.abs(list.get(0) - 24.0) < eps) {
                return true;
            }
            return false;
        }

        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < i; j++) {
                // 每次拿两个数去得到新的数
                double num1 = list.get(i);
                double num2 = list.get(j);
                // 计算着两个数可以得到的所有结果
                List<Double> calculation = new ArrayList<>();
                calculation.addAll(Arrays.asList(num1 + num2, num1 - num2, num2 - num1, num1 * num2));
                // 注意除法 check if num1 or num2 is 0
                if (Math.abs(num1) > eps) {
                    calculation.add(num2 / num1);
                }
                if (Math.abs(num2) > eps) {
                    calculation.add(num1 / num2);
                }
                // 对于每一种结果， 都可以和除了得到它的两个数以为的所有原来的数字构成新的下一轮数字
                for (double num : calculation) {
                    List<Double> next = new ArrayList<>();
                    next.add(num);
                    // 把其他的没用过的数放进来
                    for (int k = 0; k < list.size(); k++) {
                        if (k == i || k == j) {
                            continue;
                        }
                        next.add(list.get(k));
                    }
                    // 走下一层
                    if (dfs(next, eps)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}

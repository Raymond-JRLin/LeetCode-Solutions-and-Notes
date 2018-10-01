// 282. Expression Add Operators
// DescriptionHintsSubmissionsDiscussSolution
// Given a string that contains only digits 0-9 and a target value, return all possibilities to add binary operators (not unary) +, -, or * between the digits so they evaluate to the target value.
//
// Example 1:
//
// Input: num = "123", target = 6
// Output: ["1+2+3", "1*2*3"]
// Example 2:
//
// Input: num = "232", target = 8
// Output: ["2*3+2", "2+3*2"]
// Example 3:
//
// Input: num = "105", target = 5
// Output: ["1*0+5","10-5"]
// Example 4:
//
// Input: num = "00", target = 0
// Output: ["0+0", "0-0", "0*0"]
// Example 5:
//
// Input: num = "3456237490", target = 9191
// Output: []


class Solution {
    public List<String> addOperators(String num, int target) {
        if (num == null || num.isEmpty()) {
            return Collections.emptyList();
        }

        return method1(num, target);
    }

    private List<String> method1(String num, int target) {
        // 肯定是 backtracking 了， 注意这里没有除法。 有个关键的点就是， 因为乘法的运算优先级不同， 所以在往下 recursion 的时候， 要记住这一层 （单层） 的运算结果， 为的是下一层如果需要做乘法， 那就要把上一层单层的结果先减回去
        //  O(N ^ 2 + 3 ^ N) time, N ^ 2 是每个 substring， 3 ^ N 是 3 种运算符号每个都 check 了相邻的 digit
        // ref: https://leetcode.com/problems/expression-add-operators/discuss/71895/Java-Standard-Backtrace-AC-Solutoin-short-and-clear
        List<String> result = new ArrayList<>();
        dfs(num, target, result, "", 0, 0, 0);
        return result;
    }
    private void dfs(String num, int target, List<String> result, String s, int index, long sum, long prev) {
        if (index == num.length()) {
            if (sum == target) {
                result.add(s);
            }
            return;
        }
        for (int i = index; i < num.length(); i++) {
            // 如果取多位， 而开头是 0， 那么不能够； 单个 0 则要考虑进去
            if (i != index && num.charAt(index) == '0') {
                break;
            }
            long curr = Long.parseLong(num.substring(index, i + 1)); // record the calculation result of current recursion level
            if (index == 0) {
                // 第一位开头的时候， 前面不能加符号
                dfs(num, target, result, s + curr, i + 1, curr, curr);
            } else {
                dfs(num, target, result, s + "+" + curr, i + 1, sum + curr, curr);
                dfs(num, target, result, s + "-" + curr, i + 1, sum - curr, -curr);
                dfs(num, target, result, s + "*" + curr, i + 1, (sum - prev) + prev * curr, prev * curr); // 要做乘法， 所以先把上一层加上的减去， 乘法的结果也是上一层 （离乘号最近的那一层） 的结果乘以当前的数字
            }
        }
    }
}

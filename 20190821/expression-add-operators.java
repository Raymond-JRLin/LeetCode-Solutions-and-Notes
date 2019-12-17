// 282. Expression Add Operators
// DescriptionHintsSubmissionsDiscussSolution
//
// Given a string that contains only digits 0-9 and a target value, return all possibilities to add binary operators (not unary) +, -, or * between the digits so they evaluate to the target value.
//
// Example 1:
//
// Input: num = "123", target = 6
// Output: ["1+2+3", "1*2*3"]
//
// Example 2:
//
// Input: num = "232", target = 8
// Output: ["2*3+2", "2+3*2"]
//
// Example 3:
//
// Input: num = "105", target = 5
// Output: ["1*0+5","10-5"]
//
// Example 4:
//
// Input: num = "00", target = 0
// Output: ["0+0", "0-0", "0*0"]
//
// Example 5:
//
// Input: num = "3456237490", target = 9191
// Output: []
//
// Seen this question in a real interview before?
//
//     Difficulty:Hard


class Solution {
    public List<String> addOperators(String num, int target) {

        return method1(num, target);
    }

    // 一开始我觉得和 241. Different Ways to Add Parentheses 很像
    // 但其实不太一样， 这里需要所有的 path， 而不是最终的结果
    // 所以如果是按照 241 来 DFS 的话， 并不能很好的拿到 path
    // 所以就是 DFS 就好了， 每个数用 3 中运算符往下走就好了
    // 有个注意点就是 example 3 当中， 105 可以组成 10 - 5， 也就是数字并不是一位
    // 那么 leading 0 就是特殊情况

    private List<String> method1(String num, int target) {
        List<String> result = new ArrayList<>();
        dfs(num, target, 0, 0, 0, result, "");
        return result;
    }
    private void dfs(String num, int target, int index, long sum, long prev, List<String> result, String s) {
        if (index == num.length()) {
            if (sum == target) {
                result.add(s);
            }
            return;
        }
        for (int i = index; i < num.length(); i++) {
            // 0 不能做开头
            if (i > index && num.charAt(index) == '0') {
                break;
            }
            String curr = num.substring(index, i + 1);
            long val = Long.parseLong(curr);
            // 如果是第一位， 就没有 prev 和 符号
            if (index == 0) {
                dfs(num, target, i + 1, sum + val, val, result, curr);
            } else {
                dfs(num, target, i + 1, sum + val, val, result, s + "+" + curr);
                dfs(num, target, i + 1, sum - val, - val, result, s + "-" + curr);
                // 注意乘法， 要把 prev 先减掉
                dfs(num, target, i + 1, sum - prev + prev * val, prev * val, result, s + "*" + curr);
            }
        }
    }
}

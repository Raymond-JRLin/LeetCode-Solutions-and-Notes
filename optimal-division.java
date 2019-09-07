// 553. Optimal Division
// DescriptionHintsSubmissionsDiscussSolution
// Given a list of positive integers, the adjacent integers will perform the float division. For example, [2,3,4] -> 2 / 3 / 4.
//
// However, you can add any number of parenthesis at any position to change the priority of operations. You should find out how to add parenthesis to get the maximum result, and return the corresponding expression in string format. Your expression should NOT contain redundant parenthesis.
//
// Example:
// Input: [1000,100,10,2]
// Output: "1000/(100/10/2)"
// Explanation:
// 1000/(100/10/2) = 1000/((100/10)/2) = 200
// However, the bold parenthesis in "1000/((100/10)/2)" are redundant,
// since they don't influence the operation priority. So you should return "1000/(100/10/2)".
//
// Other cases:
// 1000/(100/10)/2 = 50
// 1000/(100/(10/2)) = 50
// 1000/100/10/2 = 0.5
// 1000/100/(10/2) = 2
// Note:
//
// The length of the input array is [1, 10].
// Elements in the given array will be in range [2, 1000].
// There is only one optimal division for each test case.
// Seen this question in a real interview before?
// Difficulty:Medium


class Solution {
    public String optimalDivision(int[] nums) {
        if (nums == null) {
            return null;
        }
        if (nums.length == 0) {
            return "";
        }
//         对除法来说， x / y, 要使得结果最大， 要么增大 x， 要么减小 y， 而这题是个连除， 括号里只会越多越小， 所以就第一个作被除数， 剩下的作除数就好了， 没什么算法思想， 单纯是个字符串的操作
        int n = nums.length;
        if (n == 1) {
            return String.valueOf(nums[0]);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(nums[0]).append("/");
        if (n == 2) {
            sb.append(nums[1]);
            return sb.toString().trim();
        }
        sb.append("(").append(nums[1]);
        for (int i = 2; i < n; i++) {
            sb.append("/").append(nums[i]);
        }
        return sb.append(")").toString();
    }
}

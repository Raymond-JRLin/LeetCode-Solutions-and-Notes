// 1276. Number of Burgers with No Waste of Ingredients
//
//     User Accepted: 2188
//     User Tried: 2404
//     Total Accepted: 2223
//     Total Submissions: 4597
//     Difficulty: Medium
//
// Given two integers tomatoSlices and cheeseSlices. The ingredients of different burgers are as follows:
//
//     Jumbo Burger: 4 tomato slices and 1 cheese slice.
//     Small Burger: 2 Tomato slices and 1 cheese slice.
//
// Return [total_jumbo, total_small] so that the number of remaining tomatoSlices equal to 0 and the number of remaining cheeseSlices equal to 0. If it is not possible to make the remaining tomatoSlices and cheeseSlices equal to 0 return [].
//
//
//
// Example 1:
//
// Input: tomatoSlices = 16, cheeseSlices = 7
// Output: [1,6]
// Explantion: To make one jumbo burger and 6 small burgers we need 4*1 + 2*6 = 16 tomato and 1 + 6 = 7 cheese. There will be no remaining ingredients.
//
// Example 2:
//
// Input: tomatoSlices = 17, cheeseSlices = 4
// Output: []
// Explantion: There will be no way to use all ingredients to make small and jumbo burgers.
//
// Example 3:
//
// Input: tomatoSlices = 4, cheeseSlices = 17
// Output: []
// Explantion: Making 1 jumbo burger there will be 16 cheese remaining and making 2 small burgers there will be 15 cheese remaining.
//
// Example 4:
//
// Input: tomatoSlices = 0, cheeseSlices = 0
// Output: [0,0]
//
// Example 5:
//
// Input: tomatoSlices = 2, cheeseSlices = 1
// Output: [0,1]
//
//
//
// Constraints:
//
//     0 <= tomatoSlices <= 10^7
//     0 <= cheeseSlices <= 10^7
//


class Solution {
    public List<Integer> numOfBurgers(int tomatoSlices, int cheeseSlices) {

        return mytry(tomatoSlices, cheeseSlices);
    }

    private List<Integer> mytry(int t, int c) {
        // 我是类似于解方程
        // t 我觉得至少是 c 的 2 倍， 然后 t 要能被 2 整除
        if (t < c || t % 2 != 0) {
            return Collections.emptyList();
        }
        // 只用 jumbo burger 即可
        if (t == 4 * c) {
            return Arrays.asList(c, 0);
        }
        // 只用 small burger 即可
        if (t == 2 * c) {
            return Arrays.asList(0, c);
        }
        // 设 jumbo burger 有 x 个， small burger 有 y 个， 则
        // 4x + 2y = t
        // x + y = c
        // => 2x + 2c = t => x = (t - 2c) / 2
        if ((t - 2 * c) % 2 != 0) {
            return Collections.emptyList();
        }
        // t > 2 * c && x = (t - 2c) / 2 <= c
        int jumbo = (t - 2 * c) / 2;
        if (jumbo < 0 || jumbo > c) {
            return Collections.emptyList();
        }
        int small = c - jumbo;
        return Arrays.asList(jumbo, small);

    }
}

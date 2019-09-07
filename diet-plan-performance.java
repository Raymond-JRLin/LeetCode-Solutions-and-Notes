// 1176. Diet Plan Performance
// User Accepted: 2319
// User Tried: 2691
// Total Accepted: 2347
// Total Submissions: 5685
// Difficulty: Easy
// A dieter consumes calories[i] calories on the i-th day.  For every consecutive sequence of k days, they look at T, the total calories consumed during that sequence of k days:
//
// If T < lower, they performed poorly on their diet and lose 1 point;
// If T > upper, they performed well on their diet and gain 1 point;
// Otherwise, they performed normally and there is no change in points.
// Return the total number of points the dieter has after all calories.length days.
//
// Note that: The total points could be negative.
//
//
//
// Example 1:
//
// Input: calories = [1,2,3,4,5], k = 1, lower = 3, upper = 3
// Output: 0
// Explaination: calories[0], calories[1] < lower and calories[3], calories[4] > upper, total points = 0.
// Example 2:
//
// Input: calories = [3,2], k = 2, lower = 0, upper = 1
// Output: 1
// Explaination: calories[0] + calories[1] > upper, total points = 1.
// Example 3:
//
// Input: calories = [6,5,0,0], k = 2, lower = 1, upper = 5
// Output: 0
// Explaination: calories[0] + calories[1] > upper, calories[2] + calories[3] < lower, total points = 0.
//
//
// Constraints:
//
// 1 <= k <= calories.length <= 10^5
// 0 <= calories[i] <= 20000
// 0 <= lower <= upper
//



class Solution {
    public int dietPlanPerformance(int[] calories, int k, int lower, int upper) {

        // return mytry(calories, k, lower, upper);

        return method2(calories, k, lower, upper);
    }

    private int method2(int[] calories, int k, int lower, int upper) {
        // 其实就是 sliding window
        int n = calories.length;
        int result = 0;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += calories[i];
            if (i >= k - 1) {
                if (sum < lower) {
                    result--;
                } else if (sum > upper) {
                    result++;
                }
                sum -= calories[i - k + 1];
            }
        }
        return result;
    }

    private int mytry(int[] calories, int k, int lower, int upper) {
        // 题目中的 "every consecutive sequence of k days" 蛮迷惑人的， 其实是有 overlapping 的连续
        int n = calories.length;
        int result = 0;
        for (int i = 0; i < n; i++) {
            int start = i;
            int end = i + k;
            if (end > n) {
                break;
            }
            int sum = 0;
            while (start < end) {
                sum += calories[start++];
            }
            if (sum < lower) {
                result--;
            } else if (sum > upper) {
                result++;
            }
        }
        return result;
    }
}

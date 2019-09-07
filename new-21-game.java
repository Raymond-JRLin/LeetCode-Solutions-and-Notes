// 837. New 21 Game
// DescriptionHintsSubmissionsDiscussSolution
// Alice plays the following game, loosely based on the card game "21".
//
// Alice starts with 0 points, and draws numbers while she has less than K points.  During each draw, she gains an integer number of points randomly from the range [1, W], where W is an integer.  Each draw is independent and the outcomes have equal probabilities.
//
// Alice stops drawing numbers when she gets K or more points.  What is the probability that she has N or less points?
//
// Example 1:
//
// Input: N = 10, K = 1, W = 10
// Output: 1.00000
// Explanation:  Alice gets a single card, then stops.
// Example 2:
//
// Input: N = 6, K = 1, W = 10
// Output: 0.60000
// Explanation:  Alice gets a single card, then stops.
// In 6 out of W = 10 possibilities, she is at or below N = 6 points.
// Example 3:
//
// Input: N = 21, K = 17, W = 10
// Output: 0.73278
// Note:
//
// 0 <= K <= N <= 10000
// 1 <= W <= 10000
// Answers will be accepted as correct if they are within 10^-5 of the correct answer.
// The judging time limit has been reduced for this question.
// Seen this question in a real interview before?
// Difficulty:Medium


class Solution {
    public double new21Game(int N, int K, int W) {
        if (K == 0 || K - 1 + W <= N) {
            return 1.0;
        }

        return method1(N, K, W);
    }
    private double method1(int N, int K, int W) {
        double prob = 1.0 / W;
        // definition
        double[] f = new double[K + W];
        // initialization
        f[0] = 1;
        double prev = 0.0; // prev is
        // DP
        // 1: 1 -> K
        for (int i = 1; i <= K; i++) {
            prev = prev - (i - 1 - W >= 0 ? f[i - 1 - W] : 0) + f[i - 1];
            f[i] = prev * prob;
        }
        // 2: K + 1 -> K - 1 + W
        double result = f[K];
        for (int i = K + 1; i <= N; i++) {
            prev = prev - (i - 1 - W >= 0 ? f[i - 1 - W] : 0); // no need to add f[i - 1], since we stop draw when score >= K
            f[i] = prev * prob;
            result += f[i];
        }
        // result
        return result;
    }
}

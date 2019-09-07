// 464. Can I Win
// DescriptionHintsSubmissionsDiscussSolution
// In the "100 game," two players take turns adding, to a running total, any integer from 1..10. The player who first causes the running total to reach or exceed 100 wins.
//
// What if we change the game so that players cannot re-use integers?
//
// For example, two players might take turns drawing from a common pool of numbers of 1..15 without replacement until they reach a total >= 100.
//
// Given an integer maxChoosableInteger and another integer desiredTotal, determine if the first player to move can force a win, assuming both players play optimally.
//
// You can always assume that maxChoosableInteger will not be larger than 20 and desiredTotal will not be larger than 300.
//
// Example
//
// Input:
// maxChoosableInteger = 10
// desiredTotal = 11
//
// Output:
// false
//
// Explanation:
// No matter which integer the first player choose, the first player will lose.
// The first player can choose an integer from 1 up to 10.
// If the first player choose 1, the second player can only choose integers from 2 up to 10.
// The second player will win by choosing 10 and get a total = 11, which is >= desiredTotal.
// Same with other integers chosen by the first player, the second player will always win.


class Solution {
    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        // the question is asking aboud "cannot re-use integers"
        if (desiredTotal <= 0) {
            return true;
        }

        // return method1(maxChoosableInteger, desiredTotal);

        return method2(maxChoosableInteger, desiredTotal);
    }

    private boolean method2(int max, int target) {
        int sum = (max + 1) * max / 2; // largest value at most (each number is picked)
        if (sum < target) {
            return false;
        }

        // another way of recursion DP
        int[] result = new int[1 << max]; // result[i] = 0: uncalculated yet; 1: can win; -1: lose
        return recursion(max, target, result, 0);
    }
    private boolean recursion(int max, int target, int[] result, int state) {
        if (target <= 0) {
            // cannot add any value, opponent already got or larget max
            return false;
        }
        if (result[state] != 0) {
            // already checked this stae
            return result[state] == 1;
        }

        for (int i = 0; i < max; i++) {
            int num = 1 << i;
            if ((state & num) != 0) {
                // already checked, the position of i is 1
                continue;
            }

            if (!recursion(max, target - (i + 1), result, state | num)) {
                // i is from [0, max - 1] - the distance to move left, but the value is [1, max], so i should be added 1; state | num: put 1 to the next state position
                // next person - opponent - lost, then I will win
                result[state] = 1;
                return true;
            }
        }

        result[state] = -1;
        return false;
    }

    private boolean method1(int max, int target) {
        // ref: https://leetcode.com/problems/can-i-win/discuss/95277/Java-solution-using-HashMap-with-detailed-explanation
        int sum = (max + 1) * max / 2; // largest value at most (each number is picked)
        if (sum < target) {
            return false;
        }

        // recursion DP, I think it's like 698. Partition to K Equal Sum Subsets method2, use bit space to do DP, but here we're gonna do recursion way, since we should check every different number
        boolean[] visited = new boolean[max + 1]; // visited[i] = if this i already used or not
        Map<Integer, Boolean> map = new HashMap<>(); // result[i] = if this start i can win or not, if i is not in map, then we have not check it yet. If we use a boolean array, then we don't know if we already check it or not
        return dfs(max, target, visited, map);
    }
    private boolean dfs(int max, int target, boolean[] visited, Map<Integer, Boolean> map) {
        if (target <= 0) {
            // cannot add any value, opponent already got or larget max
            return false;
        }
        int state = getState(visited); // get bit state
        if (!map.containsKey(state)) {
            for (int i = 1; i < visited.length; i++) {
                if (!visited[i]) {
                    visited[i] = true;
                    if (!dfs(max, target - i, visited, map)) {
                        // next person - opponent - lost, then I will win
                        map.put(state, true);
                        visited[i] = false;
                        return true;
                    }
                    visited[i] = false;
                }
            }
            map.put(state, false);
        }
        return map.get(state);
    }
    private int getState(boolean[] visited) {
        int state = 0;
        for (int i = 0; i < visited.length; i++) {
            if (visited[i]) {
                state |= (1 << i);
            }
        }
        return state;
    }
}

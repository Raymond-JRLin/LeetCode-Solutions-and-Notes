// 134. Gas Station
// DescriptionHintsSubmissionsDiscussSolution
// There are N gas stations along a circular route, where the amount of gas at station i is gas[i].
//
// You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1). You begin the journey with an empty tank at one of the gas stations.
//
// Return the starting gas station's index if you can travel around the circuit once in the clockwise direction, otherwise return -1.
//
// Note:
//
// If there exists a solution, it is guaranteed to be unique.
// Both input arrays are non-empty and have the same length.
// Each element in the input arrays is a non-negative integer.
// Example 1:
//
// Input:
// gas  = [1,2,3,4,5]
// cost = [3,4,5,1,2]
//
// Output: 3
//
// Explanation:
// Start at station 3 (index 3) and fill up with 4 unit of gas. Your tank = 0 + 4 = 4
// Travel to station 4. Your tank = 4 - 1 + 5 = 8
// Travel to station 0. Your tank = 8 - 2 + 1 = 7
// Travel to station 1. Your tank = 7 - 3 + 2 = 6
// Travel to station 2. Your tank = 6 - 4 + 3 = 5
// Travel to station 3. The cost is 5. Your gas is just enough to travel back to station 3.
// Therefore, return 3 as the starting index.
// Example 2:
//
// Input:
// gas  = [2,3,4]
// cost = [3,4,3]
//
// Output: -1
//
// Explanation:
// You can't start at station 0 or 1, as there is not enough gas to travel to the next station.
// Let's start at station 2 and fill up with 4 unit of gas. Your tank = 0 + 4 = 4
// Travel to station 0. Your tank = 4 - 3 + 2 = 3
// Travel to station 1. Your tank = 3 - 3 + 3 = 3
// You cannot travel back to station 2, as it requires 4 unit of gas but you only have 3.
// Therefore, you can't travel around the circuit once no matter where you start.
// Seen this question in a real interview before?
// Difficulty:Medium


class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        if (gas == null || gas.length == 0) {
            return -1;
        }

        // return method1(gas, cost);

        return method2(gas, cost);
    }

    private int method2(int[] gas, int[] cost) {
        int totalDiff = 0;
        int currDiff = 0;
        int start = 0;
        for (int i = 0; i < gas.length; i++) {
            totalDiff += gas[i] - cost[i];
            currDiff += gas[i] - cost[i];
            if (currDiff < 0) {
                start = i + 1; // 和 method1 中 i = i + j + 1; 是一样的
                currDiff = 0;
            }
        }
        if (totalDiff < 0) {
            return -1;
        } else {
            return start;
        }
    }

    private int method1(int[] gas, int[] cost) {
        int n = gas.length;
        int i = 0; // check each starting position
        while (i < n) {
            int left = 0; // SUM{diff} = SUM{gas[i] - cost[i]}
            int j = 0;
            while (j < n) {
                int index = (i + j) % n; // clockwise
                left += gas[index] - cost[index];
                if (left < 0) {
                    // cannot reach index + 1
                    break;
                }
                j++;
            }
            // go through whole circle
            if (j == n) {
                return i;
            }
            // 没有解
            // [0, i - 1] 已经走过了， 都没有解， 从 i 出发到 n - 1 没有解， 则整体无解
            // e.g. 从 0 出发， 到达 n - 1， 不能走到下一个 n （也就是 0）， 那么已经走完全部
            if (i + j >= n - 1) {
                return -1;
            } else {
                // i 走到 i + j， 无法继续走到 i + j + 1， 那么 i 到 j 之间也无解
                // 可用反证法
                // 对二重循环的优化地方
                i = i + j + 1;
            }
        }
        return -1;
    }
}

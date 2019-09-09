// 1184. Distance Between Bus Stops
// User Accepted: 3258
// User Tried: 3410
// Total Accepted: 3338
// Total Submissions: 5853
// Difficulty: Easy
// A bus has n stops numbered from 0 to n - 1 that form a circle. We know the distance between all pairs of neighboring stops where distance[i] is the distance between the stops number i and (i + 1) % n.
//
// The bus goes along both directions i.e. clockwise and counterclockwise.
//
// Return the shortest distance between the given start and destination stops.
//
//
//
// Example 1:
//
//
//
// Input: distance = [1,2,3,4], start = 0, destination = 1
// Output: 1
// Explanation: Distance between 0 and 1 is 1 or 9, minimum is 1.
//
//
// Example 2:
//
//
//
// Input: distance = [1,2,3,4], start = 0, destination = 2
// Output: 3
// Explanation: Distance between 0 and 2 is 3 or 7, minimum is 3.
//
//
// Example 3:
//
//
//
// Input: distance = [1,2,3,4], start = 0, destination = 3
// Output: 4
// Explanation: Distance between 0 and 3 is 6 or 4, minimum is 4.
//
//
// Constraints:
//
// 1 <= n <= 10^4
// distance.length == n
// 0 <= start, destination < n
// 0 <= distance[i] <= 10^4
//


class Solution {
    public int distanceBetweenBusStops(int[] distance, int start, int destination) {
        if (distance == null || distance.length == 0) {
            return 0;
        }

        // return mytry(distance, start, destination);

        // return method2(distance, start, destination);

        return method3(distance, start, destination);
    }

    public int method3(int[] distance, int start, int destination) {
        // optimization of method2 using 2 variables
        if (start > destination) {
            return mytry(distance, destination, start);
        }
        int n = distance.length;
        int total = 0;
        for (int dist : distance) {
            total += dist;
        }
        int clockwise = 0;
        for (int i = start; i < destination; i++) {
            clockwise += distance[i];
        }
        int counterclockwise = total - clockwise;
        return Math.min(clockwise, counterclockwise);
    }

    public int method2(int[] distance, int start, int destination) {
        // 想法是对的， 简单一点就是它是一个圈儿， 所以两个方向相加就是总的距离， 即 distance 所有的和
        // 可以用 prefix sum 来计算 start 到 destination 的距离， 总和减去它即是反方向的距离
        // ref: https://leetcode.com/problems/distance-between-bus-stops/discuss/377457/Java-Prefix-Sum-w-brief-explanation-and-analysis.
        if (start > destination) {
            return mytry(distance, destination, start);
        }
        int n = distance.length;
        int[] prefix = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            prefix[i] = prefix[i - 1] + distance[i - 1];
        }
        int clockwise = prefix[destination] - prefix[start];
        int counterclockwise = prefix[n] - clockwise;
        return Math.min(clockwise, counterclockwise);
    }

    public int mytry(int[] distance, int start, int destination) {
        // 我觉得就是两个方向算一下
        if (start > destination) {
            return mytry(distance, destination, start);
        }
        int n = distance.length;
        // clockwise
        int clockwise = 0;
        for (int i = 0; i < destination - start; i++) {
            clockwise += distance[(start + i) % n];

        }
        // counterclockwise
        int counterclockwise = 0;
        for (int i = 0; i < n - destination + start; i++) {
            counterclockwise += distance[(destination + i) % n];
        }
        return Math.min(clockwise, counterclockwise);
    }
}

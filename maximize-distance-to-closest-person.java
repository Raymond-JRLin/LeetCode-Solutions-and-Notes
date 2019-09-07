// 849. Maximize Distance to Closest Person
// DescriptionHintsSubmissionsDiscussSolution
// In a row of seats, 1 represents a person sitting in that seat, and 0 represents that the seat is empty.
//
// There is at least one empty seat, and at least one person sitting.
//
// Alex wants to sit in the seat such that the distance between him and the closest person to him is maximized.
//
// Return that maximum distance to closest person.
//
// Example 1:
//
// Input: [1,0,0,0,1,0,1]
// Output: 2
// Explanation:
// If Alex sits in the second open seat (seats[2]), then the closest person has distance 2.
// If Alex sits in any other open seat, the closest person has distance 1.
// Thus, the maximum distance to the closest person is 2.
// Example 2:
//
// Input: [1,0,0,0]
// Output: 3
// Explanation:
// If Alex sits in the last seat, the closest person is 3 seats away.
// This is the maximum distance possible, so the answer is 3.
// Note:
//
// 1 <= seats.length <= 20000
// seats contains only 0s or 1s, at least one 0, and at least one 1.
// Seen this question in a real interview before?
// Difficulty:Easy


class Solution {
    public int maxDistToClosest(int[] seats) {
        if (seats == null || seats.length == 0) {
            return 0;
        }

        // return mytry(seats);

        return method2(seats);
    }

    private int method2(int[] seats) {
        // O(N) time and O(1) space
        // https://leetcode.com/problems/maximize-distance-to-closest-person/discuss/137912/C++Java-1-pass-Easy-Understood
        int n = seats.length;
        int result = 0;
        // 3 scenarios
        // 1: suffix 0
        int zero = 0;
        for (int i = 0; seats[i] == 0; i++) {
            result = Math.max(result, ++zero);
        }
        // 2: middle 0
        zero = 0;
        for (int i = 0; i < n; i++) {
            if (seats[i]== 1) {
                zero = 0;
            } else {
                result = Math.max(result, (++zero + 1) / 2);
            }
        }
        // 3: suffix 0
        zero = 0;
        for (int i = n - 1; seats[i] == 0; i--) {
            result = Math.max(result, ++zero);
        }

        return result;
    }

    private int mytry(int[] seats) {
        // O(N) time and O(N) space
        int n = seats.length;
        // record position took by people already
        List<Integer> ones = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (seats[i] == 1) {
                ones.add(i);
            }
        }

        int result = 0;
        // edge case: head/tail is empty, get the distance between their closest people, no need to / 2
        if (seats[0] == 0) {
            result = Math.max(result, ones.get(0) - 0);
        }
        if (seats[n - 1] == 0) {
            result = Math.max(result, n - 1 - ones.get(ones.size() - 1));
        }
        for (int i = 0; i < ones.size() - 1; i++) {
            int left = ones.get(i);
            int right = ones.get(i + 1);
            result = Math.max(result, (right - left) / 2);
        }
        return result;
    }
}

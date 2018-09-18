// 365. Water and Jug Problem
// DescriptionHintsSubmissionsDiscussSolution
// You are given two jugs with capacities x and y litres. There is an infinite amount of water supply available. You need to determine whether it is possible to measure exactly z litres using these two jugs.
//
// If z liters of water is measurable, you must have z liters of water contained within one or both buckets by the end.
//
// Operations allowed:
//
// Fill any of the jugs completely with water.
// Empty any of the jugs.
// Pour water from one jug into another till the other jug is completely full or the first jug itself is empty.
// Example 1: (From the famous "Die Hard" example)
//
// Input: x = 3, y = 5, z = 4
// Output: True
// Example 2:
//
// Input: x = 2, y = 6, z = 5
// Output: False


class Solution {
    public boolean canMeasureWater(int x, int y, int z) {
        if (x + y < z) {
            return false;
        }
        if (x == z || y == z || x + y == z) {
            return true;
        }

        // if x and y are coprime,  then we can and only can reach every integer z in [0, x + y]
        // otherwise we can only get multiply of GCD(x, y)

        return method1(x, y, z);
    }

    private boolean method1(int x, int y, int z) {
        // ref: https://leetcode.com/problems/water-and-jug-problem/discuss/83715/Math-solution-Java-solution
        return z % gcd(x, y) == 0;
    }
    private int gcd(int x, int y) {
        if (y == 0) {
            return x;
        }
        return gcd(y, x % y);
    }
}

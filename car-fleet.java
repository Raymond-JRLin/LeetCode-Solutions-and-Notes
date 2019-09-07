// 853. Car Fleet
// DescriptionHintsSubmissionsDiscussSolution
// N cars are going to the same destination along a one lane road.  The destination is target miles away.
//
// Each car i has a constant speed speed[i] (in miles per hour), and initial position position[i] miles towards the target along the road.
//
// A car can never pass another car ahead of it, but it can catch up to it, and drive bumper to bumper at the same speed.
//
// The distance between these two cars is ignored - they are assumed to have the same position.
//
// A car fleet is some non-empty set of cars driving at the same position and same speed.  Note that a single car is also a car fleet.
//
// If a car catches up to a car fleet right at the destination point, it will still be considered as one car fleet.
//
//
// How many car fleets will arrive at the destination?
//
//
//
// Example 1:
//
// Input: target = 12, position = [10,8,0,5,3], speed = [2,4,1,1,3]
// Output: 3
// Explanation:
// The cars starting at 10 and 8 become a fleet, meeting each other at 12.
// The car starting at 0 doesn't catch up to any other car, so it is a fleet by itself.
// The cars starting at 5 and 3 become a fleet, meeting each other at 6.
// Note that no other cars meet these fleets before the destination, so the answer is 3.
//
// Note:
//
// 0 <= N <= 10 ^ 4
// 0 < target <= 10 ^ 6
// 0 < speed[i] <= 10 ^ 6
// 0 <= position[i] < target
// All initial positions are different.


class Solution {
    public int carFleet(int target, int[] position, int[] speed) {
        if (position == null || position.length == 0) {
            return 0;
        }

        return method1(target, position, speed);
    }

    private int method1(int target, int[] position, int[] speed) {
        // 考虑到达终点的时间， 从距离终点近的 position 找起， 如果后面一辆车 （距离终点更远 index 更小的） 到达终点的时间更长， 那么它们俩一定不会相遇， 而后面那辆车就会成为它以及它身后那些车的 fleet 的车头
        // ref: https://leetcode.com/problems/car-fleet/discuss/139850/C++JavaPython-Straight-Forward
        int n = position.length;
        double[][] nums = new double[n][2]; // [[position, time to the target]]
        for (int i = 0; i < n; i++) {
            double time = (double) (target - position[i]) / speed[i]; // cannot do (double) (whole equation), otherwise we do int over, than change it to double
            nums[i] = new double[]{(double) position[i], time};
        }

        Arrays.sort(nums, new Comparator<double[]>(){
            @Override
            public int compare(double[] o1, double[] o2) {
                return Double.compare(o1[0], o2[0]);
            }
        });

        int result = 0;
        double curr = 0.0;
        for (int i = n - 1; i >= 0; i--) {
            // System.out.println("new round ");
            if (nums[i][1] > curr) {
                curr = nums[i][1];
                result++;
            }
        }

        return result;
    }
}

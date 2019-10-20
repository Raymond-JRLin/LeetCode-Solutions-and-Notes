// 1235. Maximum Profit in Job Scheduling
//
//     User Accepted: 548
//     User Tried: 934
//     Total Accepted: 586
//     Total Submissions: 1809
//     Difficulty: Hard
//
// We have n jobs, where every job is scheduled to be done from startTime[i] to endTime[i], obtaining a profit of profit[i].
//
// You're given the startTime , endTime and profit arrays, you need to output the maximum profit you can take such that there are no 2 jobs in the subset with overlapping time range.
//
// If you choose a job that ends at time X you will be able to start another job that starts at time X.
//
//
//
// Example 1:
//
// Input: startTime = [1,2,3,3], endTime = [3,4,5,6], profit = [50,10,40,70]
// Output: 120
// Explanation: The subset chosen is the first and fourth job.
// Time range [1-3]+[3-6] , we get profit of 120 = 50 + 70.
//
// Example 2:
//
//
// Input: startTime = [1,2,3,4,6], endTime = [3,5,10,6,9], profit = [20,20,100,70,60]
// Output: 150
// Explanation: The subset chosen is the first, fourth and fifth job.
// Profit obtained 150 = 20 + 70 + 60.
//
// Example 3:
//
// Input: startTime = [1,1,1], endTime = [2,3,4], profit = [5,6,4]
// Output: 6
//
//
//
// Constraints:
//
//     1 <= startTime.length == endTime.length == profit.length <= 5 * 10^4
//     1 <= startTime[i] < endTime[i] <= 10^9
//     1 <= profit[i] <= 10^4
//


class Solution {
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {

        return method1(startTime, endTime, profit);
    }

    private int method1(int[] startTime, int[] endTime, int[] profit) {
        // 每个 job 可做可不做， 也和前面的 jobs 有关系， 可以考虑 DP
        int n = startTime.length;
        Job[] jobs = new Job[n];
        for (int i = 0; i < n; i++) {
            jobs[i] = new Job(startTime[i], endTime[i], profit[i]);
        }
        // 按照 end 排序
        Arrays.sort(jobs, (o1, o2) -> (Integer.compare(o1.end, o2.end)));
        // definition: f[i] := [0, i] job 的最大 profit， 可能取 i 也可能不取 i
        int[] f = new int[n];
        // initialization
        f[0] = jobs[0].profit;
        // DP
        for (int i = 1; i < n; i++) {
            Job curr = jobs[i];
            // 1. 不做当前 job => f[i - 1]
            // 2. 只做当前 job => curr.profit
            // 3. 做当前 job， 也去前面找能够接的上不 overlap 的 job => curr.profit + f[j]
            int max = Math.max(curr.profit, f[i - 1]); // MAX{取 i job， 不取}
            for (int j = i - 1; j >= 0; j--) {
                // non-overlap
                if (jobs[j].end <= curr.start) {
                    if (f[j] + curr.profit > max) {
                        max = f[j] + curr.profit;
                        break; // f[i] 的定义就是 [0, i] 的最大 profit， 因为倒着找， 所以只要找到了就是最大的
                    }
                }
            }
            f[i] = max;
        }
        // result
        return f[n - 1];
    }
    private class Job {
        int start;
        int end;
        int profit;

        Job(int start, int end, int profit) {
            this.start = start;
            this.end = end;
            this.profit = profit;
        }
    }
}

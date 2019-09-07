// 826. Most Profit Assigning Work
// DescriptionHintsSubmissionsDiscussSolution
// We have jobs: difficulty[i] is the difficulty of the ith job, and profit[i] is the profit of the ith job.
//
// Now we have some workers. worker[i] is the ability of the ith worker, which means that this worker can only complete a job with difficulty at most worker[i].
//
// Every worker can be assigned at most one job, but one job can be completed multiple times.
//
// For example, if 3 people attempt the same job that pays $1, then the total profit will be $3.  If a worker cannot complete any job, his profit is $0.
//
// What is the most profit we can make?
//
// Example 1:
//
// Input: difficulty = [2,4,6,8,10], profit = [10,20,30,40,50], worker = [4,5,6,7]
// Output: 100
// Explanation: Workers are assigned jobs of difficulty [4,4,6,6] and they get profit of [20,20,30,30] seperately.
// Notes:
//
// 1 <= difficulty.length = profit.length <= 10000
// 1 <= worker.length <= 10000
// difficulty[i], profit[i], worker[i]  are in range [1, 10^5]
// Seen this question in a real interview before?
// Difficulty:Medium


class Solution {
    public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        if (worker == null || worker.length == 0 || profit == null || profit.length == 0) {
            return 0;
        }

        // return mytry(difficulty, profit, worker);

        return method2(difficulty, profit, worker);
    }

    private int method2(int[] diff, int[] profit, int[] worker) {
        // 看了下 discuss 目前好像都是类似的做法， 和我自己的差不多， 就是实现的方式不同
        int[] best = new int[100001];
        for (int i = 0; i < diff.length; i++) {
            best[diff[i]] = Math.max(best[diff[i]], profit[i]);
        }
        // 相当于 sort， 找到当前 difficulty 最大的 profit
        for (int i = 1; i <= 100000; i++) {
            best[i] = Math.max(best[i], best[i - 1]);
        }

        int result = 0;
        for (int w : worker) {
            result += best[w];
        }
        return result;
    }

    private int mytry(int[] diff, int[] profit, int[] worker) {
        // O(NlogN) time and O(N) space
        // use priority queue to get the max profit for this difficulty， 不一定是 difficulty 越大 profit 越大
        int n = diff.length;
        // 按照 difficulty 从小到大排列
        PriorityQueue<Work> pq = new PriorityQueue<>(new Comparator<Work>() {
            @Override
            public int compare(Work o1, Work o2) {
                return Integer.compare(o1.diff, o2.diff);
            }
        });
        for (int i = 0; i < n; i++) {
            Work work = new Work(diff[i], profit[i]);
            pq.offer(work);
        }

        Arrays.sort(diff); // sort 之后和 pq 的顺序是一样的了
        int[] best = new int[n]; // record 当前 difficulty 下能取到的最大的 profit
        int j = 0;
        int max = 0;
        while (!pq.isEmpty()) {
            Work curr = pq.poll();
            max = Math.max(max, curr.prof); // 不断地取较大值
            best[j++] = max;
        }

        int result = 0;
        for (int w : worker) {
            if (w < diff[0]) {
                continue;
            }
            if (w >= diff[n - 1]) {
                result += max;
                continue;
            }
            // 找到能做的最大的 difficulty 任务
            int index = lessOrEqual(diff, w);
            if (index != -1) {
                result += best[index];
            }
        }
        return result;
    }
    private int lessOrEqual(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] > target) {
                end = mid;
            } else {
                start = mid;
            }
        }
        if (nums[end] <= target) {
            return end;
        } else if (nums[start] <= target) {
            return start;
        } else {
            return -1;
        }
    }
    private class Work {
        private int diff;
        private int prof;
        public Work(int d, int p) {
            diff = d;
            prof = p;
        }
    }
}

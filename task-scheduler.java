// 621. Task Scheduler
// DescriptionHintsSubmissionsDiscussSolution
// Given a char array representing tasks CPU need to do. It contains capital letters A to Z where different letters represent different tasks.Tasks could be done without original order. Each task could be done in one interval. For each interval, CPU could finish one task or just be idle.
//
// However, there is a non-negative cooling interval n that means between two same tasks, there must be at least n intervals that CPU are doing different tasks or just be idle.
//
// You need to return the least number of intervals the CPU will take to finish all the given tasks.
//
// Example 1:
// Input: tasks = ["A","A","A","B","B","B"], n = 2
// Output: 8
// Explanation: A -> B -> idle -> A -> B -> idle -> A -> B.
// Note:
// The number of tasks is in the range [1, 10000].
// The integer n is in the range [0, 100].


class Solution {
    public int leastInterval(char[] tasks, int n) {
        if (tasks == null || tasks.length == 0) {
            return 0;
        }
        if (n == 0) {
            // no need to seperate
            return tasks.length;
        }
        return method1(tasks, n);
    }

    private int method1(char[] tasks, int n) {
        // ref: https://leetcode.com/problems/task-scheduler/discuss/104500/Java-O(n)-time-O(1)-space-1-pass-no-sorting-solution-with-detailed-explanation
        int[] nums = new int[26];
        int maxFreq = 0;
        int maxCount = 0;
        for (char c : tasks) {
            nums[c - 'A']++;
            int freq = nums[c - 'A'];
            if (freq > maxFreq) {
                maxFreq = freq;
                maxCount = 1;
            } else if (freq == maxFreq) {
                maxCount++;
            }
        }

        int len = tasks.length; // total # chars
        int part = maxFreq - 1; // seperated part
        int empty = part * (n - (maxCount - 1)); // char with max frequency may be several
        int available = len - maxFreq * maxCount; // rest chars (frequncy less than mostFreq) to insert within part
        int idles = Math.max(0, empty - available);
        return len + idles;
    }
}

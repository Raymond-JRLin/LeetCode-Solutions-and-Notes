// 539. Minimum Time Difference
// DescriptionHintsSubmissionsDiscussSolution
// Given a list of 24-hour clock time points in "Hour:Minutes" format, find the minimum minutes difference between any two time points in the list.
// Example 1:
// Input: ["23:59","00:00"]
// Output: 1
// Note:
// The number of time points in the given list is at least 2 and won't exceed 20000.
// The input time is legal and ranges from 00:00 to 23:59.
// Seen this question in a real interview before?
// Difficulty:Medium


class Solution {
    public int findMinDifference(List<String> timePoints) {

        // O(nlogn)
        // return my_try(timePoints);

        // 通过上面的计算， 可以发现一天一共 24 * 60 = 1440 minutes， 所以一共也就这么多种可能如果把所有的变为 minutes， 那么可以使用 bucket sort 来达到 O(n)
        return method2_bucket(timePoints);
    }

    private int method2_bucket(List<String> timePoints) {
        int[] minutes = new int[24 * 60];
        // build bucket array to record if a time slot happened already
        for (String string : timePoints) {
            String[] time = string.split(":");
            int hour = Integer.parseInt(time[0]);
            int minute = Integer.parseInt(time[1]);
            int sum = hour * 60 + minute;
            if (minutes[sum] > 0) {
                return 0;
            }
            minutes[sum]++;
        }
        // 依次比较 diff， 同时别忘了比较首尾， 这时候要去记录首尾在哪儿， 是在数组中第一个出现的和最后一个出现的， 即最小值和最大值
        int first = Integer.MAX_VALUE;
        int last = Integer.MIN_VALUE;
        int prev = 0;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < 24 * 60; i++) {
            if (minutes[i] > 0) {
                // this i minute slot appear
                if (first != Integer.MAX_VALUE) {
                    // this i is not the first time slot
                    min = Math.min(min, i - prev);
                }
                first = Math.min(first, i);
                last = Math.max(last, i);
                prev = i;
            }
        }
        return Math.min(min, 24 * 60 - last + first);
    }

    private int my_try(List<String> timePoints) {
        List<Integer> minutes = simplify(timePoints);
        Collections.sort(minutes);
        int min = Integer.MAX_VALUE;
        int n = minutes.size();
        for (int i = 1; i < n; i++) {
            int diff = minutes.get(i) - minutes.get(i - 1);
            min = Math.min(min, diff);
        }
        // 记住比较首尾
        return Math.min(min, 24 * 60 - minutes.get(n - 1) + minutes.get(0));
    }
    private List<Integer> simplify(List<String> timePoints) {
        List<Integer> minutes = new ArrayList<>();
        for (String string : timePoints) {
            String[] time = string.split(":");
            // 不对 12 取余 直接划分 24 份， 然后在 sort 完比较的时候， 多比较一次首尾即可
            int hour = Integer.parseInt(time[0]);
            int minute = Integer.parseInt(time[1]);
            minutes.add(hour * 60 + minute);
        }
        return minutes;
    }
}

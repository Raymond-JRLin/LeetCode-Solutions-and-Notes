// 319. Bulb Switcher
// DescriptionHintsSubmissionsDiscussSolution
// There are n bulbs that are initially off. You first turn on all the bulbs. Then, you turn off every second bulb. On the third round, you toggle every third bulb (turning on if it's off or turning off if it's on). For the i-th round, you toggle every i bulb. For the n-th round, you only toggle the last bulb. Find how many bulbs are on after n rounds.
//
// Example:
//
// Input: 3
// Output: 1
// Explanation:
// At first, the three bulbs are [off, off, off].
// After first round, the three bulbs are [on, on, on].
// After second round, the three bulbs are [on, off, on].
// After third round, the three bulbs are [on, off, off].
//
// So you should return 1, because there is only one bulb is on.


class Solution {
    public int bulbSwitch(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }

//         my try: just do as the process, but TLE expectedlly
        // return my_try(n);

//         因此就肯定是要去找之间的规律
        return method1(n);

//         or we can do directly
        // return (int) Math.sqrt(n);
    }
    private int my_try (int n) {
        boolean[] nums = new boolean[n];
        Arrays.fill(nums, true);
        for (int i = 1; i < n; i++) {
            for (int j = i; j < n; j += i + 1) {
                nums[j] = !nums[j];
            }
        }
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i]) {
                count++;
            }
        }
        return count;
    }

    private int method1(int n) {
        // 只有这一轮 ith 是第几个灯泡的因子时， 这个灯泡才会被操作， 比如说 n = 36， 那么它的因子有 (1, 36), (2, 18), (3, 12), (4, 9), (6, 6), 可以看到它们都是成对出现的， 第一个因子改变了灯泡的状态， 成对的另一个因子又将它变回来了， 只有它的平方数不会这样， 所以问题就变成了找 n 以内的完全平方数
        // int count = 0;
        // for (int i = 1; i * i <= n; i++) {
        //     if (i * i <= n) {
        //         count++;
        //     }
        // }
        // return count;
        // or we can do:
        int count = 1;
        while (count * count <= n) {
            count++;
        }
        return count - 1;
    }
}

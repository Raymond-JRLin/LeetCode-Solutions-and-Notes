// 875. Koko Eating Bananas
// User Accepted: 679
// User Tried: 939
// Total Accepted: 691
// Total Submissions: 1994
// Difficulty: Medium
// Koko loves to eat bananas.  There are N piles of bananas, the i-th pile has piles[i] bananas.  The guards have gone and will come back in H hours.
//
// Koko can decide her bananas-per-hour eating speed of K.  Each hour, she chooses some pile of bananas, and eats K bananas from that pile.  If the pile has less than K bananas, she eats all of them instead, and won't eat any more bananas during this hour.
//
// Koko likes to eat slowly, but still wants to finish eating all the bananas before the guards come back.
//
// Return the minimum integer K such that she can eat all the bananas within H hours.
//
//
//
// Example 1:
//
// Input: piles = [3,6,7,11], H = 8
// Output: 4
// Example 2:
//
// Input: piles = [30,11,23,4,20], H = 5
// Output: 30
// Example 3:
//
// Input: piles = [30,11,23,4,20], H = 6
// Output: 23
//
//
// Note:
//
// 1 <= piles.length <= 10^4
// piles.length <= H <= 10^9
// 1 <= piles[i] <= 10^9


class Solution {
    public int minEatingSpeed(int[] piles, int H) {
        if (piles == null || piles.length == 0) {
            return 0;
        }

        // return method1(piles, H);

        return method2(piles, H);
    }

    private int method2(int[] piles, int H) {
        // 可以考虑使用 BS
        // ref: https://leetcode.com/problems/koko-eating-bananas/discuss/152338/Binary-search-with-optimized-runtime
        Arrays.sort(piles);
        long sum = 0;
        for (int p : piles) {
            sum += p;
        }
        int min = (int) Math.ceil(sum / (double) H);
        int max = piles[piles.length - 1];
        while (min + 1 < max) {
            int mid = min + (max - min) / 2;
            if (canFinish(piles, H, mid)) {
                max = mid;
            } else {
                min = mid;
            }
        }
        if (canFinish(piles, H, min)) {
            return min;
        } else if (canFinish(piles, H, max)) {
            return max;
        } else {
            return -1;
        }
    }

    private int method1(int[] piles, int H) {
        // 其实这就是一个搜索问题， 找一个最慢的速度值能吃完， 那么就会有一个最快速度即 1h 能吃完最多的 pile 的， 其实可以设置最慢速度为 0， 但是没办法完成， 至少要能够吃完所有， 就是一个平均值。 所以就有上限和下限， 在这之间找一个最佳的

        long sum = 0;
        int largest = piles[0];
        for (int p : piles) {
            sum += p;
            largest = Math.max(largest, p);
        }
        int min = (int) Math.ceil(sum / (double) H);
        int max = largest;
        for (int i = min; i <= max; i++) {
            if (canFinish(piles, H, i)) {
                return i;
            }
        }
        return max;
    }
    private boolean canFinish(int[] piles, int H, int speed) {
        int result = 0;
        for (int p : piles) {
            result += (int) Math.ceil(p / (double) speed);
        }
        return result <= H;
    }
}

// 313. Super Ugly Number
// DescriptionHintsSubmissionsDiscussSolution
// Write a program to find the nth super ugly number.
//
// Super ugly numbers are positive numbers whose all prime factors are in the given prime list primes of size k.
//
// Example:
//
// Input: n = 12, primes = [2,7,13,19]
// Output: 32
// Explanation: [1,2,4,7,8,13,14,16,19,26,28,32] is the sequence of the first 12
//              super ugly numbers given primes = [2,7,13,19] of size 4.
// Note:
//
// 1 is a super ugly number for any given primes.
// The given numbers in primes are in ascending order.
// 0 < k ≤ 100, 0 < n ≤ 106, 0 < primes[i] < 1000.
// The nth super ugly number is guaranteed to fit in a 32-bit signed integer.



class Solution {
    public int nthSuperUglyNumber(int n, int[] primes) {
        if (primes == null || primes.length == 0) {
            return 1;
        }

        // return method1(n, primes);

        // return method2(n, primes);

        // return method2_2(n, primes);

        return method3(n, primes);
    }

    private int method3(int n, int[] primes) {
        // 用 PQ 来处理最小值
        int[] result = new int[n];

        PriorityQueue<Num> pq = new PriorityQueue<>();
        for (int i = 0; i < primes.length; i++) {
            pq.offer(new Num(primes[i], 1, primes[i]));
        }

        result[0] = 1;
        for (int i = 1; i < n; i++) {
            result[i] = pq.peek().val;
            while (pq.peek().val == result[i]) {
                Num next = pq.poll();
                pq.add(new Num(next.p * result[next.idx], next.idx + 1, next.p));
            }
        }

        return result[n - 1];
    }

    private class Num implements Comparable<Num> {
        int val; // 这个 prime number 对应的当前的 result
        int idx; // 前一个在答案中的 index
        int p; // prime number

        public Num(int val, int idx, int p) {
            this.val = val;
            this.idx = idx;
            this.p = p;
        }

        @Override
        public int compareTo(Num that) {
            return this.val - that.val;
        }
    }

    private int method2_2(int n, int[] primes) {
        // improved version of method2
        int[] result = new int[n];
        int[] pointers = new int[primes.length]; // 同 method1
        result[0] = 1;
        for (int i = 1; i < n; i++) {
            result[i] = Integer.MAX_VALUE;
            for (int j = 0; j < primes.length; j++) {
                // 同样的 skip duplicate and avoid extra multiplication
                // 不同的是用 result[i - 1] 来直接记录上一个
                if (result[pointers[j]] * primes[j] == result[i - 1]) {
                    pointers[j]++;
                }
                // 记录这一轮的要重新计算过， 因为 pointers[j] 有加 1
                result[i] = Math.min(result[i], result[pointers[j]] * primes[j]);
            }

        }
        return result[n - 1];
    }

    private int method2(int n, int[] primes) {
        // ref: https://leetcode.com/problems/super-ugly-number/discuss/76291/Java-three-methods-23ms-36-ms-58ms(with-heap)-performance-explained
        int[] result = new int[n];
        int[] pointers = new int[primes.length]; // 同 method1
        // 个人的理解是去记录这个 prime 上一个得到的 ugly number 是多少
        int[] prev = new int[primes.length];
        Arrays.fill(prev, 1);
        int next = 1;
        for (int i = 0; i < n; i++) {
            result[i] = next;
            next = Integer.MAX_VALUE;
            for (int j = 0; j < primes.length; j++) {
                // 如果当前 ugly number 是此 prime 上一轮的值， 那么这一轮就可以用它来乘得到下一个 ugly number （在下一个 outer for 中才赋给 result）， 也起到了一个 skip duplicate and avoid extra multiplication 的作用
                if (prev[j] == result[i]) {
                    prev[j] = result[pointers[j]] * primes[j];
                    pointers[j]++;
                }
                next = Math.min(next, prev[j]);
            }

        }
        return result[n - 1];
    }

    private int method1(int n, int[] primes) {
        // ref: https://leetcode.com/problems/super-ugly-number/discuss/76292/Java-solution21ms
        int[] result = new int[n];
        // pointers[i] = 对应的 primes[i] 前一个 ugly number 在 result 中的 index， i.e. result[poiters[i]]
        int[] pointers = new int[primes.length];
        result[0] = 1;
        for (int i = 1; i < n; i++) {
            int min = Integer.MAX_VALUE;
            int index = 0;
            for (int j = 0; j < primes.length; j++) {
                int curr = primes[j] * result[pointers[j]]; // 计算当前 primes[j] 得到的下一个 ugly number
                if (curr < min) {
                    min = curr;
                    index = j;
                } else if (curr == min) {
                    // 如果有一个 primes[j] 也得到这个这个最小的 ugly number, 也要把 pointer 移向下一个， 例如 Ugly Number II 中的 2 和 3
                    pointers[j]++;
                }
            }
            // 更新当前结果
            result[i] = min;
            pointers[index]++; // 别忘了此时选中的 prime number 的 index 也要向后移动一位
        }
        return result[n - 1];
    }
}

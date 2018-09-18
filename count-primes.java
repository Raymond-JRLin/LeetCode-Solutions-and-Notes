// 204. Count Primes
// DescriptionHintsSubmissionsDiscussSolution
// Count the number of prime numbers less than a non-negative number, n.
//
// Example:
//
// Input: 10
// Output: 4
// Explanation: There are 4 prime numbers less than 10, they are 2, 3, 5, 7.


class Solution {
    public int countPrimes(int n) {
        // 计算的是比 n 小的质数
        if (n <= 2) {
            return 0;
        }

        // return method1(n);

        // return method1_2(n);

        return method2(n);
    }

    private int method2(int n) {
        // trick： 偶数都不可能是 prime， 所以 1- 可以 i += 2 来跳过偶数， 2- count 最多有 n / 2 个
        int count = n / 2;
        boolean[] notPrime = new boolean[n];
        for (int i = 3; i <= Math.sqrt(n); i += 2) {
            if (!notPrime[i]) {
                for (int j = i * i; j < n; j += 2 * i) {
                    if (!notPrime[j]) {
                        count--;
                        notPrime[j] = true;
                    }
                }
            }
        }
        return count;
    }

    private int method1_2(int n) {
        // we can count as process
        boolean[] notPrime = new boolean[n];
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (!notPrime[i]) {
                count++;
                for (int j = 2; i * j < n; j++) {
                    notPrime[i * j] = true;
                }
            }
        }
        return count;
    }

    private int method1(int n) {
        boolean[] notPrime = new boolean[n];
        notPrime[0] = true;
        notPrime[1] = true;
        for (int i = 2; i < n; i++) {
            if (!notPrime[i]) {
                for (int j = 2; j * i < n; j++) {
                    // 在这里把 prime 乘上各种系数， 得到就不是 prime 了， 也因此我们要 notPrime 的数组而不是 isPrime， 因为 boolean default 是 false， 如果用 isPrime 找到的就本身就是 false 了
                    notPrime[i * j] = true;
                }
            }
        }
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (!notPrime[i]) {
                count++;
            }
        }
        return count;
    }
}

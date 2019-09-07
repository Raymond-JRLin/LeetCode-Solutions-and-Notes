// 1175. Prime Arrangements
// User Accepted: 1899
// User Tried: 2224
// Total Accepted: 1932
// Total Submissions: 4033
// Difficulty: Easy
// Return the number of permutations of 1 to n so that prime numbers are at prime indices (1-indexed.)
//
// (Recall that an integer is prime if and only if it is greater than 1, and cannot be written as a product of two positive integers both smaller than it.)
//
// Since the answer may be large, return the answer modulo 10^9 + 7.
//
//
//
// Example 1:
//
// Input: n = 5
// Output: 12
// Explanation: For example [1,2,5,4,3] is a valid permutation, but [5,2,3,4,1] is not because the prime number 5 is at index 1.
// Example 2:
//
// Input: n = 100
// Output: 682289015
//
//
// Constraints:
//
// 1 <= n <= 100


class Solution {
    public int numPrimeArrangements(int n) {

        // return mytry(n);

        return method2(n);
    }

    private int method2(int n) {
        // sieve of eratosthenes
        int primeCount = sieveCount(n);
        int notPrimeCount = n - primeCount;
        int MOD = 1000000007;
        long result = 1L;
        for (int i = 1; i <= primeCount; i++) {
            result = (result * i) % MOD;
        }
        for (int i = 1; i <= notPrimeCount; i++) {
            result = (result * i) % MOD;
        }
        return (int) result;
    }
    private int sieveCount(int n) {
        boolean[] isPrime = new boolean[n + 1];
        Arrays.fill(isPrime, true);
        for (int i = 2; i * i <= n; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= n; j += i) {
                    isPrime[j] = false;
                }
            }
        }
        int result = 0;
        for (int i =  2; i <= n; i++) {
            // start from 2, which is the 1st prime number
            if (isPrime[i]) {
                result++;
            }
        }
        return result;
    }

    private int mytry(int n) {
        // 一看到的想法是： count prime 然后做排列组合计算
        int prime = countPrime(n + 1);
        int notPrime = n - prime;
        final int MOD = (int) Math.pow(10, 9) + 7;
        long primePer = factorial(prime, MOD);
        long notPrimePer = factorial(notPrime, MOD);
        return (int) (primePer * notPrimePer % MOD);
    }
    private long factorial(int n, int mod) {
        long result = 1;
        while (n > 1) {
            result = (result * n) % mod;
            n--;
        }
        return result;
    }
    private int countPrime(int n) {
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
}

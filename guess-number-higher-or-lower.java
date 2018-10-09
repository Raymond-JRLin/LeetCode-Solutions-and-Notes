// 374. Guess Number Higher or Lower
// DescriptionHintsSubmissionsDiscussSolution
// We are playing the Guess Game. The game is as follows:
//
// I pick a number from 1 to n. You have to guess which number I picked.
//
// Every time you guess wrong, I'll tell you whether the number is higher or lower.
//
// You call a pre-defined API guess(int num) which returns 3 possible results (-1, 1, or 0):
//
// -1 : My number is lower
//  1 : My number is higher
//  0 : Congrats! You got it!
// Example :
//
// Input: n = 10, pick = 6
// Output: 6


/* The guess API is defined in the parent class GuessGame.
   @param num, your guess
   @return -1 if my number is lower, 1 if my number is higher, otherwise return 0
      int guess(int num); */

public class Solution extends GuessGame {
    public int guessNumber(int n) {

        // return mytry(n);

        // return mytry2(n);

        return method2(n);
    }

    private int method2(int n) {
        // similar to BS, we can do Ternary Search, partition the range to 3 parts
        int start = 1;
        int end = n;
        while (start + 1 < end) {
            int mid1 = start + (end - start) / 3;
            int mid2 = mid1 + (end - mid1) / 2;
            int ans1 = guess(mid1);
            int ans2 = guess(mid2);
            if (ans1 == 0) {
                return mid1;
            } else if (ans2 == 0) {
                return mid2;
            } else if (ans2 == 1) {
                start = mid2;
            } else if (ans1 == -1) {
                end = mid1;
            } else {
                start = mid1;
                end = mid2;
            }
        }
        if (guess(start) == 0) {
            return start;
        } else {
            return end;
        }
    }

    private int mytry2(int n) {
        // since O(N) TLE, then it should be O(logN) with BS
        int start = 1;
        int end = n;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            int ans = guess(mid);
            if (ans == 0) {
                return mid;
            } else if (ans == 1) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (guess(start) == 0) {
            return start;
        } else {
            return end;
        }
    }

    private int mytry(int n) {
        // brute force: O(N) time - TLE
        int result = (n + 1) / 2;
        while (result >= 1 && result <= n) {
            int ans = guess(result);
            if (ans == 0) {
                return result;
            } else if (ans == 1) {
                result++;
            } else {
                result--;
            }
        }
        return result;
    }
}

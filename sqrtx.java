// 69. Sqrt(x)
// DescriptionHintsSubmissionsDiscussSolution
// Implement int sqrt(int x).
//
// Compute and return the square root of x, where x is guaranteed to be a non-negative integer.
//
// Since the return type is an integer, the decimal digits are truncated and only the integer part of the result is returned.
//
// Example 1:
//
// Input: 4
// Output: 2
// Example 2:
//
// Input: 8
// Output: 2
// Explanation: The square root of 8 is 2.82842..., and since
             // the decimal part is truncated, 2 is returned.


class Solution {
   public int mySqrt(int x) {
       if (x == 0) {
           return 0;
       }
       if (x == 1) {
           return 1;
       }

       return method1(x);

       // return method2(x);
   }

   private int method2(int x) {
       // Newton's method: f(x) = x ^ 2 + a, find the root of f(x). f'(x) = 2x,
       long res = (long) x;
       while (res * res > x) {
           res = (res + x / res) / 2;
       }
       return (int) res;
   }

   private int method1(int x) {
       // BS: O(logN) time and O(1) space
       long start = 1;
       long end = (long) x;
       while (start + 1 < end) {
           long mid = start + (end - start) / 2;
           if (mid * mid == x) {
               return (int) mid;
           } else if (mid * mid < x) {
               start = mid;
           } else {
               end = mid;
           }
       }
       if (end * end < x) {
           return (int) end;
       } else {
           return (int) start;
       }
   }
}

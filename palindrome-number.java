// 9. Palindrome Number
// DescriptionHintsSubmissionsDiscussSolution
// Determine whether an integer is a palindrome. An integer is a palindrome when it reads the same backward as forward.
//
// Example 1:
//
// Input: 121
// Output: true
// Example 2:
//
// Input: -121
// Output: false
// Explanation: From left to right, it reads -121. From right to left, it becomes 121-. Therefore it is not a palindrome.
// Example 3:
//
// Input: 10
// Output: false
// Explanation: Reads 01 from right to left. Therefore it is not a palindrome.
// Follow up:
//
// Coud you solve it without converting the integer to a string?
//
// Seen this question in a real interview before?
// Difficulty:Easy


class Solution {
    public boolean isPalindrome(int x) {
        if (x < 0 || (x >= 10 && x % 10 == 0)) {
            // negative number or has trailing 0 (since number cannot have leading 0)
            return false;
        }

        // return mytry(x);

        return method2(x);
    }

    private boolean method2(int x) {
        // get the half of x, and compare them, attention to odd/even number of digits
        int half = 0;
        while (half < x) {
            half = half * 10 + x % 10;
            x /= 10;
        }
        return half == x || half / 10 == x; // even digit || odd digit
    }

    private boolean mytry(int x) {
        // change to string to compare palindrome string
        String s = String.valueOf(x);
        int i = 0;
        int j = s.length() - 1;
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
}

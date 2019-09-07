// 168. Excel Sheet Column Title
// DescriptionHintsSubmissionsDiscussSolution
// Given a positive integer, return its corresponding column title as appear in an Excel sheet.
//
// For example:
//
//     1 -> A
//     2 -> B
//     3 -> C
//     ...
//     26 -> Z
//     27 -> AA
//     28 -> AB
//     ...
// Example 1:
//
// Input: 1
// Output: "A"
// Example 2:
//
// Input: 28
// Output: "AB"
// Example 3:
//
// Input: 701
// Output: "ZY"


class Solution {
    public String convertToTitle(int n) {
        // if (n < 1) {
        //     return null;
        // }

        // it's wrong, it cannot handle 26, moreover, it can only handle 2-digit number, obviously we should do % and / continuously to get digit one by one
        // return my_try(n);

        // return method1(n);

        // or we can do 1-line recursion
        return n == 0 ? "" : convertToTitle((n - 1) / 26) + String.valueOf((char) ('A' + (n - 1) % 26));
        // return n == 0 ? "" : convertToTitle(--n / 26) + (char)('A' + (n % 26));
    }

    private String method1(int n) {
        String result = "";
        while (n != 0) {
            n--;
            char c = (char) (n % 26 + 'A'); // n - 1 to deal with starting 1 as A and 26 as Z, we do n-- first because to handle 26
            result = String.valueOf(c) + result; // attention the sequence, rest value is more significant digit
            n /= 26; // handle next round of rest value,
        }
        return result;
    }

    // private String my_try(int n) {
    //     // it's WRONG
    //     int a = n / 26;
    //     String result = "";
    //     while (a != 0) {
    //         result += "A";
    //         a--;
    //     }
    //     int remainder = n % 26;
    //     if (remainder == 0) {
    //         return result;
    //     }
    //     char c = (char) ('A' + remainder - 1);
    //     result += String.valueOf(c);
    //     return result;
    // }
}

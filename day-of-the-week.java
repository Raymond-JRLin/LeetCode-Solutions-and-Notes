// 1185. Day of the Week
// User Accepted: 2649
// User Tried: 2776
// Total Accepted: 2734
// Total Submissions: 4143
// Difficulty: Easy
// Given a date, return the corresponding day of the week for that date.
//
// The input is given as three integers representing the day, month and year respectively.
//
// Return the answer as one of the following values {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"}.
//
//
//
// Example 1:
//
// Input: day = 31, month = 8, year = 2019
// Output: "Saturday"
// Example 2:
//
// Input: day = 18, month = 7, year = 1999
// Output: "Sunday"
// Example 3:
//
// Input: day = 15, month = 8, year = 1993
// Output: "Sunday"
//
//
// Constraints:
//
// The given dates are valid dates between the years 1971 and 2100.

class Solution {
    public String dayOfTheWeek(int day, int month, int year) {

        return mytry(day, month, year);
    }

    private String mytry(int day, int month, int year) {
        int[] months = {0, 11, 12, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int m = months[month];
        if (month < 3) {
            year--;
        }
        int D = year % 100;
        int C = year / 100;
        // Zeller's Rule
        int num = day + (13 * m - 1) / 5 + D + D / 4 + C / 4 - 2 * C;
        String[] date = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        return date[(num % 7 + 7) % 7];
    }
}

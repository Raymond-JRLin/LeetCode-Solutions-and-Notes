// 825. Friends Of Appropriate Ages
// DescriptionHintsSubmissionsDiscussSolution
// Some people will make friend requests. The list of their ages is given and ages[i] is the age of the ith person.
//
// Person A will NOT friend request person B (B != A) if any of the following conditions are true:
//
// age[B] <= 0.5 * age[A] + 7
// age[B] > age[A]
// age[B] > 100 && age[A] < 100
// Otherwise, A will friend request B.
//
// Note that if A requests B, B does not necessarily request A.  Also, people will not friend request themselves.
//
// How many total friend requests are made?
//
// Example 1:
//
// Input: [16,16]
// Output: 2
// Explanation: 2 people friend request each other.
// Example 2:
//
// Input: [16,17,18]
// Output: 2
// Explanation: Friend requests are made 17 -> 16, 18 -> 17.
// Example 3:
//
// Input: [20,30,100,110,120]
// Output:
// Explanation: Friend requests are made 110 -> 100, 120 -> 110, 120 -> 100.
//
//
// Notes:
//
// 1 <= ages.length <= 20000.
// 1 <= ages[i] <= 120.
// Seen this question in a real interview before?
// Difficulty:Medium


class Solution {
    public int numFriendRequests(int[] ages) {
        if (ages == null || ages.length < 2) {
            return 0;
        }

        // return mytry(ages);

        // return method1(ages);

        return method1_2(ages);
    }

    private int method1_2(int[] ages) {
        // some improvements on same method1
        int[] people = new int[121];
        for (int age : ages) {
            people[age]++;
        }
        int result = 0;
        for (int i = 15; i <= 120; i++) {
            for (int j = (int) i / 2 + 8; j <= i; j++) {
                result += people[i] * (i == j ? people[i] - 1 : people[j]);
            }
        }
        return result;
    }

    private int method1(int[] ages) {
        //
        int[] people = new int[121];
        for (int age : ages) {
            people[age]++;
        }
        int result = 0;
        for (int i = 1; i <= 120; i++) {
            for (int j = 1; j <= 120; j++) {
                if (j <= i * 0.5 + 7 || j > i || j > 100 && i < 100) {
                    continue;
                }
                if (i == j) {
                    result += people[i] * (people[i] - 1);
                } else {
                    result += people[i] * people[j];
                }
            }
        }
        return result;
    }

    private int mytry(int[] ages) {
        // TLE: O(NlogN)
        int n = ages.length;
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j || ages[j] <= 0.5 * ages[i] + 7 || ages[j] > ages[i] || ages[j] > 100 && ages[i] < 100) {
                    continue;
                }
                count++;
            }
        }
        return count;
    }
}

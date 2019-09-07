// 771. Jewels and Stones
// DescriptionHintsSubmissionsDiscussSolution
// You're given strings J representing the types of stones that are jewels, and S representing the stones you have.  Each character in S is a type of stone you have.  You want to know how many of the stones you have are also jewels.
//
// The letters in J are guaranteed distinct, and all characters in J and S are letters. Letters are case sensitive, so "a" is considered a different type of stone from "A".
//
// Example 1:
//
// Input: J = "aA", S = "aAAbbbb"
// Output: 3
// Example 2:
//
// Input: J = "z", S = "ZZ"
// Output: 0
// Note:
//
// S and J will consist of letters and have length at most 50.
// The characters in J are distinct.
// Seen this question in a real interview before?
// Difficulty:Easy


class Solution {
    public int numJewelsInStones(String J, String S) {
        if (J == null || J.isEmpty() || S == null || S.isEmpty()) {
            return 0;
        }

        return mytry(J, S);
    }

    private int mytry(String j, String s) {
        // O(N) time and O(N) space
        Set<Character> set = new HashSet<>();
        for (char c : j.toCharArray()) {
            set.add(c);
        }
        int result = 0;
        for (char c : s.toCharArray()) {
            if (set.contains(c)) {
                result++;
            }
        }
        return result;
    }
}

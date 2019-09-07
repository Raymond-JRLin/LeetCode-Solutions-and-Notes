// 848. Shifting Letters
// DescriptionHintsSubmissionsDiscussSolution
// We have a string S of lowercase letters, and an integer array shifts.
//
// Call the shift of a letter, the next letter in the alphabet, (wrapping around so that 'z' becomes 'a').
//
// For example, shift('a') = 'b', shift('t') = 'u', and shift('z') = 'a'.
//
// Now for each shifts[i] = x, we want to shift the first i+1 letters of S, x times.
//
// Return the final string after all such shifts to S are applied.
//
// Example 1:
//
// Input: S = "abc", shifts = [3,5,9]
// Output: "rpl"
// Explanation:
// We start with "abc".
// After shifting the first 1 letters of S by 3, we have "dbc".
// After shifting the first 2 letters of S by 5, we have "igc".
// After shifting the first 3 letters of S by 9, we have "rpl", the answer.
// Note:
//
// 1 <= S.length = shifts.length <= 20000
// 0 <= shifts[i] <= 10 ^ 9
// Seen this question in a real interview before?
// Difficulty:Medium


class Solution {
    public String shiftingLetters(String S, int[] shifts) {
        if (S == null || S.isEmpty() || shifts == null || shifts.length == 0) {
            return S;
        }

        // return mytry(S, shifts);

        return mytry2(S, shifts);
    }

    private String mytry2(String s, int[] shifts) {
        int n = shifts.length;
        // pre-calculate
        int[] sum = new int[n];
        sum[n - 1] = shifts[n - 1] % 26;
        // from back to front
        for (int i = shifts.length - 2; i >= 0; i--) {
            sum[i] = (sum[i + 1] + shifts[i]) % 26;
        }

        char[] arr = s.toCharArray();
        for (int i = 0; i < shifts.length; i++) {
            int shift = sum[i];
            arr[i] = (char) ((arr[i] + shift - 'a') % 26 + 'a');

        }
        return String.valueOf(arr);
    }

    private String mytry(String s, int[] shifts) {
        // O(N ^ 2): TLE
        char[] arr = s.toCharArray();
        for (int i = 0; i < shifts.length; i++) {
            int shift = shifts[i];
            for (int j = 0; j < i + 1; j++) {
                arr[j] = (char) ((arr[j] + shift - 'a') % 26 + 'a');
            }
        }
        return String.valueOf(arr);
    }
}

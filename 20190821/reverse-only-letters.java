// 917. Reverse Only Letters
// DescriptionHintsSubmissionsDiscussSolution
//
// Given a string S, return the "reversed" string where all characters that are not a letter stay in the same place, and all letters reverse their positions.
//
//
//
// Example 1:
//
// Input: "ab-cd"
// Output: "dc-ba"
//
// Example 2:
//
// Input: "a-bC-dEf-ghIj"
// Output: "j-Ih-gfE-dCba"
//
// Example 3:
//
// Input: "Test1ng-Leet=code-Q!"
// Output: "Qedo1ct-eeLg=ntse-T!"
//
//
//
// Note:
//
//     S.length <= 100
//     33 <= S[i].ASCIIcode <= 122
//     S doesn't contain \ or "
//
// Seen this question in a real interview before?
//
//     Difficulty:Easy


class Solution {
    public String reverseOnlyLetters(String S) {
        if (S == null || S.length() == 0) {
            return S;
        }

        return mytry(S);
    }

    private String mytry(String s) {
        char[] arr = s.toCharArray();
        int i = 0;
        int j = arr.length - 1;
        while (i < j) {
            while (i < j && !Character.isLetter(s.charAt(i))) {
                i++;
            }
            while (i < j && !Character.isLetter(s.charAt(j))) {
                j--;
            }
            char temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++;
            j--;
        }

        return String.valueOf(arr);
    }
}

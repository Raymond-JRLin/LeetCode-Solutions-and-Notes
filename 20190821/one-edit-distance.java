// 161. One Edit Distance
// DescriptionHintsSubmissionsDiscussSolution
// Given two strings s and t, determine if they are both one edit distance apart.
//
// Note:
//
// There are 3 possiblities to satisify one edit distance apart:
//
// Insert a character into s to get t
// Delete a character from s to get t
// Replace a character of s to get t
// Example 1:
//
// Input: s = "ab", t = "acb"
// Output: true
// Explanation: We can insert 'c' into s to get t.
// Example 2:
//
// Input: s = "cab", t = "ad"
// Output: false
// Explanation: We cannot get t from s by only one step.
// Example 3:
//
// Input: s = "1203", t = "1213"
// Output: true
// Explanation: We can replace '0' with '1' to get t.
// Seen this question in a real interview before?
// Difficulty:Medium


class Solution {
    public boolean isOneEditDistance(String s, String t) {
        if (Math.abs(s.length() - t.length()) > 1) {
            return false;
        }
        // only 1 edit, so check 3 different ways
        if (s.length() == t.length()) {
            return canReplaceOne(s, t);
        } else if (s.length() < t.length()) {
            return canDeleteOne(s, t);
        } else {
            // s.length() > t.length()
            return canDeleteOne(t, s);
        }
    }
    private boolean canReplaceOne(String s, String t) {
        int diff = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != t.charAt(i)) {
                diff++;
            }
            if (diff > 1) {
                return false;
            }
        }
        return diff == 1; // 题目的意思是必须要做一个 edit, 如果是 "" & "" => return false
    }
    private boolean canDeleteOne(String shorter, String longer) {
        int i = 0, j = 0;
        while (i < shorter.length() && j < longer.length()) {
            if (shorter.charAt(i) != longer.charAt(j)) {
                break;
            }
            i++;
            j++;
        }
        return shorter.substring(i).equals(longer.substring(j + 1));
    }
}

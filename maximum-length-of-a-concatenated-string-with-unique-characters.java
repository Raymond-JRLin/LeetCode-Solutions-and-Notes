// 1239. Maximum Length of a Concatenated String with Unique Characters
//
//     User Accepted: 1304
//     User Tried: 1985
//     Total Accepted: 1336
//     Total Submissions: 4841
//     Difficulty: Medium
//
// Given an array of strings arr. String s is a concatenation of a sub-sequence of arr which have unique characters.
//
// Return the maximum possible length of s.
//
//
//
// Example 1:
//
// Input: arr = ["un","iq","ue"]
// Output: 4
// Explanation: All possible concatenations are "","un","iq","ue","uniq" and "ique".
// Maximum length is 4.
//
// Example 2:
//
// Input: arr = ["cha","r","act","ers"]
// Output: 6
// Explanation: Possible solutions are "chaers" and "acters".
//
// Example 3:
//
// Input: arr = ["abcdefghijklmnopqrstuvwxyz"]
// Output: 26
//
//
//
// Constraints:
//
//     1 <= arr.length <= 16
//     1 <= arr[i].length <= 26
//     arr[i] contains only lower case English letters.
//


class Solution {
    public int maxLength(List<String> arr) {
        if (arr == null || arr.size() == 0) {
            return 0;
        }

        return mytry(arr);
    }

    private int mytry(List<String> arr) {
        int n = arr.size();
        result = 0;
        for (int i = 0; i < n; i++) {
            dfs(arr, n, i, new HashSet<Character>(), 0);
        }

        return result;
    }
    int result;
    private void dfs(List<String> arr, int n, int index, Set<Character> visited, int len) {
        // System.out.println("check " + index + "th string: " + (index == n ? "" : arr.get(index)) + " with length of " + len);
        if (index == n) {
            result = Math.max(result, len);
            return;
        }
        result = Math.max(result, len);
        String curr = arr.get(index);
        Set<Character> set = new HashSet<>();
        for (char c : curr.toCharArray()) {
            if (visited.contains(c) || set.contains(c)) {
                // System.out.println("duplicate");
                return;
            }
            set.add(c);
        }
        visited.addAll(set);
        for (int i = index; i < n; i++) {
            // System.out.println("go " + (i + 1));
            dfs(arr, n, i + 1, visited, len + curr.length());
        }
        visited.removeAll(set);
    }
}

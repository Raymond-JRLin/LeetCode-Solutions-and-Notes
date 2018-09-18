// 893. Groups of Special-Equivalent Strings
// User Accepted: 1086
// User Tried: 1223
// Total Accepted: 1093
// Total Submissions: 1775
// Difficulty: Easy
// You are given an array A of strings.
//
// Two strings S and T are special-equivalent if after any number of moves, S == T.
//
// A move consists of choosing two indices i and j with i % 2 == j % 2, and swapping S[i] with S[j].
//
// Now, a group of special-equivalent strings from A is a non-empty subset S of A such that any string not in S is not special-equivalent with any string in S.
//
// Return the number of groups of special-equivalent strings from A.
//
//
//
// Example 1:
//
// Input: ["a","b","c","a","c","c"]
// Output: 3
// Explanation: 3 groups ["a","a"], ["b"], ["c","c","c"]
// Example 2:
//
// Input: ["aa","bb","ab","ba"]
// Output: 4
// Explanation: 4 groups ["aa"], ["bb"], ["ab"], ["ba"]
// Example 3:
//
// Input: ["abc","acb","bac","bca","cab","cba"]
// Output: 3
// Explanation: 3 groups ["abc","cba"], ["acb","bca"], ["bac","cab"]
// Example 4:
//
// Input: ["abcd","cdab","adcb","cbad"]
// Output: 1
// Explanation: 1 group ["abcd","cdab","adcb","cbad"]
//
//
// Note:
//
// 1 <= A.length <= 1000
// 1 <= A[i].length <= 20
// All A[i] have the same length.
// All A[i] consist of only lowercase letters.


class Solution {
    public int numSpecialEquivGroups(String[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }

        // return mytry(A);

        return method2(A);
    }

    private int method2(String[] strs) {
        // ref: https://leetcode.com/problems/groups-of-special-equivalent-strings/discuss/163413/Java-Concise-Set-Solution
        int n = strs.length;
        Set<String> set = new HashSet<>();
        for (String s : strs) {
            int[] odd = new int[26];
            int[] even = new int[26];
            for (int i = 0; i < s.length(); i++) {
                if (i % 2 == 0) {
                    even[s.charAt(i) - 'a']++;
                } else {
                    odd[s.charAt(i) - 'a']++;
                }

            }
            set.add(Arrays.toString(odd) + Arrays.toString(even));
        }
        return set.size();
    }

    private int mytry(String[] strs) {
        // 看清楚题目， 条件说的是： i % 2 == j % 2， 也就是奇数位互相调换， 偶数位可以互相调换， 然后看是否相等。 那就直接去偶数位和奇数位 sort 之后放在一起看是否相等　
        int n = strs.length;
        Set<String> set = new HashSet<>();
        for (String s : strs) {
            List<Character> odd = new ArrayList<>();
            List<Character> even = new ArrayList<>();
            for (int i = 0; i < s.length(); i++) {
                if (i % 2 == 0) {
                    even.add(s.charAt(i));
                } else {
                    odd.add(s.charAt(i));
                }

            }
            Collections.sort(odd);
            Collections.sort(even);
            StringBuilder sb = new StringBuilder();
            for (char c : odd) {
                sb.append(c);
            }
            for (char c : even) {
                sb.append(c);
            }
            set.add(sb.toString());
        }
        return set.size();
    }
}

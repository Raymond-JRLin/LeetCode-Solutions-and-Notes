// 14. Longest Common Prefix
// DescriptionHintsSubmissionsDiscussSolution
// Write a function to find the longest common prefix string amongst an array of strings.
//
// If there is no common prefix, return an empty string "".
//
// Example 1:
//
// Input: ["flower","flow","flight"]
// Output: "fl"
// Example 2:
//
// Input: ["dog","racecar","car"]
// Output: ""
// Explanation: There is no common prefix among the input strings.
// Note:
//
// All given inputs are in lowercase letters a-z.
//
// Seen this question in a real interview before?
// Difficulty:Easy


class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }

        // return my_try(strs);

        return method2(strs);

        // return method3(strs);
    }

    private String method3(String[] strs) {
        // O(max{nlogn, minLen})
        Arrays.sort(strs);
        int n = strs.length;
        int len = strs[0].length();
        int i = 0;
        String result = "";
        // after sorting, we can just compare the 1st and last string
        while (i < len) {
            if (strs[0].charAt(i) != strs[n - 1].charAt(i)) {
                return result;
            }
            result += String.valueOf(strs[0].charAt(i));
            i++;
        }
        return result;
    }

    private String method2(String[] strs) {
        int n = strs.length;
        String prev = strs[0];
        for (int i = 1; i < n; i++) {
            // while (strs[i].indexOf(prev) != 0) {
            //     prev = prev.substring(0, prev.length() - 1);
            // }
            // or we can do munually and better:
            if (prev == null || prev.length() == 0 || strs[i] == null || strs[i].length() == 0) {
                return "";
            }
            int j;
            for (j = 0; j < Math.min(prev.length(), strs[i].length()); j++) {
                if (prev.charAt(j) != strs[i].charAt(j)) {
                    break;
                }
            }
            prev = prev.substring(0, j);
        }
        return prev;
    }

    private String my_try(String[] strs) {
        // O(n * minLen) where minLen is the minimum length of string in array, n is # strings in array
        int n = strs.length;
        int minLen = Integer.MAX_VALUE;
        for (String str : strs) {
            minLen = Math.min(minLen, str.length());
        }
        String result = "";
        int index = 0;
        while (index < minLen) {
            char c = strs[0].charAt(index);
            for (int i = 1; i < n; i++) {
                if (strs[i].charAt(index) != c) {
                    return result;
                }
            }
            result += String.valueOf(c);
            index++;
        }
        return result;
    }
}

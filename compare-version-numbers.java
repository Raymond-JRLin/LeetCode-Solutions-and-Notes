// 165. Compare Version Numbers
// DescriptionHintsSubmissionsDiscussSolution
// Compare two version numbers version1 and version2.
// If version1 > version2 return 1; if version1 < version2 return -1;otherwise return 0.
//
// You may assume that the version strings are non-empty and contain only digits and the . character.
// The . character does not represent a decimal point and is used to separate number sequences.
// For instance, 2.5 is not "two and a half" or "half way to version three", it is the fifth second-level revision of the second first-level revision.
//
// Example 1:
//
// Input: version1 = "0.1", version2 = "1.1"
// Output: -1
// Example 2:
//
// Input: version1 = "1.0.1", version2 = "1"
// Output: 1
// Example 3:
//
// Input: version1 = "7.5.2.4", version2 = "7.5.3"
// Output: -1



class Solution {
    public int compareVersion(String version1, String version2) {
        if ((version1 == null || version1.isEmpty()) && (version1 == null || version1.isEmpty())) {
            return 0;
        }
        if (version1 == null || version1.isEmpty()) {
            return -1;
        }
        if (version1 == null || version1.isEmpty()) {
            return 1;
        }
        if (version1.equals(version2)) {
            return 0;
        }


        // return mytry(version1, version2);

        // return method2(version1, version2);

        return method3(version1, version2);
    }

    private int method3(String v1, String v2) {
        // another concise version
        // ref: https://leetcode.com/problems/compare-version-numbers/discuss/50774/Accepted-small-Java-solution.
        String[] arr1 = v1.split("\\.");
        String[] arr2 = v2.split("\\.");
        int len = Math.max(arr1.length, arr2.length);
        for (int i = 0; i < len; i++) {
            int num1 = i < arr1.length ? Integer.parseInt(arr1[i]) : 0;
            int num2 = i < arr2.length ? Integer.parseInt(arr2[i]) : 0;
            int result = Integer.compare(num1, num2);
            if (result != 0) {
                return result;
            }
        }
        return 0;
    }

    private int method2(String v1, String v2) {
        // more concise version with same idea: compare 2 arrays
        String[] arr1 = v1.split("\\.");
        String[] arr2 = v2.split("\\.");
        int i = 0;
        int j = 0;
        while (i < arr1.length || j < arr2.length) {
            // 2 tricks: 1- Integer.parseInt() will remove leading zeros automatically; 2- if it achieve the end, assign it as 0
            int num1 = i < arr1.length ? Integer.parseInt(arr1[i]) : 0;
            int num2 = j < arr2.length ? Integer.parseInt(arr2[j]) : 0;
            if (num1 > num2) {
                return 1;
            } else if (num1 < num2) {
                return -1;
            } else {
                i++;
                j++;
            }
        }
        return 0;
    }

    private int mytry(String v1, String v2) {
        // attention "." is a special regex
        String[] arr1 = v1.split("\\.");
        String[] arr2 = v2.split("\\.");

        int n = arr1.length;
        int m = arr2.length;
        if (n < m) {
            return -helper(arr2, arr1); // changed the order, so get make it negative
        } else {
            return helper(arr1, arr2);
        }
    }
    private int helper(String[] arr1, String[] arr2) {
        int n = arr1.length; // longer
        int m = arr2.length; // shorter or equal
        for (int i = 0; i < m; i++) {
            int num1 = Integer.parseInt(getString(arr1[i]));
            int num2 = Integer.parseInt(getString(arr2[i]));
            if (num1 > num2) {
                return 1;
            } else if (num2 > num1) {
                return -1;
            }
        }
        if (n > m) {
            for (int i = m; i < n; i++) {
                // now arr1 is longer, if there's a non-zero digit, then it will larger than arr2
                int num1 = Integer.parseInt(getString(arr1[i]));
                if (num1 != 0) {
                    return 1;
                }
            }
        }
        return 0;
    }
    private String getString(String s) {
        int i = 0;
        // remove all leading zeros
        while (i < s.length()) {
            if (s.charAt(i) == '0') {
                i++;
            } else {
                break;
            }
        }
        return i == s.length() ? "0" : s.substring(i, s.length());
    }
}

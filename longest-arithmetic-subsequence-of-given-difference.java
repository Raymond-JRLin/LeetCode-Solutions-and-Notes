// 1218. Longest Arithmetic Subsequence of Given Difference
// User Accepted:1930
// User Tried:3144
// Total Accepted:1984
// Total Submissions:7762
// Difficulty:Medium
// Given an integer array arr and an integer difference, return the length of the longest subsequence in arr which is an arithmetic sequence such that the difference between adjacent elements in the subsequence equals difference.
//
//
//
// Example 1:
//
// Input: arr = [1,2,3,4], difference = 1
// Output: 4
// Explanation: The longest arithmetic subsequence is [1,2,3,4].
// Example 2:
//
// Input: arr = [1,3,5,7], difference = 1
// Output: 1
// Explanation: The longest arithmetic subsequence is any single element.
// Example 3:
//
// Input: arr = [1,5,7,8,5,3,4,2,1], difference = -2
// Output: 4
// Explanation: The longest arithmetic subsequence is [7,5,3,1].
//
//
// Constraints:
//
// 1 <= arr.length <= 10^5
// -10^4 <= arr[i], difference <= 10^4


class Solution {
    public int longestSubsequence(int[] arr, int difference) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        return mytry(arr, difference);
    }

    private int mytry(int[] arr, int diff) {
        int n = arr.length;
        Map<Integer, Integer> map = new HashMap<>(); // <num, longest length>
        int result = 1;
        for (int num : arr) {
            int prev = num - diff;
            if (map.containsKey(prev)) {
                map.put(num, map.get(prev) + 1);
            } else {
                map.put(num, 1);
            }
            result = Math.max(result, map.get(num));
        }
        return result;
    }
}

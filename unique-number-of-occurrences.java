// 1207. Unique Number of Occurrences
// User Accepted: 3828
// User Tried: 3899
// Total Accepted: 3925
// Total Submissions: 5005
// Difficulty: Easy
// Given an array of integers arr, write a function that returns true if and only if the number of occurrences of each value in the array is unique.
//
//
//
// Example 1:
//
// Input: arr = [1,2,2,1,1,3]
// Output: true
// Explanation: The value 1 has 3 occurrences, 2 has 2 and 3 has 1. No two values have the same number of occurrences.
// Example 2:
//
// Input: arr = [1,2]
// Output: false
// Example 3:
//
// Input: arr = [-3,0,1,-3,1,1,1,-3,10,0]
// Output: true
//
//
// Constraints:
//
// 1 <= arr.length <= 1000
// -1000 <= arr[i] <= 1000
//


class Solution {
    public boolean uniqueOccurrences(int[] arr) {
        if (arr == null || arr.length == 0) {
            return true;
        }

        return mytry(arr);
    }

    private boolean mytry(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>(); // <num, freq>
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        Set<Integer> set = new HashSet<>(); // <freq>
        for (int key : map.keySet()) {
            if (!set.add(map.get(key))) {
                return false;
            }
        }
        return true;
    }
}

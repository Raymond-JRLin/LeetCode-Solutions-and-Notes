// 179. Largest Number
// DescriptionHintsSubmissionsDiscussSolution
//
// Given a list of non negative integers, arrange them such that they form the largest number.
//
// Example 1:
//
// Input: [10,2]
// Output: "210"
//
// Example 2:
//
// Input: [3,30,34,5,9]
// Output: "9534330"
//
// Note: The result may be very large, so you need to return a string instead of an integer.
// Seen this question in a real interview before?
//
//     Difficulty:Medium


class Solution {
    public String largestNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return "";
        }

        return method1(nums);
    }

    private String method1(int[] nums) {
        // turn int -> string to compare
        // sort 是 O(NlogN), where N is the number of values in nums[]
        // string.compareTo() is O(k), where k is the average length of string in arr[]
        // total time complexity: O(NklogN), space complexity: O(N)
        int n = nums.length;
        String[] arr = new String[n];
        for (int i = 0; i < n; i++) {
            arr[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(arr, (o1, o2) -> ((o2 + o1).compareTo(o1 + o2)));
        StringBuilder sb = new StringBuilder();
        for (String s : arr) {
            sb.append(s);
        }
        if (sb.charAt(0) == '0') {
            return "0";
        }
        return sb.toString();
    }
}

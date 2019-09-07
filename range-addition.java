// 370. Range Addition
// DescriptionHintsSubmissionsDiscussSolution
// Assume you have an array of length n initialized with all 0's and are given k update operations.
//
// Each operation is represented as a triplet: [startIndex, endIndex, inc] which increments each element of subarray A[startIndex ... endIndex] (startIndex and endIndex inclusive) with inc.
//
// Return the modified array after all k operations were executed.
//
// Example:
//
// Input: length = 5, updates = [[1,3,2],[2,4,3],[0,2,-2]]
// Output: [-2,0,3,5,3]
// Explanation:
//
// Initial state:
// [0,0,0,0,0]
//
// After applying operation [1,3,2]:
// [0,2,2,2,0]
//
// After applying operation [2,4,3]:
// [0,2,5,5,3]
//
// After applying operation [0,2,-2]:
// [-2,0,3,5,3]
// Seen this question in a real interview before?
// Difficulty:Medium


class Solution {
    public int[] getModifiedArray(int length, int[][] updates) {
        if (updates == null || updates.length == 0 || updates[0].length == 0) {
            return new int[length];
        }

        // return mytry(length, updates);

        return mytry2(length, updates);
    }

    private int[] mytry2(int len, int[][] updates) {
        // brute force: O(len + N), where N is length of updates
        // pre-calculate the value we should count at start and end position
        int[] sum = new int[len];
        for (int[] update : updates) {
            int start = update[0];
            int end = update[1];
            int val = update[2];
            sum[start] += val; // enter: add
            if (end + 1 < len) {
                // exit: decrease, but attention the end position still needs to add the value, and decrease from next index
                sum[end + 1] -= val;
            }
        }

        int[] result = new int[len];
        int count = 0;
        for (int i = 0; i < len; i++) {
            // keep tracking the total value
            count += sum[i];
            result[i] = count;
        }
        return result;
    }

    private int[] mytry(int len, int[][] updates) {
        // brute force: O(len * N), where N is length of updates
        int[] result = new int[len];
        for (int[] update : updates) {
            int start = update[0];
            int end = update[1];
            int val = update[2];
            for (int i = start; i <= end; i++) {
                result[i] += val;
            }
        }
        return result;
    }
}

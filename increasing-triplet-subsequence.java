// 334. Increasing Triplet Subsequence
// DescriptionHintsSubmissionsDiscussSolution
// Given an unsorted array return whether an increasing subsequence of length 3 exists or not in the array.
//
// Formally the function should:
//
// Return true if there exists i, j, k
// such that arr[i] < arr[j] < arr[k] given 0 ≤ i < j < k ≤ n-1 else return false.
// Note: Your algorithm should run in O(n) time complexity and O(1) space complexity.
//
// Example 1:
//
// Input: [1,2,3,4,5]
// Output: true
// Example 2:
//
// Input: [5,4,3,2,1]
// Output: false
// Seen this question in a real interview before?
// Difficulty:Medium


class Solution {
    public boolean increasingTriplet(int[] nums) {
        if (nums == null || nums.length < 3) {
            return false;
        }

        // return mytry(nums);

        return method2(nums);
    }

    private boolean method2(int[] nums) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MAX_VALUE;
        for (int num : nums) {
            if (num <= min) {
                // 等号都要包括进去， 不然就会出现相等然后返回 true 的
                min = num;
            } else if (num <= max) {
                max = num;
            } else {
                return true;
            }
        }
        return false;
    }

    /*private boolean method2(int[] nums) {
        // it's wrong, cuz it can ensure that relative positions that 1 < 2 < 3, e.g. [2,1,0,2,0,2,0,2,0,2,0,1]
        int n = nums.length;
        Map<Integer, List<Integer>> map = new HashMap<>(); // <nun, list of values larger than num and behind num>
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (nums[j] > nums[i]) {
                    List<Integer> list = map.getOrDefault(nums[i], new ArrayList<>());
                    list.add(nums[j]);
                    map.put(nums[i], list);
                }
            }
        }
        for (int i = 0; i < n - 2; i++) {
            if (map.containsKey(nums[i])) {
                for (int j : map.get(nums[i])) {
                    if (map.containsKey(j)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }*/

    private boolean mytry(int[] nums) {
        // brute: O(N ^ 3)
        int n = nums.length;
        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                if (nums[j] > nums[i]) {
                    for (int k = j + 1; k < n; k++) {
                        if (nums[k] > nums[j]) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}

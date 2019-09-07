// 448. Find All Numbers Disappeared in an Array
// DescriptionHintsSubmissionsDiscussSolution
// Given an array of integers where 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.
//
// Find all the elements of [1, n] inclusive that do not appear in this array.
//
// Could you do it without extra space and in O(n) runtime? You may assume the returned list does not count as extra space.
//
// Example:
//
// Input:
// [4,3,2,7,8,2,3,1]
//
// Output:
// [5,6]


class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        if (nums == null || nums.length == 0) {
            return Collections.emptyList();
        }

        // return mytry(nums);

        return method1(nums);
    }

    private List<Integer> method1(int[] nums) {
        // 如果要 O(N) time and O(1) space， 那么要想比较巧的方法。 找一找题目中特殊的条件： 1 ≤ a[i] ≤ n， 要找的 disappear 的数也在 [1, n] 范围内， 所以可以看到这里 index 和 value 有某种联系， 他们有着同样的大小范围， 所以我们可以考虑利用这两者之间的联系来处理。 其实我认为我们找的最终对象可以认为是在 index 中的， 因为 value 可以有重复， 我们没有办法去重或者分辨， 所以我们要想如何把 iterate 到的 value 转换成 index， 标记下来， 然后再找
        // ref: https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/discuss/92956/Java-accepted-simple-solution
        // O(N) time and O(1) space
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            // iterate values, attention to use absolute value, since it may already turned to be negative
            int val = Math.abs(nums[i]) - 1; // value -> index, 实际上就是出现过的数字在它作为 index 时上做标记
            if (nums[val] > 0) {
                // mark as appeared
                nums[val] = 0 - nums[val];
            }
        }
        List<Integer> result = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (nums[i - 1] > 0) {
                // not be touched, i.e. disappeared
                result.add(i);
            }
        }
        return result;
    }

    private List<Integer> mytry(int[] nums) {
        // use HashSet: O(N) time and O(N) space
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        List<Integer> result = new ArrayList<>();
        for (int i = 1; i <= nums.length; i++) {
            if (!set.contains(i)) {
                result.add(i);
            }
        }
        return result;
    }
}

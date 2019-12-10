// 448. Find All Numbers Disappeared in an Array
// DescriptionHintsSubmissionsDiscussSolution
//
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
//
// Seen this question in a real interview before?
//
//     Difficulty:Easy


class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {

        return method1(nums);
    }

    private List<Integer> method1(int[] nums) {
        // 因为其中可能会有多对重复， 所以没办法利用 missing number 里面交换的方式来把数放到对应的位置上
        // 应该考虑其他 index - value 的关系
        // 比如直接 mark 一下, 标记为负， 或者 % n 再 + n， 但要注意 overflow
        // 总之就是让其表现出不一样的特性
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int val = Math.abs(nums[i]);
            if (nums[val - 1] > 0) {
                nums[val - 1] = - nums[val - 1];
            }
        }

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (nums[i] > 0) {
                result.add(i + 1);
            }
        }
        return result;
    }
}

// 90. Subsets II
// DescriptionHintsSubmissionsDiscussSolution
// Given a collection of integers that might contain duplicates, nums, return all possible subsets (the power set).
//
// Note: The solution set must not contain duplicate subsets.
//
// Example:
//
// Input: [1,2,2]
// Output:
// [
//   [2],
//   [1],
//   [1,2,2],
//   [2,2],
//   [1,2],
//   []
// ]


class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        if (nums == null || nums.length == 0) {
            return Collections.emptyList();
        }

        return mytry(nums);
    }

    private List<List<Integer>> mytry(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        dfs(nums, result, new ArrayList<Integer>(), 0);
        return result;
    }
    private void dfs(int[] nums, List<List<Integer>> result, List<Integer> list, int start) {
        result.add(new ArrayList<Integer>(list));
        if (start >= nums.length) {
            return;
        }
        for (int i = start; i < nums.length; i++) {
            if (i != start && nums[i] == nums[i - 1]) {
                continue;
            }
            list.add(nums[i]);
            dfs(nums, result, list, i + 1);
            list.remove(list.size() - 1);
        }
    }
}

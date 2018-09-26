// 39. Combination Sum
// DescriptionHintsSubmissionsDiscussSolution
// Given a set of candidate numbers (candidates) (without duplicates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.
//
// The same repeated number may be chosen from candidates unlimited number of times.
//
// Note:
//
// All numbers (including target) will be positive integers.
// The solution set must not contain duplicate combinations.
// Example 1:
//
// Input: candidates = [2,3,6,7], target = 7,
// A solution set is:
// [
//   [7],
//   [2,2,3]
// ]
// Example 2:
//
// Input: candidates = [2,3,5], target = 8,
// A solution set is:
// [
//   [2,2,2,2],
//   [2,3,3],
//   [3,5]
// ]
//


class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        if (candidates == null || candidates.length == 0) {
            return new ArrayList<List<Integer>>();
        }

        return mytry(candidates, target);
    }

    private List<List<Integer>> mytry(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        dfs(nums, target, result, new ArrayList<Integer>(), 0, 0);
        return result;
    }
    private void dfs(int[] nums, int target, List<List<Integer>> result, List<Integer> list, int index, int sum) {
        if (sum == target) {
            result.add(new ArrayList<Integer>(list));
            return;
        }
        if (sum > target) {
            return;
        }
        for (int i = index; i < nums.length; i++) {
            list.add(nums[i]);
            // 下一轮至少要从当前 i 开始， 避免重复 - we can choose the same repeated number unlimitedly
            dfs(nums, target, result, list, i, sum + nums[i]);
            list.remove(list.size() - 1);
        }
    }
}

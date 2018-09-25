// 78. Subsets
// DescriptionHintsSubmissionsDiscussSolution
// Given a set of distinct integers, nums, return all possible subsets (the power set).
//
// Note: The solution set must not contain duplicate subsets.
//
// Example:
//
// Input: nums = [1,2,3]
// Output:
// [
//   [3],
//   [1],
//   [2],
//   [1,2,3],
//   [1,3],
//   [2,3],
//   [1,2],
//   []
// ]

class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new ArrayList<List<Integer>>();
        }

        // return mytry(nums);

        return method2(nums);
    }

    private List<List<Integer>> method2(int[] nums) {
        // iteration
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        result.add(list);
        for (int num : nums) {
            int size = result.size();
            for (int i = 0; i < size; i++) {
                List<Integer> temp = new ArrayList<Integer>(result.get(i));
                temp.add(num);
                result.add(temp);
            }
        }
        return result;
    }

    private List<List<Integer>> mytry(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        dfs(nums, 0, result, new ArrayList<Integer>());
        return result;
    }
    private void dfs(int[] nums, int index, List<List<Integer>> result, List<Integer> list) {
        result.add(new ArrayList<Integer>(list)); // 每次得到一个新的都可以加进去
        for (int i = index; i < nums.length; i++) {
            list.add(nums[i]);
            dfs(nums, i + 1, result, list);
            list.remove(list.size() - 1);
        }
    }
}

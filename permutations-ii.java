// 47. Permutations II
// DescriptionHintsSubmissionsDiscussSolution
// Given a collection of numbers that might contain duplicates, return all possible unique permutations.
//
// Example:
//
// Input: [1,1,2]
// Output:
// [
//   [1,1,2],
//   [1,2,1],
//   [2,1,1]
// ]


class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        if (nums == null || nums.length == 0) {
            return Collections.emptyList();
        }

        return mytry(nums);
    }

    private List<List<Integer>> mytry(int[] nums) {
        // DFS
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        dfs(nums, result, new ArrayList<Integer>(), new boolean[nums.length]);
        return result;
    }
    private void dfs(int[] nums, List<List<Integer>> result, List<Integer> list, boolean[] visited) {
        if (list.size() == nums.length) {
            result.add(new ArrayList<>(list));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) {
                continue;
            }
            if (i > 0 && nums[i] == nums[i - 1] && !visited[i - 1]) {
                continue;
            }
            list.add(nums[i]);
            visited[i] = true;
            dfs(nums, result, list, visited);
            list.remove(list.size() - 1);
            visited[i] = false;
        }
    }
}

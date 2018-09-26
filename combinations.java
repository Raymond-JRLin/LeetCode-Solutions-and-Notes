// 77. Combinations
// DescriptionHintsSubmissionsDiscussSolution
// Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.
//
// Example:
//
// Input: n = 4, k = 2
// Output:
// [
//   [2,4],
//   [3,4],
//   [2,3],
//   [1,2],
//   [1,3],
//   [1,4],
// ]



class Solution {
    public List<List<Integer>> combine(int n, int k) {
        if (n < 1 || k < 1) {
            return Collections.emptyList();
        }

        return mytry(n, k);
    }

    private List<List<Integer>> mytry(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        dfs(result, new ArrayList<Integer>(), n, k, 1);
        return result;
    }
    private void dfs(List<List<Integer>> result, List<Integer> list, int n, int k, int index) {
        if (list.size() == k) {
            result.add(new ArrayList<>(list));
            return;
        }
        for (int i = index; i <= n; i++) {
            list.add(i);
            dfs(result, list, n, k, i + 1);
            list.remove(list.size() - 1);
        }
    }
}

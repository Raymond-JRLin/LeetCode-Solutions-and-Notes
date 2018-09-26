// 216. Combination Sum III
// DescriptionHintsSubmissionsDiscussSolution
// Find all possible combinations of k numbers that add up to a number n, given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.
//
// Note:
//
// All numbers will be positive integers.
// The solution set must not contain duplicate combinations.
// Example 1:
//
// Input: k = 3, n = 7
// Output: [[1,2,4]]
// Example 2:
//
// Input: k = 3, n = 9
// Output: [[1,2,6], [1,3,5], [2,3,4]]


class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {
        // 我感觉可以用 DFS 来做， 或许固定一个然后作 2 sum 也行， 可能会 TLE， 但最终过了

        // return my_try(k, n);

        // 20180606 update: no need to get sum of list, just use a variable to record it, and pruning on sum and list size
        return mytry2(k, n);
    }

    private List<List<Integer>> mytry2(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        recursion(result, new ArrayList<Integer>(), k, n, 1, 0);
        return result;
    }
    private void recursion(List<List<Integer>> result, List<Integer> list, int k, int n, int index, int sum) {
        if (list.size() == k && sum == n) {
            result.add(new ArrayList<Integer>(list));
            return;
        }
        if (sum > n || list.size() > k) {
            return;
        }
        for (int i = index; i <= 9; i++) {
            list.add(i);
            recursion(result, list, k, n, i + 1, sum + i);
            list.remove(list.size() - 1);
        }
    }

    private List<List<Integer>> my_try(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        dfs(result, new ArrayList<Integer>(), k, n, 1);
        return result;
    }
    private void dfs(List<List<Integer>> result, List<Integer> list, int k, int n, int pos) {
        if (k == 0 && getSum(list) == n) {
            result.add(new ArrayList<Integer>(list));
            return;
        }
        if (k <= 0) {
            return;
        }
        for (int i = pos; i <= 9; i++) {
            list.add(i);
            dfs(result, list, k - 1, n, i + 1);
            list.remove(list.size() - 1);
        }
    }
    private int getSum(List<Integer> list) {
        int sum = 0;
        for (int num : list) {
            sum += num;
        }
        return sum;
    }
}

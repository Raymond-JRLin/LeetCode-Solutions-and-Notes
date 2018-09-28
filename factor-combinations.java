// 254. Factor Combinations
// DescriptionHintsSubmissionsDiscussSolution
// Numbers can be regarded as product of its factors. For example,
//
// 8 = 2 x 2 x 2;
//   = 2 x 4.
// Write a function that takes an integer n and return all possible combinations of its factors.
//
// Note:
//
// You may assume that n is always positive.
// Factors should be greater than 1 and less than n.
// Example 1:
//
// Input: 1
// Output: []
// Example 2:
//
// Input: 37
// Output:[]
// Example 3:
//
// Input: 12
// Output:
// [
//   [2, 6],
//   [2, 2, 3],
//   [3, 4]
// ]
// Example 4:
//
// Input: 32
// Output:
// [
//   [2, 16],
//   [2, 2, 8],
//   [2, 2, 2, 4],
//   [2, 2, 2, 2, 2],
//   [2, 4, 4],
//   [4, 8]
// ]


class Solution {
    public List<List<Integer>> getFactors(int n) {
        if (n <= 3) {
            return Collections.emptyList();
        }

        return mytry(n);
    }

    private List<List<Integer>> mytry(int n) {
        List<List<Integer>> result = new ArrayList<>();
        dfs(n, result, new ArrayList<>(), 2);
        return result;
    }
    private void dfs(int n, List<List<Integer>> result, List<Integer> list, int index) {
        if (n <= 1) {
            // 到这里可能是把上一层的 n 自身除了一下 （for 循环中可以取到 n）， 所以这里要 > 1 来判断是否已经有了其他至少一个 factor
            if (list.size() > 1) {
                result.add(new ArrayList<Integer>(list));
            }

            return;
        }
        for (int i = index; i <= n; i++) {
            // we should use "<=" since we need to include the same factors after we do, so we control on exit if condition to check is there's already 1 factor in list
            if (n % i == 0) {
                list.add(i);
                dfs(n / i, result, list, i); // no need i + 1, since there may be multiple same factors
                list.remove(list.size() - 1);
            }
        }
    }

}

// 46. Permutations
// DescriptionHintsSubmissionsDiscussSolution
// Given a collection of distinct integers, return all possible permutations.
//
// Example:
//
// Input: [1,2,3]
// Output:
// [
//   [1,2,3],
//   [1,3,2],
//   [2,1,3],
//   [2,3,1],
//   [3,1,2],
//   [3,2,1]
// ]


class Solution {
    public List<List<Integer>> permute(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }

        // return method1(nums);

        // return method2(nums);

        // return method3(nums);

        return method4(nums);
    }

    private List<List<Integer>> method4(int[] nums) {
        // 受到 permutations-ii 的启发， 用一样的方法
        List<List<Integer>> result = new ArrayList<>();
        dfs4(nums, 0, result);
        return result;
    }
    private void dfs4(int[] nums, int pos, List<List<Integer>> result) {
        if (pos == nums.length) {
            List<Integer> list = new ArrayList<>();
            for (int num : nums) {
                list.add(num);
            }
            result.add(list);
            return;
        }
        for (int i = pos; i < nums.length; i++) {
            swap(nums, i, pos);
            dfs4(nums, pos + 1, result);
            swap(nums, i, pos);
        }
    }
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private List<List<Integer>> method3(int[] nums) {
        // iteration
        // 和 method2 类似
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        list.add(nums[0]);
        result.add(list);
        for (int i = 1; i < nums.length; i++) {
            // 要插入的数是 nums[i]
            List<List<Integer>> newResult = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                // 插入的位置是 j
                for (List<Integer> curr : result) {
                    // 对于上一轮的每一个结果， 分别在 j 位置插入 nums[i]
                    List<Integer> next = new ArrayList<>(curr);
                    next.add(j, nums[i]);
                    newResult.add(next);
                }
            }
            // 更新结果
            result = newResult;
        }
        return result;
    }

    private List<List<Integer>> method2(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        dfs2(nums, 0, result, new ArrayList<Integer>());
        return result;
    }
    // 第二种 DFS 或者说 recursion， 用 pos 来指示要插入的数， 类似于 iteration， 在上一轮的 list 的所有不同位置分别插入
    private void dfs2(int[] nums, int pos, List<List<Integer>> result, List<Integer> list) {
        if (list.size() == nums.length) {
            result.add(new ArrayList<>(list));
            return;
        }
        for (int i = 0; i <= list.size(); i++) {
            // 注意这里的 i 的范围， 是上一轮 list 所有的不同位置
            List<Integer> next = new ArrayList<>(list);
            next.add(i, nums[pos]); // 插入 i 位置
            dfs2(nums, pos + 1, result, next);
        }
    }

    private List<List<Integer>> method1(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        dfs(nums, result, new ArrayList<Integer>(), new HashSet<Integer>());
        return result;
    }
    private void dfs(int[] nums, List<List<Integer>> result, List<Integer> list, Set<Integer> set) {
        if (list.size() == nums.length) {
            result.add(new ArrayList<Integer>(list));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(i)) {
                continue;
            }
            list.add(nums[i]);
            set.add(i);
            dfs(nums, result, list, set);
            list.remove(list.size() - 1);
            set.remove(i);
        }
    }
}

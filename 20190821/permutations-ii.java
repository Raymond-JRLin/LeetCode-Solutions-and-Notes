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
            return new ArrayList<>();
        }

        // return method1(nums);

        return method2(nums);
    }

    private List<List<Integer>> method2(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        dfs2(nums, result, 0);
        return result;
    }
    private void dfs2(int[] nums, List<List<Integer>> result, int pos) {
        if (pos == nums.length) {
            List<Integer> list = new ArrayList<>();
            for (int num : nums) {
                list.add(num);
            }
            result.add(list);
            return;
        }
        // 避免重复， 在这一层内部使用一个 set 来去重
        Set<Integer> set = new HashSet<>();
        for (int i = pos; i < nums.length; i++) {
            if (set.contains(nums[i])) {
                continue;
            }
            set.add(nums[i]);
            // 想清楚了这个 swap 其实没有什么特别的， 就是 permutation 的本质
            swap(nums, i, pos);
            dfs2(nums, result, pos + 1);
            swap(nums, i, pos);
        }
    }
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private List<List<Integer>> method1(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        dfs(nums, result, new ArrayList<Integer>(), new boolean[nums.length]);
        return result;
    }
    private void dfs(int[] nums, List<List<Integer>> result, List<Integer> list, boolean[] isVisited) {
        if (list.size() == nums.length) {
            result.add(new ArrayList<>(list));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            // 注意这里的 i 的范围， 找所有可以加入的数
            // 没办法像别的方法一样跳过第一个位置重复的， 因为除了第一个可能重复， 后面的数也可能重复， 这样用起始位置是没办法判断的
            if (isVisited[i]) {
                continue;
            }
            if (i > 0 && nums[i] == nums[i - 1] && !isVisited[i - 1]) {
                continue;
            }
            list.add(nums[i]);
            isVisited[i] = true;
            dfs(nums, result, list, isVisited);
            list.remove(list.size() - 1);
            isVisited[i] = false;
        }
    }
}

// 698. Partition to K Equal Sum Subsets
// DescriptionHintsSubmissionsDiscussSolution
// Given an array of integers nums and a positive integer k, find whether it's possible to divide this array into k non-empty subsets whose sums are all equal.
//
//
//
// Example 1:
//
// Input: nums = [4, 3, 2, 3, 5, 2, 1], k = 4
// Output: True
// Explanation: It's possible to divide it into 4 subsets (5), (1, 4), (2,3), (2,3) with equal sums.
//
//
// Note:
//
// 1 <= k <= len(nums) <= 16.
// 0 < nums[i] < 10000.
// Seen this question in a real interview before?
// Difficulty:Medium


class Solution {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        if (nums == null || nums.length < k) {
            return false;
        }

        // return mytry(nums, k);

        // return method2(nums, k);

        return method3(nums, k);
    }

    private boolean method3(int[] nums, int k) {
        // modified DFS: start from end of array, add values to possible part in k parts
        int n = nums.length;

        int sum = 0; // total sum
        for (int num : nums) {
            sum += num;
        }
        if (sum % k != 0) {
            return false;
        }

        int target = sum / k; // average
        Arrays.sort(nums);
        if (nums[n - 1] > target) {
            // just compare the largest value
            return false;
        }

        return dfs2(nums, k, target, new int[k], n - 1);
    }
    private boolean dfs2(int[] nums, int k, int target, int[] sums, int index) {
        if (index < 0) {
            // use all numbers
            for (int sum : sums) {
                if (sum != target) {
                    return false;
                }
            }
            // each part satisfied
            return true;
        }
        int num = nums[index]; // current value to add
        for (int i = 0; i < k; i++) {
            // iterate k part to see which part can add this current value
            if (sums[i] + num <= target) {
                sums[i] += num;
                if (dfs2(nums, k, target, sums, index - 1)) {
                    return true;
                }
                sums[i] -= num;
            }
            // pruning - accelerate a lot but I'm not very clear
            // it's basically pruning if this part cannot make requirements satisfied, if one value cannot be added into any of parts, then it will do backtracking, i.e. sums[i] -= num, at some value, the part will be removed at all to 0, then there's no way to divied to k parts equally
            // e.g. [2,2,2,2,3,4,5], 4: 最后一步的情况是 sums[5, 4, 3 + 2, 2 + 2], then last 2 cannot be added to any part, then return false -> sums[5, 4, 3 + 2, 2 (+ 2)] -> sums[5, 4, 3 + 2, (2)] -> pruning
            // 所以我感觉这种做法类似于把所有的 values 填充到 k part 里， 巧在我们 sort 之后， 从最后一个最大的数开始从第一个 part 填充， 依次往后， 先把每一个坑位至少占一部分， 然后 value 开始变得小了， 继续从前往后填坑， 如果有一个数哪个坑都放不进去， 那么意味着没办法分 k parts， 这个时候通过 backtracking 把之前做的消掉， 就可以提前结束了
            if (sums[i] == 0) {
                // System.out.println("current part is " + i + ", value is " + num + ", at " + index);
                break;
            }
        }
        return false;
    }

    private boolean method2(int[] nums, int k) {
        // iteration bottom-up DP: bits (len(nums) <= 16)
        // ref: https://leetcode.com/articles/partition-to-k-equal-sum-subsets/
        int n = nums.length;

        int sum = 0; // total sum
        for (int num : nums) {
            sum += num;
        }
        if (sum % k != 0) {
            return false;
        }

        int target = sum / k; // average
        for (int num : nums) {
            if (num > target) {
                return false;
            }
        }

        Arrays.sort(nums); // so we can break in advance
        // definition: f[i] = if the state can be divided with satisfactory of requirements, sums[i] = sum of state i
        boolean[] f = new boolean[1 << n];
        int[] sums = new int[1 << n];
        // initialization
        f[0] = true;
        // DP
        for (int curr = 0; curr < (1 << n); curr++) {
            if (!f[curr]) {
                // this state cannot make it, then no need to go further based on this state
                continue;
            }
            // add each index of nums
            for (int i = 0; i < n; i++) {
                int next = curr | (1 << i);
                if (next != curr && !f[next]) {
                    // it's not going back and it's not already valid
                    if (nums[i] <= target - sums[curr] % target) {
                        // it can be added into sum
                        sums[next] = sums[curr] + nums[i];
                        f[next] = true;
                    } else {
                        // since it's sorted, no need to go further bigger
                        break;
                    }
                }
            }
        }
        // result
        return f[(1 << n) - 1]; // 11...11, use all of digits
    }

    private boolean mytry(int[] nums, int k) {
        // DFS, similar to make subset
        int n = nums.length;

        int sum = 0; // total sum
        for (int num : nums) {
            sum += num;
        }
        if (sum % k != 0) {
            return false;
        }

        sum /= k; // average
        for (int num : nums) {
            if (num > sum) {
                return false;
            }
        }

        return dfs(nums, k, sum, new boolean[n], 0, 0);
    }
    private boolean dfs(int[] nums, int k, int target, boolean[] visited, int index, int sum) {
        if (k == 0) {
            // divide k parts
            for (int i = 0; i < visited.length; i++) {
                if (!visited[i]) {
                    return false;
                }
            }
            // every number is used already
            return true;
        }
        // have 1 part successfully, search next part from beginning
        if (sum == target) {
            return dfs(nums, k - 1, target, visited, 0, 0);
        }
        for (int i = index; i < nums.length; i++) {
            if (visited[i]) {
                // already used
                continue;
            }
            if (sum + nums[i] <= target) {
                // this position can be added
                visited[i] = true;
                if (dfs(nums, k, target, visited, i + 1, sum + nums[i])) {
                    return true;
                }
                visited[i] = false;
            }
        }
        return false;
    }
}

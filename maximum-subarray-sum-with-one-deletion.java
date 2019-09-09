// 1186. Maximum Subarray Sum with One Deletion
// User Accepted: 1108
// User Tried: 2310
// Total Accepted: 1189
// Total Submissions: 5748
// Difficulty: Medium
// Given an array of integers, return the maximum sum for a non-empty subarray (contiguous elements) with at most one element deletion. In other words, you want to choose a subarray and optionally delete one element from it so that there is still at least one element left and the sum of the remaining elements is maximum possible.
//
// Note that the subarray needs to be non-empty after deleting one element.
//
//
//
// Example 1:
//
// Input: arr = [1,-2,0,3]
// Output: 4
// Explanation: Because we can choose [1, -2, 0, 3] and drop -2, thus the subarray [1, 0, 3] becomes the maximum value.
// Example 2:
//
// Input: arr = [1,-2,-2,3]
// Output: 3
// Explanation: We just choose [3] and it's the maximum sum.
// Example 3:
//
// Input: arr = [-1,-1,-1,-1]
// Output: -1
// Explanation: The final subarray needs to be non-empty. You can't choose [-1] and delete -1 from it, then get an empty subarray to make the sum equals to 0.
//
//
// Constraints:
//
// 1 <= arr.length <= 10^5
// -10^4 <= arr[i] <= 10^4


class Solution {
    public int maximumSum(int[] arr) {
        // 我一个开始的想法是肯定要用 prefix sum， 去记录负数， 然后判定 => 不全对
        // 应该这么理解： 最多删一个， 可删可不删， 那么不删就是 Maximum Subarray Sum, 如果要删除， 当然要是负数， 也就是这个数不加入 prefix sum 中。 因为按我一开始想的， 是没办法去记录前面的负数， 因为每个 subarray 中的负数都不一样， 也没有统一的标准譬如加最小的还是怎样。 所以就应该是跳过这个数， 但是 prefix sum 是不能跳过的， 所以从前往后计算一遍， 从后往前计算一遍， 对于某个负数， 删掉它的 maximum subarray sum 是从前往后计算到它前一个位置的最大， 加上从后往前计算到它后一个位置的最大。
        // 这个分别从前后两个方向计算的方法也是 prefix sum 见过的解法。

        int n = arr.length;
        int result = arr[0];

        // 初始化的时候都要把第一个/最后一个位置分别初始化成第一个/最后一个
        // 因为题目要求删掉一个后也还至少要有一个， 如果是 n + 1, input 是单一一个负数就不行了, e.g. [-50]
        int[] forward = new int[n];
        forward[0] = arr[0];
        int[] backward = new int[n];
        backward[n - 1] = arr[n - 1];
        for (int i = 1; i < n; i++) {
            // 这样的计算方法其实不能算是 prefix sum， 准确的说应该是 DP， f[i] = 以 i 结尾的 subarray 的 max sum
            forward[i] = Math.max(arr[i], arr[i] + forward[i - 1]);
            result = Math.max(result, forward[i]);
        }
        for (int i = n - 2; i >= 0; i--) {
            backward[i] = Math.max(arr[i], arr[i] + backward[i + 1]);
            result = Math.max(result, backward[i]);
        }
        // 上面跟随计算的是不删除时候的正常的 Maximum Subarray Sum
        // 现在判断删掉一个负数可以得到的结果
        for (int i = 1; i < n - 1; i++) {
            // 注意这里的 range 和对应的 forward/backward 取值
            if (arr[i] < 0) {
                result = Math.max(result, forward[i - 1] + backward[i + 1]);
            }
        }
        return result;
    }
}

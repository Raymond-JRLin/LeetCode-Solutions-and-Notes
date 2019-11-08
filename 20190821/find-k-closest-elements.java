// 658. Find K Closest Elements
// DescriptionHintsSubmissionsDiscussSolution
//
// Given a sorted array, two integers k and x, find the k closest elements to x in the array. The result should also be sorted in ascending order. If there is a tie, the smaller elements are always preferred.
//
// Example 1:
//
// Input: [1,2,3,4,5], k=4, x=3
// Output: [1,2,3,4]
//
// Example 2:
//
// Input: [1,2,3,4,5], k=4, x=-1
// Output: [1,2,3,4]
//
// Note:
//
//     The value k is positive and will always be smaller than the length of the sorted array.
//     Length of the given array is positive and will not exceed 104
//     Absolute value of elements in the array and x will not exceed 104
//
// UPDATE (2017/9/19):
// The arr parameter had been changed to an array of integers (instead of a list of integers). Please reload the code definition to get the latest changes.
// Seen this question in a real interview before?
//
//     Difficulty:Medium


class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        if (arr == null || arr.length == 0) {
            return Collections.emptyList();
        }

        // return method1(arr, k, x);

        // return method2(arr, k, x);

        return method3(arr, k, x);
    }

    private List<Integer> method3(int[] arr, int k, int x) {
        // 另一种 BS， 去找到最终答案的区间， 用 start 来找到开始的 index
        // O(logN + k) time
        int n = arr.length;
        int start = 0;
        int end = n - k;
        // start 和 end 都可能是答案区间的起始位置 (inclusive)
        while (start < end) {
            int mid = start + (end - start) / 2;
            // 比较 mid 和 mid + k 的值与 x 的差值
            // 这里不能用绝对值比较， 因为如果 x 在 mid + k 右边， 绝对值得比较会错误地移动 end
            if (x - arr[mid] > arr[mid + k] - x) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        // System.out.println("left/right: " + start + ", " + end);
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            result.add(arr[start + i]);
        }
        Collections.sort(result);
        return result;
    }

    private List<Integer> method2(int[] arr, int k, int x) {
        // 2 pointer 向中间缩进找到 k 个
        // O(N) time
        int n = arr.length;
        int left = 0;
        int right = n - 1;
        // 这里和 method1 的 2 pointers 不同是指针移动前指向的不是我们要的答案， 就是排除在外
        // 最终答案的 pointers 是 inclusive
        while (right + 1 - left > k) {
            int leftDiff = Math.abs(arr[left] - x);
            int rightDiff = Math.abs(arr[right] - x);
            if (leftDiff > rightDiff) {
                left++;
            } else {
                right--;
            }
        }

        List<Integer> result = new ArrayList<>();
        for (int i = left; i <= right; i++) {
            result.add(arr[i]);
        }
        Collections.sort(result);
        return result;
    }

    private List<Integer> method1(int[] arr, int k, int x) {
        // 我看到也会想到用 BS， 会想到先找到离 x 最近的 value， 然后 2 pointer 向两边找到 k 个
        // O(logN + k) time
        int n = arr.length;
        int left = Arrays.binarySearch(arr, x);
        if (left < 0) {
            left = - left - 1;
        }
        // 这个 left 找到的实际上是 arr 中第一个大于等于 x 的位置， 或者是 x 应该插入的位置
        // 如果 x 比 arr 中所有数都大， 那么 left == n， 会出现越界， 所以把 left 左移一位
        int right = left;
        left--;
        // System.out.println("left/right: " + left + ", " + right);

        // 在下面的查找过程， 实际上每移动一次 pointer， 无论左右， 都意味着刚刚移动前 pointer 指向的数字是我们找到的一个答案
        // 也就是说 left 和 right 夹着的 (exclusive) 才是我们要的答案
        while (k > 0) {
            int leftDiff = left >= 0 ? Math.abs(arr[left] - x) : Integer.MAX_VALUE;
            int rightDiff = right < n ? Math.abs(arr[right] - x) : Integer.MAX_VALUE;
            if (leftDiff <= rightDiff) {
                left--;
            } else {
                right++;
            }
            k--;
        }
        // System.out.println("left/right: " + left + ", " + right);
        List<Integer> result = new ArrayList<>();
        for (int i = left + 1; i < right; i++) {
            result.add(arr[i]);
        }
        Collections.sort(result);
        return result;
    }
}

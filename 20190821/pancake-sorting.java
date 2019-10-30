// 969. Pancake Sorting
// DescriptionHintsSubmissionsDiscussSolution
//
// Given an array A, we can perform a pancake flip: We choose some positive integer k <= A.length, then reverse the order of the first k elements of A.  We want to perform zero or more pancake flips (doing them one after another in succession) to sort the array A.
//
// Return the k-values corresponding to a sequence of pancake flips that sort A.  Any valid answer that sorts the array within 10 * A.length flips will be judged as correct.
//
//
//
// Example 1:
//
// Input: [3,2,4,1]
// Output: [4,2,4,3]
// Explanation:
// We perform 4 pancake flips, with k values 4, 2, 4, and 3.
// Starting state: A = [3, 2, 4, 1]
// After 1st flip (k=4): A = [1, 4, 2, 3]
// After 2nd flip (k=2): A = [4, 1, 2, 3]
// After 3rd flip (k=4): A = [3, 2, 1, 4]
// After 4th flip (k=3): A = [1, 2, 3, 4], which is sorted.
//
// Example 2:
//
// Input: [1,2,3]
// Output: []
// Explanation: The input is already sorted, so there is no need to flip anything.
// Note that other answers, such as [3, 3], would also be accepted.
//
//
//
// Note:
//
//     1 <= A.length <= 100
//     A[i] is a permutation of [1, 2, ..., A.length]
//
// Seen this question in a real interview before?
//
//     Difficulty:Medium


class Solution {
    public List<Integer> pancakeSort(int[] A) {
        if (A == null || A.length == 0) {
            return Collections.emptyList();
        }

        return method1(A);
    }

    // 非常直接的做法：
    // 1. 每次找到还没有放到最后的最大值
    // 2. reverse 0 到最大值的 index => 把最大值放在 index == 0 上
    // 3. reverse 0 到最大值应该去的位置， 即 max - 1 => 把最大值放在 index == max - 1 上
    // 4. 重复上述步骤

    private List<Integer> method1(int[] nums) {
        int n = nums.length;
        int largest = n;
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int maxIndex = findMaxIndex(nums, largest);
            reverse(nums, 0, maxIndex);
            result.add(maxIndex + 1); // result 加的是个数
            reverse(nums, 0, largest - 1);
            result.add(largest - 1 + 1);
            largest--;
        }
        return result;
    }
    private int findMaxIndex(int[] nums, int max) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == max) {
                return i;
            }
        }
        return -1;
    }
    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            swap(nums, start, end);
            start++;
            end--;
        }
    }
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}

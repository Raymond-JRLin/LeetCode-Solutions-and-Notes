// 900. RLE Iterator
// DescriptionHintsSubmissionsDiscussSolution
//
// Write an iterator that iterates through a run-length encoded sequence.
//
// The iterator is initialized by RLEIterator(int[] A), where A is a run-length encoding of some sequence.  More specifically, for all even i, A[i] tells us the number of times that the non-negative integer value A[i+1] is repeated in the sequence.
//
// The iterator supports one function: next(int n), which exhausts the next n elements (n >= 1) and returns the last element exhausted in this way.  If there is no element left to exhaust, next returns -1 instead.
//
// For example, we start with A = [3,8,0,9,2,5], which is a run-length encoding of the sequence [8,8,8,5,5].  This is because the sequence can be read as "three eights, zero nines, two fives".
//
//
//
// Example 1:
//
// Input: ["RLEIterator","next","next","next","next"], [[[3,8,0,9,2,5]],[2],[1],[1],[2]]
// Output: [null,8,8,5,-1]
// Explanation:
// RLEIterator is initialized with RLEIterator([3,8,0,9,2,5]).
// This maps to the sequence [8,8,8,5,5].
// RLEIterator.next is then called 4 times:
//
// .next(2) exhausts 2 terms of the sequence, returning 8.  The remaining sequence is now [8, 5, 5].
//
// .next(1) exhausts 1 term of the sequence, returning 8.  The remaining sequence is now [5, 5].
//
// .next(1) exhausts 1 term of the sequence, returning 5.  The remaining sequence is now [5].
//
// .next(2) exhausts 2 terms, returning -1.  This is because the first term exhausted was 5,
// but the second term did not exist.  Since the last term exhausted does not exist, we return -1.
//
// Note:
//
//     0 <= A.length <= 1000
//     A.length is an even integer.
//     0 <= A[i] <= 10^9
//     There are at most 1000 calls to RLEIterator.next(int n) per test case.
//     Each call to RLEIterator.next(int n) will have 1 <= n <= 10^9.
//
// Seen this question in a real interview before?
//
//     Difficulty:Medium


class RLEIterator {

    // int[] nums;
    // int index;
    // int used; // # already used in nums[index]

    // 3. BS
    int[] nums; // store A values
    long[] count; // store count continuously
    long sum; // total count along with calling next()
    int left;

    public RLEIterator(int[] A) {
        // this.nums = A;
        // this.index = 0;
        // this.used = 0;

        int n = A.length;
        nums = new int[n / 2];
        count = new long[n / 2];
        for (int i = 0; i < A.length; i += 2) {
            nums[i / 2] = A[i + 1]; // value
            count[i / 2] = (i > 1 ? count[i / 2 - 1] + A[i] : A[0]); // prefix count
        }
        sum = 0L;
        left = 0;
    }

    public int next(int n) {
        // 1.
        // while (index < nums.length - 1 && n > nums[index]) {
        //     n -= nums[index];
        //     index += 2;
        // }
        // if (index >= nums.length) {
        //     return -1;
        // }
        // nums[index] -= n;
        // return nums[index + 1];

        // 2. do not modify the array
        // while (index < nums.length) {
        //     if (n > nums[index] - used) {
        //         // 当前的这个 nums[index + 1] 不够数
        //         n -= (nums[index] - used); // 全用掉
        //         used = 0;
        //         index += 2;
        //     } else {
        //         // 当前够用, 包括 == 的情况， 因为此时仍然要返回 nums[index + 1]
        //         used += n;
        //         return nums[index + 1];
        //     }
        // }
        // return -1;

        // 3. BS
        sum += n; // n 只是当前针对已经 exhausted 的个数， 而 count 要从一开始记录下的总个数
        if (sum > count[count.length - 1]) {
            return -1;
        }
        int start = left;
        int end = count.length - 1;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (count[mid] >= sum) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        left = start; // 更新一下左边
        return nums[start];
    }
}

/**
 * Your RLEIterator object will be instantiated and called as such:
 * RLEIterator obj = new RLEIterator(A);
 * int param_1 = obj.next(n);
 */

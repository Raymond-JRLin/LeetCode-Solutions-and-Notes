// 670. Maximum Swap
// DescriptionHintsSubmissionsDiscussSolution
//
// Given a non-negative integer, you could swap two digits at most once to get the maximum valued number. Return the maximum valued number you could get.
//
// Example 1:
//
// Input: 2736
// Output: 7236
// Explanation: Swap the number 2 and the number 7.
//
// Example 2:
//
// Input: 9973
// Output: 9973
// Explanation: No swap.
//
// Note:
//
//     The given number is in the range [0, 108]
//
// Seen this question in a real interview before?
//
//     Difficulty:Medium


class Solution {
    public int maximumSwap(int num) {

        // return method1(num);

        return method2(num);
    }

    private int method2(int num) {
        // 和 method1 类似， 也是去看后面大的换到前面小的
        // 从前往后， 找到的第一个数， 后面有比它大的能够换的， 就换了
        char[] arr = String.valueOf(num).toCharArray();
        int n = arr.length;
        int[] bucket = new int[10]; // 记录 0 - 9 的 最后出现的 index
        for (int i = 0; i < n; i++) {
            bucket[arr[i] - '0'] = i;
        }
        for (int i = 0; i < n; i++) {
            int curr = (int) (arr[i] - '0'); // 当前位置的数
            for (int j = 9; j > curr; j--) {
                // 去比它大的里面找
                if (bucket[j] > i) {
                    // 最大的数并且在它后面， 就可以交换
                    char temp = arr[i];
                    arr[i] = arr[bucket[j]];
                    arr[bucket[j]] = temp;
                    return Integer.valueOf(String.valueOf(arr));
                }
            }
        }
        return num;
    }

    private int method1(int num) {
        // 很显然我们要把后面部分一个最大的数换到前面一个小的数上去
        // 类似于 permutation 那题， 从前往后找到第一个上升的地方， 以此为界， 后面找到后面半部分最大的数， 调换前面第一个比它小的数
        char[] arr = String.valueOf(num).toCharArray();
        int n = arr.length;
        int index = 1;
        while (index < n && arr[index] <= arr[index - 1]) {
            // <= ， 如果是相等也要往后走
            index++;
        }
        if (index == n) {
            // 本身的数是整体递减的， 已经是最大的了
            return num;
        }
        // 以 index 为界， 分成前半部分， 和后半部分 (inclusive)
        // 找到后面最大的数
        int max = arr[index];
        int maxIndex = index;
        for (int i = index; i < n; i++) {
            if (arr[i] >= max) {
                // 有多个最大的数， 要取最后一个， 这样交换之后可以得到更大的数
                max = arr[i];
                maxIndex = i;
            }
        }
        for (int i = 0; i < index; i++) {
            // 找前面第一个比这个最大的数小的， 交换
            if (arr[i] < max) {
                char temp = arr[i];
                arr[i] = arr[maxIndex];
                arr[maxIndex] = temp;
                return Integer.valueOf(String.valueOf(arr));
            }
        }
        return num;
    }
}

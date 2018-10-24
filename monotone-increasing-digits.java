// 738. Monotone Increasing Digits
// DescriptionHintsSubmissionsDiscussSolution
// Given a non-negative integer N, find the largest number that is less than or equal to N with monotone increasing digits.
//
// (Recall that an integer has monotone increasing digits if and only if each pair of adjacent digits x and y satisfy x <= y.)
//
// Example 1:
// Input: N = 10
// Output: 9
// Example 2:
// Input: N = 1234
// Output: 1234
// Example 3:
// Input: N = 332
// Output: 299
// Note: N is an integer in the range [0, 10^9].


class Solution {
    public int monotoneIncreasingDigits(int N) {
        if (N < 10) {
            return N;
        }

        // 开始想这题是不是和找前一个全排列的数是类似的， 只不过把尾部的数全换成 9， 所以： 先找到从前往后第一个下降的地方， 后面全要变成 9， 前面变成递增的数列。 但是是错的， 因为这里面有可能有多组大小不同的重复的数， 所以找从前往后第一个下降的数并不准确， 而是应该从后往前不断的去找， 因为从前往后第一个下降的数 - 1 之后， 可能会改变它和前面的数的大小关系， 所以要不断的 - 1， 去比较
        return method1(N);
    }

    private int method1(int num) {
        char[] array = String.valueOf(num).toCharArray();
        int n = array.length;
        int index = n;
        // 不能从前往后找第一个， 因为可能有多个 invert， 如 1234
        for (int i = n - 1; i > 0; i--) {
            print(array);
            if (array[i - 1] > array[i]) {
                // i - 1 比后面的大
                index = i - 1; // 是一个我们要的 invert 的位置
                array[i - 1]--; // 这个位置 - 1
            }
        }

        // StringBuilder sb = new StringBuilder();
        // for (int i = 0; i < n; i++) {
        //     if (i > index) {
        //         sb.append(9);
        //     } else {
        //         sb.append(array[i] - '0');
        //     }
        // }
        // return Integer.parseInt(sb.toString());
        // or
        for (int i = index + 1; i < n; i++) {
            array[i] = '9';
        }
        return Integer.parseInt(new String(array));
    }
    private void print(char[] arr) {
        for (char c : arr) {
            System.out.print(c + " ");
        }
        System.out.println();
    }

}

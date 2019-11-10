// 1247. Minimum Swaps to Make Strings Equal
//
//     User Accepted: 1726
//     User Tried: 2087
//     Total Accepted: 1783
//     Total Submissions: 3577
//     Difficulty: Easy
//
// You are given two strings s1 and s2 of equal length consisting of letters "x" and "y" only. Your task is to make these two strings equal to each other. You can swap any two characters that belong to different strings, which means: swap s1[i] and s2[j].
//
// Return the minimum number of swaps required to make s1 and s2 equal, or return -1 if it is impossible to do so.
//
//
//
// Example 1:
//
// Input: s1 = "xx", s2 = "yy"
// Output: 1
// Explanation:
// Swap s1[0] and s2[1], s1 = "yx", s2 = "yx".
//
// Example 2:
//
// Input: s1 = "xy", s2 = "yx"
// Output: 2
// Explanation:
// Swap s1[0] and s2[0], s1 = "yy", s2 = "xx".
// Swap s1[0] and s2[1], s1 = "xy", s2 = "xy".
// Note that you can't swap s1[0] and s1[1] to make s1 equal to "yx", cause we can only swap chars in different strings.
//
// Example 3:
//
// Input: s1 = "xx", s2 = "xy"
// Output: -1
//
// Example 4:
//
// Input: s1 = "xxyyxyxyxx", s2 = "xyyxyxxxyx"
// Output: 4
//
//
//
// Constraints:
//
//     1 <= s1.length, s2.length <= 1000
//     s1, s2 only contain 'x' or 'y'.
//


class Solution {
    public int minimumSwap(String s1, String s2) {

        return method1(s1, s2);
    }

    private int method1(String s1, String s2) {
        // 怎么说呢， 解法其实是比较直接的， 就是去 count 那些 s1 和 s2 中不能够 cancel out 的
        // 然后看看是否能 swap 成两个 string 相等
        // 因为 swap 没有任何要求， 两个 string 之间换就可以了， 所以不用关心 index， 只要关心个数就好
        int n = s1.length();
        int x1 = 0, x2 = 0, y1 = 0, y2 = 0; // 分别记录 1 和 2 中的 x 和 y
        for (int i = 0; i < n; i++) {
            if (s1.charAt(i) == s2.charAt(i)) {
                continue;
            }
            x1 += (s1.charAt(i) == 'x' ? 1 : 0);
            y1 += (s1.charAt(i) == 'y' ? 1 : 0);
            x2 += (s2.charAt(i) == 'x' ? 1 : 0);
            y2 += (s2.charAt(i) == 'y' ? 1 : 0);
        }
        // 如果 x 或 y 在两个 string 中的和是奇数， 那么怎么样也不能 swap 相等
        // 因为奇数就意味着有一个 string 中的 x/y 多一个， 那怎么样都没办法让两个 string 能够 cancel out 这个多出来的一个
        if ((x1 + x2) % 2 != 0 || (y1 + y2) % 2 != 0) {
            return -1;
        }
        // 现在 x 和 y 的总和都是偶数， 那么可以 swap 成功
        // 我们总是想要把 x 和 y 对调， 这样成对的 x 和 y 就可以 cancel out
        // 但是因为是 x 和 y 的【和】是偶数， 它们分布在 string 1 和 2 中并不一定每边都是偶数
        // 如果两边都是偶数， 那好办， 只要把一半的 x 与另一边一半的 y 交换即可
        // 如果两边是奇数， 此时就会出现 example 2: xy 与 yx， 那么这种情况对于一对 xy 要交换两次
        // 无论怎样， 交换的次数由两部分组成： 1. xx/yy 2. xy， 算一边 string 就好了
        return x1 / 2 + y1 / 2 + (x1 % 2) * 2;
    }
}

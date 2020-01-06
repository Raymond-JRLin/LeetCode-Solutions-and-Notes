// 821. Shortest Distance to a Character
// DescriptionHintsSubmissionsDiscussSolution
//
// Given a string S and a character C, return an array of integers representing the shortest distance from the character C in the string.
//
// Example 1:
//
// Input: S = "loveleetcode", C = 'e'
// Output: [3, 2, 1, 0, 1, 0, 0, 1, 2, 2, 1, 0]
//
//
//
// Note:
//
//     S string length is in [1, 10000].
//     C is a single character, and guaranteed to be in string S.
//     All letters in S and C are lowercase.
//
// Seen this question in a real interview before?
//
//     Difficulty:Easy


class Solution {
    public int[] shortestToChar(String S, char C) {

        // return mytry(S, C);

        // return method2(S, C);

        return method3(S, C);
    }

    private int[] method3(String s, char c) {
        // 类似于 method2， 先把 c 位置设成 0， 然后 2 pass 分别更新从 0 往左/往右
        int n = s.length();
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            result[i] = s.charAt(i) == c ? 0 : n;
        }
        for (int i = 1; i < n; i++) {
            result[i] = Math.min(result[i], result[i - 1] + 1);
        }
        for (int i = n - 2; i >= 0; i--) {
            result[i] = Math.min(result[i], result[i + 1] + 1);
        }
        return result;
    }

    private int[] method2(String s, char c) {
        // 在前面我对每两个 c 的 index， 从两边向中间走
        // 其实可以写成 2 pass
        int n = s.length();
        int[] result = new int[n];
        int pos = - n;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == c) {
                pos = i;
            }
            // 减去前一个 c 出现的 position
            result[i] = i - pos;
        }
        for (int i = n - 1; i >= 0; i--) {
            if (s.charAt(i) == c) {
                pos = i;
            }
            result[i] = Math.min(result[i], Math.abs(i - pos));
        }
        return result;
    }

    private int[] mytry(String s, char c) {
        int n = s.length();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == c) {
                list.add(i);
            }
        }
        int len = 0;
        int[] result = new int[n];
        for (int i = list.get(0); i >= 0; i--) {
            result[i] = len++;
        }
        for (int i = 1; i < list.size(); i++) {
            int prev = list.get(i - 1);
            int curr = list.get(i);
            int mid = (prev + curr) / 2;
            len = 0;
            for (int j = 0; j + prev <= mid; j++) {
                result[j + prev] = len;
                result[curr - j] = len;
                len++;
            }
        }
        len = 0;
        for (int i = list.get(list.size() - 1); i < n; i++) {
            result[i] = len++;
        }
        return result;
    }
}

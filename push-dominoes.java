// 838. Push Dominoes
// DescriptionHintsSubmissionsDiscussSolution
// There are N dominoes in a line, and we place each domino vertically upright.
//
// In the beginning, we simultaneously push some of the dominoes either to the left or to the right.
//
//
//
// After each second, each domino that is falling to the left pushes the adjacent domino on the left.
//
// Similarly, the dominoes falling to the right push their adjacent dominoes standing on the right.
//
// When a vertical domino has dominoes falling on it from both sides, it stays still due to the balance of the forces.
//
// For the purposes of this question, we will consider that a falling domino expends no additional force to a falling or already fallen domino.
//
// Given a string "S" representing the initial state. S[i] = 'L', if the i-th domino has been pushed to the left; S[i] = 'R', if the i-th domino has been pushed to the right; S[i] = '.', if the i-th domino has not been pushed.
//
// Return a string representing the final state.
//
// Example 1:
//
// Input: ".L.R...LR..L.."
// Output: "LL.RR.LLRRLL.."
// Example 2:
//
// Input: "RR.L"
// Output: "RR.L"
// Explanation: The first domino expends no additional force on the second domino.
// Note:
//
// 0 <= N <= 10^5
// String dominoes contains only 'L', 'R' and '.'
// Seen this question in a real interview before?
// Difficulty:Medium


class Solution {
    public String pushDominoes(String dominoes) {
        if (dominoes == null || dominoes.length() == 0) {
            return dominoes;
        }

        return method1(dominoes);
    }

    private String method1(String s) {
        // 如果通过 . 来判断是不对的， 因为有从左往右扫 . 的时候是有先后关系的， 但是本题中力量不可传递， 意味着同时向里倒下则中间的不动， 所以要保证夹在中间的 .  要同时 check 两端并改动， 而不能先后改动
        // actually there're only 4 scenarios:
        // 1: L ... L => L (L) L
        // 2: R ... L => R (R) (.) (L) L
        // 3: L ... R => L ... R
        // 4: R ... R => R (R) R
        // 因此我们可以判断当走到 L 或者 R 的时候， . 的前一个 （最后遇到的）是 L 还是 R
        int n = s.length();
        char[] arr = s.toCharArray();
        int lastL = -1;
        int lastR = -1;
        for (int i = 0; i <= n; i++) {
            if (i == n || arr[i] == 'R') {
                // attention for the last index
                if (lastR > lastL) {
                    // 4
                    while (lastR < i) {
                        arr[lastR++] = 'R';
                    }
                }
                // 3: no changes
                // update the last seen R
                lastR = i;
            } else if (arr[i] == 'L') {
                if (lastL > lastR || lastL == -1 && lastR == -1) {
                    // attention to the very beginning
                    // 1
                    while (++lastL < i) {
                        // add 1 to lastL first
                        arr[lastL] = 'L';
                    }
                } else {
                    // 2
                    int l = lastR + 1;
                    int r = i - 1;
                    while (l < r) {
                        arr[l++] = 'R';
                        arr[r--] = 'L';
                    }
                }
                lastL = i;
            }
        }
        return String.valueOf(arr);
    }
}

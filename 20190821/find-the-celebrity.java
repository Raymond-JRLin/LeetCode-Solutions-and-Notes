// 277. Find the Celebrity
// DescriptionHintsSubmissionsDiscussSolution
//
// Suppose you are at a party with n people (labeled from 0 to n - 1) and among them, there may exist one celebrity. The definition of a celebrity is that all the other n - 1 people know him/her but he/she does not know any of them.
//
// Now you want to find out who the celebrity is or verify that there is not one. The only thing you are allowed to do is to ask questions like: "Hi, A. Do you know B?" to get information of whether A knows B. You need to find out the celebrity (or verify there is not one) by asking as few questions as possible (in the asymptotic sense).
//
// You are given a helper function bool knows(a, b) which tells you whether A knows B. Implement a function int findCelebrity(n). There will be exactly one celebrity if he/she is in the party. Return the celebrity's label if there is a celebrity in the party. If there is no celebrity, return -1.
//
//
//
// Example 1:
//
// Input: graph = [
//   [1,1,0],
//   [0,1,0],
//   [1,1,1]
// ]
// Output: 1
// Explanation: There are three persons labeled with 0, 1 and 2. graph[i][j] = 1 means person i knows person j, otherwise graph[i][j] = 0 means person i does not know person j. The celebrity is the person labeled as 1 because both 0 and 2 know him but 1 does not know anybody.
//
// Example 2:
//
// Input: graph = [
//   [1,0,1],
//   [1,1,0],
//   [0,1,1]
// ]
// Output: -1
// Explanation: There is no celebrity.
//
//
//
// Note:
//
//     The directed graph is represented as an adjacency matrix, which is an n x n matrix where a[i][j] = 1 means person i knows person j while a[i][j] = 0 means the contrary.
//     Remember that you won't have direct access to the adjacency matrix.
//
// Seen this question in a real interview before?
//
//     Difficulty:Medium



/* The knows API is defined in the parent class Relation.
      boolean knows(int a, int b); */

public class Solution extends Relation {
    public int findCelebrity(int n) {
        if (n < 1) {
            return -1;
        }

        // return mytry(n);

        // return method2(n);

        return method3(n);
    }

    private int method3(int n) {
        // another 2 pass O(N)
        int candidate = 0;
        for (int i = 1; i < n; i++) {
            if (knows(candidate, i)) {
                candidate = i;
            }
        }
        // 经过上面一轮， candidate 一定会停留在 k, 0 <= k <= n - 1
        // 就意味着 [0, k - 1] 都不可能是 celebrity， 因为他们都至少知道一个数在 [0, k - 1] 中
        // 后面的 [k + 1, n - 1] 也不可能， 因为 k 不知道他们中的任何一个
        // 所以 k 是潜在的 celebrity
        // double check
        for (int i = 0; i < n; i++) {
            if (i != candidate && (knows(candidate, i) || !knows(i, candidate))) {
                return -1;
            }
        }
        return candidate;
    }

    private int method2(int n) {
        // 在 mytry 当中总是查 i 是否可能是 celebrity， 但其实 knows(i, j) 的结果可以反映 i 或 j
        // 如果是 true： i 不可能是
        // 如果是 false： j 不可能是
        // 所以可以利用这个要么抹掉 i 要么抹掉 j， 这个其实就是 2 pointers

        // 也可以用 stack 来做， 每次 pop 2 个来比较， 总会有一个留下来， 另一个被抹掉， 留下来的继续放进 stack 里直到只剩下一个， 那就是潜在的 candidate

        int start = 0;
        int end = n - 1;
        while (start < end) {
            if (knows(start, end)) {
                start++;
            } else {
                end--;
            }
        }
        // double check
        // 因为 start 可能会知道它前面的
        for (int i = 0; i < n; i++) {
            if (i != start && (knows(start, i) || !knows(i, start))) {
                return -1;
            }
        }
        return start;
    }

    private int mytry(int n) {
        // brute force
        for (int i = 0; i < n; i++) {
            boolean isCelebrity = true;
            for (int j = 0; j < n; j++) {
                if (j == i) {
                    continue;
                }
                if (knows(i, j) || !knows(j, i)) {
                    isCelebrity = false;
                    break;
                }
            }
            if (isCelebrity) {
                return i;
            }
        }
        return -1;
    }
}

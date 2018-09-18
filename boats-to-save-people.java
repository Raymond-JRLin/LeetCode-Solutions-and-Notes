// 885. Boats to Save People
// User Accepted: 1356
// User Tried: 1854
// Total Accepted: 1381
// Total Submissions: 4230
// Difficulty: Medium
// The i-th person has weight people[i], and each boat can carry a maximum weight of limit.
//
// Each boat carries at most 2 people at the same time, provided the sum of the weight of those people is at most limit.
//
// Return the minimum number of boats to carry every given person.  (It is guaranteed each person can be carried by a boat.)
//
//
//
// Example 1:
//
// Input: people = [1,2], limit = 3
// Output: 1
// Explanation: 1 boat (1, 2)
// Example 2:
//
// Input: people = [3,2,2,1], limit = 3
// Output: 3
// Explanation: 3 boats (1, 2), (2) and (3)
// Example 3:
//
// Input: people = [3,5,3,4], limit = 5
// Output: 4
// Explanation: 4 boats (3), (3), (4), (5)
// Note:
//
// 1 <= people.length <= 50000
// 1 <= people[i] <= limit <= 30000


class Solution {
    public int numRescueBoats(int[] people, int limit) {
        if (people == null || people.length == 0) {
            return 0;
        }

        return method1(people, limit);
    }

    private int method1(int[] p, int limit) {
        // 2 pointers
        // 注意审题啊： 一艘船最多只能坐两个人， 所以先放着数学证明， 可以直观的感受到， 如果头尾可以坐得下， 那么同时移动； 如果不能同时坐下， j 移动。 所以可以看到每一次必然有一艘船走， 并且 j 总是移动的
        // can check some provements:
        // https://leetcode.com/problems/boats-to-save-people/discuss/156715/Simple-sort-and-two-pointer-with-detailed-provement
        // https://leetcode.com/problems/boats-to-save-people/discuss/156855/6-lines-Java-O(nlogn)-code-sorting-+-greedy-with-greedy-algorithm-proof.
        int n = p.length;
        Arrays.sort(p);
        int i = 0;
        int j = n - 1;
        int result = 0;
        while (i <= j) {
            if (sum + p[i] + p[j] <= limit) {
                i++;
            }
            j--;
            result++;
        }
        return result;
    }
}

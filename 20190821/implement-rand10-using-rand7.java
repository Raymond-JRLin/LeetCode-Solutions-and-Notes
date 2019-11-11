// 470. Implement Rand10() Using Rand7()
// DescriptionHintsSubmissionsDiscussSolution
//
// Given a function rand7 which generates a uniform random integer in the range 1 to 7, write a function rand10 which generates a uniform random integer in the range 1 to 10.
//
// Do NOT use system's Math.random().
//
//
//
// Example 1:
//
// Input: 1
// Output: [7]
//
// Example 2:
//
// Input: 2
// Output: [8,4]
//
// Example 3:
//
// Input: 3
// Output: [8,1,10]
//
//
//
// Note:
//
//     rand7 is predefined.
//     Each testcase has one argument: n, the number of times that rand10 is called.
//
//
//
// Follow up:
//
//     What is the expected value for the number of calls to rand7() function?
//     Could you minimize the number of calls to rand7()?
//
// Seen this question in a real interview before?
//
//     Difficulty:Medium


/**
 * The rand7() API is already defined in the parent class SolBase.
 * public int rand7();
 * @return a random integer in the range 1 to 7
 */
class Solution extends SolBase {
    public int rand10() {
        // 这里不好写解释， 看看 discussion https://leetcode.com/problems/implement-rand10-using-rand7/discuss/150301/Three-line-Java-solution-the-idea-can-be-generalized-to-%22Implement-RandM()-Using-RandN()%22， 和花花的讲解 https://www.youtube.com/watch?v=Wyauxe92JJA
        // 用一个 7 * 7 的 matrix 来看会比较直观一点
        // 总的来说就是要用 rand7() 先得到一个至少有 10 的数， 要超过它， 但是加法和乘法是错的， 因为产生不同的数的概率是不相等的
        // 所以可以想像扔骰子， 产生 7 * 7 的 matrix， 结果是 0 - 48 共 49 个数， 每个数都可以 % 10 + 1 产生 1 - 10
        // 最后一部分 40 - 48 并不是均匀分布在 0 - 9 之间， 所以如果结果在这个范围内， 则不要， 重新来过， 这叫 rejection sampling
        int result = 40;
        while (result >= 40) {
            result = (rand7() - 1) * 7 + (rand7() - 1);
        }
        return result % 10 + 1;

        // 不减 1 的做法：
        // 上面就是为了从 0 开始， 在 matrix 可能好看一点
        // int result = 41;
        // while (result >= 41) {
        //     result = (rand7() - 1) * 7 + rand7();
        // }
        // return result % 10 + 1;
    }
}

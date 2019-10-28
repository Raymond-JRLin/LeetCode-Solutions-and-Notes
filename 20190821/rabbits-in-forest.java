// 781. Rabbits in Forest
// DescriptionHintsSubmissionsDiscussSolution
//
// In a forest, each rabbit has some color. Some subset of rabbits (possibly all of them) tell you how many other rabbits have the same color as them. Those answers are placed in an array.
//
// Return the minimum number of rabbits that could be in the forest.
//
// Examples:
// Input: answers = [1, 1, 2]
// Output: 5
// Explanation:
// The two rabbits that answered "1" could both be the same color, say red.
// The rabbit than answered "2" can't be red or the answers would be inconsistent.
// Say the rabbit that answered "2" was blue.
// Then there should be 2 other blue rabbits in the forest that didn't answer into the array.
// The smallest possible number of rabbits in the forest is therefore 5: 3 that answered plus 2 that didn't.
//
// Input: answers = [10, 10, 10]
// Output: 11
//
// Input: answers = []
// Output: 0
//
// Note:
//
//     answers will have length at most 1000.
//     Each answers[i] will be an integer in the range [0, 999].
//
// Seen this question in a real interview before?
//
//     Difficulty:Medium


class Solution {
    public int numRabbits(int[] answers) {
        if (answers == null || answers.length == 0) {
            return 0;
        }

        return method1(answers);
    }

    // 感觉这个题目不是很好理解
    // answers 数组里的每个 number 表示的是某种颜色的一只兔子， 看和它相同颜色的其他兔子， 有多少只， 不包括它自己
    // 所以这种颜色的兔子会有 number + 1 只
    // 但是题目中说了， 是 subset 兔子回答， 就是说 answers 数组里的回答的兔子可能是一部分， 也可能全回答
    // 如果都回答了， 那么不可以直接由 number + 1 得到这个颜色组的兔子数
    // 所以我们要把所有相同的答案和在一起， 因为相同答案的必定是同种颜色， 即它们看其他的同颜色的兔子的个数是相等的， （当然可以可能不同颜色的兔子有相同的个数， 但也无所谓， 反正求的是兔子个数， 而不是颜色种类数）
    // 通过除和取余来看是否这一组颜色相同的兔子都回答了， 还是 subset 回答了， subset 的话如果数量太少要补足一组

    private int method1(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>(); // <answers 数组中的回答数（即 number）， # 兔子回答了这个 number>
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int result = 0;
        for (int key : map.keySet()) {
            int count = map.get(key); // 有 count 只兔子回答了 key number
            // 那么这种颜色的兔子一共有 key + 1 只
            if (count % (key + 1) == 0) {
                // count / (key + 1) 表示有多少组这种兔子
                result += count / (key + 1) * (key + 1);
            } else {
                result += (count / (key + 1) + 1) * (key + 1);
            }
            // if else 可以简写成: (count + key) / (key + 1) * (key + 1), 加一个 key 先补足一下
        }
        return result;
    }
}

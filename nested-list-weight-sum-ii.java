// 364. Nested List Weight Sum II
// DescriptionHintsSubmissionsDiscussSolution
// Given a nested list of integers, return the sum of all integers in the list weighted by their depth.
//
// Each element is either an integer, or a list -- whose elements may also be integers or other lists.
//
// Different from the previous question where weight is increasing from root to leaf, now the weight is defined from bottom up. i.e., the leaf level integers have weight 1, and the root level integers have the largest weight.
//
// Example 1:
//
// Input: [[1,1],2,[1,1]]
// Output: 8
// Explanation: Four 1's at depth 1, one 2 at depth 2.
// Example 2:
//
// Input: [1,[4,[6]]]
// Output: 17
// Explanation: One 1 at depth 3, one 4 at depth 2, and one 6 at depth 1; 1*3 + 4*2 + 6*1 = 17.



/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *     // Constructor initializes an empty nested list.
 *     public NestedInteger();
 *
 *     // Constructor initializes a single integer.
 *     public NestedInteger(int value);
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // Set this NestedInteger to hold a single integer.
 *     public void setInteger(int value);
 *
 *     // Set this NestedInteger to hold a nested list and adds a nested integer to it.
 *     public void add(NestedInteger ni);
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
class Solution {
    public int depthSumInverse(List<NestedInteger> nestedList) {
        if (nestedList == null || nestedList.size() == 0) {
            return 0;
        }

        // return mytry(nestedList);

        // return method2(nestedList);

        // return method3(nestedList);

        return method4(nestedList);
    }

    private int method4(List<NestedInteger> nestedList) {
        // iteration version of method3
        // ref: https://leetcode.com/problems/nested-list-weight-sum-ii/discuss/83641/No-depth-variable-no-multiplication
        int weighted = 0;
        int unweighted = 0;
        while (!nestedList.isEmpty()) {
            List<NestedInteger> nextLevel = new ArrayList<>();
            for (NestedInteger ni : nestedList) {
                if (ni.isInteger()) {
                    unweighted += ni.getInteger();
                } else {
                    nextLevel.addAll(ni.getList());
                }
            }
            weighted += unweighted; // keep adding previous level sum
            nestedList = nextLevel;
        }
        return weighted;
    }

    private int method3(List<NestedInteger> nestedList) {
        // 因为这题是 bottom-up, 如果我们 top-down 走的话， 就是把之前的和乘上它的 height， 其实也就是加 height 次， 但因为我们事先不知道 height 是多少， 因此我们可以用加法来代替乘法， top-down 的把当前的和传下去， 在下一次层再加一次
        // ref: https://leetcode.com/problems/nested-list-weight-sum-ii/discuss/83649/Share-my-2ms-intuitive-one-pass-no-multiplication-solution
        return dfs(nestedList, 0);
    }
    private int dfs(List<NestedInteger> nestedList, int prevSum) {
        if (nestedList == null || nestedList.size() == 0) {
            return 0;
        }
        int currSum = prevSum;
        List<NestedInteger> nextLevel = new ArrayList<>();
        for (NestedInteger ni : nestedList) {
            if (ni.isInteger()) {
                currSum += ni.getInteger();
            } else {
                nextLevel.addAll(ni.getList());
            }
        }
        int nextSum = dfs(nextLevel, currSum);
        return currSum + nextSum; // currSum will be added twice here
    }

    private int method2(List<NestedInteger> nestedList) {
        // HashMap: record the sum with same height, and get the sum after getting the map. O(N) time and O(Height) space
        // ref: https://leetcode.com/problems/nested-list-weight-sum-ii/discuss/83703/HashMap-Java-solution-with-Explanation
        Map<Integer, Integer> map = new HashMap<>();
        int h = getMap(nestedList, map, 1);
                // [1,[4,[6]]]
        // depth   3   2  1
        // level   1   2  3
        int sum = 0;
        for (int i = 1; i <= h; i++) {
            if (map.containsKey(i)) {
                sum += map.get(i) * (h + 1 - i);
            }
        }
        return sum;
    }
    private int getMap(List<NestedInteger> nestedList, Map<Integer, Integer> map, int level) {
        if (nestedList == null || nestedList.size() == 0) {
            return 0;
        }
        int height = 0;
        for (NestedInteger ni : nestedList) {
            if (ni.isInteger()) {
                height = Math.max(height, 1);
                map.put(level, map.getOrDefault(level, 0) + ni.getInteger());
            } else {
                height = Math.max(height, getMap(ni.getList(), map, level + 1) + 1);
            }
        }
        return height;
    }

    private int mytry(List<NestedInteger> nestedList) {
        // brute force with 2 DFS: find the depth first by 1st DFS, then do like I with 2nd DFS. O(N) time
        int h = getHeight(nestedList);
        System.out.println(h);
        return getSum(nestedList, h);
    }
    private int getSum(List<NestedInteger> nestedList, int h) {
        if (nestedList == null || nestedList.size() == 0) {
            return 0;
        }
        int sum = 0;
        for (NestedInteger ni : nestedList) {
            if (ni.isInteger()) {
                sum += h * ni.getInteger();
            } else {
                sum += getSum(ni.getList(), h - 1); // no need to * h, since h is current level's height
            }
        }
        return sum;
    }
    private int getHeight(List<NestedInteger> nestedList) {
        if (nestedList == null || nestedList.size() == 0) {
            return 0;
        }
        int height = 0;
        for (NestedInteger ni : nestedList) {
            if (ni.isInteger()) {
                height = Math.max(height, 1);
            } else {
                height = Math.max(height, getHeight(ni.getList()) + 1);
            }
        }
        return height;
    }
}

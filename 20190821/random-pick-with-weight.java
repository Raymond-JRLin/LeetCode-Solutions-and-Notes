// 528. Random Pick with Weight
// DescriptionHintsSubmissionsDiscussSolution
//
// Given an array w of positive integers, where w[i] describes the weight of index i, write a function pickIndex which randomly picks an index in proportion to its weight.
//
// Note:
//
//     1 <= w.length <= 10000
//     1 <= w[i] <= 10^5
//     pickIndex will be called at most 10000 times.
//
// Example 1:
//
// Input:
// ["Solution","pickIndex"]
// [[[1]],[]]
// Output: [null,0]
//
// Example 2:
//
// Input:
// ["Solution","pickIndex","pickIndex","pickIndex","pickIndex","pickIndex"]
// [[[1,3]],[],[],[],[],[]]
// Output: [null,0,1,1,1,0]
//
// Explanation of Input Syntax:
//
// The input is two lists: the subroutines called and their arguments. Solution's constructor has one argument, the array w. pickIndex has no arguments. Arguments are always wrapped with a list, even if there aren't any.
// Seen this question in a real interview before?
//
//     Difficulty:Medium


class Solution {

    // 1. prefix sum => count, BS
    int[] nums;
    Random rand;

    // 2. TreeMap: store i as key, count as value
    // TreeMap<Integer, Integer> map; // <count, i>
    // Random rand;
    // int count;

    // 个人觉得 BS 的方法会好一点， 因为 TreeMap 没办法 handle follow-up 比如说有的数的 weight 是 0
    // TreeMap 不能有 duplicate 所以不行
    // 我下面写的 BS 是可以直接 handle 的， 因为找到了没有立即返回， 而是去找第一个 value == index 的

    public Solution(int[] w) {
        // 1.
        int n = w.length;
        nums = new int[n];
        rand = new Random();

        int count = 0;
        for (int i = 0; i < n; i++) {
            count += w[i];
            nums[i] = count;
        }

        // 2.
        // map = new TreeMap<>();
        // rand = new Random();
        // count = 0;
        // for (int i = 0; i < w.length; i++) {
        //     count += w[i];
        //     map.put(count, i);
        // }
    }

    public int pickIndex() {
        // 1. BS
        int start = 0;
        int end = nums.length - 1;
        // nextInt(end): [0, end)
        // 而这里比较的个数是从 1 开始的， 到 nums[end] (inclusive)
        int index = rand.nextInt(nums[end]) + 1;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] < index) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        return start;

        // 2.
        // int index = rand.nextInt(count) + 1;
        // int key = map.ceilingKey(index);
        // return map.get(key);
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(w);
 * int param_1 = obj.pickIndex();
 */

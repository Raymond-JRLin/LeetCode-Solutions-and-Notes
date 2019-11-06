// 403. Frog Jump
// DescriptionHintsSubmissionsDiscussSolution
//
// A frog is crossing a river. The river is divided into x units and at each unit there may or may not exist a stone. The frog can jump on a stone, but it must not jump into the water.
//
// Given a list of stones' positions (in units) in sorted ascending order, determine if the frog is able to cross the river by landing on the last stone. Initially, the frog is on the first stone and assume the first jump must be 1 unit.
//
// If the frog's last jump was k units, then its next jump must be either k - 1, k, or k + 1 units. Note that the frog can only jump in the forward direction.
//
// Note:
//
//     The number of stones is ≥ 2 and is < 1,100.
//     Each stone's position will be a non-negative integer < 231.
//     The first stone's position is always 0.
//
// Example 1:
//
// [0,1,3,5,6,8,12,17]
//
// There are a total of 8 stones.
// The first stone at the 0th unit, second stone at the 1st unit,
// third stone at the 3rd unit, and so on...
// The last stone at the 17th unit.
//
// Return true. The frog can jump to the last stone by jumping
// 1 unit to the 2nd stone, then 2 units to the 3rd stone, then
// 2 units to the 4th stone, then 3 units to the 6th stone,
// 4 units to the 7th stone, and 5 units to the 8th stone.
//
// Example 2:
//
// [0,1,2,3,4,8,9,11]
//
// Return false. There is no way to jump to the last stone as
// the gap between the 5th and 6th stone is too large.
//
// Seen this question in a real interview before?
//
//     Difficulty:Hard


class Solution {
    public boolean canCross(int[] stones) {
        if (stones == null || stones.length == 0) {
            return true;
        }

        // return method1(stones);

        return method2(stones);
    }

    private boolean method2(int[] stones) {
        // 第一步从 0 起跳， 走 1 unit， 第二步最多 2， 第三步最多 3， 所以最多可以走的长度是 n + 1， n 是 stones[] 的 length
        // 这里要注意这指的是最多可以走的， 而不是最远可以走到的
        // 所以可以用 DP 来解
        int n = stones.length;
        // definitiln: f[i][j] = 在 i stone 上是否能向前走 j unit
        boolean[][] f = new boolean[n][n + 1];
        // initialization
        f[0][1] = true;
        // DP
        // 从 1 - n， 看是否前面某一个 stone 可以走足够距离到它
        // 对于每个 i， f[i][] 都只有 3 个位置为 true
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                int k = stones[i] - stones[j]; // 从 j stone 到 i stone 的距离为 k
                // invalid
                if (k < 0 || k >= n + 1) {
                    continue;
                }
                // 前一块 j stone 没办法走 k 到 i
                if (!f[j][k]) {
                    continue;
                }
                // i stone 有 3 种距离可以往下走
                if (k > 1) {
                    f[i][k - 1] = true;
                }
                f[i][k] = true;
                if (k + 1 < n + 1) {
                    f[i][k + 1] = true;
                }
            }
        }
        // result
        // 最后一块 stone 是否可以有任意一个 k valid 可以往下跳， 意味着前面有走到最后一块 stone
        // 也可以在 DP 过程中看 i 是否达到了 n - 1
        for (int j = 1; j < n + 1; j++) {
            if (f[n - 1][j]) {
                return true;
            }
        }
        return false;
    }

    private boolean method1(int[] stones) {
        // 每次跳 k unit 到 stone i 上， 往后跳可以是 k - 1, k, k + 1, 那就把每个 stone i 可以跳的 k 记下来， 看看能不能到达最后
        int n = stones.length;
        int last = stones[n - 1];

        Map<Integer, Set<Integer>> map = new HashMap<>(); // <stone, <set of k at stone k>>
        // initialization, 第一个 stone 总是 0
        map.put(0, new HashSet<>());
        map.get(0).add(1);
        for (int i = 0; i < n - 1; i++) {
            int stone = stones[i];
            // 这个 stone reach 不到
            if (!map.containsKey(stone)) {
                return false;
            }
            for (int k : map.get(stone)) {
                int next = stone + k;
                if (next == last) {
                    return true;
                }
                Set<Integer> set = map.computeIfAbsent(next, dummy -> (new HashSet<>()));
                if (k > 1) {
                    set.add(k - 1);
                }
                set.add(k);
                set.add(k + 1);
            }
        }
        return false;
    }
}

// 1203. Sort Items by Groups Respecting Dependencies
// User Accepted: 117
// User Tried: 195
// Total Accepted: 119
// Total Submissions: 373
// Difficulty: Hard
// There are n items each belonging to zero or one of m groups where group[i] is the group that the i-th item belongs to and it's equal to -1 if the i-th item belongs to no group. The items and the groups are zero indexed. A group can have no item belonging to it.
//
// Return a sorted list of the items such that:
//
// The items that belong to the same group are next to each other in the sorted list.
// There are some relations between these items where beforeItems[i] is a list containing all the items that should come before the i-th item in the sorted array (to the left of the i-th item).
// Return any solution if there is more than one solution and return an empty list if there is no solution.
//
//
//
// Example 1:
//
//
//
// Input: n = 8, m = 2, group = [-1,-1,1,0,0,1,0,-1], beforeItems = [[],[6],[5],[6],[3,6],[],[],[]]
// Output: [6,3,4,1,5,2,0,7]
// Example 2:
//
// Input: n = 8, m = 2, group = [-1,-1,1,0,0,1,0,-1], beforeItems = [[],[6],[5],[6],[3],[],[4],[]]
// Output: []
// Explanation: This is the same as example 1 except that 4 needs to be before 6 in the sorted list.
//
//
//
// Constraints:
//
// 1 <= m <= n <= 3*10^4
// group.length == beforeItems.length == n
// -1 <= group[i] <= m-1
// 0 <= beforeItems[i].length <= n-1
// 0 <= beforeItems[i][j] <= n-1
// i != beforeItems[i][j]
// beforeItems[i] does not contain duplicates elements.


class Solution {
    public int[] sortItems(int n, int m, int[] group, List<List<Integer>> beforeItems) {

        return method1(n, m, group, beforeItems);
    }

    private int[] method1(int n, int m, int[] group, List<List<Integer>> beforeItems) {
        // 题本身其实不难， 充分理解题意比较重要， 并且要想到这里 item 之间的拓扑关系还会涉及到不同组之间的
        // 那在下面的处理就是， 不同组之间 item 的拓扑关系只认为是 group 之间的就好， 因为题目要求
        // 相同组的 item 之间的拓扑关系再具体表现为 item 之间的
        // ref: https://leetcode.com/problems/sort-items-by-groups-respecting-dependencies/discuss/387601/ChineseC++-1203.
        int groupNum = m; // assign those un-grouped items, to their own single group number
        // 得到组与其 items
        Map<Integer, List<Integer>> groupOfItem = new HashMap<>(); // <group, List<item>>
        for (int i = 0; i < n; i++) {
            if (group[i] == -1) {
                group[i] = groupNum;
                groupNum++;
            }
            List<Integer> list = groupOfItem.computeIfAbsent(group[i], (dummy) -> new ArrayList<>());
            list.add(i);
        }
        // get in-degree and relation between groups & items
        int[] groupIndegree = new int[groupNum]; // group 的入度
        Map<Integer, Set<Integer>> groupAdj = new HashMap<>(); // 组间的依赖关系
        int[] itemIndegree = new int[n]; // item 的入度
        Map<Integer, List<Integer>> itemAdj = new HashMap<>(); // 同一个组内的 item 依赖关系
        for (int to = 0; to < n; to++) {

            int toGroup = group[to];
            List<Integer> before = beforeItems.get(to);
            // items in before-list should be put before i
            for (int from : before) {
                int fromGroup = group[from];
                if (fromGroup == toGroup) {
                    // in the same group
                    itemIndegree[to]++;
                    List<Integer> list = itemAdj.computeIfAbsent(from, (dummy) -> new ArrayList<>());
                    list.add(to);
                } else {
                    // 组间关系
                    // 注意一个组内可能多个 item 都有 before 的关系对应到另一个组的 item， 此时不能重复计算
                    if (groupAdj.containsKey(fromGroup) && groupAdj.get(fromGroup).contains(toGroup)) {
                        continue;
                    }
                    groupIndegree[toGroup]++;
                    Set<Integer> set = groupAdj.computeIfAbsent(fromGroup, (dummy) -> new HashSet<>());
                    set.add(toGroup);
                }
            }
        }
        // 接下去就是要用 topological sorting 查看组间与组内是否都能满足关系
        // 1. 组间
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < groupNum; i++) {
            if (groupIndegree[i] == 0) {
                queue.offer(i);
            }
        }
        List<Integer> groupResult = new ArrayList<>();
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            groupResult.add(curr);
            if (!groupAdj.containsKey(curr)) {
                continue;
            }
            for (int next : groupAdj.get(curr)) {
                groupIndegree[next]--;
                if (groupIndegree[next] == 0) {
                    queue.offer(next);
                }
            }
        }
        if (groupResult.size() != groupNum) {
            return new int[0];
        }
        // 2. 组内
        // 上一步查看中得到的就是组间的拓扑排序结果， 现在要按照得到的排序结果来排序每个组内的 item
        int[] itemResult = new int[n];
        int index = 0;
        for (int currGroup : groupResult) {
            // 可能没有这个组号， e.g. input:
            // 5
            // 5
            // [2,0,-1,3,0]
            // [[2,1,3],[2,4],[],[],[]]
            if (!groupOfItem.containsKey(currGroup)) {
                continue;
            }
            List<Integer> items = groupOfItem.get(currGroup);
            queue = new LinkedList<>();
            for (int item : items) {
                if (itemIndegree[item] == 0) {
                    queue.offer(item);
                }
            }
            int itemCount = 0;
            while (!queue.isEmpty()) {
                itemCount++;
                int curr = queue.poll();
                itemResult[index++] = curr;
                if (!itemAdj.containsKey(curr)) {
                    continue;
                }
                for (int next : itemAdj.get(curr)) {
                    itemIndegree[next]--;
                    if (itemIndegree[next] == 0) {
                        queue.offer(next);
                    }
                }
            }
            if (itemCount != items.size()) {
                return new int[0];
            }
        }
        return itemResult;
    }
}

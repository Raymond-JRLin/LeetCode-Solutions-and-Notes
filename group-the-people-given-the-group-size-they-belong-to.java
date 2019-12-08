// 1282. Group the People Given the Group Size They Belong To
//
//     User Accepted: 2545
//     User Tried: 2592
//     Total Accepted: 2571
//     Total Submissions: 3027
//     Difficulty: Medium
//
// There are n people whose IDs go from 0 to n - 1 and each person belongs exactly to one group. Given the array groupSizes of length n telling the group size each person belongs to, return the groups there are and the people's IDs each group includes.
//
// You can return any solution in any order and the same applies for IDs. Also, it is guaranteed that there exists at least one solution.
//
//
//
// Example 1:
//
// Input: groupSizes = [3,3,3,3,3,1,3]
// Output: [[5],[0,1,2],[3,4,6]]
// Explanation:
// Other possible solutions are [[2,1,6],[5],[0,4,3]] and [[5],[0,6,2],[4,3,1]].
//
// Example 2:
//
// Input: groupSizes = [2,1,3,3,3,2]
// Output: [[1],[0,5],[2,3,4]]
//
//
//
// Constraints:
//
//     groupSizes.length == n
//     1 <= n <= 500
//     1 <= groupSizes[i] <= n
//


class Solution {
    public List<List<Integer>> groupThePeople(int[] groupSizes) {

        // return mytry(groupSizes);

        return method2(groupSizes);
    }

    private List<List<Integer>> method2(int[] sizes) {
        // 相同的逻辑， 更加简单一点， 就是 contest 中我也想在一边 map 的时候， 一边加到结果里面
        int n = sizes.length;
        List<List<Integer>> result = new ArrayList<>();
        Map<Integer, List<Integer>> map = new HashMap<>(); // <size, index>
        for (int i = 0; i < n; i++) {
            int size = sizes[i];
            List<Integer> list = map.computeIfAbsent(size, dummy -> (new ArrayList<>()));
            list.add(i);
            if (list.size() == size) {
                result.add(list);
                map.remove(size);
            }
        }
        return result;
    }

    private List<List<Integer>> mytry(int[] sizes) {
        int n = sizes.length;
        Map<Integer, List<Integer>> map = new HashMap<>(); // <size, index>
        for (int i = 0; i < n; i++) {
            int size = sizes[i];
            List<Integer> list = map.computeIfAbsent(size, dummy -> (new ArrayList<>()));
            list.add(i);
        }
        List<List<Integer>> result = new ArrayList<>();
        for (int size : map.keySet()) {
            List<Integer> list = map.get(size);
            List<Integer> group = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                System.out.println(list.get(i));
                if (i % size == 0 && !group.isEmpty()) {
                    result.add(group);
                    group = new ArrayList<>();
                }
                group.add(list.get(i));
            }
            result.add(group);
        }
        return result;
    }
}

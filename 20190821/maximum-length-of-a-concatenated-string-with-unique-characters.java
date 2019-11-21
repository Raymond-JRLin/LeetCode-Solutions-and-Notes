// 1239. Maximum Length of a Concatenated String with Unique Characters
// DescriptionHintsSubmissionsDiscussSolution
//
// Given an array of strings arr. String s is a concatenation of a sub-sequence of arr which have unique characters.
//
// Return the maximum possible length of s.
//
//
//
// Example 1:
//
// Input: arr = ["un","iq","ue"]
// Output: 4
// Explanation: All possible concatenations are "","un","iq","ue","uniq" and "ique".
// Maximum length is 4.
//
// Example 2:
//
// Input: arr = ["cha","r","act","ers"]
// Output: 6
// Explanation: Possible solutions are "chaers" and "acters".
//
// Example 3:
//
// Input: arr = ["abcdefghijklmnopqrstuvwxyz"]
// Output: 26
//
//
//
// Constraints:
//
//     1 <= arr.length <= 16
//     1 <= arr[i].length <= 26
//     arr[i] contains only lower case English letters.
//
// Seen this question in a real interview before?
//
//     Difficulty:Medium


class Solution {
    public int maxLength(List<String> arr) {
        if (arr == null || arr.size() == 0) {
            return 0;
        }

        return mytry(arr);
    }

    private int mytry(List<String> list) {
        int n = list.size();
        result = 0;
        Set<Integer> exclude = new HashSet<>(); // 有些 word 自身有重复， 那么就无论如何都不能用了
        dfs(list, 0, new HashSet<>(), 0, exclude);
        return result;
    }
    private int result;
    private void dfs(List<String> list, int index, Set<Character> visited, int prev, Set<Integer> exclude) {
        if (index == list.size()) {
            return;
        }
        for (int i = index; i < list.size(); i++) {
            if (exclude.contains(i)) {
                continue;
            }
            String curr = list.get(i);
            // 查自身是否有重复
            boolean singleWord = true;
            Set<Character> set = new HashSet<>();
            // 查是否能和前面连起来
            boolean canCatenate = true;
            for (char c : curr.toCharArray()) {
                // 如果和前面有重复， 继续查是否自身有重复
                if (visited.contains(c)) {
                    canCatenate = false;
                }
                // 自身有重复
                if (set.contains(c)) {
                    singleWord = false;
                    exclude.add(i);
                    break;
                }
                set.add(c);
            }
            if (!singleWord) {
                continue;
            }
            // 自身若无重复， 可以用自身单个 word
            result = Math.max(result, curr.length());
            if (!canCatenate) {
                continue;
            }
            result = Math.max(result, prev + curr.length());
            visited.addAll(set);
            dfs(list, i + 1, visited, prev + curr.length(), exclude);
            visited.removeAll(set);
        }

    }
}

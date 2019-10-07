// 294. Flip Game II
// DescriptionHintsSubmissionsDiscussSolution
// You are playing the following Flip Game with your friend: Given a string that contains only these two characters: + and -, you and your friend take turns to flip two consecutive "++" into "--". The game ends when a person can no longer make a move and therefore the other person will be the winner.
//
// Write a function to determine if the starting player can guarantee a win.
//
// Example:
//
// Input: s = "++++"
// Output: true
// Explanation: The starting player can guarantee a win by flipping the middle "++" to become "+--+".
// Follow up:
// Derive your algorithm's runtime complexity.
//
// Seen this question in a real interview before?
// Difficulty:Medium


class Solution {
    public boolean canWin(String s) {
        // 判断先手是否能赢

        if (s == null || s.length() < 2) {
            return false;
        }

        return method1(s);
    }

    private boolean method1(String s) {
        Map<String, Boolean> map = new HashMap<>();
        return dfs(s, map);
    }
    private boolean dfs(String s, Map<String, Boolean> map) {
        if (s == null || s.length() < 2) {
            return false;
        }
        if (map.containsKey(s)) {
            return map.get(s);
        }
        for (int i = 0; i < s.length() - 1; i++) {
            // 查看所有可以走的下一步
            if (s.substring(i, i + 2).equals("++")) {
                String curr = s.substring(0, i) + "--" + s.substring(i + 2);
                // 下一步是对手， 如果对手无法完成， 则己方赢
                if (!dfs(curr, map)) {
                    map.put(s, true);
                    return true;
                }
            }

        }
        map.put(s, false);
        return false;
    }
}

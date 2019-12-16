// 301. Remove Invalid Parentheses
// DescriptionHintsSubmissionsDiscussSolution
//
// Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.
//
// Note: The input string may contain letters other than the parentheses ( and ).
//
// Example 1:
//
// Input: "()())()"
// Output: ["()()()", "(())()"]
//
// Example 2:
//
// Input: "(a)())()"
// Output: ["(a)()()", "(a())()"]
//
// Example 3:
//
// Input: ")("
// Output: [""]
//
// Seen this question in a real interview before?
//
//     Difficulty:Hard


class Solution {
    public List<String> removeInvalidParentheses(String s) {
        if (s == null) {
            return Collections.emptyList();
        }

        return method1(s);
    }

    private List<String> method1(String s) {
        // 暴力解就是移除每一个试试看， DFS 得到所有的结果， 然后看是否 valid
        // 这道题问的是【最少的】移除方法来使得 string 是 valid parentheses
        // 如果找最少， 那么就可以使用 BFS， 一旦 valid， 那么就是 remove minimal 的情况， 也就不再往下继续了
        // 注意还有 letters
        int n = s.length();
        List<String> result = new ArrayList<>();
        Queue<String> queue = new LinkedList<>();
        Set<String> set = new HashSet<>();
        queue.offer(s);
        set.add(s);
        boolean found = false;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String curr = queue.poll();
                if (isValid(curr)) {
                    result.add(curr);
                    found = true;
                }
                // 一旦找到了， 剩下的都不再往下走了
                // 不然 BFS 会一直做下去， 直到把所有的可能都走完
                if (found) {
                    continue;
                }
                for (int j = 0; j < curr.length(); j++) {
                    if (!isParenthesis(curr.charAt(j))) {
                        continue;
                    }
                    // remove current parenthesis
                    String next = curr.substring(0, j) + curr.substring(j + 1);
                    if (set.contains(next)) {
                        continue;
                    }
                    queue.offer(next);
                    set.add(next);
                }
            }
        }
        return result;
    }
    private boolean isValid(String s) {
        int count = 0;
        for (char c : s.toCharArray()) {
            if (!isParenthesis(c)) {
                continue;
            }
            count += c == '(' ? 1 : -1;
            if (count < 0) {
                return false;
            }
        }
        return count == 0;
    }
    private boolean isParenthesis(char c) {
        return c == '(' || c == ')';
    }
}

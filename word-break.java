// 139. Word Break
// DescriptionHintsSubmissionsDiscussSolution
// Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s can be segmented into a space-separated sequence of one or more dictionary words.
//
// Note:
//
// The same word in the dictionary may be reused multiple times in the segmentation.
// You may assume the dictionary does not contain duplicate words.
// Example 1:
//
// Input: s = "leetcode", wordDict = ["leet", "code"]
// Output: true
// Explanation: Return true because "leetcode" can be segmented as "leet code".
// Example 2:
//
// Input: s = "applepenapple", wordDict = ["apple", "pen"]
// Output: true
// Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
//              Note that you are allowed to reuse a dictionary word.
// Example 3:
//
// Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
// Output: false


class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        if (s == null || s.length() == 0) {
            return true;
        }
        if (wordDict == null || wordDict.size() == 0) {
            return false;
        }

        // return mytry(s, wordDict);

        // return method2(s, wordDict);

        // return method3(s, wordDict);

        return method4(s, wordDict);
    }

    private boolean method4(String s, List<String> dict) {
        // BFS: offer every reachable (start) index into queue, keep going onwards until we reach n or failed
        int n = s.length();
        Set<String> set = new HashSet<>();
        for (String str : dict) {
            set.add(str);
        }

        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0); // start point
        while (!queue.isEmpty()) {
            int start = queue.poll();
            if (visited[start]) {
                continue;
            }
            for (int end = start + 1; end <= n; end++) {
                if (set.contains(s.substring(start, end))) {
                    // this end can be reachable
                    if (end == n) {
                        return true;
                    }
                    queue.offer(end);
                }
            }
            visited[start] = true;
        }
        return false;
    }

    private boolean method3(String s, List<String> dict) {
        // recursion DP
        int n = s.length();
        Set<String> set = new HashSet<>();
        for (String str : dict) {
            set.add(str);
        }
        // use boolean[] we cannot tell 3 different scenarios
        int[] memo = new int[n]; // 0: not assgined yet; 1: reachable; -1: cannot partition
        return recursion(s, set, memo, 0) == 1 ? true : false;
    }
    private int recursion(String s, Set<String> set, int[] memo, int start) {
        if (start == s.length()) {
            return 1;
        }
        if (memo[start] != 0) {
            // already visited this start point whatever it's reachable or not, if we use a boolean[], then we don't know false means cannot partition or not assigned yet
            return memo[start];
        }
        for (int end = start + 1; end <= s.length(); end++) {
            if (set.contains(s.substring(start, end))) {
                if (recursion(s, set, memo, end) == 1) {
                    return memo[start] = 1;
                }
            }
        }
        return memo[start] = -1;
    }

    private boolean method2(String s, List<String> dict) {
        Set<String> set = new HashSet<>();
        for (String str : dict) {
            set.add(str);
        }
        // iteration DP
        int n = s.length();
        // definition: f[i] = whether or not this string end of i can be reaching
        boolean[] f = new boolean[n + 1];
        // initialization
        f[0] = true;
        // DP
        for (int i = 1; i < n + 1; i++) {
            for (int j = 0; j < i; j++) {
                if (f[j] && set.contains(s.substring(j, i))) {
                    // similar to jump from j to i, string with end index of j can be reaching, and we have a string of s[j, i]
                    f[i] = true;
                    break; // no need to go further
                }
            }
        }
        // result
        return f[n];
    }

    private boolean mytry(String s, List<String> dict) {
        Set<Integer> set = new HashSet<>();
        return dfs(s, dict, 0, set);
    }
    private boolean dfs(String s, List<String> dict, int index, Set<Integer> set) {
        if (index == s.length()) {
            return true;
        }
        // 用 set 还记录哪些 starting index 是走不通的， 剪枝
        if (set.contains(index)) {
            return false;
        }
        for (int i = index + 1; i <= s.length(); i++) {
            String str = s.substring(index, i);
            if (dict.contains(str)) {
                if (dfs(s, dict, i, set)) {
                    // i 是 end index， 所以向下传的时候不能 + 1 了
                    return true;
                } else {
                    set.add(i);
                }
            }
        }
        set.add(index);
        return false;
    }
}

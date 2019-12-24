// 1055. Shortest Way to Form String
// DescriptionHintsSubmissionsDiscussSolution
//
// From any string, we can form a subsequence of that string by deleting some number of characters (possibly no deletions).
//
// Given two strings source and target, return the minimum number of subsequences of source such that their concatenation equals target. If the task is impossible, return -1.
//
//
//
// Example 1:
//
// Input: source = "abc", target = "abcbc"
// Output: 2
// Explanation: The target "abcbc" can be formed by "abc" and "bc", which are subsequences of source "abc".
//
// Example 2:
//
// Input: source = "abc", target = "acdbc"
// Output: -1
// Explanation: The target string cannot be constructed from the subsequences of source string due to the character "d" in target string.
//
// Example 3:
//
// Input: source = "xyz", target = "xzyxz"
// Output: 3
// Explanation: The target string can be constructed as follows "xz" + "y" + "xz".
//
//
//
// Constraints:
//
//     Both the source and target strings consist of only lowercase English letters from "a"-"z".
//     The lengths of source and target string are between 1 and 1000.
//
// Seen this question in a real interview before?
//
//     Difficulty:Medium


class Solution {
    public int shortestWay(String source, String target) {

        // return mytry(source, target);

        // return method1(source, target);

        // return method2(source, target);

        return method3(source, target);
    }

    private int method3(String source, String target) {
        // 如果还要更快， 那么就要用空间来换时间， O(M) time to iterate target 是省不了的， 那么就思考如何 O(1) 得到对应的 index
        // 那就要做一些 pre-processing, 事先记下对应的下一个 index
        // O(N + M) time, O(26 * N) space
        int n = source.length();
        int m = target.length();

        int[][] indices = new int[26][n]; // indices[i][j] 表示的是 char i 在 j 这个位置后面的下一个， 如果为 0， 表示后面没有了
        char[] arr = source.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            indices[arr[i] - 'a'][i] = i + 1;
        }
        for (int i = 0; i < 26; i++) {
            int prev = 0;
            for (int j = n - 1; j >= 0; j--) {
                if (indices[i][j] == 0) {
                    indices[i][j] = prev;
                } else {
                    prev = indices[i][j];
                }
            }
        }
        // source: "abc"
        // a : 1 0 0
        // b : 2 2 0
        // c : 3 3 3
        int result = 0;
        int s = 0;
        int t = 0;
        while (t < m) {
            char c = target.charAt(t);
            // 到头了， 重新来过， 新的 subsequence
            if (s == n) {
                s = 0;
                result++;
            }
            // 首位就是 0， 说明 0 之后没有对应的 char， 即 target 出现了 source 中没有的 char
            if (indices[c - 'a'][0] == 0) {
                return -1;
            }
            // 跳到下一个有的位置， 在 preprocessing 中已经加过 1 了
            s = indices[c - 'a'][s];
            if (s == 0) {
                // 在 s 之后没找到对应的 target 中的 char， s 从头再开始
                // 也就意味着新的 subsequence
                result++;
                t--;
            }
            t++;
        }
        return result + 1; // + 1 是因为中间重新开始了才 ++， 那么之前本身就有 1， 也可以 init 成 1
    }

    private int method2(String source, String target) {
        // O(logN * M) time, O(N) space
        int n = source.length();
        int m = target.length();

        Map<Character, List<Integer>> map = new HashMap<>(); // <char in source, list of <index>>
        char[] arr = source.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            List<Integer> list = map.computeIfAbsent(arr[i], dummy -> (new ArrayList<>()));
            list.add(i);
        }

        int result = 0;
        int s = 0;
        int t = 0;
        while (t < m) {
            char c = target.charAt(t);
            // target 出现了 source 中没有的 char
            if (!map.containsKey(c)) {
                return -1;
            }
            int index = Collections.binarySearch(map.get(c), s);
            if (index < 0) {
                index = - index - 1;
            }
            if (index == map.get(c).size()) {
                // 在 s 之后没找到对应的 target 中的 char， s 从头再开始
                // 也就意味着新的 subsequence
                s = 0;
                result++;
            } else {
                // 找到了， s 从下一个继续
                s = map.get(c).get(index) + 1;
                t++;
            }
        }
        return result + 1; // + 1 是因为中间重新开始了才 ++， 那么之前本身就有 1， 也可以 init 成 1
    }

    private int method1(String source, String target) {
        // 因为是 subsequence， 所以在查看 target 的时候， 从 source 开始， 能找到就往后走
        // O(N * M) time
        int n = source.length();
        int m = target.length();
        int result = 0;
        int t = 0;
        while (t < m) {
            int k = t;
            for (int i = 0; k < m && i < n; i++) {
                if (source.charAt(i) == target.charAt(k)) {
                    k++;
                }
            }
            // 不能移动， 说明 target 出现了 source 中没有的 char
            if (k == t) {
                return -1;
            }
            t = k;
            result++;
        }
        return result;
    }

    private int mytry(String source, String target) {
        // TLE
        // 先拿到所有 subsequence, 然后类似于 combination sum 或是 coin change
        // 我一开始还想过是不是 DP， 但是这题问的是 subsequence， 用 DP 是不好解的
        // 譬如说 f[i][j] 表示的是 [i, j] 区间内的什么东西， 但是 subsequence 在某个区间内不一定是固定唯一的， 这和 substring 有很大不同， 这也导致了没办法用一个区间来表示 subsequence 及其关系， 因此 DP 不太适用这道题
        int n = source.length();
        int m = target.length();
        Set<String> set = new HashSet<>();
        dfs(source, 0, set, new StringBuilder());

        for (char c : target.toCharArray()) {
            if (!set.contains(String.valueOf(c))) {
                return -1;
            }
        }

        List<String> words = new ArrayList<>(set);
        Collections.sort(words, (o1, o2) -> (Integer.compare(o2.length(), o1.length())));
        // for (String s : words) {
        //     System.out.print(s + ", ");
        // }
        result = m + 1;
        if (dfs(target, 0, words, 0, 0)) {
            return result;
        } else {
            return -1;
        }
    }
    int result;
    private boolean dfs(String target, int pos, List<String> words, int index, int count) {
        if (pos == target.length()) {
            // System.out.print("new count:  " + count);
            result = Math.min(result, count);
            return true;
        }
        if (index == words.size()) {
            // System.out.print("reach last words.size ");
            return false;
        }
        if (count > result) {
            // System.out.print( "count already larger ");
            return false;
        }
        for (int i = 0; i < words.size(); i++) {
            // if (i > index && words.get(i).equals(words.get(index))) {
            //     continue;
            // }
            String word = words.get(i);
            if (pos + word.length() > target.length()) {
                continue;
            }

            if (target.substring(pos, pos + word.length()).equals(word)) {
                // System.out.println("pos = " + pos + ", curr word = " + word + ", test sub = " + target.substring(pos, pos + word.length()));
                if (dfs(target, pos + word.length(), words, 0, count + 1)) {
                    return true;
                }
            }
        }
        return false;
    }
    private void dfs(String s, int index, Set<String> set, StringBuilder sb) {
        if (index == s.length()) {
            return;
        }
        sb.append(s.charAt(index));
        set.add(sb.toString());
        dfs(s, index + 1, set, sb);
        sb.deleteCharAt(sb.length() - 1);

        dfs(s, index + 1, set, sb);
    }
}

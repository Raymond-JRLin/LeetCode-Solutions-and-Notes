// 269. Alien Dictionary
// DescriptionHintsSubmissionsDiscussSolution
//
// There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you. You receive a list of non-empty words from the dictionary, where words are sorted lexicographically by the rules of this new language. Derive the order of letters in this language.
//
// Example 1:
//
// Input:
// [
//   "wrt",
//   "wrf",
//   "er",
//   "ett",
//   "rftt"
// ]
//
// Output: "wertf"
//
// Example 2:
//
// Input:
// [
//   "z",
//   "x"
// ]
//
// Output: "zx"
//
// Example 3:
//
// Input:
// [
//   "z",
//   "x",
//   "z"
// ]
//
// Output: ""
//
// Explanation: The order is invalid, so return "".
//
// Note:
//
//     You may assume all letters are in lowercase.
//     You may assume that if a is a prefix of b, then a must appear before b in the given dictionary.
//     If the order is invalid, return an empty string.
//     There may be multiple valid order of letters, return any one of them is fine.
//
// Seen this question in a real interview before?
//
//     Difficulty:Hard


class Solution {
    public String alienOrder(String[] words) {
        if (words == null || words.length == 0) {
            return "";
        }

        return mytry(words);
    }

    private String mytry(String[] words) {
        Map<Character, Set<Character>> map = new HashMap<>(); // <curr char, set of next char of curr>
        Map<Character, Integer> indegree = new HashMap<>();

        // initializae indegree, set as 0 for each char appeared
        for (String word : words) {
            for (char c : word.toCharArray()) {
                if (!indegree.containsKey(c)) {
                    indegree.put(c, 0);
                }
            }
        }

        for (int k = 0; k < words.length - 1; k++) {
            String first = words[k];
            String second = words[k + 1];
            int i = 0;
            int j = 0;
            while (i < first.length() && j < second.length()) {
                if (first.charAt(i) != second.charAt(j)) {
                    break;
                }
                i++;
                j++;
            }
            if (i == first.length() || j == second.length()) {
                continue;
            }
            char from = first.charAt(i);
            char to = second.charAt(j);
            Set<Character> set = map.computeIfAbsent(from, (dummy) -> new HashSet<>());
            // 如果这个 from -> to 的关系已经存过了就跳过， 否则 indegree 会多
            if (set.contains(to)) {
                continue;
            }
            set.add(to);
            indegree.put(to, indegree.get(to) + 1);
        }

        Queue<Character> queue = new LinkedList<>();
        for (char c : indegree.keySet()) {
            if (indegree.get(c) == 0) {
                queue.offer(c);
            }
        }
        if (queue.isEmpty()) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                char curr = queue.poll();
                sb.append(curr);
                for (char next : map.getOrDefault(curr, new HashSet<>())) {
                    indegree.put(next, indegree.get(next) - 1);
                    if (indegree.get(next) == 0) {
                        queue.offer(next);
                    }
                }
            }
        }
        // 有环， 那么有些 char 根本没进 queue
        if (sb.length() != indegree.size()) {
            return "";
        }
        return sb.toString();
    }
}

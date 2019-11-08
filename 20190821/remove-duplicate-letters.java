// 316. Remove Duplicate Letters
// DescriptionHintsSubmissionsDiscussSolution
//
// Given a string which contains only lowercase letters, remove duplicate letters so that every letter appears once and only once. You must make sure your result is the smallest in lexicographical order among all possible results.
//
// Example 1:
//
// Input: "bcabc"
// Output: "abc"
//
// Example 2:
//
// Input: "cbacdcbc"
// Output: "acdb"
//
// Seen this question in a real interview before?
//
//     Difficulty:Hard


class Solution {
    public String removeDuplicateLetters(String s) {
        if (s == null || s.isEmpty()) {
            return s;
        }

        // return mytry(s);

        return method1(s);
    }

    private String method1(String s) {
        int n = s.length();
        char[] arr = s.toCharArray();
        // count char freq
        int[] freq = new int[26];
        for (char c : arr) {
            freq[c - 'a']++;
        }
        // 现在就是对于每一个 char， 我们都选择要还是不要， 尤其是那些有重复的
        // 比如说， 对当前的char c， 如果它前面的 char prev 比 c 大， 并且 prev 在后面还会出现， 那么这个 prev 不应该放置在前面这个位置
        Stack<Character> stack = new Stack<>();
        Set<Character> set = new HashSet<>();
        for (char c : arr) {
            freq[c - 'a']--;
            if (set.contains(c)) {
                continue;
            }
            while (!stack.isEmpty() && stack.peek() > c && freq[stack.peek() - 'a'] > 0) {
                set.remove(stack.pop());
            }
            stack.push(c);
            set.add(c);
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.reverse().toString();
    }

    private String mytry(String s) {
        // DFS TLE
        int n = s.length();
        Map<Character, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            List<Integer> list = map.computeIfAbsent(s.charAt(i), dummy -> (new ArrayList<>()));
            list.add(i);
        }
        // 我想的是 DFS， 找所有
        List<Character> duplicates = new ArrayList<>(map.keySet());
        // 先把有重复的都 mark 成 #， 然后每个重复的 char 只选一个位置
        char[] array = s.toCharArray();
        for (int i = 0; i < duplicates.size(); i++) {
            char c = duplicates.get(i);
            if (map.get(c).size() == 1) {
                map.remove(c);
            } else {
                for (int index : map.get(c)) {
                    array[index] = '#';
                }
            }
        }
        duplicates = new ArrayList<>(map.keySet());
        dfs(map, duplicates, 0, array);
        return result;
    }
    String result = "";
    private void dfs(Map<Character, List<Integer>> map, List<Character> duplicates, int pos, char[] array) {
        if (pos == duplicates.size()) {
            StringBuilder sb = new StringBuilder();
            for (char c : array) {
                if (c == '#') {
                    continue;
                }
                sb.append(c);
            }
            String s = sb.toString();
            if (result.isEmpty() || s.compareTo(result) < 0) {
                result = new String(s);
            }
            return;
        }
        char charToAdd = duplicates.get(pos);
        for (int index : map.get(charToAdd)) {
            array[index] = charToAdd;
            dfs( map, duplicates, pos + 1, array);
            array[index] = '#';
        }
    }
}

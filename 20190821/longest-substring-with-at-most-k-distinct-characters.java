// 340. Longest Substring with At Most K Distinct Characters
// DescriptionHintsSubmissionsDiscussSolution
//
// Given a string, find the length of the longest substring T that contains at most k distinct characters.
//
// Example 1:
//
// Input: s = "eceba", k = 2
// Output: 3
// Explanation: T is "ece" which its length is 3.
//
// Example 2:
//
// Input: s = "aa", k = 1
// Output: 2
// Explanation: T is "aa" which its length is 2.
//
// Seen this question in a real interview before?
//
//     Difficulty:Hard


class Solution {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        // return mytry(s, k);

        return method2(s, k);
    }

    private int method2(String s, int k) {
        if (k == 0) {
            return 0;
        }
        // potential follow-up on input as stream, then cannot use s.charAt(left)
        // 这里
        Map<Character, Integer> map = new HashMap<>(); // <char, index>, last occurance position
        TreeMap<Integer, Character> treeMap = new TreeMap<>(); // <index, char>, last occurance position
        int left = 0;
        int result = 0;
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            // 当已经有 k 个不同的 char 了， 此时出现了一个新的
            while (map.size() == k && !map.containsKey(c)) {
                // 找到一个 char， 它最后出现的 index 在最左边
                // 比如 "abaccc", 到第一个 c 的时候， 找到的应该是 b 在 index === 1
                // a 已经更新过了， 因为如果我们把 a 下一个当成新 window 的 left， 那么后面还是有 a 的
                int first = treeMap.firstKey();
                char outChar = treeMap.get(first);
                map.remove(outChar);
                treeMap.remove(first);
                left = first + 1; // 更新一下 window 的 left
            }
            // treemap key 存的是最后出现的 index， 先把之前的删掉
            if (map.containsKey(c)) {
                treeMap.remove(map.get(c));
            }
            // 记住的是最后出现的 index
            // 这样 remove 的时候就相当于把 window 内最左边那个 char 的最后出现的 index 的左边 string 全抹掉
            // 才可以保证此时 window 内完全没有最左边那个 char
            map.put(c, right);
            treeMap.put(right, c);
            result = Math.max(result, right + 1 - left);
        }
        return result;
    }

    private int mytry(String s, int k) {
        Map<Character, Integer> map = new HashMap<>(); // <char, freq>
        int left = 0;
        int result = 0;
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            map.put(c, map.getOrDefault(c, 0) + 1);
            while (map.size() > k) {
                map.put(s.charAt(left), map.get(s.charAt(left)) - 1);
                if (map.get(s.charAt(left)) == 0) {
                    map.remove(s.charAt(left));
                }
                left++;
            }
            result = Math.max(result, right + 1 - left);
        }
        return result;
    }
}

// 438. Find All Anagrams in a String
// DescriptionHintsSubmissionsDiscussSolution
// Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.
//
// Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 20,100.
//
// The order of output does not matter.
//
// Example 1:
//
// Input:
// s: "cbaebabacd" p: "abc"
//
// Output:
// [0, 6]
//
// Explanation:
// The substring with start index = 0 is "cba", which is an anagram of "abc".
// The substring with start index = 6 is "bac", which is an anagram of "abc".
// Example 2:
//
// Input:
// s: "abab" p: "ab"
//
// Output:
// [0, 1, 2]
//
// Explanation:
// The substring with start index = 0 is "ab", which is an anagram of "ab".
// The substring with start index = 1 is "ba", which is an anagram of "ab".
// The substring with start index = 2 is "ab", which is an anagram of "ab".



class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        if (s == null || s.length() < p.length()) {
            return Collections.emptyList();
        }

        // return mytry(s, p);

        // return mytry2(s, p);

        return method2(s, p);
    }

    private List<Integer> method2(String s, String p) {
        // sliding window: O(N) time and O(M) space
        // ref: https://leetcode.com/problems/find-all-anagrams-in-a-string/discuss/92007/Sliding-Window-algorithm-template-to-solve-all-the-Leetcode-substring-search-problem.
        // 先记录下 target 的 char 及其 freq
        Map<Character, Integer> map = new HashMap<>();
        for (char c : p.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        // 因为 char 有可能有重复， 所以当所有相同的一个 char 被找到后才能减一个 count
        int count = map.size(); // total number of char (dedupe)

        List<Integer> result = new ArrayList<>();
        int start = 0;
        int end = 0;
        int n = s.length();
        int m = p.length();
        while (end < n) {
            char c = s.charAt(end);
            // System.out.println("now end char is " + c);
            // 如果当前 end 在 anagram 中
            if (map.containsKey(c)) {
                map.put(c, map.get(c) - 1);
                // System.out.println("end char map decrease to " + map.get(c));
                // 如果全找到了同一个 char
                if (map.get(c) == 0) {
                    count--;
                }
            }

            end++; // so we just use end - start to get the distance

            while (count == 0) {
                // System.out.println("count is 0 ");
                char left = s.charAt(start);
                // System.out.println("now start char is " + left);
                // 走过的左边的 char 如果是 target 中的， 要重新加回来
                if (map.containsKey(left)) {
                    map.put(left, map.get(left) + 1);
                    // System.out.println("start char increase to " + map.get(left));
                    if (map.get(left) > 0) {
                        count++;
                    }
                }
                // 找到一个符合长度的 anagram
                if (end - start == m) {
                    // System.out.println("get one: " + start + " - " + end);
                    result.add(start);
                }
                start++;
            }
        }
        return result;
    }

    private List<Integer> mytry2(String s, String p) {
        // use a array or map to record the chars in p: O(max{26, M} * (N - M)) time and O(26) space
        int[] count = new int[26];
        for (char c : p.toCharArray()) {
            count[c - 'a']++;
        }
        List<Integer> result = new ArrayList<>();

        int len = p.length();
        for (int i = 0; i < s.length() - len + 1; i++) {
            int[] nums = Arrays.copyOf(count, 26);
            char[] curr = s.substring(i, i + len).toCharArray();
            boolean isAna = true;
            // 查看每一段是否是 anagram
            for (char c : curr) {
                nums[c - 'a']--;
                if (nums[c - 'a'] < 0) {
                    isAna = false;
                    break;
                }
            }
            for (int j = 0; j < 26; j++) {
                if (nums[j] != 0) {
                    isAna = false;
                    break;
                }
            }
            if (isAna) {
                result.add(i);
            }
        }
        return result;
    }

    private List<Integer> mytry(String s, String p) {
        // do like check anagram every time: TLE
        List<Integer> result = new ArrayList<>();
        char[] arr = p.toCharArray();
        Arrays.sort(arr);
        String target = String.valueOf(arr);
        int len = target.length();
        for (int i = 0; i < s.length() - len + 1; i++) {
            char[] curr = s.substring(i, i + len).toCharArray();
            Arrays.sort(curr);
            String str = String.valueOf(curr);
            if (str.equals(target)) {
                result.add(i);
            }
        }
        return result;
    }
}

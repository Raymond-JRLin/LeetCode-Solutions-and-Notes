// 3. Longest Substring Without Repeating Characters
// DescriptionHintsSubmissionsDiscussSolution
// Given a string, find the length of the longest substring without repeating characters.
//
// Example 1:
//
// Input: "abcabcbb"
// Output: 3
// Explanation: The answer is "abc", with the length of 3.
// Example 2:
//
// Input: "bbbbb"
// Output: 1
// Explanation: The answer is "b", with the length of 1.
// Example 3:
//
// Input: "pwwkew"
// Output: 3
// Explanation: The answer is "wke", with the length of 3.
//              Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
//
//

class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        // 有几点注意：
        // 1. 用 map 的时候， 并不一定要删除之前的， 因为 map 总是会更新， 这时只要选比当前左指针右边的就可以了
        // 2. 总想着 right - left 可以当成是当前区间长度，
        //     2.1 但是如果是在发现重复 char 的 if 里面判断确实是， 但是这样就不会更新到每种情况， 比如走到 n 没有重复
        //     2.2 所以要在判断重复外面更新， 此时就是正常的长度计算， 即 right - left + 1, 这样也保证了每次计算的时候都不是在有重复出现的情况下

        // return method1(s);

        return method2(s);
    }

    private int method2(String s) {
        int n = s.length();
        int result = 1;
        Set<Character> set = new HashSet<>();
        int left = 0, right = 0;
        while (right < n) {
            if (set.contains(s.charAt(right))) {
                // 一直删除左指针， 直到没有右指针重复的 char 为止
                set.remove(s.charAt(left));
                left++;
            } else {
                // 同样地， 在没有重复出现的时候计算长度
                // 能按照指针来计算， 或是看 set 当中有多少个 char
                set.add(s.charAt(right));
                result = Math.max(result, right - left + 1);
                // result = Math.max(result, set.size());
                right++;
            }
        }
        return result;
    }

    private int method1(String s) {
        int n = s.length();
        int result = 1;
        int left = 0;
        int right = 0;
        int len = 0;
        Map<Character, Integer> map = new HashMap<>(); // <char, index>
        while (right < n) {
            if (map.containsKey(s.charAt(right))) {
                // make sure left would go back, since map is not deleting previous result, but update first then calculate the current window's left
                left = Math.max(left, map.get(s.charAt(right)) + 1);
            }
            result = Math.max(result, right - left + 1);
            map.put(s.charAt(right), right);
            right++;

        }
        return result;
    }
}

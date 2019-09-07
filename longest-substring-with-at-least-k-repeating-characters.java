// 395. Longest Substring with At Least K Repeating Characters
// DescriptionHintsSubmissionsDiscussSolution
// Find the length of the longest substring T of a given string (consists of lowercase letters only) such that every character in T appears no less than k times.
//
// Example 1:
//
// Input:
// s = "aaabb", k = 3
//
// Output:
// 3
//
// The longest substring is "aaa", as 'a' is repeated 3 times.
// Example 2:
//
// Input:
// s = "ababbc", k = 2
//
// Output:
// 5
//
// The longest substring is "ababb", as 'a' is repeated 2 times and 'b' is repeated 3 times.
// Seen this question in a real interview before?
// Difficulty:Medium


class Solution {
    public int longestSubstring(String s, int k) {
        if (s == null || s.length() < k) {
            return 0;
        }

        // return mytry(s, k);

        // return method1(s, k);

        return method2(s, k);
    }

    private int method2(String s, int k) {
        // use bit manipulation trick to track # repeating
        int result = 0, start = 0;
        int n = s.length();
        while (start + k - 1 < n) {
            int mask = 0; // 用 mask 的 bit 来表示 char 是否有 k 次, 1 表示不足, 0 表示有 k 次
            int index = start;
            int[] nums = new int[26];
            for (int end = start; end < n; end++) {
                // System.out.println("current start is " + start + ", current char: " + s.charAt(end));
                int curr = s.charAt(end) - 'a';
                nums[curr]++;
                if (nums[curr] < k) {
                    mask |= (1 << curr);
                } else {
                    mask &= ~ (1 << curr);
                }
                // System.out.println("mask is " + mask);
                if (mask == 0) {
                    // System.out.println("found one: " + s.substring(start, end + 1));
                    result = Math.max(result, end - start + 1);
                    index = end;
                }
            }
            start = index + 1;
        }
        return result;
    }

    private int method1(String s, int k) {
        // 难就难在拿到一个 substring 的时候， 怎么去很快的判断是否满足每个 char 都重复 k 次
        // D&C：
        // 1、统计每个字母【只有小写】出现的次数
        // 2、找出不合法的字符【出现了，但是次数不够K】
        // 3、如果没有任何不合法字符，那么返回串的长度
        // 4、如果有不合法的字符，那么将这个串按照本轮不合法的字符位置切开（一个不合法的字符就切一刀），切开后的每一个子串递归执行1，取最长的返回

        return dc(s, k, 0, s.length() - 1);
    }
    private int dc(String s, int k, int start, int end) {
        if (end - start + 1 < k) {
            return 0;
        }
        // count char repeat # of current string
        // 要注意的就是当前 string 是 start - end， 所有 for 的地方取值都是这个范围
        int[] nums = new int[26];
        for (int i = start; i <= end; i++) {
            nums[s.charAt(i) - 'a']++;
        }
        // scan
        for (int i = 0; i < 26; i++) {
            if (nums[i] > 0 && nums[i] < k) {
                // the char unsatisfied requirements
                char curr = (char) (i + 'a');
                for (int j = start; j <= end; j++) {
                    if (curr == s.charAt(j)) {
                        int left_len = dc(s, k, start, j - 1);
                        int right_len = dc(s, k, j + 1, end);
                        return Math.max(left_len, right_len);
                    }
                }
            }
        }
        // substring from start to end is valid
        return end + 1 - start;
    }

    private int mytry(String s, int k) {
        // brute: obviously TLE with long string of same 1 char and k = 1
        int n = s.length();
        int result = 0; // final result may not exist
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                String curr = s.substring(j, i);
                if (isValid(curr, k)) {
                    result = Math.max(result, curr.length());
                }
            }
        }
        return result;
    }
    private boolean isValid(String s, int k) {
        int[] arr = new int[26];
        for (char c : s.toCharArray()) {
            arr[c - 'a']++;
        }
        for (int num : arr) {
            if (num != 0 && num < k) {
                return false;
            }
        }
        return true;
    }
}

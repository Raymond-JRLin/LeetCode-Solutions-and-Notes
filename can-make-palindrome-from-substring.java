// 1177. Can Make Palindrome from Substring
// User Accepted: 795
// User Tried: 1923
// Total Accepted: 809
// Total Submissions: 5059
// Difficulty: Medium
// Given a string s, we make queries on substrings of s.
//
// For each query queries[i] = [left, right, k], we may rearrange the substring s[left], ..., s[right], and then choose up to k of them to replace with any lowercase English letter.
//
// If the substring is possible to be a palindrome string after the operations above, the result of the query is true. Otherwise, the result is false.
//
// Return an array answer[], where answer[i] is the result of the i-th query queries[i].
//
// Note that: Each letter is counted individually for replacement so if for example s[left..right] = "aaa", and k = 2, we can only replace two of the letters.  (Also, note that the initial string s is never modified by any query.)
//
//
//
// Example :
//
// Input: s = "abcda", queries = [[3,3,0],[1,2,0],[0,3,1],[0,3,2],[0,4,1]]
// Output: [true,false,false,true,true]
// Explanation:
// queries[0] : substring = "d", is palidrome.
// queries[1] : substring = "bc", is not palidrome.
// queries[2] : substring = "abcd", is not palidrome after replacing only 1 character.
// queries[3] : substring = "abcd", could be changed to "abba" which is palidrome. Also this can be changed to "baab" first rearrange it "bacd" then replace "cd" with "ab".
// queries[4] : substring = "abcda", could be changed to "abcba" which is palidrome.
//
//
// Constraints:
//
// 1 <= s.length, queries.length <= 10^5
// 0 <= queries[i][0] <= queries[i][1] < s.length
// 0 <= queries[i][2] <= s.length
// s only contains lowercase English letters.


class Solution {
    public List<Boolean> canMakePaliQueries(String s, int[][] queries) {
        if (s == null || s.length() == 0) {
            return new ArrayList<>();
        }

        // return mytry(s, queries);

        return method1(s, queries);
    }

    private List<Boolean> method1(String s, int[][] queries) {
        // 直接算 TLE 了， 所以要做个预处理， 那这个预处理要怎么做， 做到哪个数据上呢？ 第一直觉就是 prefix sum 相关
        // 如果我们考虑记住之前处理过的 query， 其实并没有什么用， 因为不一定会有重复的 query, 同时也很难去记 key 应该是什么
        // 如果考虑， 知道 [0, i] 的不同的 char 的个数， 和 [0, j] 的不同的 char 的个数， 也没有办法可以相减得到 [i, j] 的不同的 char 的个数， 因为不知道他们不同的 char 分别是什么
        // 因此， 思路应该是用一种方法记录下 [0, i] 这一段有多少个不同的 char 以及他们的个数
        // 如果只限 26 个字母， 那么可以分别把他们在 [0, i] 之间的个数记下来
        int n = s.length();
        int[][] count = new int[26][n + 1]; // count[i][j] = [0, j] 有几个 (i + 'a') 的 char
        for (int i = 0; i < 26; i++) {
            for (int j = 1; j < n + 1; j++) {
                count[i][j] = count[i][j - 1] + (((i + 'a') == s.charAt(j - 1)) ? 1 : 0);
            }
        }

        List<Boolean> result = new ArrayList<>();
        for (int[] query : queries) {
            int start = query[0];
            int end = query[1] + 1;
            int k = query[2];
            int diff = 0;
            for (int i = 0; i < 26; i++) {
                diff += (count[i][end] - count[i][start]) % 2; // 注意这里是 prefix sum 的相减， start 这个位置是要包括进去的， 所以实际上减掉的是 [0， start - 1] (include start - 1) 的 sum
            }
            result.add(diff / 2 <= k); // 只需要换掉不同的 char 的一半就可以变成 palindrome， 不管奇偶， 奇数个正好 / 是向下取整
        }
        return result;
    }

    private List<Boolean> mytry(String s, int[][] queries) {
        // TLE
        List<Boolean> result = new ArrayList<>();
        int n = s.length();
        int[][] diff = new int[n][n];
        for (int[] query : queries) {
            int start = query[0];
            int end = query[1];
            int k = query[2];
            String sub = s.substring(start, end + 1);
            int num = getDiffNum(sub);
            if (num <= k) {
                result.add(true);
            } else {
                result.add(false);
            }
        }
        return result;
    }
    private int getDiffNum(String s) {
        Set<Character> set = new HashSet<>();
        for (char c : s.toCharArray()) {
            if (!set.add(c)) {
                set.remove(c);
            }
        }
        if (s.length() % 2 == 0) {
            return set.size() / 2;
        } else {
            return (set.size() - 1) / 2;
        }
    }
}

// 1309. Decrypt String from Alphabet to Integer Mapping
//
//     User Accepted: 3340
//     User Tried: 3447
//     Total Accepted: 3409
//     Total Submissions: 4379
//     Difficulty: Easy
//
// Given a string s formed by digits ('0' - '9') and '#' . We want to map s to English lowercase characters as follows:
//
//     Characters ('a' to 'i') are represented by ('1' to '9') respectively.
//     Characters ('j' to 'z') are represented by ('10#' to '26#') respectively.
//
// Return the string formed after mapping.
//
// It's guaranteed that a unique mapping will always exist.
//
//
//
// Example 1:
//
// Input: s = "10#11#12"
// Output: "jkab"
// Explanation: "j" -> "10#" , "k" -> "11#" , "a" -> "1" , "b" -> "2".
//
// Example 2:
//
// Input: s = "1326#"
// Output: "acz"
//
// Example 3:
//
// Input: s = "25#"
// Output: "y"
//
// Example 4:
//
// Input: s = "12345678910#11#12#13#14#15#16#17#18#19#20#21#22#23#24#25#26#"
// Output: "abcdefghijklmnopqrstuvwxyz"
//
//
//
// Constraints:
//
//     1 <= s.length <= 1000
//     s[i] only contains digits letters ('0'-'9') and '#' letter.
//     s will be valid string such that mapping is always possible.
//


class Solution {
    public String freqAlphabets(String s) {

        // return mytry(s);

        return method2(s);
    }

    private String method2(String s) {
        // 简单一点就是不要 split， 直接按照条件走
        // 类似于 482. License Key Formatting， 如果 split 反而麻烦了
        int n = s.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (i < n - 2 && s.charAt(i + 2) == '#') {
                sb.append((char) (Integer.parseInt(s.substring(i, i + 2)) - 10 + 'j'));
                i += 2;
            } else {
                sb.append((char) (s.charAt(i) - '1' + 'a'));
            }
        }
        return sb.toString();
    }

    private String mytry(String s) {
        String[] arr = s.split("#");
        StringBuilder sb = new StringBuilder();
        // 我写的有点儿复杂， 因为不知道最后一个是带 # 还是不带 # 的, 所以把 split 后的最后一个 string 单独拎出来处理
        for (int j = 0; j < arr.length - 1; j++) {
            String str = arr[j];
            int n = str.length();
            for (int i = 0; i < n - 2; i++) {
                char c = (char) (str.charAt(i) - '1' + 'a');
                sb.append(c);
            }
            sb.append((char) (Integer.parseInt(str.substring(n - 2)) - 10 + 'j'));
        }
        if (s.charAt(s.length() - 1) == '#') {
            String str = arr[arr.length - 1];
            int n = str.length();
            for (int i = 0; i < n - 2; i++) {
                char c = (char) (str.charAt(i) - '1' + 'a');
                sb.append(c);
            }
            sb.append((char) (Integer.parseInt(str.substring(n - 2)) - 10 + 'j'));
        } else {
            String str = arr[arr.length - 1];
            int n = str.length();
            for (int i = 0; i < n; i++) {
                char c = (char) (str.charAt(i) - '1' + 'a');
                sb.append(c);
            }
        }
        return sb.toString();
    }
}

// 422. Valid Word Square
// DescriptionHintsSubmissionsDiscussSolution
//
// Given a sequence of words, check whether it forms a valid word square.
//
// A sequence of words forms a valid word square if the kth row and column read the exact same string, where 0 ≤ k < max(numRows, numColumns).
//
// Note:
//
//     The number of words given is at least 1 and does not exceed 500.
//     Word length will be at least 1 and does not exceed 500.
//     Each word contains only lowercase English alphabet a-z.
//
// Example 1:
//
// Input:
// [
//   "abcd",
//   "bnrt",
//   "crmy",
//   "dtye"
// ]
//
// Output:
// true
//
// Explanation:
// The first row and first column both read "abcd".
// The second row and second column both read "bnrt".
// The third row and third column both read "crmy".
// The fourth row and fourth column both read "dtye".
//
// Therefore, it is a valid word square.
//
// Example 2:
//
// Input:
// [
//   "abcd",
//   "bnrt",
//   "crm",
//   "dt"
// ]
//
// Output:
// true
//
// Explanation:
// The first row and first column both read "abcd".
// The second row and second column both read "bnrt".
// The third row and third column both read "crm".
// The fourth row and fourth column both read "dt".
//
// Therefore, it is a valid word square.
//
// Example 3:
//
// Input:
// [
//   "ball",
//   "area",
//   "read",
//   "lady"
// ]
//
// Output:
// false
//
// Explanation:
// The third row reads "read" while the third column reads "lead".
//
// Therefore, it is NOT a valid word square.
//
// Seen this question in a real interview before?
//
//     Difficulty:Easy


class Solution {
    public boolean validWordSquare(List<String> words) {
        if (words == null || words.size() == 0) {
            return false;
        }

        // return mytry(words);

        return method2(words);
    }

    private boolean method2(List<String> words) {
        // 和 mytry 是相同的， 只不过更加 neat 一点
        // 不匹配的只有 3 种情况: 这一行的 string too long, 往下查的时候下面 string too short, 两个 string chars are not equal
        int n = words.size();
        for (int i = 0; i < n; i++) {
            String row = words.get(i);
            int len = row.length();
            // 1. too long, 这个 string 已经超过了 words.size(), 拿不到下面的 string 了
            if (len > n) {
                return false;
            }
            // 去拿每个 string 的第 i 个 char
            for (int j = 0; j < len; j++) {
                // 2. 下面 string too short， 不够当前的 string row
                if (i >= words.get(j).length()) {
                    return false;
                }
                // 3. chars are not equal
                if (row.charAt(j) != words.get(j).charAt(i)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean mytry(List<String> words) {
        // 按照每一行/列去比较
        int n = words.size();
        for (int i = 0; i < n; i++) {
            String row = words.get(i);
            int len = row.length();
            // 这个 string 已经超过了 words.size(), 拿不到下面的 string 了
            if (len > n) {
                return false;
            }
            StringBuilder sb = new StringBuilder();
            // 去拿每个 string 的第 i 个 char
            for (int j = 0; j < len; j++) {
                if (i >= words.get(j).length()) {
                    return false;
                }
                sb.append(words.get(j).charAt(i));
            }
            // 下面这个我是想假设 string 可以匹配， 但是第 i 行下面的 string 还有 char 在第 i 列
            // 也就是说列 string 的长度超过行 string
            // 但实际上不需要， 因为如果有这种情况， 也就意味着下面行 string 匹配后面的列 string 一定是列 string 会短， 然后不能匹配
            // for (int j = len; j < n; j++) {
            //     if (words.get(j).length() > i) {
            //         return false;
            //     }
            // }
            if (!row.equals(sb.toString())) {
                return false;
            }
        }
        return true;
    }
}

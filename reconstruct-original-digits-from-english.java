// 423. Reconstruct Original Digits from English
// DescriptionHintsSubmissionsDiscussSolution
// Given a non-empty string containing an out-of-order English representation of digits 0-9, output the digits in ascending order.
//
// Note:
// Input contains only lowercase English letters.
// Input is guaranteed to be valid and can be transformed to its original digits. That means invalid inputs such as "abc" or "zerone" are not permitted.
// Input length is less than 50,000.
// Example 1:
// Input: "owoztneoer"
//
// Output: "012"
// Example 2:
// Input: "fviefuro"
//
// Output: "45"
// Seen this question in a real interview before?
// Difficulty:Medium


class Solution {
    public String originalDigits(String s) {
        // 表示数字的单词"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"，我们可以发现有些的单词的字符是独一无二的，比如z，只出现在zero中，还有w，u，x，g这四个单词，分别只出现在two，four，six，eight中，那么这五个数字的个数就可以被确定了，由于含有o的单词有zero，two，four，one，其中前三个都被确定了，那么one的个数也就知道了；由于含有h的单词有eight，three，其中eight个数已知，那么three的个数就知道了；由于含有f的单词有four，five，其中four个数已知，那么five的个数就知道了；由于含有s的单词有six，seven，其中six个数已知，那么seven的个数就知道了；由于含有i的单词有six，eight，five，nine，其中前三个都被确定了，那么nine的个数就知道了
        int[] count = new int[128]; // count haow many times each char occured
        int[] nums = new int[10]; // count final result of digits
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i)]++;
        }
        nums[0] = count['z']; // 'z'ero
        nums[2] = count['w']; // t'w'0
        nums[4] = count['u']; // fo'u'r
        nums[6] = count['x']; // si'x'
        nums[8] = count['g']; // ei'g'ht
        nums[1] = count['o'] - nums[0] - nums[2] - nums[4]; // common char 'o'
        nums[3] = count['h'] - nums[8]; // 3 and 8 share common char 'h'
        nums[5] = count['f'] - nums[4]; // 4 and 5 share common char 'f'
        nums[7] = count['s'] - nums[6]; // 6 and 7 share common char 's'
        nums[9] = count['i'] - nums[5] - nums[6] - nums[8]; // common char 'i
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            while (nums[i] != 0) {
                sb.append(i);
                nums[i]--;
            }
        }
        return sb.toString().trim();
    }
}

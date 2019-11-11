// 443. String Compression
// DescriptionHintsSubmissionsDiscussSolution
//
// Given an array of characters, compress it in-place.
//
// The length after compression must always be smaller than or equal to the original array.
//
// Every element of the array should be a character (not int) of length 1.
//
// After you are done modifying the input array in-place, return the new length of the array.
//
//
// Follow up:
// Could you solve it using only O(1) extra space?
//
//
// Example 1:
//
// Input:
// ["a","a","b","b","c","c","c"]
//
// Output:
// Return 6, and the first 6 characters of the input array should be: ["a","2","b","2","c","3"]
//
// Explanation:
// "aa" is replaced by "a2". "bb" is replaced by "b2". "ccc" is replaced by "c3".
//
//
//
// Example 2:
//
// Input:
// ["a"]
//
// Output:
// Return 1, and the first 1 characters of the input array should be: ["a"]
//
// Explanation:
// Nothing is replaced.
//
//
//
// Example 3:
//
// Input:
// ["a","b","b","b","b","b","b","b","b","b","b","b","b"]
//
// Output:
// Return 4, and the first 4 characters of the input array should be: ["a","b","1","2"].
//
// Explanation:
// Since the character "a" does not repeat, it is not compressed. "bbbbbbbbbbbb" is replaced by "b12".
// Notice each digit has it's own entry in the array.
//
//
//
// Note:
//
//     All characters have an ASCII value in [35, 126].
//     1 <= len(chars) <= 1000.
//
// Seen this question in a real interview before?
//
//     Difficulty:Easy


class Solution {
    public int compress(char[] chars) {
        if (chars == null || chars.length == 0) {
            return 0;
        }

        // return mytry(chars);

        return method2(chars);
    }

    private int method2(char[] chars) {
        // neat
        int left = 0;
        int right = 0;
        int n = chars.length;
        while (right < n) {
            char curr = chars[right];
            int count = 0;
            // 用 while loop 一直扫到不同或结束
            while (right < n && chars[right] == curr) {
                right++;
                count++;
            }
            // 先得放上当前 char
            chars[left++] = curr;
            // 1 个就跳过
            if (count == 1) {
                continue;
            }
            // 放上个数
            for (char c : String.valueOf(count).toCharArray()) {
                chars[left++] = c;
            }
        }
        return left;
    }

    private int mytry(char[] chars) {
        int n = chars.length;
        int left = 0;
        char prev = chars[left];
        int right = 0;
        int count = 0;
        String s = "";
        while (right < n) {
            // System.out.println("left/right char: " + chars[left] + " , " + chars[right] + ", prev = " + prev);
            if (prev == chars[right]) {
                count++;
                right++;
            } else {
                if (count == 1) {
                    chars[left++] = prev;
                    count = 0;
                    prev = chars[right];
                    continue;
                }
                s = "";
                while (count > 0) {
                    s = (count % 10) + s;
                    count /= 10;
                }
                // System.out.println("has " + s + " " + chars[left]);
                chars[left++] = prev;
                for (char c : s.toCharArray()) {
                    chars[left++] = c;
                }
                prev = chars[right];
            }
        }
        if (count == 1) {
            chars[left++] = prev;
        } else {
            s = "";
            while (count > 0) {
                s = (count % 10) + s;
                count /= 10;
            }
            // System.out.println("has " + s + " " + chars[left]);
            chars[left++] = prev;
            for (char c : s.toCharArray()) {
                chars[left++] = c;
            }
        }
        return left;
    }
}

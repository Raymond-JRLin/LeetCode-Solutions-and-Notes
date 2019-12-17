// 68. Text Justification
// DescriptionHintsSubmissionsDiscussSolution
//
// Given an array of words and a width maxWidth, format the text such that each line has exactly maxWidth characters and is fully (left and right) justified.
//
// You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra spaces ' ' when necessary so that each line has exactly maxWidth characters.
//
// Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line do not divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right.
//
// For the last line of text, it should be left justified and no extra space is inserted between words.
//
// Note:
//
//     A word is defined as a character sequence consisting of non-space characters only.
//     Each word's length is guaranteed to be greater than 0 and not exceed maxWidth.
//     The input array words contains at least one word.
//
// Example 1:
//
// Input:
// words = ["This", "is", "an", "example", "of", "text", "justification."]
// maxWidth = 16
// Output:
// [
//    "This    is    an",
//    "example  of text",
//    "justification.  "
// ]
//
// Example 2:
//
// Input:
// words = ["What","must","be","acknowledgment","shall","be"]
// maxWidth = 16
// Output:
// [
//   "What   must   be",
//   "acknowledgment  ",
//   "shall be        "
// ]
// Explanation: Note that the last line is "shall be    " instead of "shall     be",
//              because the last line must be left-justified instead of fully-justified.
//              Note that the second line is also left-justified becase it contains only one word.
//
// Example 3:
//
// Input:
// words = ["Science","is","what","we","understand","well","enough","to","explain",
//          "to","a","computer.","Art","is","everything","else","we","do"]
// maxWidth = 20
// Output:
// [
//   "Science  is  what we",
//   "understand      well",
//   "enough to explain to",
//   "a  computer.  Art is",
//   "everything  else  we",
//   "do                  "
// ]
//
// Seen this question in a real interview before?
//
//     Difficulty:Hard


class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        if (words == null || words.length == 0) {
            return Collections.emptyList();
        }

        return method1(words, maxWidth);
    }

    private List<String> method1(String[] words, int maxWidth) {
        // 虽然是 hard， 想通了也并没有那么难， 只是 string 的操作大多都会很繁
        // straightforward 的做法： 每次找到该放在这一行的 words， 然后去按要求 justify
        // 分成 3 种情况：
        // 1. 只有 1 个 word： 空格全往后放
        // 2. 是最后一行： word 之间只有 1 个空格， 剩余空格全往后放
        // 3. 其他任意 case： 尽可能平分， 多余的优先填在前面的 word 之间 => 采用 / 和 % 的方法
        int n = words.length;
        List<String> result = new ArrayList<>();
        int left = 0;
        while (left < n) {
            int right = getRightIndex(words, left, maxWidth);
            String curr = justify(words, left, right, maxWidth);
            result.add(curr);
            left = right + 1;
        }
        return result;
    }
    private int getRightIndex(String[] words, int left, int maxWidth) {
        int sum = words[left].length();
        while (left + 1 < words.length && sum + words[left + 1].length() + 1 <= maxWidth) {
            // 多 + 1 是因为还要至少填一个空格
            sum += words[left + 1].length() + 1;
            left++;
        }
        return left;
    }
    private String justify(String[] words, int start, int end, int maxWidth) {
        // 1. only 1 word
        if (end - start == 0) {
            String line = getLine(words, start, end);
            int len = maxWidth - line.length();
            return addSpaces(line, len);
        }
        // 2. the last line
        if (end == words.length - 1) {
            String line = getLine(words, start, end);
            int len = maxWidth - line.length();
            return addSpaces(line, len);
        }
        // 3. normal case
        int totalStringLength = getLength(words, start, end); // total length of string (words only) exclusive spaces
        int totalSpaces = maxWidth - totalStringLength; // total spaces need to fill
        // 平均分至少多少个 space
        int baseSpace = totalSpaces / (end + 1 - start - 1); // 注意空格是填到 string 之间， 也就是比 string 的个数少一个
        // 平均分完后， 还剩下的 space
        int residue = totalSpaces % (end + 1 - start - 1); // 还有多的， 先往前面填
        StringBuilder sb = new StringBuilder();
        for (int i = start; i <= end; i++) {
            // 每次填平均分的 baseSpace， 然后如果还有剩， 就先填
            sb.append(addSpaces(words[i], baseSpace)).append(residue > 0 ? " " : "");
            residue--;
        }
        String line = sb.toString().trim();
        int len = maxWidth - line.length();
        return addSpaces(line, len);
    }
    private int getLength(String[] words, int start, int end) {
        int sum = 0;
        for (int i = start; i <= end; i++) {
            sum += words[i].length();
        }
        return sum;
    }
    private String getLine(String[] words, int start, int end) {
        StringBuilder sb = new StringBuilder();
        for (int i = start; i <= end; i++) {
            sb.append(words[i]).append(" ");
        }
        return sb.toString().trim();
    }
    private String addSpaces(String s, int len) {
        while (len > 0) {
            s += " ";
            len--;
        }
        return s;
    }
}

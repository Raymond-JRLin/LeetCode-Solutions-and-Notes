// 819. Most Common Word
// DescriptionHintsSubmissionsDiscussSolution
// Given a paragraph and a list of banned words, return the most frequent word that is not in the list of banned words.  It is guaranteed there is at least one word that isn't banned, and that the answer is unique.
//
// Words in the list of banned words are given in lowercase, and free of punctuation.  Words in the paragraph are not case sensitive.  The answer is in lowercase.
//
//
//
// Example:
//
// Input:
// paragraph = "Bob hit a ball, the hit BALL flew far after it was hit."
// banned = ["hit"]
// Output: "ball"
// Explanation:
// "hit" occurs 3 times, but it is a banned word.
// "ball" occurs twice (and no other word does), so it is the most frequent non-banned word in the paragraph.
// Note that words in the paragraph are not case sensitive,
// that punctuation is ignored (even if adjacent to words, such as "ball,"),
// and that "hit" isn't the answer even though it occurs more because it is banned.
//
//
// Note:
//
// 1 <= paragraph.length <= 1000.
// 0 <= banned.length <= 100.
// 1 <= banned[i].length <= 10.
// The answer is unique, and written in lowercase (even if its occurrences in paragraph may have uppercase symbols, and even if it is a proper noun.)
// paragraph only consists of letters, spaces, or the punctuation symbols !?',;.
// There are no hyphens or hyphenated words.
// Words only consist of letters, never apostrophes or other punctuation symbols.
// Seen this question in a real interview before?
// Difficulty:Easy


class Solution {
    public String mostCommonWord(String paragraph, String[] banned) {

        return mytry(paragraph, banned);
    }

    private String mytry(String paragraph, String[] banned) {
        Set<String> bans = new HashSet<>();
        for (String ban : banned) {
            bans.add(ban);
        }

        StringBuilder sb = new StringBuilder();
        Map<String, Integer> map = new HashMap<>(); // <word, freq>
        for (char c : paragraph.toCharArray()) {
            if (c == ' ') {
                if (sb.length() != 0) {
                    String word = sb.toString();
                    sb = new StringBuilder();
                    if (!bans.contains(word)) {
                        // System.out.println("we met a punctuation: " + c + ", but we got a word: " + word);
                        int freq = map.getOrDefault(word, 0) + 1;
                        map.put(word, freq);
                    }
                }
            } else if (Character.isLetter(c)) {
                sb.append(Character.toLowerCase(c));
            } else {
                if (sb.length() != 0) {

                    String word = sb.toString();

                    sb = new StringBuilder();
                    if (!bans.contains(word)) {
                        // System.out.println("we met a punctuation: " + c + ", but we got a word: " + word);
                        int freq = map.getOrDefault(word, 0) + 1;
                        map.put(word, freq);
                    }
                }
            }
        }
        if (sb.length() != 0) {
            String word = sb.toString();
            sb = new StringBuilder();
            if (!bans.contains(word)) {
                int freq = map.getOrDefault(word, 0) + 1;
                map.put(word, freq);
            }
        }

        String result = "";
        int max = 0;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            int freq = entry.getValue();
            // System.out.println("now we have: " + entry.getKey() + ", and its freq is " + freq);
            if (freq > max) {
                max = freq;
                result = entry.getKey();
            }
        }

        return result;
    }
}

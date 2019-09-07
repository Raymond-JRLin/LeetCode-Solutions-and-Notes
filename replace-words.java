// 648. Replace Words
// DescriptionHintsSubmissionsDiscussSolution
// In English, we have a concept called root, which can be followed by some other words to form another longer word - let's call this word successor. For example, the root an, followed by other, which can form another word another.
//
// Now, given a dictionary consisting of many roots and a sentence. You need to replace all the successor in the sentence with the root forming it. If a successor has many roots can form it, replace it with the root with the shortest length.
//
// You need to output the sentence after the replacement.
//
// Example 1:
//
// Input: dict = ["cat", "bat", "rat"]
// sentence = "the cattle was rattled by the battery"
// Output: "the cat was rat by the bat"
//
//
// Note:
//
// The input will only have lower-case letters.
// 1 <= dict words number <= 1000
// 1 <= sentence words number <= 1000
// 1 <= root length <= 100
// 1 <= sentence words length <= 1000
//
//
// Seen this question in a real interview before?
// Difficulty:Medium


class Solution {
    public String replaceWords(List<String> dict, String sentence) {
        if (dict == null || dict.size() == 0 || sentence == null) {
            return sentence;
        }

        // my try: use Trie
        // return my_try(dict, sentence);

        // or we can use HashSet
        return method2_HashSet(dict, sentence);
    }
    private String my_try(List<String> dict, String sentence) {
        TrieNode root = insert(dict);
        String[] sent = sentence.split(" ");
        for (int j = 0; j < sent.length; j++) {
            String word = sent[j];
            TrieNode head = root;
            String replace = "";
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (!head.map.containsKey(c)) {
                    // no need to replace
                    break;
                }
                replace += String.valueOf(c);
                if (head.map.get(c).isWord) {
                    sent[j] = new String(replace);
                    break;
                }
                head = head.map.get(c);
            }
        }
        String result = "";
        for (String word : sent) {
            result += word + " ";
        }
        return result.trim();
    }
    private TrieNode insert(List<String> dict) {
        TrieNode root = new TrieNode();
        for (String word : dict) {
            TrieNode head = root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (!head.map.containsKey(c)) {
                    TrieNode node = new TrieNode();
                    head.map.put(c, node);
                    head = node;
                } else {
                    head = head.map.get(c);
                }
            }
            head.isWord = true;
        }
        return root;
    }

    // use HashSet to store all dict, and check every substring of words in sentence
    private String method2_HashSet(List<String> dict, String sentence) {
        Set<String> set = new HashSet<>();
        for (String word : dict) {
            set.add(word);
        }
        String result = "";
        String[] sent = sentence.split("\\s+");
        for (String word : sent) {
            String temp = "";
            for (int i = 1; i <= word.length(); i++) {
                temp = word.substring(0, i);
                if (set.contains(temp)) {
                    // maybe we cannot find a shorter replacement
                    break;
                }
            }
            result += temp + " ";
        }
        return result.trim();
    }
}
class TrieNode {
    public Map<Character, TrieNode> map;
    public boolean isWord;
    public TrieNode() {
        map = new HashMap<Character, TrieNode>();
        isWord = false;
    }
}

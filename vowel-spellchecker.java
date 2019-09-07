// 966. Vowel Spellchecker
// DescriptionHintsSubmissionsDiscussSolution
// Given a wordlist, we want to implement a spellchecker that converts a query word into a correct word.
//
// For a given query word, the spell checker handles two categories of spelling mistakes:
//
// Capitalization: If the query matches a word in the wordlist (case-insensitive), then the query word is returned with the same case as the case in the wordlist.
// Example: wordlist = ["yellow"], query = "YellOw": correct = "yellow"
// Example: wordlist = ["Yellow"], query = "yellow": correct = "Yellow"
// Example: wordlist = ["yellow"], query = "yellow": correct = "yellow"
// Vowel Errors: If after replacing the vowels ('a', 'e', 'i', 'o', 'u') of the query word with any vowel individually, it matches a word in the wordlist (case-insensitive), then the query word is returned with the same case as the match in the wordlist.
// Example: wordlist = ["YellOw"], query = "yollow": correct = "YellOw"
// Example: wordlist = ["YellOw"], query = "yeellow": correct = "" (no match)
// Example: wordlist = ["YellOw"], query = "yllw": correct = "" (no match)
// In addition, the spell checker operates under the following precedence rules:
//
// When the query exactly matches a word in the wordlist (case-sensitive), you should return the same word back.
// When the query matches a word up to capitlization, you should return the first such match in the wordlist.
// When the query matches a word up to vowel errors, you should return the first such match in the wordlist.
// If the query has no matches in the wordlist, you should return the empty string.
// Given some queries, return a list of words answer, where answer[i] is the correct word for query = queries[i].
//
//
//
// Example 1:
//
// Input: wordlist = ["KiTe","kite","hare","Hare"], queries = ["kite","Kite","KiTe","Hare","HARE","Hear","hear","keti","keet","keto"]
// Output: ["kite","KiTe","KiTe","Hare","hare","","","KiTe","","KiTe"]
//
//
// Note:
//
// 1 <= wordlist.length <= 5000
// 1 <= queries.length <= 5000
// 1 <= wordlist[i].length <= 7
// 1 <= queries[i].length <= 7
// All strings in wordlist and queries consist only of english letters.
// Seen this question in a real interview before?
// Difficulty:Medium


class Solution {
    public String[] spellchecker(String[] wordlist, String[] queries) {
        if (wordlist == null || wordlist.length == 0 || queries == null || queries.length == 0) {
            return new String[0];
        }

        // return mytry(wordlist, queries);

        return method1(wordlist, queries);
    }

    private String[] method1(String[] wordlist, String[] queries) {
        Map<String, String> lowers = new HashMap<>(); // <lower case, original>
        Map<String, String> vowels = new HashMap<>(); // <word replaced vowels, original>
        Set<String> words = new HashSet<>(Arrays.asList(wordlist)); // original words
        for (String word : wordlist) {
            String lower = word.toLowerCase();
            String vowel = devowel(lower); // case-insensitive
            // use putIfAbsent() to add when it's 1st occurs
            lowers.putIfAbsent(lower, word);
            vowels.putIfAbsent(vowel, word);
        }

        int n = queries.length;
        String[] result = new String[n];
        for (int i = 0; i < n; i++) {
            String curr = queries[i];
            String lower = curr.toLowerCase();
            String vowel = devowel(lower);
            if (words.contains(curr)) {
                // exactly same word
                result[i] = curr;
            } else if (lowers.containsKey(lower)) {
                result[i] = lowers.get(lower);
            } else if (vowels.containsKey(vowel)) {
                result[i] = vowels.get(vowel);
            } else {
                result[i] = "";
            }
        }
        return result;
    }
    private String devowel(String s) {
        String vowerls = "aeiou";
        char[] arr = s.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            if (vowerls.indexOf(arr[i]) >= 0) {
                arr[i] = '#';
            }
        }
        return String.valueOf(arr);
    }

    private String[] mytry(String[] wordlist, String[] queries) {
        // TLE: 每次去判断 wordlist 中替换 vowel 是否能匹配， 太费时
        // 既然想到用 Trie， 可以考虑事先把 wordlist 中每个 string 的 vowel 都换了
        Map<String, List<String>> words = new HashMap<>(); // <lower case, original>
        Set<String> set = new HashSet<>(); // original words
        Set<Character> vowels = new HashSet<>();
        for (char c : "aeiou".toCharArray()) {
            vowels.add(c);
        }
        for (int i = 0; i < wordlist.length; i++) {
            String word = wordlist[i];
            List<String> list = words.getOrDefault(word.toLowerCase(), new ArrayList<>());
            list.add(word);
            words.put(word.toLowerCase(), list);
            set.add(word);
        }

        int n = queries.length;
        String[] result = new String[n];
        for (int i = 0; i < n; i++) {
            String curr = queries[i];
            if (set.contains(curr)) {
                result[i] = curr;
            } else if (words.containsKey(curr.toLowerCase())) {
                result[i] = words.get(curr.toLowerCase()).get(0);
            } else {
                String s = "";
                for (String word : wordlist) {
                    if (convert(curr, word, vowels)) {
                        s = word;
                        break;
                    }
                }
                result[i] = s;
            }
        }
        return result;
    }
    private boolean convert(String s, String word, Set<Character> vowels) {
        if (s.length() != word.length()) {
            return false;
        }
        for (int i = 0; i < s.length(); i++) {
            if (Character.toLowerCase(s.charAt(i)) != Character.toLowerCase(word.charAt(i))
                && (!vowels.contains(Character.toLowerCase(s.charAt(i))) || !vowels.contains(Character.toLowerCase(word.charAt(i))))) {
                return false;
            }
        }
        return true;
    }

    /*
    class TrieNode {
        // Initialize your data structure here.

        // method 1: use array
        // public TrieNode[] children;
        // public boolean isWord;
        // public TrieNode() {
        //     children = new TrieNode[26];
        //     isWord = false;
        // }

        // method 2: HashMap
        public HashMap<Character, TrieNode> map;
        public boolean isWord;
        public TrieNode() {
            map = new HashMap<Character, TrieNode>();
            isWord = false;
        }
    }

    public class Trie {
        private TrieNode root;
        private String vowel = "aeiou";
        public Trie() {
            root = new TrieNode();
        }

        // Inserts a word into the trie.
        public void insert(String word) {
            // method 1: use array
            // TrieNode head = root;
            // for (int i = 0; i < word.length(); i++) {
            //     int pos = word.charAt(i) - 'a';
            //     if (head.children[pos] == null) {
            //         // if current char is null then create a new one
            //         head.children[pos] = new TrieNode();
            //     }
            //     // keep going down with connection
            //     head = head.children[pos];
            // }
            // head.isWord = true;

            // method 2: HashMap
            TrieNode head = root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (vowel.indexOf(c) >= 0) {
                    c = '#';
                }
                if (head.map.containsKey(c)) {
                    head = head.map.get(c);
                } else {
                    TrieNode node = new TrieNode();
                    head.map.put(c, node);
                    head = node;
                }
            }
            head.isWord = true;
        }

        // Returns if the word is in the trie.
        public String search(String word) {
            // method 1: use array
            // TrieNode head = root;
            // for (int i = 0; i < word.length(); i++) {
            //     int pos = word.charAt(i) - 'a';
            //     if (head.children[pos] == null) {
            //         return false;
            //     } else {
            //         // keep searching down
            //         head = head.children[pos];
            //     }
            // }
            // return head.isWord;

            // method 2: HashMap
            TrieNode head = root;
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (vowel.indexOf(c) >= 0) {
                    c = '#';
                }
                if (head.map.containsKey(c)) {
                    head = head.map.get(c);
                    sb.add(word.charAt(i));
                } else {
                    return "";
                }
            }
            return head.isWord ? sb.toString() : "";
        }

        // Returns if there is any word in the trie
        // that starts with the given prefix.
        public boolean startsWith(String prefix) {
            // method 1: use array
            // TrieNode head = root;
            // for (int i = 0; i < prefix.length(); i++) {
            //     int pos = prefix.charAt(i) - 'a';
            //     if (head.children[pos] == null) {
            //         return false;
            //     } else {
            //         head = head.children[pos];
            //     }
            // }
            // // 这里不能判断是否等于null， 因为在 for loop 里其实已经到了 prefix 下一个了， 所以只要 for loop 里没有返回 false， 那就意味着找到了
            // return true;

            // method 2: HashMap
            TrieNode head = root;
            for (int i = 0; i < prefix.length(); i++) {
                char c = prefix.charAt(i);
                if (!head.map.containsKey(c)) {
                    return false;
                } else {
                    head = head.map.get(c);
                }
            }
            return true;
        }
    }
    */
}

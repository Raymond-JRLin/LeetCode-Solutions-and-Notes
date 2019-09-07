// 676. Implement Magic Dictionary
// DescriptionHintsSubmissionsDiscussSolution
// Implement a magic directory with buildDict, and search methods.
//
// For the method buildDict, you'll be given a list of non-repetitive words to build a dictionary.
//
// For the method search, you'll be given a word, and judge whether if you modify exactly one character into another character in this word, the modified word is in the dictionary you just built.
//
// Example 1:
// Input: buildDict(["hello", "leetcode"]), Output: Null
// Input: search("hello"), Output: False
// Input: search("hhllo"), Output: True
// Input: search("hell"), Output: False
// Input: search("leetcoded"), Output: False
// Note:
// You may assume that all the inputs are consist of lowercase letters a-z.
// For contest purpose, the test data is rather small by now. You could think about highly efficient algorithm after the contest.
// Please remember to RESET your class variables declared in class MagicDictionary, as static/class variables are persisted across multiple test cases. Please see here for more details.
// Seen this question in a real interview before?
// Difficulty:Medium


class MagicDictionary {

//     如果用 Set 放进去， 那么得一个个找长度和要找的单词一样长， 才能够正好改变一个字符， 那不如使用 HashMap 把长度相等的单词放在一起

    Map<Integer, List<String>> map;

    /** Initialize your data structure here. */
    public MagicDictionary() {
        map = new HashMap<Integer, List<String>>();
    }

    /** Build a dictionary through a list of words */
    public void buildDict(String[] dict) {
        for (String word : dict) {
            int len = word.length();
            if (!map.containsKey(len)) {
                List<String> list = new ArrayList<>();
                list.add(word);
                map.put(len, list);
            } else {
                List<String> list = map.get(len);
                list.add(word);
                map.put(len, list);
            }
        }
    }

    /** Returns if there is any word in the trie that equals to the given word after modifying exactly one character */
    public boolean search(String word) {
        int n = word.length();
        if (!map.containsKey(n)) {
            return false;
        }
        List<String> list = map.get(n);
        for (String origin : list) {
            if (origin.equals(word)) {
                continue;
            }
            int count = 0;
            int i = 0;
            for (; i < n; i++) {
                if (origin.charAt(i) == word.charAt(i)) {
                    continue;
                }
                if (origin.charAt(i) != word.charAt(i) && count == 1) {
                    break;
                }
                count++;
            }
            if (i == n && count == 1) {
                return true;
            }
        }
        return false;
    }
}

/**
 * Your MagicDictionary object will be instantiated and called as such:
 * MagicDictionary obj = new MagicDictionary();
 * obj.buildDict(dict);
 * boolean param_2 = obj.search(word);
 */

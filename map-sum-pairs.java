// 677. Map Sum Pairs
// DescriptionHintsSubmissionsDiscussSolution
// Implement a MapSum class with insert, and sum methods.
//
// For the method insert, you'll be given a pair of (string, integer). The string represents the key and the integer represents the value. If the key already existed, then the original key-value pair will be overridden to the new one.
//
// For the method sum, you'll be given a string representing the prefix, and you need to return the sum of all the pairs' value whose key starts with the prefix.
//
// Example 1:
// Input: insert("apple", 3), Output: Null
// Input: sum("ap"), Output: 3
// Input: insert("app", 2), Output: Null
// Input: sum("ap"), Output: 5
// Seen this question in a real interview before?
// Difficulty:Medium


class MapSum {

    /** Initialize your data structure here. */
    TrieNode root;
    public MapSum() {
        root = new TrieNode();
    }

    public void insert(String key, int val) {
        TrieNode head = root;
        for (int i = 0; i < key.length(); i++) {
            char c = key.charAt(i);
            if (head.map.containsKey(c)) {
                head = head.map.get(c);
            } else {
                TrieNode curr = new TrieNode();
                head.map.put(c, curr);
                head = curr;
            }
        }
        head.val = val;
        head.isWord = true;
    }

    public int sum(String prefix) {
        TrieNode head = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (head.map.containsKey(c)) {
                head = head.map.get(c);
            } else {
                return 0;
            }
        }
        return getSum(head);
    }

    private int getSum(TrieNode head) {
        int sum = 0;
        for (char c : head.map.keySet()) {
            sum += getSum(head.map.get(c));
        }
        return sum + head.val;
    }
}
class TrieNode {
    public Map<Character, TrieNode> map;
    public int val;
    public boolean isWord;
    public TrieNode() {
        map = new HashMap<Character, TrieNode>();
        val = 0;
        isWord = false;
    }
}

/**
 * Your MapSum object will be instantiated and called as such:
 * MapSum obj = new MapSum();
 * obj.insert(key,val);
 * int param_2 = obj.sum(prefix);
 */

// 1268. Search Suggestions System
//
//     User Accepted: 1720
//     User Tried: 2026
//     Total Accepted: 1765
//     Total Submissions: 4342
//     Difficulty: Medium
//
// Given an array of strings products and a string searchWord. We want to design a system that suggests at most three product names from products after each character of searchWord is typed. Suggested products should have common prefix with the searchWord. If there are more than three products with a common prefix return the three lexicographically minimums products.
//
// Return list of lists of the suggested products after each character of searchWord is typed.
//
//
//
// Example 1:
//
// Input: products = ["mobile","mouse","moneypot","monitor","mousepad"], searchWord = "mouse"
// Output: [
// ["mobile","moneypot","monitor"],
// ["mobile","moneypot","monitor"],
// ["mouse","mousepad"],
// ["mouse","mousepad"],
// ["mouse","mousepad"]
// ]
// Explanation: products sorted lexicographically = ["mobile","moneypot","monitor","mouse","mousepad"]
// After typing m and mo all products match and we show user ["mobile","moneypot","monitor"]
// After typing mou, mous and mouse the system suggests ["mouse","mousepad"]
//
// Example 2:
//
// Input: products = ["havana"], searchWord = "havana"
// Output: [["havana"],["havana"],["havana"],["havana"],["havana"],["havana"]]
//
// Example 3:
//
// Input: products = ["bags","baggage","banner","box","cloths"], searchWord = "bags"
// Output: [["baggage","bags","banner"],["baggage","bags","banner"],["baggage","bags"],["bags"]]
//
// Example 4:
//
// Input: products = ["havana"], searchWord = "tatiana"
// Output: [[],[],[],[],[],[],[]]
//
//
//
// Constraints:
//
//     1 <= products.length <= 1000
//     1 <= Σ products[i].length <= 2 * 10^4
//     All characters of products[i] are lower-case English letters.
//     1 <= searchWord.length <= 1000
//     All characters of searchWord are lower-case English letters.
//


class Solution {
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {

        // return mytry(products, searchWord);

        return method2(products, searchWord);
    }

    private List<List<String>> method2(String[] products, String searchWord) {
        // 只是 concise 一点， 其他无变化
        // 还可以有一些些优化： 比如 PQ 只放前 3 个等
        TrieNode root = buildTrie(products);
        List<List<String>> result = new ArrayList<>();
        for (char c : searchWord.toCharArray()) {
            List<String> list = new ArrayList<>();
            if (root != null) {
                root = root.children[c - 'a'];
            }
            if (root != null) {
                int size = root.strings.size();
                for (int i = 0; i < Math.min(size, 3); i++) {
                    String word = root.strings.poll();
                    list.add(word);
                }
            }
            result.add(list);
        }
        return result;
    }

    private List<List<String>> mytry(String[] products, String searchWord) {
        TrieNode root = buildTrie(products);
        List<List<String>> result = new ArrayList<>();
        for (char c : searchWord.toCharArray()) {
            if (root.children[c - 'a'] == null) {
                break;
            }
            root = root.children[c - 'a'];
            List<String> list = new ArrayList<>();

            int size = root.strings.size();
            for (int i = 0; i < Math.min(size, 3); i++) {
                String word = root.strings.poll();
                list.add(word);
            }

            result.add(list);

        }
        int n  = searchWord.length();
        if (result.size() < n) {
            int size = result.size();
            for (int i = size; i < n; i++) {
                result.add(new ArrayList<>());
            }
        }
        return result;
    }
    private TrieNode buildTrie(String[] products) {
        TrieNode root = new TrieNode();
        for (String product : products) {
            TrieNode curr = root;
            for (char c : product.toCharArray()) {
                if (curr.children[c - 'a'] == null) {
                    curr.children[c - 'a'] = new TrieNode();
                }
                curr = curr.children[c - 'a'];
                curr.strings.add(product);
            }
        }
        return root;
    }

    private class TrieNode {
        TrieNode[] children;
        PriorityQueue<String> strings;

        TrieNode() {
            children = new TrieNode[26];
            strings = new PriorityQueue<>((o1, o2) -> (o1.compareTo(o2)));
        }
    }
}

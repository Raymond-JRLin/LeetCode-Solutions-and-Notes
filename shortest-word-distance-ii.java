// 244. Shortest Word Distance II
// DescriptionHintsSubmissionsDiscussSolution
// Design a class which receives a list of words in the constructor, and implements a method that takes two words word1 and word2 and return the shortest distance between these two words in the list. Your method will be called repeatedly many times with different parameters.
//
// Example:
// Assume that words = ["practice", "makes", "perfect", "coding", "makes"].
//
// Input: word1 = “coding”, word2 = “practice”
// Output: 3
// Input: word1 = "makes", word2 = "coding"
// Output: 1
// Note:
// You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.
//
// Seen this question in a real interview before?
// Difficulty:Medium


class WordDistance {

    // Since we will call shortest() again and again, so we cannot use O(N) method in I to iterate words array multiple times, we should record the value and index
    Map<String, List<Integer>> map; // <string, index list>

    public WordDistance(String[] words) {
        map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            List<Integer> list = map.getOrDefault(words[i], new ArrayList<Integer>());
            list.add(i);
            map.put(words[i], list);
        }
    }

    public int shortest(String word1, String word2) {
        int result = Integer.MAX_VALUE;
        // for (int index1:  map.get(word1)) {
        //     for (int index2 : map.get(word2)) {
        //         result = Math.min(Math.abs(index1 - index2) , result);
        //     }
        // }
        // improvement on searching by 2 pointers:
        List<Integer> list1 = map.get(word1);
        List<Integer> list2 = map.get(word2);
        int i = 0;
        int j = 0;
        while (i < list1.size() && j < list2.size()) {
            int index1 = list1.get(i);
            int index2 = list2.get(j);
            if (index1 < index2) {
                result = Math.min(result, index2 - index1);
                i++; // move i bigger to get closer to index2
            } else {
                result = Math.min(result, index1 - index2);
                j++; // move j bigger to get closer to index1
            }
        }
        return result;
    }
}

/**
 * Your WordDistance object will be instantiated and called as such:
 * WordDistance obj = new WordDistance(words);
 * int param_1 = obj.shortest(word1,word2);
 */

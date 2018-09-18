// 884. Uncommon Words from Two Sentences
// User Accepted: 1910
// User Tried: 1985
// Total Accepted: 1929
// Total Submissions: 3046
// Difficulty: Easy
// We are given two sentences A and B.  (A sentence is a string of space separated words.  Each word consists only of lowercase letters.)
//
// A word is uncommon if it appears exactly once in one of the sentences, and does not appear in the other sentence.
//
// Return a list of all uncommon words.
//
// You may return the list in any order.
//
//
//
// Example 1:
//
// Input: A = "this apple is sweet", B = "this apple is sour"
// Output: ["sweet","sour"]
// Example 2:
//
// Input: A = "apple apple", B = "banana"
// Output: ["banana"]
//
//
// Note:
//
// 0 <= A.length <= 200
// 0 <= B.length <= 200
// A and B both contain only spaces and lowercase letters.


class Solution {
    public String[] uncommonFromSentences(String A, String B) {
        if (A == null || A.length() == 0 || B == null || B.length() == 0 || A.equals(B)) {
            return new String[0];
        }

        return mytry(A, B);
    }

    private String[] mytry(String A, String B) {
        String[] arr1 = A.split(" ");
        String[] arr2 = B.split(" ");
        Map<String, Integer> map = new HashMap<>(); // <word, freq>
        for (String s : arr1) {
            map.put(s, map.getOrDefault(s, 0) + 1);
        }
        for (String s : arr2) {
            map.put(s, map.getOrDefault(s, 0) + 1);
        }

        List<String> list = new ArrayList<>();
        for (String key : map.keySet()) {
            if (map.get(key) == 1) {
                list.add(key);
            }
        }

        // String[] result = new String[list.size()];
        // int i = 0;
        // for (String s : list) {
        //     result[i++] = s;
        // }
        // return result;

        return list.toArray(new String[0]);
    }
}

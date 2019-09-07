// 249. Group Shifted Strings
// DescriptionHintsSubmissionsDiscussSolution
// Given a string, we can "shift" each of its letter to its successive letter, for example: "abc" -> "bcd". We can keep "shifting" which forms the sequence:
//
// "abc" -> "bcd" -> ... -> "xyz"
// Given a list of strings which contains only lowercase alphabets, group all strings that belong to the same shifting sequence.
//
// Example:
//
// Input: ["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"],
// Output:
// [
//   ["abc","bcd","xyz"],
//   ["az","ba"],
//   ["acef"],
//   ["a","z"]
// ]
// Seen this question in a real interview before?
// Difficulty:Medium


class Solution {
    public List<List<String>> groupStrings(String[] strings) {
        if (strings == null || strings.length == 0) {
            return Collections.emptyList();
        }

        // wrong
        // return mytry(strings);

        return method1(strings);
    }

    private List<List<String>> method1(String[] strings) {
        // similar to anagram group, we convert each string to "original" string, and make it as key
        int n = strings.length;
        Map<String, List<String>> map = new HashMap<>(); // <diff, list of strings>
        for (String s : strings) {
            String key = "";
            // we start from index 1, so if the s only has length of 1, then the key is ""
            for (int i = 1; i < s.length(); i++) {
                // count the difference (shift), and use it as the char, which will includ the "length" info
                char c = (char) ((s.charAt(i) - s.charAt(i - 1) + 26) % 26);
                key += c;
            }
            List<String> list = map.getOrDefault(key, new ArrayList<>());
            list.add(s);
            map.put(key, list);
        }

        return new ArrayList<List<String>>(map.values());
    }

    // wrong
    private List<List<String>> mytry(String[] strings) {
        int n = strings.length;
        Map<Integer, List<String>> map = new HashMap<>(); // <diff, list of strings>
        for (String s : strings) {
            if (s.length() == 1) {
                List<String> list = map.getOrDefault(-1, new ArrayList<>());
                list.add(s);
                map.put(-1, list);
            } else {
                int diff = ((s.charAt(1) - s.charAt(0) + 26) % 26 * 12345 + s.length() * 67890) % Integer.MAX_VALUE;
                List<String> list = map.getOrDefault(diff, new ArrayList<>());
                list.add(s);
                map.put(diff, list);
            }
        }

        List<List<String>> result = new ArrayList<>();
        for (int key : map.keySet()) {
            result.add(new ArrayList<>(map.get(key)));
        }

        return result;
    }
}

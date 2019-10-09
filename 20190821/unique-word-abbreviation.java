// 288. Unique Word Abbreviation
// DescriptionHintsSubmissionsDiscussSolution
//
// An abbreviation of a word follows the form <first letter><number><last letter>. Below are some examples of word abbreviations:
//
// a) it                      --> it    (no abbreviation)
//
//      1
//      ↓
// b) d|o|g                   --> d1g
//
//               1    1  1
//      1---5----0----5--8
//      ↓   ↓    ↓    ↓  ↓
// c) i|nternationalizatio|n  --> i18n
//
//               1
//      1---5----0
//      ↓   ↓    ↓
// d) l|ocalizatio|n          --> l10n
//
// Assume you have a dictionary and given a word, find whether its abbreviation is unique in the dictionary. A word's abbreviation is unique if no other word from the dictionary has the same abbreviation.
//
// Example:
//
// Given dictionary = [ "deer", "door", "cake", "card" ]
//
// isUnique("dear") -> false
// isUnique("cart") -> true
// isUnique("cane") -> false
// isUnique("make") -> true
//
// Seen this question in a real interview before?
//
//     Difficulty:Medium



class ValidWordAbbr {

    private Map<String, Set<String>> map;

    // 题目审清楚！ 问的是 convert 之后在原来的 dict 里面是不是 unique 的

    // discuss 总一个解法很巧， 就是不用 set， 而是 Map<String, String>
    // 然后如果 dict 里面有不同的 s 对应到相同的 abbreviation， 那么设为 map.put(key, "");
    // 最后查 map.get(word).equals(word)

    public ValidWordAbbr(String[] dictionary) {
        map = new HashMap<>();
        for (String word : dictionary) {
            String s = convert(word);
            Set<String> set = map.computeIfAbsent(s, (dummy) -> new HashSet<>());
            set.add(word);
        }
    }

    public boolean isUnique(String word) {
        String s = convert(word);
        if (!map.containsKey(s)) {
            return true;
        } else {
            Set<String> set = map.get(s);
            return set.size() == 1 && set.contains(word);
        }
    }

    private String convert(String s) {
        if (s.length() < 3) {
            return s;
        }
        int n = s.length();
        return s.charAt(0) + String.valueOf(n - 2) + s.charAt(n - 1);
    }
}

/**
 * Your ValidWordAbbr object will be instantiated and called as such:
 * ValidWordAbbr obj = new ValidWordAbbr(dictionary);
 * boolean param_1 = obj.isUnique(word);
 */

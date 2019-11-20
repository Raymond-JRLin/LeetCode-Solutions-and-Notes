// 527. Word Abbreviation
// DescriptionHintsSubmissionsDiscussSolution
//
// Given an array of n distinct non-empty strings, you need to generate minimal possible abbreviations for every word following rules below.
//
//     Begin with the first character and then the number of characters abbreviated, which followed by the last character.
//     If there are any conflict, that is more than one words share the same abbreviation, a longer prefix is used instead of only the first character until making the map from word to abbreviation become unique. In other words, a final abbreviation cannot map to more than one original words.
//     If the abbreviation doesn't make the word shorter, then keep it as original.
//
// Example:
//
// Input: ["like", "god", "internal", "me", "internet", "interval", "intension", "face", "intrusion"]
// Output: ["l2e","god","internal","me","i6t","interval","inte4n","f2e","intr4n"]
//
// Note:
//
//     Both n and the length of each word will not exceed 400.
//     The length of each word is greater than 1.
//     The words consist of lowercase English letters only.
//     The return answers should be in the same order as the original array.
//
// Seen this question in a real interview before?
//
//     Difficulty:Hard


class Solution {
    public List<String> wordsAbbreviation(List<String> dict) {
        if (dict == null || dict.size() == 0) {
            return Collections.emptyList();
        }

        // return method1(dict);

        // return method2(dict);

        return method3(dict);
    }

    private List<String> method3(List<String> dict) {
        // build Trie on words with same shortest abbr
        Map<String, List<String>> shortestAbbr = new HashMap<>(); // <shortest abbr, List of words>
        for (String word : dict) {
            String abbr = makeAbbr(word, 1);
            List<String> list = shortestAbbr.computeIfAbsent(abbr, dummy -> (new ArrayList<>()));
            list.add(word);
        }

        Map<String, String> wordToAbbr = new HashMap<>();
        for (String abbr : shortestAbbr.keySet()) {
            if (shortestAbbr.get(abbr).size() == 1) {
                // 没有重复的 abbr
                wordToAbbr.put(shortestAbbr.get(abbr).get(0), abbr);
            } else {
                // abbr 重复
                solveDuplicate(shortestAbbr.get(abbr), wordToAbbr);
            }
        }

        List<String> result = new ArrayList<>();
        for (String word : dict) {
            result.add(wordToAbbr.get(word));
        }
        return result;
    }
    private void solveDuplicate(List<String> words, Map<String, String> wordToAbbr) {
        // 把有重复的 word 建 Trie
        Trie root = buildTrie(words);
        for (String word : words) {
            Trie curr = root;
            int i = 0;
            for (; i < word.length(); i++) {
                char c = word.charAt(i);
                // 到这时候才只有 word 它自己是这样的 prefix
                if (curr.count == 1) {
                    break;
                }
                curr = curr.children.get(c);
            }
            String abbr = makeAbbr(word, i);
            wordToAbbr.put(word, abbr);
        }
    }
    private Trie buildTrie(List<String> words) {
        Trie root = new Trie();
        for (String word : words) {
            Trie curr = root;
            for (char c : word.toCharArray()) {
                if (curr.children.containsKey(c)) {
                    curr = curr.children.get(c);
                } else {
                    Trie next = new Trie();
                    curr.children.put(c, next);
                    curr = next;
                }
                // 把走过 char 的 Trie 都 + 1， 表示有 word 有相同的 prefix
                curr.count++;
            }
        }
        return root;
    }
    private class Trie {
        Map<Character, Trie> children;
        int count; // 有多少个 word share 到当前的 char
        String word;

        Trie() {
            this.children = new HashMap<>();
            this.count = 0;
            this.word = null;
        }
    }

    private List<String> method2(List<String> dict) {
        int n = dict.size();
        Map<String, String> wordToAbbr = new HashMap<>();

        // 把 words 按照长度分， 不同长度的 word 肯定 abbr 不一样
        Map<Integer, List<String>> lenToWord = new HashMap<>();
        for (String s : dict) {
            // 如果长度 <= 3, 那么没办法 abbr 直接放入结果
            if (s.length() <= 3) {
                wordToAbbr.put(s, s);
            } else {
                List<String> list = lenToWord.computeIfAbsent(s.length(), dummy -> (new ArrayList<>()));
                list.add(s);
            }
        }

        // 把每个长度的 words 拿去 abbr
        for (int len : lenToWord.keySet()) {
            Map<String, String> map = getAbbr(lenToWord.get(len), len);
            for (String word : map.keySet()) {
                wordToAbbr.put(word, map.get(word));
            }
        }

        List<String> result = new ArrayList<>();
        for (String s : dict) {
            result.add(wordToAbbr.get(s));
        }
        return result;
    }
    private Map<String, String> getAbbr(List<String> list, int len) {
        Map<String, String> map = new HashMap<>(); // <word, abbr>
        // 从 1 开始做 abbr
        for (int i = 1; i < len; i++) {
            Map<String, String> abbrToWord = new HashMap<>();
            for (String s : list) {
                if (map.containsKey(s)) {
                    continue;
                }
                // 这里调用了下面我写的 makeAbbr， 不然要注意 i 的范围
                // 然后 list 中保持原 string 的不会在这个过程中被加入， 在最后要查一遍 list 中没被 map 存住的， 要存下来
                String abbr = makeAbbr(s, i);
                if (abbrToWord.containsKey(abbr)) {
                    // 重复的 abbr
                    abbrToWord.put(abbr, ""); // 标记为空
                } else {
                    abbrToWord.put(abbr, s);
                }
            }
            // 得到了所有的 s 及其 abbr， 把不重复的放入 map， 重复的得走下一个 i
            for (String abbr : abbrToWord.keySet()) {
                if (abbrToWord.get(abbr).isEmpty()) {
                    continue;
                }
                map.put(abbrToWord.get(abbr), abbr);
            }
        }
        return map;
    }


    private List<String> method1(List<String> dict) {
        // 比较直接的， 每次都把相同的 abbr 进一步压缩， 消除重复的
        int n = dict.size();
        List<String> result = new ArrayList<>();
        int[] numberToPrefix = new int[n];
        for (int i = 0; i < n; i++) {
            numberToPrefix[i] = 1;
            result.add(makeAbbr(dict.get(i), 1));
        }
        for (int i = 0; i < n; i++) {
            while (true) {
                List<Integer> list = new ArrayList<>(); // duplicate abbreviations
                for (int j = i + 1; j < n; j++) {
                    // abbr 相同
                    if (result.get(j).equals(result.get(i))) {
                        list.add(j);
                    }
                }
                // 已经没有相同的了
                if (list.isEmpty()) {
                    break;
                }
                // 记住把 i 这个也放进去
                list.add(i);
                for (int k : list) {
                    numberToPrefix[k]++;
                    // 重新把相同 abbr 的再次做更长 prefix 的 abbr， 注意这里要使用在 dict 中的原 string
                    result.set(k, makeAbbr(dict.get(k), numberToPrefix[k]));
                }
            }
        }
        return result;
    }
    private String makeAbbr(String s, int k) {
        int n = s.length();
        if (k > n - 3) {
            return s;
        }
        return s.substring(0, k) + (n - 2 + 1 - k) + s.substring(n - 1);
    }
}

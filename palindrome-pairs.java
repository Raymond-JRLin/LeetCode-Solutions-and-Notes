// 336. Palindrome Pairs
// DescriptionHintsSubmissionsDiscussSolution
// Given a list of unique words, find all pairs of distinct indices (i, j) in the given list, so that the concatenation of the two words, i.e. words[i] + words[j] is a palindrome.
//
// Example 1:
//
// Input: ["abcd","dcba","lls","s","sssll"]
// Output: [[0,1],[1,0],[3,2],[2,4]]
// Explanation: The palindromes are ["dcbaabcd","abcddcba","slls","llssssll"]
// Example 2:
//
// Input: ["bat","tab","cat"]
// Output: [[0,1],[1,0]]
// Explanation: The palindromes are ["battab","tabbat"]
// Seen this question in a real interview before?
// Difficulty:Hard


class Solution {
    public List<List<Integer>> palindromePairs(String[] words) {
        if (words == null || words.length == 0) {
            return Collections.emptyList();
        }

        // return mytry(words);

        return method1(words);
    }

    private List<List<Integer>> method1(String[] words) {
        // 分析： 在 brute force 中， check 了两两 pair， 然后查是否是 palindrome， 为了查 palindrome 这步是省不掉的， 所以要想办法如何不去查两两 pair， 加快这个过程， 从时间复杂度来看也看到这部分是大头。 同时， for 一遍这些 words 是不可少的， 因为我们要找到所有的可能性， 那么就是能否在 for 一遍的时候能够找到 palindrome 的可能性？ 因此我们就要用额外的空间存下 words 并且能够在 O(1) 的时间内找到， 同时还有它在原 words 中的 index 存入最终结果， 那么 map 就是我们要的。 现在考虑的就是如何找 palindrome， （不知道是否有关联：查 palindrome 有两种， 一个是两边向内， 一种是中心向两边）， 具体的题我记不清了， 但是有一种方法是如果一部分是 palindrome， 那么在它两边加上对称的， 最终的结果也会是 palindrome
        // ref: https://leetcode.com/problems/palindrome-pairs/discuss/79199/150-ms-45-lines-JAVA-solution
        // O(N * k ^ 2)
        int n = words.length;
        Map<String, Integer> map = new HashMap<>(); // <word, index>
        for (int i = 0; i < n; i++) {
            map.put(words[i], i);
        }

        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String curr = words[i];
            for (int end = 0; end <= curr.length(); end++) {
                // we got curr.length() here for edge case: ["a", ""]
                String left = curr.substring(0, end);
                String right = curr.substring(end, curr.length());
                if (isPal(left)) {
                    String rev = new StringBuilder(right).reverse().toString();
                    if (map.containsKey(rev) && map.get(rev) != i) {
                        // got a combined palindrome: reversed-right + left + right
                        List<Integer> list = new ArrayList<>();
                        list.add(map.get(rev));
                        list.add(i);
                        result.add(list);
                    }
                }
                if (isPal(right)) {
                    // since we reach curr.length(), i.e. right = "", skip duplicate answer in edge case like["abcd", "dcab"]
                    // or we can store all results in set, and return a list of this set
                    if (right.isEmpty()) {
                        continue;
                    }
                    String rev = new StringBuilder(left).reverse().toString();
                    if (map.containsKey(rev) && map.get(rev) != i) {
                        // got a combined palindrome: left + right + reversed-left
                        List<Integer> list = new ArrayList<>();
                        list.add(i);
                        list.add(map.get(rev));
                        result.add(list);
                    }
                }
            }
        }
        return result;
    }

    private List<List<Integer>> mytry(String[] words) {
        // brute force: check every pair, O(N ^ 2 * k), where k is the max length of 2 string in words - TLE unexpetedly
        int n = words.length;
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }
                String s = words[i] + words[j]; // concatenated 2 strings
                if (isPal(s)) {
                    List<Integer> list = new ArrayList<>();
                    list.add(i);
                    list.add(j);
                    result.add(list);
                }
            }
        }
        return result;
    }
    private boolean isPal(String s) {
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}

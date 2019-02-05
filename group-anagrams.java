// 49. Group Anagrams
// DescriptionHintsSubmissionsDiscussSolution
// Given an array of strings, group anagrams together.
//
// Example:
//
// Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
// Output:
// [
//   ["ate","eat","tea"],
//   ["nat","tan"],
//   ["bat"]
// ]
// Note:
//
// All inputs will be in lowercase.
// The order of your output does not matter.


class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) {
            return new ArrayList<List<String>>();
        }

        // return mytry(strs);

        return method2(strs);
    }

    private List<List<String>> method2(String[] strs) {
        // hash every string in array
        int[] prime = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103};//最多10609个z
        List<List<String>> result = new ArrayList<>();
        Map<Integer, List<String>> map = new HashMap<>();
        for (String str : strs) {
            int count = 1; // initialization should be 1 cuz we do multiplication
            for (char c : str.toCharArray()) {
                count *= prime[c - 'a'];
            }
            if (map.containsKey(count)) {
                List<String> list = map.get(count);
                list.add(str);
                map.put(count, list);
            } else {
                List<String> list = new ArrayList<>();
                list.add(str);
                map.put(count, list);
            }
        }
        for (Map.Entry<Integer, List<String>> entry : map.entrySet()) {
            result.add(new ArrayList<String>(entry.getValue()));
        }
        return result;
    }

    private List<List<String>> mytry(String[] strs) {
        // O(n * mlogm) time: n is the size of strs, m is the max length of string in the strs array; O(n) space
        // 在做 anagram 的时候， 一种是用 array 来比较每一位， 适合两个 string 比较； 另一种是转成 charArray 然后 sort， 再转回 string 看是否一样， 是否多个 string 比较
        List<List<String>> result = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] array = str.toCharArray();
            Arrays.sort(array);
            String copy = String.valueOf(array);
            if (map.containsKey(copy)) {
                List<String> list = map.get(copy);
                list.add(str);
                map.put(copy, list);
            } else {
                List<String> list = new ArrayList<>();
                list.add(str);
                map.put(copy, list);
            }
        }
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            result.add(new ArrayList<String>(entry.getValue()));
        }
        return result;
    }
}

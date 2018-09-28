// 17. Letter Combinations of a Phone Number
// DescriptionHintsSubmissionsDiscussSolution
// Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.
//
// A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.
//
//
//
// Example:
//
// Input: "23"
// Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
// Note:
//
// Although the above answer is in lexicographical order, your answer could be in any order you want.


class Solution {
    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) {
            return new ArrayList<String>();
        }

        // return mytry(digits);

        // iteration
        return method2(digits);
    }

    private List<String> method2(String digits) {
        // iteration
        // ref: https://discuss.leetcode.com/topic/8465/my-java-solution-with-fifo-queue/1
        int n = digits.length();
        List<String> result = new ArrayList<String>();
        Queue<String> queue = new LinkedList<>();
        String[] mapping = new String[]{"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        queue.add("");
        for (int i = 0; i < n; i++) {
            int digit = digits.charAt(i) - '0';
            while (queue.peek().length() == i) {
                String output = queue.poll();
                for (char c : mapping[digit].toCharArray()) {
                    queue.offer(output + c);
                }
            }
        }
        while (!queue.isEmpty()) {
            result.add(queue.poll());
        }
        return result;
    }

    private List<String> mytry(String digits) {
        // DFS
        List<String> result = new ArrayList<String>();
        Map<Character, String> map = getMapping();
        dfs(result, map, "", digits, 0);
        return result;
    }
    private void dfs(List<String> result, Map<Character, String> map, String output, String digits, int index) {
        if (index == digits.length()) {
            result.add(output);
            return;
        }
        for(char c : map.get(digits.charAt(index)).toCharArray()) {
            output += String.valueOf(c);
            dfs(result, map, output, digits, index + 1);
            output = output.substring(0, output.length() - 1);
        }
    }
    private Map<Character, String> getMapping() {
        Map<Character, String> map = new HashMap<>();
        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");
        return map;
    }
}

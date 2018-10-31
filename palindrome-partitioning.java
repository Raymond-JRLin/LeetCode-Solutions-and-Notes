// 131. Palindrome Partitioning
// DescriptionHintsSubmissionsDiscussSolution
// Given a string s, partition s such that every substring of the partition is a palindrome.
//
// Return all possible palindrome partitioning of s.
//
// Example:
//
// Input: "aab"
// Output:
// [
//   ["aa","b"],
//   ["a","a","b"]
// ]


class Solution {
    public List<List<String>> partition(String s) {
        if (s == null || s.length() == 0) {
            return new ArrayList<List<String>>();
        }

        // return mytry(s);

        return method1(s);
    }

    private List<List<String>> method1(String s) {
        // DP
        int n = s.length();
        List<List<String>>[] result = new List[n + 1];
        result[0] = new ArrayList<>();
        result[0].add(new ArrayList<>());
        boolean[][] f = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            result[i + 1] = new ArrayList<>();
            for (int left = 0; left <= i; left++) {
                if (s.charAt(left) == s.charAt(i) && (i - left <= 1 || f[left + 1][i - 1])) {
                    // System.out.println("now left and i are: " + left + " " + i);
                    f[left][i] = true;
                    String curr = s.substring(left, i + 1);
                    // System.out.println("current string is: " + curr);
                    for (List<String> l : result[left]) {
                        List<String> list = new ArrayList<>(l);
                        // System.out.print(left + " has: ");
                        // print(list);
                        list.add(curr);
                        // System.out.print("after adding: " + curr + ", then list has: ");
                        // print(list);
                        result[i + 1].add(list);
                    }
                }
            }
        }
        return result[n];
    }
    private void print(List<String> list) {
        for (String s : list) {
            System.out.print(s + ", ");
        }
        System.out.println();
    }

    private List<List<String>> mytry(String s) {
        List<List<String>> result = new ArrayList<>();
        dfs(s, result, new ArrayList<String>(), "", 0);
        return result;
    }
    private void dfs(String s, List<List<String>> result, List<String> list, String curr, int index) {
        if (index == s.length()) {
            result.add(new ArrayList<String>(list));
            return;
        }

        for (int i = index + 1; i <= s.length(); i++) {
            curr = s.substring(index, i);
            if (isValid(curr)) {
                list.add(curr);
                dfs(s, result, list, curr, i);
                list.remove(list.size() - 1);
            }
        }
    }
    private boolean isValid(String s) {
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

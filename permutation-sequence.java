// 60. Permutation Sequence
// DescriptionHintsSubmissionsDiscussSolution
// The set [1,2,3,...,n] contains a total of n! unique permutations.
//
// By listing and labeling all of the permutations in order, we get the following sequence for n = 3:
//
// "123"
// "132"
// "213"
// "231"
// "312"
// "321"
// Given n and k, return the kth permutation sequence.
//
// Note:
//
// Given n will be between 1 and 9 inclusive.
// Given k will be between 1 and n! inclusive.
// Example 1:
//
// Input: n = 3, k = 3
// Output: "213"
// Example 2:
//
// Input: n = 4, k = 9
// Output: "2314"


class Solution {
    public String getPermutation(int n, int k) {

        // return mytry(n, k);

        // return mytry2(n, k);

        return method1(n, k);
    }

    private String method1(int n, int k) {
        // DFS will TLE, so we should find the behind math
        // ref: https://leetcode.com/problems/permutation-sequence/discuss/22507/%22Explain-like-I'm-five%22-Java-Solution-in-O(n)
        List<Integer> list = new ArrayList<>();// add candidates
        int[] factor = new int[n + 1]; // #permutations of n numbers
        factor[0] = 1; // f[i] = i!
        for (int i = 1; i <= n; i++) {
            list.add(i);
            factor[i] = factor[i - 1] * i;
        }

        k--; // change kth -> index
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) { // find n numbers
            int index = k / factor[n - i]; // which number is going do have in this round
            sb.append(list.get(index));
            list.remove(index); // removed this used number
            k -= index * factor[n - i]; // kth (actually index right now) among rest numbers
        }

        return sb.toString();
    }

    private String mytry2(int n, int k) {
        // another DFS: TLE
        List<String> list = new ArrayList<>();
        return dfs2(n, k, list, new boolean[n + 1], "");
    }
    int index = 1;
    private String dfs2(int n, int k, List<String> list, boolean[] visited, String curr) {
        if (curr.length() == n) {
            if (index == k) {
                return curr;
            } else {
                index++;
                return "";
            }
        }

        for (int i = 1; i <= n; i++) {
            if (visited[i]) {
                continue;
            }
            curr += String.valueOf(i);
            visited[i] = true;
            String result = dfs2(n, k, list, visited, curr);
            if (!result.isEmpty()) {
                return result;
            }
            curr = curr.substring(0, curr.length() - 1);
            visited[i] = false;
        }
        return "";
    }

    private String mytry(int n, int k) {
        // brute force: DFS to get kth permutation: TLE
        List<String> list = new ArrayList<>();
        dfs(n, k, list, new boolean[n + 1], "");
        return list.get(k - 1);
    }
    private void dfs(int n, int k, List<String> list, boolean[] visited, String curr) {
        if (curr.length() == n) {
            list.add(curr);
            return;
        }
        if (list.size() == k) {
            return;
        }
        for (int i = 1; i <= n; i++) {
            if (visited[i]) {
                continue;
            }
            if (list.size() == k) {
                return;
            }
            curr += String.valueOf(i);
            visited[i] = true;
            dfs(n, k, list, visited, curr);
            if (list.size() == k) {
                return;
            }
            curr = curr.substring(0, curr.length() - 1);
            visited[i] = false;
        }
    }
}

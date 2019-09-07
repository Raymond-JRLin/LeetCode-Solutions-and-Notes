// 839. Similar String Groups
// DescriptionHintsSubmissionsDiscussSolution
// Two strings X and Y are similar if we can swap two letters (in different positions) of X, so that it equals Y.
//
// For example, "tars" and "rats" are similar (swapping at positions 0 and 2), and "rats" and "arts" are similar, but "star" is not similar to "tars", "rats", or "arts".
//
// Together, these form two connected groups by similarity: {"tars", "rats", "arts"} and {"star"}.  Notice that "tars" and "arts" are in the same group even though they are not similar.  Formally, each group is such that a word is in the group if and only if it is similar to at least one other word in the group.
//
// We are given a list A of strings.  Every string in A is an anagram of every other string in A.  How many groups are there?
//
// Example 1:
//
// Input: ["tars","rats","arts","star"]
// Output: 2
// Note:
//
// A.length <= 2000
// A[i].length <= 1000
// A.length * A[i].length <= 20000
// All words in A consist of lowercase letters only.
// All words in A have the same length and are anagrams of each other.
// The judging time limit has been increased for this question.
// Seen this question in a real interview before?
// Difficulty:Hard


class Solution {
    public int numSimilarGroups(String[] A) {
        // 其实这就是一个搜索的问题， 遍历两个 string 就可以得到这两个是否是 similar 的， 那么 brute force 就是把所有的 string 两两 for 一遍， 但是两个不 similar 的 string 可以和共同的一个 string similar 从而使得他们两也是 similar 的， 也就是说当查找一个 string 的时候， 要从头到尾找到所有的可以在一个 group 内的 string， 因为可以经过中间的 string 使得两个不 similar 的 string 在同一个 group 内， 那么就意味着在查找过程中分叉出去的也要去找到该分叉点的 similar 的 string， 那么可以采用 DFS 的方法

        if (A == null || A.length == 0) {
            return 0;
        }

        // return mytry(A);

        return method2(A);
    }

    private int method2(String[] arr) {
        int n = arr.length;
        boolean[] visited = new boolean[n];
        Queue<String> queue = new LinkedList<>();
        int result = 0;
        for (int i = 0; i < n; i++) {
            if (visited[i]) {
                continue;
            }
            visited[i] = true;
            queue.offer(arr[i]);
            while (!queue.isEmpty()) {
                String curr = queue.poll();
                for (int j = 0; j < n; j++) {
                    if (visited[j]) {
                        continue;
                    }
                    if (!isSimilar(curr, arr[j])) {
                        continue;
                    }
                    visited[j] = true;
                    queue.offer(arr[j]);
                }
            }
            result++;
        }
        return result;
    }

    private int mytry(String[] arr) {
        int n = arr.length;
        int result = 0;
        Set<String> visited = new HashSet<>();
        for (String s : arr) {
            // to find the same group of s
            if (visited.contains(s)) {
                continue;
            }
            visited.add(s);
            dfs(arr, s, visited);
            result++;

        }
        return result;
    }
    private void dfs(String[] arr, String curr, Set<String> visited) {
        // kinda flooding process - 既然做的是 flooding 的工作， 那么同样也可以用 BFS 来解决
        for (String next : arr) {
            if (visited.contains(next)) {
                continue;
            }
            if (!isSimilar(curr, next)) {
                continue;
            }
            visited.add(next);
            dfs(arr, next, visited);
        }
    }
    private boolean isSimilar(String a, String b) {
        int count = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) {
                count++;
            }
            if (count > 2) {
                return false;
            }
        }
        return count == 2;
    }
}

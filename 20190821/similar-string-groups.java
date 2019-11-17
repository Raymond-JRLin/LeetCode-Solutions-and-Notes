// 839. Similar String Groups
// DescriptionHintsSubmissionsDiscussSolution
//
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
//
// Note:
//
//     A.length <= 2000
//     A[i].length <= 1000
//     A.length * A[i].length <= 20000
//     All words in A consist of lowercase letters only.
//     All words in A have the same length and are anagrams of each other.
//     The judging time limit has been increased for this question.
//


class Solution {
    public int numSimilarGroups(String[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }

        return mytry(A);
    }

    private int mytry(String[] strs) {
        // BFS and DFS both word
        // tyr Union Find here
        int n = strs.length;
        UnionFind uf = new UnionFind(n);
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (isSimilar(strs[i], strs[j])) {
                    uf.union(i, j);
                }
            }
        }
        return uf.getSize();
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
        return true;
    }
    private class UnionFind {
        int[] nums;
        int size;

        UnionFind(int n) {
            this.size = n;
            nums = new int[n];
            for (int i = 0; i < n; i++) {
                nums[i] = i;
            }
        }

        int find(int i) {
            while (i != nums[i]) {
                nums[i] = nums[nums[i]];
                i = nums[i];
            }
            return i;
        }

        void union(int a, int b) {
            int rootA = this.find(a);
            int rootB = this.find(b);
            if (rootA != rootB) {
                nums[rootA] = rootB;
                this.size--;
            }
        }

        int getSize() {
            return this.size;
        }
    }
}

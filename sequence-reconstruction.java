// 444. Sequence Reconstruction
// DescriptionHintsSubmissionsDiscussSolution
// Check whether the original sequence org can be uniquely reconstructed from the sequences in seqs. The org sequence is a permutation of the integers from 1 to n, with 1 ≤ n ≤ 104. Reconstruction means building a shortest common supersequence of the sequences in seqs (i.e., a shortest sequence so that all sequences in seqs are subsequences of it). Determine whether there is only one sequence that can be reconstructed from seqs and it is the org sequence.
//
// Example 1:
//
// Input:
// org: [1,2,3], seqs: [[1,2],[1,3]]
//
// Output:
// false
//
// Explanation:
// [1,2,3] is not the only one sequence that can be reconstructed, because [1,3,2] is also a valid sequence that can be reconstructed.
// Example 2:
//
// Input:
// org: [1,2,3], seqs: [[1,2]]
//
// Output:
// false
//
// Explanation:
// The reconstructed sequence can only be [1,2].
// Example 3:
//
// Input:
// org: [1,2,3], seqs: [[1,2],[1,3],[2,3]]
//
// Output:
// true
//
// Explanation:
// The sequences [1,2], [1,3], and [2,3] can uniquely reconstruct the original sequence [1,2,3].
// Example 4:
//
// Input:
// org: [4,1,5,2,6,3], seqs: [[5,2,6,3],[4,1,5,2]]
//
// Output:
// true
// UPDATE (2017/1/8):
// The seqs parameter had been changed to a list of list of strings (instead of a 2d array of strings). Please reload the code definition to get the latest changes.


class Solution {
    public boolean sequenceReconstruction(int[] org, List<List<Integer>> seqs) {
        if (seqs == null || seqs.size() == 0) {
            return false;
        }

        // return mytry(org, seqs);

        return method2(org, seqs);

        // return method3(org, seqs);
    }

    /*
    ** cannot pass:
    ** [1]
    ** [[],[]]

    private boolean method3(int[] org, List<List<Integer>> seqs) {
        // 或者不去建立图之类的关系， 重要的就是按照顺序上， 1-每个只被唯一访问过， 并且 2-先后顺序和 org 的一样
        int n = org.length;
        int[] pos = new int[n + 1]; // record the positino of org
        for (int i = 0; i < n; i++) {
            pos[org[i]] = i;
        }
        boolean[] visited = new boolean[n + 1];
        int match = n - 1; // n 个只需要匹配 n - 1 次
        for (List<Integer> seq : seqs) {
            for (int i = 0; i < seq.size(); i++) {
                int curr = seq.get(i);
                // invalid input
                if (curr <= 0 || curr > n) {
                    return false;
                }
                // omit the first one
                if (i == 0) {
                    continue;
                }
                int prev = seq.get(i - 1);

                // 相对顺序不对
                if (pos[prev] >= pos[curr]) {
                    return false;
                }
                // 没有多个匹配， 并且正好前后差一个
                if (!visited[prev] && pos[prev] + 1 == pos[curr]) {
                    visited[prev] = true;
                    match--;
                }
            }
        }
        return match == 0;
    }
    */

    private boolean method2(int[] org, List<List<Integer>> seqs) {
        int n = org.length;
        Map<Integer, Set<Integer>> map = new HashMap<>();
        Map<Integer, Integer> indegree = new HashMap<>();
        for (List<Integer> seq : seqs) {
            if (seq.size() == 1) {
                if (!map.containsKey(seq.get(0))) {
                    map.put(seq.get(0), new HashSet<>());
                    indegree.put(seq.get(0), 0);
                }
            } else {
                for (int i = 0; i < seq.size() - 1; i++) {
                    int curr = seq.get(i);
                    int next = seq.get(i + 1);
                    if (!map.containsKey(curr)) {
                        map.put(curr, new HashSet<>());
                        indegree.put(curr, 0);
                    }
                    if (!map.containsKey(seq.get(i + 1))) {
                        map.put(next, new HashSet<>());
                        indegree.put(next, 0);
                    }
                    if (map.get(curr).add(next)) {
                        // 重复的就不算了， 所以用个 set 来判断和记录下一个
                        indegree.put(next, indegree.get(next) + 1);
                    }
                }
            }
        }
        // there's extra character that are not shown in org
        if (map.size() != n) {
            return false;
        }
        // BFS
        Queue<Integer> queue = new LinkedList<>();
        for (int key : indegree.keySet()) {
            if (indegree.get(key) == 0) {
                queue.offer(key);
            }
        }
        int pos = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            // 只有唯一一个下一个选项
            if (size > 1) {
                return false;
            }
            int curr = queue.poll();
            // 同时这个选项和给定的 org 的顺序一致
            if (pos >= n || org[pos] != curr) {
                return false;
            }
            for (int next : map.get(curr)) {
                indegree.put(next, indegree.get(next) - 1);
                if (indegree.get(next) == 0) {
                    queue.offer(next);
                }
            }
            pos++;
        }
        // 是否位置完全相同
        return pos == n;
    }

    private boolean mytry(int[] org, List<List<Integer>> seqs) {
        // 很显然是 topological sorting, 但这里还有一点要注意： 就是题目还要求唯一性， 如果 topological 的 indegree 条件满足， 但是有多种组合， 那也不可以。 怎么做到？ -> 在 topological sorting 的过程中查看下一层是不是只有一个元素可以使用
        // 根据好几次 fail 的提交， 增加了很多 valid input 的查看， 不 robust， 看了别人的解法， 用 map 可以避免这样的问题 -> method 2， 全往里加， 如果超过了 org 的长度， 那肯定有多余的 char， 直接返回 false
        // 注意两个问题：
        // 1. indegree++ 的时候， 要是第一次出现这样的顺序才可以加
        // 2. 最后返回的时候要看 pos 是否已经走超过了
        int n = org.length;
        int[] indegree = new int[n + 1]; // 1 - n
        Arrays.fill(indegree, -1);
        // adjacent list
        List<Set<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            adj.add(new HashSet<>());
        }
        for (List<Integer> seq : seqs) {
            if (seq.size() == 1) {
                if (seq.get(0) >= n + 1 || seq.get(0) < 1) {
                    return false;
                }
                if (indegree[seq.get(0)] == -1) {
                    indegree[seq.get(0)] = 0;
                }
            } else {
                for (int i = 0; i < seq.size() - 1; i++) {
                    if (seq.get(i) >= n + 1 || seq.get(i) < 1) {
                        return false;
                    }
                    if (indegree[seq.get(i)] == -1) {
                        indegree[seq.get(i)] = 0;
                    }
                    if (seq.get(i + 1) >= n + 1 || seq.get(i + 1) < 1) {
                        return false;
                    }
                    if (indegree[seq.get(i + 1)] == -1) {
                        indegree[seq.get(i + 1)] = 0;
                    }

                    // System.out.println(seq.get(i + 1) + ": " + indegree[seq.get(i + 1)]);
                    if (adj.get(seq.get(i)).add(seq.get(i + 1))) {
                        // 重复的就不算了， 所以用个 set 来判断和记录下一个
                        indegree[seq.get(i + 1)]++;
                    }
                }
            }
        }
        // BFS
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i < n + 1; i++) {
            // System.out.println(i + ": " + indegree[i]);
            if (indegree[i] == 0) {
                queue.offer(i);
            }
            if (indegree[i] == -1) {
                return false;
            }
        }
        int pos = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            // 有多个下一个选项， 就是会有多个子序列结果了
            if (size > 1) {
                // System.out.println("more than 1 " );
                return false;
            }
            int curr = queue.poll();
            // System.out.println(curr);
            if (pos >= org.length || org[pos] != curr) {
                // System.out.println("pos: " + pos);
                return false;
            }
            for (int next : adj.get(curr)) {
                indegree[next]--;
                if (indegree[next] == 0) {
                    queue.offer(next);
                }
            }
            pos++;
        }
        return pos == n;
    }
}

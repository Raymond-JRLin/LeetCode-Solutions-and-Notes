// 767. Reorganize String
// DescriptionHintsSubmissionsDiscussSolution
//
// Given a string S, check if the letters can be rearranged so that two characters that are adjacent to each other are not the same.
//
// If possible, output any possible result.  If not possible, return the empty string.
//
// Example 1:
//
// Input: S = "aab"
// Output: "aba"
//
// Example 2:
//
// Input: S = "aaab"
// Output: ""
//
// Note:
//
//     S will consist of lowercase letters and have length in range [1, 500].
//
//
// Seen this question in a real interview before?
//
//     Difficulty:Medium


class Solution {
    public String reorganizeString(String S) {
        if (S == null || S.length() == 0) {
            return S;
        }

        // return method1(S);

        return method2(S);
    }

    private String method2(String s) {
        // 另一种 greedy， 先拿最多的先间隔填满， 然后用其他剩下的填到间隙当中
        int n = s.length();
        int[] freq = new int[26];
        int max = 0;
        int mostCharIndex = 0;
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
            if (freq[c - 'a'] > max) {
                max = freq[c - 'a'];
                mostCharIndex = c - 'a';
            }
            // 有个 char 超过了一半， 那么无论如何都不行
            if (max > (n + 1) / 2) {
                return "";
            }
        }
        // 先间隔填满最多的
        char[] result = new char[n];
        int index = 0;
        while (freq[mostCharIndex] > 0) {
            result[index] = (char) (mostCharIndex + 'a');
            index += 2; // jump over
            freq[mostCharIndex]--;
        }
        // 间隔填剩下的 char
        for (int i = 0; i < 26; i++) {
            if (freq[i] == 0) {
                continue;
            }
            // 绕一圈回头开始
            while (freq[i] > 0) {
                if (index >= n) {
                    index = 1;
                }
                result[index] = (char) (i + 'a');
                index += 2;
                freq[i]--;
            }
        }

        return String.valueOf(result);
    }

    private String method1(String s) {
        int n = s.length();
        int[] freq = new int[26];
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
            // 有个 char 超过了一半， 那么无论如何都不行
            if (freq[c - 'a'] > (n + 1) / 2) {
                return "";
            }
        }
        // 这里算是 greedy， 总是拿多的先填满
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        for (int i = 0; i < 26; i++) {
            if (freq[i] == 0) {
                continue;
            }
            pq.offer(new Pair((char) ('a' + i), freq[i]));
        }
        char[] result = new char[n];
        int index = 0;
        while (!pq.isEmpty()) {
            Pair curr = pq.poll();
            // 如果之前没有， 或者前面一个和当前不相等
            if (index == 0 || result[index - 1] != curr.c) {
                result[index++] = curr.c;
                curr.freq = curr.freq - 1;
                if (curr.freq > 0) {
                    pq.offer(curr);
                }
            } else {
                // 不然的话， 取下一个不同的 char
                Pair next = pq.poll();
                result[index++] = next.c;
                next.freq = next.freq - 1;
                if (next.freq > 0) {
                    pq.offer(next);
                }
                // 放回去
                pq.offer(curr);
            }
        }
        if (index != n) {
            return "";
        }
        return String.valueOf(result);
    }
    private class Pair implements Comparable<Pair> {
        char c;
        int freq;

        Pair(char c, int freq) {
            this.c = c;
            this.freq = freq;
        }

        @Override
        public int compareTo(Pair o2) {
            return Integer.compare(o2.freq, this.freq);
        }
    }
}

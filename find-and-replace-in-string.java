// 833. Find And Replace in String
// DescriptionHintsSubmissionsDiscussSolution
// To some string S, we will perform some replacement operations that replace groups of letters with new ones (not necessarily the same size).
//
// Each replacement operation has 3 parameters: a starting index i, a source word x and a target word y.  The rule is that if x starts at position i in the original string S, then we will replace that occurrence of x with y.  If not, we do nothing.
//
// For example, if we have S = "abcd" and we have some replacement operation i = 2, x = "cd", y = "ffff", then because "cd" starts at position 2 in the original string S, we will replace it with "ffff".
//
// Using another example on S = "abcd", if we have both the replacement operation i = 0, x = "ab", y = "eee", as well as another replacement operation i = 2, x = "ec", y = "ffff", this second operation does nothing because in the original string S[2] = 'c', which doesn't match x[0] = 'e'.
//
// All these operations occur simultaneously.  It's guaranteed that there won't be any overlap in replacement: for example, S = "abc", indexes = [0, 1], sources = ["ab","bc"] is not a valid test case.
//
// Example 1:
//
// Input: S = "abcd", indexes = [0,2], sources = ["a","cd"], targets = ["eee","ffff"]
// Output: "eeebffff"
// Explanation: "a" starts at index 0 in S, so it's replaced by "eee".
// "cd" starts at index 2 in S, so it's replaced by "ffff".
// Example 2:
//
// Input: S = "abcd", indexes = [0,2], sources = ["ab","ec"], targets = ["eee","ffff"]
// Output: "eeecd"
// Explanation: "ab" starts at index 0 in S, so it's replaced by "eee".
// "ec" doesn't starts at index 2 in the original S, so we do nothing.
// Notes:
//
// 0 <= indexes.length = sources.length = targets.length <= 100
// 0 < indexes[i] < S.length <= 1000
// All characters in given inputs are lowercase letters.


class Solution {
    public String findReplaceString(String S, int[] indexes, String[] sources, String[] targets) {
        if (S == null || S.length() == 0 || indexes == null || indexes.length == 0) {
            return S;
        }

        return mytry(S, indexes, sources, targets);
    }

    private String mytry(String S, int[] indexes, String[] sources, String[] targets) {
        int n = S.length();
        int m = indexes.length;
        strReplace[] replaces = new strReplace[m];
        for (int i = 0; i < m; i++) {
            replaces[i] = new strReplace(indexes[i], sources[i], targets[i]);
        }
        Arrays.sort(replaces, new Comparator<strReplace>() {
            @Override
            public int compare(strReplace o1, strReplace o2) {
                return Integer.compare(o1.index, o2.index);
            }
        });

        StringBuilder sb = new StringBuilder();
        int start = 0;
        for (int i = 0; i < replaces.length; i++) {
            int index = replaces[i].index;
            String source = replaces[i].source;
            String target = replaces[i].target;
            int len = source.length();
            if (S.substring(index, index + len).equals(source)) {
                sb.append(S.substring(start, index));
                sb.append(target);
                start = index + len;
            }
        }
        if (start < n) {
            sb.append(S.substring(start, n));
        }
        return sb.toString();
    }
    private class strReplace {
        private int index;
        private String source;
        private String target;
        public strReplace(int i, String s, String t) {
            index = i;
            source = s;
            target = t;
        }
    }
}

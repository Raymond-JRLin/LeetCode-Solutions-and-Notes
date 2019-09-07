// 816. Ambiguous Coordinates
// DescriptionHintsSubmissionsDiscussSolution
// We had some 2-dimensional coordinates, like "(1, 3)" or "(2, 0.5)".  Then, we removed all commas, decimal points, and spaces, and ended up with the string S.  Return a list of strings representing all possibilities for what our original coordinates could have been.
//
// Our original representation never had extraneous zeroes, so we never started with numbers like "00", "0.0", "0.00", "1.0", "001", "00.01", or any other number that can be represented with less digits.  Also, a decimal point within a number never occurs without at least one digit occuring before it, so we never started with numbers like ".1".
//
// The final answer list can be returned in any order.  Also note that all coordinates in the final answer have exactly one space between them (occurring after the comma.)
//
// Example 1:
// Input: "(123)"
// Output: ["(1, 23)", "(12, 3)", "(1.2, 3)", "(1, 2.3)"]
// Example 2:
// Input: "(00011)"
// Output:  ["(0.001, 1)", "(0, 0.011)"]
// Explanation:
// 0.0, 00, 0001 or 00.01 are not allowed.
// Example 3:
// Input: "(0123)"
// Output: ["(0, 123)", "(0, 12.3)", "(0, 1.23)", "(0.1, 23)", "(0.1, 2.3)", "(0.12, 3)"]
// Example 4:
// Input: "(100)"
// Output: [(10, 0)]
// Explanation:
// 1.0 is not allowed.
//
//
// Note:
//
// 4 <= S.length <= 12.
// S[0] = "(", S[S.length - 1] = ")", and the other elements in S are digits.
//


class Solution {
    public List<String> ambiguousCoordinates(String S) {
        if (S == null || S.length() == 0) {
            return Collections.emptyList();
        }

        return method1(S);
    }

    private List<String> method1(String s) {
        // divide s to left and right substring, to check every possibility in left and right, then product them together
        int n = s.length();
        List<String> result = new ArrayList<>();
        for (int i = 2; i < n - 1; i++) {
            // input has ()
            List<String> left = check(s.substring(1, i));
            List<String> right = check(s.substring(i, n - 1));
            if (!left.isEmpty() && !right.isEmpty()) {
                for (String l : left) {
                    for (String r : right) {
                        result.add("(" + l + ", " + r + ")");
                    }
                }
            }
        }
        return result;
    }
    private List<String> check(String s) {
        // ref: https://leetcode.com/problems/ambiguous-coordinates/discuss/123851/C++JavaPython-Solution-with-Explanation
        // several scenarios (carefull about leading and trailing 0, X mean other valid digit from 1 - 9, ? means 0 - 9):
        // null: []
        // 0: [0]
        // 0??0: []
        // 0??X: [0.??X]
        // X??0: [XXX0]
        // others like X??X: [X??X, X??.X, X?.?X, X.??X]
        List<String> result = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return result;
        } else if (s.length() == 1) {
            result.add(s);
            return result;
        } else {
            // s.length() > 1
            int n = s.length();
            if (s.charAt(0) == '0' && s.charAt(n - 1) == '0')  {
                return result;
            } else if (s.charAt(0) == '0') {
                result.add(s.substring(0,1) + "." + s.substring(1));
                return result;
            } else if (s.charAt(n - 1) == '0') {
                result.add(s);
                return result;
            } else {
                return addDecimal(s);
            }
        }
    }
    private List<String> addDecimal(String s) {
        List<String> list = new ArrayList<>();
        list.add(s);
        for (int i = 1; i < s.length(); i++) {
            list.add(s.substring(0, i) + "." + s.substring(i));
        }
        return list;
    }
}

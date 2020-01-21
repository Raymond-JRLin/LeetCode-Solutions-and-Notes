// 1324. Print Words Vertically
//
//     User Accepted: 3089
//     User Tried: 3277
//     Total Accepted: 3155
//     Total Submissions: 5224
//     Difficulty: Medium
//
// Given a string s. Return all the words vertically in the same order in which they appear in s.
// Words are returned as a list of strings, complete with spaces when is necessary. (Trailing spaces are not allowed).
// Each word would be put on only one column and that in one column there will be only one word.
//
//
//
// Example 1:
//
// Input: s = "HOW ARE YOU"
// Output: ["HAY","ORO","WEU"]
// Explanation: Each word is printed vertically.
//  "HAY"
//  "ORO"
//  "WEU"
//
// Example 2:
//
// Input: s = "TO BE OR NOT TO BE"
// Output: ["TBONTB","OEROOE","   T"]
// Explanation: Trailing spaces is not allowed.
// "TBONTB"
// "OEROOE"
// "   T"
//
// Example 3:
//
// Input: s = "CONTEST IS COMING"
// Output: ["CIC","OSO","N M","T I","E N","S G","T"]
//
//
//
// Constraints:
//
//     1 <= s.length <= 200
//     s contains only upper case English letters.
//     It's guaranteed that there is only one space between 2 words.
//


class Solution {
    public List<String> printVertically(String s) {

        return mytry(s);
    }

    private List<String> mytry(String s) {
        String[] arr = s.split(" ");
        List<String> result = new ArrayList<>();
        int index = 0;
        while (true) {
            String curr = "";
            int last = -1;
            for (int i = 0; i < arr.length; i++) {
                if (index >= arr[i].length()) {
                    curr += " ";
                } else {
                    last = i;
                    curr += arr[i].charAt(index);
                }
            }
            if (last == -1) {
                break;
            }
            result.add(curr.substring(0, last + 1));
            index++;
        }
        return result;
    }
}

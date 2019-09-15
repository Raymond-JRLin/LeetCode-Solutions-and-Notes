// 1189. Maximum Number of Balloons
// User Accepted: 3427
// User Tried: 3554
// Total Accepted: 3497
// Total Submissions: 5615
// Difficulty: Easy
// Given a string text, you want to use the characters of text to form as many instances of the word "balloon" as possible.
//
// You can use each character in text at most once. Return the maximum number of instances that can be formed.
//
//
//
// Example 1:
//
//
//
// Input: text = "nlaebolko"
// Output: 1
// Example 2:
//
//
//
// Input: text = "loonbalxballpoon"
// Output: 2
// Example 3:
//
// Input: text = "leetcode"
// Output: 0
//
//
// Constraints:
//
// 1 <= text.length <= 10^4
// text consists of lower case English letters only.


class Solution {
    public int maxNumberOfBalloons(String text) {
        if (text.isEmpty()) {
            return 0;
        }

        // probably input only has few of necessary chars
        // or just keep recording given text, then result = Math.min(result, map.getOrDefault('b', 0))
        Map<Character, Integer> map = new HashMap<>();
        map.put('b', 0);
        map.put('a', 0);
        map.put('l', 0);
        map.put('o', 0);
        map.put('n', 0);
        for (char c : text.toCharArray()) {
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            }
        }
        int result = text.length();
        for (char key : map.keySet()) {
            if (key == 'l' || key == 'o') {
                result = Math.min(result, map.get(key) / 2);
            } else {
                result = Math.min(result, map.get(key));
            }
        }
        return result;
    }
}

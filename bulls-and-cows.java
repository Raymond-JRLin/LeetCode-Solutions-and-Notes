// 299. Bulls and Cows
// DescriptionHintsSubmissionsDiscussSolution
// You are playing the following Bulls and Cows game with your friend: You write down a number and ask your friend to guess what the number is. Each time your friend makes a guess, you provide a hint that indicates how many digits in said guess match your secret number exactly in both digit and position (called "bulls") and how many digits match the secret number but locate in the wrong position (called "cows"). Your friend will use successive guesses and hints to eventually derive the secret number.
//
// Write a function to return a hint according to the secret number and friend's guess, use A to indicate the bulls and B to indicate the cows.
//
// Please note that both secret number and friend's guess may contain duplicate digits.
//
// Example 1:
//
// Input: secret = "1807", guess = "7810"
//
// Output: "1A3B"
//
// Explanation: 1 bull and 3 cows. The bull is 8, the cows are 0, 1 and 7.
// Example 2:
//
// Input: secret = "1123", guess = "0111"
//
// Output: "1A1B"
//
// Explanation: The 1st 1 in friend's guess is a bull, the 2nd or 3rd 1 is a cow.
// Note: You may assume that the secret number and your friend's guess only contain digits, and their lengths are always equal.


class Solution {
    public String getHint(String secret, String guess) {
        if (secret == null || secret.length() == 0) {
            return "";
        }
        if (guess == null || guess.length() == 0) {
            return String.valueOf(secret.length()) + "B";
        }

        return mytry(secret, guess);

        // return method2(secret, guess);
    }

    private String method2(String s, String g) {
        // ref: https://leetcode.com/problems/bulls-and-cows/discuss/74621/One-pass-Java-solution
        // 要看到题目中每个条件， note 里都说了两个输入的 string 都是只包含数字的 （因为是猜数字）， 所以每个 char 都是 0 － 9， 这样在空间上就可以只建立长度为 10 的数组来记录了; 并且他们有相同的长度
        // 在 mytry 中， 我尝试着做当不匹配的时候， 对 s 的 ++， 对 g 的 --, 因为有重复， 所以会出错， 那么其实思路是对的， 就需要在每次 +- 操作之前先判断一下， 如果已经有过差异了， 就算一个 cow
        // O(N) time and O(10) space
        int n = s.length();
        int bulls = 0;
        int cows = 0;
        int[] nums = new int[10];
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == g.charAt(i)) {
                bulls++;
            } else {
                // 注意这里的正负比较， 因为我们对出现在 s 中的 ++, 对 g 中出现的 --, 所以对 s 来说， 如果加之前就为负， 说明 g 中已经出现过 s 的这个 char， 有一个 cow， 然后再 ++， 对 g 来说， 如果已经为正， 说明 s 中已经有了 g 的这个 char ， 有一个 cow， 再 --
                if (nums[s.charAt(i) - '0']++ < 0) {
                    cows++;
                }
                if (nums[g.charAt(i) - '0']-- > 0) {
                    cows++;
                }
            }
        }
        return bulls + "A" + cows + "B";
    }

    private String mytry(String s, String g) {
        // O(max{N, M}) time and O(10) space (since there's only number from 0 to 9)
        // s and g must have same length
        int n = s.length();
        // int m = g.length();
        int bulls = 0;
        int cows = 0;
        Map<Integer, Integer> map = new HashMap<>();
        Map<Integer, Integer> guess = new HashMap<>();
        for (int i = 0; i < n; i++) {
            // if (i < m) {
                if (s.charAt(i) == g.charAt(i)) {
                    bulls++;
                } else {
                    map.put(s.charAt(i) - '0', map.getOrDefault(s.charAt(i) - '0', 0) + 1);
                    guess.put(g.charAt(i) - '0', guess.getOrDefault(g.charAt(i) - '0', 0) + 1);
                }
            // }
        }
        // for (int i = n; i < m; i++) {
            // guess.put(g.charAt(i) - '0', guess.getOrDefault(g.charAt(i) - '0', 0) + 1);
        // }
        for (int k : map.keySet()) {
            if (guess.containsKey(k)) {
                cows += Math.min(map.get(k), guess.get(k));
            }
        }

        return String.valueOf(bulls) + "A" + String.valueOf(cows) + "B";
    }
}

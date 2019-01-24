// 843. Guess the Word
// DescriptionHintsSubmissionsDiscussSolution
// This problem is an interactive problem new to the LeetCode platform.
//
// We are given a word list of unique words, each word is 6 letters long, and one word in this list is chosen as secret.
//
// You may call master.guess(word) to guess a word.  The guessed word should have type string and must be from the original list with 6 lowercase letters.
//
// This function returns an integer type, representing the number of exact matches (value and position) of your guess to the secret word.  Also, if your guess is not in the given wordlist, it will return -1 instead.
//
// For each test case, you have 10 guesses to guess the word. At the end of any number of calls, if you have made 10 or less calls to master.guess and at least one of these guesses was the secret, you pass the testcase.
//
// Besides the example test case below, there will be 5 additional test cases, each with 100 words in the word list.  The letters of each word in those testcases were chosen independently at random from 'a' to 'z', such that every word in the given word lists is unique.
//
// Example 1:
// Input: secret = "acckzz", wordlist = ["acckzz","ccbazz","eiowzz","abcczz"]
//
// Explanation:
//
// master.guess("aaaaaa") returns -1, because "aaaaaa" is not in wordlist.
// master.guess("acckzz") returns 6, because "acckzz" is secret and has all 6 matches.
// master.guess("ccbazz") returns 3, because "ccbazz" has 3 matches.
// master.guess("eiowzz") returns 2, because "eiowzz" has 2 matches.
// master.guess("abcczz") returns 4, because "abcczz" has 4 matches.
//
// We made 5 calls to master.guess and one of them was the secret, so we pass the test case.
// Note:  Any solutions that attempt to circumvent the judge will result in disqualification.


/**
 * // This is the Master's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface Master {
 *     public int guess(String word) {}
 * }
 */
class Solution {
    public void findSecretWord(String[] wordlist, Master master) {
        // https://leetcode.com/problems/guess-the-word/discuss/133862/Random-Guess-and-Minimax-Guess-with-Comparison

        // method1(wordlist, master);

        method2(wordlist, master);
    }

    public void method2(String[] wordlist, Master master) {
        // method1 choose word to guess randomly so it may pass. So we should think about a more efficient way to choose word to guess, like if the chosen word has 0 match, then it goes slowly. Thus we can choose those words that have the highest match with others

        for (int i = 0, x = 0; i < 10 && x < 6; ++i) {
            Map<String, Integer> map = new HashMap<>(); // to record the string and how many other words match 0 with it, <word, # string with 0 match word>
            for (String word : wordlist) {
                for (String next : wordlist) {
                    if (!word.equals(next) && match(word, next) == 0) {
                        map.put(word, map.getOrDefault(word, 0) + 1);
                    }
                }
            }
            // we gonna choose a word to guess, which has the min 0 match with others
            String guess = "";
            int m = wordlist.length;
            for (String word : wordlist) {
                if (map.getOrDefault(word, 0) < m) {
                    guess = word;
                    m = map.getOrDefault(word, 0);
                }
            }

            x = master.guess(guess);
            // choose those words that have the same match as this guessing word
            List<String> wordlist2 = new ArrayList<>();
            for (String w : wordlist) {
                if (match(guess, w) == x) {
                    wordlist2.add(w);
                }
            }
            wordlist = wordlist2.toArray(new String[wordlist2.size()]);
        }
    }

    private void method1(String[] wordlist, Master master) {
        // straightforward to call master.guess() on random word within 10 times and before we get the correct answer
        for (int i = 0, x = 0; i < 10 && x < 6; ++i) {
            String guess = wordlist[new Random().nextInt(wordlist.length)];
            x = master.guess(guess);
            // choose those words that have the same match as this guessing word
            List<String> wordlist2 = new ArrayList<>();
            for (String w : wordlist) {
                if (match(guess, w) == x) {
                    wordlist2.add(w);
                }
            }
            wordlist = wordlist2.toArray(new String[wordlist2.size()]);
        }
    }
    private int match(String a, String b) {
        int matches = 0;
        for (int i = 0; i < a.length(); ++i) {
            if (a.charAt(i) == b.charAt(i)) {
                matches ++;
            }
        }
        return matches;
    }

}

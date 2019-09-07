// 748. Shortest Completing Word
// DescriptionHintsSubmissionsDiscussSolution
// Find the minimum length word from a given dictionary words, which has all the letters from the string licensePlate. Such a word is said to complete the given string licensePlate
//
// Here, for letters we ignore case. For example, "P" on the licensePlate still matches "p" on the word.
//
// It is guaranteed an answer exists. If there are multiple answers, return the one that occurs first in the array.
//
// The license plate might have the same letter occurring multiple times. For example, given a licensePlate of "PP", the word "pair" does not complete the licensePlate, but the word "supper" does.
//
// Example 1:
// Input: licensePlate = "1s3 PSt", words = ["step", "steps", "stripe", "stepple"]
// Output: "steps"
// Explanation: The smallest length word that contains the letters "S", "P", "S", and "T".
// Note that the answer is not "step", because the letter "s" must occur in the word twice.
// Also note that we ignored case for the purposes of comparing whether a letter exists in the word.
// Example 2:
// Input: licensePlate = "1s3 456", words = ["looks", "pest", "stew", "show"]
// Output: "pest"
// Explanation: There are 3 smallest length words that contains the letters "s".
// We return the one that occurred first.
// Note:
// licensePlate will be a string with length in range [1, 7].
// licensePlate will contain digits, spaces, or letters (uppercase or lowercase).
// words will have a length in the range [10, 1000].
// Every words[i] will consist of lowercase letters, and have length in range [1, 15].
// Seen this question in a real interview before?
// Difficulty:Easy


class Solution {
    public String shortestCompletingWord(String licensePlate, String[] words) {
        if (licensePlate == null || licensePlate.length() == 0) {
            return licensePlate;
        }
        if (words == null || words.length == 0) {
            return "";
        }

        // return my_try(licensePlate, words);

        // may reduce some space by using char array, or we can use HashMap with same idea
        return method2(licensePlate, words);
    }

    private String method2(String licensePlate, String[] words) {
        int[] charMap = new int[26];
        for (char c : licensePlate.toCharArray()) {
            if (Character.isLetter(c)) {
                int val = Character.toLowerCase(c) - 'a';
                charMap[val]++;
            }
        }
        String result = "";
        int minLen = Integer.MAX_VALUE;
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if (match(charMap, word) && word.length() < minLen) {
                minLen = word.length();
                result = word;
            }
        }
        return result;
    }
    private boolean match(int[] charMap, String word) {
        int[] wordMap = new int[26];
        for (char c : word.toCharArray()) {
            wordMap[Character.toLowerCase(c) - 'a']++;
        }
        for (int i = 0; i < 26; i++) {
            if (charMap[i] != 0 && wordMap[i] < charMap[i]) {
                return false;
            }
        }
        return true;
    }

    private String my_try(String licensePlate, String[] words) {
        List<Character> list = getChar(licensePlate);
        String result = "";
        int len = Integer.MAX_VALUE;
        for (String word : words) {
            List<Character> temp = new ArrayList<>(list);
            for (char c : word.toCharArray()) {
                char curr = Character.toLowerCase(c);
                if (temp.contains(curr)) {
                    temp.remove((Character) curr);
                    if (temp.isEmpty()) {
                        // get the potential resutl
                        if (word.length() < len) {
                            // get a shorter answer
                            len = word.length();
                            result = word;
                        }
                    }
                }
            }
        }
        return result;

    }
    private List<Character> getChar(String licensePlate) {
        char[] license = licensePlate.toCharArray();
        List<Character> list = new ArrayList<>();
        for (char c : license) {
            if (Character.isLetter(c)) {
                list.add(Character.toLowerCase(c));
            }
        }
        return list;
    }
}

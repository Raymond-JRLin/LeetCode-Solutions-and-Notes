// 383. Ransom Note
// DescriptionHintsSubmissionsDiscussSolution
//
// Given an arbitrary ransom note string and another string containing letters from all the magazines, write a function that will return true if the ransom note can be constructed from the magazines ; otherwise, it will return false.
//
// Each letter in the magazine string can only be used once in your ransom note.
//
// Note:
// You may assume that both strings contain only lowercase letters.
//
// canConstruct("a", "b") -> false
// canConstruct("aa", "ab") -> false
// canConstruct("aa", "aab") -> true
//
// Seen this question in a real interview before?
//
//     Difficulty:Easy


class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
        if (magazine == null || magazine.length() < ransomNote.length()) {
            return false;
        }

        return mytry(ransomNote, magazine);
    }

    private boolean mytry(String note, String maga) {
        int[] bucket = new int[26];
        for (char c : maga.toCharArray()) {
            bucket[c - 'a']++;
        }
        for (char c : note.toCharArray()) {
            bucket[c - 'a']--;
            if (bucket[c - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }
}

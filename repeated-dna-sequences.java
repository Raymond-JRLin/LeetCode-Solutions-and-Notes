// 187. Repeated DNA Sequences
// DescriptionHintsSubmissionsDiscussSolution
// All DNA is composed of a series of nucleotides abbreviated as A, C, G, and T, for example: "ACGAATTCCG". When studying DNA, it is sometimes useful to identify repeated sequences within the DNA.
//
// Write a function to find all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule.
//
// Example:
//
// Input: s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
//
// Output: ["AAAAACCCCC", "CCCCCAAAAA"]
// Seen this question in a real interview before?
// Difficulty:Medium


class Solution {
    public List<String> findRepeatedDnaSequences(String s) {
        if (s == null || s.length() < 10) {
            return Collections.emptyList();
        }

        // return mytry(s);

        return method2(s);
    }

    private List<String> method2(String s) {
        // String uses 8 bytes and int use 4 bytes to record and reduce space
        // ref: https://leetcode.com/problems/repeated-dna-sequences/discuss/53867/Clean-Java-solution-(hashmap-+-bits-manipulation)
        int[] nums = new int[26];
        // use 4 number to represent 4 scenarios
        nums['C' - 'A'] = 1;
        nums['G' - 'A'] = 2;
        nums['T' - 'A'] = 3;
        Set<Integer> set = new HashSet<>();
        Set<String> repeat = new HashSet<>();
        for (int i = 0; i <= s.length() - 10; i++) {
            int val = 0;
            // count the value of current 10 chars
            for (int j = i; j < i + 10; j++) {
                val <<= 2; // move left by 2 bits
                val |= nums[s.charAt(j) - 'A']; // add current char bits
            }
            if (!set.add(val)) {
                repeat.add(s.substring(i, i + 10));
            }
        }
        return new ArrayList<String>(repeat);
    }

    private List<String> mytry(String s) {
        // O(N) time and space
        Set<String> set = new HashSet<>();
        Set<String> repeat = new HashSet<>();
        for (int i = 0; i <= s.length() - 10; i++) {
            // attention "<= s.length() - 10", i is the starting index, so we should get it: usually i < n -> to get n: i <= n -> minus 10
            String curr = s.substring(i, i + 10);
            if (set.contains(curr)) {
                repeat.add(curr);
            } else {
                set.add(curr);
            }
        }

        // List<String> result = new ArrayList<>();
        // for (String str : repeat) {
        //     result.add(str);
        // }
        // return result;
        // or we can do:
        return new ArrayList<String>(repeat);
    }
}

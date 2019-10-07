// 293. Flip Game
// DescriptionHintsSubmissionsDiscussSolution
// You are playing the following Flip Game with your friend: Given a string that contains only these two characters: + and -, you and your friend take turns to flip two consecutive "++" into "--". The game ends when a person can no longer make a move and therefore the other person will be the winner.
//
// Write a function to compute all possible states of the string after one valid move.
//
// Example:
//
// Input: s = "++++"
// Output:
// [
//   "--++",
//   "+--+",
//   "++--"
// ]
// Note: If there is no valid move, return an empty list [].
//
// Seen this question in a real interview before?
// Difficulty:Easy


class Solution {
    public List<String> generatePossibleNextMoves(String s) {
        if (s == null || s.length() < 2) {
            return Collections.emptyList();
        }

        return mytry(s);
    }

    private List<String> mytry(String s) {
        int n = s.length();
        List<String> result = new ArrayList<>();
        String str = "--";
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.substring(i, i + 2).equals("++")) {
                result.add(s.substring(0, i) + str + s.substring(i + 2));
            }
        }
        return result;
    }
}

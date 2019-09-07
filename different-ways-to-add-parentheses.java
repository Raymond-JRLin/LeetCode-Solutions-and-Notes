// 241. Different Ways to Add Parentheses
// DescriptionHintsSubmissionsDiscussSolution
// Given a string of numbers and operators, return all possible results from computing all the different possible ways to group numbers and operators. The valid operators are +, - and *.
//
// Example 1:
//
// Input: "2-1-1"
// Output: [0, 2]
// Explanation:
// ((2-1)-1) = 0
// (2-(1-1)) = 2
// Example 2:
//
// Input: "2*3-4*5"
// Output: [-34, -14, -10, -10, 10]
// Explanation:
// (2*(3-(4*5))) = -34
// ((2*3)-(4*5)) = -14
// ((2*(3-4))*5) = -10
// (2*((3-4)*5)) = -10
// (((2*3)-4)*5) = 10


class Solution {
    public List<Integer> diffWaysToCompute(String input) {
        // we can do recursion - divide & conquer, to solve this problem recursively
        if (input == null || input.length() == 0) {
            return new ArrayList<Integer>();
        }
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == '+' || c == '-' || c == '*') {
                String left = input.substring(0, i);
                String right = input.substring(i + 1);
                List<Integer> first = diffWaysToCompute(left);
                List<Integer> second = diffWaysToCompute(right);
                for (int j : first) {
                    for (int k : second) {
                        if (c == '+') {
                            result.add(j + k);
                        } else if (c == '-') {
                            result.add(j - k);
                        } else if (c == '*') {
                            result.add(j * k);
                        }
                    }
                }
            }
        }
        if (result.size() == 0) {
            result.add(Integer.parseInt(input));
        }
        return result;
    }
}

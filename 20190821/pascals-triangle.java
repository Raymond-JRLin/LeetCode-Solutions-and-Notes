// 118. Pascal's Triangle
// DescriptionHintsSubmissionsDiscussSolution
//
// Given a non-negative integer numRows, generate the first numRows of Pascal's triangle.
//
//
// In Pascal's triangle, each number is the sum of the two numbers directly above it.
//
// Example:
//
// Input: 5
// Output:
// [
//      [1],
//     [1,1],
//    [1,2,1],
//   [1,3,3,1],
//  [1,4,6,4,1]
// ]
//
// Seen this question in a real interview before?
//
//     Difficulty:Easy


class Solution {
    public List<List<Integer>> generate(int numRows) {
        if (numRows <= 0) {
            return Collections.emptyList();
        }

        return mytry(numRows);
    }

    private List<List<Integer>> mytry(int rows) {
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>(Arrays.asList(1)));
        for (int i = 1; i < rows; i++) {
            List<Integer> list = new ArrayList<>();
            list.add(1);
            for (int j = 1; j < i; j++) {
                list.add(result.get(i - 1).get(j - 1) + result.get(i - 1).get(j));
            }
            list.add(1);
            result.add(list);
        }
        return result;
    }
}

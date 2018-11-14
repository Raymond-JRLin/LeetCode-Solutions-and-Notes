// 118. Pascal's Triangle
// DescriptionHintsSubmissionsDiscussSolution
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


class Solution {
    public List<List<Integer>> generate(int numRows) {
        if (numRows < 1) {
            return new ArrayList<List<Integer>>();
        }
        // 杨辉三角

        // return method1(numRows);

        // from discussion, no need to check boundaries - head & tail 1
        return method2(numRows);
    }

    private List<List<Integer>> method2(int rows) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        List<Integer> row = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            // loop each row
            row.add(0, 1); // 每次都向前插入， 因此不能每次都 new， 需要在 loop 外建好一直用， 这样才不会 outOfIndex， 然后就需要 deep copy
            for (int j = 1; j < row.size() - 1; j++) {
                // 从 index 为 1 开始才要计算， 到倒数第二个， 注意下面是哪两位的 index 加和
                row.set(j, row.get(j) + row.get(j + 1));
            }
            result.add(new ArrayList<Integer>(row)); // deep copy
        }
        return result;
    }

    private List<List<Integer>> method1(int rows) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        for (int i = 0; i < rows; i++) {
            // use 1st for loop to loop rows
            List<Integer> row = new ArrayList<Integer>();
            for (int j = 0; j < i + 1; j++) {
                // use 2nd for loop to loop column value in this row
                if (j == 0 || j == i) {
                    row.add(1);
                } else {
                    // 观察规律， 从第三行开始除首尾的 1 的每个数 = 它左上方 + 它右上方， 即：
                    // f[i][j] = f[i - 1][j - 1] + f[i - 1][j]
                    row.add(result.get(i - 1).get(j - 1) + result.get(i - 1).get(j));
                }
            }
            result.add(row);
        }
        return result;
    }
}

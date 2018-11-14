// 119. Pascal's Triangle II
// DescriptionHintsSubmissionsDiscussSolution
// Given a non-negative index k where k ≤ 33, return the kth index row of the Pascal's triangle.
//
// Note that the row index starts from 0.
//
//
// In Pascal's triangle, each number is the sum of the two numbers directly above it.
//
// Example:
//
// Input: 3
// Output: [1,3,3,1]
// Follow up:
//
// Could you optimize your algorithm to use only O(k) extra space?


class Solution {
    public List<Integer> getRow(int rowIndex) {
        if (rowIndex < 0) {
            return Collections.emptyList();
        }

        // return mytry(rowIndex);

        // return method2(rowIndex);

        return method3(rowIndex);
    }

    private List<Integer> method3(int rowIndex) {
        // if we use array to do method2, then it's a bit easier to handle index
        Integer[] result = new Integer[rowIndex + 1]; // we should use Integer as returned list, so we can use Arrays.asList(), otherwise their types are incompatible
        Arrays.fill(result, 0); // set 0, since this array is initialized as NULL because of Integere type
        result[0] = 1;
        for (int i = 1; i <= rowIndex; i++) {
            for (int j = i; j >= 1; j--) {
                result[j] = result[j] + result[j - 1];
            }
        }
        return Arrays.asList(result);
    }

    private List<Integer> method2(int rowIndex) {
        // 如果真的严格 O(K) 的话
        List<Integer> result = new ArrayList<>();
        result.add(1);
        for (int i = 1; i <= rowIndex; i++) {
            result.add(1); // add trailing 1
            for (int j = i - 1; j >= 1; j--) {
                // from index of i - 1 to index of 1, 更改 index 上原本的数
                // 加上的是 j 前面一个， 所以要从后往前， 拿到的才是上一轮的结果
                result.set(j, result.get(j) + result.get(j - 1));
            }
        }
        return result;
    }

    private List<Integer> mytry(int rowIndex) {
        // 我觉得这个方法也是 O(K) 的， 因为 worst case 就是 k - 1 和 k 倒换的时候， 一共是 2K
        List<Integer> prev = new ArrayList<>();
        prev.add(1);

        for (int i = 1; i <= rowIndex; i++) {
            List<Integer> curr = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    curr.add(1);
                } else {
                    curr.add(prev.get(j - 1) + prev.get(j));
                }
            }
            prev = curr;
        }
        return prev;
    }
}

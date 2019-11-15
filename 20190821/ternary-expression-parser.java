// 439. Ternary Expression Parser
// DescriptionHintsSubmissionsDiscussSolution
//
// Given a string representing arbitrarily nested ternary expressions, calculate the result of the expression. You can always assume that the given expression is valid and only consists of digits 0-9, ?, :, T and F (T and F represent True and False respectively).
//
// Note:
//
//     The length of the given string is ≤ 10000.
//     Each number will contain only one digit.
//     The conditional expressions group right-to-left (as usual in most languages).
//     The condition will always be either T or F. That is, the condition will never be a digit.
//     The result of the expression will always evaluate to either a digit 0-9, T or F.
//
// Example 1:
//
// Input: "T?2:3"
//
// Output: "2"
//
// Explanation: If true, then result is 2; otherwise result is 3.
//
// Example 2:
//
// Input: "F?1:T?4:5"
//
// Output: "4"
//
// Explanation: The conditional expressions group right-to-left. Using parenthesis, it is read/evaluated as:
//
//              "(F ? 1 : (T ? 4 : 5))"                   "(F ? 1 : (T ? 4 : 5))"
//           -> "(F ? 1 : 4)"                 or       -> "(T ? 4 : 5)"
//           -> "4"                                    -> "4"
//
// Example 3:
//
// Input: "T?T?F:5:3"
//
// Output: "F"
//
// Explanation: The conditional expressions group right-to-left. Using parenthesis, it is read/evaluated as:
//
//              "(T ? (T ? F : 5) : 3)"                   "(T ? (T ? F : 5) : 3)"
//           -> "(T ? F : 3)"                 or       -> "(T ? F : 5)"
//           -> "F"                                    -> "F"
//
// Seen this question in a real interview before?
//
//     Difficulty:Medium


class Solution {
    public String parseTernary(String expression) {
        if (expression == null || expression.isEmpty()) {
            return "";
        }

        // return mytry(expression);

        // return method2(expression);

        return method3(expression);
    }

    private String method3(String exp) {
        // 三元表达式判定过程和 binary tree 非常像， 所以可以用 bt 来模拟这个过程
        Node root = buildTree(exp.toCharArray());
        return getValue(root);
    }
    private String getValue(Node root) {
        while (root.val == 'T' || root.val == 'F') {
            if (root.left == null && root.right == null) {
                break;
            }
            if (root.val == 'T') {
                root = root.left;
            } else {
                root = root.right;
            }
        }
        return String.valueOf(root.val);
    }
    private Node buildTree(char[] arr) {
        // 建树的过程要注意怎么走
        Node root = new Node(arr[0]);
        Node curr = root;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == '?') {
                Node left = new Node(arr[i + 1]);
                curr.left = left;
                left.parent = curr;
                curr = left;
            } else if (arr[i] == ':') {
                curr = curr.parent;
                while (curr.right != null && curr.parent != null) {
                    curr = curr.parent;
                }
                Node right = new Node(arr[i + 1]);
                curr.right = right;
                right.parent = curr;
                curr = curr.right;
            }
        }
        return root;
    }
    class Node {
        char val;
        Node left, right, parent;

        Node(char v) {
            this.val = v;
            left = null;
            right = null;
            parent = null;
        }
    }

    private String method2(String exp) {
        int n = exp.length();
        return dfs(exp.toCharArray(), 0, n - 1);
    }
    private String dfs(char[] arr, int start, int end) {
        // 和 stack 的方法我觉得正好相反， 从最外层到最里面， 然后递归回来
        if (start == end) {
            return String.valueOf(arr[start]);
        }
        // 用这个 count 来找应该去哪一层
        int count = 0;
        int i = start;
        while (i <= end) {
            if (arr[i] == '?') {
                count++;
            } else if (arr[i] == ':') {
                count--;
                if (count == 0) {
                    break;
                }
            }
        }
        if (arr[start] == 'T') {
            // i 的左半边， start + 2 跳过判断的 T/F 和 ？， i - 1 砍掉 i 位置的 ：
            return dfs(arr, start + 2, i - 1);
        } else {
            // i 的右半边
            return dfs(arr, i + 1, end);
        }
    }

    private String mytry(String exp) {
        // 用 stack 去模拟从右往左这个顺序
        // 当碰到 ？ 的时候， 才来计算应该选哪个， 其他时候往里放
        char[] arr = exp.toCharArray();
        int n = arr.length;
        Stack<Character> stack = new Stack<>();
        for (int i = n - 1; i >= 0; i--) {
            char curr = arr[i];
            if (curr == ':') {
                continue;
            }
            if (!stack.isEmpty() && stack.peek() == '?') {
                // 前一个是 ？， 当下 curr 是 T 或 F
                stack.pop(); // pop ?
                char left = stack.pop();
                char right = stack.pop();
                if (curr == 'T') {
                    stack.push(left);
                } else {
                    stack.push(right);
                }
            } else {
                stack.push(curr);
            }
        }
        return String.valueOf(stack.peek());
    }
}

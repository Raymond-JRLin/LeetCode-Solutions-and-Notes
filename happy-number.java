// 202. Happy Number
// DescriptionHintsSubmissionsDiscussSolution
// Write an algorithm to determine if a number is "happy".
//
// A happy number is a number defined by the following process: Starting with any positive integer, replace the number by the sum of the squares of its digits, and repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1. Those numbers for which this process ends in 1 are happy numbers.
//
// Example:
//
// Input: 19
// Output: true
// Explanation:
// 12 + 92 = 82
// 82 + 22 = 68
// 62 + 82 = 100
// 12 + 02 + 02 = 1
// Seen this question in a real interview before?
// Difficulty:Easy


class Solution {
    public boolean isHappy(int n) {
        if (n == 1) {
            return true;
        }
        // 就直接不断的去做这个过程， 如果碰到 1， 那说明是， 如果碰不到， 那么就是题目说的另一种情况， 即无限循环 - “it loops endlessly in a cycle which does not include 1”， 其实这里就要注意审题， 因此我们可以通过判断是否有 1 和是否循环了 - 即又得到了相同的结果来判断

        // return method1_hashset(n);

        // 从上面的过程看出来， 其实和快慢指针找 linkedlist 是否有环是一个道理， 不妨用这种方法来做 - improvement of O(1) space
        return method2_pointers(n);
    }

    private boolean method2_pointers(int n) {
        int slow = n;
        int fast = n;
        while (slow > 1) {
            slow = getSum(slow);
            if (slow == 1) {
                return true;
            }
            fast = getSum(getSum(fast));
            if (fast == 1) {
                return true;
            }
            if (slow == fast) {
                // there is a cycle
                return false;
            }
        }
        return true;
    }

    private boolean method1_hashset(int n) {
        Set<Integer> set = new HashSet<>(); // use set to record result when we do square sum
        while (n > 1) {
            n = getSum(n);
            if (set.contains(n)) {
                return false;
            }
            if (n == 1) {
                return true;
            }
            set.add(n);
        }
        return true;
    }
    private int getSum(int n) {
        int sum = 0;
        while (n > 0) {
            sum += (n % 10) * (n % 10);
            n /= 10;
        }
        return sum;
    }
}

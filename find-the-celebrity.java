// 277. Find the Celebrity
// DescriptionHintsSubmissionsDiscussSolution
// Suppose you are at a party with n people (labeled from 0 to n - 1) and among them, there may exist one celebrity. The definition of a celebrity is that all the other n - 1 people know him/her but he/she does not know any of them.
//
// Now you want to find out who the celebrity is or verify that there is not one. The only thing you are allowed to do is to ask questions like: "Hi, A. Do you know B?" to get information of whether A knows B. You need to find out the celebrity (or verify there is not one) by asking as few questions as possible (in the asymptotic sense).
//
// You are given a helper function bool knows(a, b) which tells you whether A knows B. Implement a function int findCelebrity(n), your function should minimize the number of calls to knows.
//
// Note: There will be exactly one celebrity if he/she is in the party. Return the celebrity's label if there is a celebrity in the party. If there is no celebrity, return -1.
//


/* The knows API is defined in the parent class Relation.
      boolean knows(int a, int b); */

public class Solution extends Relation {
    public int findCelebrity(int n) {
        if (n < 0) {
            return -1;
        }
        if (n == 1) {
            return 0;
        }
        //             / True: a knows b, then a cannot be celebrity
        // knows(a, b)
        //             \ False: a doesn't know b, then b cannot be celebrity
        /// so every comparison would get rid of one possibility

        // return mytry(n);

        // return method2(n);

        return method3(n);
    }

    private int method3(int n) {
        // 1 pointer
        int candidate = 0;
        for (int i = 1; i < n; i++) {
            if (knows(candidate, i)) {
                candidate = i;
            }
        }
        // after above for loop, numbers before candidate know at least 1 number, candidate doesn't know numbers after it, since we only have 1 celebrity
        // same idea of double-checking, we can also do just 1 for loop
        for (int i = 0; i < candidate; i++) {
            if (knows(candidate, i)) {
                return -1;
            }
        }
        for (int i = candidate + 1; i < n; i++) {
            if (!knows(i, candidate)) {
                return -1;
            }
        }
        return candidate;
    }

    private int method2(int n) {
        // stack
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            stack.push(i);
        }
        while (stack.size() > 1) {
            int a = stack.pop();
            int b = stack.pop();
            if (knows(a, b)) {
                stack.push(b);
            } else {
                stack.push(a);
            }
        }
        int candidate = stack.pop();
        for (int i = 0; i < n; i++) {
            if (i != candidate && (!knows(i, candidate) || knows(candidate, i))) {
                return -1;
            }
        }
        return candidate;
    }

    private int mytry(int n) {
        // 2 points like 2 sum
        int left = 0;
        int right = n - 1;
        while (left < right) {
            if (knows(left, right)) {
                // left cannot be celebrity
                left++;
            } else {
                right--;
            }
        }
        // double check if final left/right is the celebrity
        for (int i = 0; i < n; i++) {
            if (i != left && (!knows(i, left) || knows(left, i))) {
                // 只要有一个 i 不知道 left 或者 left 知道某一个 i， 那么 left 都不可能是 celebrity
                return -1;
            }
        }
        // after checking all other number then we can say left is celebrity so we should return false in for loop
        return left;
    }
}

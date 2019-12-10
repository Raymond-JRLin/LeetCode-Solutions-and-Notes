// 457. Circular Array Loop
// DescriptionHintsSubmissionsDiscussSolution
//
// You are given a circular array nums of positive and negative integers. If a number k at an index is positive, then move forward k steps. Conversely, if it's negative (-k), move backward k steps. Since the array is circular, you may assume that the last element's next element is the first element, and the first element's previous element is the last element.
//
// Determine if there is a loop (or a cycle) in nums. A cycle must start and end at the same index and the cycle's length > 1. Furthermore, movements in a cycle must all follow a single direction. In other words, a cycle must not consist of both forward and backward movements.
//
//
//
// Example 1:
//
// Input: [2,-1,1,2,2]
// Output: true
// Explanation: There is a cycle, from index 0 -> 2 -> 3 -> 0. The cycle's length is 3.
//
// Example 2:
//
// Input: [-1,2]
// Output: false
// Explanation: The movement from index 1 -> 1 -> 1 ... is not a cycle, because the cycle's length is 1. By definition the cycle's length must be greater than 1.
//
// Example 3:
//
// Input: [-2,1,-1,-2,-2]
// Output: false
// Explanation: The movement from index 1 -> 2 -> 1 -> ... is not a cycle, because movement from index 1 -> 2 is a forward movement, but movement from index 2 -> 1 is a backward movement. All movements in a cycle must follow a single direction.
//
//
//
// Note:
//
//     -1000 ≤ nums[i] ≤ 1000
//     nums[i] ≠ 0
//     1 ≤ nums.length ≤ 5000
//
//
//
// Follow up:
//
// Could you solve it in O(n) time complexity and O(1) extra space complexity?
// Seen this question in a real interview before?
//
//     Difficulty:Medium


class Solution {
    public boolean circularArrayLoop(int[] nums) {

        // return mytry(nums);

        return method2(nums);
    }

    private boolean method2(int[] nums) {
        // 在数组中找环， 可以用快慢指针
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int slow = i;
            int fast = i;
            while (isValid(nums, i, fast) && isValid(nums, i, getNextIndex(nums, fast))) {
                slow = getNextIndex(nums, slow);
                fast = getNextIndex(nums, getNextIndex(nums, fast));
                if (slow == fast) {
                    // circle length == 1
                    if (slow == getNextIndex(nums, slow)) {
                        break;
                    }
                    return true;
                }
            }
            // mark this path as not reachable
            slow = i;
            while (isValid(nums, i, slow)) {
                int temp = slow;
                slow = getNextIndex(nums, slow);
                nums[temp] = 0;
            }
        }
        return false;
    }
    private int getNextIndex(int[] nums, int i) {
        return ((i + nums[i]) % nums.length + nums.length) % nums.length;
    }
    private boolean isValid(int[] nums, int i, int j) {
        return nums[i] < 0 && nums[j] < 0 || nums[i] > 0 && nums[j] > 0;
    }

    private boolean mytry(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (move(nums, n, i, new HashSet<Integer>())) {
                return true;
            }
        }
        return false;
    }
    private boolean move(int[] nums, int n, int i, Set<Integer> set) {
        boolean positive = nums[i] > 0;
        int ori = i;
        while (true) {
            if (set.contains(i)) {
                break;
            }
            if (nums[i] > 0 != positive) {
                return false;
            }
            set.add(i);
            i = ((i + nums[i]) % n + n) % n;
        }
        return set.size() > 1 && i == ori;
    }
}

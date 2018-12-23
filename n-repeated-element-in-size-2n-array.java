// 961. N-Repeated Element in Size 2N Array
// User Accepted: 1913
// User Tried: 1927
// Total Accepted: 1939
// Total Submissions: 2410
// Difficulty: Easy
// In a array A of size 2N, there are N+1 unique elements, and exactly one of these elements is repeated N times.
//
// Return the element repeated N times.
//
//
//
// Example 1:
//
// Input: [1,2,3,3]
// Output: 3
// Example 2:
//
// Input: [2,1,2,5,3,2]
// Output: 2
// Example 3:
//
// Input: [5,1,5,2,5,3,5,4]
// Output: 5


class Solution {
    public int repeatedNTimes(int[] A) {
        if (A == null || A.length == 0) {
            return -1;
        }

        // return mytry(A);

        // return method2(A);

        return method3(A);
    }

    private int method3(int[] nums) {
        int n = nums.length;
        int i = 0;
        int j = 0;
        Random rand = new Random();
        while (i == j || nums[i] != nums[j]) {
            i = rand.nextInt(n);
            j = rand.nextInt(n);
        }
        // while 退出的条件是 (i != j && nums[i] == nums[j])
        return nums[i];
    }

    public int method2(int[] A) {
        int[] count = new int[10000];
        for (int a : A) {
            if (count[a]++ == 1) {
                // 后 ++， 别的只有 1 个
                return a;
            }
        }
        return -1;
    }

    private int mytry(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (!set.add(num)) {
                return num;
            }
        }
        return -1;
    }
}

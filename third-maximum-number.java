// 414. Third Maximum Number
// DescriptionHintsSubmissionsDiscussSolution
// Given a non-empty array of integers, return the third maximum number in this array. If it does not exist, return the maximum number. The time complexity must be in O(n).
//
// Example 1:
// Input: [3, 2, 1]
//
// Output: 1
//
// Explanation: The third maximum is 1.
// Example 2:
// Input: [1, 2]
//
// Output: 2
//
// Explanation: The third maximum does not exist, so the maximum (2) is returned instead.
// Example 3:
// Input: [2, 2, 3, 1]
//
// Output: 1
//
// Explanation: Note that the third maximum here means the third maximum distinct number.
// Both numbers with value 2 are both considered as second maximum.


class Solution {
    public int thirdMax(int[] nums) {

        // return mytry(nums);

        // or we can use Long.MIN_VALUE or Integer
        // return method2(nums);

        return method3(nums);
    }

    private int method3(int[] nums) {
        // pq and set: O(Nlog3)
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (set.contains(num)) {
                continue;
            }
            pq.offer(num);
            set.add(num);
            // remember poll into pq FIRST, then remove the largest one
            if (pq.size() > 3) {
                set.remove(pq.poll()); // set should also remove
            }
        }
        if (pq.size() < 3) {
            if (pq.size() > 1) {
                pq.poll();
            }
        }
        return pq.peek();
    }

    private int method2(int[] nums) {
        Integer max = null;
        Integer mid = null;
        Integer small = null;
        for (Integer num : nums) {
            // iterate as Integer too
            if (num.equals(max) || num.equals(mid) || num.equals(small)) {
                continue;
            }
            if (max == null || num > max) {
                small = mid;
                mid = max;
                max = num;
            } else if (mid == null || num > mid) {
                small = mid;
                mid = num;
            } else if (small == null || num > small) {
                small = num;
            }
        }
        return small == null ? max : small;
    }

    private int mytry(int[] nums) {
        int first = Integer.MIN_VALUE;
        int second = Integer.MIN_VALUE;
        int third = Integer.MIN_VALUE;
        int count = 0;
        for (int num : nums) {
            // avoid shifting, be careful about special cases: [1,-2147483648,2], [1,2,-2147483648]
            if (num == first && count > 0 || num == second && count > 1) {
                continue;
            }
            if (num > first) {
                third = second;
                second = first;
                first = num;
                count++;
            } else if (num > second) {
                third = second;
                second = num;
                count++;
            } else if (num >= third) {
                third = num;
                count++;
            }
        }
        return count < 3 ? first : third;
    }
}

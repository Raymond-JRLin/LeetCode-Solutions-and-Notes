// 229. Majority Element II
// DescriptionHintsSubmissionsDiscussSolution
// Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.
//
// Note: The algorithm should run in linear time and in O(1) space.
//
// Example 1:
//
// Input: [3,2,3]
// Output: [3]
// Example 2:
//
// Input: [1,1,1,3,3,2,2,2]
// Output: [1,2]


class Solution {
    public List<Integer> majorityElement(int[] nums) {
        if (nums == null || nums.length == 0) {
            return Collections.emptyList();
        }

        // return mytry(nums);

        return method1(nums);
    }

    private List<Integer> method1(int[] nums) {
        // same idea with I by vote: O(N) time and O(1) space
        // since it should larger than 1 / 3, so at most there are 2 numbers as result
        int n = nums.length;
        List<Integer> result = new ArrayList<>();
        int can1 = nums[0];
        int can2 = nums[0];
        int f1 = 0, f2 = 0; // counter
        for (int num : nums) {
            if (num == can1) {
                f1++;
            } else if (num == can2) {
                f2++;
            } else if (f1 == 0) {
                can1 = num;
                f1 = 1;
            } else if (f2 == 0) {
                can2 = num;
                f2 = 1;
            } else {
                f1--;
                f2--;
            }
        }
        // double check
        f1 = 0;
        f2 = 0;
        for (int num : nums) {
            if (num == can1) {
                f1++;
            } else if (num == can2) {
                f2++;
            }
        }
        if (f1 > (double) n / 3) {
            result.add(can1);
        }
        if (f2 > (double) n / 3) {
            result.add(can2);
        }
        return result;
    }

    private List<Integer> mytry(int[] nums) {
        // use HashMap: O(N) time and O(N) space
        int n = nums.length;
        List<Integer> result = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>(); // <value, freq>
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for (int key : map.keySet()) {
            if (map.get(key) > (double) n / 3) {
                result.add(key);
            }
        }
        return result;
    }
}

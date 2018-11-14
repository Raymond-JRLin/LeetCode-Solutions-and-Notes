// 169. Majority Element
// DescriptionHintsSubmissionsDiscussSolution
// Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.
//
// You may assume that the array is non-empty and the majority element always exist in the array.
//
// Example 1:
//
// Input: [3,2,3]
// Output: 3
// Example 2:
//
// Input: [2,2,1,1,1,2,2]
// Output: 2


class Solution {
    public int majorityElement(int[] nums) {

        // basic method to record the frequency of numbers - O(n) time/space
        // return my_try(nums);

        // return method2(nums);

        // voting candidate method
        return method3(nums);
    }

    private int method3(int[] nums) {
        int candidate = nums[0];
        int count = 0;
        for (int num : nums) {
            if (count == 0) {
                candidate = num;
                count++;
            } else if (num == candidate) {
                count++;
            } else {
                count--;
            }
        }
        // or we can exam again though problem says the majority element always exist
        count = 0;
        for (int num : nums) {
            if (candidate == num) {
                count++;
            }
        }
        if (count > nums.length / 2) {
            return candidate;
        } else {
            return -1;
        }
    }

    private int method2(int[] nums) {
        // we can also do sorting, and then the middle would be the majority number
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }

    private int my_try(int[] nums) {
        // use HashMap to record values and their frequency
        // O(N) time and O(N) space
        Map<Integer, Integer> map = new HashMap<>(); // <number, frequency>
        for (int num : nums) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
            // and we can check frequency here like
            if (map.get(num) > nums.length / 2) {
                return num;
            }
        }
        // int n = nums.length;
        // for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
        //     if (entry.getValue() > n / 2) {
        //         return entry.getKey();
        //     }
        // }
        return -1;
    }
}

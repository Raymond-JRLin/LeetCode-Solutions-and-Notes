// 350. Intersection of Two Arrays II
// DescriptionHintsSubmissionsDiscussSolution
// Given two arrays, write a function to compute their intersection.
//
// Example 1:
//
// Input: nums1 = [1,2,2,1], nums2 = [2,2]
// Output: [2,2]
// Example 2:
//
// Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
// Output: [4,9]
// Note:
//
// Each element in the result should appear as many times as it shows in both arrays.
// The result can be in any order.
// Follow up:
//
// What if the given array is already sorted? How would you optimize your algorithm?
// What if nums1's size is small compared to nums2's size? Which algorithm is better?
// What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements into the memory at once?



class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) {
            return new int[0];
        }

        // we can use HashMap to record, but it's complicated to do the whole process, especially we need to pay much more attention, like dealing with 2 maps and get temporary array. This would cause a lot of space for 2 maps
        // improvement: use List to replace 2nd map so we can don't need to care 2nd map and be easier to handles final result
        // return my_try(nums1, nums2);

        // I 中使用 BS 去找第二个数组中是否有， 这题行不通， 因为有多个重复， 我们也要全部输出， 但是 BS 只能算出是否有， 但不知道有没有， 譬如 nums1 中有 2 个重复的值， 但是 nums2 中只有一个一样的， 此时 BS 第二个重复值的时候， 也依然会输出
        return method2_sort2pointer(nums1, nums2);
    }

    private int[] method2_sort2pointer(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int i = 0;
        int j = 0;
        List<Integer> list = new ArrayList<>();
        while (i < nums1.length && j < nums2.length) {
            // 注意交错可能是多个交错， 并且中间有无交错的， 所以要去找每一个
            if (nums1[i] == nums2[j]) {
                list.add(nums1[i]);
                i++;
                j++;
            } else if (nums1[i] < nums2[j]) {
                i++;
            } else {
                j++;
            }
        }
        if (list.size() == 0) {
            return new int[0];
        }
        int[] result = new int[list.size()];
        int index = 0;
        for (int num : list) {
            result[index++] = num;
        }
        return result;
    }

    private int[] my_try(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums1) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }
        // Map<Integer, Integer> inter = new HashMap<>();
        // for (int num : nums2) {
        //     if (map.containsKey(num)) {
        //         // nums2 may not have the same value as much as nums1 has
        //         if (inter.containsKey(num)) {
        //             inter.put(num, inter.get(num) + 1);
        //         } else {
        //             inter.put(num, 1);
        //         }
        //         // delete freq in nums1's map
        //         map.put(num, map.get(num) - 1);
        //         if (map.get(num) == 0) {
        //             map.remove(num);
        //         }
        //     }
        // }
        // int[] temp = new int[Math.min(nums1.length, nums2.length)];
        // int index = 0;
        // int len = 0;
        // for (Map.Entry<Integer, Integer> entry : inter.entrySet()) {
        //     int num = entry.getKey();
        //     int freq = entry.getValue();
        //     len += freq;
        //     while (freq > 0) {
        //         temp[index] = num;
        //         index++;
        //         freq--;
        //     }
        // }
        // int[] result = new int[len];
        // for (int i = 0; i < len; i++) {
        //     result[i] = temp[i];
        // }

        // use List to make an improvement
        List<Integer> list = new ArrayList<>();
        for (int num : nums2) {
            if (map.containsKey(num)) {
                list.add(num);
                map.put(num, map.get(num) - 1);
                if (map.get(num) == 0) {
                    map.remove(num);
                }
            }
        }
        int[] result = new int[list.size()];
        int i = 0;
        for (int num : list) {
            result[i++] = num;
        }
        return result;
    }
}

// 1122. Relative Sort Array
// DescriptionHintsSubmissionsDiscussSolution
//
// Given two arrays arr1 and arr2, the elements of arr2 are distinct, and all elements in arr2 are also in arr1.
//
// Sort the elements of arr1 such that the relative ordering of items in arr1 are the same as in arr2.  Elements that don't appear in arr2 should be placed at the end of arr1 in ascending order.
//
//
//
// Example 1:
//
// Input: arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
// Output: [2,2,2,1,4,3,3,9,6,7,19]
//
//
//
// Constraints:
//
//     arr1.length, arr2.length <= 1000
//     0 <= arr1[i], arr2[i] <= 1000
//     Each arr2[i] is distinct.
//     Each arr2[i] is in arr1.
//
// Seen this question in a real interview before?
//
//     Difficulty:Easy


class Solution {
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        if (arr2 == null || arr2.length == 0) {
            return arr1;
        }

        // return mytry(arr1, arr2);

        // return method2(arr1, arr2);

        return method3(arr1, arr2);
    }

    private int[] method3(int[] nums1, int[] nums2) {
        // 用了 map， 还要去 sort key， 就要想到用 TreeMap 而不是把 key 拿出来 sort 再搞
        TreeMap<Integer, Integer> map = new TreeMap<>(); // <num, freq>
        for (int num : nums1) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        int i = 0;
        for (int num : nums2) {
            for (int j = 0; j < map.get(num); j++) {
                nums1[i++] = num;
            }
            map.remove(num);
        }

        for (int num : map.keySet()) {
            for (int j = 0; j < map.get(num); j++) {
                nums1[i++] = num;
            }
        }
        return nums1;
    }

    private int[] method2(int[] nums1, int[] nums2) {
        // 直接利用条件 sort nums1
        Map<Integer, Integer> map = new HashMap<>(); // <num in nums2, order>
        for (int j = 0; j < nums2.length; j++) {
            map.put(nums2[j], j);
        }
        // sort: 按照 nums2 中出现的 index/order， 不然就 + 1000， 按照 val 值大小
        List<Pair> list = new ArrayList<>();
        for (int num : nums1) {
            list.add(new Pair(num, map.getOrDefault(num, num + 1000)));
        }
        Collections.sort(list);

        for (int i = 0; i < nums1.length; i++) {
            nums1[i] = list.get(i).val;
        }
        return nums1;
    }
    private class Pair implements Comparable<Pair> {
        int val;
        int order;

        Pair(int v, int o) {
            this.val = v;
            this.order = o;
        }

        @Override
        public int compareTo(Pair o2) {
            return Integer.compare(this.order, o2.order);
        }
    }

    private int[] mytry(int[] nums1, int[] nums2) {
        // brute force and straightforward
        // 用了 map， 还要去 sort key， 就要想到用 TreeMap 而不是把 key 拿出来 sort， 再搞
        int n = nums1.length;
        int m = nums2.length;
        Map<Integer, Integer> map = new HashMap<>(); // <num, freq>
        for (int num : nums1) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        int i = 0;
        for (int num : nums2) {
            for (int j = 0; j < map.get(num); j++) {
                nums1[i++] = num;
            }
            map.remove(num);
        }

        List<Integer> rest = new ArrayList<>(map.keySet());
        Collections.sort(rest);
        for (int num : rest) {
            for (int j = 0; j < map.get(num); j++) {
                nums1[i++] = num;
            }
            map.remove(num);
        }

        return nums1;
    }
}

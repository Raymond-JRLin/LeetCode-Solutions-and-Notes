// 658. Find K Closest Elements
// DescriptionHintsSubmissionsDiscussSolution
// Given a sorted array, two integers k and x, find the k closest elements to x in the array. The result should also be sorted in ascending order. If there is a tie, the smaller elements are always preferred.
//
// Example 1:
// Input: [1,2,3,4,5], k=4, x=3
// Output: [1,2,3,4]
// Example 2:
// Input: [1,2,3,4,5], k=4, x=-1
// Output: [1,2,3,4]
// Note:
// The value k is positive and will always be smaller than the length of the sorted array.
// Length of the given array is positive and will not exceed 104
// Absolute value of elements in the array and x will not exceed 104
// UPDATE (2017/9/19):
// The arr parameter had been changed to an array of integers (instead of a list of integers). Please reload the code definition to get the latest changes.



class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {

        // return mytry(arr, k, x);

        // return mytry_2(arr, k, x);

        // return method2(arr, k, x);

        // return method3(arr, k, x);

        return method4(arr, k, x);
    }

    private List<Integer> method4(int[] nums, int k, int target) {
        // kinda merge sort idea: O(N) time
        // ref: https://leetcode.com/problems/find-k-closest-elements/discuss/106424/Java-4-Liner-and-O(n)-Time-Solution
        List<Integer> greater = new ArrayList<>();
        List<Integer> less = new ArrayList<>();
        for (int num : nums) {
            if (num > target) {
                greater.add(num);
            } else {
                less.add(num);
            }
        }
        // now both lists are ascending order, but we should reverse less list to make element closer to x to be front
        Collections.reverse(less);
        // record the result in 2 lists, since we should output them as ascending order
        List<Integer> lessResult = new ArrayList<>();
        List<Integer> greaterResult = new ArrayList<>();
        int i = 0;
        int j = 0;
        int n = less.size();
        int m = greater.size();
        while (k-- > 0) {
            if (i < n && j < m) {
                if (target - less.get(i) <= greater.get(j) - target) {
                    lessResult.add(less.get(i));
                    i++;
                } else {
                    greaterResult.add(greater.get(j));
                    j++;
                }
            } else if (i < n) {
                lessResult.add(less.get(i));
                i++;
            } else {
                greaterResult.add(greater.get(j));
                j++;
            }
        }
        // reverse lessResult to supposed ascending order
        Collections.reverse(lessResult);
        // add greater resut values into final list
        lessResult.addAll(greaterResult);
        return lessResult;
    }

    private List<Integer> method3(int[] nums, int k, int x) {
        int n = nums.length;
        // binary search on the start index of final result
        // ref: https://discuss.leetcode.com/topic/99281/o-log-n-java-1-line-o-log-n-k-ruby
        int start = 0;
        int end = n - k;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (Math.abs(nums[mid] - x) > Math.abs(nums[mid + k] - x)) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            result.add(nums[start++]);
        }
        return result;
    }

    private List<Integer> method2(int[] arr, int k, int x) {
        // O(nlogn) but simple: sort arr as the diff between values and x
        int n = arr.length;
        Integer[] nums = new Integer[n];
        for (int i = 0; i < n; i++) {
            nums[i] = (Integer) arr[i];
        }
        Arrays.sort(nums, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Math.abs(o1 - x) - Math.abs(o2 - x);
            }
        });
        List<Integer> result = new ArrayList<>();
        int index = 0;
        for (int i = 0; i < k; i++) {
            result.add((int) nums[index++]);
        }
        Collections.sort(result);
        return result;
    }

    private List<Integer> mytry_2(int[] arr, int k, int x) {
        // 2 pointers + BS: O(logn + k) time, improved mytry method
        // ref: // concise version: https://leetcode.com/problems/find-k-closest-elements/discuss/106439/JavaC++-Very-simple-binary-search-solution
        int n = arr.length;
        int index = Arrays.binarySearch(arr, x);
        if (index < 0) {
            index = - (index + 1);
        }
        // to find
        int i = index - 1;
        int j = index; // in case all numbers are less than x
        while (k > 0) {
            if (i < 0 || (j < n && Math.abs(arr[j] - x) < Math.abs(arr[i] - x))) {
                // pick later number
                j++;
            } else {
                i--;
            }
            k--;
        }
        List<Integer> result = new ArrayList<>();
        for (int l = i + 1; l < j; l++) {
            // start from i + 1, 因为在 while 中我们是查到了一个需要的值才 ++ 或 -- 的， 所以 i j 的位置都不能包括进去
            result.add(arr[l]);
        }
        return result;
    }

    private List<Integer> mytry(int[] arr, int k, int x) {
        int n = arr.length;
        List<Integer> result = new ArrayList<>();
        if (x <= arr[0]) {
            int index = 0;
            for (int i = 0; i < k; i++) {
                result.add(arr[index++]);
            }
            return result;
        }
        if (x >= arr[n - 1]) {
            int index = n - k;
            for (int i = 0; i < k; i++) {
                // output has a increasing order
                result.add(arr[index++]);
            }
            return result;
        }
        // my idea: use bs to find the most closest one, and use 2 pointers to move left and right - O(logn + k + klogk), finally to sort the result array would cause klogk, so we can improve this by another version using this same idea
        int index = bs(arr, x); // index of closest number
        result.add(arr[index]);
        k--;
        int left = index - 1;
        int right = index + 1;
        while (left >= 0 && right < n && k > 0) {
            if (Math.abs(arr[left] - x) <= Math.abs(arr[right] - x)) {
                result.add(arr[left]);
                left--;
                k--;
            } else {
                result.add(arr[right]);
                right++;
                k--;
            }
        }
        while (k > 0 && left >= 0) {
            result.add(arr[left]);
            left--;
            k--;
        }
        while (k > 0 && right < n) {
            result.add(arr[right]);
            right++;
            k--;
        }
        Collections.sort(result);
        return result;
    }
    private int bs(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (Math.abs(nums[start] - target) < Math.abs(nums[end] - target)) {
            return start;
        } else {
            return end;
        }
    }
}

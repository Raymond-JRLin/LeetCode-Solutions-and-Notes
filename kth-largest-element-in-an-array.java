// 215. Kth Largest Element in an Array
// DescriptionHintsSubmissionsDiscussSolution
// Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.
//
// Example 1:
//
// Input: [3,2,1,5,6,4] and k = 2
// Output: 5
// Example 2:
//
// Input: [3,2,3,1,2,4,5,5,6] and k = 4
// Output: 4
// Note:
// You may assume k is always valid, 1 ≤ k ≤ array's length.


class Solution {
    public int findKthLargest(int[] nums, int k) {

        // return mytry(nums, k);

        // return method2(nums, k);

        // return method2_2(nums, k);

        return method3(nums, k);
    }

    private int method3(int[] nums, int k) {
        // quickselect: O(n) time (average) / O(n ^ 2) time (worst), O(1) space
        int n = nums.length;
        int start = 0;
        int end = n - 1;
        int index = n - k;
        while (start < end) {
            int pivot = partition(nums, start, end); // pivot is index
            if (pivot < index) {
                start = pivot + 1;
            } else if (pivot > index) {
                end = pivot - 1;
            } else {
                return nums[pivot];
            }
        }
        return nums[start];
    }
    private int partition(int[] nums, int start, int end) {
        // 3 way partition
        int pivot = start; // pivot is index
        while (start <= end) {
            while (start <= end && nums[start] <= nums[pivot]) {
                start++;
            }
            while (start <= end && nums[end] > nums[pivot]) {
                end--;
            }
            if (start > end) {
                break;
            }
            swap(nums, start, end);
        }
        swap(nums, pivot, end);
        return end; // already swapped pivot and end, so return end index
    }
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private int method2_2(int nums[], int k) {
        // improved PQ method with size of k: O(nlogk) time, O(k) space
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(); // min heap
        for (int num : nums) {
            pq.offer(num);
            if (pq.size() > k) {
                pq.poll(); // poll the min value
            }
        }
        return pq.peek();
    }

    private int method2(int[] nums, int k) {
        // use PQ: O(nlogn) time, O(n) space
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(k, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1; // descending
            }
        }); // max heap
        for (int num : nums) {
            pq.offer(num);
        }
        while (k > 1) {
            pq.poll();
            k--;
        }
        return pq.poll();
    }

    private int mytry(int[] nums, int k) {
        // brute force: O(nlogn)
        Arrays.sort(nums);
        return nums[nums.length - k];
    }
}

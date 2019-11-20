// 315. Count of Smaller Numbers After Self
// DescriptionHintsSubmissionsDiscussSolution
//
// You are given an integer array nums and you have to return a new counts array. The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].
//
// Example:
//
// Input: [5,2,6,1]
// Output: [2,1,1,0]
// Explanation:
// To the right of 5 there are 2 smaller elements (2 and 1).
// To the right of 2 there is only 1 smaller element (1).
// To the right of 6 there is 1 smaller element (1).
// To the right of 1 there is 0 smaller element.
//
// Seen this question in a real interview before?
//
//     Difficulty:Hard


class Solution {class Solution {
    public List<Integer> countSmaller(int[] nums) {
        if (nums == null || nums.length == 0) {
            return Collections.emptyList();
        }

        // return method1(nums);

        // return method2(nums);

        return method3(nums);
    }

    private List<Integer> method3(int[] nums) {
        // 另一种用自己建的带原 index 的 object， 这样就类似正常的 merge sort， 不要去记 index， 而是直接包含其中
        int n = nums.length;

        Item[] items = new Item[n];
        for (int i = 0; i < n; i++) {
            items[i] = new Item(nums[i], i);
        }
        int[] count = new int[n];
        itemMergeSort(items, 0, n - 1, count);

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            result.add(count[i]);
        }
        return result;
    }
    private void itemMergeSort(Item[] items, int start, int end, int[] count) {
        if (start >= end) {
            return;
        }
        int mid = start + (end - start) / 2;
        itemMergeSort(items, start, mid, count);
        itemMergeSort(items, mid + 1, end, count);
        itemMerge(items, start, end, count);
    }
    private void itemMerge(Item[] items, int start, int end, int[] count) {
        int mid = start + (end - start) / 2;
        int left = start;
        int right = mid + 1;
        Item[] sorted = new Item[end + 1 - start];
        int index = 0;
        int smaller = 0;
        while (left <= mid || right <= end) {
            if (left <= mid && right <= end) {
                if (items[left].val <= items[right].val) {
                    count[items[left].index] += smaller;
                    sorted[index++] = items[left++];
                } else {
                    smaller++;
                    sorted[index++] = items[right++];
                }
            } else if (left <= mid) {
                count[items[left].index] += smaller;
                sorted[index++] = items[left++];
            } else if (right <= end) {
                smaller++;
                sorted[index++] = items[right++];
            }
        }
        for (int i = 0; i < sorted.length; i++) {
            items[i + start] = sorted[i];
        }
    }
    private class Item {
        int val;
        int index;

        Item(int val, int index) {
            this.val = val;
            this.index = index;
        }
    }

    private List<Integer> method2(int[] nums) {
        int n = nums.length;
        // merge sort 是需要额外空间来记录排序后的数组， 然后覆盖原来的
        // 那么排序之后要怎么知道原来的数在哪儿呢？ 知道这个是为了正确计算 count[], 不然 sort 之后计算的 count 不会加到正确的位置
        // 所以 indices[i] 指的是完成这一轮排序后， i 位置上的数 (我们没有真正记录排序后的数组) 在最原数组 nums[] 中的位置是 indices[i]
        int[] indices = new int[n];
        for (int i = 0; i < n; i++) {
            indices[i] = i;
        }
        int[] count = new int[n];
        mergeSort(nums, indices, 0, n - 1, count);
        // for (int num : indices) {
        //     System.out.print(num + " ");
        // }
        // System.out.println();
        // for (int num : count) {
        //     System.out.print(num + " ");
        // }
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            result.add(count[i]);
        }
        return result;
    }
    private void mergeSort(int[] nums, int[] indices, int start, int end, int[] count) {
        // System.out.println("merge sort " + start + " to " + end);
        if (start >= end) {
            return;
        }
        int mid = start + (end - start) / 2;
        mergeSort(nums, indices, start, mid, count);
        mergeSort(nums, indices, mid + 1, end, count);
        merge(nums, indices, start, end, count);
    }
    private void merge(int[] nums, int[] indices, int start, int end, int[] count) {
        // System.out.println("sort nums from " + start + " -> " + end);
        int mid = start + (end - start) / 2;
        int left = start;
        int right = mid + 1;
        int[] newIndices = new int[end + 1 - start];
        int index = 0;
        int smaller = 0;
        while (left <= mid || right <= end) {
            // System.out.println("left/right: " + left + " : " + right);
            if (left <= mid && right <= end) {
                if (nums[indices[right]] >= nums[indices[left]]) {
                    count[indices[left]] += smaller;
                    // System.out.println(nums[indices[left]] + " get smaller count: " + smaller + ", total: " + count[indices[left]]);
                    newIndices[index++] = indices[left++];
                } else {
                    smaller++;
                    // System.out.println(nums[indices[right]] + " is smaller, get count: " + smaller);
                    newIndices[index++] = indices[right++];
                }
            } else if (left <= mid) {
                count[indices[left]] += smaller;
                // System.out.println(nums[indices[left]] + " get smaller count: " + smaller + ", total: " + count[indices[left]]);
                newIndices[index++] = indices[left++];
            } else if (right <= end) {
                smaller++;
                newIndices[index++] = indices[right++];
            }
        }
        // System.out.print("newIndices[]: ");
        // for (int num : newIndices) {
        //     System.out.print(num + " ");
        // }
        // System.out.println();
        // System.out.print("indices[]: ");
        // for (int num : indices) {
        //     System.out.print(num + " ");
        // }
        // System.out.println();
        for (int i = 0; i < newIndices.length; i++) {
            indices[i + start] = newIndices[i];
        }
    }

    private List<Integer> method1(int[] nums) {
        int n = nums.length;
        List<Integer> result = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        for (int i = n - 1; i >= 0; i--) {
            int index = bs(list, nums[i]);
            result.add(0, index);
            list.add(index, nums[i]);
        }
        return result;
    }
    private int bs(List<Integer> list, int num) {
        // 找的是 num 要插入的 index
        // 也就是比 num 大的数中最小的那个数的 index
        if (list.isEmpty()) {
            return 0;
        }
        int start = 0;
        int end = list.size() - 1;
        if (num > list.get(end)) {
            return end + 1;
        }
        if (num <= list.get(start)) {
            return start;
        }
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (list.get(mid) < num) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        return start;
    }
}

// 315
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


class Solution {
    public List<Integer> countSmaller(int[] nums) {
        if (nums == null || nums.length == 0) {
            return Collections.emptyList();
        }

        // return mytry(nums);

        // return method1(nums);

        // return method2(nums);

        return method3(nums);
    }

    private List<Integer> method3(int[] nums) {
        // use merge sort, 在 merge sort 的过程中不断去累加记录右边比左边小的个数。
        int n = nums.length;
        int[] counts = new int[n]; // 记录比自己右边小的个数
        int[] indexes = new int[n]; // 记录排序后的顺序的 index
        // initialization
        for (int i = 0; i < n; i++) {
            indexes[i] = i;
        }
        mergeSort(nums, counts, indexes, 0, n - 1);

        List<Integer> result = new ArrayList<>();
        for (int count : counts) {
            result.add(count);
        }
        return result;
    }
    private void mergeSort(int[] nums, int[] counts, int[] indexes, int start, int end) {
        if (start >= end) {
            return;
        }
        int mid = start + (end - start) / 2;
        mergeSort(nums, counts, indexes, start, mid);
        mergeSort(nums, counts, indexes, mid + 1, end);
        merge(nums, counts, indexes, start, end);
    }
    private void merge(int[] nums, int[] counts, int[] indexes, int start, int end) {
        int mid = start + (end - start) / 2;
        int left = start; // 左边 index pointer
        int right = mid + 1; // 右边 index pointer
        int sorted = 0; // sorted 的 index array 的 index pointer
        int smaller = 0; // 右边小的个数
        int[] newIndexes = new int[end - start + 1];
        // 记住 left 和 right 都不是真正的 index， 而是要去 indexes 里面拿
        while (left <= mid || right <= end) {
            if (left <= mid && right <= end) {
                if (nums[indexes[right]] < nums[indexes[left]]) {
                    // 右边更小， 增加 smaller
                    newIndexes[sorted++] = indexes[right++];
                    smaller++;
                } else {
                    // 左边更小， 要把前面记录下的右边小的数都加上去
                    counts[indexes[left]] += smaller;
                    newIndexes[sorted++] = indexes[left++];
                }
            } else if (left <= mid) {
                counts[indexes[left]] += smaller;
                newIndexes[sorted++] = indexes[left++];
            } else {
                newIndexes[sorted++] = indexes[right++];
                smaller++;
            }
        }
        // copy newIndexes array to original indexes array
        for (int i = start; i <= end; i++) {
            indexes[i] = newIndexes[i - start];
        }
    }

    private List<Integer> method2(int[] nums) {
        // build a BST
        // ref: https://leetcode.com/problems/count-of-smaller-numbers-after-self/discuss/76580/9ms-short-Java-BST-solution-get-answer-when-building-BST
        // 这个方法我感觉很巧妙， 但是其实也没那么好， 因为这个 BST 并不是 balanced， 而总是加到最末尾， 这样树会非常的 skew， 最差情况是 O(N ^ 2)
        int n = nums.length;
        List<Integer> result = new ArrayList<>();
        Node root = null;
        for (int i = n - 1; i >= 0; i--) {
            // 从后往前看， 记录的是这个数前面（左边）的比它小的数， 这么做的原因我猜是因为用了树， 可以利用树的性质来做
            // 当插入的时候， 向右走意味着当前的数比它的 parent 的大， 那么此时 parent 中记录的某个信息就应该是比它小的个数， 但是向右走又不能够 check 到 parent 的左子树中的个数， 所以记录的要是左边的个数， 也就是在 insert 的过程中， 比当前的 node 小的个数
            root = insert(nums[i], result, i, root, 0);
        }
        return result;
    }
    private Node insert(int num, List<Integer> result, int i, Node root, int preSum) {
        if (root == null) {
            root = new Node(num, 0);
            result.add(0, preSum);
        } else if (root.val == num) {
            // duplicates
            root.duplicate += 1;
            result.add(0, preSum + root.leftCount);
        } else if (root.val > num) {
            // go left
            root.leftCount += 1; // record left subtree #node to current root node
            root.left = insert(num, result, i, root.left, preSum); // insert left child
        } else {
            // go right, insert right child, 此时 right child 记录的 leftCount 比它小的个数是 root 自身和它的左子树
            root.right = insert(num, result, i, root.right, preSum + root.leftCount + root.duplicate);
        }
        return root;
    }
    private class Node{
        private Node left, right;
        private int val, leftCount, duplicate;
        private Node(int v, int c) {
            val = v; // number's value
            leftCount = c; // # node on left subtree (在原数组中， 在这个数前面比它小的数的个数)
            duplicate = 1; // 判断重复数
        }
    }

    private List<Integer> method1(int[] nums) {
        // 要想到前面 LIS 这题， 同样的不能够排序， 那要如何 BS？ 就是如果从后往前看， 按照从小到大的顺序排列的话， 就可以找到有几个比它小， 也就是他的位置数
        // ref: https://leetcode.com/problems/count-of-smaller-numbers-after-self/discuss/76576/My-simple-AC-Java-Binary-Search-code
        int n = nums.length;
        int[] arr = new int[n];
        List<Integer> list = new ArrayList<>(); // ascending sorted list from back
        for (int i = n - 1; i >= 0; i--) {
            int index = getIndex(list, nums[i]); // index to insert this number
            arr[i] = index; // 后面比它小的数的个数， 就是要插入的 index
            list.add(index, nums[i]);
        }
        List<Integer> result = new ArrayList<>();
        for (int num : arr) {
            result.add(num);
        }
        return result;
    }
    private int getIndex(List<Integer> list, int target) {
        // 注意相等的情况， 题目要求是 smaller， 如果是相等则不算， 此时插入的位置就是相等的数的位置
        // e.g. input [-1,-1] => output [0,0]
        if (list == null || list.size() == 0) {
            return 0;
        }
        int start = 0;
        int end = list.size() - 1;
        if (target > list.get(end)) {
            return end + 1;
        }
        if (target <= list.get(start)) {
            return start;
        }
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (list.get(mid) < target) {
                start = mid;
            } else {
                // 相等仍要向前找
                end = mid;
            }
        }
        if (list.get(start) >= target) {
            return start;
        } else if (target <= list.get(end)) {
            return end;
        } else {
            return end + 1;
        }
    }

    private List<Integer> mytry(int[] nums) {
        // brute force: O(N ^ 2), TLE
        int n = nums.length;
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int count = 0;
            for (int j = i + 1; j < n; j++) {
                if (nums[j] < nums[i]) {
                    count++;
                }
            }
            result.add(count);
        }
        return result;
    }
}

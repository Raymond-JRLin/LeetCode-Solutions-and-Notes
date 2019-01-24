// 307. Range Sum Query - Mutable
// DescriptionHintsSubmissionsDiscussSolution
// Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.
//
// The update(i, val) function modifies nums by updating the element at index i to val.
//
// Example:
//
// Given nums = [1, 3, 5]
//
// sumRange(0, 2) -> 9
// update(1, 2)
// sumRange(0, 2) -> 8
// Note:
//
// The array is only modifiable by the update function.
// You may assume the number of calls to update and sumRange function is distributed evenly.


class NumArray {

    SegNode root;

    public NumArray(int[] nums) {
        root = buildSegTree(nums, 0, nums.length - 1);
    }
    private SegNode buildSegTree(int[] nums, int start, int end) {
        if (start > end) {
            return null;
        }
        SegNode curr = new SegNode(start, end);
        if (start == end) {
            curr.sum = nums[start];
        } else {
            int mid = start + (end - start) / 2;
            SegNode left = buildSegTree(nums, start, mid);
            SegNode right = buildSegTree(nums, mid + 1, end);
            curr.left = left;
            curr.right = right;
            curr.sum = left.sum + right.sum;
        }
        return curr;
    }

    public void update(int i, int val) {
        update(i, val, root);
    }
    private void update(int i, int val, SegNode root) {
        if (root.start == root.end) {
            root.sum = val;
        } else {
            int mid = root.start + (root.end - root.start) / 2;
            if (i <= mid) {
                update(i, val, root.left);
            } else {
                update(i, val, root.right);
            }
            root.sum = root.left.sum + root.right.sum;
        }
    }

    public int sumRange(int i, int j) {
        return sumRange(i, j, root);
    }
    private int sumRange(int i, int j, SegNode root) {
        if (i == root.start && j == root.end) {
            return root.sum;
        } else {
            int mid = root.start + (root.end - root.start) / 2;
            if (j <= mid) {
                // 要查询的区间 [i, j] 全在左边
                return sumRange(i, j, root.left);
            } else if (i > mid) {
                // 要查询的区间 [i, j] 全在右边
                return sumRange(i, j, root.right);
            } else {
                // 跨域
                return sumRange(i, mid, root.left) + sumRange(mid + 1, j, root.right);
            }
        }
    }
}
class SegNode {
    int start, end;
    SegNode left, right;
    int sum;
    public SegNode(int start, int end) {
        this.start = start;
        this.end = end;
        left = right = null;
        sum = 0;
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(i,val);
 * int param_2 = obj.sumRange(i,j);
 */

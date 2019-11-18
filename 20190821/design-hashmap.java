// 706. Design HashMap
// DescriptionHintsSubmissionsDiscussSolution
//
// Design a HashMap without using any built-in hash table libraries.
//
// To be specific, your design should include these functions:
//
//     put(key, value) : Insert a (key, value) pair into the HashMap. If the value already exists in the HashMap, update the value.
//     get(key): Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key.
//     remove(key) : Remove the mapping for the value key if this map contains the mapping for the key.
//
//
// Example:
//
// MyHashMap hashMap = new MyHashMap();
// hashMap.put(1, 1);
// hashMap.put(2, 2);
// hashMap.get(1);            // returns 1
// hashMap.get(3);            // returns -1 (not found)
// hashMap.put(2, 1);          // update the existing value
// hashMap.get(2);            // returns 1
// hashMap.remove(2);          // remove the mapping for 2
// hashMap.get(2);            // returns -1 (not found)
//
//
// Note:
//
//     All keys and values will be in the range of [0, 1000000].
//     The number of operations will be in the range of [1, 10000].
//     Please do not use the built-in HashMap library.
//
// Seen this question in a real interview before?
//
//     Difficulty:Easy


class MyHashMap {

    ListNode[] nums;

    /** Initialize your data structure here. */
    public MyHashMap() {
        nums = new ListNode[10000];

    }

    /** value will always be non-negative. */
    public void put(int key, int value) {
        int index = hash(key);
        if (nums[index] == null) {
            nums[index] = new ListNode(-1, -1);
        }
        ListNode node = findNode(nums[index], key);
        if (node.next == null) {
            // new k-v
            node.next = new ListNode(key, value);
        } else {
            // update existing k-v
            node.next.val = value;
        }
    }

    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
    public int get(int key) {
        int index = hash(key);
        if (nums[index] == null) {
            return -1;
        }
        ListNode node = findNode(nums[index], key);
        if (node.next == null) {
            return -1;
        } else {
            return node.next.val;
        }
    }

    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
    public void remove(int key) {
        int index = hash(key);
        if (nums[index] == null) {
            return;
        }
        ListNode node = findNode(nums[index], key);
        if (node.next == null) {
            return;
        } else {
            node.next = node.next.next;
        }
    }

    private ListNode findNode(ListNode head, int key) {
        ListNode curr = head;
        ListNode prev = null;
        while (curr != null && curr.key != key) {
            prev = curr;
            curr = curr.next;
        }
        return prev;
    }
    private int hash(int key) {
        return Integer.hashCode(key) % nums.length;
    }

    class ListNode {
        int key;
        int val;
        ListNode next;

        ListNode(int k, int v) {
            this.key = k;
            this.val = v;
            this.next = null;
        }
    }
}

// class MyHashMap {

//     int[] nums;

//     /** Initialize your data structure here. */
//     public MyHashMap() {
//         nums = new int[1000001];
//         Arrays.fill(nums, -1);
//     }

//     /** value will always be non-negative. */
//     public void put(int key, int value) {
//         nums[key] = value;
//     }

//     /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
//     public int get(int key) {
//         return nums[key];
//     }

//     /** Removes the mapping of the specified value key if this map contains a mapping for the key */
//     public void remove(int key) {
//         nums[key] = -1;
//     }
// }

/**
 * Your MyHashMap object will be instantiated and called as such:
 * MyHashMap obj = new MyHashMap();
 * obj.put(key,value);
 * int param_2 = obj.get(key);
 * obj.remove(key);
 */

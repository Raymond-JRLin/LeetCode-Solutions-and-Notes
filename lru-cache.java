// 146. LRU Cache
// DescriptionHintsSubmissionsDiscussSolution
// Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and put.
//
// get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
// put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.
//
// The cache is initialized with a positive capacity.
//
// Follow up:
// Could you do both operations in O(1) time complexity?
//
// Example:
//
// LRUCache cache = new LRUCache( 2 /* capacity */ );
//
// cache.put(1, 1);
// cache.put(2, 2);
// cache.get(1);       // returns 1
// cache.put(3, 3);    // evicts key 2
// cache.get(2);       // returns -1 (not found)
// cache.put(4, 4);    // evicts key 1
// cache.get(1);       // returns -1 (not found)
// cache.get(3);       // returns 3
// cache.get(4);       // returns 4
//
//
// Seen this question in a real interview before?
// Difficulty:Medium


class LRUCache {

    // construct our own class to store the Node type
    private class Node {
        int key;
        int val;
        Node pre;
        Node next;
        public Node(int k, int v) {
            key = k;
            val = v;
            pre = null;
            next = null;
        }
    }
    // we need to set head and tail, which are not included in the LinkedList
    private Node head = new Node(-1, -1);
    private Node tail = new Node(-1, -1);
    private HashMap<Integer, Node> hash = new HashMap<>();
    private int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        // initialize the LinkedList, head points to tail
        head.next = tail;
        tail.pre = head;
    }

    public int get(int key) {
        // write your code here
        if (!hash.containsKey(key)) {
            return -1;
        }
        // if there exists this node, we need to get this node first
        Node node = hash.get(key);
        // remove this node from current position
        node.pre.next = node.next;
        node.next.pre = node.pre;
        // move it to the tail
        moveToTail(node);
        return hash.get(key).val;
    }

    public void put(int key, int value) {
        // if there exists such node, update its value
        // we use key, so the LinkedList will update positions, move this updated node to the tail since we operate this one
        if (get(key) != -1) {
            hash.get(key).val = value;
            return;
        }
        // if it's full, delete the 1st node
        if (hash.size() == capacity) {
            hash.remove(head.next.key); // remember delete the corresponding key in the hash map
            // that's why we need to record key and value in the node at the same time, then we can get the key from the node to be used in the HashMap
            head.next = head.next.next;
            head.next.pre = head;
        }
        // no matter whether it's full or not, we need to create a new node, and insert to the last position
        Node newNode = new Node(key, value);
        hash.put(key, newNode); // don't forget to add into hash map
        moveToTail(newNode);
    }
    // everytime when we operate any number, we need to move this node to the tail, so we can write a single method to invoke
    private void moveToTail(Node node) {
        tail.pre.next = node;
        node.next = tail;
        node.pre = tail.pre;
        tail.pre = node;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */

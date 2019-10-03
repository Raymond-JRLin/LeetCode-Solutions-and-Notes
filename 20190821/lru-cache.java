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
    private class Node {
        int key;
        int val;
        Node prev;
        Node next;

        Node(int key, int val) {
            this.key = key;
            this.val = val;
            this.prev = null;
            this.next = null;
        }
    }

    Map<Integer, Node> map;
    Node head;
    Node tail;
    int capacity;

    public LRUCache(int capacity) {
        this.map = new HashMap<>();
        this.head = new Node(-1, -1);
        this.tail = new Node(-1, -1);
        this.capacity = capacity;
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        // 是否存在这个 key
        if (!map.containsKey(key)) {
            return -1;
        }
        // 如果存在
        Node node = map.get(key);
        // 先删除原来的位置
        node.prev.next = node.next;
        node.next.prev = node.prev;
        // move to tail
        moveToTail(node);
        return map.get(key).val;
    }

    public void put(int key, int value) {
        if (get(key) != -1) {
            // 已经存在 => update
            map.get(key).val = value;
            return;
        }
        // 不存在 => create
        // 注意要先 check map capacity 够不够
        if (map.size() == capacity) {
            // 达到上限， remove lease recently used
            map.remove(head.next.key);
            head.next = head.next.next;
            head.next.prev = head;
        }
        // create & move to tail
        Node node = new Node(key, value);
        map.put(key, node);
        moveToTail(node);
    }

    private void moveToTail(Node node) {
        tail.prev.next = node;
        node.next = tail;
        node.prev = tail.prev;
        tail.prev = node;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */

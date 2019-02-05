// 138. Copy List with Random Pointer
// DescriptionHintsSubmissionsDiscussSolution
// A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.
//
// Return a deep copy of the list.
//


/**
 * Definition for singly-linked list with a random pointer.
 * class RandomListNode {
 *     int label;
 *     RandomListNode next, random;
 *     RandomListNode(int x) { this.label = x; }
 * };
 */
public class Solution {
    public RandomListNode copyRandomList(RandomListNode head) {
        if (head == null) {
            return head;
        }
        // ATTENTION: cannot say if (head.next == null) { return;}, because even thought there's only one node, we still need to copy it

        // return mytry(head); // wrong

        // return method2(head);

        return method3(head);
    }

    private RandomListNode method3(RandomListNode head) {
        // copy node after it within original list to double length, and then split modified list: extract copied nodes to a new list and change orignal list back
        RandomListNode curr = head;
        while (curr != null) {
            RandomListNode next = new RandomListNode(curr.label);
            // next.random = curr.random; // we can skip this, if we check since the new node's random nodes should also be in new list
            next.next = curr.next;
            curr.next = next;
            curr = next.next;
        }
        // copy random nodes
        curr = head;
        while (curr != null) {
            if (curr.random != null) {
                // has random node: 新的 node 的 random 指针指向它本体的 random 的下一个， 即复制的 random node
                curr.next.random = curr.random.next;

            }
            curr = curr.next.next;
        }
        // split
        RandomListNode dummy = new RandomListNode(0);
        RandomListNode node = dummy;
        while (head != null) {
            node.next = head.next; // connect to new list
            head.next = head.next.next; // recover original list
            head = head.next;
            node = node.next;
        }
        return dummy.next;
    }

    private RandomListNode method2(RandomListNode head) {
        // 用 Map 把每个 node 放入 key， 对应 new 出来的一样值的 node 放入它的 value: O(N) time and O(N) space
        // ref: https://discuss.leetcode.com/topic/18086/java-o-n-solution
        Map<RandomListNode, RandomListNode> map = new HashMap<>();
        RandomListNode node = head;
        while (node != null) {
            map.put(node, new RandomListNode(node.label));
            node = node.next;
        }
        node = head;
        while (node != null) {
            map.get(node).next = map.get(node.next);
            map.get(node).random = map.get(node.random);
            node = node.next;
        }
        return map.get(head);
    }

    /* 我觉得这个应该是错的， 因为把 next 和 random 都 new 出来了， 其实原本的 next 和 random 都是 list 里面的 node， 只不过是有指针互相指向
    private RandomListNode mytry(RandomListNode head) {
        // use dummy node to create a new list: O(N) time and O(1) space
        RandomListNode dummy = new RandomListNode(0);
        RandomListNode node = dummy;
        while (head != null) {
            // create node in new list
            RandomListNode next = new RandomListNode(head.label);
            if (head.next != null) {
                next.next = new RandomListNode(head.next.label);
            }
            if (head.random != null) {
                next.random = new RandomListNode(head.random.label);
            }
            // move to next on new list
            node.next = next;
            node = next;
            // move to next on original list
            head = head.next;
        }

        return dummy.next;
    }
    */
}

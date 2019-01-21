package leetcode.linkedList;

import org.junit.Test;

/**
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 * <p>
 * 示例：
 * <p>
 * 给定一个链表: 1->2->3->4->5, 和 n = 2.
 * <p>
 * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
 */
public class No19RemoveNthNodeFromEndOfList {
    private int number = 0;

    public ListNode removeNthFromEnd(ListNode head, int n) {
        return removeNthFromEnd3(head, n);
    }


    public ListNode removeNthFromEnd1(ListNode head, int n) {
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;

        int length = 0;
        for (ListNode cur = dummyNode.next; cur != null; cur = cur.next) {
            length++;
        }

        int position = length - n;
        if (position < 0) {
            return null;
        }

        ListNode cur = dummyNode;
        for (int i = 0; i < position; i++) {
            cur = cur.next;
        }

        cur.next = cur.next.next;

        return dummyNode.next;
    }

    private ListNode removeNthFromEnd2(ListNode head, int n) {
        if (head == null) {
            return null;
        }

        head.next = removeNthFromEnd2(head.next, n);
        if (n == ++number) {
            return head.next;
        }
        return head;
    }

    private ListNode removeNthFromEnd3(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode first = dummy;
        ListNode second = dummy;
        // Advances first pointer so that the gap between first and second is n nodes apart
        for (int i = 1; i <= n + 1; i++) {
            first = first.next;
        }
        // Move first to the end, maintaining the gap
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        return dummy.next;
    }

    /**
     * 给定一个链表: 1->2->3->4->5, 和 n = 2.
     * <p>
     * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
     */
    @Test
    public void testCase1() {
        int n = 2;
        ListNode listNode = ListNode.of(new int[]{1, 2, 3, 4, 5});
        ListNode node = removeNthFromEnd(listNode, n);
    }

    /**
     * 给定一个链表: 1, 和 n = 1.
     * <p>
     * 当删除了倒数第二个节点后，链表变为 0.
     */
    @Test
    public void testCase2() {
        int n = 1;
        ListNode listNode = ListNode.of(new int[]{1});
        ListNode node = removeNthFromEnd(listNode, n);
    }

    /**
     * 给定一个链表: 1-> 2, 和 n = 1.
     * <p>
     * 当删除了倒数第二个节点后，链表变为 1.
     */
    @Test
    public void testCase3() {
        int n = 1;
        ListNode listNode = ListNode.of(new int[]{1, 2});
        ListNode node = removeNthFromEnd(listNode, n);
    }

    /**
     * 给定一个链表: 1-> 2, 和 n = 2.
     * <p>
     * 当删除了倒数第二个节点后，链表变为 2.
     */
    @Test
    public void testCase4() {
        int n = 2;
        ListNode listNode = ListNode.of(new int[]{1, 2});
        ListNode node = removeNthFromEnd(listNode, n);
    }
}

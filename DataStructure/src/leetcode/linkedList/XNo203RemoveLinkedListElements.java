package leetcode.linkedList;

import org.junit.Test;

public class XNo203RemoveLinkedListElements {
    public ListNode removeElements(ListNode head, int val) {
        return removeElements1(head, val);
    }

    public ListNode removeElements1(ListNode head, int val) {
        ListNode fakeHead = new ListNode(-1);
        fakeHead.next = head;

        ListNode current = fakeHead.next;
        ListNode prev = fakeHead;

        while (current != null) {
            if (current.val == val) {
                prev.next = current.next;
                current.next = null;
                current = prev.next;
            } else {
                current = current.next;
                prev = prev.next;
            }
        }
        return fakeHead.next;
    }

    public ListNode removeElements2(ListNode head, int val) {
        if (head == null) {
            return null;
        }
        ListNode res = removeElements2(head.next, val);
        if (head.val == val) {
            return res;
        } else {
            head.next = res;
            return head;
        }
    }

    /**
     * 输入: 1->2->6->3->4->5->6, val = 6
     * 输出: 1->2->3->4->5
     */
    @Test
    public void testCase1() {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(6);
        head.next.next.next = new ListNode(3);
        head.next.next.next.next = new ListNode(4);
        head.next.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next.next = new ListNode(6);

        ListNode result = removeElements(head, 6);
    }

    @Test
    public void testCase2() {
        ListNode head = new ListNode(1);

        ListNode listNode = removeElements(head, 1);

    }
}

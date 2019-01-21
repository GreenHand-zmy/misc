package leetcode.linkedList;

import org.junit.Test;

import java.util.Stack;

public class XNo206ReverseLinkedList {
    public ListNode reverseList(ListNode head) {
        return reverseList3(head);
    }

    public ListNode reverseList1(ListNode head) {
        if (head == null) {
            return null;
        }

        Stack<ListNode> listNodeStack = new Stack<>();
        for (ListNode cur = head; cur != null; cur = cur.next) {
            listNodeStack.push(cur);
        }
        ListNode headNode = listNodeStack.pop();
        headNode.next = null;

        ListNode tail = headNode;

        while (!listNodeStack.isEmpty()) {
            ListNode node = listNodeStack.pop();
            node.next = null;
            tail.next = node;
            tail = tail.next;
        }
        return headNode;
    }

    public ListNode reverseList2(ListNode head) {
        ListNode prev = null;
        ListNode cur = head;

        while (cur != null) {
            ListNode nextTemp = cur.next;
            cur.next = prev;
            prev = cur;
            cur = nextTemp;
        }
        return prev;
    }

    public ListNode reverseList3(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode p = reverseList3(head.next);
        head.next.next = head;
        head.next = null;
        return p;
    }

    /**
     * 输入: 1->2->3->4->5->NULL
     * 输出: 5->4->3->2->1->NULL
     */
    @Test
    public void testCase1() {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        ListNode listNode = reverseList(head);
    }
}

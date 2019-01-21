package leetcode.linkedList;

import org.junit.Assert;
import org.junit.Test;

public class XNo21MergeTwoSortedLists {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        return mergeTwoLists2(l1,l2);
    }

    public ListNode mergeTwoLists1(ListNode l1, ListNode l2) {
        ListNode left = l1, right = l2;

        ListNode sortedLinkedList = new ListNode(-1);
        ListNode tail = sortedLinkedList;
        while (left != null || right != null) {
            if (left == null) {
                tail.next = new ListNode(right.val);

                tail = tail.next;
                right = right.next;
            } else if (right == null) {
                tail.next = new ListNode(left.val);

                tail = tail.next;
                left = left.next;
            } else if (left.val < right.val) {
                tail.next = new ListNode(left.val);

                tail = tail.next;
                left = left.next;
            } else {
                tail.next = new ListNode(right.val);

                tail = tail.next;
                right = right.next;
            }
        }
        return sortedLinkedList.next;
    }

    private ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        if (l1.val < l2.val) {
            l1.next = mergeTwoLists2(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists2(l1, l2.next);
            return l2;
        }
    }

    /**
     * 输入：1->2->4, 1->3->4
     * 输出：1->1->2->3->4->4
     */
    @Test
    public void testCase1() {
        ListNode sortedLinkedList1 = ListNode.of(new int[]{1, 2, 4});
        ListNode sortedLinkedList2 = ListNode.of(new int[]{1, 3, 4});

        ListNode exceptResult = ListNode.of(new int[]{1, 1, 2, 3, 4, 4});
        ListNode result = mergeTwoLists(sortedLinkedList1, sortedLinkedList2);

        Assert.assertTrue(ListNode.equals(exceptResult, result));
    }

    @Test
    public void testCase2() {
        ListNode sortedLinkedList1 = ListNode.of(new int[]{5, 8, 9});
        ListNode sortedLinkedList2 = ListNode.of(new int[]{1, 3, 4});

        ListNode exceptResult = ListNode.of(new int[]{1, 3, 4, 5, 8, 9});
        ListNode result = mergeTwoLists(sortedLinkedList1, sortedLinkedList2);

        Assert.assertTrue(ListNode.equals(exceptResult, result));
    }
}

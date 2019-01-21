package leetcode.linkedList;

import org.junit.Assert;
import org.junit.Test;

public class No83RemoveDuplicatesFromSortedList {
    public ListNode deleteDuplicates(ListNode head) {
        return deleteDuplicates2(head);
    }

    public ListNode deleteDuplicates1(ListNode head) {
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;

        for (ListNode cur = dummyNode.next; cur != null; cur = cur.next) {
            ListNode index = cur.next;

            while (index != null && cur.val == index.val) {
                index = index.next;
            }
            cur.next = index;
        }
        return dummyNode.next;
    }

    public ListNode deleteDuplicates2(ListNode head) {
        ListNode current = head;
        while (current != null && current.next != null) {
            if (current.val == current.next.val) {
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }
        return head;
    }

    /**
     * 输入: 1->1->2
     * 输出: 1->2
     */
    @Test
    public void testCase1() {
        ListNode input = ListNode.of(new int[]{1, 1, 2});
        ListNode exceptOutput = ListNode.of(new int[]{1, 2});

        ListNode result = deleteDuplicates(input);
        Assert.assertTrue(ListNode.equals(result, exceptOutput));
    }

    /**
     * 输入: 1->1->2->3->3
     * 输出: 1->2->3
     */
    @Test
    public void testCase2() {
        ListNode input = ListNode.of(new int[]{1, 1, 2, 3, 3});
        ListNode exceptOutput = ListNode.of(new int[]{1, 2, 3});

        ListNode result = deleteDuplicates(input);
        Assert.assertTrue(ListNode.equals(result, exceptOutput));
    }

    /**
     * 输入: 1->1->2->3->3
     * 输出: 1->2->3
     */
    @Test
    public void testCase3() {
        ListNode input = ListNode.of(new int[]{1, 1, 1});
        ListNode exceptOutput = ListNode.of(new int[]{1});

        ListNode result = deleteDuplicates(input);
        Assert.assertTrue(ListNode.equals(result, exceptOutput));
    }
}

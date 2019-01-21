package leetcode.linkedList;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class XNo234PalindromeLinkedList {
    public boolean isPalindrome(ListNode head) {
        return isPalindrome2(head);
    }

    public boolean isPalindrome1(ListNode head) {
        List<ListNode> nodeList = new ArrayList<>();

        ListNode cur = head;
        while (cur != null) {
            ListNode nextTemp = cur.next;
            cur.next = null;
            nodeList.add(cur);

            cur = nextTemp;
        }

        for (int i = 0; i < nodeList.size(); i++) {
            if (nodeList.get(i).val != nodeList.get(nodeList.size() - i - 1).val) {
                return false;
            }
        }
        return true;
    }

    public boolean isPalindrome2(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        if (fast != null) {
            slow = slow.next;
        }

        // 逆转右部分链表
        slow = reverse(slow);
        fast = head;

        while (slow != null) {
            if (fast.val != slow.val) {
                return false;
            }
            fast = fast.next;
            slow = slow.next;
        }
        return true;
    }

    private ListNode reverse(ListNode head) {
        ListNode prev = null;
        ListNode current = head;

        while (current != null) {
            ListNode nextTemp = current.next;
            current.next = prev;
            prev = current;
            current = nextTemp;
        }
        return prev;
    }

    /**
     * 输入: 1->2
     * 输出: false
     */
    @Test
    public void testCase1() {
        ListNode input = ListNode.of(new int[]{1, 2});

        Assert.assertFalse(isPalindrome(input));
    }

    /**
     * 输入: 1->2->2->1
     * 输出: true
     */
    @Test
    public void testCase2() {
        ListNode input = ListNode.of(new int[]{1, 2, 2, 1});

        Assert.assertTrue(isPalindrome(input));
    }

    /**
     * 输入: 1->2->2->1
     * 输出: true
     */
    @Test
    public void testCase3() {
        ListNode input = ListNode.of(new int[]{1, 3, 1});

        Assert.assertTrue(isPalindrome(input));
    }

    @Test
    public void testCase4() {
        ListNode input = ListNode.of(new int[]{1, 2});
        ListNode listNode = reverse(input);
    }

}

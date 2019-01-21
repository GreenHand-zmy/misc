package leetcode.linkedList;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class No141LinkedListCycle {
    public boolean hasCycle(ListNode head) {
        return hasCycle2(head);
    }

    private boolean hasCycle1(ListNode head) {
        Set<ListNode> nodeSet = new HashSet<>();

        while (head != null) {
            if (nodeSet.contains(head)) {
                return true;
            } else {
                nodeSet.add(head);
            }
            head = head.next;
        }
        return false;
    }

    private boolean hasCycle2(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (slow != fast) {
            if (fast == null || fast.next == null) {
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }

    @Test
    public void testCase1() {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next = head;
        Assert.assertTrue(hasCycle(head));
    }

    @Test
    public void testCase2() {
        ListNode head = ListNode.of(new int[]{3, 2, 0, -4});
        Assert.assertFalse(hasCycle(head));
    }
}

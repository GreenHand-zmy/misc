package leetcode.linkedList;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
 * <p>
 * 说明：不允许修改给定的链表。
 * <p>
 * 进阶：
 * 你是否可以不用额外空间解决此题？
 */
public class No142LinkedListCycleII {
    public ListNode detectCycle(ListNode head) {
        return detectCycle1(head);
    }

    private ListNode detectCycle1(ListNode head) {
        Map<ListNode, Integer> fact = new HashMap<>();
        while (head != null) {
            if (fact.containsKey(head)) {
                fact.put(head, fact.get(head) + 1);
            } else {
                fact.put(head, 1);
            }

            if (fact.get(head) == 2) {
                return head;
            }
            head = head.next;
        }

        return null;
    }

    private ListNode detectCycle2(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                break;
            }
        }
        if (fast == null || fast.next == null)
            return null;
        slow = head;
        while (slow != fast) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }
}

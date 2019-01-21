package leetcode.linkedList;

public class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }

    public static ListNode of(int[] data) {
        ListNode head = null;
        ListNode tail = null;

        for (int element : data) {
            if (head == null) {
                head = new ListNode(element);
                tail = head;
            } else {
                tail.next = new ListNode(element);
                tail = tail.next;
            }
        }
        return head;
    }

    public static boolean equals(ListNode list1, ListNode list2) {
        if (list1 == null || list2 == null) {
            return false;
        }

        if (list1 == list2) {
            return true;
        } else {
            ListNode listNode = list2;
            for (ListNode cur = list1; cur != null; cur = cur.next) {
                if (cur.val != listNode.val) {
                    return false;
                }
                listNode = listNode.next;
            }
        }
        return true;
    }
}
